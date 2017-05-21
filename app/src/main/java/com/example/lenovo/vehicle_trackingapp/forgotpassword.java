package com.example.lenovo.vehicle_trackingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.lenovo.vehicle_trackingapp.R.id.email_et;

public class forgotpassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

    }




            public void goto_otp(View v)
            {
                EditText email_et = (EditText) findViewById(R.id.entemail);

                String mails =  email_et.getText().toString();

                if(!Patterns.EMAIL_ADDRESS.matcher(mails).matches() ||
                        mails.contains("_"))
                {
                    Toast.makeText(forgotpassword.this , "please enter valid email type" , Toast.LENGTH_SHORT).show();
                    return;
                }

                int randomPIN = (int)(Math.random()*9000)+1000;

                Intent i = new Intent(forgotpassword.this , sendotp.class);

                i.putExtra("emails" , mails);

                i.putExtra("pin" , randomPIN);

                startActivity(i);
            }

        }

