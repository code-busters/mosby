package main.java.com.mosby.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.mosby.controller.services.EventService;
import main.java.com.mosby.controller.services.ReadGenericObjectService;
import main.java.com.mosby.model.Event;
import main.java.com.mosby.model.EventCategory;
import main.java.com.mosby.model.EventType;

import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<EventType> listEventTypes = new ReadGenericObjectService<>(EventType.class).readList();
        request.setAttribute("eventTypes", listEventTypes);
		List<EventCategory> listEventCategories = new ReadGenericObjectService<>(EventCategory.class).readList();
		request.setAttribute("eventCategories", listEventCategories);
        List<Event> events = new EventService().search(request);
        request.setAttribute("events", events);
		request.getRequestDispatcher("/pages/search.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<EventType> listEventTypes = new ReadGenericObjectService<>(EventType.class).readList();
        request.setAttribute("eventTypes", listEventTypes);
		List<EventCategory> listEventCategories = new ReadGenericObjectService<>(EventCategory.class).readList();
		request.setAttribute("eventCategories", listEventCategories);
		List<Event> events = new EventService().search(request);
		request.setAttribute("events", events);
		request.getRequestDispatcher("/pages/search.jsp").forward(request, response);
    }
}
