package main.java.com.mosby.controller.services;

import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.model.*;
import main.java.com.mosby.utils.MailUtils;

import javax.servlet.http.HttpServletRequest;

import com.sun.jndi.url.dns.dnsURLContextFactory;

import java.util.Date;
import java.util.List;

public class TicketsService {
	
	public void register(HttpServletRequest request){
		int eventId = Integer.parseInt(request.getParameter("eventId"));
		Date timeOfPurchase = new Date();
		PromoCode promoCode = null;
		String enteredPromoCode = request.getParameter("promo_code"); 
		List <PromoCode> promoCodesList = new ReadGenericObjectService<>(PromoCode.class).readListByField("event_ref=", eventId);
        for (PromoCode code : promoCodesList) {
			if (code.getCode().equals(enteredPromoCode)){
				promoCode = code;
			}
		}
        User user = (User) request.getSession(false).getAttribute("user");
        Event event = new ReadGenericObjectService<>(Event.class).readById(eventId);
        
        List <TicketInfo> ticketsInfoList = new ReadGenericObjectService<>(TicketInfo.class).readListByField("event_ref=", eventId);
        for (TicketInfo ticketInfo : ticketsInfoList) {
        	int ticketInfoId = ticketInfo.getId();
        	if (request.getParameter("ticket_quantity_" + ticketInfoId) != null){
        		int ticketQuantity = Integer.parseInt(request.getParameter("ticket_quantity_" + ticketInfoId));
        		for (int i = 0; i < ticketQuantity; i++){
        			if (ticketInfo.getQuantityAvailable() > 0){
        				Ticket ticket = new Ticket(ticketInfo, timeOfPurchase, promoCode, false, user, event);
        				if (!ticketInfo.getType().equals("Free")){
        					double priceOfTicket;
        					if(ticketInfo.getType().equals("Donation")){
        						priceOfTicket = Integer.parseInt(request.getParameter("ticket_price_" + ticketInfoId));
        					}
        					else{
        						int discount = 0;
        						if (promoCode != null && promoCode.getQuantityAvailable() > 0){
        							discount = promoCode.getDiscount();
        							promoCode.setQuantityAvailable(promoCode.getQuantityAvailable()-1);
        							new ReflectionDao<>(PromoCode.class).updateObjects(promoCode);
        						}
        						priceOfTicket = ticketInfo.getPrice()-(ticketInfo.getPrice() * discount * 0.01);
        					}        				
        					if (user.getCredits()>=priceOfTicket){
        						ticketInfo.setQuantityAvailable(ticketInfo.getQuantityAvailable()-1);
        						new ReflectionDao<>(Ticket.class).insertObjects(ticket);
        						new ReflectionDao<>(TicketInfo.class).updateObjects(ticketInfo);
        						user.setCredits(user.getCredits()-priceOfTicket);
        						new ReflectionDao<>(User.class).updateObjects(user);
        						request.getSession().setAttribute("user", user);        						
        					}
        				}
        				else{
        					ticketInfo.setQuantityAvailable(ticketInfo.getQuantityAvailable()-1);
        					new ReflectionDao<>(Ticket.class).insertObjects(ticket);
        					new ReflectionDao<>(TicketInfo.class).updateObjects(ticketInfo);        					
        				}
        				new MailUtils().sendTicket(user.getEmail(), request.getSession().getServletContext().getRealPath(""), ticket);
        			}
        		}
        	}
		}
	}
	
	public void check(HttpServletRequest request){
		String[] tickets = request.getParameterValues("checked_tickets");
		for (String string : tickets) {
			Ticket ticket = new ReadGenericObjectService<>(Ticket.class).readById(Integer.parseInt(string));
			ticket.setChecked(true);
			new ReflectionDao<>(Ticket.class).updateObjects(ticket);
		}
	}

    public void check(int id){
        Ticket ticket = new ReadGenericObjectService<>(Ticket.class).readById(id);
        ticket.setChecked(true);
        new ReflectionDao<>(Ticket.class).updateObjects(ticket);
    }    
	
	public void delete(HttpServletRequest request){
		ReflectionDao<Ticket> ticketDao = new ReflectionDao<>(Ticket.class);
		ReflectionDao<User> userDao = new ReflectionDao<>(User.class);
		String[] tickets = request.getParameterValues("checked_tickets");
		for (String string : tickets) {
			Ticket ticket = new ReadGenericObjectService<>(Ticket.class).readById(Integer.parseInt(string));
			if (ticket.getTicketInfo().getType().equals("Paid")){
				int discount = 0;
				if (ticket.getPromoCode() != null){
					discount = ticket.getPromoCode().getDiscount();
				}
				double priceOfTicket = ticket.getTicketInfo().getPrice()-(ticket.getTicketInfo().getPrice() * discount * 0.01);
				ticket.getUser().setCredits(ticket.getUser().getCredits() + priceOfTicket);
				userDao.updateObjects(ticket.getUser());
				if (ticket.getUser().getId() == ((User) request.getSession(false).getAttribute("user")).getId()){
					request.getSession(false).setAttribute("user", ticket.getUser());
				}
			}
			ticketDao.deleteObjects(ticket);
			ticket.getTicketInfo().setQuantityAvailable(ticket.getTicketInfo().getQuantityAvailable()+1);
			new ReflectionDao<>(TicketInfo.class).updateObjects(ticket.getTicketInfo());
		}
	}
	
	public void delete (HttpServletRequest request, int id){
		Ticket ticket = new ReadGenericObjectService<>(Ticket.class).readById(id);
		if (ticket.getTicketInfo().getType().equals("Paid")){
			int discount = 0;
			if (ticket.getPromoCode() != null){
				discount = ticket.getPromoCode().getDiscount();
			}
			double priceOfTicket = ticket.getTicketInfo().getPrice()-(ticket.getTicketInfo().getPrice() * discount * 0.01);
			ticket.getUser().setCredits(ticket.getUser().getCredits() + priceOfTicket);
			new ReflectionDao<>(User.class).updateObjects(ticket.getUser());
			if (ticket.getUser().getId() == ((User) request.getSession(false).getAttribute("user")).getId()){
				request.getSession(false).setAttribute("user", ticket.getUser());
			}			
		}
		new ReflectionDao<>(Ticket.class).deleteObjects(ticket);
		ticket.getTicketInfo().setQuantityAvailable(ticket.getTicketInfo().getQuantityAvailable()+1);
		new ReflectionDao<>(TicketInfo.class).updateObjects(ticket.getTicketInfo()); 
	}
	
	public void save(HttpServletRequest request){
		String[] tickets = request.getParameterValues("id");
		String[] checkedTickets = request.getParameterValues("checked_in_tickets");
		for (String string : tickets) {
			Ticket ticket = (Ticket) new ReflectionDao<>(Ticket.class).selectObjects(1, "id=", Integer.parseInt(string)).get(0);
			ticket.setChecked(false);
			new ReflectionDao<>(Ticket.class).updateObjects(ticket);
		}
		
		for (String string : checkedTickets) {
			Ticket ticket = (Ticket) new ReflectionDao<>(Ticket.class).selectObjects(1, "id=", Integer.parseInt(string)).get(0);
			ticket.setChecked(true);
			new ReflectionDao<>(Ticket.class).updateObjects(ticket);
		}
	}
	
}
