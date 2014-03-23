package main.java.com.mosby.view.web.servlets;

import main.java.com.mosby.controller.services.ReadGenericObjectService;
import main.java.com.mosby.model.Event;
import main.java.com.mosby.model.Ticket;
import main.java.com.mosby.utils.MailUtils;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import java.io.IOException;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Logger log = Logger.getLogger(IndexServlet.class);

	public IndexServlet() {
		super();

	}

	//@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Event> list = new ReadGenericObjectService<Event>((Class<Event>) new Event().getClass()).readList();
		request.setAttribute("eventList", list);
		System.out.println(request.getAttribute("eventList"));
		
//		Ticket ticket = (Ticket) new ReadGenericObjectService<Ticket>((Class<Ticket>) new Ticket().getClass()).readList().get(0);
//		System.out.println(ticket);
		new MailUtils().sendMessage("AlexHamer777@gmail.com", "...");
		System.out.println("WORKS");
        request.getRequestDispatcher("/pages/index.jsp").forward(request, response);
        
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
