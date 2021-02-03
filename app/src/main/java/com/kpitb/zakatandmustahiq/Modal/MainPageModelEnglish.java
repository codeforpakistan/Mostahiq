package com.kpitb.zakatandmustahiq.Modal;

public class MainPageModelEnglish {
    private String requestStatusEng;
    private int color;
    private int status_icon;

    public MainPageModelEnglish(String requestStatusEng, int color, int status_icon) {
        this.requestStatusEng = requestStatusEng;
        this.color = color;
        this.status_icon = status_icon;
    }

    public String getRequestStatusEng() {
        return requestStatusEng;
    }

    public void setRequestStatusEng(String requestStatusEng) {
        this.requestStatusEng = requestStatusEng;
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
