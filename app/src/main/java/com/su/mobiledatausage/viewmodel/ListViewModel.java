package com.su.mobiledatausage.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.su.mobiledatausage.model.DataModel;

import java.util.ArrayList;
import java.util.List;

public class ListViewModel extends ViewModel {

    public MutableLiveData<List<DataModel>> dataList = new MutableLiveData<>();
    public MutableLiveData<Boolean> dataLoadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    public ListViewModel() {
        super();
    }

    public void refresh() {
        fetchData();
    }

    private void fetchData() {

        DataModel model1 = new DataModel(1, "0.000384", "2004-Q1");
        DataModel model2 = new DataModel(2, "0.0002", "2004-Q2");
        DataModel model3 = new DataModel(3, "0.0003", "2004-Q3");
        DataModel model4 = new DataModel(4, "0.0004", "2004-Q4");

        List<DataModel> list = new ArrayList<>();
        list.add(model1);
        list.add(model2);
        list.add(model3);
        list.add(model4);

        dataList.setValue(list);
        dataLoadError.setValue(false);
        loading.setValue(false);
    }
}
