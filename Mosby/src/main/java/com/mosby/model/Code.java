package main.java.com.mosby.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="codes")
public class Code {
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="event_ref")	
	private String eventRef;
	
	@Column(name="code")	
	private String code;
	
	@Column(name="description")	
	private int description;
	
	@Column(name="max_number")	
	private boolean maxNumber;

	public Code(int id, String eventRef, String code, int description,
			boolean maxNumber) {
		super();
		this.id = id;
		this.eventRef = eventRef;
		this.code = code;
		this.description = description;
		this.maxNumber = maxNumber;
	}

	public Code(String eventRef, String code, int description, boolean maxNumber) {
		super();
		this.eventRef = eventRef;
		this.code = code;
		this.description = description;
		this.maxNumber = maxNumber;
	}
	
}
