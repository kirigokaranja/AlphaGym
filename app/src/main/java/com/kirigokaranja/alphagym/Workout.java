package com.kirigokaranja.alphagym;

public class Workout {

    private String WorkoutName;
    private String Workoutdate;
    private String WorkoutReps;
    private String WorkoutSets;
    private String WorkoutStatus;
    private int WorkoutThumbnail;

    public Workout() {
    }

    public Workout(String workoutName, String workoutdate, String workoutReps, String workoutSets, String workoutStatus, int workoutThumbnail) {
        WorkoutName = workoutName;
        Workoutdate = workoutdate;
        WorkoutReps = workoutReps;
        WorkoutSets = workoutSets;
        WorkoutStatus = workoutStatus;
        WorkoutThumbnail = workoutThumbnail;
    }


    public String getWorkoutName() {
        return WorkoutName;
    }

    public String getWorkoutdate() {
        return Workoutdate;
    }

    public String getWorkoutReps() {
        return WorkoutReps;
    }

    public String getWorkoutSets() {
        return WorkoutSets;
    }

    public String getWorkoutStatus() {
        return WorkoutStatus;
    }

    public int getWorkoutThumbnail() {
        return WorkoutThumbnail;
    }


    public void setWorkoutName(String workoutName) {
        WorkoutName = workoutName;
    }

    public void setWorkoutdate(String workoutdate) {
        Workoutdate = workoutdate;
    }

    public void setWorkoutReps(String workoutReps) {
        WorkoutReps = workoutReps;
    }

    public void setWorkoutSets(String workoutSets) {
        WorkoutSets = workoutSets;
    }

    public void setWorkoutStatus(String workoutStatus) {
        WorkoutStatus = workoutStatus;
    }

    public void setWorkoutThumbnail(int workoutThumbnail) {
        WorkoutThumbnail = workoutThumbnail;
    }
}
