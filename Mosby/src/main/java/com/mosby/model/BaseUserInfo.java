package main.java.com.mosby.model;

public class BaseUserInfo {
	
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private int admin;
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
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	public BaseUserInfo(int id, String firstName, String lastName,
			String email, String password, int isAdmin) {
		this.setId(id);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setPassword(password);
		this.setAdmin(isAdmin);
	}
	public BaseUserInfo(String firstName, String lastName, String email,
			String password) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setPassword(password);
	}
	
	public BaseUserInfo() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "BaseUserInfo [id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", admin=" + admin + "]";
	}
	
	

}
