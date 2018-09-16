package com.example.android.sunshine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.zip.Inflater;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolder>{
    private String[] mWeatherData;

    public ForecastAdapter() {

    }

    @Override
    public ForecastAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.forecast_list_item;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View newLayout = layoutInflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);

        return new ForecastAdapterViewHolder(newLayout);
    }

    @Override
    public void onBindViewHolder(ForecastAdapterViewHolder holder, int position) {
        holder.mWeatherTextView.setText(mWeatherData[position]);
    }

    @Override
    public int getItemCount() {
        if (mWeatherData == null) {
            return 0;
        }

        return mWeatherData.length;
    }

    public void setWeatherData(String[] mWeatherData) {
        this.mWeatherData = mWeatherData;

        notifyDataSetChanged();
    }

    public class ForecastAdapterViewHolder extends RecyclerView.ViewHolder {
       public final TextView mWeatherTextView;


       public ForecastAdapterViewHolder(View itemView) {
           super(itemView);

           mWeatherTextView = (TextView) itemView.findViewById(R.id.tv_weather_data);
       }
   }
}
