package main.java.com.mosby.model;

import java.io.Serializable;

import main.java.com.mosby.model.annotations.dao.*;
import main.java.com.mosby.model.annotations.validate.NotNull;
import main.java.com.mosby.model.annotations.validate.Size;

@Table(name="event_categories")
public class EventCategory implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Column(name="id")
	private int id;
	
	@NotNull
	@Size(min=0, max=45)
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