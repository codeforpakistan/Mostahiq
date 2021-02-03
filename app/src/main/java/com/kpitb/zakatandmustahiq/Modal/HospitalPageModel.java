package com.kpitb.zakatandmustahiq.Modal;

public class HospitalPageModel {
    private String districtTitle;
    private String noOfLzcs;
    private String dzo_name;
    private int color;
    //private int status_icon;
    private String id;


    private String phoneNumber;

    public HospitalPageModel(String districtTitle, String dzo_name, String noOfLzcs,String phoneNumber, int color) {
        this.districtTitle = districtTitle;
        this.noOfLzcs = noOfLzcs;
        this.dzo_name = dzo_name;
        this.color = color;
        this.phoneNumber = phoneNumber;
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
}
