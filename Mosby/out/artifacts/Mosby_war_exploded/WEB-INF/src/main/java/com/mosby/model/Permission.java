package main.java.com.mosby.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="permissions")
public class Permission {
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="admin_panel")	
	private boolean adminPanel;
	
	@Column(name="edit_event")	
	private boolean editEvent;
	
	@Column(name="invite")	
	private boolean invite;
	
	@Column(name="delete_users")	
	private boolean deleteUsers;

	public Permission(int id, boolean adminPanel, boolean editEvent,
			boolean invite, boolean deleteUsers) {
		super();
		this.id = id;
		this.adminPanel = adminPanel;
		this.editEvent = editEvent;
		this.invite = invite;
		this.deleteUsers = deleteUsers;
	}

	public Permission(boolean adminPanel, boolean editEvent, boolean invite,
			boolean deleteUsers) {
		super();
		this.adminPanel = adminPanel;
		this.editEvent = editEvent;
		this.invite = invite;
		this.deleteUsers = deleteUsers;
	}
	

}
