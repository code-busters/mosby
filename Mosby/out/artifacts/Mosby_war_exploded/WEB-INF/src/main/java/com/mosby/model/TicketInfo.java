package main.java.com.mosby.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="tickets_info")
public class TicketInfo {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="event_ref")
	private int eventRef;
	
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

	public TicketInfo(int eventRef, String type, String description,
			int maxNumber, int price) {
		super();
		this.eventRef = eventRef;
		this.type = type;
		this.description = description;
		this.maxNumber = maxNumber;
		this.price = price;
	}

	public TicketInfo(int id, int eventRef, String type, String description,
			int maxNumber, int price) {
		super();
		this.id = id;
		this.eventRef = eventRef;
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

	public int getEventRef() {
		return eventRef;
	}

	public void setEventRef(int eventRef) {
		this.eventRef = eventRef;
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
