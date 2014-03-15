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

import main.java.com.mosby.controller.services.ReadUsersService;
import main.java.com.mosby.controller.services.SignUpUserService;
import main.java.com.mosby.controller.services.SocialSignUpUserService;
import main.java.com.mosby.model.User;
import main.java.com.mosby.model.UserProfile;
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
		HttpSession httpSession = request.getSession();
		String faceCode = request.getParameter("code");
		String state = request.getParameter("state");
		String accessToken = getFacebookAccessToken(faceCode);
		User user = getUserFromJsonResponse(accessToken, httpSession);
		String email = user.getEmail();
		
		String sessionID = httpSession.getId();
		if (state.equals(sessionID)){
			try {	
				user = new SocialSignUpUserService().signUpUser(user );
	
				if(user == null){
					ReadUsersService readUsersService = new ReadUsersService();
					
					user = readUsersService.readSocialUser(email);
					
					System.out.println("signed in using facebook " + user);
					
					HttpSession session = request.getSession(false);
					session.setAttribute("user", user);
					session.setAttribute("user_type", "facebook");

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

	private String getFacebookAccessToken(String faceCode){
		String token = null;
		if (faceCode != null && ! "".equals(faceCode)) {
			String appId = "601170126631442";
			String redirectUrl = "http://localhost:8080/Mosby/socialSignUp";
			String faceAppSecret = "f2d80932e636decfa07add67e1a05cbf";
			String newUrl = "https://graph.facebook.com/oauth/access_token?client_id="
					+ appId + "&redirect_uri=" + redirectUrl + "&client_secret=" 
					+ faceAppSecret + "&code=" + faceCode;
			
			HttpClient httpclient = new DefaultHttpClient();
			try {
				String responseBody = "";
				HttpGet httpget = new HttpGet(newUrl);
				HttpResponse response = httpclient.execute(httpget);
		        
		        HttpEntity entity = response.getEntity();
		        responseBody  = EntityUtils.toString(entity);
				token = StringUtils.removeEnd(StringUtils.removeStart(responseBody, "access_token="), "&expires=5180795");
			} catch (ClientProtocolException e) {
				log.error(e);
			} catch (IOException e) {
				log.error(e);
			} finally {
				httpclient.getConnectionManager().shutdown();
			}
		}
		return token;
	}

	private User getUserFromJsonResponse(String accessToken, HttpSession session) {
		String email = null;
		User user = new User();
		HttpClient httpclient = new DefaultHttpClient();
		try {
			if (accessToken != null && ! "".equals(accessToken)) {
				String newUrl = "https://graph.facebook.com/me?access_token=" + accessToken;
				httpclient = new DefaultHttpClient();
				HttpGet httpget = new HttpGet(newUrl);
				System.out.println("Get info from facebook --> executing request: " + httpget.getURI());
				ResponseHandler<String> responseHandler = new BasicResponseHandler();
				String responseBody = httpclient.execute(httpget, responseHandler);
				JSONObject json = (JSONObject)JSONSerializer.toJSON(responseBody);
				
				String facebookId = json.getString("id");
				URL imageURL = new URL("http://graph.facebook.com/"+facebookId +"/picture?type=large");
				URLConnection conn = imageURL.openConnection();
				((HttpURLConnection) conn).setInstanceFollowRedirects(false);
				String imageLocation = conn.getHeaderField("Location");
				
				
				user.setPassword(json.getString("id"));
				user.setFirstName(json.getString("first_name"));
				user.setLastName(json.getString("last_name"));
				user.setEmail(json.getString("email"));
				user.setUserProfile(new UserProfile());
				user.getUserProfile().setImage(imageLocation);
				user.getUserProfile().setBirthDate(new SimpleDateFormat("MM/dd/yyyy").parse(json.getString("birthday")));
				System.out.println("BIRTHDAY" + json.getString("birthday"));
				
				session.setAttribute("facebookUserImage", imageLocation);
				session.setAttribute("userType", "facebookUser");
				
				String firstName = json.getString("first_name");
				String lastName = json.getString("last_name");
				email= json.getString("email");

				System.out.println("facebookId " + facebookId + " | name " + firstName + " " + lastName + " | email " + email + " | " + imageLocation);
			} else {
				System.err.println("facebook accessToken = null");
			}
		} catch (ClientProtocolException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		} catch (ParseException e) {
			log.error(e);
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return user;
	}

}
