package main.java.com.mosby.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import main.java.com.mosby.model.annotations.validate.*;

@Entity
@Table(name = "base_user_infos")
public class BaseUserInfo {
	@Id
	@Column(name = "id")
	private int id;

	@NotNull
	@Size(min=1, max=30)
	@Column(name = "first_name")
	private String firstName;

	@NotNull
	@Size(min=1, max=50)
	@Column(name = "last_name")
	private String lastName;

	@NotNull
	@Size(min=1, max=30)
	@Email(pattern = "[a-zA-Z]{1}[a-zA-Z\\d\\u002E\\u005F]+@([a-zA-Z]+\\u002E){1,2}((net)|(com)|(org))")
	@Column(name = "email")
	private String email;

	@NotNull
	@Size(min=8, max=250)
	@Password(pattern = "((?=.*\\d)((?=.*[a-z])|(?=.*[à-ÿ]))((?=.*[A-Z])|(?=.*[À-ß])).{3,250})")
	@Column(name = "password")
	private String password;
	
	@Size(min=0, max=10000)
	@Column(name = "credits")
	private double credits;

	@Column(name = "admin")
	private boolean admin;

    public BaseUserInfo() {
    }

    public BaseUserInfo(int id, String firstName, String lastName,
                        String email, String password, double credits, boolean admin) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.admin = admin;
    }

    public BaseUserInfo(String firstName, String lastName, String email,
                        String password, double credits, boolean admin) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.admin = admin;
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

	public double getCredits() {
		return credits;
	}
	
	public void setCredits(double credits) {
		this.credits = credits;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "BaseUserInfo [id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", password="
				+ password + ", admin=" + admin + "]";
	}

}
