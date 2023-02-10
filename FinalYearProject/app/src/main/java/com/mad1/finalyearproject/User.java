package com.mad1.finalyearproject;

import java.util.HashMap;

public class User {
    public String userId;
    public String email;
    public String password;
    public Boolean GeneralAccess;
    public Boolean DevelopmentLabs;
    public Boolean NetworkLabs;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String userId, String email, String password) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.GeneralAccess = false;
        this.DevelopmentLabs = false;
        this.NetworkLabs = false;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getGeneralAccess() {
        return GeneralAccess;
    }

    public void setGeneralAccess(Boolean generalAccess) {
        GeneralAccess = generalAccess;
    }

    public Boolean getDevelopmentLabs() {
        return DevelopmentLabs;
    }

    public void setDevelopmentLabs(Boolean developmentLabs) {
        DevelopmentLabs = developmentLabs;
    }

    public Boolean getNetworkLabs() {
        return NetworkLabs;
    }

    public void setNetworkLabs(Boolean networkLabs) {
        NetworkLabs = networkLabs;
    }
}
