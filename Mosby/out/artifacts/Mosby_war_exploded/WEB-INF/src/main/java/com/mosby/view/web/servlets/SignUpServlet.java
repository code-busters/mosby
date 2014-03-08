package main.java.com.mosby.view.web.servlets;

import main.java.com.mosby.controller.services.SignUpUserService;
import main.java.com.mosby.model.BaseUserInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/signUp.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String firstName = request.getParameter("first_name");
    	String lastName = request.getParameter("last_name");
    	String email = request.getParameter("email");
    	String password = request.getParameter("password");
    	
    	BaseUserInfo baseUserInfo = new SignUpUserService().signUpUser(firstName, lastName, email, password);
    	
    	HttpSession session = request.getSession(false);
		session.setAttribute("baseUserInfo", baseUserInfo);
		
		response.sendRedirect("/pages/index.jsp");
    }
}