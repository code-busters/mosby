package main.java.com.mosby.view.web.filters;

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

@WebFilter("/SignUpFilter")
public class SignUpFilter implements Filter {

	public SignUpFilter() {
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

		if (session == null || session.getAttribute("user") == null) {

			String firstName = request.getParameter("first_name");
			String lastName = request.getParameter("last_name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String confirmPassword = request.getParameter("confirm_password");

			User user = new User(firstName, lastName, email, password);
			ValidatorUtils<User> validatorUtils = new ValidatorUtils<>(
					(Class<User>) user.getClass());
			try {
				user = validatorUtils.validate(user);
				if (password != null && confirmPassword != null) {
					validatorUtils.checkConfirmPass(password, confirmPassword);
				}
			} catch (NoSuchMethodException | SecurityException
					| IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}

			if (user == null || validatorUtils.getErrors().isEmpty() == false) {

				request.setAttribute("errors", validatorUtils.getErrors());
				System.out.println(validatorUtils.getErrors());
				request.getRequestDispatcher("/pages/signUp.jsp").forward(
						request, response);
			} else {
				chain.doFilter(request, response);
			}
		} else {

		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
