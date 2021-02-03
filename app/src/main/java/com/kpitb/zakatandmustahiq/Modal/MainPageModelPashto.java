package com.kpitb.zakatandmustahiq.Modal;

public class MainPageModelPashto {
    private String requestStatusPashto;
    private int color;
    private int status_icon;

    public MainPageModelPashto(String requestStatusPashto, int color, int status_icon) {
        this.requestStatusPashto = requestStatusPashto;
        this.color = color;
        this.status_icon = status_icon;
    }

    public String getRequestStatusPashto() {
        return requestStatusPashto;
    }

    public void setRequestStatusPashto(String requestStatusPashto) {
        this.requestStatusPashto = requestStatusPashto;
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
