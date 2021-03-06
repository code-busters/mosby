package main.java.com.mosby.web.servlets;

import main.java.com.mosby.controller.services.ReadGenericObjectService;
import main.java.com.mosby.model.Event;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Logger log = Logger.getLogger(IndexServlet.class);

	public IndexServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Event> list = new ReadGenericObjectService<>(Event.class).readList();
		Collections.sort(list, new Comparator<Event>() {
			public int compare(Event o1, Event o2) {
				return o1.getStartDate().compareTo(o2.getStartDate());
			}
		});
		Date currentDate = new Date();
		Iterator<Event> iter = list.iterator();
		while (iter.hasNext()){
			Event currentEvent = iter.next();
			if (currentEvent.getStartDate().compareTo(currentDate) < 0 || currentEvent.isPrivacy()){
				iter.remove();
			}		
		}
		if (list.size()>12){
			list.subList(0, 10).clear();
		}
	
		request.setAttribute("eventList", list);     
		request.getRequestDispatcher("/pages/index.jsp").forward(request, response);
        
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
