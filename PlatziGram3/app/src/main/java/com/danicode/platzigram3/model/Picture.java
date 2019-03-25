package com.danicode.platzigram3.model;

public class Picture {

    private String picture; // URL de internet para imagen
    private String userName;
    private String time;
    private String likesNumber = "0 días";

    public Picture(String picture, String userName, String time, String likesNumber) {
        this.picture = picture;
        this.userName = userName;
        this.time = time;
        this.likesNumber = likesNumber;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLikesNumber() {
        return likesNumber;
    }

    public void setLikesNumber(String likesNumber) {
        this.likesNumber = likesNumber;
    }
}
