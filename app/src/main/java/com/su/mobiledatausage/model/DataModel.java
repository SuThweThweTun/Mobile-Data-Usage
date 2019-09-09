package com.su.mobiledatausage.model;

public class DataModel {

    private int id;

    private String mobile_data;

    private String quarter;

    private boolean image;

    public DataModel(int id, String mobile_data, String quarter) {
        this.id = id;
        this.mobile_data = mobile_data;
        this.quarter = quarter;
    }

    public int getId() {
        return id;
    }

    public String getMobile_data() {
        return mobile_data;
    }

    public String getQuarter() {
        return quarter;
    }

    public boolean isImage() {
        return image;
    }
}
