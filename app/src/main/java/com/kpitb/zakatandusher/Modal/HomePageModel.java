package com.kpitb.zakatandusher.Modal;

/**
 * Created by Manxoor on 30/10/2018.
 */

public class HomePageModel {
    private String requestStatus;
    private int color;
    private int status_icon;
    private String id;

    public HomePageModel(String s, int greenA700, int ic_others_icon, String i){
        this.requestStatus = s;
        this.color = greenA700;
        this.status_icon = ic_others_icon;
        this.id = i;
    }

    public HomePageModel(String requestStatus, int color, int status_icon) {
        this.requestStatus = requestStatus;
        this.color = color;
        this.status_icon = status_icon;
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

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getRequestStatus() {
        return requestStatus;
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
