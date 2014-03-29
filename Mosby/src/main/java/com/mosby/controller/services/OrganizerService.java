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

public class OrganizerService {
	private static final String ORGANIZER_LOGO_PATH = "media\\images\\organizers";
	private static Logger log = Logger.getLogger(OrganizerService.class);

	
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

	
	public void update(HttpServletRequest request, HttpServlet servlet) throws IllegalStateException, IOException, ServletException{
    	HttpSession session = request.getSession(false);
		
    	String [] idArray = request.getParameterValues("organizerId");
    	for (String string : idArray) {
    		int id = Integer.parseInt(string); 
    		User user = (User) session.getAttribute("user");
    		String name = request.getParameter("name_" + id);
    		String email = request.getParameter("email_" + id);
    		String phone = request.getParameter("phone_" + id);
    		String about = request.getParameter("about_" + id);
    		String site = request.getParameter("website_" + id);
    		String googlePlus = request.getParameter("google_plus_" + id);
    		String facebook = request.getParameter("facebook_" + id);
    		String twitter = request.getParameter("twitter_" + id);
    		
    		String logo = (new ReadGenericObjectService<Organizer>((Class<Organizer>) Organizer.class).readById(id)).getLogo();
    		Part filePart = request.getPart("logo_" + id);
    		try {
    			String contentType = filePart.getContentType();
    			if (contentType.startsWith("image")) {
    				File image = FileUploadUtils.uploadFile(servlet, ORGANIZER_LOGO_PATH, filePart);
    				logo = FileUploadUtils.getFilename(image);
    			}
    		} catch (Exception e) {
    			log.error(e);
    		}
    		
    		
    		Organizer changedOrganizer = new Organizer(id, user, name, email, phone, about, site, googlePlus, facebook, twitter, logo);
    		
    		ReflectionDao<Organizer> organizerDao = new ReflectionDao<>((Class<Organizer>) Organizer.class);
    		organizerDao.updateObjects(changedOrganizer);
			
		}
	}
}
