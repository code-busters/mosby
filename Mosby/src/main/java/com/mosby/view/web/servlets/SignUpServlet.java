package main.java.com.mosby.view.web.servlets;

import main.java.com.mosby.controller.services.ReadUsersService;
import main.java.com.mosby.controller.services.SignUpUserService;
import main.java.com.mosby.model.User;
import main.java.com.mosby.model.UserProfile;
import main.java.com.mosby.utils.ValidatorUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        String sessionId = session.getId();
        String appId = "601170126631442";
        String redirectUrl = "http://localhost:8080/Mosby/socialSignUp";
        String returnValue = "https://www.facebook.com/dialog/oauth?client_id="
                + appId + "&redirect_uri=" + redirectUrl
                + "&scope=email,user_birthday&state=" + sessionId;
       
    	request.setAttribute("facebookURL", returnValue);
		request.getRequestDispatcher("/pages/signUp.jsp").forward(request,
				response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		User user = new User(firstName, lastName, email, password, 0, false);
		ValidatorUtils<User> validatorUtils = new ValidatorUtils<>(
				(Class<User>) user.getClass());

		try {
			user = validatorUtils.validate(user);
		} catch (NoSuchMethodException | SecurityException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		if (user == null) {
			request.setAttribute("errors", validatorUtils.getErrors());
			System.out.println(validatorUtils.getErrors());
			request.getRequestDispatcher("/pages/signUp.jsp").forward(request,
					response);
		} else {

			user = new SignUpUserService().signUpUser(firstName, lastName,
					email, password);

			if (user == null) {
				request.getRequestDispatcher("/pages/signUp.jsp").forward(
						request, response);
			} else {
				HttpSession session = request.getSession(false);
				session.setAttribute("user", user);

				response.sendRedirect("index");
			}
		}
	}
}
