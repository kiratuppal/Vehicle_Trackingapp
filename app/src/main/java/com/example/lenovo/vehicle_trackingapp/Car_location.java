package com.example.lenovo.vehicle_trackingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Car_location extends AppCompatActivity {

    EditText eddt;
    String snum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_location);
        eddt = (EditText)findViewById(R.id.eddt1);
    }

    public void viewcar(View view) {

        snum = eddt.getText().toString();
        Intent i = new Intent(Car_location.this, Show_map.class);
        i.putExtra("number",snum);
        startActivity(i);
    }
}
