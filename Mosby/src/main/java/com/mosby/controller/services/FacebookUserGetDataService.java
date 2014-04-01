package main.java.com.mosby.controller.services;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import main.java.com.mosby.model.User;
import main.java.com.mosby.view.web.servlets.SocialSignUpServlet;

public class FacebookUserGetDataService {
	private static Logger log = Logger.getLogger(SocialSignUpServlet.class);
	
	public User getUserDataFromFacebook(String faceCode, HttpServletRequest request){
		User user = new User();
		String token = null;
		if (faceCode != null && ! "".equals(faceCode)) {
			String appId = "601170126631442";
			String redirectUrl = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ "/Mosby/socialSignUp";
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
		
		user = getUserFromJsonResponse(token);
		return user;
		
	}
	
	private User getUserFromJsonResponse(String accessToken) {
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
				user.setImage(imageLocation);	
				user.setBirthDate(new SimpleDateFormat("MM/dd/yyyy").parse(json.getString("birthday")));
				
				System.out.println("BIRTHDAY" + json.getString("birthday"));
				
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
