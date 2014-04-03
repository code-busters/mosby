package main.java.com.mosby.web.servlets;

import main.java.com.mosby.controller.services.GooglePlusService;
import main.java.com.mosby.controller.services.UserService;
import main.java.com.mosby.model.User;
import main.java.com.mosby.utils.ValidatorUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        String state = new BigInteger(130, new SecureRandom()).toString(32);
        request.getSession().setAttribute("state", state);

		String appId = "601170126631442";
//		String redirectUrl = request.getScheme() + "://"
//				+ request.getServerName() + ":" + request.getServerPort()
//				+ "/socialSignUp";
        String redirectUrl = request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath() + "/socialSignUp";
		String returnValue = "https://www.facebook.com/dialog/oauth?client_id="
				+ appId + "&redirect_uri=" + redirectUrl
				+ "&scope=email,user_birthday&state=" + state;


        request.setAttribute("clientId", new GooglePlusService().getClientId());

		request.setAttribute("facebookURL", returnValue);
		request.getRequestDispatcher("/pages/login.jsp").forward(request,
				response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		UserService userService = new UserService();

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		User user = userService.readUser(email, password);

		if (user == null || !user.isActive()) {

			ValidatorUtils<User> validatorUtils = null;
			if(request.getParameter("language").equals("ru_RU")){
			validatorUtils = new ValidatorUtils<>(
					User.class, "en");
			} else {
			validatorUtils = new ValidatorUtils<>(
						User.class, request.getParameter("language"));
			}
			validatorUtils.correctFields();
			request.setAttribute("errors", validatorUtils.getErrors());
			request.getRequestDispatcher("/pages/login.jsp").forward(request,
					response);
		} else {

			HttpSession session = request.getSession(false);
			session.setAttribute("user", user);
			session.setAttribute("userType", "common");

			if (session.getAttribute("waitUrl") != null) {
				String url = session.getAttribute("waitUrl").toString();
				response.sendRedirect(url);
			} else {
				response.sendRedirect("index");
			}
		}
	}
}
