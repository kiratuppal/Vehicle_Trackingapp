package com.example.lenovo.vehicle_trackingapp.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.vehicle_trackingapp.R;
import com.example.lenovo.vehicle_trackingapp.Viewdriver;
import com.example.lenovo.vehicle_trackingapp.viewholder.view_holder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lenovo on 4/17/2017.
 */

public class adapter extends RecyclerView.Adapter<view_holder>{

    JSONArray jsarr;

    // creating Activity variable globally
    Activity a;

    // making constructer for adapter class which will be
    // called when object of this class is created

    public adapter(JSONArray jsarr , Activity a)
    {
        this.jsarr = jsarr;

        this.a = a;
    }
    @Override
    public view_holder onCreateViewHolder(ViewGroup parent, int viewType) {

        // creating view_holder object and passing cell layout as parameter
        view_holder view = new view_holder(LayoutInflater.from(a).inflate(R.layout.cell,parent,false));

        return view;
    }

    @Override
    public void onBindViewHolder(view_holder holder, int position) {

        try {
            // iterating for each json object in json array
            final JSONObject job = (JSONObject) jsarr.get(position);

            // binding values from json object to cell layout via view holder
            holder.name_id.setText(job.getString("name"));
            holder.contnum_id.setText(job.getString("contact_no"));
            holder.email_id.setText(job.getString("email"));
            holder.age_id.setText(job.getString("age"));
            holder.vehicle_id.setText(job.getString("vehicle_no"));
            holder.admin_id.setText(job.getString("aid"));

            holder.delete_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Viewdriver.delete_driver(job.getString("driver_id"),a);
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
    public int getItemCount()
    {
        // return how many cells will be created i.e length of json array
        return jsarr.length();
    }
}
