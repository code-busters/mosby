package main.java.com.mosby.view.web.servlets;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.mosby.controller.services.MainService;
import main.java.com.mosby.model.User;
import main.java.com.mosby.utils.ValidatorUtils;

@WebServlet("/test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Test() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = new User(1, null, "firstName", "lastName", "AlexHamer777@gmail.com", "Qwerty123", 0.0 , false);
		ValidatorUtils<User> validatorUtils = new ValidatorUtils<>((Class<User>)user.getClass());
		try {
			System.out.println(validatorUtils.validate(user));
		} catch (NoSuchMethodException | SecurityException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
