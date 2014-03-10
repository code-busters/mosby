package main.java.com.mosby.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="event_admins")
public class EventAdmin {
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="event_ref")	
	private int eventRef;
	
	@Column(name="user_ref")
	private int userRef;
	
	@Column(name="permission_ref")
	private int rermissionRef;

	public EventAdmin(int id, int eventRef, int userRef, int rermissionRef) {
		super();
		this.id = id;
		this.eventRef = eventRef;
		this.userRef = userRef;
		this.rermissionRef = rermissionRef;
	}

	public EventAdmin(int eventRef, int userRef, int rermissionRef) {
		super();
		this.eventRef = eventRef;
		this.userRef = userRef;
		this.rermissionRef = rermissionRef;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEventRef() {
		return eventRef;
	}

	public void setEventRef(int eventRef) {
		this.eventRef = eventRef;
	}

	public int getUserRef() {
		return userRef;
	}

	public void setUserRef(int userRef) {
		this.userRef = userRef;
	}

	public int getRermissionRef() {
		return rermissionRef;
	}

	public void setRermissionRef(int rermissionRef) {
		this.rermissionRef = rermissionRef;
	}
	

}
