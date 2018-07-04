package com.kirigokaranja.alphagym.Model;

import com.google.gson.annotations.SerializedName;

public class Instructors {

    @SerializedName("name")
    private String InstructorName;
    @SerializedName("gender")
    private String InstructorGender;
    @SerializedName("contact")
    private String InstructorContact;
    @SerializedName("email")
    private String InstructorEmail;
    @SerializedName("bio")
    private String InstructorBio;
    @SerializedName("image")
    private int Thumbnail;
    @SerializedName("id")
    private int Id;
    @SerializedName("gym_id")
    private int GymId;

    private Instructors(){
    }

    public Instructors(String instructorName, String instructorGender, String instructorContact, String instructorEmail, String instructorBio, int thumbnail) {
        InstructorName = instructorName;
        InstructorGender = instructorGender;
        InstructorContact = instructorContact;
        InstructorEmail = instructorEmail;
        InstructorBio = instructorBio;
        Thumbnail = thumbnail;
    }

    public Instructors(String instructorName, String instructorGender, String instructorContact, String instructorEmail, String instructorBio, int thumbnail, int id, int gymId) {
        InstructorName = instructorName;
        InstructorGender = instructorGender;
        InstructorContact = instructorContact;
        InstructorEmail = instructorEmail;
        InstructorBio = instructorBio;
        Thumbnail = thumbnail;
        Id = id;
        GymId = gymId;
    }

    public String getInstructorName() {
        return InstructorName;
    }


    public String getInstructorGender() {
        return InstructorGender;
    }

    public String getInstructorContact() {
        return InstructorContact;
    }

    public String getInstructorEmail() {
        return InstructorEmail;
    }

    public String getInstructorBio() {
        return InstructorBio;
    }

    public int getThumbnail() {
        return Thumbnail;
    }

    public int getId() {
        return Id;
    }

    public int getGymId() {
        return GymId;
    }

    public void setInstructorName(String instructorName) {
        InstructorName = instructorName;
    }


    public void setInstructorGender(String instructorGender) {
        InstructorGender = instructorGender;
    }

    public void setInstructorContact(String instructorContact) {
        InstructorContact = instructorContact;
    }

    public void setInstructorEmail(String instructorEmail) {
        InstructorEmail = instructorEmail;
    }

    public void setInstructorBio(String instructorBio) {
        InstructorBio = instructorBio;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setGymId(int gymId) {
        GymId = gymId;
    }
}
