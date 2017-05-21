package com.example.lenovo.vehicle_trackingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Car_location extends AppCompatActivity {

    Spinner eddt;
    String snum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_location);
        eddt = (Spinner) findViewById(R.id.eddt1);

        get_vehicles();
    }

    public void viewcar(View view) {

        snum = eddt.getSelectedItem().toString();
        Intent i = new Intent(Car_location.this, Show_map.class);
        i.putExtra("number",snum);
        startActivity(i);

    }

    public void get_vehicles() {
        JSONObject job = new JSONObject();

        SharedPreferences sp = getSharedPreferences("admin_info", MODE_PRIVATE);
        String admin_id = sp.getString("admin_id", "");

            try {

                job.put("aid", admin_id);

            } catch (JSONException e) {
                e.printStackTrace();
            }


            System.out.println(job);
            JsonObjectRequest jobreq = new JsonObjectRequest("http://" + ip_adress.ip + "/viewvehicle.php", job, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jarr = response.getJSONArray("key");

                        List<String> list = new ArrayList<>();
                        list.add("select vehicle number");

                        for (int i = 0; i < jarr.length(); i++) {
                            try {
                                JSONObject job = jarr.getJSONObject(i);
                                list.add(job.getString("vehiclenumber"));
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }


                        }

                        ArrayAdapter<String> dataadapter = new ArrayAdapter<String>(Car_location.this, R.layout.spinner_cell, list);
                        eddt.setAdapter(dataadapter);


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
            jobreq.setRetryPolicy(new DefaultRetryPolicy(20000, 3, 2));
            AppController app = new AppController(Car_location.this);
            app.addToRequestQueue(jobreq);


    }
}
