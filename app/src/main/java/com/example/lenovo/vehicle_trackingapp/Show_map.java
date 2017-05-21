package com.example.lenovo.vehicle_trackingapp;

import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static com.example.lenovo.vehicle_trackingapp.R.id.map;

public class Show_map extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_map);
        // Getting reference to the SupportMapFragmegetMap();nt of activity_main.xml
        SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(map);

        // Getting GoogleMap object from the fragment
        fm.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {

                JSONObject job = new JSONObject();
                try {
                    job.put("vehicle_number",getIntent().getStringExtra("number"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest req = new JsonObjectRequest("http://"+ip_adress.ip+"/locations.php",job , new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        System.out.println(response);

                        try {
                            JSONArray jarr = response.getJSONArray("result");



                                JSONObject obj = null;

                                obj = (JSONObject) jarr.get(0);

                                String lati = obj.getString("Latitude");
                                String longi = obj.getString("Longitude");
                            Geocoder geocoder;
                            List<Address> addresses;
                            geocoder = new Geocoder(Show_map.this, Locale.getDefault());

                            addresses = geocoder.getFromLocation(Double.parseDouble (lati), Double.parseDouble (longi), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                            String city = addresses.get(0).getLocality();

                                googleMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(Double.valueOf(lati), Double.valueOf(longi)))
                                        .title(address+ "," +city));
                                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                                LatLng coordinate = new LatLng(Double.valueOf(lati), Double.valueOf(longi));
                                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinate, 16));



                        }catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });


                AppController app = new AppController(Show_map.this);
                app.addToRequestQueue(req);

            }
        });










    }
}