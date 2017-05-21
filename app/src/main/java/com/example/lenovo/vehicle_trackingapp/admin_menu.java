package com.example.lenovo.vehicle_trackingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import static android.R.id.edit;

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

    public void goto_vehicle(View view) {
        Intent i = new Intent(admin_menu.this, seevehicles.class);

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

    public void goto_profile (View view) {

        Intent i = new Intent(admin_menu.this, Viewadminprofile.class);

        startActivity(i);
    }

    public void goto_maps(View view) {

        Intent i = new Intent(admin_menu.this, Car_location.class);

        startActivity(i);

    }

    public void goto_logout(View view) {
        SharedPreferences.Editor sp = getSharedPreferences("admin_info" , MODE_PRIVATE) .edit();
        sp.clear();
        sp.commit();
        finish();
   }
}
