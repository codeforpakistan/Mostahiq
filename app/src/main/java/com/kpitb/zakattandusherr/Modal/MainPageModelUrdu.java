package com.kpitb.zakattandusherr.Modal;

public class MainPageModelUrdu {
    private String requestStatusUrdu;
    private int color;
    private int status_icon;

    public MainPageModelUrdu(String requestStatusUrdu, int color, int status_icon) {
        this.requestStatusUrdu = requestStatusUrdu;
        this.color = color;
        this.status_icon = status_icon;
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
