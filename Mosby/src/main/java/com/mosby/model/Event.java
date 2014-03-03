package main.java.com.mosby.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="events")
public class Event {
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="name")	
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="topic_ref")
	private int topicRef;
	
	@Column(name="type_ref")
	private int typeRef;
	
	@Column(name="start_datetime")
	private Date start;

	@Column(name="end_datetime")
	private Date end;
	
	@Column(name="price")
	private Date price;

	@Column(name="image")
	private Date image;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTopicRef() {
		return topicRef;
	}

	public void setTopicRef(int topicRef) {
		this.topicRef = topicRef;
	}

	public int getTypeRef() {
		return typeRef;
	}

	public void setTypeRef(int typeRef) {
		this.typeRef = typeRef;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Date getPrice() {
		return price;
	}

	public void setPrice(Date price) {
		this.price = price;
	}

	public Date getImage() {
		return image;
	}

	public void setImage(Date image) {
		this.image = image;
	}

	public Event(int id, String name, String description, int topicRef,
			int typeRef, Date start, Date end, Date price, Date image) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.topicRef = topicRef;
		this.typeRef = typeRef;
		this.start = start;
		this.end = end;
		this.price = price;
		this.image = image;
	}

	public Event(String name, String description, int topicRef, int typeRef,
			Date start, Date end, Date price, Date image) {
		super();
		this.name = name;
		this.description = description;
		this.topicRef = topicRef;
		this.typeRef = typeRef;
		this.start = start;
		this.end = end;
		this.price = price;
		this.image = image;
	}
	

}
