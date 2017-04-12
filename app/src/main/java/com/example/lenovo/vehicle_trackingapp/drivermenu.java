package com.example.lenovo.vehicle_trackingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class drivermenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivermenu);
    }
    public void goto_fuel (View view) {

        Intent i = new Intent(drivermenu.this, Fuel_details.class);

        startActivity(i);
    }

    public void goto_service (View view) {

        Intent i = new Intent(drivermenu.this, service_details.class);

        startActivity(i);
    }

    public void distance (View view) {

        Intent i = new Intent(drivermenu.this, distance_driver.class);

        startActivity(i);
    }
}
