package main.java.com.mosby.view.web.filters.event;

import main.java.com.mosby.controller.services.ReadGenericObjectService;
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

	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);

		if ((session.getAttribute("eventTypes") == null) || (session.getAttribute("eventCategories") == null)) {
			
			List<EventType> listEventTypes = new ReadGenericObjectService<EventType>((Class<EventType>) new EventType().getClass()).readList();
			session.setAttribute("eventTypes", listEventTypes);
			
			List<EventCategory> listEventCategories = new ReadGenericObjectService<EventCategory>((Class<EventCategory>) new EventCategory().getClass()).readList();
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