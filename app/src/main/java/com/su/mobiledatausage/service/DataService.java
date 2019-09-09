package com.su.mobiledatausage.service;

import com.su.mobiledatausage.model.DataModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataService {

    private static final String BASE_URL = "https://data.gov.sg/api/action/";

    public static DataService instance;

    public DataAPI api = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(DataAPI.class);

    public DataService() {

    }

    public static DataService getInstance() {
        if(instance == null)
            instance = new DataService();

        return instance;
    }

    public Call<DataModel> getData() {
        return api.getData();
    }
}
