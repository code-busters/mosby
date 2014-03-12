package main.java.com.mosby.view.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.controller.services.ReadUsersService;
import main.java.com.mosby.model.BaseUserInfo;
import main.java.com.mosby.model.User;
import main.java.com.mosby.model.UserProfile;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		ReadUsersService readUsersService = new ReadUsersService();
		
		BaseUserInfo baseUserInfo = readUsersService.readUser(email, password);

		if (baseUserInfo == null) {
			request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
		} else {
			int baseUsersInfoRef = baseUserInfo.getId();
			UserProfile userProfile = readUsersService.readUserProfile(baseUsersInfoRef);
			User user = new User(baseUserInfo, userProfile);
			
			System.out.println(user);
			
			HttpSession session = request.getSession(false);
			session.setAttribute("user", user);

			request.getRequestDispatcher("/pages/index.jsp").forward(request, response);
		}

	}
}
