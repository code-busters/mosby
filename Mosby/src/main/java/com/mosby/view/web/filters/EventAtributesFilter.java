package main.java.com.mosby.view.web.filters;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.com.mosby.controller.services.ReadEventService;
import main.java.com.mosby.model.EventCategorie;
import main.java.com.mosby.model.EventType;


@WebFilter("/EventAtributesFilter")
public class EventAtributesFilter implements Filter {


	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();
		
		if (session.getAttribute("eventTypes") == null || session.getAttribute("eventCategories") == null) {
            ReadEventService readEventService = new ReadEventService();
            List<EventCategorie> listEventCategories = readEventService.readCategories();
            List<EventType> listEventTypes = readEventService.readTypes();

            session.setAttribute("eventCategories", listEventCategories);
            session.setAttribute("eventTypes", listEventTypes);
			
			chain.doFilter(request, response);
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
