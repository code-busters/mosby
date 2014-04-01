package main.java.com.mosby.view.web.servlets;

import main.java.com.mosby.controller.services.ReadGenericObjectService;
import main.java.com.mosby.controller.services.EventService;
import main.java.com.mosby.model.Event;
import main.java.com.mosby.model.EventCategory;
import main.java.com.mosby.model.EventType;
import main.java.com.mosby.model.Organizer;
import main.java.com.mosby.model.User;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/createEvent")
@MultipartConfig
public class CreateEventServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(CreateEventServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User sessionUser = (User) session.getAttribute("user");
        int userId = sessionUser.getId();
        List<Organizer> organizersList = new ReadGenericObjectService<>(Organizer.class).readListByField("user_ref", userId);
        List<EventType> listEventTypes = new ReadGenericObjectService<>(EventType.class).readList();
        request.setAttribute("eventTypes", listEventTypes);
		List<EventCategory> listEventCategories = new ReadGenericObjectService<>(EventCategory.class).readList();
		request.setAttribute("eventCategories", listEventCategories);
        request.setAttribute("organizers", organizersList);
        request.getRequestDispatcher("/pages/createEvent.jsp").forward(request, response);
    }

    @SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int eventId = new EventService().create(request, this);
            Event event = new ReadGenericObjectService<>(Event.class).readById(eventId);
            request.setAttribute("event", event);
            response.sendRedirect("eventPage?eventId=" + event.getId());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }
    }
}