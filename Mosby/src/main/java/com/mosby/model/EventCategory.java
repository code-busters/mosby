package main.java.com.mosby.model;

import main.java.com.mosby.model.annotations.dao.*;

@Table(name="event_categories")
public class EventCategory {
	
	@Column(name="id")
	private int id;
	
	@Column(name="category")	
	private String category;

	public EventCategory() {
	}

	public EventCategory(int id, String category) {
		super();
		this.id = id;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getcategory() {
		return category;
	}

	public void setcategory(String category) {
		this.category = category;
	}
	
}