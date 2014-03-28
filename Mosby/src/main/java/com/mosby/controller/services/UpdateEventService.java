package main.java.com.mosby.controller.services;

import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.model.Event;
import main.java.com.mosby.model.EventCategory;
import main.java.com.mosby.model.EventType;
import main.java.com.mosby.model.PromoCode;
import main.java.com.mosby.model.Ticket;
import main.java.com.mosby.model.TicketInfo;
import main.java.com.mosby.utils.FileUploadUtils;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class UpdateEventService {
	private static final String DATE_FORMAT = "yyyy-MM-dd";
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
		try {
			startDate = new SimpleDateFormat(DATE_FORMAT).parse(request.getParameter("start_date"));
			endDate = new SimpleDateFormat(DATE_FORMAT).parse(request.getParameter("end_date"));
			startTime = new SimpleDateFormat(TIME_FORMAT).parse(request.getParameter("start_time"));
			endTime = new SimpleDateFormat(TIME_FORMAT).parse(request.getParameter("end_time"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		EventCategory eventCategory = new ReadGenericObjectService<EventCategory>((Class<EventCategory>) EventCategory.class).readById(Integer.parseInt(request.getParameter("event_category")));  
		EventType eventType = new ReadGenericObjectService<EventType>((Class<EventType>) EventType.class).readById(Integer.parseInt(request.getParameter("event_type")));

		String description = request.getParameter("event_description");

		String location = request.getParameter("event_location");

		
		Event updatedEvent = new Event(eventId, null, name, description, eventCategory, eventType, startDate, startTime, endDate, endTime, location, eventLogo, eventBackground, false);
		
		ReflectionDao<Event> eventDao = new ReflectionDao<>((Class<Event>) Event.class);
		eventDao.updateObjects(updatedEvent);
	}
	
	public void updateTicketsInfo(HttpServletRequest request){
		ReflectionDao<TicketInfo> ticketInfoDao = new ReflectionDao<>((Class<TicketInfo>) TicketInfo.class);
		int eventId = Integer.parseInt(request.getParameter("eventId"));
		
		Event event = new ReadGenericObjectService<Event>((Class<Event>) new Event().getClass()).readById(eventId);
		List <TicketInfo> currentTicketsInfoList = new ReadGenericObjectService<TicketInfo>((Class<TicketInfo>) TicketInfo.class).readListByField("event_ref", (Integer)eventId);
		List<String> currentIdTicketsInfoList = new ArrayList<>();
		for (TicketInfo ticketInfo : currentTicketsInfoList) {
			currentIdTicketsInfoList.add(Integer.toString(ticketInfo.getId()));
		}
		
		String idTicketsArray = request.getParameter("tickets_id");
		List<String> newIdTicketsInfoList = new ArrayList<String>(Arrays.asList(idTicketsArray.split("_")));
		
		for (String newId : newIdTicketsInfoList) {
			TicketInfo ticketInfo;
			int currentId = Integer.parseInt(newId);
			
			String ticketInfoName = request.getParameter("event_ticket_name_" + currentId);
			String type;
			String ticketDescription = request.getParameter("ticket_description_" + currentId);
			int maxNumber = Integer.parseInt(request.getParameter("event_ticket_quantity_" + currentId));
			String stringPrice = request.getParameter("event_ticket_price_" + currentId);
			int price;
			if (stringPrice == null || stringPrice.equals("0")){
				type = "Free";
				price = 0;
			}
			else {
				type = "paid";
				price = Integer.parseInt(request.getParameter("event_ticket_price_" + currentId));
			}
			
			Date startDate= null, startTime= null, endDate = null, endTime= null;

			try {
				startDate = new SimpleDateFormat(DATE_FORMAT).parse(request.getParameter("ticket_start_date_" + currentId));
				endDate = new SimpleDateFormat(DATE_FORMAT).parse(request.getParameter("ticket_end_date_" + currentId));
				startTime = new SimpleDateFormat(TIME_FORMAT).parse(request.getParameter("ticket_start_time_" + currentId));
				endTime = new SimpleDateFormat(TIME_FORMAT).parse(request.getParameter("ticket_end_time_" + currentId));
			} catch (ParseException e) {
				e.printStackTrace();
			}
				
			if (currentIdTicketsInfoList.contains(newId)){
				ticketInfo = new TicketInfo(currentId, ticketInfoName, event, type, ticketDescription, maxNumber, price, startDate, startTime, endDate, endTime);
				ticketInfoDao.updateObjects(ticketInfo);
			}
			else{
				ticketInfo = new TicketInfo(ticketInfoName, event, type, ticketDescription, maxNumber, price, startDate, startTime, endDate, endTime);
				ticketInfoDao.insertObjects(ticketInfo);
			}
		}
		
		for (String id : currentIdTicketsInfoList) {
			if (!newIdTicketsInfoList.contains(id)){
				List <Ticket> ticketsList = new ReadGenericObjectService<Ticket>((Class<Ticket>) Ticket.class).readListByField("ticket_info_ref", Integer.parseInt(id));
				if (ticketsList.isEmpty()){
					TicketInfo ticketInfo = new ReadGenericObjectService<TicketInfo>((Class<TicketInfo>) TicketInfo.class).readById(Integer.parseInt(id));
					ticketInfoDao.deleteObjects(ticketInfo);
				}
				else{
					System.out.println("You cant delete this TicketInfo, tickets already bougth");
				}
			}
		}
	}
	
	public void updatePromoCodes(HttpServletRequest request){
		ReflectionDao<PromoCode> promoCodeDao = new ReflectionDao<>((Class<PromoCode>) PromoCode.class);
		int eventId = Integer.parseInt(request.getParameter("eventId"));
		
		Event event = new ReadGenericObjectService<Event>((Class<Event>) Event.class).readById(eventId);
		List <PromoCode> currentPromoCodesList = new ReadGenericObjectService<PromoCode>((Class<PromoCode>) PromoCode.class).readListByField("event_ref", (Integer)eventId);
		List<String> currentIdPromoCodesList = new ArrayList<>();
		for (PromoCode promoCode : currentPromoCodesList) {
			currentIdPromoCodesList.add(Integer.toString(promoCode.getId()));
		}
		
		String idPromoCodesArray = request.getParameter("promo_codes_id");
		List<String> newIdPromoCodesList = new ArrayList<String>(Arrays.asList(idPromoCodesArray.split("_")));
		
		for (String newId : newIdPromoCodesList) {
			PromoCode promoCode;
			int currentId = Integer.parseInt(newId);
			
			String code = request.getParameter("promo_code_code_" + currentId);
			int discount = Integer.parseInt(request.getParameter("promo_code_discount_" + currentId));
			String description = request.getParameter("promo_code_description_" + currentId);
			int maxNumber = Integer.parseInt(request.getParameter("promo_code_quantity_" + currentId));
			
			if (currentIdPromoCodesList.contains(newId)){
				promoCode = new PromoCode(currentId, event, code, discount, description, maxNumber);
				promoCodeDao.updateObjects(promoCode);
			}
			else{
				promoCode = new PromoCode(event, code, discount, description, maxNumber);
				promoCodeDao.insertObjects(promoCode);
			}
		}
		
		for (String id : currentIdPromoCodesList ) {
			if (!newIdPromoCodesList.contains(id)){
				List <Ticket> ticketsList = new ReadGenericObjectService<Ticket>((Class<Ticket>) Ticket.class).readListByField("promo_codes_ref", Integer.parseInt(id));
				if (ticketsList.isEmpty()){
					PromoCode promoCode = new ReadGenericObjectService<PromoCode>((Class<PromoCode>) PromoCode.class).readById(Integer.parseInt(id));
					promoCodeDao.deleteObjects(promoCode);
				}
				else{
					System.out.println("You cant delete this PromoCode, tickets already bougth");
				}
			}
		}
	}
}
