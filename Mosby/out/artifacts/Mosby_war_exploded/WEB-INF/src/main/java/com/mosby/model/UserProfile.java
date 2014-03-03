package main.java.com.mosby.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "base_user_infos")
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
	
	
}
