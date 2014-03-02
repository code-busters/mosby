package main.java.com.mosby.controller.web.servlets;

import main.java.com.mosby.controller.services.MainService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/index")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MainService mainService = new MainService();
       
    public MainServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		mainService.selectUsers();
        request.getRequestDispatcher("/pages/index.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
