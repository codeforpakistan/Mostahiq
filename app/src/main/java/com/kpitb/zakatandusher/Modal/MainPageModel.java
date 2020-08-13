package com.kpitb.zakatandusher.Modal;

public class MainPageModel {
    private String requestStatus;
    private String requestStatusUrdu;
    private int color;
    private int status_icon;

    public MainPageModel(String requestStatus, String requestStatusUrdu, int color, int status_icon) {
        this.requestStatus = requestStatus;
        this.requestStatusUrdu = requestStatusUrdu;
        this.color = color;
        this.status_icon = status_icon;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getRequestStatusUrdu() {
        return requestStatusUrdu;
    }

    public void setRequestStatusUrdu(String requestStatusUrdu) {
        this.requestStatusUrdu = requestStatusUrdu;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getStatus_icon() {
        return status_icon;
    }

    public void setStatus_icon(int status_icon) {
        this.status_icon = status_icon;
    }
}
