package indi.mat.work.android.model.bean;

import java.io.Serializable;

public class WareHouse implements Serializable {
    private int id;
    private String name;
    private String nameNo;

    public WareHouse(int id, String name, String nameNo) {
        this.id = id;
        this.name = name;
        this.nameNo = nameNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameNo() {
        return nameNo;
    }

    public void setNameNo(String nameNo) {
        this.nameNo = nameNo;
    }
}
