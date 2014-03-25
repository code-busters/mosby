package main.java.com.mosby.controller.services;

import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.model.User;
import main.java.com.mosby.utils.EncryptionUtils;
import main.java.com.mosby.utils.FileUploadUtils;
import main.java.com.mosby.utils.ValidatorUtils;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateUserService {
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final String USER_IMAGE_PATH = "media\\images\\users";
	private static Logger log = Logger.getLogger(UpdateUserService.class);

	public void update(HttpServletRequest request, HttpServlet servlet) throws IllegalStateException, IOException, ServletException {
		HttpSession session = request.getSession(false);
		ReflectionDao<User> usersDao = new ReflectionDao<>((Class<User>) User.class);

		User sessionUser = (User) session.getAttribute("user");

		int id = sessionUser.getId();
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String email = sessionUser.getEmail();
		String password = sessionUser.getPassword();
		System.out.println(password);
		double credits = sessionUser.getCredits();
		boolean admin = sessionUser.isAdmin();

		String userImage = sessionUser.getImage();
		Part filePart = request.getPart("profile_img");
		try {
			String contentType = filePart.getContentType();
			if (contentType.startsWith("image")) {
				File image = FileUploadUtils.uploadFile(servlet, USER_IMAGE_PATH, filePart);
				userImage = FileUploadUtils.getFilename(image);
			}
		} catch (Exception e) {
			log.error(e);
		}

		String country = request.getParameter("country");
		String city = request.getParameter("city");
		Date birthDate = sessionUser.getBirthDate();
		if (birthDate != null && birthDate.toString().equals(request.getParameter("birthday"))) {

		} else if (request.getParameter("birthday") != null) {
			try {
				birthDate = new SimpleDateFormat(DATE_FORMAT).parse(request.getParameter("birthday"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		String site = request.getParameter("website");
		String about = request.getParameter("about");
		String authenticationCode = sessionUser.getAuthenticationCode();
		boolean active = sessionUser.isActive();

		User user = new User(id, firstName, lastName, email, password,  "", credits, admin, userImage, country, city, birthDate, site, about, authenticationCode, active);
		// ValidatorUtils<User> validatorUtils = new
		// ValidatorUtils<>((Class<User>) user.getClass());
		// try {
		// user = validatorUtils.validate(user);
		// } catch (NoSuchMethodException | SecurityException
		// | IllegalAccessException | IllegalArgumentException
		// | InvocationTargetException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// System.out.println(validatorUtils.getErrors());
		usersDao.updateObjects(user);
		session.setAttribute("user", user);
	}

	public String changePassword(HttpServletRequest request) {
		String result = null;
		HttpSession session = request.getSession(false);
		ReflectionDao<User> usersDao = new ReflectionDao<>(
				(Class<User>) User.class);

		User sessionUser = (User) session.getAttribute("user");
		if ((request.getParameter("new_password")).equals(request
				.getParameter("confirm_password"))) {
			String currentPassword = request.getParameter("current_password");
			String correctHash = sessionUser.getPassword();
			if (EncryptionUtils.validatePassword(currentPassword, correctHash)) {
				String encryptedPassword = EncryptionUtils.createHash(request
						.getParameter("new_password"));
				sessionUser.setPassword(encryptedPassword);
				result = "password changed successfully";
			} else {
				result = "enter correct current password";
			}
		} else {
			result = "passwords don't match";
		}

		usersDao.updateObjects(sessionUser);
		session.setAttribute("user", sessionUser);
		return result;
	}
}
