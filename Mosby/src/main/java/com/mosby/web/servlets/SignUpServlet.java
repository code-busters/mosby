package main.java.com.mosby.web.servlets;

import main.java.com.mosby.controller.services.FacebookUserService;
import main.java.com.mosby.controller.services.GooglePlusService;
import main.java.com.mosby.controller.services.UserService;
import main.java.com.mosby.model.User;
import main.java.com.mosby.utils.MailUtils;
import main.java.com.mosby.utils.ValidatorUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("clientId", new GooglePlusService().getClientId());

		request.setAttribute("facebookURL", new FacebookUserService().generateRedirectUrl(request));
		request.getRequestDispatcher("/pages/signUp.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		User user = new UserService().signUpUser(firstName, lastName, email, password);

		if (user == null) {
			ValidatorUtils<User> validatorUtils = null;
			if(!request.getParameter("language").equals("en")&&!request.getParameter("language").equals("uk")){
				validatorUtils = new ValidatorUtils<>(User.class, "en");
			} else {
				validatorUtils = new ValidatorUtils<>(User.class, request.getParameter("language"));
			}
			validatorUtils.changeEmail();
			request.setAttribute("errors", validatorUtils.getErrors());
			request.setAttribute("first_name", firstName);
			request.setAttribute("last_name", lastName);
			request.setAttribute("email", email);
			request.getRequestDispatcher("/pages/signUp.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/pages/emailSent.jsp").forward(request, response);
		}
	}
}
