package main.java.com.mosby.view.web.servlets;

import main.java.com.mosby.controller.services.ReadUsersService;
import main.java.com.mosby.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        String sessionId = session.getId();
        String appId = "601170126631442";
        String redirectUrl = "http://localhost:8080/Mosby/socialSignUp";
        String returnValue = "https://www.facebook.com/dialog/oauth?client_id="
                + appId + "&redirect_uri=" + redirectUrl
                + "&scope=email,user_birthday&state=" + sessionId;
       
    	request.setAttribute("facebookURL", returnValue);
		request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		ReadUsersService readUsersService = new ReadUsersService();
		
		User user = readUsersService.readUser(email, password);

		if (user == null) {
			request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
		} else {
			
			System.out.println(user);
			
			HttpSession session = request.getSession(false);
			session.setAttribute("user", user);

            response.sendRedirect("index");
		}

	}
}
