package main.java.com.mosby.web.servlets;

import main.java.com.mosby.controller.services.GooglePlusService;
import main.java.com.mosby.model.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/logout")
public class LogOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Logger log = Logger.getLogger(LogOutServlet.class);
       

    public LogOutServlet() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
        if (request.getSession().getAttribute("token") != null) {
            new GooglePlusService().disconnect(request, response);
        }
		User user = (User) session.getAttribute("user");
        log.info("Logged out: " + user.getFirstName() + " " + user.getLastName());
        session.removeAttribute("user");
        response.sendRedirect("index");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}