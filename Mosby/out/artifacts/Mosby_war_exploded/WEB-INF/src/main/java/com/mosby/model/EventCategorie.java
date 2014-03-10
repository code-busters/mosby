package main.java.com.mosby.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="event_categories")
public class EventCategorie {	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="type")	
	private String type;

	public EventCategorie() {
	}
	
	
	public EventCategorie(String type) {
		super();
		this.type = type;
	}

	public EventCategorie(int id, String type) {
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