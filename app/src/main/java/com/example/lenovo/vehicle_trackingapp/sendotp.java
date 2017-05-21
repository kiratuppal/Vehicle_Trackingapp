package com.example.lenovo.vehicle_trackingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

public class sendotp extends AppCompatActivity {

    EditText otp1 , otp2 , otp3 , otp4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendotp);

        final String emails = getIntent().getStringExtra("emails");

        final int pin = getIntent().getIntExtra("pin" , 0);


        Toast.makeText(sendotp.this , String.valueOf(pin) ,
                Toast.LENGTH_SHORT ).show();


        otp1 = (EditText) findViewById(R.id.firstblck);
        otp2 = (EditText) findViewById(R.id.secndblck);
        otp3 = (EditText) findViewById(R.id.thirdblck);
        otp4 = (EditText) findViewById(R.id.fourthtblck);

        otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int
                    before, int count) {

                if(s.length() == 1)
                {
                    otp1.clearFocus();
                    otp2.requestFocus();
                    otp2.setCursorVisible(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int
                    before, int count) {

                if(s.length() == 1)
                {
                    otp2.clearFocus();
                    otp3.requestFocus();
                    otp3.setCursorVisible(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int
                    before, int count) {

                if(s.length() == 1)
                {
                    otp3.clearFocus();
                    otp4.requestFocus();
                    otp4.setCursorVisible(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int
                    before, int count) {

                if(s.length() == 1)
                {
                    String value1 = otp1.getText().toString();
                    String value2 = otp2.getText().toString();
                    String value3 = otp3.getText().toString();
                    String value4 = otp4.getText().toString();

                    String final_pin = value1+value2+value3+value4;

                    int fin_pin = Integer.parseInt(final_pin);

                    if(fin_pin == pin)
                    {
                        Intent i = new Intent(sendotp.this , resetpassword.class);

                        i.putExtra("email_et" , emails);
                        startActivity(i);
                        finish();
                    }


                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}


