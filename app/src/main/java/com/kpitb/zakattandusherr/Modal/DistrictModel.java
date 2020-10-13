package com.kpitb.zakattandusherr.Modal;

public class DistrictModel {
    String d_id,d_name,d_officer_name,
            d_no_lzc,d_officer_phone,d_chairman_name,d_chairman_phone,d_latitude,d_longitude;

    public DistrictModel(String d_id, String d_name, String d_officer_name, String d_no_lzc, String d_officer_phone, String d_chairman_name, String d_chairman_phone, String d_latitude, String d_longitude) {
        this.d_id = d_id;
        this.d_name = d_name;
        this.d_officer_name = d_officer_name;
        this.d_no_lzc = d_no_lzc;
        this.d_officer_phone = d_officer_phone;
        this.d_chairman_name = d_chairman_name;
        this.d_chairman_phone = d_chairman_phone;
        this.d_latitude = d_latitude;
        this.d_longitude = d_longitude;
    }

    public String getD_id() {
        return d_id;
    }

    public void setD_id(String d_id) {
        this.d_id = d_id;
    }

    public String getD_name() {
        return d_name;
    }

    public void setD_name(String d_name) {
        this.d_name = d_name;
    }

    public String getD_officer_name() {
        return d_officer_name;
    }

    public void setD_officer_name(String d_officer_name) {
        this.d_officer_name = d_officer_name;
    }

    public String getD_no_lzc() {
        return d_no_lzc;
    }

    public void setD_no_lzc(String d_no_lzc) {
        this.d_no_lzc = d_no_lzc;
    }

    public String getD_officer_phone() {
        return d_officer_phone;
    }

    public void setD_officer_phone(String d_officer_phone) {
        this.d_officer_phone = d_officer_phone;
    }

    public String getD_chairman_name() {
        return d_chairman_name;
    }

    public void setD_chairman_name(String d_chairman_name) {
        this.d_chairman_name = d_chairman_name;
    }

    public String getD_chairman_phone() {
        return d_chairman_phone;
    }

    public void setD_chairman_phone(String d_chairman_phone) {
        this.d_chairman_phone = d_chairman_phone;
    }

    public String getD_latitude() {
        return d_latitude;
    }

    public void setD_latitude(String d_latitude) {
        this.d_latitude = d_latitude;
    }

    public String getD_longitude() {
        return d_longitude;
    }

    public void setD_longitude(String d_longitude) {
        this.d_longitude = d_longitude;
    }
}
