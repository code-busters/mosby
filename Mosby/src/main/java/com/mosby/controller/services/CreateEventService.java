package main.java.com.mosby.controller.services;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.model.Event;
import main.java.com.mosby.model.EventCategory;
import main.java.com.mosby.model.EventType;

public class CreateEventService {
	private static final String DATE_FORMAT = "yyyy/MM/dd";
	private static final String TIME_FORMAT = "HH:mm";
	
	private static Logger log = Logger.getLogger(CreateEventService.class);

	public int create(HttpServletRequest request, HttpServlet servlet,
			String logo, String background) throws IllegalStateException,
			IOException, ServletException {

		EventCategory eventCategory = null;
		EventType eventType = null;
		int organizerRef = 5;
		String name = request.getParameter("event_name");
		String description = request.getParameter("event_description");
		int categoryRef = Integer.parseInt(request.getParameter("event_category"));
		int typeRef = Integer.parseInt(request.getParameter("event_type"));
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

		 Event event = new Event(null, name, description,
		 eventCategory, eventType, startDate, startTime, endDate, endTime, location, logo, background);
		System.out.println(event);

		ReflectionDao<Event> eventDao = new ReflectionDao<>(
				(Class<Event>) Event.class);

		int id = eventDao.insertObjects(event);

		return id;
	}

	public void update() {

	}

}
