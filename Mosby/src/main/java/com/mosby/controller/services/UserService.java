package main.java.com.mosby.controller.services;

import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.model.User;
import main.java.com.mosby.utils.EncryptionUtils;
import main.java.com.mosby.utils.FileUploadUtils;
import main.java.com.mosby.utils.MailUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserService {
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final String USER_IMAGE_PATH = "media\\images\\users";
	private static Logger log = Logger.getLogger(UserService.class);

	public User readUser(String email, String password) {
		User user = new User();
		ReflectionDao<User> usersDao = new ReflectionDao<>(User.class);

		if (!usersDao.selectObjects(5,"email=", email).isEmpty()) {
			user = usersDao.selectObjects(5,"email=", email).get(0);
			if(!user.isActive()){
				return null;
			}

			String correctHash = user.getPassword();

			if (!EncryptionUtils.validatePassword(password, correctHash)) {
				user = null;
				System.out.println("login fail");
			}
			return user;

		} else {
			return null;
		}
	}

	public User readSocialUser(User user){
		ReflectionDao<User> usersDao = new ReflectionDao<>(User.class);
		
		System.out.println(user.toString());
		
		if (!usersDao.selectObjects(5,"email=", user.getEmail()).isEmpty()) {
			User usr = usersDao.selectObjects(5,"email=", user.getEmail()).get(0);

			user.setId(usr.getId());
			user.setCity(usr.getCity());
			user.setCountry(usr.getCountry());
			user.setPassword(usr.getPassword());
			user.setSite(usr.getSite());
			user.setAbout(usr.getAbout());
			user.setActive(true);
			usersDao.updateObjects(user);

			return user;

		} else {
			return null;
		}
	}

	
	
	

	public void update(HttpServletRequest request, HttpServlet servlet)
			throws IllegalStateException, IOException, ServletException {
		HttpSession session = request.getSession(false);
		ReflectionDao<User> usersDao = new ReflectionDao<>(User.class);

		User sessionUser = (User) session.getAttribute("user");

		int id = sessionUser.getId();
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String email = sessionUser.getEmail();
		String password = sessionUser.getPassword();
		String gender = request.getParameter("gender");
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
		if (!request.getParameter("birthday").equals("")) {
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

		User user = new User(id, firstName, lastName, email, password, gender,
				credits, admin, userImage, country, city, birthDate, site,
				about, authenticationCode, active);

		usersDao.updateObjects(user);
		user = new ReadGenericObjectService<>(User.class).readById(user.getId());
		session.removeAttribute("user");
		session.setAttribute("user", user);
	}

	public boolean changePassword(HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		ReflectionDao<User> usersDao = new ReflectionDao<>(User.class);

		User sessionUser = (User) session.getAttribute("user");

		String currentPassword = request.getParameter("current_password");
		String correctHash = sessionUser.getPassword();
		if (EncryptionUtils.validatePassword(currentPassword, correctHash)) {
			String encryptedPassword = EncryptionUtils.createHash(request
					.getParameter("new_password"));
			sessionUser.setPassword(encryptedPassword);
			usersDao.updateObjects(sessionUser);
			session.setAttribute("user", sessionUser);
			return true;
		} else {
			return false;
		}
	}

	public User signUpUser(String firstName, String lastName, String email,
			String password) {
        ReflectionDao<User> usersDao = new ReflectionDao<>(User.class);

		if (!usersDao.selectObjects(1,"email=", email).isEmpty()) {
			System.out.println("signUp fail! change email!");
			return null;
		} else {
			String encryptedPassword = EncryptionUtils.createHash(password);
            String authentication = EncryptionUtils.generateSecureRandom(24) + EncryptionUtils.toHex(email.getBytes());

            User user = new User(firstName, lastName, email, encryptedPassword, authentication, false);
			new OrganizerService().createDefaultOrganizer(user);
			usersDao.insertObjects(user);
			
			new MailUtils().sendMessage(email, authentication);
			user = usersDao.selectObjects(1,"email=", email).get(0);

			return user;
		}
	}

	public User socialSignUpUser(User user) {
        ReflectionDao<User> usersDao = new ReflectionDao<>(User.class);

        if (!usersDao.selectObjects(1,"email=", user.getEmail()).isEmpty()) {
            return null;
        } else {
            usersDao.insertObjects(user);
            new OrganizerService().createDefaultOrganizer(user);
            user = usersDao.selectObjects(1,"email=", user.getEmail()).get(0);
            return user;
        }
	}

}
