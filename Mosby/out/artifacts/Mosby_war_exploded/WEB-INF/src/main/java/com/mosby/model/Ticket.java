package main.java.com.mosby.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tickets")
public class Ticket {
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="registrations_ref")	
	private int registrationsRef;
	
	@Column(name="tickets_info_ref")	
	private int ticketsInfoRef;
	
	@Column(name="time_of_purchase")	
	private Date timeOfPurchase;
	
	@Column(name="promo_codes_ref")	
	private int promoCodesRef;
	
	@Column(name="checked")	
	private boolean checked;
	

	public Ticket() {
	}

	public Ticket(int registrationsRef, int ticketsInfoRef,
			Date timeOfPurchase, int promoCodesRef, boolean checked) {
		super();
		this.registrationsRef = registrationsRef;
		this.ticketsInfoRef = ticketsInfoRef;
		this.timeOfPurchase = timeOfPurchase;
		this.promoCodesRef = promoCodesRef;
		this.checked = checked;
	}

	public Ticket(int id, int registrationsRef, int ticketsInfoRef,
			Date timeOfPurchase, int promoCodesRef, boolean checked) {
		super();
		this.id = id;
		this.registrationsRef = registrationsRef;
		this.ticketsInfoRef = ticketsInfoRef;
		this.timeOfPurchase = timeOfPurchase;
		this.promoCodesRef = promoCodesRef;
		this.checked = checked;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRegistrationsRef() {
		return registrationsRef;
	}

	public void setRegistrationsRef(int registrationsRef) {
		this.registrationsRef = registrationsRef;
	}

	public int getTicketsInfoRef() {
		return ticketsInfoRef;
	}

	public void setTicketsInfoRef(int ticketsInfoRef) {
		this.ticketsInfoRef = ticketsInfoRef;
	}

	public Date getTimeOfPurchase() {
		return timeOfPurchase;
	}

	public void setTimeOfPurchase(Date timeOfPurchase) {
		this.timeOfPurchase = timeOfPurchase;
	}

	public int getPromoCodesRef() {
		return promoCodesRef;
	}

	public void setPromoCodesRef(int promoCodesRef) {
		this.promoCodesRef = promoCodesRef;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	
}
