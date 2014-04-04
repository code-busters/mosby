package main.java.com.mosby.model.api;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Attendee implements Serializable {

    @SerializedName("id")
    private String mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("surname")
    private String mSurname;

    @SerializedName("ticketType")
    private String mTicketType;

    @SerializedName("avatarUrl")
    private String mAvatarUrl;

    public Attendee() {

    }

    public Attendee(String id, String name, String surname, String ticketType, String avatarUrl) {
        mId = id;
        mName = name;
        mSurname = surname;
        mTicketType = ticketType;
        mAvatarUrl = avatarUrl;
    }

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getSurname() {
        return mSurname;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public String getTicketType() {
        return mTicketType;
    }

    @Override
    public String toString() {
        return "Attendee{" +
                "Id='" + mId + '\'' +
                ", Name='" + mName + '\'' +
                ", Surname='" + mSurname + '\'' +
                ", TicketType='" + mTicketType + '\'' +
                ", AvatarUrl='" + mAvatarUrl + '\'' +
                '}';
    }

}
