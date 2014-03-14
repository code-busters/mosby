package main.java.com.mosby.controller.services;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.model.Event;
import main.java.com.mosby.model.EventCategory;
import main.java.com.mosby.model.EventType;
import main.java.com.mosby.utils.FileUploadUtils;

public class CreateEventService {
	private static final String DATE_FORMAT = "yyyy/MM/dd";
	private static final String TIME_FORMAT = "HH:mm";
    private static final String EVENT_BACKGROUND_PATH = "media\\images\\events\\background";
    private static final String EVENT_LOGO_PATH = "media\\images\\events\\logo";
	
	private static Logger log = Logger.getLogger(CreateEventService.class);

	public int create(HttpServletRequest request, HttpServlet servlet) throws IllegalStateException, IOException, ServletException {

//      Image uploading
		String eventLogo = "default.png";
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
		String eventBackground = "default.jpg";
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
		
		
		EventCategory eventCategory = null;
		EventType eventType = null;
		
		int organizerRef = 5;
		String name = request.getParameter("event_name");
		String description = request.getParameter("event_description");
		
		
//		Must work but.....
//		ReadEventService readEventService = new ReadEventService();
//		EventCategory eventCategory = readEventService.readCategotyById(Integer.parseInt(request.getParameter("event_category")));
//		EventType eventType = readEventService.readTypeById(Integer.parseInt(request.getParameter("event_type")));
//
		
//		if(categoryRef != -1){
//			ReflectionDao<EventCategory> eventCategoryDao = new ReflectionDao<>(
//					(Class<EventCategory>) EventCategory.class);
//			eventCategory = eventCategoryDao.selectObjects("id", categoryRef).get(0);
//		}
//		if(typeRef != -1){
//			ReflectionDao<EventType> eventTypeDao = new ReflectionDao<>(
//					(Class<EventType>) EventType.class);
//			eventType = eventTypeDao.selectObjects("id", typeRef).get(0);
//		}
		
		Date startDate = null, endDate = null, startTime = null, endTime = null;
		try {
			startDate = new SimpleDateFormat(DATE_FORMAT).parse(request.getParameter("start_date"));
			endDate = new SimpleDateFormat(DATE_FORMAT).parse(request.getParameter("end_date"));
			startTime = new SimpleDateFormat(TIME_FORMAT).parse(request.getParameter("start_time"));
			endTime = new SimpleDateFormat(TIME_FORMAT).parse(request.getParameter("end_time"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(request.getParameter("start_time"));

		String location = request.getParameter("event_location");

		Event event = new Event(null, name, description, eventCategory, eventType, startDate, startTime, endDate, endTime, location, eventLogo, eventBackground);
		System.out.println(event);

		ReflectionDao<Event> eventDao = new ReflectionDao<>((Class<Event>) Event.class);

		int id = eventDao.insertObjects(event);

		return id;
	}

	public void update() {

	}

}
