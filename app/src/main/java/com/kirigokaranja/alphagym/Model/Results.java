package com.kirigokaranja.alphagym.Model;

import com.google.gson.annotations.SerializedName;

public class Results {

    @SerializedName("status")
    private Boolean status;

    @SerializedName("message")
    private String message;

    @SerializedName("user")
    private User user;

    @SerializedName("instructor")
    private Instructors instructor;

    public Results(Boolean status, String message, User user) {
        this.status = status;
        this.message = message;
        this.user = user;
    }

    public Results(Boolean status, String message, Instructors instructor) {
        this.status = status;
        this.message = message;
        this.instructor = instructor;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }

    public Instructors getInstructor() {
        return instructor;
    }
}
