package indi.mat.work.android.model.bean;

import java.util.List;

public class LoginResult {

    private Boolean status;
    private String message;
    private List<WareHouse> wareHouseList;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setWareHouseList(List<WareHouse> wareHouseList) {
        this.wareHouseList = wareHouseList;
    }

    public List<WareHouse> getWareHouseList() {
        return wareHouseList;
    }
}
