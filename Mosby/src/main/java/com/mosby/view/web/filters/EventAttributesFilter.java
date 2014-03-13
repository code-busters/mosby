package main.java.com.mosby.view.web.filters;

import main.java.com.mosby.controller.services.ReadEventService;
import main.java.com.mosby.model.EventCategory;
import main.java.com.mosby.model.EventType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebFilter("/EventAttributesFilter")
public class EventAttributesFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		System.out.println("Start EventAtributesFilter");
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);
		ReadEventService readEventService = null;
		
		if(session == null){
			System.out.println("Create session");
			session = request.getSession();
		}

		if (session.getAttribute("eventTypes") == null) {
			
			readEventService = new ReadEventService();
			List<EventType> listEventTypes = readEventService.readTypes();
			System.out.println(listEventTypes);
			session.setAttribute("eventTypes", listEventTypes);

		} if (session.getAttribute("eventCategories") == null){
			
			readEventService = new ReadEventService();
			List<EventCategory> listEventCategories = readEventService
					.readCategories();
			System.out.println(listEventCategories);
			session.setAttribute("eventCategories", listEventCategories);
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
