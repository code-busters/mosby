package main.java.com.mosby.view.web.servlets.eventManagment;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.mosby.controller.services.ReadGenericObjectService;
import main.java.com.mosby.model.Event;
import main.java.com.mosby.model.Ticket;
import main.java.com.mosby.model.TicketInfo;

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
        	int eventId = Integer.parseInt(request.getParameter("eventId"));
        	Event event = new ReadGenericObjectService<Event>((Class<Event>) new Event().getClass()).readById(eventId);
            request.setAttribute("event", event);
            List <Ticket> ticketsList = new ReadGenericObjectService<Ticket>((Class<Ticket>) new Ticket().getClass()).readListByField("event_ref", (Integer)eventId);
            request.setAttribute("tickets", ticketsList);
            request.getRequestDispatcher("/pages/eventManagement/registeredTable.jsp").forward(request, response);
        } else {
            response.sendRedirect("index");
        }
		
		
		
        
    }
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] users = request.getParameterValues("checked_tickets");
        if (request.getParameter("delete") != null) {
            deleteUsers(request, users);
        } else if (request.getParameter("update") != null) {
            updateUsers(request, users);
        } else if (request.getParameter("save") != null) {
            saveUsers(request, users);
        }
        int eventId = Integer.parseInt(request.getParameter("eventId"));
        response.sendRedirect("/Mosby/editEvent?eventId=" + eventId);
    }

    public void deleteUsers(HttpServletRequest request, String[] users) throws ServletException, IOException {

    }

    public void updateUsers(HttpServletRequest request, String[] users) throws ServletException, IOException {

    }

    public void saveUsers(HttpServletRequest request, String[] users) throws ServletException, IOException {

    }
}
