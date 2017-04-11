package com.example.lenovo.vehicle_trackingapp;

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

public class registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }
    public void save_to_drivers (View v) {
        EditText name = (EditText) findViewById(R.id.entname);
        EditText contact = (EditText) findViewById(R.id.editcntc);
        EditText email = (EditText) findViewById(R.id.entemail);
        EditText age = (EditText) findViewById(R.id.urage);
        EditText vehicle = (EditText) findViewById(R.id.vehinum);

        String name_s = name.getText().toString();
        String contact_s = contact.getText().toString();
        String email_s = email.getText().toString();
        String age_s = age.getText().toString();
        String vehicle_s = vehicle.getText().toString();

        if (name_s.equals("")) {
            Toast.makeText(registration.this, "please enter name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (contact_s.equals("")) {
            Toast.makeText(registration.this, "please enter contact number", Toast.LENGTH_SHORT).show();
            return;
        }
        if (email_s.equals("")) {
            Toast.makeText(registration.this, "please enter email", Toast.LENGTH_SHORT).show();
            return;

        }
        if (age_s.equals("")) {
            Toast.makeText(registration.this, "please enter your age", Toast.LENGTH_SHORT).show();
            return;
        }
        if (vehicle_s.equals("")) {
            Toast.makeText(registration.this, "please enter vehicle number", Toast.LENGTH_SHORT).show();
            return;
        }

        JSONObject job = new JSONObject();
        {
            try {
                job.put("name_key" , name_s);
                job.put("cont_key" , contact_s);
                job.put("email_key" , email_s);
                job.put("age_key" , age_s);
                job.put("veh_key" , vehicle_s);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            System.out.println(job);
            JsonObjectRequest jobreq = new JsonObjectRequest("http://"+ip_adress.ip+"/drsignup.php", job, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if (response.getString("key").equals("1")) {
                            Toast.makeText(registration.this, "done", Toast.LENGTH_SHORT).show();
                        }
                        if (response.getString("key").equals("0")) {
                            Toast.makeText(registration.this, "This email already exists", Toast.LENGTH_SHORT).show();
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
            jobreq.setRetryPolicy(new DefaultRetryPolicy(20000 , 3 , 2));
            AppController app = new AppController(registration.this);
            app.addToRequestQueue(jobreq);

        }
        }

    }