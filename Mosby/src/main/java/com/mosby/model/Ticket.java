package main.java.com.mosby.model;

import java.util.Date;

import main.java.com.mosby.model.annotations.dao.Column;
import main.java.com.mosby.model.annotations.dao.Key;
import main.java.com.mosby.model.annotations.dao.Table;


@Table(name="tickets")
public class Ticket {
	
	@Column(name="id")
	private int id;
	
	@Key(name="ticket_info_ref")	
	private TicketInfo ticketInfo;
	
	@Column(name="time_of_purchase")	
	private Date timeOfPurchase;
	
	@Key(name="promo_code_ref")	
	private PromoCode promoCode;
	
	@Column(name="checked")	
	private boolean checked;
	
	@Key(name="user_ref")	
	private User user;
	
	@Key(name="event_ref")
	private Event event;

	public Ticket() {
	}


	public Ticket(TicketInfo ticketInfo, Date timeOfPurchase, PromoCode promoCode, boolean checked, User user, Event event) {
		this.ticketInfo = ticketInfo;
		this.timeOfPurchase = timeOfPurchase;
		this.promoCode = promoCode;
		this.checked = checked;
		this.user = user;
		this.event = event;
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
