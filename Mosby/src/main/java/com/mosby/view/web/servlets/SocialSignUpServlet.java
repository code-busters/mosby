package main.java.com.mosby.view.web.servlets;

import com.google.gson.Gson;
import main.java.com.mosby.controller.services.FacebookUserGetDataService;
import main.java.com.mosby.controller.services.GooglePlusService;
import main.java.com.mosby.controller.services.UserService;
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

	private static final long serialVersionUID = 1L;
    private static final Gson GSON = new Gson();
	private static Logger log = Logger.getLogger(SocialSignUpServlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String state = request.getParameter("state");
        String faceCode = request.getParameter("code");
        if(faceCode != null) {
            FacebookUserGetDataService userService = new FacebookUserGetDataService();
            User user = userService.getUserDataFromFacebook(faceCode, session);

            String sessionID = session.getId();
            if (state.equals(sessionID)) {
                try {
                    session.setAttribute("user", user);
                    session.setAttribute("user_type", "facebook");

                    if (new UserService().socialSignUpUser(user) == null) {
                        new UserService().readSocialUser(user);

                        System.out.println("signed in using facebook " + user);

                    } else {
                        System.out.println("signed up using facebook " + user);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendRedirect("/signUp");
                }
                response.sendRedirect("index");
            } else {
                System.err.println("CSRF protection validation");
            }
        } else  {
            String tokenData = (String) request.getSession().getAttribute("token");
            if (tokenData != null) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().print(GSON.toJson("Current user is already connected."));
                return;
            }

            if (!request.getParameter("state").equals(request.getSession().getAttribute("state"))) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().print(GSON.toJson("Invalid state parameter."));
                return;
            }
            User user = new GooglePlusService().readData(request, response);
            session.setAttribute("user", user);
            session.setAttribute("user_type", "googlePlus");
        }
    }

}
