package com.example.lenovo.vehicle_trackingapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.*;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;;
import com.example.lenovo.vehicle_trackingapp.adapter.vehicle_adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class vehiclemodule extends AppCompatActivity {

    RecyclerView r;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_vehiclemodule);
        r = (RecyclerView) findViewById(R.id.recycler4_id);

        get_data();
    }

    public void get_data() {

        JSONObject job = new JSONObject();

        SharedPreferences sp = getSharedPreferences("admin_info", MODE_PRIVATE);

        try {
            job.put("aid", sp.getString("admin_id", ""));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // making json array request to get json array data
        System.out.println(job);
        JsonObjectRequest jsonreq = new JsonObjectRequest("http://" + ip_adress.ip + "/viewvehicle.php", job,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        System.out.println(response);

                        try {
                            JSONArray jarr = response.getJSONArray("key");

                            vehicle_adapter ad = new vehicle_adapter(jarr, vehiclemodule.this);

                            r.setLayoutManager(new android.support.v7.widget.LinearLayoutManager(vehiclemodule.this, android.support.v7.widget.LinearLayoutManager.VERTICAL, false));

                            // setting adapter ad to recycler view r
                            r.setAdapter(ad);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println(error);
            }
        });


        jsonreq.setRetryPolicy(new DefaultRetryPolicy(20000, 2, 2));

        AppController app = new AppController(vehiclemodule.this);

        app.addToRequestQueue(jsonreq);
    }

    public static void delete_vehicle(String Vid,  final Activity a) {
        {
            JSONObject job = new JSONObject();

            try {
                job.put("vehicle_id", Vid);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JsonObjectRequest jobjreq = new JsonObjectRequest("http://" + ip_adress.ip + "/delete_vehicle.php", job, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    Toast.makeText(a , "deleted successfully", Toast.LENGTH_SHORT).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            jobjreq.setRetryPolicy(new DefaultRetryPolicy(20000 , 2 ,2));

            AppController app = new AppController(a);

            app.addToRequestQueue(jobjreq);
        }
    }
}

