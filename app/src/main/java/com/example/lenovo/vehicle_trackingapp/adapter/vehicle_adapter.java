package com.example.lenovo.vehicle_trackingapp.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.vehicle_trackingapp.R;
import com.example.lenovo.vehicle_trackingapp.vehiclemodule;
import com.example.lenovo.vehicle_trackingapp.viewholder.viewholder_service;
import com.example.lenovo.vehicle_trackingapp.viewholder.viewholder_vehicle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lenovo on 5/9/2017.
 */

public class vehicle_adapter extends RecyclerView.Adapter<viewholder_vehicle> {

    JSONArray jsarr;

    // creating Activity variable globally
    Activity a;

    // making constructer for adapter class which will be
    // called when object of this class is created

    public vehicle_adapter(JSONArray jsarr , Activity a)
    {
        this.jsarr = jsarr;

        this.a = a;
    }

    @Override
    public viewholder_vehicle onCreateViewHolder(ViewGroup parent, int viewType) {
        viewholder_vehicle view = new viewholder_vehicle(LayoutInflater.from(a).inflate(R.layout.vehiclecell,parent,false));
        return view;
    }


    @Override
    public void onBindViewHolder(viewholder_vehicle holder, int position) {
        try {
            // iterating for each json object in json array
            final JSONObject job = (JSONObject) jsarr.get(position);

            // binding values from json object to cell layout via view holder
            holder.Name.setText(job.getString("name"));
            holder.company.setText(job.getString("company"));
            holder.color.setText(job.getString("color"));
            holder.mnyear.setText(job.getString("makeinyear"));
            holder.regdnum.setText(job.getString("vehiclenumber"));
            holder.type.setText(job.getString("vehicletype"));
            holder.fuel.setText(job.getString("fueltype"));

            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        vehiclemodule.delete_vehicle(job.getString("Vid"),a);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return jsarr.length();
    }
}
