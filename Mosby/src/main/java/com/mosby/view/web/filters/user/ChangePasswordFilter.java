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

@WebFilter("/ChangePasswordFilter")
public class ChangePasswordFilter implements Filter {

	public ChangePasswordFilter() {
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
		if (request.getMethod().equals("POST")) {

			User sessionUser = (User) session.getAttribute("user");

			String firstName = sessionUser.getFirstName();
			String lastName = sessionUser.getLastName();
			String email = sessionUser.getEmail();
			String newPassword = request.getParameter("new_password");
			String confirmPassword = request.getParameter("confirm_password");

			User user = new User(firstName, lastName, email, newPassword);

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
				validatorUtils.checkConfirmPass(newPassword, confirmPassword);
			} catch (NoSuchMethodException | SecurityException
					| IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
			if (user == null || validatorUtils.getErrors().isEmpty() == false) {

				request.setAttribute("errors", validatorUtils.getErrors());
				request.getRequestDispatcher(
						"/pages/userProfile/changePassword.jsp").forward(
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
