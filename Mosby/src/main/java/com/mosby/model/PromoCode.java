package main.java.com.mosby.model;

import main.java.com.mosby.model.annotations.dao.*;
import main.java.com.mosby.model.annotations.validate.Min;
import main.java.com.mosby.model.annotations.validate.NotNull;
import main.java.com.mosby.model.annotations.validate.Size;

@Table(name="promo_codes")
public class PromoCode {
	
	@Column(name="id")
	private int id;
	
	@Key(name="event_ref")	
	private Event event;
	
	@NotNull
	@Size(min=1, max=50)
	@Column(name="code")	
	private String code;
	
	@Size(min=1, max=100)
	@Column(name="discount")	
	private int discount;
	
	@Column(name="description")	
	private String description;
	
	@Min(value=1)
	@Column(name="quantity_available")	
	private int quantityAvailable;

	public PromoCode() {
	}

	public PromoCode(int id, Event event, String code, int discount,
			String description, int quantityAvailable) {
		super();
		this.id = id;
		this.event = event;
		this.code = code;
		this.discount = discount;
		this.description = description;
		this.quantityAvailable = quantityAvailable;
	}

	public PromoCode(Event event, String code, int discount,
			String description, int quantityAvailable) {
		super();
		this.event = event;
		this.code = code;
		this.discount = discount;
		this.description = description;
		this.quantityAvailable = quantityAvailable;
	}
	
	public PromoCode(String code, int discount,
			String description, int quantityAvailable) {
		super();
		this.code = code;
		this.discount = discount;
		this.description = description;
		this.quantityAvailable = quantityAvailable;
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

	public int getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}
	
}
