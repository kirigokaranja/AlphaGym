package com.kirigokaranja.alphagym.Model;

public class Workout {

    private String WorkoutName;
    private String Workoutdate;
    private String WorkoutReps;
    private String WorkoutSets;
    private String WorkoutLocation;
    private int WorkoutThumbnail;

    public Workout() {
    }

    public Workout(String workoutName, String workoutdate, String workoutReps, String workoutSets, String workoutLocation,  int workoutThumbnail) {
        WorkoutName = workoutName;
        Workoutdate = workoutdate;
        WorkoutReps = workoutReps;
        WorkoutSets = workoutSets;
        WorkoutLocation = workoutLocation;
        WorkoutThumbnail = workoutThumbnail;
    }

    public Workout(String workoutName, String workoutdate, String workoutReps, String workoutSets, String workoutLocation) {
        WorkoutName = workoutName;
        Workoutdate = workoutdate;
        WorkoutReps = workoutReps;
        WorkoutSets = workoutSets;
        WorkoutLocation = workoutLocation;
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


    public String getWorkoutLocation() {
        return WorkoutLocation;
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


    public void setWorkoutLocation(String workoutLocation) {
        WorkoutLocation = workoutLocation;
    }

    public void setWorkoutThumbnail(int workoutThumbnail) {
        WorkoutThumbnail = workoutThumbnail;
    }
}
