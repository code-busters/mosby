package main.java.com.mosby.view.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.model.User;

@WebServlet("/authentication")
public class AuthenticationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AuthenticationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("authentication_code");
		User user = new User();
		ReflectionDao<User> usersDao = new ReflectionDao<>((Class<User>) user.getClass());
		user = usersDao.selectObjects("authentication_code", code).get(0);
		user.setActive(true);
		usersDao.updateObjects(user, "id", user.getId());	
		System.out.println(code);
		response.sendRedirect("index");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
