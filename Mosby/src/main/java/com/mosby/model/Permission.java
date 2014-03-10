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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isAdminPanel() {
		return adminPanel;
	}

	public void setAdminPanel(boolean adminPanel) {
		this.adminPanel = adminPanel;
	}

	public boolean isEditEvent() {
		return editEvent;
	}

	public void setEditEvent(boolean editEvent) {
		this.editEvent = editEvent;
	}

	public boolean isInvite() {
		return invite;
	}

	public void setInvite(boolean invite) {
		this.invite = invite;
	}

	public boolean isDeleteUsers() {
		return deleteUsers;
	}

	public void setDeleteUsers(boolean deleteUsers) {
		this.deleteUsers = deleteUsers;
	}
	

}
