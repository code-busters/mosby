package main.java.com.mosby.model;

import java.sql.Date;

import main.java.com.mosby.model.annotations.dao.*;
import main.java.com.mosby.model.annotations.validate.*;

@Table(name="tickets")
public class Ticket {
	
	@Column(name="id")
	private int id;
	
	@Key(name="ticket_info_ref")	
	private TicketInfo ticketInfo = null;
	
	@Column(name="time_of_purchase")	
	private Date timeOfPurchase;
	
	@Key(name="promo_code_ref")	
	private PromoCode promoCode = null;
	
	@Column(name="checked")	
	private boolean checked;
	
	@Key(name="user_ref")	
	private User user = null;
	
	@Key(name="event_ref")
	private Event event = null;

	public Ticket() {
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public TicketInfo getTicketInfo() {
		return ticketInfo;
	}


	public void setTicketInfo(TicketInfo ticketInfo) {
		this.ticketInfo = ticketInfo;
	}


	public Date getTimeOfPurchase() {
		return timeOfPurchase;
	}


	public void setTimeOfPurchase(Date timeOfPurchase) {
		this.timeOfPurchase = timeOfPurchase;
	}


	public PromoCode getPromoCode() {
		return promoCode;
	}


	public void setPromoCode(PromoCode promoCode) {
		this.promoCode = promoCode;
	}


	public boolean isChecked() {
		return checked;
	}


	public void setChecked(boolean checked) {
		this.checked = checked;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Event getEvent() {
		return event;
	}


	public void setEvent(Event event) {
		this.event = event;
	}
	
}
