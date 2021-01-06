package com.kpitb.zakattandusherr.Modal;

public class LZCModel {
    private String tehsilId;
    private String tehsilName;
    private String tehsilNameUrd;
    private String tehsilNamePsh;
    private String lzcChairman;
    private String lzcChairmanUrd;
    private String lzcChairmanPsh;
    private String lzcName;
    private String lzcNameUrd;
    private String lzcNamePsh;
    private String lzcPhone;

    public LZCModel(String tehsilId, String tehsilName, String tehsilNameUrd, String tehsilNamePsh, String lzcChairman,
                    String lzcChairmanUrd, String lzcChairmanPsh, String lzcName,
                    String lzcNameUrd, String lzcNamePsh, String lzcPhone) {
        this.tehsilId = tehsilId;
        this.tehsilName = tehsilName;
        this.tehsilNameUrd = tehsilNameUrd;
        this.tehsilNamePsh = tehsilNamePsh;
        this.lzcChairman = lzcChairman;
        this.lzcChairmanUrd = lzcChairmanUrd;
        this.lzcChairmanPsh = lzcChairmanPsh;
        this.lzcName = lzcName;
        this.lzcNameUrd = lzcNameUrd;
        this.lzcNamePsh = lzcNamePsh;
        this.lzcPhone = lzcPhone;
    }

    public String getTehsilId() {
        return tehsilId;
    }

    public void setTehsilId(String tehsilId) {
        this.tehsilId = tehsilId;
    }

    public String getTehsilName() {
        return tehsilName;
    }

    public void setTehsilName(String tehsilName) {
        this.tehsilName = tehsilName;
    }

    public String getTehsilNameUrd() {
        return tehsilNameUrd;
    }

    public void setTehsilNameUrd(String tehsilNameUrd) {
        this.tehsilNameUrd = tehsilNameUrd;
    }

    public String getTehsilNamePsh() {
        return tehsilNamePsh;
    }

    public void setTehsilNamePsh(String tehsilNamePsh) {
        this.tehsilNamePsh = tehsilNamePsh;
    }

    public String getLzcChairman() {
        return lzcChairman;
    }

    public void setLzcChairman(String lzcChairman) {
        this.lzcChairman = lzcChairman;
    }

    public String getLzcChairmanUrd() {
        return lzcChairmanUrd;
    }

    public void setLzcChairmanUrd(String lzcChairmanUrd) {
        this.lzcChairmanUrd = lzcChairmanUrd;
    }

    public String getLzcChairmanPsh() {
        return lzcChairmanPsh;
    }

    public void setLzcChairmanPsh(String lzcChairmanPsh) {
        this.lzcChairmanPsh = lzcChairmanPsh;
    }

    public String getLzcName() {
        return lzcName;
    }

    public void setLzcName(String lzcName) {
        this.lzcName = lzcName;
    }

    public String getLzcNameUrd() {
        return lzcNameUrd;
    }

    public void setLzcNameUrd(String lzcNameUrd) {
        this.lzcNameUrd = lzcNameUrd;
    }

    public String getLzcNamePsh() {
        return lzcNamePsh;
    }

    public void setLzcNamePsh(String lzcNamePsh) {
        this.lzcNamePsh = lzcNamePsh;
    }

    public String getLzcPhone() {
        return lzcPhone;
    }

    public void setLzcPhone(String lzcPhone) {
        this.lzcPhone = lzcPhone;
    }
}
