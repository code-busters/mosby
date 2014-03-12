package main.java.com.mosby.model;

import main.java.com.mosby.model.annotations.dao.*;
import main.java.com.mosby.model.annotations.validate.*;

@Table(name="registrations")
public class Registration {
	
	@Column(name="id")
	private int id;
	
	@Key(name="user_ref")	
	private User user = null;
	
	@Key(name="event_ref")
	private Event event = null;

	public Registration() {
	}

	public Registration(int id, User user, Event event) {
		super();
		this.id = id;
		this.user = user;
		this.event = event;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
