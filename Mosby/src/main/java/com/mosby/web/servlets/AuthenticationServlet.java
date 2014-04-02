package main.java.com.mosby.web.servlets;

import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/authentication")
public class AuthenticationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AuthenticationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("authentication_code");
		ReflectionDao<User> usersDao = new ReflectionDao<>(User.class);
        User user = usersDao.selectObjects(5,"authentication_code=", code).get(0);
		user.setActive(true);
		System.out.println(code);
		usersDao.updateObjects(user);
		System.out.println(user);
		HttpSession session = request.getSession(false);
		session.setAttribute("user", user);
		session.setAttribute("userType", "common");
		response.sendRedirect("contactInfo");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
