package main.java.com.mosby.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_profiles")
public class UserProfile {
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="base_users_info_ref")	
	private int baseUserInfoRef;
	
	@Column(name="image")
	private String image;
	
	@Column(name="country")
	private String country;
	
	@Column(name="city")	
	private String city;
	
	@Column(name="birth_date")
	private Date birthDate;
	
	@Column(name="site")
	private String site;
	
	@Column(name="about")
	private String about;

	
	public UserProfile() {
	}

	public UserProfile(int id, int baseUserInfoRef, String image,
			String country, String city, Date birthDate, String site,
			String about) {
		super();
		this.id = id;
		this.baseUserInfoRef = baseUserInfoRef;
		this.image = image;
		this.country = country;
		this.city = city;
		this.birthDate = birthDate;
		this.site = site;
		this.about = about;
	}

	public UserProfile(int baseUserInfoRef, String image, String country,
			String city, Date birthDate, String site, String about) {
		super();
		this.baseUserInfoRef = baseUserInfoRef;
		this.image = image;
		this.country = country;
		this.city = city;
		this.birthDate = birthDate;
		this.site = site;
		this.about = about;
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
	
	
}
