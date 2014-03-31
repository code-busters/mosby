package main.java.com.mosby.view.web.servlets.userProfile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.com.mosby.controller.services.ReadGenericObjectService;
import main.java.com.mosby.controller.services.EventService;
import main.java.com.mosby.model.Event;
import main.java.com.mosby.model.Organizer;
import main.java.com.mosby.model.Ticket;
import main.java.com.mosby.model.TicketInfo;
import main.java.com.mosby.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/myEvents")
public class MyEventsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	EventService eventService = new EventService();
    	if (!(request.getParameter("delete") == null)){
    		eventService.deleteEvent(request);
    	}
    	request = eventService.readMyEvents(request);
    	request.getRequestDispatcher("/pages/userProfile/myEvents.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
