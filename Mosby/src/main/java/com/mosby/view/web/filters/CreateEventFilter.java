package main.java.com.mosby.view.web.filters;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import main.java.com.mosby.model.Event;
import main.java.com.mosby.model.User;
import main.java.com.mosby.utils.ValidatorUtils;

@WebFilter("/CreateEventFilter")
public class CreateEventFilter implements Filter {

	private static final String DATE_FORMAT = "dd/MM/yyyy";
	private static final String TIME_FORMAT = "HH:mm";

	public CreateEventFilter() {
		// TODO Auto-generated constructor stub
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);

		System.out.println("createEventFilter");
		Event event = new Event();
		ValidatorUtils<Event> validatorUtils = new ValidatorUtils<>(
				(Class<Event>) event.getClass());

		if (session != null && session.getAttribute("user") != null) {
			
		
			SimpleDateFormat parseDate = new SimpleDateFormat("dd/MM/yyyy hh:mm");
	        if(request.getParameter("start_date") != null && request.getParameter("start_time") != null &&
	        		request.getParameter("end_date") != null && request.getParameter("end_time") != null){
	        String startTimestamp = request.getParameter("start_date") + " " + request.getParameter("start_time");
	        String endTimestamp = request.getParameter("end_date") + " " + request.getParameter("end_time");

	        Timestamp start = null;
	        Timestamp end = null;
	        try {
	            start = new Timestamp(parseDate.parse(startTimestamp).getTime());
	            end = new Timestamp(parseDate.parse(endTimestamp).getTime());
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        event = new Event(start, end);
	        
	        try {
				validatorUtils.validate(event);
			} catch (NoSuchMethodException | SecurityException
					| IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        }
	        
	        
			if (event == null || validatorUtils.getErrors().isEmpty() == false) {

				request.setAttribute("errors", validatorUtils.getErrors());
				System.out.println(validatorUtils.getErrors());
				request.getRequestDispatcher("/pages/createEvent.jsp").forward(
						request, response);
			}
			else {
				chain.doFilter(request, response);
			}
		} else {

		}
	}
				

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
