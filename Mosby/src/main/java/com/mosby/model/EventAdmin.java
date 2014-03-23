package main.java.com.mosby.model;

import main.java.com.mosby.model.annotations.dao.Column;
import main.java.com.mosby.model.annotations.dao.Key;
import main.java.com.mosby.model.annotations.dao.Table;
import main.java.com.mosby.model.annotations.validate.NotNull;

@Table(name="event_admins")
public class EventAdmin {
	
	@Column(name="id")
	private int id;
	
	@NotNull
	@Key(name="event_ref")	
	private Event event;
	
	@NotNull
	@Key(name="user_ref")
	private User user;
	
	@Key(name="permission_ref")
	private Permission permission;
	
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
