package main.java.com.mosby.model;

import java.util.Date;

import main.java.com.mosby.model.annotations.dao.*;
import main.java.com.mosby.model.annotations.validate.*;

@Table(name = "users")
public class User {

	@Column(name = "id")
	private int id;

	@NotNull
	@Size(min = 1, max = 30)
	@Column(name = "first_name")
	private String firstName;

	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "last_name")
	private String lastName;

	@NotNull
	@Size(min = 1, max = 30)
	@Email(pattern = "[a-zA-Z]{1}[a-zA-Z\\d\\u002E\\u005F]+@([a-zA-Z]+\\u002E){1,2}((net)|(com)|(org))")
	@Column(name = "email")
	private String email;

	@NotNull
	@Size(min = 8, max = 250)
	@Password(pattern = "((?=.*\\d)((?=.*[a-z])|(?=.*[à-ÿ]))((?=.*[A-Z])|(?=.*[À-ß])).{3,250})")
	@Column(name = "password")
	private String password;

	@Column(name = "credits")
	private double credits;

	@Column(name = "admin")
	private boolean admin;

	@Column(name = "image")
	private String image;

	@Column(name = "country")
	private String country;

	@Column(name = "city")
	private String city;

	@Column(name = "birth_date")
	private Date birthDate;

	@Column(name = "site")
	private String site;

	@Column(name = "about")
	private String about;
	
	@Column(name = "authentication_code")
	private String authenticationCode;
	
	@Column(name = "active")
	private boolean active;
	public User() {

	}

	
	public User(int id, String firstName, String lastName, String email,
			String password, double credits, boolean admin, String image,
			String country, String city, Date birthDate, String site,
			String about, String authenticationCode, boolean active) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.credits = credits;
		this.admin = admin;
		this.image = image;
		this.country = country;
		this.city = city;
		this.birthDate = birthDate;
		this.site = site;
		this.about = about;
		this.authenticationCode = authenticationCode;
		this.active = active;
	}


	public User(String firstName, String lastName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getCredits() {
		return credits;
	}

	public void setCredits(double credits) {
		this.credits = credits;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getAuthenticationCode() {
		return authenticationCode;
	}


	public void setAuthenticationCode(String authenticationCode) {
		this.authenticationCode = authenticationCode;
	}
	

	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", password=" + password
				+ ", credits=" + credits + ", admin=" + admin + ", image="
				+ image + ", country=" + country + ", city=" + city
				+ ", birthDate=" + birthDate + ", site=" + site + ", about="
				+ about + ", authenticationCode=" + authenticationCode
				+ ", active=" + active + "]";
	}

}
