package main.java.com.mosby.controller.services;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.model.User;
import main.java.com.mosby.utils.FileUploadUtils;

public class UpdateUserService {
	private static final String DATE_FORMAT = "dd/MM/yyyy";
	private static final String USER_IMAGE_PATH = "media\\images\\users";
	private static Logger log = Logger.getLogger(UpdateUserService.class);
	
	public void update(HttpServletRequest request, HttpServlet servlet) throws IllegalStateException, IOException, ServletException{
		HttpSession session = request.getSession(false);
		ReflectionDao<User> usersDao = new ReflectionDao<>((Class<User>) User.class);
		
		int id = ((User) session.getAttribute("user")).getId();
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String email = ((User) session.getAttribute("user")).getEmail();
		String password = ((User) session.getAttribute("user")).getPassword();
		double credits = ((User) session.getAttribute("user")).getCredits();
		boolean admin = ((User) session.getAttribute("user")).isAdmin();
		
		
//		String userImage = "default.jpg";
//		Part filePart = request.getPart("profile_img");
//		try {
//			String contentType = filePart.getContentType();
//			if (contentType.startsWith("image")) {
//				File image = FileUploadUtils.uploadFile(servlet, USER_IMAGE_PATH, filePart);
//				userImage = FileUploadUtils.getFilename(image);
//			}
//		} catch (Exception e) {
//			log.error(e);
//		};
		
		String country = request.getParameter("country");
		String city = request.getParameter("city");
		Date birthDate = null;
		try {
			birthDate = new SimpleDateFormat(DATE_FORMAT).parse(request.getParameter("birthday"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String site = request.getParameter("website");
		String about = request.getParameter("about");
		String authenticationCode = ((User) session.getAttribute("user")).getAuthenticationCode();
		boolean active = ((User) session.getAttribute("user")).isActive();
		
		User user = new User(id, firstName, lastName, email, password, credits, admin, userImage, country, city, birthDate, site, about, authenticationCode, active);
		usersDao.updateObjects(user);
		session.setAttribute("user", user);
	}
}
