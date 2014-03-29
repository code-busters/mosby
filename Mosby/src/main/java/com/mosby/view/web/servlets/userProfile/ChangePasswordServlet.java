package main.java.com.mosby.view.web.servlets.userProfile;

import main.java.com.mosby.controller.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import main.java.com.mosby.controller.services.UserService;
import main.java.com.mosby.model.User;
import main.java.com.mosby.utils.ValidatorUtils;

import java.io.IOException;

@WebServlet("/changePassword")
public class ChangePasswordServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/userProfile/changePassword.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	UserService updateUserService = new UserService();
    	if(updateUserService.changePassword(request)){
    	response.sendRedirect("/Mosby/contactInfo");
    	} else {
    		ValidatorUtils<User> validatorUtils = null;
			if(request.getParameter("language").equals("ru_RU")){
			validatorUtils = new ValidatorUtils<>(
					User.class, "en");
			} else {
			validatorUtils = new ValidatorUtils<>(
						User.class, request.getParameter("language"));
			}
			validatorUtils.changePassword();
			request.setAttribute("errors", validatorUtils.getErrors());
    		request.getRequestDispatcher("/pages/userProfile/changePassword.jsp").forward(request,response);
    	}
    }
}
