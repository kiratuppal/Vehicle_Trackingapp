package com.example.lenovo.vehicle_trackingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class AdminSignup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_signup);
    }


    public void save_details(View v)
    {
        EditText email = (EditText) findViewById(R.id.email_et);
        EditText name = (EditText) findViewById(R.id.name_et);
        EditText password = (EditText) findViewById(R.id.password_et);
        EditText contact = (EditText)  findViewById(R.id.contact_et);

        String email_s = email.getText().toString();
        String name_s = name.getText().toString();
        String password_s = password.getText().toString();
        String contact_s = contact.getText().toString();
        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&*+=?-]).{8,15}$";

        if(!Patterns.EMAIL_ADDRESS.matcher(email_s).matches() || email_s.contains("_"))
        {
            Toast.makeText(AdminSignup.this , "please enter valid email type" , Toast.LENGTH_SHORT).show();
            return;

        }

        if(name.length() < 4 || !name_s.matches("[a-zA-Z ]+"))
        {
            Toast.makeText(AdminSignup.this , "name must be 4 character long and not contain any digits" , Toast.LENGTH_SHORT).show();
            return;

        }

        if(!password_s.matches(pattern) || password_s.length() < 8)
        {
            Toast.makeText(AdminSignup.this ,  "password must contain atleast one alphabet , digit , special character and length must be 8 character" , Toast.LENGTH_SHORT).show();
            return;

        }
        if(contact_s.length() < 10)
        {
            Toast.makeText(AdminSignup.this ,  "contact must be 10 digit" , Toast.LENGTH_SHORT).show();
            return;

        }

        JSONObject job = new JSONObject();

        try {
            job.put("name_key" , name_s);
            job.put("email_key" , email_s);
            job.put("pass_key",password_s);
            job.put("cont_key",contact_s);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println(job);
        JsonObjectRequest jobreq = new JsonObjectRequest("http://"+ip_adress.ip+"/signup.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {

                try {
                    if(response.getString("key").equals("1"))
                    {
                        Toast.makeText(AdminSignup.this , "Registered Successfully" , Toast.LENGTH_SHORT).show();
                    }

                    if(response.getString("key").equals("0"))
                    {
                        Toast.makeText(AdminSignup.this , "email already exists" , Toast.LENGTH_SHORT).show();

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
        AppController app = new AppController(AdminSignup.this);
        app.addToRequestQueue(jobreq);


    }
}
