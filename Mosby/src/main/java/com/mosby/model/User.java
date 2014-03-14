package main.java.com.mosby.model;

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
	
	@Key(name = "user_profile_key")
	private UserProfile userProfile;

	public User() {

	}

	public User(int id, UserProfile userProfile, String firstName,
			String lastName, String email, String password, double credits,
			boolean admin) {
		super();
		this.id = id;
		this.userProfile = userProfile;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.credits = credits;
		this.admin = admin;
	}

	public User(String firstName, String lastName, String email,
			String password, double credits, boolean admin) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.credits = credits;
		this.admin = admin;
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

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", password=" + password
				+ ", credits=" + credits + ", admin=" + admin
				+ ", userProfile=" + userProfile + "]";
	}

}
