package main.java.com.mosby.model;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import main.java.com.mosby.model.annotations.dao.*;
import main.java.com.mosby.model.annotations.validate.*;

@Table(name = "events")
public class Event {

	@Column(name = "id")
	private int id;

	@Key(name = "organizer_ref")
	private Organizer organizer = null;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Key(name = "category_ref")
	private EventCategory eventCategory = null;

	@Key(name = "type_ref")
	private EventType eventType = null;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "start_time")
	private Time startTime;

	@StartFuture
	private Timestamp startDateTime;

	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "end_time")
	private Time endTime;

	@EndFuture
	private Timestamp endDateTime;

	@Column(name = "location")
	private String location;

	@Column(name = "logo")
	private String logo;

	@Column(name = "background")
	private String background;

	public Event() {
	}

	public Event(int id, Organizer organizer, String name, String description,
			EventCategory eventCategory, EventType eventType, Date startDate,
			Time startTime, Date endDate, Time endTime, String location,
			String logo, String background) {
		super();
		this.id = id;
		this.organizer = organizer;
		this.name = name;
		this.description = description;
		this.eventCategory = eventCategory;
		this.eventType = eventType;
		this.startDate = startDate;
		this.startTime = startTime;
		this.endDate = endDate;
		this.endTime = endTime;
		this.location = location;
		this.logo = logo;
		this.background = background;
	}

	public Event(Organizer organizer, String name, String description,
			EventCategory eventCategory, EventType eventType, Date startDate,
			Time startTime, Date endDate, Time endTime, String location,
			String logo, String background) {
		super();
		this.organizer = organizer;
		this.name = name;
		this.description = description;
		this.eventCategory = eventCategory;
		this.eventType = eventType;
		this.startDate = startDate;
		this.startTime = startTime;
		this.endDate = endDate;
		this.endTime = endTime;
		this.location = location;
		this.logo = logo;
		this.background = background;
	}

	public Event(Organizer organizer, String name, String description,
			EventCategory eventCategory, EventType eventType, Date startDate,
			Date endDate, String location, String logo, String background) {
		super();
		this.organizer = organizer;
		this.name = name;
		this.description = description;
		this.eventCategory = eventCategory;
		this.eventType = eventType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.location = location;
		this.logo = logo;
		this.background = background;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Organizer getOrganizer() {
		return organizer;
	}

	public void setOrganizer(Organizer organizer) {
		this.organizer = organizer;
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

	public EventCategory getEventCategory() {
		return eventCategory;
	}

	public void setEventCategory(EventCategory eventCategory) {
		this.eventCategory = eventCategory;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public Timestamp getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Date date, Time time) {
		this.startDateTime = startDateTime;
	}

	public Timestamp getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(Date date, Time time) {
		this.endDateTime = endDateTime;
	}

}
