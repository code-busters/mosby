package main.java.com.mosby.controller.services;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.model.Event;
import main.java.com.mosby.model.PromoCode;
import main.java.com.mosby.model.Ticket;
import main.java.com.mosby.model.TicketInfo;
import main.java.com.mosby.model.User;

public class TicketsService {
	
	@SuppressWarnings("unchecked")
	public void register(HttpServletRequest request){
		ReflectionDao<Ticket> ticketDao = new ReflectionDao<>((Class<Ticket>) Ticket.class);
		int eventId = Integer.parseInt(request.getParameter("eventId"));
		Date timeOfPurchase = new Date();
		PromoCode promoCode = null;
		String enteredPromoCode = request.getParameter("promo_code"); 
		List <PromoCode> promoCodesList = new ReadGenericObjectService<PromoCode>((Class<PromoCode>) PromoCode.class).readListByField("event_ref", (Integer)eventId);
        for (PromoCode code : promoCodesList) {
			if (code.getCode().equals(enteredPromoCode)){
				promoCode = code;
			}
		}
        User user = (User) request.getSession(false).getAttribute("user");
        Event event = new ReadGenericObjectService<Event>((Class<Event>) new Event().getClass()).readById(eventId);
        
        List <TicketInfo> ticketsInfoList = new ReadGenericObjectService<TicketInfo>((Class<TicketInfo>) new TicketInfo().getClass()).readListByField("event_ref", (Integer)eventId);
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
		ReflectionDao<Ticket> ticketDao = new ReflectionDao<>((Class<Ticket>) Ticket.class);
		String[] tickets = request.getParameterValues("checked_tickets");
		for (String string : tickets) {
			Ticket ticket = new ReadGenericObjectService<Ticket>((Class<Ticket>) Ticket.class).readById(Integer.parseInt(string));
			ticket.setChecked(true);
			ticketDao.updateObjects(ticket);
		}
	}
	
	public void delete(HttpServletRequest request){
		ReflectionDao<Ticket> ticketDao = new ReflectionDao<>((Class<Ticket>) Ticket.class);
		String[] tickets = request.getParameterValues("checked_tickets");
		for (String string : tickets) {
			Ticket ticket = new ReadGenericObjectService<Ticket>((Class<Ticket>) Ticket.class).readById(Integer.parseInt(string));
			ticketDao.deleteObjects(ticket);
		}
	}
	
	public void save(HttpServletRequest request){
		
	}
	
}
