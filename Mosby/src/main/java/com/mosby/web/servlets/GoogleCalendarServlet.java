package main.java.com.mosby.web.servlets;

import main.java.com.mosby.controller.services.GoogleCalendarService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/calendarServlet")
public class GoogleCalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String state = request.getParameter("state");
		String googleCode = request.getParameter("code");

		if (googleCode != null && state.equals(request.getSession().getAttribute("state"))) {
            new GoogleCalendarService().addEvent(request, response, googleCode);
		}
		response.sendRedirect("eventPage?eventId="
				+ request.getSession().getAttribute("eventId"));
	}

}
