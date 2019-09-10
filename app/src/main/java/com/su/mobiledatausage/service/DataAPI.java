package com.su.mobiledatausage.service;

import com.su.mobiledatausage.model.DataModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataAPI {

    @GET("datastore_search?resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f&limit=44&offset=14")
    Call<DataModel> getData();
//    Single<List<DataModel>> getData();
}
