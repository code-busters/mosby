package main.java.com.mosby.model;

import java.io.Serializable;

import main.java.com.mosby.model.annotations.dao.*;
import main.java.com.mosby.model.annotations.validate.Size;

@Table(name = "organizers")
public class Organizer implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "id")
	private int id;
	
	@Key(name = "user_ref")
	private User user;
	
	@Size(min=0, max=150)
	@Column(name = "name")
	private String name;

	@Size(min=0, max=50)
	@Column(name = "email")
	private String email;
	
	@Size(min=0, max=18)
	@Column(name = "phone")
	private String phone;
	
	@Size(min=0, max=500)
	@Column(name = "about")
	private String about;

	@Size(min=0, max=100)
    @Column(name = "site")
    private String site;

    @Size(min=0, max=100)
    @Column(name = "google_plus")
    private String googlePlus;

    @Size(min=0, max=100)
    @Column(name = "facebook")
    private String facebook;

    @Size(min=0, max=100)
    @Column(name = "twitter")
    private String twitter;
	
    @Size(min=0, max=100)
	@Column(name = "logo")
	private String logo;

	public Organizer() {
	}

    public Organizer(int id, User user, String name, String email, String phone, String about, String site, String googlePlus, String facebook, String twitter, String logo) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.about = about;
        this.site = site;
        this.googlePlus = googlePlus;
        this.facebook = facebook;
        this.twitter = twitter;
        this.logo = logo;
    }
    
    public Organizer(String name, String email, String phone, String about,
			String site, String googlePlus, String facebook, String twitter) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.about = about;
		this.site = site;
		this.googlePlus = googlePlus;
		this.facebook = facebook;
		this.twitter = twitter;
	}

	public Organizer(User user, String name, String email, String phone,
			String about, String site, String googlePlus, String facebook,
			String twitter, String logo) {
		super();
		this.user = user;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.about = about;
		this.site = site;
		this.googlePlus = googlePlus;
		this.facebook = facebook;
		this.twitter = twitter;
		this.logo = logo;
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

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getGooglePlus() {
        return googlePlus;
    }

    public void setGooglePlus(String googlePlus) {
        this.googlePlus = googlePlus;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
