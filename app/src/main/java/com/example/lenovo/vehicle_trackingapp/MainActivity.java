package com.example.lenovo.vehicle_trackingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        SharedPreferences sp = getSharedPreferences("admin_info" , MODE_PRIVATE);
        if(!sp.getString("admin_id","").equals(""))
        {
            Intent i = new Intent(MainActivity.this, admin_menu.class);
            startActivity(i);
            finish();

        }


    }

    public void goto_signup(View view) {

        Intent i = new Intent(MainActivity.this, AdminSignup.class);

        startActivity(i);

    }

    public void forgot(View view) {

        Intent i = new Intent(MainActivity.this, forgotpassword.class);

        startActivity(i);
    }


    public void goto_signin(View view) {

        EditText email = (EditText) findViewById(R.id.user1);
        EditText password = (EditText) findViewById(R.id.passwrd);

        String email_s = email.getText().toString();
        String password_s = password.getText().toString();


        if (email_s.equals("")) {
            Toast.makeText(MainActivity.this, "please enter email", Toast.LENGTH_SHORT).show();
            return;

        }

        if (password_s.equals("")) {
            Toast.makeText(MainActivity.this, "please enter password", Toast.LENGTH_SHORT).show();
            return;

        }

        JSONObject job = new JSONObject();
        try {
            job.put("email_key", email_s);
            job.put("password_key", password_s);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(job);
        JsonObjectRequest jobreq = new JsonObjectRequest("http://"+ip_adress.ip+"/signin.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    if(response.getString("key").equals("done"))
                    {
                        SharedPreferences.Editor sp = getSharedPreferences("admin_info" , MODE_PRIVATE).edit();
                        sp.putString("admin_id" , response.getString("id"));
                        sp.commit();
                        Toast.makeText(MainActivity.this ,response.getString("id") , Toast.LENGTH_SHORT ).show();

                        Intent i = new Intent(MainActivity.this,admin_menu.class);

                        startActivity(i);
                    }
                    else {
                         Toast.makeText(MainActivity.this, "Login unsuccessful, please try again" , Toast.LENGTH_SHORT).show();
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
        AppController app = new AppController(MainActivity.this);
        app.addToRequestQueue(jobreq);
    }
}

