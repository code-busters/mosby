package main.java.com.mosby.view.web.filters;

import main.java.com.mosby.controller.services.ReadEventService;
import main.java.com.mosby.model.EventCategorie;
import main.java.com.mosby.model.EventType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebFilter("/EventAtributesFilter")
public class EventAtributesFilter implements Filter {


	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		System.out.println("Start EventAtributesFilter");
		HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(true);
		
		if (session.getAttribute("eventTypes") == null || session.getAttribute("eventCategories") == null) {
            ReadEventService readEventService = new ReadEventService();
            List<EventCategorie> listEventCategories = readEventService.readCategories();
            List<EventType> listEventTypes = readEventService.readTypes();

            session.setAttribute("eventCategories", listEventCategories);
            session.setAttribute("eventTypes", listEventTypes);
            System.out.println("Added event atributes to session");
        }
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
