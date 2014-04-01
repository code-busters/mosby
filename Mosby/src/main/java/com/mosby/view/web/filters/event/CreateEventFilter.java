package main.java.com.mosby.view.web.filters.event;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.mosby.controller.services.ReadGenericObjectService;
import main.java.com.mosby.model.Event;
import main.java.com.mosby.model.EventCategory;
import main.java.com.mosby.model.EventType;
import main.java.com.mosby.utils.ValidatorUtils;

@WebFilter("/CreateEventFilter")
public class CreateEventFilter implements Filter {

	public CreateEventFilter() {
		// TODO Auto-generated constructor stub
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		if (request.getMethod().equals("POST")) {
			Event event = null;

			ValidatorUtils<Event> validatorUtils = null;
			if (!request.getParameter("language").equals("en")&&!request.getParameter("language").equals("uk")) {
				validatorUtils = new ValidatorUtils<>(Event.class, "en");
			} else {
				validatorUtils = new ValidatorUtils<>(Event.class, request.getParameter("language"));
			}

			EventCategory eventCategory = null;
			EventType eventType = null;
			SimpleDateFormat parseDate = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			String startTimestamp = request.getParameter("start_date") + " " + request.getParameter("start_time");
			String endTimestamp = request.getParameter("end_date") + " " + request.getParameter("end_time");

			eventCategory = new ReadGenericObjectService<EventCategory>((Class<EventCategory>) new EventCategory().getClass()).readById(Integer.parseInt(request.getParameter("event_category")));
			eventType = new ReadGenericObjectService<EventType>((Class<EventType>) new EventType().getClass()).readById(Integer.parseInt(request.getParameter("event_type")));
			String name = request.getParameter("event_name");
			String location = request.getParameter("event_location");

			Timestamp start = null;
			Timestamp end = null;
			try {
				start = new Timestamp(parseDate.parse(startTimestamp).getTime());
				end = new Timestamp(parseDate.parse(endTimestamp).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			event = new Event(name, start, end, eventCategory, eventType, location);

			try {
				validatorUtils.validate(event);
			} catch (NoSuchMethodException | SecurityException
					| IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {

				e.printStackTrace();
			}

			if (event == null || validatorUtils.getErrors().isEmpty() == false) {

				request.setAttribute("errors", validatorUtils.getErrors());
				request.getRequestDispatcher("/pages/createEvent.jsp").forward(
						request, response);
			} else {
				chain.doFilter(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
