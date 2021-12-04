package com.newegg.logistics.model.bean;

import java.util.List;

public class User {
    private String userID;

    private String token;
    private String refreshToken;
    private List<String> whList;

    private String displayName;
    private String currWh;
    private String url;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public List<String> getWhList() {
        return whList;
    }

    public void setWhList(List<String> whList) {
        this.whList = whList;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getCurrWh() {
        return currWh;
    }

    public void setCurrWh(String currWh) {
        this.currWh = currWh;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
