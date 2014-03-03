package main.java.com.mosby.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tickets")
public class Ticket {
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="event_ref")	
	private String eventRef;
	
	@Column(name="type")	
	private String type;
	
	@Column(name="description")	
	private int description;
	
	@Column(name="max_number")	
	private boolean maxNumber;

	public Ticket(int id, String eventRef, String type, int description,
			boolean maxNumber) {
		super();
		this.id = id;
		this.eventRef = eventRef;
		this.type = type;
		this.description = description;
		this.maxNumber = maxNumber;
	}

	public Ticket(String eventRef, String type, int description,
			boolean maxNumber) {
		super();
		this.eventRef = eventRef;
		this.type = type;
		this.description = description;
		this.maxNumber = maxNumber;
	}
	
}
