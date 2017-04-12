package com.example.lenovo.vehicle_trackingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class admin_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);
    }

    public void goto_driver(View view) {

        Intent i = new Intent(admin_menu.this, add_driver.class);

        startActivity(i);
    }

    public void goto_distance(View view) {

        Intent i = new Intent(admin_menu.this, distance_driver.class);

        startActivity(i);
    }

    public void goto_service (View view) {

        Intent i = new Intent(admin_menu.this, service_driver.class);

        startActivity(i);
    }

    public void goto_fueldetails (View view) {

        Intent i = new Intent(admin_menu.this, fuel_driver.class);

        startActivity(i);
    }
}
