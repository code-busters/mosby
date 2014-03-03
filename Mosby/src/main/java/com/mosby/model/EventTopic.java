package main.java.com.mosby.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="event_topics")
public class EventTopic {	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="type")	
	private String type;

	public EventTopic(String type) {
		super();
		this.type = type;
	}

	public EventTopic(int id, String type) {
		super();
		this.id = id;
		this.type = type;
	}
	
}