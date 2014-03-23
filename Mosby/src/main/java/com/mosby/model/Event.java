package main.java.com.mosby.model;

import java.sql.Timestamp;
import java.util.Date;

import main.java.com.mosby.model.annotations.dao.*;
import main.java.com.mosby.model.annotations.validate.*;

@Table(name = "events")
public class Event {

	@Column(name = "id")
	private int id;

	@Key(name = "organizer_ref")
	private Organizer organizer;

	@NotNull
	@Size(min=0, max=40)
	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@NotNull
	@Key(name = "category_ref")
	private EventCategory eventCategory;

	@NotNull
	@Key(name = "type_ref")
	private EventType eventType;

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

	@Size(min=0, max=150)
	@Column(name = "location")
	private String location;

	@Size(min=0, max=100)
	@Column(name = "logo")
	private String logo;

	@Size(min=0, max=100)
	@Column(name = "background")
	private String background;
	
	@Column(name = "privacy")
	private boolean privacy;

	public Event() {
	}
	
	public Event(int id, Organizer organizer, String name, String description,
			EventCategory eventCategory, EventType eventType, Date startDate,
			Date startTime, Date endDate, Date endTime, String location, String logo,
			String background, boolean privacy) {
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
		this.privacy=privacy;
	}
	
	public Event( Organizer organizer, String name, String description,
			EventCategory eventCategory, EventType eventType, Date startDate,
			Date startTime, Date endDate, Date endTime, String location, String logo,
			String background, boolean privacy) {
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
		this.privacy=privacy;
	}



	public Event(String name, Timestamp startDateTime, Timestamp endDateTime, EventCategory eventCategory, EventType eventType) {
		super();
		this.name=name;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.eventCategory = eventCategory;
		this.eventType = eventType;
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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
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

	public Timestamp getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Timestamp startDateTime) {
		this.startDateTime = startDateTime;
	}

	public Timestamp getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(Timestamp endDateTime) {
		this.endDateTime = endDateTime;
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

	

	public boolean isPrivacy() {
		return privacy;
	}

	public void setPrivacy(boolean privacy) {
		this.privacy = privacy;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", organizer=" + organizer + ", name="
				+ name + ", description=" + description + ", eventCategory="
				+ eventCategory + ", eventType=" + eventType + ", startDate="
				+ startDate + ", startTime=" + startTime + ", startDateTime="
				+ startDateTime + ", endDate=" + endDate + ", endTime="
				+ endTime + ", endDateTime=" + endDateTime + ", location="
				+ location + ", logo=" + logo + ", background=" + background
				+ "]";
	}

}