package com.example.lenovo.vehicle_trackingapp.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.lenovo.vehicle_trackingapp.R;
import com.example.lenovo.vehicle_trackingapp.viewholder.viewholder_service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lenovo on 4/27/2017.
 */

public class adapter_service extends RecyclerView.Adapter<viewholder_service> {

    JSONArray jsarr;

    // creating Activity variable globally
    Activity a;

    // making constructer for adapter class which will be
    // called when object of this class is created

    public adapter_service(JSONArray jsarr , Activity a)
    {
        this.jsarr = jsarr;

        this.a = a;
    }

    @Override
    public viewholder_service onCreateViewHolder(ViewGroup parent, int viewType) {
        viewholder_service view = new viewholder_service(LayoutInflater.from(a).inflate(R.layout.servicecell,parent,false));
        return view;
    }

    @Override
    public void onBindViewHolder(viewholder_service holder, int position) {
        try {
            // iterating for each json object in json array
            JSONObject job = (JSONObject) jsarr.get(position);

            // binding values from json object to cell layout via view holder
            holder.car_id.setText(job.getString("vehicle_no"));
            holder.bill_id.setText(job.getString("bill_no"));
            holder.amt_id.setText(job.getString("amount"));
            holder.ser_id.setText(job.getString("service"));
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
