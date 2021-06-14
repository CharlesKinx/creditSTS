package com.example.creditsts.model;

import java.io.Serializable;
import java.util.ArrayList;

public class StudentInfo implements Serializable {
    private String name;
    private String studentID;
    private String password;
    private ArrayList<ScoreItemInfo> arrayList;
    private double totalScore = 0.0;

    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }

    public ArrayList<ScoreItemInfo> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<ScoreItemInfo> arrayList) {
        this.arrayList = arrayList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
