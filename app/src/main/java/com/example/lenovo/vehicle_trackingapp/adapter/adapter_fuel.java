package com.example.lenovo.vehicle_trackingapp.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.vehicle_trackingapp.R;
import com.example.lenovo.vehicle_trackingapp.viewholder.view_holder;
import com.example.lenovo.vehicle_trackingapp.viewholder.viewholder_fuel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lenovo on 4/24/2017.
 */

public class adapter_fuel extends RecyclerView.Adapter<viewholder_fuel> {

    JSONArray jsarr;

    // creating Activity variable globally
    Activity a;

    // making constructer for adapter class which will be
    // called when object of this class is created

    public adapter_fuel(JSONArray jsarr , Activity a)
    {
        this.jsarr = jsarr;

        this.a = a;
    }

    @Override
    public viewholder_fuel onCreateViewHolder(ViewGroup parent, int viewType) {
        viewholder_fuel view = new viewholder_fuel(LayoutInflater.from(a).inflate(R.layout.fuelcell,parent,false));

        return view;

    }

    @Override
    public void onBindViewHolder(viewholder_fuel holder, int position) {
        try {
            // iterating for each json object in json array
            JSONObject job = (JSONObject) jsarr.get(position);

            // binding values from json object to cell layout via view holder
            holder.car_id.setText(job.getString("vehicle_no"));
            holder.bill_id.setText(job.getString("bill_no"));
            holder.vol_id.setText(job.getString("volume"));
            holder.amt_id.setText(job.getString("amount"));
            holder.date_id.setText(job.getString("date"));

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return jsarr.length();
    }
}
