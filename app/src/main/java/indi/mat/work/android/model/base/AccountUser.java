package com.newegg.logistics.model.base;

import java.util.Date;
import java.util.List;

public class AccountUser {
    private String userID;
    private String token;
    private String refreshToken;
    private List<String> whList;

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
}
