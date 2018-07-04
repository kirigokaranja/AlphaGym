package com.kirigokaranja.alphagym.Classes;

import com.google.gson.annotations.SerializedName;
import com.kirigokaranja.alphagym.Model.Instructors;

import java.util.ArrayList;

public class Instructorarray {

    @SerializedName("instructors")
    private ArrayList<Instructors> instructors;

    public Instructorarray() {
    }

    public ArrayList<Instructors> getInstructors() {
        return instructors;
    }

    public void setInstructors(ArrayList<Instructors> instructors) {
        this.instructors = instructors;
    }
}
