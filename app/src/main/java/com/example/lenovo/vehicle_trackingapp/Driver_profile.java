package com.example.lenovo.vehicle_trackingapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Driver_profile extends AppCompatActivity {

    EditText name_et , mobile_et , age_et  , vehicle_assigned ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_profile);

        name_et = (EditText) findViewById(R.id.user_name_et);
        mobile_et = (EditText) findViewById(R.id.mobile_et);
        age_et = (EditText) findViewById(R.id.age_et);
        vehicle_assigned = (EditText) findViewById(R.id.vehicle_et);

        get_profile();

    }

    private void get_profile()
    {

        final JSONObject job = new JSONObject();
        SharedPreferences sp = getSharedPreferences("driver_info" , Context.MODE_PRIVATE);

        try {
            job.put("user_id" , sp.getString("driver_id", ""));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jobreq = new JsonObjectRequest("http://"+ip_adress.ip+"/get_basic_info.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONObject jobj = response.getJSONObject("result");


                    mobile_et.setText(jobj.getString("contact_no"));
                    name_et.setText(jobj.getString("name"));
                    age_et.setText(jobj.getString("age"));
                    vehicle_assigned.setText(jobj.getString("vehicle_no"));





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

        AppController app = new AppController(Driver_profile.this);

        app.addToRequestQueue(jobreq);
    }

    public void update_profile(View view)
    {

        SharedPreferences sp = getSharedPreferences("driver_info" , MODE_PRIVATE);

        JSONObject job = new JSONObject();

        try {
            job.put("name_key" , name_et.getText().toString());
            job.put("age_key" , age_et.getText().toString());
            job.put("mobile_key" , mobile_et.getText().toString());
            job.put("car_key" , vehicle_assigned.getText().toString());



            job.put("user_id" , sp.getString("driver_id",""));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        System.out.println(job);
        JsonObjectRequest jobreq = new JsonObjectRequest("http://"+ip_adress.ip+"/update_basic_info.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    if(response.getString("key").equals("done"))
                    {
                        Toast.makeText(Driver_profile.this , "updated" , Toast.LENGTH_SHORT).show();

                        get_profile();
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

        AppController app = new AppController(Driver_profile.this);

        app.addToRequestQueue(jobreq);

    }
}
