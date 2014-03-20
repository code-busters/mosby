package main.java.com.mosby.controller.services;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.catalina.connector.Request;
import org.apache.log4j.Logger;

import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.model.Event;
import main.java.com.mosby.model.EventCategory;
import main.java.com.mosby.model.EventType;
import main.java.com.mosby.model.User;
import main.java.com.mosby.utils.FileUploadUtils;

public class UpdateEventService {
	private static final String DATE_FORMAT = "dd-MM-yyyy";
	private static final String TIME_FORMAT = "HH:mm";
	private static final String EVENT_LOGO_PATH = "media\\images\\events\\logo";
    private static final String EVENT_BACKGROUND_PATH = "media\\images\\events\\background";
    
	private static Logger log = Logger.getLogger(UpdateEventService.class);
	
	public void updateEvent(HttpServletRequest request, HttpServlet servlet) throws IllegalStateException, IOException, ServletException{
		int eventId = Integer.parseInt(request.getParameter("eventId"));
		Event event = new ReadGenericObjectService<Event>((Class<Event>) new Event().getClass()).readById(eventId); 
		

//get info from JSP
		
//Image uploading

		String eventLogo = event.getLogo(); 
		
		Part filePart = request.getPart("event_logo");
		try {
			String contentType = filePart.getContentType();
			if (contentType.startsWith("image")) {
				File image = FileUploadUtils.uploadFile(servlet, EVENT_LOGO_PATH, filePart);
				eventLogo = FileUploadUtils.getFilename(image);
			}
		} catch (Exception e) {
			log.error(e);
		}
		String eventBackground = event.getBackground();
		filePart = request.getPart("event_background");
		try {
			String contentType = filePart.getContentType();
			if (contentType.startsWith("image")) {
				File image = FileUploadUtils.uploadFile(servlet, EVENT_BACKGROUND_PATH, filePart);
				eventBackground = FileUploadUtils.getFilename(image);
			}
		} catch (Exception e) {
			log.error(e);
		}
		
		
//event builder		
		String name = request.getParameter("event_name");

		Date startDate = null, endDate = null, startTime = null, endTime = null;
        SimpleDateFormat parseDate = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        String startTimestamp = request.getParameter("start_date") + " " + request.getParameter("start_time");
        String endTimestamp = request.getParameter("end_date") + " " + request.getParameter("end_time");
        Timestamp start = null;
        Timestamp end = null;
        try {
            start = new Timestamp(parseDate.parse(startTimestamp).getTime());
            end = new Timestamp(parseDate.parse(endTimestamp).getTime());
        } catch (ParseException e) {
            log.error(e);
        }
		try {
			startDate = new SimpleDateFormat(DATE_FORMAT).parse(request.getParameter("start_date"));
			endDate = new SimpleDateFormat(DATE_FORMAT).parse(request.getParameter("end_date"));
			startTime = new SimpleDateFormat(TIME_FORMAT).parse(request.getParameter("start_time"));
			endTime = new SimpleDateFormat(TIME_FORMAT).parse(request.getParameter("end_time"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		EventCategory eventCategory = new ReadGenericObjectService<EventCategory>((Class<EventCategory>) new EventCategory().getClass()).readById(Integer.parseInt(request.getParameter("event_category")));  
		EventType eventType = new ReadGenericObjectService<EventType>((Class<EventType>) new EventType().getClass()).readById(Integer.parseInt(request.getParameter("event_type")));

		String description = request.getParameter("event_description");

		String location = request.getParameter("event_location");

		
		Event updatedEvent = new Event(eventId, null, name, description, eventCategory, eventType, startDate, startTime, start, endDate, endTime, end, location, eventLogo, eventBackground);
		
		ReflectionDao<Event> eventDao = new ReflectionDao<>((Class<Event>) Event.class);
		eventDao.updateObjects(updatedEvent);
	} 
}
