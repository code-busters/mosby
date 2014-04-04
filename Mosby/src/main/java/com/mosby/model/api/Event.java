package main.java.com.mosby.model.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Event {

    @SerializedName("id")
    private String mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("attendees")
    private List<Attendee> mAttendees;

    public Event() {
    }

    public Event(String mId, String mName) {
        this.mId = mId;
        this.mName = mName;
    }
    public Event(String mId, String mName, List<Attendee> mAttendees) {
        this.mId = mId;
        this.mName = mName;
        this.mAttendees = mAttendees;
    }

    public boolean isAttendeeRegistered(String attendeeId) {
        for (Attendee attendee : getAttendees()) {
            if(attendeeId.equals(attendee.getId())) {
                return true;
            }
        }
        return false;
    }

    public Attendee getAttendeeById(String attendeeId) {
        for (Attendee attendee : getAttendees()) {
            if(attendeeId.equals(attendee.getId())) {
                return attendee;
            }
        }
        throw new IllegalArgumentException("Attendee " + attendeeId + " is not registered for event");
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public List<Attendee> getAttendees() {
        return mAttendees;
    }

    public void setAttendees(List<Attendee> attendees) {
        mAttendees = attendees;
    }

    @Override
    public String toString() {
        return "Event{" +
                "Id='" + mId + '\'' +
                ", Name='" + mName + '\'' +
                ", Attendees=" + mAttendees +
                '}';
    }
}
