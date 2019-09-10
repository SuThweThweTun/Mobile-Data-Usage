package com.su.mobiledatausage.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.su.mobiledatausage.R;
import com.su.mobiledatausage.view.adapter.DataListAdapter;
import com.su.mobiledatausage.viewmodel.ListViewModel;

import java.util.ArrayList;
import java.util.Observable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.dataList)
    RecyclerView dataList;

    @BindView(R.id.list_error)
    TextView listError;

    @BindView(R.id.loading)
    ProgressBar loadingView;

    private ListViewModel viewModel;
    private DataListAdapter adapter = new DataListAdapter(new ArrayList<>());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        viewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        viewModel.refresh();

        dataList.setLayoutManager(new LinearLayoutManager(this));
        dataList.setAdapter(adapter);

        observerViewModel();
    }

    private void observerViewModel() {
        viewModel.dataList.observe(this, dataModels -> {
            if(dataModels != null) {
                dataList.setVisibility(View.VISIBLE);
                adapter.updateData(dataModels);
            }
        });
        viewModel.dataLoadError.observe(this, isError -> {
            if(isError != null) {
                listError.setVisibility(isError ? View.VISIBLE : View.INVISIBLE);
            }
        });

        viewModel.loading.observe(this, isLoading -> {
            if(isLoading != null) {
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.INVISIBLE);
                if(isLoading) {
                    dataList.setVisibility(View.INVISIBLE);
                    listError.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
