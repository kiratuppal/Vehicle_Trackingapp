package com.example.lenovo.vehicle_trackingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class seevehicles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seevehicles);
    }

    public void add_vehicle(View view) {

        Intent i = new Intent(seevehicles.this, Vehiclemaster.class);

        startActivity(i);
    }

    public void view_distance(View view) {

        Intent i = new Intent(seevehicles.this, distancelayout.class);

        startActivity(i);
    }

    public void view_vehicle(View view) {
        Intent i = new Intent(seevehicles.this, vehiclemodule.class);

        startActivity(i);

    }
}
