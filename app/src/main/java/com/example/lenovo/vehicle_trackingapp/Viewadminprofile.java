package com.example.lenovo.vehicle_trackingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import static android.R.attr.name;
import static com.example.lenovo.vehicle_trackingapp.R.id.adname;

public class Viewadminprofile extends AppCompatActivity {

    TextView adminname,ademail,adcontact , pass_et;

    String name,email,contact ,pass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewadminprofile);
       adminname = (TextView)findViewById(R.id.textView);
        ademail = (TextView) findViewById(R.id.email_tex);
        adcontact = (TextView)findViewById(R.id.mobile_et);
        pass_et = (TextView)findViewById(R.id.pass_et);

        get_values();
    }

        public void get_values()

        {
            JSONObject jobj = new JSONObject();

            SharedPreferences sp = getSharedPreferences("admin_info",MODE_PRIVATE);
            String admin_aid = sp.getString("admin_id" , "");

            try {
                jobj.put("admin_id" , admin_aid);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            System.out.println (jobj);

            JsonObjectRequest jobreq = new JsonObjectRequest("http://"+ip_adress.ip+"/admnprof.php", jobj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    try {
                        JSONObject job_box =  response.getJSONObject ("result");

                        name = job_box.getString("name");

                        email =  job_box.getString("email");

                        contact = job_box.getString("contact");

                        pass = job_box.getString("password");

                        adminname.setText(name);
                        ademail.setText( email );
                        adcontact.setText(contact);
                        pass_et.setText( job_box.getString("password"));



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


            jobreq.setRetryPolicy(new DefaultRetryPolicy(20000 ,  2 , 2));
            AppController app = new AppController(Viewadminprofile.this);
            app.addToRequestQueue(jobreq);
        }


    public void update_profile(View view) {

        Intent i = new Intent(Viewadminprofile.this,Updateprofile.class);

        i.putExtra ("name_key" ,name );
        i.putExtra ("email_key" ,email );
        i.putExtra ("contact_key" ,contact );
        i.putExtra("pass", pass);
        startActivity(i);
    }
}

