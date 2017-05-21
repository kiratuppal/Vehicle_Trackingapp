package com.example.lenovo.vehicle_trackingapp;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class driverdoc extends AppCompatActivity {

    ImageView drivedoc ;
    Button verify ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driverdoc);

        drivedoc = (ImageView) findViewById(R.id.docview);

        verify = (Button) findViewById(R.id.verify_save);
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verify_doc();
            }
        });

        get_drivers_doc();


    }

    private void verify_doc() {

        JSONObject job = new JSONObject();


        try {

            job.put("driver_name", getIntent().getStringExtra("driver_name"));

        } catch (JSONException e) {
            e.printStackTrace();
        }


        System.out.println(job);
        JsonObjectRequest jobreq = new JsonObjectRequest("http://" + ip_adress.ip + "/verify_doc.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                verify.setEnabled(false);
                verify.setText("verified");


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println(error);

            }
        });
        jobreq.setRetryPolicy(new DefaultRetryPolicy(20000, 3, 2));
        AppController app = new AppController(driverdoc.this);
        app.addToRequestQueue(jobreq);
    }

    public void get_drivers_doc() {
        JSONObject job = new JSONObject();


        try {

            job.put("driver_name", getIntent().getStringExtra("driver_name"));

        } catch (JSONException e) {
            e.printStackTrace();
        }


        System.out.println(job);
        JsonObjectRequest jobreq = new JsonObjectRequest("http://" + ip_adress.ip + "/get_doc.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    if(response.getJSONObject("result").getString("document").equals("null"))
                    {
                        Toast.makeText(driverdoc.this ,"no document found for this driver" , Toast.LENGTH_SHORT).show();
                    }
                   drivedoc.setImageBitmap( StringToBitMap(response.getJSONObject("result").getString("document")));


                    if(response.getJSONObject("result").getString("status").equals("1"))
                    {
                        verify.setEnabled(false);
                        verify.setText("verified");
                    }

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
        AppController app = new AppController(driverdoc.this);
        app.addToRequestQueue(jobreq);
    }

    // function to convert string image into bitmap image
    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte=Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }
}
