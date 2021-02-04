package com.kpitb.mustahiq.Modal;

/**
 * Created by Manxoor on 30/10/2018.
 */

public class DistrictPageModel {
    private String districtTitle;
    private String noOfLzcs;
    private String dzo_name;
    private int color;
    private int status_icon;
    private String id;


    private String phoneNumber;

    public DistrictPageModel(String districtTitle, String dzo_name, String noOfLzcs,String phoneNumber, int color, int status_icon) {
        this.districtTitle = districtTitle;
        this.noOfLzcs = noOfLzcs;
        this.dzo_name = dzo_name;
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


    public String getDzo_name() {
        return dzo_name;
    }

    public void setDzo_name(String dzo_name) {
        this.dzo_name = dzo_name;
    }

    public String getNoOfLzcs() {
        return noOfLzcs;
    }

    public void setNoOfLzcs(String noOfLzcs) {
        this.noOfLzcs = noOfLzcs;
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

    public void setDistrictTitle(String districtTitle) {
        this.districtTitle = districtTitle;
    }

    public String getDistrictTitle() {
        return districtTitle;
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
