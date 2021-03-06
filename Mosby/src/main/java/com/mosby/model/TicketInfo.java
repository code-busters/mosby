package main.java.com.mosby.model;

import main.java.com.mosby.model.annotations.dao.Column;
import main.java.com.mosby.model.annotations.dao.Key;
import main.java.com.mosby.model.annotations.dao.Table;
import main.java.com.mosby.model.annotations.validate.EndFuture;
import main.java.com.mosby.model.annotations.validate.Min;
import main.java.com.mosby.model.annotations.validate.NotNull;
import main.java.com.mosby.model.annotations.validate.Size;
import main.java.com.mosby.model.annotations.validate.StartFuture;

import java.sql.Timestamp;
import java.util.Date;

@Table(name="tickets_infos")
public class TicketInfo {
	
	@Column(name="id")
	private int id;
	
	@NotNull
	@Size(min=1, max=20)
	@Column(name="name")
	private String name;

	@Key(name = "event_ref")
	private Event event;
	
	@NotNull
	@Size(min=1, max=45)
	@Column(name="type")
	private String type;
	
	@Size(min=0, max=60)
	@Column(name="description")
	private String description;
	
	@Min(value=1)
	@Column(name="quantity_available")
	private int quantityAvailable;
	
	@Column(name="price")
	private int price;
	
	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "start_time")
	private Date startTime;

	@StartFuture
	@NotNull
	private Timestamp startDateTime;

	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "end_time")
	private Date endTime;

	@EndFuture
	@NotNull
	private Timestamp endDateTime;
	
	public TicketInfo() {
	}
	
	public TicketInfo(String name, String type,
			String description, int quantityAvailable, int price, Timestamp startDateTime, Timestamp endDateTime) {
		super();
		this.name = name;
		this.type = type;
		this.description = description;
		this.quantityAvailable = quantityAvailable;
		this.price = price;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
	}
	
	public TicketInfo(String name, Event event, String type,
			String description, int quantityAvailable, int price, Date startDate,
			Date startTime, Date endDate, Date endTime) {
		super();
		this.name = name;
		this.event = event;
		this.type = type;
		this.description = description;
		this.quantityAvailable = quantityAvailable;
		this.price = price;
		this.startDate = startDate;
		this.startTime = startTime;
		this.endDate = endDate;
		this.endTime = endTime;
	}
	
	public TicketInfo(int id, String name, Event event, String type, String description, int quantityAvailable, int price, Date startDate, Date startTime, Date endDate, Date endTime) {
		super();
		this.id = id;
		this.name = name;
		this.event = event;
		this.type = type;
		this.description = description;
		this.quantityAvailable = quantityAvailable;
		this.price = price;
		this.startDate = startDate;
		this.startTime = startTime;
		this.endDate = endDate;
		this.endTime = endTime;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Timestamp getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Timestamp startDateTime) {
		this.startDateTime = startDateTime;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Timestamp getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(Timestamp endDateTime) {
		this.endDateTime = endDateTime;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
}
