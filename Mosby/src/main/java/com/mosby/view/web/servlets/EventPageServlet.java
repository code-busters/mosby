package main.java.com.mosby.view.web.servlets;

import main.java.com.mosby.controller.services.ReadEventService;
import main.java.com.mosby.model.Event;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/eventPage")
public class EventPageServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("eventId") != null) {
            int eventId = Integer.parseInt(request.getParameter("eventId"));
            ReadEventService readEventService = new ReadEventService();
            Event event = readEventService.readById(eventId);
            request.setAttribute("event", event);
            request.getRequestDispatcher("/pages/eventPage.jsp").forward(request, response);
        } else {
            response.sendRedirect("/index");
        }
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

    }
}