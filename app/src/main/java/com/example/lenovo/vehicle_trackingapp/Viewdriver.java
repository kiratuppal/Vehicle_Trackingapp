package com.example.lenovo.vehicle_trackingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;


public class Viewdriver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdriver);
    }
    public void get_data()
    {
        // making json array request to get json array data
        JsonArrayRequest jsonreq = new JsonArrayRequest("http:// ip address / path of php file", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                // making object of adapter class and passing json array and Activity to adapter constructer
                adapter ad = new adapter(response , Viewdriver.this);


                // setting properties for recycler view like how it scroll vertically , horizontally
                r.setLayoutManager(new LinearLayoutManager(Viewdriver.this , LinearLayoutManager.VERTICAL,false));

                // setting adapter ad to recycler view r
                r.setAdapter(ad);

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
}
