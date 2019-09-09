package com.su.mobiledatausage.service;

import com.su.mobiledatausage.di.DaggerApiComponent;
import com.su.mobiledatausage.model.DataModel;

import javax.inject.Inject;

import retrofit2.Call;

public class DataService {

    public static DataService instance;

    @Inject
    public DataAPI api;

    public DataService() {
        DaggerApiComponent.create().inject(this);
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
