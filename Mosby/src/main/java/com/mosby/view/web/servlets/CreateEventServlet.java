package main.java.com.mosby.view.web.servlets;

import main.java.com.mosby.controller.services.CreateEventService;
import main.java.com.mosby.controller.services.ReadEventService;
import main.java.com.mosby.model.Event;
import main.java.com.mosby.utils.FileUploadUtils;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.File;
import java.io.IOException;

@WebServlet("/createEvent")
@MultipartConfig
public class CreateEventServlet extends HttpServlet {
    private static Logger log = Logger.getLogger(CreateEventServlet.class);
    private static final String EVENT_BACKGROUND_PATH = "media\\images\\events\\background";
    private static final String EVENT_LOGO_PATH = "media\\images\\events\\logo";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/createEvent.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Image uploading
        String eventLogo = "default.png";
        Part filePart = request.getPart("event_logo");
        try {
            String contentType = filePart.getContentType();
            if (contentType.startsWith("image")) {
                File image = FileUploadUtils.uploadFile(this, EVENT_LOGO_PATH, filePart);
                eventLogo = FileUploadUtils.getFilename(image);
            }
        } catch (Exception e) {
            log.error(e);
        }
        String eventBackground = "default.jpg";
        filePart = request.getPart("event_background");
        try {
            String contentType = filePart.getContentType();
            if (contentType.startsWith("image")) {
                File image = FileUploadUtils.uploadFile(this, EVENT_BACKGROUND_PATH, filePart);
                eventBackground = FileUploadUtils.getFilename(image);
            }
        } catch (Exception e) {
            log.error(e);
        }
        
        
        CreateEventService createEventService = new CreateEventService();
        ReadEventService readEventService = new ReadEventService();
    	try {
    		int eventId;
    		eventId = createEventService.create(request, this, eventLogo, eventBackground);
    		Event event = readEventService.readById(eventId);  
    		request.setAttribute("event", event);
            response.sendRedirect("/Mosby/eventPage?eventId=" + event.getId());
		} catch (Exception e) {
			log.error(e);
		}
    	
//    	request.getRequestDispatcher("/pages/eventPage.jsp").forward(request, response);

    }
}