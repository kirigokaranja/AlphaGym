package com.kirigokaranja.alphagym;

public class Instructors {

    private String InstructorName;
    private String InstructorExercise;
    private String InstructorGender;
    private String InstructorContact;
    private String InstructorEmail;
    private String InstructorBio;
    private int Thumbnail;

    private Instructors(){
    }

    public Instructors(String instructorName, String instructorExercise, String instructorGender, String instructorContact, String instructorEmail, String instructorBio, int thumbnail) {
        InstructorName = instructorName;
        InstructorExercise = instructorExercise;
        InstructorGender = instructorGender;
        InstructorContact = instructorContact;
        InstructorEmail = instructorEmail;
        InstructorBio = instructorBio;
        Thumbnail = thumbnail;
    }

    public String getInstructorName() {
        return InstructorName;
    }

    public String getInstructorExercise() {
        return InstructorExercise;
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

    public void setInstructorName(String instructorName) {
        InstructorName = instructorName;
    }

    public void setInstructorExercise(String instructorExercise) {
        InstructorExercise = instructorExercise;
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
}
