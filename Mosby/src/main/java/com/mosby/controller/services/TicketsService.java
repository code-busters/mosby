package main.java.com.mosby.controller.services;

import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.model.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

public class TicketsService {
	
	@SuppressWarnings("unchecked")
	public void register(HttpServletRequest request){
		ReflectionDao<Ticket> ticketDao = new ReflectionDao<>(Ticket.class);
		int eventId = Integer.parseInt(request.getParameter("eventId"));
		Date timeOfPurchase = new Date();
		PromoCode promoCode = null;
		String enteredPromoCode = request.getParameter("promo_code"); 
		List <PromoCode> promoCodesList = new ReadGenericObjectService<>(PromoCode.class).readListByField("event_ref", eventId);
        for (PromoCode code : promoCodesList) {
			if (code.getCode().equals(enteredPromoCode)){
				promoCode = code;
			}
		}
        User user = (User) request.getSession(false).getAttribute("user");
        Event event = new ReadGenericObjectService<>(Event.class).readById(eventId);
        
        List <TicketInfo> ticketsInfoList = new ReadGenericObjectService<>(TicketInfo.class).readListByField("event_ref", eventId);
        for (TicketInfo ticketInfo : ticketsInfoList) {
        	int ticketInfoId = ticketInfo.getId();
        	if (request.getParameter("ticket_quantity_" + ticketInfoId) != null){
        		int ticketQuantity = Integer.parseInt(request.getParameter("ticket_quantity_" + ticketInfoId));
        		for (int i = 0; i < ticketQuantity; i++){
        			Ticket ticket = new Ticket(ticketInfo, timeOfPurchase, promoCode, false, user, event);
        			System.out.println(ticket);
        			ticketDao.insertObjects(ticket);
        		}
        	}
		}
	}
	
	public void check(HttpServletRequest request){
		ReflectionDao<Ticket> ticketDao = new ReflectionDao<>(Ticket.class);
		String[] tickets = request.getParameterValues("checked_tickets");
		for (String string : tickets) {
			Ticket ticket = new ReadGenericObjectService<>(Ticket.class).readById(Integer.parseInt(string));
			ticket.setChecked(true);
			ticketDao.updateObjects(ticket);
		}
	}
	
	public void delete(HttpServletRequest request){
		ReflectionDao<Ticket> ticketDao = new ReflectionDao<>(Ticket.class);
		String[] tickets = request.getParameterValues("checked_tickets");
		for (String string : tickets) {
			Ticket ticket = new ReadGenericObjectService<>(Ticket.class).readById(Integer.parseInt(string));
			ticketDao.deleteObjects(ticket);
		}
	}
	
	public void save(HttpServletRequest request){
		
	}
	
}
