package com.example.creditsts.model;

import java.io.Serializable;
import java.util.ArrayList;

public class StudentInfo implements Serializable {
    private String account;
    private String telephone;
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
