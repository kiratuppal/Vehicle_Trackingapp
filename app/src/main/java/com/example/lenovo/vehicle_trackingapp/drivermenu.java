package com.example.lenovo.vehicle_trackingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

public class drivermenu extends AppCompatActivity {

    DrawerLayout d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivermenu);
        d = (DrawerLayout) findViewById(R.id.activity_drivermenu);
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

    public void goto_userlogin(View view) {
        SharedPreferences.Editor sp = getSharedPreferences("driver_info" , MODE_PRIVATE).edit();
        sp.clear();
        sp.commit();
        finish();
    }

    public void upload_document(View view) {


        Intent i = new Intent(drivermenu.this , Upload_document.class);

        startActivity(i);
    }

    public void show_drawer(View view) {

        d.openDrawer(Gravity.LEFT);
    }

    public void driver_profile(View view) {

        Intent i = new Intent(drivermenu.this , Driver_profile.class);

        startActivity(i);
    }
}
