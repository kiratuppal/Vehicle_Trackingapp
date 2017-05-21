package com.example.lenovo.vehicle_trackingapp;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Distanceview extends AppCompatActivity {

    TextView vnumber,date,totaldist , totaldist2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance_view);
        vnumber=(TextView)findViewById (R.id.vehicle_number_txt);
        date=(TextView)findViewById (R.id.date_txt);
        totaldist = (TextView)findViewById (R.id.distance_txt);

        totaldist2 = (TextView)findViewById (R.id.distance_txt2);

        vnumber.setText (getIntent().getStringExtra("vehiclenum"));

        date.setText (getIntent().getStringExtra("fromdate"));



        get_distancedata();
    }
    public void get_distancedata()
    {

        final JSONObject jobj = new JSONObject();
        try {
            jobj.put("vehicleno_key",getIntent().getStringExtra("vehiclenum"));

            jobj.put("date_key",getIntent().getStringExtra("fromdate"));


        } catch (JSONException e) {
            e.printStackTrace();
        }


        System.out.println(jobj);
        // making json array request to get json array data
        JsonObjectRequest jsonreq = new JsonObjectRequest("http://"+ip_adress.ip+"/viewdistance.php", jobj,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);

                try {

                    JSONArray jarr2 =  response.getJSONArray ("key2");

                    Double finaldistance2 = 0.0;

                    for(int i = 0 ; i < jarr2.length ()-1 ; i++)
                    {
                        JSONObject job1 = jarr2.getJSONObject (i);

                        JSONObject job2 = jarr2.getJSONObject (i+1);

                        Location startPoint=new Location("locationA");
                        startPoint.setLatitude(Double.parseDouble (job1.getString ("Latitude")));
                        startPoint.setLongitude(Double.parseDouble (job1.getString ("Longitude")));

                        Location endPoint=new Location ("locationB");
                        endPoint.setLatitude(Double.parseDouble (job2.getString ("Latitude")));
                        endPoint.setLongitude(Double.parseDouble (job2.getString ("Longitude")));

                        double distance=startPoint.distanceTo(endPoint);

                        System.out.println (distance);

                        finaldistance2 += distance;
                    }

                    int dis2 = (int) (finaldistance2/1000 );

                   

                    totaldist2.setText (String.valueOf (dis2)+" km total" );

                    JSONArray jarr =  response.getJSONArray ("key");

                    Double finaldistance = 0.0;

                    for(int i = 0 ; i < jarr.length ()-1 ; i++)
                    {
                        JSONObject job1 = jarr.getJSONObject (i);

                        JSONObject job2 = jarr.getJSONObject (i+1);

                        Location startPoint=new Location("locationA");
                        startPoint.setLatitude(Double.parseDouble (job1.getString ("Latitude")));
                        startPoint.setLongitude(Double.parseDouble (job1.getString ("Longitude")));

                        Location endPoint=new Location ("locationB");
                        endPoint.setLatitude(Double.parseDouble (job2.getString ("Latitude")));
                        endPoint.setLongitude(Double.parseDouble (job2.getString ("Longitude")));

                        double distance=startPoint.distanceTo(endPoint);

                        System.out.println (distance);

                        finaldistance += distance;
                    }

                    int dis = (int) (finaldistance/1000 );

                    System.out.println(dis);

                    totaldist.setText (String.valueOf (dis)+" km today");


                } catch (JSONException e) {
                    e.printStackTrace();


                    // making object of adapter class and passing json array and Activity to adapter constructer



                    // setting properties for recycler view like how it scroll vertically , horizontally

                }
            }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println(error);

            }
        });


        jsonreq.setRetryPolicy(new DefaultRetryPolicy(20000 , 2 ,2));

        AppController app = new AppController(Distanceview.this);

        app.addToRequestQueue(jsonreq);
    }
}