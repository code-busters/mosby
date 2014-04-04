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

import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EventService {
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final String TIME_FORMAT = "HH:mm";
	private static final String EVENT_LOGO_PATH = "media\\images\\events\\logo";
    private static final String EVENT_BACKGROUND_PATH = "media\\images\\events\\background";
    
	private static Logger log = Logger.getLogger(EventService.class);
	
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
		
		
		Organizer organizer = new ReadGenericObjectService<>(Organizer.class).readById(Integer.parseInt(request.getParameter("organizer")));
		String name = request.getParameter("event_name");
		String description = request.getParameter("event_description");

		EventCategory eventCategory = new ReadGenericObjectService<>(EventCategory.class).readById(Integer.parseInt(request.getParameter("event_category")));  
		EventType eventType = new ReadGenericObjectService<>(EventType.class).readById(Integer.parseInt(request.getParameter("event_type")));
		
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
		if (!(request.getParameter("privacy_event").equals("0"))){
			privacy = true;
		}

		Event event = new Event(organizer, name, description, eventCategory, eventType, startDate, startTime, endDate, endTime, location, eventLogo, eventBackground, privacy);
		System.out.println(event);

		ReflectionDao<Event> eventDao = new ReflectionDao<>(Event.class);

		int id = eventDao.insertObjects(event);
		event.setId(id);

		
//Tickets Info builder
		
		String[] idTicketsArray = request.getParameterValues("ticket_id");
		
		if (idTicketsArray != null && idTicketsArray.length != 0){
			ReflectionDao<TicketInfo> ticketInfoDao = new ReflectionDao<>(TicketInfo.class);
			for (String currInt : idTicketsArray){
				int currentId = Integer.parseInt(currInt);
				String type;
				String ticketInfoName = request.getParameter("event_ticket_name_" + currentId);
				String ticketDescription = request.getParameter("ticket_description_" + currentId);
				int maxNumber = Integer.parseInt(request.getParameter("event_ticket_quantity_" + currentId));
				String stringPrice = request.getParameter("event_ticket_price_" + currentId);
				
				try {
					startDate = new SimpleDateFormat(DATE_FORMAT).parse(request.getParameter("ticket_start_date_" + currentId));
					endDate = new SimpleDateFormat(DATE_FORMAT).parse(request.getParameter("ticket_end_date_" + currentId));
					startTime = new SimpleDateFormat(TIME_FORMAT).parse(request.getParameter("ticket_start_time_" + currentId));
					endTime = new SimpleDateFormat(TIME_FORMAT).parse(request.getParameter("ticket_end_time_" + currentId));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				int price;
				if (stringPrice.equals("Free")){
					type = "Free";
					price = 0;
				} else if (stringPrice.equals("Donation")){
					type = "Donation";
					price = 0;
				} else {
					type = "Paid";
					price = Integer.parseInt(request.getParameter("event_ticket_price_" + currentId));
				}
				TicketInfo ticketInfo = new TicketInfo(ticketInfoName, event, type, ticketDescription, maxNumber, price, startDate, startTime, endDate, endTime);
						
				ticketInfoDao.insertObjects(ticketInfo);
				System.out.println(ticketInfo);
			}
		}
		
		
// Promo codes builder
        String[] idPromoCodesArray = request.getParameterValues("promo_code_id");
		
		if (idPromoCodesArray != null && idPromoCodesArray.length != 0){
			ReflectionDao<PromoCode> promoCodeDao = new ReflectionDao<>(PromoCode.class);
			for (String currInt : idPromoCodesArray){
				int currentId = Integer.parseInt(currInt);
				String code = request.getParameter("promo_code_code_" + currentId);
				int discount = Integer.parseInt(request.getParameter("promo_code_discount_" + currentId));
				String promoCodeDescription = request.getParameter("promo_code_description_" + currentId);
				int maxNumber = Integer.parseInt(request.getParameter("promo_code_quantity_" + currentId));
				
				PromoCode promoCode = new PromoCode(event, code, discount, promoCodeDescription, maxNumber);
						
				promoCodeDao.insertObjects(promoCode);
				System.out.println(promoCode);
			}
		}
		
		return id;
	}
	
	public void updateEvent(HttpServletRequest request, HttpServlet servlet) throws IllegalStateException, IOException, ServletException{
		int eventId = Integer.parseInt(request.getParameter("eventId"));
		Event event = new ReadGenericObjectService<>(Event.class).readById(eventId); 
		
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

		Organizer organizer = new ReadGenericObjectService<>(Organizer.class).readById(Integer.parseInt(request.getParameter("organizer")));
		Date startDate = null, endDate = null, startTime = null, endTime = null;
		try {
			startDate = new SimpleDateFormat(DATE_FORMAT).parse(request.getParameter("start_date"));
			endDate = new SimpleDateFormat(DATE_FORMAT).parse(request.getParameter("end_date"));
			startTime = new SimpleDateFormat(TIME_FORMAT).parse(request.getParameter("start_time"));
			endTime = new SimpleDateFormat(TIME_FORMAT).parse(request.getParameter("end_time"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		EventCategory eventCategory = new ReadGenericObjectService<>(EventCategory.class).readById(Integer.parseInt(request.getParameter("event_category")));  
		EventType eventType = new ReadGenericObjectService<>(EventType.class).readById(Integer.parseInt(request.getParameter("event_type")));

		String description = request.getParameter("event_description");
		String location = request.getParameter("event_location");
		boolean privacy = false;
		if (!request.getParameter("privacy_event").equals("0")){
			privacy = true;
		}
		
		Event updatedEvent = new Event(eventId, organizer, name, description, eventCategory, eventType, startDate, startTime, endDate, endTime, location, eventLogo, eventBackground, privacy);
		
		ReflectionDao<Event> eventDao = new ReflectionDao<>(Event.class);
		eventDao.updateObjects(updatedEvent);
	}
	
	public void updateTicketsInfo(HttpServletRequest request){
		ReflectionDao<TicketInfo> ticketInfoDao = new ReflectionDao<>(TicketInfo.class);
		int eventId = Integer.parseInt(request.getParameter("eventId"));
		
		Event event = new ReadGenericObjectService<>(Event.class).readById(eventId);
		List <TicketInfo> currentTicketsInfoList = new ReadGenericObjectService<>(TicketInfo.class).readListByField("event_ref=", eventId);
		List<String> currentIdTicketsInfoList = new ArrayList<>();
		for (TicketInfo ticketInfo : currentTicketsInfoList) {
			currentIdTicketsInfoList.add(Integer.toString(ticketInfo.getId()));
		}

        String[] idTicketsArray = request.getParameterValues("ticket_id");

		for (String newId : idTicketsArray) {
			TicketInfo ticketInfo;
			int currentId = Integer.parseInt(newId);
			String ticketInfoName = request.getParameter("event_ticket_name_" + currentId);
			String type;
			String ticketDescription = request.getParameter("ticket_description_" + currentId);
			int maxNumber = Integer.parseInt(request.getParameter("event_ticket_quantity_" + currentId));
			String stringPrice = request.getParameter("event_ticket_price_" + currentId);
			int price;
			if (stringPrice.equals("Free")){
				type = "Free";
				price = 0;
			} else if (stringPrice.equals("Donation")){
				type = "Donation";
				price = 0;
			} else {
				type = "Paid";
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
			if (!Arrays.asList(idTicketsArray).contains(id)){
				List <Ticket> ticketsList = new ReadGenericObjectService<>(Ticket.class).readListByField("ticket_info_ref=", Integer.parseInt(id));
				if (ticketsList.isEmpty()){
					TicketInfo ticketInfo = new ReadGenericObjectService<>(TicketInfo.class).readById(Integer.parseInt(id));
					ticketInfoDao.deleteObjects(ticketInfo);
				}
				else{
					System.out.println("You cant delete this TicketInfo, tickets already bougth");
				}
			}
		}
	}
	
	public void updatePromoCodes(HttpServletRequest request){
		ReflectionDao<PromoCode> promoCodeDao = new ReflectionDao<>(PromoCode.class);
		int eventId = Integer.parseInt(request.getParameter("eventId"));
		
		Event event = new ReadGenericObjectService<>(Event.class).readById(eventId);
		List <PromoCode> currentPromoCodesList = new ReadGenericObjectService<>(PromoCode.class).readListByField("event_ref=", eventId);
		List<String> currentIdPromoCodesList = new ArrayList<>();
		for (PromoCode promoCode : currentPromoCodesList) {
			currentIdPromoCodesList.add(Integer.toString(promoCode.getId()));
		}

        String[] idPromoCodesArray = request.getParameterValues("promo_code_id");

		for (String newId : idPromoCodesArray) {
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
			if (!Arrays.asList(idPromoCodesArray).contains(id)){
				List <Ticket> ticketsList = new ReadGenericObjectService<>(Ticket.class).readListByField("promo_codes_ref=", Integer.parseInt(id));
				if (ticketsList.isEmpty()){
					PromoCode promoCode = new ReadGenericObjectService<>(PromoCode.class).readById(Integer.parseInt(id));
					promoCodeDao.deleteObjects(promoCode);
				}
				else{
					System.out.println("You cant delete this PromoCode, tickets already bougth");
				}
			}
		}
	}

	public HttpServletRequest readMyEvents(HttpServletRequest request) {
    	HttpSession session = request.getSession(false);
    	User sessionUser = (User) session.getAttribute("user");
    	int userId = sessionUser.getId();
    	List <Organizer> organizersList = new ReadGenericObjectService<>(Organizer.class).readListByField("user_ref=", userId);
    	List <Event> myEvents = new ArrayList<>();

        Map <Integer, Integer> ticketsSold = new HashMap<>();
        Map <Integer, Integer> tickets = new HashMap<>();
    	for (Organizer organizer : organizersList) {
    		List <Event> currentEvents = new ReadGenericObjectService<>(Event.class).readListByField("organizer_ref=", organizer.getId());
    		for (Event event : currentEvents) {
    			int allTicketsSold = 0;
    			int allTickets = 0;
    			try {
					if(!new ReadGenericObjectService<>(TicketInfo.class).readListByField("event_ref=", event.getId()).isEmpty()){
						allTicketsSold += new ReflectionDao<>(Ticket.class).selectAggregateObjects("COUNT(event_ref)", "event_ref=", event.getId()).get(0).intValue();    				
						allTickets += new ReflectionDao<>(TicketInfo.class).selectAggregateObjects("SUM(quantity_available)", "event_ref=", event.getId()).get(0).intValue() + allTicketsSold;
					}
				} catch (Exception e) {
					log.debug("There is no tickets to event");
				}
    			ticketsSold.put(event.getId(), allTicketsSold);
                tickets.put(event.getId(), allTickets);
			}
    		myEvents.addAll(currentEvents);
    	}
    	request.setAttribute("events", myEvents);
        request.setAttribute("tickets", tickets);
        request.setAttribute("ticketsSold", ticketsSold);
		return request;
	}

	private Date combineDateTime (Date date, Date time){
		Calendar calendarA = Calendar.getInstance();
		calendarA.setTime(date);
		Calendar calendarB = Calendar.getInstance();
		calendarB.setTime(time);
		
		calendarA.set(Calendar.HOUR_OF_DAY, calendarB.get(Calendar.HOUR_OF_DAY));
		calendarA.set(Calendar.MINUTE, calendarB.get(Calendar.MINUTE));
		calendarA.set(Calendar.SECOND, calendarB.get(Calendar.SECOND));
		calendarA.set(Calendar.MILLISECOND, calendarB.get(Calendar.MILLISECOND));
		
		Date result = calendarA.getTime();
		return result;
	}

	public List<TicketInfo> readTicketInfo(int eventId){
		List <TicketInfo> ticketsInfoList = new ReadGenericObjectService<>(TicketInfo.class).readListByField("event_ref=", eventId);;
        
        Date currentDate = new Date();
		Iterator<TicketInfo> iter = ticketsInfoList.iterator();
		while (iter.hasNext()){
			TicketInfo currentTicketInfo = iter.next();
			Date startDate = combineDateTime(currentTicketInfo.getStartDate(), currentTicketInfo.getStartTime());
			Date endDate = combineDateTime(currentTicketInfo.getEndDate(), currentTicketInfo.getEndTime());
			if(startDate.compareTo(currentDate) > 0){
				iter.remove();
			}
			else if (endDate.compareTo(currentDate) < 0){
				iter.remove();
			}
		}
		return ticketsInfoList;
	}

	public void deleteEvent(HttpServletRequest request, int id){
		List <Ticket> ticketsList = new ReadGenericObjectService<>(Ticket.class).readListByField("event_ref=", id);
		TicketsService ticketsService = new TicketsService();
		for (Ticket ticket : ticketsList) {
			ticketsService.delete(request, ticket.getId());
		}
		ReflectionDao<TicketInfo> ticketInfoDao = new ReflectionDao<>(TicketInfo.class);
		List <TicketInfo> ticketInfoList = new ReadGenericObjectService<>(TicketInfo.class).readListByField("event_ref=", id);
		for (TicketInfo ticketInfo : ticketInfoList) {
			ticketInfoDao.deleteObjects(ticketInfo);
		}
		ReflectionDao<PromoCode> promoCodeDao = new ReflectionDao<>(PromoCode.class);
		List <PromoCode> promoCodeList = new ReadGenericObjectService<>(PromoCode.class).readListByField("event_ref=", id);
		for (PromoCode promoCode : promoCodeList) {
			promoCodeDao.deleteObjects(promoCode);
		}
		new ReflectionDao<>(Event.class).deleteObjects(new ReadGenericObjectService<>(Event.class).readById(id));
	}

	public List<Event> search (HttpServletRequest request){
		//Query builder		
				String searchText = request.getParameter("search");
				String query = "name,%" + searchText + "%, description,%" + searchText + "%";
				
				if (!(request.getParameter("event_category") == null) && !request.getParameter("event_category").equals("-1")){
					query = query + ", category_ref=, " + request.getParameter("event_category");
				}
								
				if (!(request.getParameter("event_type") == null) && !request.getParameter("event_type").equals("-1")){
					query = query + ", type_ref=, " + request.getParameter("event_type");
				}
				
				if (!(request.getParameter("start_date") == null) && !request.getParameter("start_date").equals("")){
					query = query + ", start_date>" + request.getParameter("start_date");
				}
				
				if (!(request.getParameter("end_date") == null) && !request.getParameter("end_date").equals("")){
					query = query + ", end_date<" + request.getParameter("end_date");
				}

				String[] queryToExecute = query.split(",");
				
				List <Event> events = new ReflectionDao<>(Event.class).selectObjects(2, queryToExecute);

				Iterator<Event> iter = events.iterator();

				if (!(request.getParameter("min_price") == null) && !request.getParameter("min_price").equals("0")){
					while (iter.hasNext()){
						Event currentEvent = iter.next();
						List<TicketInfo> currentEventTicketsInfo = new ReflectionDao<>(TicketInfo.class).selectObjects(1, "event_ref=", currentEvent.getId(), "price>", request.getParameter("min_price"));
						if (currentEventTicketsInfo.isEmpty()){
							iter.remove();
						}		
					}
				}
				
				if (!(request.getParameter("max_price") == null) && !request.getParameter("max_price").equals("0")){
					while (iter.hasNext()){
						Event currentEvent = iter.next();
						List<TicketInfo> currentEventTicketsInfo = new ReflectionDao<>(TicketInfo.class).selectObjects(1, "event_ref=", currentEvent.getId(), "price<", request.getParameter("max_price"));
						if (currentEventTicketsInfo.isEmpty()){
							iter.remove();
						}		
					}
				}
				
				request.removeAttribute("search_again"); 
				request.removeAttribute("event_category");
				request.removeAttribute("event_type");
				request.removeAttribute("start_date");
				request.removeAttribute("end_date");
				request.removeAttribute("min_price");
				request.removeAttribute("max_price");
				return events;
	}
}