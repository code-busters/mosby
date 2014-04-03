package main.java.com.mosby.web.filters.event;

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

@WebFilter("/OrganizerFilter")
public class OrganizerFilter implements Filter {

    public OrganizerFilter() {
        
    }

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (request.getMethod().equals("POST")) {
            ValidatorUtils<Event> validatorUtils = null;
            if (!request.getParameter("language").equals("en") && !request.getParameter("language").equals("uk")) {
                validatorUtils = new ValidatorUtils<>(Event.class, "en");
            } else {
                validatorUtils = new ValidatorUtils<>(Event.class, request.getParameter("language"));
            }

            SimpleDateFormat parseDate = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            String startTimestamp = request.getParameter("start_date") + " " + request.getParameter("start_time");
            String endTimestamp = request.getParameter("end_date") + " " + request.getParameter("end_time");

            EventCategory eventCategory = new ReadGenericObjectService<>(EventCategory.class).readById(Integer.parseInt(request.getParameter("event_category")));
            EventType eventType = new ReadGenericObjectService<>(EventType.class).readById(Integer.parseInt(request.getParameter("event_type")));
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
            Event event = new Event(name, start, end, eventCategory, eventType, location);

            try {
                validatorUtils.validate(event);
            } catch (NoSuchMethodException | SecurityException
                    | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {

                e.printStackTrace();
            }

            if (event == null || validatorUtils.getErrors().isEmpty() == false) {

                request.setAttribute("errors", validatorUtils.getErrors());
                request.setAttribute("event_name", request.getParameter("event_name"));
                request.setAttribute("start_date", request.getParameter("start_date"));
                request.setAttribute("start_time", request.getParameter("start_time"));
                request.setAttribute("end_date", request.getParameter("end_date"));
                request.setAttribute("end_time", request.getParameter("end_time"));
                request.setAttribute("event_category", request.getParameter("event_category"));
                request.setAttribute("event_type", request.getParameter("event_type"));
                request.setAttribute("event_description", request.getParameter("event_description"));
                request.setAttribute("event_location", request.getParameter("event_location"));
                request.setAttribute("privacy_event", request.getParameter("privacy_event"));
                request.setAttribute("organizer", request.getParameter("organizer"));
                request.getRequestDispatcher("/pages/createEvent.jsp").forward(
                        request, response);
            } else {
                chain.doFilter(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
