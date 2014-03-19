package main.java.com.mosby.view.web.filters;

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
import javax.servlet.http.HttpSession;

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
		HttpSession session = request.getSession(false);

		System.out.println("createEventFilter");

		if (session != null && session.getAttribute("user") != null) {

			Event event = new Event();
			
			ValidatorUtils<Event> validatorUtils = new ValidatorUtils<>(
					(Class<Event>) event.getClass(), "en");
			
			EventCategory eventCategory = null;
			EventType eventType = null;
			SimpleDateFormat parseDate = new SimpleDateFormat(
					"dd/MM/yyyy hh:mm");
			if (request.getMethod().equals("POST")) {
				String startTimestamp = request.getParameter("start_date")
						+ " " + request.getParameter("start_time");
				String endTimestamp = request.getParameter("end_date") + " "
						+ request.getParameter("end_time");

				eventCategory = new ReadGenericObjectService<EventCategory>(
						(Class<EventCategory>) new EventCategory().getClass())
						.readById(Integer.parseInt(request
								.getParameter("event_category")));
				eventType = new ReadGenericObjectService<EventType>(
						(Class<EventType>) new EventType().getClass())
						.readById(Integer.parseInt(request
								.getParameter("event_type")));

				Timestamp start = null;
				Timestamp end = null;
				try {
					start = new Timestamp(parseDate.parse(startTimestamp)
							.getTime());
					end = new Timestamp(parseDate.parse(endTimestamp).getTime());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				event = new Event(start, end, eventCategory, eventType);

				try {
					validatorUtils.validate(event);
				} catch (NoSuchMethodException | SecurityException
						| IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (event == null
						|| validatorUtils.getErrors().isEmpty() == false) {

					request.setAttribute("errors", validatorUtils.getErrors());
					System.out.println(validatorUtils.getErrors());
					request.getRequestDispatcher("/pages/createEvent.jsp")
							.forward(request, response);
				} else {
					chain.doFilter(request, response);
				}
			} else {
				request.getRequestDispatcher("/pages/createEvent.jsp").forward(
						request, response);
			}
		} else {
        	session.setAttribute("waitUrl", request.getRequestURL());
            response.sendRedirect(request.getContextPath() + "/login");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
