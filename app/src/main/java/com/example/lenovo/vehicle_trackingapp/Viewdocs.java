package com.example.lenovo.vehicle_trackingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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

public class Viewdocs extends AppCompatActivity {

    Spinner drivername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdocs);



        drivername = (Spinner)findViewById(R.id.drivernamespinner);
        get_drivers();
    }

    public void get_drivers() {
        JSONObject job = new JSONObject();

        SharedPreferences sp = getSharedPreferences("admin_info", MODE_PRIVATE);
        String admin_id = sp.getString("admin_id", "");

        try {

            job.put("aid", admin_id);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        System.out.println(job);
        JsonObjectRequest jobreq = new JsonObjectRequest("http://" + ip_adress.ip + "/viewname.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jarr = response.getJSONArray("key");

                    List<String> list = new ArrayList<>();
                    list.add("select Driver");

                    for (int i = 0; i < jarr.length(); i++) {
                        try {
                            JSONObject job = jarr.getJSONObject(i);
                            list.add(job.getString("name"));
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }


                    }

                    ArrayAdapter<String> dataadapter = new ArrayAdapter<String>(Viewdocs.this, R.layout.spinner_cell, list);
                    drivername.setAdapter(dataadapter);


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
        AppController app = new AppController(Viewdocs.this);
        app.addToRequestQueue(jobreq);
    }

    public void see_document(View view) {

        Intent i = new Intent(Viewdocs.this, driverdoc.class);

        i.putExtra("driver_name" , drivername.getSelectedItem().toString());

        startActivity(i);
    }
}
