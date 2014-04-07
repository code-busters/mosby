package main.java.com.mosby.model;

import main.java.com.mosby.model.annotations.dao.Column;
import main.java.com.mosby.model.annotations.dao.Key;
import main.java.com.mosby.model.annotations.dao.Table;
import main.java.com.mosby.model.annotations.validate.NotNull;

import java.util.Date;

@Table(name = "apis")
public class Api {
    @Column(name = "id")
    private int id;

    @Key(name="organizer_ref")
    private Organizer organizer;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "code")
    private String key;

    @NotNull
    @Column(name="time_of_creation")
    private Date timeOfCreation;

    public Api() {
    }

    public Api(int id, Organizer organizer, String name, String key, Date timeOfCreation) {
        this.id = id;
        this.organizer = organizer;
        this.name = name;
        this.key = key;
        this.timeOfCreation = timeOfCreation;
    }

    public Api(Organizer organizer, String name, String key, Date timeOfCreation) {
        this.organizer = organizer;
        this.name = name;
        this.key = key;
        this.timeOfCreation = timeOfCreation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Date getTimeOfCreation() {
        return timeOfCreation;
    }

    public void setTimeOfCreation(Date timeOfCreation) {
        this.timeOfCreation = timeOfCreation;
    }
}
