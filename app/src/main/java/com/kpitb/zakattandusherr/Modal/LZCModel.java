package com.kpitb.zakattandusherr.Modal;

public class LZCModel {
    private String tehsilId;
    private String tehsilName;
    private String lzcChairman;
    private String lzcName;
    private String lzcPhone;

    public LZCModel(String tehsilId, String tehsilName, String lzcChairman, String lzcName, String lzcPhone) {
        this.tehsilId = tehsilId;
        this.tehsilName = tehsilName;
        this.lzcChairman = lzcChairman;
        this.lzcName = lzcName;
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

    public String getLzcChairman() {
        return lzcChairman;
    }

    public void setLzcChairman(String lzcChairman) {
        this.lzcChairman = lzcChairman;
    }

    public String getLzcName() {
        return lzcName;
    }

    public void setLzcName(String lzcName) {
        this.lzcName = lzcName;
    }

    public String getLzcPhone() {
        return lzcPhone;
    }

    public void setLzcPhone(String lzcPhone) {
        this.lzcPhone = lzcPhone;
    }
}
