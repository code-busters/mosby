package main.java.com.mosby.controller.services;

import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.model.Event;
import main.java.com.mosby.model.Organizer;
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
		new ReflectionDao<>(Organizer.class).insertObjects(organizer);
	}

	public void createDefaultOrganizer(User user){
		
        String name = user.getFirstName() + " " + user.getLastName();
		String email = "office@mosby.com";
		String phone = "+38 032 222 22 22";
        String about = "At Mosby, we believe that gathering with others is the best way for people to learn, grow, get inspired, feel connected, get healthy, give back, and celebrate. So we're building technology that facilitates those gatherings, by helping people find and attend events that feed their interests, while connecting them with others who share their passions.When we indulge our curiosity, events can transform us and make us better.";
		String site = "www.mosby.com";
		String googlePlus = "plus.google.com";
		String facebook = "facebook.com";
		String twitter = "twitter.com";
		String logo =  "default.png";
		Organizer organizer = new Organizer(user, name, email, phone, about, site, googlePlus, facebook, twitter, logo);
		new ReflectionDao<>(Organizer.class).insertObjects(organizer);
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
    		
    		String logo = (new ReadGenericObjectService<>(Organizer.class).readById(id)).getLogo();
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
    		
    		ReflectionDao<Organizer> organizerDao = new ReflectionDao<>(Organizer.class);
    		organizerDao.updateObjects(changedOrganizer);
			
		}
	}
	
	public void delete (User user, int id){
		if (user.getId() == new ReadGenericObjectService<>(Organizer.class).readById(id).getUser().getId() && new ReadGenericObjectService<>(Event.class).readListByField("organizer_ref=", id).isEmpty()){
			Organizer organizer = new ReadGenericObjectService<>(Organizer.class).readById(id);
			new ReflectionDao<>(Organizer.class).deleteObjects(organizer);
		}
	}
}