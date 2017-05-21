package com.example.lenovo.vehicle_trackingapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class distancelayout extends AppCompatActivity {

    EditText date;
    Spinner vehicle_num;

    int year;
    int month;
    int day;
    EditText disdate;
    String disdate_s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distancelayout);

        disdate = (EditText)findViewById(R.id.disdate);


        vehicle_num = (Spinner) findViewById(R.id.godist);
        disdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar mcurrentDate = Calendar.getInstance();
                year = mcurrentDate.get(Calendar.YEAR);
                month = mcurrentDate.get(Calendar.MONTH);
                day = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                final DatePickerDialog mDatePicker = new DatePickerDialog(distancelayout.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        disdate.setText(new StringBuilder().append(selectedday).append("/").append(selectedmonth + 1).append("/").append(selectedyear));
                        int month_k = selectedmonth + 1;

                    }
                }, year, month, day);
                mDatePicker.setTitle("Please select date");
                // TODO Hide Future Date Here
                mDatePicker.getDatePicker().setMaxDate(System.currentTimeMillis());

                // TODO Hide Past Date

                mDatePicker.show();

            }
        });

        get_vehicles();

    }



    public void get_vehicles() {
        JSONObject job = new JSONObject();

        SharedPreferences sp = getSharedPreferences("admin_info", MODE_PRIVATE);
        String admin_id = sp.getString("admin_id", "");

        try {

            job.put("aid", admin_id);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        System.out.println(job);
        JsonObjectRequest jobreq = new JsonObjectRequest("http://" + ip_adress.ip + "/viewvehicle.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jarr = response.getJSONArray("key");

                    List<String> list = new ArrayList<>();
                    list.add("select vehicle number");

                    for (int i = 0; i < jarr.length(); i++) {
                        try {
                            JSONObject job = jarr.getJSONObject(i);
                            list.add(job.getString("vehiclenumber"));
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }


                    }

                    ArrayAdapter<String> dataadapter = new ArrayAdapter<String>(distancelayout.this, R.layout.spinner_cell, list);
                    vehicle_num.setAdapter(dataadapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println(error);

            }
        });
        jobreq.setRetryPolicy(new DefaultRetryPolicy(20000, 3, 2));
        AppController app = new AppController(distancelayout.this);
        app.addToRequestQueue(jobreq);

    }

    public void goto_distance(View view) {


        if (disdate.getText().toString().equals("")) {

            return;
        }
        if (vehicle_num.getSelectedItem().toString().equals("")) {
            return;
        }

        Intent i = new Intent(distancelayout.this, Distanceview.class);
        i.putExtra("vehiclenum", vehicle_num.getSelectedItem().toString());
        i.putExtra("fromdate", disdate.getText().toString());

        startActivity(i);


    }
        }

