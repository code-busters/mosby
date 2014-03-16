package main.java.com.mosby.view.web.servlets.userProfile;

import main.java.com.mosby.controller.services.UpdateUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/contactInfo")
@MultipartConfig
public class ContactInfoServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/pages/userProfile/contactInfo.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UpdateUserService updateUserService = new UpdateUserService();
		updateUserService.update(request, this);
		response.sendRedirect("contactInfo");
	}
}
