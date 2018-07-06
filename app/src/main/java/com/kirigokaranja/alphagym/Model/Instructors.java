package com.kirigokaranja.alphagym.Model;



public class Instructors {


    private String InstructorName;
    private String InstructorGender;
    private String InstructorContact;
    private String InstructorEmail;
    private String InstructorBio;
    private String Thumbnail;
    private int Id;
    private int GymId;

    private Instructors(){
    }

    public Instructors(String instructorName, String instructorGender, String instructorContact, String instructorEmail, String instructorBio, String thumbnail) {
        InstructorName = instructorName;
        InstructorGender = instructorGender;
        InstructorContact = instructorContact;
        InstructorEmail = instructorEmail;
        InstructorBio = instructorBio;
        Thumbnail = thumbnail;
    }

    public Instructors(String instructorName, String instructorGender, String instructorContact, String instructorEmail, String instructorBio, String thumbnail, int id, int gymId) {
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

    public String getThumbnail() {
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

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setGymId(int gymId) {
        GymId = gymId;
    }
}
