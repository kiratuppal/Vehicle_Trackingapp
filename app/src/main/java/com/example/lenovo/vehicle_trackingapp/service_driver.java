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

public class service_driver extends AppCompatActivity {

    EditText fdate , tdate ;
    String   frmdate , todate , vhnum;

    Spinner  vehinumbr;

    int year;
    int month;
    int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_driver);
        fdate = (EditText)findViewById(R.id.dateofservice);
        tdate = (EditText)findViewById(R.id.dateofservice2);
        vehinumbr = (Spinner) findViewById(R.id.numofveh);
        fdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar mcurrentDate = Calendar.getInstance();
                year = mcurrentDate.get(Calendar.YEAR);
                month = mcurrentDate.get(Calendar.MONTH);
                day = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                final DatePickerDialog mDatePicker = new DatePickerDialog(service_driver.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        fdate.setText(new StringBuilder().append(selectedday).append("/").append(selectedmonth + 1).append("/").append(selectedyear));
                        int month_k = selectedmonth + 1;

                    }
                }, year, month, day);
                mDatePicker.setTitle("Please select date");
                // TODO Hide Future Date Here
                mDatePicker.getDatePicker().setMaxDate(System.currentTimeMillis());

                // TODO Hide Past Date Here
                //  mDatePicker.getDatePicker().setMinDate(System.currentTimeMillis());
                mDatePicker.show();

            }
        });
        tdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar mcurrentDate = Calendar.getInstance();
                year = mcurrentDate.get(Calendar.YEAR);
                month = mcurrentDate.get(Calendar.MONTH);
                day = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                final DatePickerDialog mDatePicker = new DatePickerDialog(service_driver.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        tdate.setText(new StringBuilder().append(selectedday).append("/").append(selectedmonth + 1).append("/").append(selectedyear));
                        int month_k = selectedmonth + 1;

                    }
                }, year, month, day);
                mDatePicker.setTitle("Please select date");
                // TODO Hide Future Date Here
                mDatePicker.getDatePicker().setMaxDate(System.currentTimeMillis());

                // TODO Hide Past Date Here
                //  mDatePicker.getDatePicker().setMinDate(System.currentTimeMillis());
                mDatePicker.show();

            }
        });

        get_vehicles();
    }


    public void see_service(View View){

        frmdate = fdate.getText().toString();
        todate = tdate.getText().toString();
        vhnum = vehinumbr.getSelectedItem().toString();

        Intent i = new Intent(service_driver.this, viewservice.class);
        i.putExtra("vehiclenum",vhnum);
        i.putExtra("fromdate",frmdate);
        i.putExtra("todatee",todate);
        startActivity(i);
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

                    ArrayAdapter<String> dataadapter = new ArrayAdapter<String>(service_driver.this, R.layout.spinner_cell, list);
                    vehinumbr.setAdapter(dataadapter);


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
        AppController app = new AppController(service_driver.this);
        app.addToRequestQueue(jobreq);


    }
}


