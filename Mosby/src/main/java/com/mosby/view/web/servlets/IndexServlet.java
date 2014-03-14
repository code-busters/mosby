package main.java.com.mosby.view.web.servlets;

import main.java.com.mosby.controller.services.MainService;
import main.java.com.mosby.controller.services.ReadGenericObjectService;
import main.java.com.mosby.model.Event;
import main.java.com.mosby.model.User;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Logger log = Logger.getLogger(IndexServlet.class);
	private MainService mainService = new MainService();

	public IndexServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		List<Event> list = new ReadGenericObjectService<Event>((Class<Event>) new Event().getClass()).readList();
		request.setAttribute("eventList", list);
		
		System.out.println(request.getAttribute("eventList"));
		
//	for test
		User user = new ReadGenericObjectService<User>((Class<User>) new User().getClass()).readById(2);
		System.out.println(user);
		
		
        request.getRequestDispatcher("/pages/index.jsp").forward(request, response);
        
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
