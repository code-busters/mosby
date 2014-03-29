package main.java.com.mosby.controller.services;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.controller.services.ReadGenericObjectService;
import main.java.com.mosby.model.Event;
import main.java.com.mosby.model.EventCategory;
import main.java.com.mosby.model.EventType;
import main.java.com.mosby.model.PromoCode;
import main.java.com.mosby.model.TicketInfo;
import main.java.com.mosby.utils.FileUploadUtils;

public class CreateEventService {
	private static final String DATE_FORMAT = "dd-MM-yyyy";
	private static final String TIME_FORMAT = "HH:mm";
    private static final String EVENT_BACKGROUND_PATH = "media\\images\\events\\background";
    private static final String EVENT_LOGO_PATH = "media\\images\\events\\logo";
	
	private static Logger log = Logger.getLogger(CreateEventService.class);

	@SuppressWarnings("unchecked")
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
		
//Event builder		
		String name = request.getParameter("event_name");
		String description = request.getParameter("event_description");

		EventCategory eventCategory = new ReadGenericObjectService<EventCategory>((Class<EventCategory>) EventCategory.class).readById(Integer.parseInt(request.getParameter("event_category")));  
		EventType eventType = new ReadGenericObjectService<EventType>((Class<EventType>) EventType.class).readById(Integer.parseInt(request.getParameter("event_type")));
		
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

		Event event = new Event(null, name, description, eventCategory, eventType, startDate, startTime, endDate, endTime, location, eventLogo, eventBackground, false);
		System.out.println(event);

		ReflectionDao<Event> eventDao = new ReflectionDao<>((Class<Event>) Event.class);

		int id = eventDao.insertObjects(event);
		event.setId(id);

		
//Tickets Info builder
		ReflectionDao<TicketInfo> ticketInfoDao = new ReflectionDao<>((Class<TicketInfo>) TicketInfo.class);
		String idTicketsArray = request.getParameter("tickets_id");
		List<String> idTicketsList = new ArrayList<String>(Arrays.asList(idTicketsArray.split("_")));
		for (String currInt : idTicketsList){
			int currentId = Integer.parseInt(currInt);
			String type;
			String ticketInfoName = request.getParameter("event_ticket_name_" + currentId);
			String ticketDescription = request.getParameter("ticket_description_" + currentId);
			int maxNumber = Integer.parseInt(request.getParameter("event_ticket_quantity_" + currentId));
			String stringPrice = request.getParameter("event_ticket_price_" + currentId);
			System.out.println(stringPrice);
			int price;
			if (stringPrice == null){
				type = "Free";
				price = 0;
			}
			else {
				type = "paid";
				price = Integer.parseInt(request.getParameter("event_ticket_price_" + currentId));
			}
			TicketInfo ticketInfo = new TicketInfo(ticketInfoName, event, type, ticketDescription, maxNumber, price, startDate, startTime, endDate, endTime);
					
			ticketInfoDao.insertObjects(ticketInfo);
			System.out.println(ticketInfo);
		}		
		
		
// Promo codes builder
		ReflectionDao<PromoCode> promoCodeDao = new ReflectionDao<>((Class<PromoCode>) PromoCode.class);
		String promoCodesArray = request.getParameter("promo_codes_id");
		List<String> idPromoCodesList = new ArrayList<String>(Arrays.asList(promoCodesArray.split("_")));
		for (String currInt : idPromoCodesList){
			int currentId = Integer.parseInt(currInt);
			String code = request.getParameter("promo_code_code_" + currentId);
			int discount = Integer.parseInt(request.getParameter("promo_code_discount_" + currentId));
			String promoCodeDescription = request.getParameter("promo_code_description_" + currentId);
			int maxNumber = Integer.parseInt(request.getParameter("promo_code_quantity_" + currentId));
			
			PromoCode promoCode = new PromoCode(event, code, discount, promoCodeDescription, maxNumber);
					
			promoCodeDao.insertObjects(promoCode);
			System.out.println(promoCode);
		}		
		
		return id;
	}

	public void update() {

	}

}
