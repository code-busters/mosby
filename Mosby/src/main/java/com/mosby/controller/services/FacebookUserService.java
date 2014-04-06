package main.java.com.mosby.controller.services;

import main.java.com.mosby.model.User;
import main.java.com.mosby.utils.EncryptionUtils;
import main.java.com.mosby.web.servlets.SocialSignUpServlet;
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

import javax.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FacebookUserService {
	private static Logger log = Logger.getLogger(SocialSignUpServlet.class);
	private static String APP_ID;
    private static String APP_SECRET;

    public FacebookUserService() {
        try {	
        	InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("/facebook_app_secrets.txt");
        	BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        	APP_ID = br.readLine();
        	APP_SECRET = br.readLine();
        } catch (IOException e) {
            throw new Error("No facebook_app_secrets.txt found", e);
        }
    }

    public String getAppId() {
        return APP_ID;
    }
    
    public String getAppSecret() {
        return APP_SECRET;
    }
    
    public String generateRedirectUrl(HttpServletRequest request){
        String state = new BigInteger(130, new SecureRandom()).toString(32);
        request.getSession(false).setAttribute("state", state);

		String redirectUrl = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/socialSignUp";
		
		String returnValue = "https://www.facebook.com/dialog/oauth?client_id="
				+ APP_ID + "&redirect_uri=" + redirectUrl
				+ "&scope=email,user_birthday&state=" + state;
		return returnValue;
    }
    
	public User getUserDataFromFacebook(String faceCode, HttpServletRequest request){
		User user = new User();
		String token = null;
		if (faceCode != null && ! "".equals(faceCode)) {
			String redirectUrl = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/socialSignUp";
			String newUrl = "https://graph.facebook.com/oauth/access_token?client_id="
					+ APP_ID + "&redirect_uri=" + redirectUrl + "&client_secret=" 
					+ APP_SECRET + "&code=" + faceCode;
			
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
		User user = new User();
		HttpClient httpclient = new DefaultHttpClient();
		try {
			if (accessToken != null && ! "".equals(accessToken)) {
				String newUrl = "https://graph.facebook.com/me?access_token=" + accessToken;
				httpclient = new DefaultHttpClient();
				HttpGet httpget = new HttpGet(newUrl);
				ResponseHandler<String> responseHandler = new BasicResponseHandler();
				String responseBody = httpclient.execute(httpget, responseHandler);
				JSONObject json = (JSONObject)JSONSerializer.toJSON(responseBody);
				
				String facebookId = json.getString("id");
				URL imageURL = new URL("http://graph.facebook.com/"+facebookId +"/picture?type=large");
				URLConnection conn = imageURL.openConnection();
				((HttpURLConnection) conn).setInstanceFollowRedirects(false);
				String imageLocation = conn.getHeaderField("Location");

				user.setFirstName(json.getString("first_name"));
				user.setLastName(json.getString("last_name"));
				user.setEmail(json.getString("email"));
				user.setImage(imageLocation);	
				}
			}
		} catch (ClientProtocolException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return user;
	}
	
}
