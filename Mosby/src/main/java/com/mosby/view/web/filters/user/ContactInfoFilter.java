package main.java.com.mosby.view.web.filters.user;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

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

import main.java.com.mosby.model.User;
import main.java.com.mosby.utils.ValidatorUtils;

@WebFilter("/ContactInfoFilters")
public class ContactInfoFilter implements Filter {

	public ContactInfoFilter() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);

		if (request.getMethod().equals("POST")) {

			User sessionUser = (User) session.getAttribute("user");

			int id = sessionUser.getId();
			String firstName = request.getParameter("first_name");
			String lastName = request.getParameter("last_name");
			String email = sessionUser.getEmail();
			String password = sessionUser.getPassword();
			String gender = request.getParameter("gender");
			double credits = sessionUser.getCredits();
			boolean admin = sessionUser.isAdmin();
			String userImage = sessionUser.getImage();
			String country = request.getParameter("country");
			String city = request.getParameter("city");
			String site = request.getParameter("website");
			String authenticationCode = sessionUser.getAuthenticationCode();
			boolean active = sessionUser.isActive();

			User user = new User(id, firstName, lastName, email, password,
					gender, credits, admin, userImage, country, city, null,
					site, "", authenticationCode, active);

			ValidatorUtils<User> validatorUtils = null;
			if (!request.getParameter("language").equals("en")
					&& !request.getParameter("language").equals("uk")) {
				validatorUtils = new ValidatorUtils<>(User.class, "en");
			} else {
				validatorUtils = new ValidatorUtils<>(User.class,
						request.getParameter("language"));
			}
			try {
				user = validatorUtils.validate(user);
			} catch (NoSuchMethodException | SecurityException
					| IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
			if (user == null || validatorUtils.getErrors().isEmpty() == false) {

				request.setAttribute("errors", validatorUtils.getErrors());
				request.getRequestDispatcher(
						"/pages/userProfile/contactInfo.jsp").forward(request,
						response);
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
