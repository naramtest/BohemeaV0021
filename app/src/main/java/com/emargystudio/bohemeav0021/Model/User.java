package com.emargystudio.bohemeav0021.Model;

public class User {
    private int userId;
    private String userName;
    private String userEmail;
    private String userPhoto;
    private int userPhoneNumer;

    public User(int userId, String userName, String userEmail, String userPhoto, int userPhoneNumer) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhoto = userPhoto;
        this.userPhoneNumer = userPhoneNumer;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public int getUserPhoneNumer() {
        return userPhoneNumer;
    }

    public void setUserPhoneNumer(int userPhoneNumer) {
        this.userPhoneNumer = userPhoneNumer;
    }
}
