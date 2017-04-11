package com.example.lenovo.vehicle_trackingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class start_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
    }
    public void goto_admin (View v){
        Intent I = new Intent(start_page.this , MainActivity.class);
        startActivity(I);
    }
    public void goto_user (View v){
        Intent I = new Intent(start_page.this , Userlogin.class);
        startActivity(I);
    }
}
