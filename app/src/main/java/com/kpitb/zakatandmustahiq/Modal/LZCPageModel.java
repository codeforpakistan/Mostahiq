package com.kpitb.zakatandmustahiq.Modal;

/**
 * Created by Manxoor on 30/10/2018.
 */

public class LZCPageModel {
    private String nameOfLZC;
    private String chairmanName;
    private String tehsilName;
    private int color;
    private int status_icon;
    private String id;

    private String phoneNumber;

    public LZCPageModel(String nameOfLZC, String tehsilName, String chairmanName, String phoneNumber, int color, int status_icon) {
        this.nameOfLZC = nameOfLZC;
        this.chairmanName = chairmanName;
        this.tehsilName = tehsilName;
        this.color = color;
        this.phoneNumber = phoneNumber;
        this.status_icon = status_icon;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getTehsilName() {
        return tehsilName;
    }

    public void setTehsilName(String tehsilName) {
        this.tehsilName = tehsilName;
    }

    public String getChairmanName() {
        return chairmanName;
    }

    public void setChairmanName(String chairmanName) {
        this.chairmanName = chairmanName;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setNameOfLZC(String nameOfLZC) {
        this.nameOfLZC = nameOfLZC;
    }

    public String getNameOfLZC() {
        return nameOfLZC;
    }

    public int getColor() {
        return color;
    }

    public void setStatus_icon(int status_icon) {
        this.status_icon = status_icon;
    }

    public int getStatus_icon() {
        return status_icon;
    }
}
