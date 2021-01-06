package com.kpitb.zakattandusherr.Modal;

public class SourceList {

    String hos_name,hos_name_urdu,hos_name_pashto,hos_focal_person,hos_focal_person_urdu,hos_focal_person_pashto
            ,focal_person_phone,
            hos_district_id,hos_district_name,hos_district_latitude,hos_district_longitude;

    public SourceList(String hos_name, String hos_name_urdu, String hos_name_pashto, String hos_focal_person,
                      String hos_focal_person_urdu, String hos_focal_person_pashto, String focal_person_phone,
                      String hos_district_id, String hos_district_name, String hos_district_latitude,
                      String hos_district_longitude) {
        this.hos_name = hos_name;
        this.hos_name_urdu = hos_name_urdu;
        this.hos_name_pashto = hos_name_pashto;
        this.hos_focal_person = hos_focal_person;
        this.hos_focal_person_urdu = hos_focal_person_urdu;
        this.hos_focal_person_pashto = hos_focal_person_pashto;
        this.focal_person_phone = focal_person_phone;
        this.hos_district_id = hos_district_id;
        this.hos_district_name = hos_district_name;
        this.hos_district_latitude = hos_district_latitude;
        this.hos_district_longitude = hos_district_longitude;
    }

    public String getHos_name() {
        return hos_name;
    }

    public void setHos_name(String hos_name) {
        this.hos_name = hos_name;
    }

    public String getHos_name_urdu() {
        return hos_name_urdu;
    }

    public void setHos_name_urdu(String hos_name_urdu) {
        this.hos_name_urdu = hos_name_urdu;
    }

    public String getHos_name_pashto() {
        return hos_name_pashto;
    }

    public void setHos_name_pashto(String hos_name_pashto) {
        this.hos_name_pashto = hos_name_pashto;
    }

    public String getHos_focal_person() {
        return hos_focal_person;
    }

    public void setHos_focal_person(String hos_focal_person) {
        this.hos_focal_person = hos_focal_person;
    }

    public String getHos_focal_person_urdu() {
        return hos_focal_person_urdu;
    }

    public void setHos_focal_person_urdu(String hos_focal_person_urdu) {
        this.hos_focal_person_urdu = hos_focal_person_urdu;
    }

    public String getHos_focal_person_pashto() {
        return hos_focal_person_pashto;
    }

    public void setHos_focal_person_pashto(String hos_focal_person_pashto) {
        this.hos_focal_person_pashto = hos_focal_person_pashto;
    }

    public String getFocal_person_phone() {
        return focal_person_phone;
    }

    public void setFocal_person_phone(String focal_person_phone) {
        this.focal_person_phone = focal_person_phone;
    }

    public String getHos_district_id() {
        return hos_district_id;
    }

    public void setHos_district_id(String hos_district_id) {
        this.hos_district_id = hos_district_id;
    }

    public String getHos_district_name() {
        return hos_district_name;
    }

    public void setHos_district_name(String hos_district_name) {
        this.hos_district_name = hos_district_name;
    }

    public String getHos_district_latitude() {
        return hos_district_latitude;
    }

    public void setHos_district_latitude(String hos_district_latitude) {
        this.hos_district_latitude = hos_district_latitude;
    }

    public String getHos_district_longitude() {
        return hos_district_longitude;
    }

    public void setHos_district_longitude(String hos_district_longitude) {
        this.hos_district_longitude = hos_district_longitude;
    }
}
