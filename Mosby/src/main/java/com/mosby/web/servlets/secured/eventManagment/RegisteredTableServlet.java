package main.java.com.mosby.web.servlets.secured.eventManagment;

import main.java.com.mosby.controller.services.ReadGenericObjectService;
import main.java.com.mosby.controller.services.TicketsService;
import main.java.com.mosby.model.Event;
import main.java.com.mosby.model.Ticket;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/registeredTable")
@MultipartConfig
public class RegisteredTableServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("REGTABLE START");
		if (request.getParameter("eventId") != null) {
			if (request.getParameter("delete") != null){
				new TicketsService().delete(request, Integer.parseInt(request.getParameter("delete")));
			}
        	int eventId = Integer.parseInt(request.getParameter("eventId"));
        	Event event = new ReadGenericObjectService<>(Event.class).readById(eventId);
            request.setAttribute("event", event);
            List <Ticket> ticketsList = new ReadGenericObjectService<>(Ticket.class).readListByField("event_ref", eventId);
            request.setAttribute("tickets", ticketsList);
            request.getRequestDispatcher("/pages/eventManagement/registeredTable.jsp").forward(request, response);
        } else {
            response.sendRedirect("index");
        }
		
		
		
        
    }
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TicketsService ticketsService = new TicketsService();
        if (request.getParameter("delete") != null) {
        	ticketsService.delete(request);
        } else if (request.getParameter("check") != null) {
            ticketsService.check(request);
        } else if (request.getParameter("save") != null) {
            ticketsService.save(request);
        }
        int eventId = Integer.parseInt(request.getParameter("eventId"));
        response.sendRedirect("/Mosby/editEvent?eventId=" + eventId);
    }
}
