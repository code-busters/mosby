package main.java.com.mosby.model;

import main.java.com.mosby.model.annotations.dao.*;
import main.java.com.mosby.model.annotations.validate.*;

@Table(name="event_admins")
public class EventAdmin {
	
	@Column(name="id")
	private int id;
	
	@Key(name="event_ref")	
	private Event event = null;
	
	@Key(name="user_ref")
	private User user = null;
	
	@Key(name="permission_ref")
	private Permission permission = null;
	
	public EventAdmin() {
	}

	public EventAdmin(int id, Event event, User user, Permission permission) {
		super();
		this.id = id;
		this.event = event;
		this.user = user;
		this.permission = permission;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

}
