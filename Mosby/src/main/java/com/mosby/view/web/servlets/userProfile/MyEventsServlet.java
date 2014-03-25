package main.java.com.mosby.view.web.servlets.userProfile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.com.mosby.controller.services.ReadGenericObjectService;
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
    	HttpSession session = request.getSession(false);
    	User sessionUser = (User) session.getAttribute("user");
    	int userId = sessionUser.getId();
    	List <Organizer> organizersList = new ReadGenericObjectService<Organizer>((Class<Organizer>) new Organizer().getClass()).readListByField("user_ref", (Integer)userId);
    	List <Event> myEvents = new ArrayList<>();
    	for (Organizer organizer : organizersList) {
    		List <Event> currentEvents = new ReadGenericObjectService<Event>((Class<Event>) new Event().getClass()).readListByField("organizer_ref", organizer.getId());
    		for (Event event : currentEvents) {
    			String atributeNameTickets = "tickets_" + event.getId(); 
    			int allTickets = 0;
    			List <TicketInfo> ticketsInfo = new ReadGenericObjectService<TicketInfo>((Class<TicketInfo>) new TicketInfo().getClass()).readListByField("event_ref", event.getId());
    			for (TicketInfo ticketInfo : ticketsInfo) {
    				allTickets = allTickets + ticketInfo.getMaxNumber();
				}
    			request.setAttribute(atributeNameTickets, allTickets);
    			
    			String atributeNameTicketsSold = "tickets_sold_" + event.getId();
    			List <Ticket> tickets = new ReadGenericObjectService<Ticket>((Class<Ticket>) new Ticket().getClass()).readListByField("event_ref", event.getId());
    			int ticketsSold = tickets.size();
    			request.setAttribute(atributeNameTicketsSold, ticketsSold);
			}
    		myEvents.addAll(currentEvents);
    	}
    	request.setAttribute("events", myEvents);
    	request.getRequestDispatcher("/pages/userProfile/myEvents.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
