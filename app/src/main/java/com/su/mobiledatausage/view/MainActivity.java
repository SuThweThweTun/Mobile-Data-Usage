package com.su.mobiledatausage.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.su.mobiledatausage.R;
import com.su.mobiledatausage.view.adapter.DataListAdapter;
import com.su.mobiledatausage.viewmodel.ListViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    Context context = this;

    @BindView(R.id.dataList)
    RecyclerView dataList;

    @BindView(R.id.list_error)
    TextView listError;

    @BindView(R.id.loading)
    ProgressBar loadingView;

    private ListViewModel viewModel;
    private DataListAdapter adapter = new DataListAdapter(new ArrayList<>());

    View childView;
    int recyclerViewItemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        viewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        viewModel.refresh();

        dataList.setLayoutManager(new LinearLayoutManager(this));
        dataList.setAdapter(adapter);

        dataList.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            GestureDetector gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override public boolean onSingleTapUp(MotionEvent motionEvent) {
                    return true;
                }
            });

            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                childView = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                if(childView != null && gestureDetector.onTouchEvent(motionEvent)) {

                    recyclerViewItemPosition = recyclerView.getChildAdapterPosition(childView);

                    AlertDialog.Builder dialog = new AlertDialog.Builder(context)
                            .setTitle("Info")
                            .setMessage("Mobile data usage was decreased in some quarters of this year.")
                            .setPositiveButton(android.R.string.yes, (dialog1, which) -> dialog1.cancel())
                            .setIcon(android.R.drawable.ic_menu_info_details);

                    dialog.show();
                }

                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

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
