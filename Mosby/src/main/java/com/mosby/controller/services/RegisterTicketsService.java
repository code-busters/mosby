package main.java.com.mosby.controller.services;

import java.util.Date;
import java.util.List;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;

import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.model.Event;
import main.java.com.mosby.model.PromoCode;
import main.java.com.mosby.model.Ticket;
import main.java.com.mosby.model.TicketInfo;
import main.java.com.mosby.model.User;

public class RegisterTicketsService {
	
	@SuppressWarnings("unchecked")
	public void register(HttpServletRequest request){
		ReflectionDao<Ticket> ticketDao = new ReflectionDao<>((Class<Ticket>) Ticket.class);
		ReflectionDao<User> userDao = new ReflectionDao<>((Class<User>) User.class);
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
        Event event = new ReadGenericObjectService<Event>((Class<Event>) Event.class).readById(eventId);
        
        List <TicketInfo> ticketsInfoList = new ReadGenericObjectService<TicketInfo>((Class<TicketInfo>) TicketInfo.class).readListByField("event_ref", (Integer)eventId);
        for (TicketInfo ticketInfo : ticketsInfoList) {
        	int ticketInfoId = ticketInfo.getId();
        	if (request.getParameter("ticket_quantity_" + ticketInfoId) != null){
        		int ticketQuantity = Integer.parseInt(request.getParameter("ticket_quantity_" + ticketInfoId));
        		for (int i = 0; i < ticketQuantity; i++){
        			Ticket ticket = new Ticket(ticketInfo, timeOfPurchase, promoCode, false, user, event);
        			int discount;
        			if (promoCode == null){
        				discount = 0;
        			}
        			else{
        				discount = promoCode.getDiscount();
        			}
        			double priceOfTicket = ticketInfo.getPrice()-(ticketInfo.getPrice() * discount * 0.01);
        			if (user.getCredits()>=priceOfTicket){
        				ticketDao.insertObjects(ticket);
        				user.setCredits(user.getCredits()-priceOfTicket);
        				userDao.updateObjects(user);
        				request.getSession().setAttribute("user", user);
        			}
        			else{
        				System.out.println("You have not enouth minerals!!!");
        			}
        		}
        	}
		}
	}
}
