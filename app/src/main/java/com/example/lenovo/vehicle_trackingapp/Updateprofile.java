package com.example.lenovo.vehicle_trackingapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Updateprofile extends AppCompatActivity {

    EditText name_et , pass_et , mobile_et , email_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateprofile);

        name_et = (EditText) findViewById(R.id.name_et);
        pass_et = (EditText) findViewById(R.id.pass_et);
        mobile_et = (EditText) findViewById(R.id.contact_et);
        email_et = (EditText) findViewById(R.id.email_et);

        name_et.setText(getIntent().getStringExtra("name_key"));
        pass_et.setText(getIntent().getStringExtra("pass"));
        mobile_et.setText(getIntent().getStringExtra("contact_key"));
        email_et.setText(getIntent().getStringExtra("email_key"));

    }

    public void update_profile(View view)
    {

        SharedPreferences sp = getSharedPreferences("admin_info" , MODE_PRIVATE);

        JSONObject job = new JSONObject();

        try {
            job.put("name_key" , name_et.getText().toString());
            job.put("email_key" , email_et.getText().toString());
            job.put("mobile_key" , mobile_et.getText().toString());
            job.put("pass_key" , pass_et.getText().toString());



            job.put("user_id" , sp.getString("admin_id",""));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        System.out.println(job);
        JsonObjectRequest jobreq = new JsonObjectRequest("http://"+ip_adress.ip+"/update_admin_basic_info.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    if(response.getString("key").equals("done"))
                    {
                        Toast.makeText(Updateprofile.this , "updated" , Toast.LENGTH_SHORT).show();


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

        AppController app = new AppController(Updateprofile.this);

        app.addToRequestQueue(jobreq);

    }
}