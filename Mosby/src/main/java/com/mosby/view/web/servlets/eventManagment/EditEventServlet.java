package main.java.com.mosby.view.web.servlets.eventManagment;

import main.java.com.mosby.controller.services.ReadGenericObjectService;
import main.java.com.mosby.controller.services.EventService;
import main.java.com.mosby.model.Event;
import main.java.com.mosby.model.Organizer;
import main.java.com.mosby.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/editEvent")
@MultipartConfig
public class EditEventServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("eventId") != null) {
            HttpSession session = request.getSession(false);
            User sessionUser = (User) session.getAttribute("user");
            int userId = sessionUser.getId();
            int eventId = Integer.parseInt(request.getParameter("eventId"));
            Event event = new ReadGenericObjectService<>(Event.class).readById(eventId);
            List<Organizer> organizersList = new ReadGenericObjectService<>(Organizer.class).readListByField("user_ref", userId);
            request.setAttribute("organizers", organizersList);
            request.setAttribute("event", event);
            request.getRequestDispatcher("/pages/eventManagement/editEvent.jsp").forward(request, response);
        } else {
            response.sendRedirect("index");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println(request.getParameter("eventId"));
    	System.out.println(request.getParameter("event_name"));
    	if (request.getParameter("eventId") != null) {
	    	int eventId = Integer.parseInt(request.getParameter("eventId"));
	    	new EventService().updateEvent(request, this);
	    	response.sendRedirect("editEvent?eventId=" + eventId);
    	} else {
    		response.sendRedirect("index");
        }
    	
    }

}
