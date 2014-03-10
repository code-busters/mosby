package main.java.com.mosby.model;

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

	
	
	public Registration() {
	}

	public Registration(int id, int userRef, int eventRef) {
		super();
		this.id = id;
		this.userRef = userRef;
		this.eventRef = eventRef;
	}

	public Registration(int userRef, int eventRef) {
		super();
		this.userRef = userRef;
		this.eventRef = eventRef;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserRef() {
		return userRef;
	}

	public void setUserRef(int userRef) {
		this.userRef = userRef;
	}

	public int getEventRef() {
		return eventRef;
	}

	public void setEventRef(int eventRef) {
		this.eventRef = eventRef;
	}
		

}
