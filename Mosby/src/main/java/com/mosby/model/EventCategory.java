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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
}