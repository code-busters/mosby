package main.java.com.mosby.web.servlets;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.services.calendar.CalendarScopes;
import main.java.com.mosby.controller.services.EventService;
import main.java.com.mosby.controller.services.GoogleCalendarService;
import main.java.com.mosby.controller.services.ReadGenericObjectService;
import main.java.com.mosby.model.Event;
import main.java.com.mosby.model.TicketInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;

@WebServlet("/eventPage")
public class EventPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("eventId") != null) {
        	String state = new BigInteger(130, new SecureRandom()).toString(32);
        	request.getSession().setAttribute("state", state);
        	 
    		String clientId = new GoogleCalendarService().getClientId();	
    		String redirectUrl = request.getScheme() + "://"
    				+ request.getServerName() + ":" + request.getServerPort()
    				+ request.getContextPath() + "/calendarServlet";   		
    		
    		GoogleAuthorizationCodeRequestUrl calendarRequestUrl = new GoogleAuthorizationCodeRequestUrl(clientId, redirectUrl, Collections.singleton(CalendarScopes.CALENDAR));
    		calendarRequestUrl.setApprovalPrompt("auto");
    		calendarRequestUrl.setState(state);
    		request.setAttribute("calendarURL", calendarRequestUrl.build());
    		
            int eventId = Integer.parseInt(request.getParameter("eventId"));
            Event event = new ReadGenericObjectService<>(Event.class).readById(eventId); 
            List <TicketInfo> ticketsInfoList = new EventService().readTicketInfo(eventId);
    		
            request.setAttribute("event", event);
            request.getSession().setAttribute("eventId", eventId);
            request.setAttribute("tickets", ticketsInfoList);
            request.getRequestDispatcher("/pages/eventPage.jsp").forward(request, response);
        } else {
            response.sendRedirect("index");
        }
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

    }
}