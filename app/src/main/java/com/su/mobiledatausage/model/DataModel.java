package com.su.mobiledatausage.model;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

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

        List<DataUsageModel> tempModelList;
        Map<String, List<DataUsageModel>> map = new HashMap<>();
        int id;
        String mobile_data;
        String quarter;
        String year;
        StringTokenizer tokens;

        JsonArray records = result.getAsJsonArray("records");
        for (int i = 0; i < records.size(); i++) {
            model = new DataUsageModel();

            obj = records.get(i).getAsJsonObject();
            id = obj.get("_id").getAsInt();
            mobile_data = obj.get("volume_of_mobile_data").getAsString();
            quarter = obj.get("quarter").getAsString();

            model.setId(id);
            model.setMobile_data(mobile_data);
            model.setQuarter(quarter);

//            modelList.add(model);

            //get Year
            tokens = new StringTokenizer(quarter, "-");
            year = tokens.nextToken();

            //collect map values by each year
            if(map.containsKey(year)) {
                tempModelList = map.get(year);
            }
            else {
                tempModelList = new ArrayList<>();
            }

            tempModelList.add(model);
            map.put(year, tempModelList);
        }

        DataUsageModel model1;
        int key;
        for (int i = 0; i < map.size(); i++) {
            key = i + 2008;
            model1 = calculateTotalDataUsage(key, map.get(String.valueOf(key)));
            modelList.add(model1);
        }

        Log.e("before", "return list");
        return modelList;
    }

    private DataUsageModel calculateTotalDataUsage(int year, List<DataUsageModel> dataUsageModels) {
        double volume = 0.0;
        Log.e("Year", String.valueOf(year));

        for (int i = 0; i < dataUsageModels.size(); i++) {
            Log.e("volume", String.valueOf(volume));
            volume = volume + Double.valueOf(dataUsageModels.get(i).getMobile_data());
        }

        DataUsageModel model = new DataUsageModel();
        model.setQuarter(String.valueOf(year));
        model.setMobile_data(String.valueOf(volume));

        return model;
    }

    /*public List<DataUsageModel> getModelList(JsonObject result) {
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
    }*/

}
