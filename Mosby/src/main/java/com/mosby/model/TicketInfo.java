package main.java.com.mosby.model;

import main.java.com.mosby.model.annotations.dao.*;
import main.java.com.mosby.model.annotations.validate.*;

@Table(name="tickets_info")
public class TicketInfo {
	
	@Column(name="id")
	private int id;
	
	@Key(name = "event_ref")
	private Event event;
	
	@Column(name="type")
	private String type;
	
	@Column(name="description")
	private String description;
	
	@Column(name="max_number")
	private int maxNumber;
	
	@Column(name="price")
	private int price;
	
	
	public TicketInfo() {
	}

	public TicketInfo(int id, Event event, String type, String description,
			int maxNumber, int price) {
		super();
		this.id = id;
		this.event = event;
		this.type = type;
		this.description = description;
		this.maxNumber = maxNumber;
		this.price = price;
	}

	public TicketInfo(Event event, String type, String description,
			int maxNumber, int price) {
		super();
		this.event = event;
		this.type = type;
		this.description = description;
		this.maxNumber = maxNumber;
		this.price = price;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMaxNumber() {
		return maxNumber;
	}

	public void setMaxNumber(int maxNumber) {
		this.maxNumber = maxNumber;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
}
