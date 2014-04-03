package main.java.com.mosby.controller.services;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Date;

public class GoogleCalendarService {
 	private static final Logger log = Logger.getLogger(GooglePlusService.class);
    private static final HttpTransport TRANSPORT = new NetHttpTransport();
    private static final JacksonFactory JSON_FACTORY = new JacksonFactory();
    private static GoogleClientSecrets clientSecrets;
    private static String CLIENT_ID;
    private static String CLIENT_SECRET;
    
    public GoogleCalendarService() {
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("/client_secrets.json");
            Reader reader = new InputStreamReader(inputStream);
            clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, reader);
            CLIENT_ID = clientSecrets.getWeb().getClientId();
            CLIENT_SECRET = clientSecrets.getWeb().getClientSecret();
        } catch (IOException e) {
            throw new Error("No calendar_client_secret.json found", e);
        }
    }
    

    public String getClientId() {
        return CLIENT_ID;
    }

    public void addEvent(HttpServletRequest request, String googleCode) {
    	try {
            String redirectUrl = request.getScheme() + "://"
                    + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath() + "/calendarServlet";
    		GoogleTokenResponse tokenResponse =
    	            new GoogleAuthorizationCodeTokenRequest(TRANSPORT, JSON_FACTORY,
    	                    CLIENT_ID, CLIENT_SECRET, googleCode, redirectUrl).execute();
    		
    		GoogleCredential credential = new GoogleCredential.Builder()
	            .setJsonFactory(JSON_FACTORY)
	            .setTransport(TRANSPORT)
	            .setClientSecrets(CLIENT_ID, CLIENT_SECRET).build()
	            .setFromTokenResponse(tokenResponse);
    		
    		Calendar service = new Calendar.Builder(TRANSPORT, JSON_FACTORY, credential).setApplicationName("Mosby").build();
    		
    		Event event = createEvent(Integer.parseInt(request.getSession().getAttribute("eventId").toString()));
    		
    		service.events().insert("primary", event).execute();
    		
    	} catch (IOException e) {
    		
    		e.printStackTrace();
    	}

    }

	private Event createEvent(int eventId) {
		main.java.com.mosby.model.Event event = new ReadGenericObjectService<>(main.java.com.mosby.model.Event.class).readById(eventId); 
		
		Event createdEvent = new Event();

		createdEvent.setSummary(event.getName());
		createdEvent.setLocation(event.getLocation());

		Date startDate = new Date(event.getStartDate().getTime() + event.getStartTime().getTime());
		Date endDate = new Date(event.getEndDate().getTime() + event.getEndTime().getTime());
		
		DateTime start = new DateTime(startDate);
		createdEvent.setStart(new EventDateTime().setDateTime(start));
		DateTime end = new DateTime(endDate);
		createdEvent.setEnd(new EventDateTime().setDateTime(end));
		
		return createdEvent;
	}
}
