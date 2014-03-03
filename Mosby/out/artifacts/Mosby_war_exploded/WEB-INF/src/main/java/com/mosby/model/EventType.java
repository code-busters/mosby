package main.java.com.mosby.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="event_types")
public class EventType {
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="type")	
	private String type;

	public EventType(String type) {
		super();
		this.type = type;
	}

	public EventType(int id, String type) {
		super();
		this.id = id;
		this.type = type;
	}
}
