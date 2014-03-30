package main.java.com.mosby.controller.services;

import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.model.Event;
import main.java.com.mosby.model.EventCategory;
import main.java.com.mosby.model.EventType;
import main.java.com.mosby.model.Organizer;
import main.java.com.mosby.model.PromoCode;
import main.java.com.mosby.model.Ticket;
import main.java.com.mosby.model.TicketInfo;
import main.java.com.mosby.model.User;
import main.java.com.mosby.utils.FileUploadUtils;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class EventService {
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final String TIME_FORMAT = "HH:mm";
	private static final String EVENT_LOGO_PATH = "media\\images\\events\\logo";
    private static final String EVENT_BACKGROUND_PATH = "media\\images\\events\\background";
    
	private static Logger log = Logger.getLogger(EventService.class);
	
	
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
		boolean privacy = false;
		if (!request.getParameter("privacy_event").equals("0")){
			privacy = true;
		}

		Event event = new Event(null, name, description, eventCategory, eventType, startDate, startTime, endDate, endTime, location, eventLogo, eventBackground, privacy);
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
	
	public void updateEvent(HttpServletRequest request, HttpServlet servlet) throws IllegalStateException, IOException, ServletException{
		int eventId = Integer.parseInt(request.getParameter("eventId"));
		Event event = new ReadGenericObjectService<Event>((Class<Event>) Event.class).readById(eventId); 
		
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
		boolean privacy = false;
		
		Event updatedEvent = new Event(eventId, null, name, description, eventCategory, eventType, startDate, startTime, endDate, endTime, location, eventLogo, eventBackground, privacy);
		
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

	public HttpServletRequest readMyEvents(HttpServletRequest request){
    	HttpSession session = request.getSession(false);
    	User sessionUser = (User) session.getAttribute("user");
    	int userId = sessionUser.getId();
    	List <Organizer> organizersList = new ReadGenericObjectService<Organizer>((Class<Organizer>) new Organizer().getClass()).readListByField("user_ref", (Integer)userId);
    	List <Event> myEvents = new ArrayList<>();
    	for (Organizer organizer : organizersList) {
    		List <Event> currentEvents = new ReadGenericObjectService<Event>((Class<Event>) new Event().getClass()).readListByField("organizer_ref", organizer.getId());
    		for (Event event : currentEvents) {
    			String atributeNameTickets = "tickets_" + event.getId(); 
    			int allTickets = 0;
    			List <TicketInfo> ticketsInfo = new ReadGenericObjectService<TicketInfo>((Class<TicketInfo>) new TicketInfo().getClass()).readListByField("event_ref", event.getId());
    			for (TicketInfo ticketInfo : ticketsInfo) {
    				allTickets = allTickets + ticketInfo.getMaxNumber();
				}
    			request.setAttribute(atributeNameTickets, allTickets);
    			
    			String atributeNameTicketsSold = "tickets_sold_" + event.getId();
    			List <Ticket> tickets = new ReadGenericObjectService<Ticket>((Class<Ticket>) new Ticket().getClass()).readListByField("event_ref", event.getId());
    			int ticketsSold = tickets.size();
    			request.setAttribute(atributeNameTicketsSold, ticketsSold);
			}
    		myEvents.addAll(currentEvents);
    	}
    	request.setAttribute("events", myEvents);
		return request;
	}
}