package com.example.lenovo.vehicle_trackingapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.*;
import android.support.v7.widget.LinearLayoutManager;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.lenovo.vehicle_trackingapp.adapter.adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Viewdriver extends AppCompatActivity
{
RecyclerView r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdriver);

        r = (RecyclerView)  findViewById(R.id.recycler_id);

        get_data();
    }
    public void get_data()
    {

        JSONObject job = new JSONObject();

        SharedPreferences sp = getSharedPreferences("admin_info" , MODE_PRIVATE);

        try {
            job.put("aid",sp.getString("admin_id" , ""));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // making json array request to get json array data
        JsonObjectRequest jsonreq = new JsonObjectRequest("http://"+ip_adress.ip+"/viewdriver.php", job ,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jarr =  response.getJSONArray("key");

                    adapter ad = new adapter(jarr , Viewdriver.this);

                    r.setLayoutManager(new LinearLayoutManager(Viewdriver.this , LinearLayoutManager.VERTICAL,false));

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

        AppController app = new AppController(Viewdriver.this);

        app.addToRequestQueue(jsonreq);
    }
}

