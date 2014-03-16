package main.java.com.mosby.view.web.servlets;

import main.java.com.mosby.controller.services.FacebookUserGetDataService;
import main.java.com.mosby.controller.services.ReadUsersService;
import main.java.com.mosby.controller.services.SocialSignUpUserService;
import main.java.com.mosby.model.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/socialSignUp")
public class SocialSignUpServlet extends HttpServlet {
	private static Logger log = Logger.getLogger(SocialSignUpServlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
//		request.getRequestDispatcher("/pages/index.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String faceCode = request.getParameter("code");
		String state = request.getParameter("state");
		
		FacebookUserGetDataService userService = new FacebookUserGetDataService();
		
		User user = userService.getUserDataFromFacebook(faceCode, session);
		String email = user.getEmail();
		
		String sessionID = session.getId();
		if (state.equals(sessionID)){
			try {	
				//user = new SocialSignUpUserService().signUpUser(user );
				session.setAttribute("user", user);
				session.setAttribute("user_type", "facebook");
				
				if(new SocialSignUpUserService().signUpUser(user ) == null){
					ReadUsersService readUsersService = new ReadUsersService();
					
					readUsersService.readSocialUser(user);
					
					System.out.println("signed in using facebook " + user);

				} else{ 
					System.out.println("signed up using facebook " + user);
				} 
			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("/signUp");
			}
			response.sendRedirect("/index");
		} else {
			System.err.println("CSRF protection validation");
		}
	}

}
