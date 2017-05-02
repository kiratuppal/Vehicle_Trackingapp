package com.example.lenovo.vehicle_trackingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Userlogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlogin);
    }


    public void driver_login (View view) {

        EditText username = (EditText) findViewById(R.id.usenme);
        EditText contact = (EditText) findViewById(R.id.passwrrd);

        String username_s = username.getText().toString();
        String contact_s = contact.getText().toString();


        if (username_s.equals("")) {
            Toast.makeText(Userlogin.this, "please enter username", Toast.LENGTH_SHORT).show();
            return;

        }

        if (contact_s.equals("")) {
            Toast.makeText(Userlogin.this, "please enter contact number", Toast.LENGTH_SHORT).show();
            return;

        }

        JSONObject job = new JSONObject();
        try {
            job.put("user_key", username_s);
            job.put("cont_key", contact_s);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(job);
        JsonObjectRequest jobreq = new JsonObjectRequest("http://"+ip_adress.ip+"/drsignin.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    if(response.getString("key").equals("done"))
                    {

                        SharedPreferences.Editor sp = getSharedPreferences("driver_info" , MODE_PRIVATE).edit();
                        sp.putString("driver_id" , response.getString("id"));
                        sp.putString("v_no" , response.getString("v_no"));
                        sp.commit();
                        Intent i = new Intent(Userlogin.this,drivermenu.class);

                        startActivity(i);
                    }
                    else {
                        Toast.makeText(Userlogin.this, "Incorrect input" , Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        jobreq.setRetryPolicy(new DefaultRetryPolicy(20000 , 3 , 2));
        AppController app = new AppController(Userlogin.this);
        app.addToRequestQueue(jobreq);
    }
}
