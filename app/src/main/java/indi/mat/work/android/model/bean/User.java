package indi.mat.work.android.model.bean;

import java.util.List;

import indi.mat.work.android.model.base.TokenRefresh;

public class User {
    private String userID;
    private String shortName;
    private String fullName;
    private TokenRefresh tokenRefresh;
    private String currWh;
    private List<String> whList;
    private String url;
    private String avatar;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public TokenRefresh getTokenRefresh() {
        return tokenRefresh;
    }

    public void setTokenRefresh(TokenRefresh tokenRefresh) {
        this.tokenRefresh = tokenRefresh;
    }

    public List<String> getWhList() {
        return whList;
    }

    public void setWhList(List<String> whList) {
        this.whList = whList;
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


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
