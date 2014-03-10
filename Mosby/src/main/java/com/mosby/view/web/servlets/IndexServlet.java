package main.java.com.mosby.view.web.servlets;

import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.controller.services.MainService;
import main.java.com.mosby.controller.services.ReadEventService;
import main.java.com.mosby.model.BaseUserInfo;
import main.java.com.mosby.model.EventCategorie;
import main.java.com.mosby.model.EventType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MainService mainService = new MainService();

	public IndexServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReadEventService readEventService = new ReadEventService();
		List<EventCategorie> listEventCategories = readEventService.readCategories();
		List<EventType> listEventTypes = readEventService.readTypes();
		
		
		HttpSession session = request.getSession(true);
		session.setAttribute("eventCategories", listEventCategories);
		session.setAttribute("eventTypes", listEventTypes);
		
		request.getRequestDispatcher("/pages/index.jsp").forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
