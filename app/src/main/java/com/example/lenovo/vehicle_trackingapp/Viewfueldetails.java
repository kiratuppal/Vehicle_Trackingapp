package com.example.lenovo.vehicle_trackingapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.*;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.lenovo.vehicle_trackingapp.adapter.adapter;
import com.example.lenovo.vehicle_trackingapp.adapter.adapter_fuel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Viewfueldetails extends AppCompatActivity {

    RecyclerView r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewfueldetails);

        System.out.println("aa gey");
        r = (RecyclerView)  findViewById(R.id.recycler2_id);

        get_data();
    }
    public void get_data()
    {

        JSONObject job = new JSONObject();


        try {
           job.put("vehi_key",getIntent().getStringExtra("vehiclenum"));
            job.put("from_key",getIntent().getStringExtra("fromdate"));
            job.put("to_key",getIntent().getStringExtra("todatee"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println(job);
        // making json array request to get json array data
        JsonObjectRequest jsonreq = new JsonObjectRequest("http://"+ip_adress.ip+"/viewfuel.php", job ,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jarr =  response.getJSONArray("key");

                            adapter_fuel ad = new adapter_fuel(jarr , Viewfueldetails.this);

                            r.setLayoutManager(new android.support.v7.widget.LinearLayoutManager(Viewfueldetails.this , android.support.v7.widget.LinearLayoutManager.VERTICAL,false));

                            // setting adapter ad to recycler view r
                            r.setAdapter(ad);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        jsonreq.setRetryPolicy(new DefaultRetryPolicy(20000 , 2 ,2));

        AppController app = new AppController(Viewfueldetails.this);

        app.addToRequestQueue(jsonreq);
    }
}



