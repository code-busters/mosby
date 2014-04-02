package main.java.com.mosby.view.web.servlets.userProfile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.mosby.controller.services.EventService;
import java.io.IOException;


@WebServlet("/myEvents")
public class MyEventsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	EventService eventService = new EventService();
    	if (!(request.getParameter("delete") == null)){
    		eventService.deleteEvent(request, Integer.parseInt(request.getParameter("delete")));
    	}
    	request = eventService.readMyEvents(request);
    	request.getRequestDispatcher("/pages/userProfile/myEvents.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
