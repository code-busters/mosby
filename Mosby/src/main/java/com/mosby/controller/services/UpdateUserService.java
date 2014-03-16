package main.java.com.mosby.controller.services;

import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.model.User;
import main.java.com.mosby.utils.FileUploadUtils;
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

public class UpdateUserService {
	private static final String DATE_FORMAT = "dd/MM/yyyy";
	private static final String USER_IMAGE_PATH = "media\\images\\users";
	private static Logger log = Logger.getLogger(UpdateUserService.class);
	
	public void update(HttpServletRequest request, HttpServlet servlet) throws IllegalStateException, IOException, ServletException{
		HttpSession session = request.getSession(false);
		ReflectionDao<User> usersDao = new ReflectionDao<>((Class<User>) User.class);

        User sessionUser = (User) session.getAttribute("user");
		
		int id = sessionUser.getId();
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String email = sessionUser.getEmail();
		String password = sessionUser.getPassword();
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
		};
		
		String country = request.getParameter("country");
		String city = request.getParameter("city");
		Date birthDate = null;
		try {
			birthDate = new SimpleDateFormat(DATE_FORMAT).parse(request.getParameter("birthday"));
		} catch (ParseException e) {
			e.printStackTrace();
            log.error(e);
		}
		String site = request.getParameter("website");
		String about = request.getParameter("about");
		String authenticationCode = sessionUser.getAuthenticationCode();
		boolean active = sessionUser.isActive();
		
		User user = new User(id, firstName, lastName, email, password, credits, admin, userImage, country, city, birthDate, site, about, authenticationCode, active);
		usersDao.updateObjects(user);
		session.setAttribute("user", user);
	}
}