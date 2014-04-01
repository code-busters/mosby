package main.java.com.mosby.view.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.com.mosby.controller.services.FacebookUserGetDataService;
import main.java.com.mosby.controller.services.GoogleCalendarService;
import main.java.com.mosby.controller.services.UserService;
import main.java.com.mosby.model.User;


@WebServlet("/calendarServlet")
public class GoogleCalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GoogleCalendarServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GoogleCalendarService calendarService = new GoogleCalendarService();
		
		HttpSession session = request.getSession(false);
        String state = request.getParameter("state");
        String googleCode = request.getParameter("code");
        
        if(googleCode != null && state.equals(session.getId())) {  
        	
        	calendarService.addEvent(request, response, googleCode);
        } 
        response.sendRedirect("eventPage?eventId="+request.getSession().getAttribute("eventId"));
	}

}
