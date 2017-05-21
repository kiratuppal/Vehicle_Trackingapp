package com.example.lenovo.vehicle_trackingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class resetpassword extends AppCompatActivity {


    EditText new_password_et;
    EditText new_passwords_et;
    Button add;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);

        email = getIntent().getStringExtra("email_et");

        new_password_et =(EditText)findViewById(R.id.newpwd);

        new_passwords_et =(EditText)findViewById(R.id.setpwd);
    }

    public void adds(View v){

        String new_password =  new_password_et.getText().toString();

        String new_passwords =  new_passwords_et.getText().toString();

        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&*+=?-]).{8,15}$";




        if(!new_password.matches(pattern) || new_password.length() < 8)
        {
            Toast.makeText(resetpassword.this, "password must contain atleast one alphabet , digit , special character and length must be 8 character", Toast.LENGTH_SHORT).show();
            return;
        }
        if ( ! new_passwords.equals(new_password))
        {

            Toast.makeText( resetpassword.this ,"enter valid password"
                    , Toast.LENGTH_SHORT).show();
            return;

        }

        JSONObject json =new JSONObject();

        try {
            json.put("n", new_password);
            json.put("e",email);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(json);
        //  Toast.makeText( newpassvolunteer.thisString.valueOf(json), Toast.LENGTH_SHORT).show();

        JsonObjectRequest jobreq = new
                JsonObjectRequest("http://"+ip_adress.ip+"/updatepassword.php", json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    if(response.getString("key").equals("done"))
                    {
                        Toast.makeText( resetpassword.this ,"password updated successfully" , Toast.LENGTH_SHORT).show();
                                finish();

                    }
                    else {
                        Toast.makeText( resetpassword.this ,"error" ,
                                Toast.LENGTH_SHORT).show();

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

        jobreq.setRetryPolicy(new DefaultRetryPolicy(20000, 2 , 2));

        AppController app = new AppController(resetpassword.this);

        app.addToRequestQueue(jobreq);


    }
}

