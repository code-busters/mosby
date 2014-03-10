package main.java.com.mosby.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="promo_codes")
public class PromoCode {
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="event_ref")	
	private int eventRef;
	
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

	public PromoCode(int eventRef, String code, int discount,
			String description, int maxNumber) {
		super();
		this.eventRef = eventRef;
		this.code = code;
		this.discount = discount;
		this.description = description;
		this.maxNumber = maxNumber;
	}

	public PromoCode(int id, int eventRef, String code, int discount,
			String description, int maxNumber) {
		super();
		this.id = id;
		this.eventRef = eventRef;
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

	public int getEventRef() {
		return eventRef;
	}

	public void setEventRef(int eventRef) {
		this.eventRef = eventRef;
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
