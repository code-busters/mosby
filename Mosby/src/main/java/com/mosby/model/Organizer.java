package main.java.com.mosby.model;

import main.java.com.mosby.model.annotations.dao.*;

@Table(name = "organizers")
public class Organizer {

	@Column(name = "id")
	private int id;
	
	@Key(name = "user_ref")
	private User user = null;
	
	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "about")
	private String about;

	@Column(name = "location")
	private String location;
	
	@Column(name = "logo_image")
	private String logoImage;

	public Organizer() {
	}

	public Organizer(int id, User user, String name, String email,
			String phone, String about, String location, String logoImage) {
		super();
		this.id = id;
		this.user = user;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.about = about;
		this.location = location;
		this.logoImage = logoImage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLogoImage() {
		return logoImage;
	}

	public void setLogoImage(String logoImage) {
		this.logoImage = logoImage;
	}

}
