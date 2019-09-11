package com.su.mobiledatausage.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.JsonObject;
import com.su.mobiledatausage.di.DaggerApiComponent;
import com.su.mobiledatausage.model.DataModel;
import com.su.mobiledatausage.model.DataUsageModel;
import com.su.mobiledatausage.service.DataService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListViewModel extends ViewModel {

    public MutableLiveData<List<DataUsageModel>> dataList = new MutableLiveData<>();
    public MutableLiveData<Boolean> dataLoadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    @Inject
    public DataService service;

    private CompositeDisposable disposable = new CompositeDisposable();

    public ListViewModel() {
        super();
        DaggerApiComponent.create().inject(this);
    }

    public void refresh() {
        fetchData();
    }

    private void fetchData() {

        Call<DataModel> call = service.getData();

        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                if(response.code() == 200) {
                    DataModel data = response.body();

                    JsonObject result = data.getRes();
                    List<DataUsageModel> dataUsageList = data.getModelList(result);
                    dataList.setValue(dataUsageList);
                    dataLoadError.setValue(false);
                    loading.setValue(false);
                }
                else{
                    dataLoadError.setValue(true);
                    loading.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                dataLoadError.setValue(true);
                loading.setValue(false);
                t.printStackTrace();
            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
