package main.java.com.mosby.controller.services;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.model.Event;

public class CreateEventService {
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	
	private static Logger log = Logger.getLogger(CreateEventService.class);

	public int create(HttpServletRequest request, HttpServlet servlet,
			String logo, String background) throws IllegalStateException,
			IOException, ServletException {

		SimpleDateFormat parseDate = new SimpleDateFormat("dd/MM/yyyy hh:mm");

		int organizersRef = 5;
		String name = request.getParameter("event_name");
		String description = request.getParameter("event_description");
		int catregoriesRef = 1;
		int typeRef = 1;
		Date startDate = null, endDate = null;
		try {
			startDate = new SimpleDateFormat(DATE_FORMAT).parse(request.getParameter("start_date"));
			endDate = new SimpleDateFormat(DATE_FORMAT).parse(request.getParameter("end_date"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String startTime = request.getParameter("start_time");
		//String endTime = request.getParameter("end_time");
//		Timestamp start = null;
//		Timestamp end = null;
//		try {
//			start = new Timestamp(parseDate.parse(startDate).getTime());
//			end = new Timestamp(parseDate.parse(endDate).getTime());
//		} catch (ParseException e) {
//			log.error(e);
//		}
		String location = request.getParameter("event_location");

		 Event event = new Event(null, name, description,
		 null, null, startDate, endDate, location, logo, background);
		System.out.println(event);

		ReflectionDao<Event> eventDao = new ReflectionDao<>(
				(Class<Event>) Event.class);

		int id = eventDao.insertObjects(event);

		return id;
	}

	public void update() {

	}

}
