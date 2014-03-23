package main.java.com.mosby.model;

import main.java.com.mosby.model.annotations.dao.*;
import main.java.com.mosby.model.annotations.validate.NotNull;
import main.java.com.mosby.model.annotations.validate.Size;


@Table(name="event_types")
public class EventType {
	
	@Column(name = "id")
	private int id;
	
	@NotNull
	@Size(min=0, max=45)
	@Column(name = "type")	
	private String type;
	
	public EventType() {
	}

	public EventType(int id, String type) {
		super();
		this.id = id;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
