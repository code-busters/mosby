package main.java.com.mosby.view.web.servlets;

import main.java.com.mosby.controller.services.CreateEventService;
import main.java.com.mosby.controller.services.ReadEventService;
import main.java.com.mosby.model.Event;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/createEvent")
@MultipartConfig
public class CreateEventServlet extends HttpServlet {
    private static Logger log = Logger.getLogger(CreateEventServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/createEvent.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        CreateEventService createEventService = new CreateEventService();
        ReadEventService readEventService = new ReadEventService();
    	try {
    		int eventId = createEventService.create(request, this);
    		Event event = readEventService.readById(eventId);  
    		request.setAttribute("event", event);
            response.sendRedirect("/Mosby/eventPage?eventId=" + event.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
//    	request.getRequestDispatcher("/pages/eventPage.jsp").forward(request, response);

    }
}