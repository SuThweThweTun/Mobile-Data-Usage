package com.su.mobiledatausage.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataModel implements Serializable {

    @SerializedName("result")
    private JsonObject result;

    private List<DataUsageModel> modelList;


    public JsonObject getRes() {
        return result;
    }

    public List<DataUsageModel> getModelList(JsonObject result) {
        modelList = new ArrayList<>();
        DataUsageModel model;
        JsonObject obj;

        JsonArray records = result.getAsJsonArray("records");
        for (int i = 0; i < records.size(); i++) {
            model = new DataUsageModel();

            obj = records.get(i).getAsJsonObject();

            model.setId(obj.get("_id").getAsInt());
            model.setMobile_data(obj.get("volume_of_mobile_data").getAsString());
            model.setQuarter(obj.get("quarter").getAsString());

            modelList.add(model);
        }


        return modelList;
    }

}
