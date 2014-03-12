package main.java.com.mosby.view.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.com.mosby.controller.services.SignUpUserService;
import main.java.com.mosby.model.BaseUserInfo;
import main.java.com.mosby.utils.ValidatorUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

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
    	
    	BaseUserInfo baseUserInfo = new BaseUserInfo(firstName, lastName, email, password, 0, false);
    	ValidatorUtils<BaseUserInfo> validatorUtils = new ValidatorUtils<>((Class<BaseUserInfo>) baseUserInfo.getClass());
    	
    	try {
			baseUserInfo = validatorUtils.validate(baseUserInfo);
		} catch (NoSuchMethodException | SecurityException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if(baseUserInfo == null){
    		request.setAttribute("errors", validatorUtils.getErrors());
    		System.out.println(validatorUtils.getErrors());
    		request.getRequestDispatcher("/pages/signUp.jsp").forward(request,
					response);
    	} else {
    	
    	baseUserInfo = new SignUpUserService().signUpUser(firstName, lastName, email, password);
    	
    	if (baseUserInfo == null) {
			request.getRequestDispatcher("/pages/signUp.jsp").forward(request,
					response);
		} else {
    	HttpSession session = request.getSession(false);
		session.setAttribute("baseUserInfo", baseUserInfo);
		
		request.getRequestDispatcher("/pages/index.jsp").forward(request,
				response);
		}
    	}
    }
}