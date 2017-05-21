package com.example.lenovo.vehicle_trackingapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.lenovo.vehicle_trackingapp.adapter.vehicle_adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class registration extends AppCompatActivity {

    Spinner vehinum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        vehinum = (Spinner) findViewById(R.id.vehinum);

        get_vehicles();
    }

    public void save_to_drivers(View v) {
        EditText name = (EditText) findViewById(R.id.entname);
        EditText contact = (EditText) findViewById(R.id.editcntc);
        EditText email = (EditText) findViewById(R.id.entemail);
        EditText age = (EditText) findViewById(R.id.urage);


        String name_s = name.getText().toString();
        String contact_s = contact.getText().toString();
        String email_s = email.getText().toString();
        String age_s = age.getText().toString();
        String vehicle_s = vehinum.getSelectedItem().toString();
        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&*+=?-]).{8,15}$";

        if(name.length() < 2 )
        {
            Toast.makeText(registration.this , "name must be 4 character long " , Toast.LENGTH_SHORT).show();
            return;

        }
        if(contact_s.length() < 10)
        {
            Toast.makeText(registration.this ,  "contact must be 10 digit" , Toast.LENGTH_SHORT).show();
            return;

        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email_s).matches() || email_s.contains("_"))
        {
            Toast.makeText(registration.this , "please enter valid email type" , Toast.LENGTH_SHORT).show();
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

        if (vehinum.getSelectedItem().toString().equals("select vehicle number")) {
            Toast.makeText(registration.this, "please select vehicle number", Toast.LENGTH_SHORT).show();
            return;
        }

        JSONObject job = new JSONObject();

        SharedPreferences sp = getSharedPreferences("admin_info", MODE_PRIVATE);
        String admin_id = sp.getString("admin_id", "");
        {
            try {
                job.put("name_key", name_s);
                job.put("cont_key", contact_s);
                job.put("email_key", email_s);
                job.put("age_key", age_s);
                job.put("veh_key", vehicle_s);
                job.put("admin_key", admin_id);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            System.out.println(job);
            JsonObjectRequest jobreq = new JsonObjectRequest("http://" + ip_adress.ip + "/drsignup.php", job, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if (response.getString("key").equals("1")) {
                            Toast.makeText(registration.this, "Driver Added Successfully", Toast.LENGTH_SHORT).show();
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
            jobreq.setRetryPolicy(new DefaultRetryPolicy(20000, 3, 2));
            AppController app = new AppController(registration.this);
            app.addToRequestQueue(jobreq);

        }
    }

    public void get_vehicles() {
        JSONObject job = new JSONObject();

        SharedPreferences sp = getSharedPreferences("admin_info", MODE_PRIVATE);
        String admin_id = sp.getString("admin_id", "");
        {
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

                        ArrayAdapter<String> dataadapter = new ArrayAdapter<String>(registration.this, R.layout.spinner_cell, list);

                        vehinum.setAdapter(dataadapter);


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
            AppController app = new AppController(registration.this);
            app.addToRequestQueue(jobreq);
        }

    }
}