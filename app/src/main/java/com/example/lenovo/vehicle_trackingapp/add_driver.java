package com.example.lenovo.vehicle_trackingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class add_driver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_driver);
    }

    public void goto_registration(View view) {

        Intent i = new Intent(add_driver.this, registration.class);

        startActivity(i);
    }

    public void goto_view_driver(View view) {

        Intent i = new Intent(add_driver.this, Viewdriver.class);

        startActivity(i);
    }

    public void goto_docs(View view) {

        Intent i = new Intent(add_driver.this, Viewdocs.class);

        startActivity(i);
    }
}
