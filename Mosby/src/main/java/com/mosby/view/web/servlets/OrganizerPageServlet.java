package main.java.com.mosby.view.web.servlets;

import main.java.com.mosby.controller.services.ReadGenericObjectService;
import main.java.com.mosby.model.Event;
import main.java.com.mosby.model.Organizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/organizerPage")
public class OrganizerPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id") != null) {
            int organizerId = Integer.parseInt(request.getParameter("id"));
            Organizer organizer = new ReadGenericObjectService<Organizer>((Class<Organizer>) Organizer.class).readById(organizerId);
            List<Event> eventsList = new ReadGenericObjectService<Event>((Class<Event>) Event.class).readListByField("organizer_ref", (Integer)organizerId);
            request.setAttribute("organizer", organizer);
            request.setAttribute("events", eventsList);
            request.getRequestDispatcher("/pages/organizerPage.jsp").forward(request, response);
        } else {
            response.sendRedirect("index");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
