package main.java.com.mosby.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="registrations")
public class Registration {
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="user_ref")	
	private int userRef;
	
	@Column(name="event_ref")
	private int eventRef;
	
	@Column(name="checked")
	private boolean checked;
	
	@Column(name="time_of_purchase")
	private Date timeOfPurchase;

	public Registration(int id, int userRef, int eventRef, boolean checked,
			Date timeOfPurchase) {
		super();
		this.id = id;
		this.userRef = userRef;
		this.eventRef = eventRef;
		this.checked = checked;
		this.timeOfPurchase = timeOfPurchase;
	}

	public Registration(int userRef, int eventRef, boolean checked,
			Date timeOfPurchase) {
		super();
		this.userRef = userRef;
		this.eventRef = eventRef;
		this.checked = checked;
		this.timeOfPurchase = timeOfPurchase;
	}
		

}
