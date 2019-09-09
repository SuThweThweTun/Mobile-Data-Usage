package com.su.mobiledatausage.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.su.mobiledatausage.R;
import com.su.mobiledatausage.model.DataModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DataListAdapter extends RecyclerView.Adapter<DataListAdapter.DataViewHolder> {

    private List<DataModel> dataList;

    public DataListAdapter(List<DataModel> dataList) { this.dataList = dataList; }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        holder.bind(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void updateCountries(List<DataModel> newCountries) {
        dataList.clear();
        dataList.addAll(newCountries);
        notifyDataSetChanged();
    }

    class DataViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.quarter)
        TextView quarterTextField;

        @BindView(R.id.data_usage)
        TextView dataUsageTextField;

        @BindView(R.id.imageView)
        ImageView imageView;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(DataModel dataModel) {
            quarterTextField.setText("Total data usage of " + dataModel.getQuarter() + " : ");
            dataUsageTextField.setText(dataModel.getMobile_data());
        }
    }
}
