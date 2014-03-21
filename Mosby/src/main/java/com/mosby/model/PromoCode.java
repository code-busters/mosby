package main.java.com.mosby.model;

import main.java.com.mosby.model.annotations.dao.*;

@Table(name="promo_codes")
public class PromoCode {
	
	@Column(name="id")
	private int id;
	
	@Key(name="event_ref")	
	private Event event;
	
	@Column(name="code")	
	private String code;
	
	@Column(name="discount")	
	private int discount;
	
	@Column(name="description")	
	private String description;
	
	@Column(name="max_number")	
	private int maxNumber;

	public PromoCode() {
	}

	public PromoCode(int id, Event event, String code, int discount,
			String description, int maxNumber) {
		super();
		this.id = id;
		this.event = event;
		this.code = code;
		this.discount = discount;
		this.description = description;
		this.maxNumber = maxNumber;
	}

	public PromoCode(Event event, String code, int discount,
			String description, int maxNumber) {
		super();
		this.event = event;
		this.code = code;
		this.discount = discount;
		this.description = description;
		this.maxNumber = maxNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
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
	
}
