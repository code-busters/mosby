package main.java.com.mosby.web.servlets.api;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.controller.services.ReadGenericObjectService;
import main.java.com.mosby.controller.services.TicketsService;
import main.java.com.mosby.model.Api;
import main.java.com.mosby.model.Event;
import main.java.com.mosby.model.Organizer;
import main.java.com.mosby.model.Ticket;
import main.java.com.mosby.model.User;
import main.java.com.mosby.model.api.Attendee;
import main.java.com.mosby.model.api.CheckinResult;

import com.google.gson.Gson;

@WebServlet("/api/*")
public class ApiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Gson GSON = new Gson();
	

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String sig = request.getParameter("sig");
        Api api = new ReadGenericObjectService<>(Api.class).readListByField("code=", sig).get(0);
        if (api == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().print("Unauthorised");
            return;
        }

        Organizer organizer = api.getOrganizer();
        ReflectionDao<Event> eventDao = new ReflectionDao<>(Event.class);

        List<Event> eventList = eventDao.selectObjects(3, "organizer_ref=", organizer.getId());
        Collections.sort(eventList, new Comparator<Event>() {
            public int compare(Event o1, Event o2) {
                return o1.getStartDate().compareTo(o2.getStartDate());
            }
        });
        Date currentDate = new Date();
        Iterator<Event> iter = eventList.iterator();
        while (iter.hasNext()) {
            Event currentEvent = iter.next();
            if (currentEvent.getStartDate().compareTo(currentDate) < 0) {
                iter.remove();
            }
        }
        Event closestEvent = eventList.get(0);


        if (request.getRequestURI().endsWith("closestEvent")) {
            response.getWriter().print(GSON.toJson(new main.java.com.mosby.model.api.Event(String.valueOf(closestEvent.getId()), closestEvent.getName())));
        } else if (request.getRequestURI().endsWith("checkin")) {
            String code = request.getParameter("registrationId");
            int ticketId = Integer.parseInt(code.substring(0, code.length() - 1));
            Ticket ticket = new ReadGenericObjectService<>(Ticket.class).readById(ticketId);
            new TicketsService().check(ticketId);
            User user = ticket.getUser();

            Attendee attendee = new Attendee(String.valueOf(user.getId()), user.getFirstName(), user.getLastName(), ticket.getTicketInfo().getName(), user.getImage());
            response.getWriter().print(GSON.toJson(new CheckinResult(CheckinResult.Result.CHECKED_IN, attendee)));
        }
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

    }
}
