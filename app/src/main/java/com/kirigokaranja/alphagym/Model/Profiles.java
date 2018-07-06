package com.kirigokaranja.alphagym.Model;

public class Profiles {

    private int ProfId;
    private String Profdob;
    private String ProfGender;
    private String Profweight;
    private String Profheight;
    private String Profdweight;
    private String Profgym;
    private int Profuser;

    public Profiles() {
    }

    public Profiles(int profId, String profdob, String profGender, String profweight, String profheight, String profdweight, String profgym, int profuser) {
        ProfId = profId;
        Profdob = profdob;
        ProfGender = profGender;
        Profweight = profweight;
        Profheight = profheight;
        Profdweight = profdweight;
        Profgym = profgym;
        Profuser = profuser;
    }

    public Profiles(int profId, String profdob, String profGender, String profweight, String profheight, String profdweight, String profgym) {
        ProfId = profId;
        Profdob = profdob;
        ProfGender = profGender;
        Profweight = profweight;
        Profheight = profheight;
        Profdweight = profdweight;
        Profgym = profgym;
    }

    public int getProfId() {
        return ProfId;
    }

    public String getProfdob() {
        return Profdob;
    }

    public String getProfGender() {
        return ProfGender;
    }

    public String getProfweight() {
        return Profweight;
    }

    public String getProfheight() {
        return Profheight;
    }

    public String getProfdweight() {
        return Profdweight;
    }

    public String getProfgym() {
        return Profgym;
    }

    public int getProfuser() {
        return Profuser;
    }
}
