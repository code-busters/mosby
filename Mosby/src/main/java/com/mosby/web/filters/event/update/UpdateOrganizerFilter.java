package main.java.com.mosby.web.filters.event.update;

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
import main.java.com.mosby.model.Organizer;
import main.java.com.mosby.model.User;
import main.java.com.mosby.utils.ValidatorUtils;

@WebFilter("/UpdateOrganizerFilter")
public class UpdateOrganizerFilter implements Filter {

	public UpdateOrganizerFilter() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		if (request.getMethod().equals("POST")) {
			ValidatorUtils<Organizer> validatorUtils = null;
			if (!request.getParameter("language").equals("en")
					&& !request.getParameter("language").equals("uk")) {
				validatorUtils = new ValidatorUtils<>(Organizer.class, "en");
			} else {
				validatorUtils = new ValidatorUtils<>(Organizer.class,
						request.getParameter("language"));
			}

			String[] idArray = request.getParameterValues("organizerId");
			for (String string : idArray) {
				int id = Integer.parseInt(string);
				String name = request.getParameter("name_" + id);
				String email = request.getParameter("email_" + id);
				String phone = request.getParameter("phone_" + id);
				String about = request.getParameter("about_" + id);
				String site = request.getParameter("website_" + id);
				String googlePlus = request.getParameter("google_plus_" + id);
				String facebook = request.getParameter("facebook_" + id);
				String twitter = request.getParameter("twitter_" + id);
				Organizer changedOrganizer = new Organizer(name, email, phone,
						about, site, googlePlus, facebook, twitter);
				try {
					validatorUtils.validate(changedOrganizer);
				} catch (NoSuchMethodException | SecurityException
						| IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (validatorUtils.getErrors().isEmpty() == false) {

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

	}

}
