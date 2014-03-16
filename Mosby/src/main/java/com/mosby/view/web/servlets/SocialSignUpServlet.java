package main.java.com.mosby.view.web.servlets;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
 

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.com.mosby.controller.services.FacebookUserGetDataService;
import main.java.com.mosby.controller.services.ReadUsersService;
import main.java.com.mosby.controller.services.SignUpUserService;
import main.java.com.mosby.controller.services.SocialSignUpUserService;
import main.java.com.mosby.model.User;
import main.java.com.mosby.utils.FileUploadUtils;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

@WebServlet("/socialSignUp")
public class SocialSignUpServlet extends HttpServlet {
	private static Logger log = Logger.getLogger(SocialSignUpServlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		//request.getRequestDispatcher("/pages/index.jsp").forward(request, response);
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
				response.sendRedirect(request.getContextPath() +"/signUp");
				return;
			}
			response.sendRedirect("index");
		} else {
			System.err.println("CSRF protection validation");
		}
	}

}
