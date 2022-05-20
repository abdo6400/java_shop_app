package com.example.myshopapp.Model;

public class UserDataModel {

    private String userID;
    private String userName;
    private String phoneNumber;
    private String imageUrl;

    public UserDataModel() {
    }

    public UserDataModel(String userID, String userName, String phoneNumber) {
        this.userID = userID;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
