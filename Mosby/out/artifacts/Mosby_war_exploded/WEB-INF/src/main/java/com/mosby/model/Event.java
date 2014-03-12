package main.java.com.mosby.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import main.java.com.mosby.model.annotations.validate.EndFuture;
import main.java.com.mosby.model.annotations.validate.Size;
import main.java.com.mosby.model.annotations.validate.StartFuture;

@Entity
@Table(name="events")
public class Event {
	@Id
	@Column(name="id")
	private int id;

	@Column(name="organizers_ref")
	private int organizersRef;
	
	@Column(name="name")	
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="categories_ref")
	private int categoriesRef;
	
	@Column(name="type_ref")
	private int typeRef;
	
	@StartFuture
	@Column(name="start_datetime")
	private Timestamp startDatetime;

	@EndFuture
	@Column(name="end_datetime")
	private Timestamp endDatetime;
	
	@Column(name="location")
	private String location;
	
	@Column(name="logo")
	private String logo;
	
	@Column(name="background")
	private String background;

	public Event() {
	}

	public Event(int organizersRef, String name, String description,
			int catregoriesRef, int typeRef, Timestamp start, Timestamp end,
			String location, String logo, String background) {
		super();
		this.organizersRef = organizersRef;
		this.name = name;
		this.description = description;
		this.categoriesRef = catregoriesRef;
		this.typeRef = typeRef;
		this.startDatetime = start;
		this.endDatetime = end;
		this.location = location;
		this.logo = logo;
	}

	public Event(int id, int organizersRef, String name, String description,
			int catregoriesRef, int typeRef, Timestamp start, Timestamp end,
			String location, String logo, String background) {
		super();
		this.id = id;
		this.organizersRef = organizersRef;
		this.name = name;
		this.description = description;
		this.categoriesRef = catregoriesRef;
		this.typeRef = typeRef;
		this.startDatetime = start;
		this.endDatetime = end;
		this.location = location;
		this.logo = logo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrganizersRef() {
		return organizersRef;
	}

	public void setOrganizersRef(int organizersRef) {
		this.organizersRef = organizersRef;
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

	public int getCategoriesRef() {
		return categoriesRef;
	}

	public void setCategoriesRef(int catregoriesRef) {
		this.categoriesRef = catregoriesRef;
	}

	public int getTypeRef() {
		return typeRef;
	}

	public void setTypeRef(int typeRef) {
		this.typeRef = typeRef;
	}

	public Timestamp getStartDatetime() {
		return startDatetime;
	}

	public void setStartDatetime(Timestamp startDatetime) {
		this.startDatetime = startDatetime;
	}

	public Timestamp getEndDatetime() {
		return endDatetime;
	}

	public void setEndDatetime(Timestamp endDatetime) {
		this.endDatetime = endDatetime;
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
}
