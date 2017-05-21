package com.example.lenovo.vehicle_trackingapp;

import android.content.SharedPreferences;
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

public class Vehiclemaster extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehiclemaster);
    }

    public void save_vehicle(View v)
    {
        EditText name = (EditText) findViewById(R.id.vehname);
        EditText company = (EditText) findViewById(R.id.cmpny);
        EditText regdnum = (EditText) findViewById(R.id.regnum);
        EditText vehicletype = (EditText)  findViewById(R.id.type);
        EditText color = (EditText)  findViewById(R.id.vehcolor);
        EditText mfyear = (EditText)  findViewById(R.id.vehyear);
        EditText fueltype = (EditText)  findViewById(R.id.fueltype);



        String name_s = name.getText().toString();
        String company_s = company.getText().toString();
        String regdnum_s = regdnum.getText().toString();
        String vehicletype_s = vehicletype.getText().toString();
        String color_s  = color.getText().toString();
        String mfyear_s  = mfyear.getText().toString();
        String fueltype_s  = fueltype.getText().toString();

        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&*+=?-]).{8,15}$";

        if(regdnum_s.length() < 10)
        {
            Toast.makeText(Vehiclemaster.this , "please enter valid vehicle number" , Toast.LENGTH_SHORT).show();
            return;

        }

        if(name.length() < 2 )
        {
            Toast.makeText(Vehiclemaster.this , "name must be 4 character long " , Toast.LENGTH_SHORT).show();
            return;

        }

        if(company.length() < 4 || !company_s.matches("[a-zA-Z ]+"))
        {
            Toast.makeText(Vehiclemaster.this , "company name must be 4 character long and not contain any digits" , Toast.LENGTH_SHORT).show();
            return;

        }

        if(vehicletype.length() < 4 || !vehicletype_s.matches("[a-zA-Z ]+"))
        {
            Toast.makeText(Vehiclemaster.this , "vehicle type must be 4 character long and not contain any digits" , Toast.LENGTH_SHORT).show();
            return;

        }

        if(color.length() < 2 || !color_s.matches("[a-zA-Z ]+"))
        {
            Toast.makeText(Vehiclemaster.this , "color must be 4 character long and not contain any digits" , Toast.LENGTH_SHORT).show();
            return;

        }

        if(fueltype.length() < 4 || !fueltype_s.matches("[a-zA-Z ]+"))
        {
            Toast.makeText(Vehiclemaster.this , "fuel type must be 4 character long and not contain any digits" , Toast.LENGTH_SHORT).show();
            return;

        }


        if(mfyear_s.length() < 4)
        {
            Toast.makeText(Vehiclemaster.this ,  "year of manufacture must be 4 digit" , Toast.LENGTH_SHORT).show();
            return;

        }

        SharedPreferences sp = getSharedPreferences("admin_info" , MODE_PRIVATE);


        JSONObject job = new JSONObject();

        try {
            job.put("name_key" , name_s);
            job.put("comp_key" , company_s);
            job.put("color_key",color_s);
            job.put("fuel_key",fueltype_s);
            job.put("year_key",mfyear_s);
            job.put("type_key",vehicletype_s);
            job.put("regnum_key",regdnum_s);
            job.put("aid" , sp.getString("admin_id",""));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println(job);
        JsonObjectRequest jobreq = new JsonObjectRequest("http://"+ip_adress.ip+"/addvehicles.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {

                try {
                    if(response.getString("key").equals("1"))
                    {
                        Toast.makeText(Vehiclemaster.this , "Vehicle Added" , Toast.LENGTH_SHORT).show();
                    }

                    if(response.getString("key").equals("0"))
                    {
                        Toast.makeText(Vehiclemaster.this , "error" , Toast.LENGTH_SHORT).show();

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
        AppController app = new AppController(Vehiclemaster.this);
        app.addToRequestQueue(jobreq);


    }
}


