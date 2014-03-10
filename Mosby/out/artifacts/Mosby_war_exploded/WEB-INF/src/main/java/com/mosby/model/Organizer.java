package main.java.com.mosby.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "organizers")
public class Organizer {

	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "base_user_info_ref")
	private int baseUserInfoRef;
	
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

	public Organizer(int baseUserInfoRef, String name, String email,
			String phone, String about, String location, String logoImage) {
		super();
		this.baseUserInfoRef = baseUserInfoRef;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.about = about;
		this.location = location;
		this.logoImage = logoImage;
	}

	public Organizer(int id, int baseUserInfoRef, String name, String email,
			String phone, String about, String location, String logoImage) {
		super();
		this.id = id;
		this.baseUserInfoRef = baseUserInfoRef;
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

	public int getBaseUserInfoRef() {
		return baseUserInfoRef;
	}

	public void setBaseUserInfoRef(int baseUserInfoRef) {
		this.baseUserInfoRef = baseUserInfoRef;
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
