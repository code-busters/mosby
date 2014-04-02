package main.java.com.mosby.web.servlets.secured;

import main.java.com.mosby.controller.services.ReadGenericObjectService;
import main.java.com.mosby.controller.services.EventService;
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

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(CreateEventServlet.class);

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/pages/createEvent.jsp").forward(request,
				response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			int eventId = new EventService().create(request, this);
			Event event = new ReadGenericObjectService<>(Event.class)
					.readById(eventId);
			request.setAttribute("event", event);
			response.sendRedirect("eventPage?eventId=" + event.getId());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}
}