package com.example.creditsts.model;



public class ResultInfo {
    private String msg;
    private boolean success;
    private StudentInfo user;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public StudentInfo getUser() {
        return user;
    }

    public void setUser(StudentInfo user) {
        this.user = user;
    }
}
