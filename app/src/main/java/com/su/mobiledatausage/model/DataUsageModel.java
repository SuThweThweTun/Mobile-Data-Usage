package com.su.mobiledatausage.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataUsageModel implements Serializable {

    @SerializedName("_id")
    private int id;

    @SerializedName("volume_of_mobile_data")
    private String mobile_data;

    @SerializedName("quarter")
    private String quarter;

    private boolean image;

    public DataUsageModel() {
    }

    public DataUsageModel(String mobile_data, String quarter, boolean image) {
        this.mobile_data = mobile_data;
        this.quarter = quarter;
        this.image = image;
    }

    public String getMobile_data() {
        return mobile_data;
    }

    public String getQuarter() {
        return quarter;
    }

    public boolean getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMobile_data(String mobile_data) {
        this.mobile_data = mobile_data;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public void setImage(boolean image) {
        this.image = image;
    }
}
