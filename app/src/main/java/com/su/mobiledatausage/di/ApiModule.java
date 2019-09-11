package com.su.mobiledatausage.di;

import com.su.mobiledatausage.service.DataAPI;
import com.su.mobiledatausage.service.DataService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    private static final String BASE_URL = "https://data.gov.sg/api/action/";

    @Provides
    public DataAPI provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(DataAPI.class);
    }

    @Provides
    public DataService provideDataService() { return new DataService().getInstance(); }
}
