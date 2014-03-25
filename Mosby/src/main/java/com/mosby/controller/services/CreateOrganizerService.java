package main.java.com.mosby.controller.services;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.model.Event;
import main.java.com.mosby.model.Organizer;
import main.java.com.mosby.model.User;
import main.java.com.mosby.utils.FileUploadUtils;

public class CreateOrganizerService {
	private static final String ORGANIZER_LOGO_PATH = "media\\images\\organizers";
	private static Logger log = Logger.getLogger(CreateOrganizerService.class);
	
	public void create(HttpServletRequest request, HttpServlet servlet) throws IllegalStateException, IOException, ServletException{
		HttpSession session = request.getSession(false);
		
		User user = (User) session.getAttribute("user");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String about = request.getParameter("about");
		String site = request.getParameter("website");
		String googlePlus = request.getParameter("google+plus");
		String facebook = request.getParameter("facebook");
		String twitter = request.getParameter("twitter");
		 
		String logo =  "default.png";
		Part filePart = request.getPart("logo");
		try {
			String contentType = filePart.getContentType();
			if (contentType.startsWith("image")) {
				File image = FileUploadUtils.uploadFile(servlet, ORGANIZER_LOGO_PATH, filePart);
				logo = FileUploadUtils.getFilename(image);
			}
		} catch (Exception e) {
			log.error(e);
		}
		
		Organizer organizer = new Organizer(user, name, email, phone, about, site, googlePlus, facebook, twitter, logo);
		
		ReflectionDao<Organizer> organizerDao = new ReflectionDao<>((Class<Organizer>) Organizer.class);
		organizerDao.insertObjects(organizer);
	}
}
