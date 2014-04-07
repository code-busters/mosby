package main.java.com.mosby.model.api;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CheckinResult implements Serializable {

    @SerializedName("result")
    private Result mResult;
    @SerializedName("attendee")
    private Attendee mAttendee;

    public CheckinResult(Result result, Attendee attendee) {
        mResult = result;
        mAttendee = attendee;
    }

    public Result getResult() {
        return mResult;
    }

    public void setResult(Result result) {
        mResult = result;
    }

    public Attendee getAttendee() {
        return mAttendee;
    }

    public void setAttendee(Attendee attendee) {
        mAttendee = attendee;
    }

    @Override
    public String toString() {
        return "CheckinResult{" +
                "Result=" + mResult +
                ", Attendee=" + mAttendee +
                '}';
    }

    public enum Result {
        CHECKED_IN, ALREADY_CHECKED_IN, NOT_REGISTERED
    }

}
