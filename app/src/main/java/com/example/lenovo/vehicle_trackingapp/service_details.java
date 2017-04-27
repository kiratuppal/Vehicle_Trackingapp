package com.example.lenovo.vehicle_trackingapp;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class service_details extends AppCompatActivity {

    public void ser_details (View v) {
        EditText vehnum = (EditText) findViewById(R.id.Vnum);
        EditText billno = (EditText) findViewById(R.id.Bno);
        EditText amount = (EditText) findViewById(R.id.amt2);
        EditText service = (EditText) findViewById(R.id.service_det);
        EditText date = (EditText) findViewById(R.id.date2);

        String vehnum_s = vehnum.getText().toString();
        String bill_s = billno.getText().toString();
        String amount_s = amount.getText().toString();
        String service_s = service.getText().toString();
        String date_s = date.getText().toString();

        if (vehnum_s.equals("")) {
            Toast.makeText(service_details.this, "please enter vehicle number", Toast.LENGTH_SHORT).show();
            return;
        }
        if (bill_s.equals("")) {
            Toast.makeText(service_details.this, "please enter bill number", Toast.LENGTH_SHORT).show();
            return;
        }
        if (amount_s.equals("")) {
            Toast.makeText(service_details.this, "please enter amount", Toast.LENGTH_SHORT).show();
            return;

        }
        if (service_s.equals("")) {
            Toast.makeText(service_details.this, "please enter service Details", Toast.LENGTH_SHORT).show();
            return;
        }
        if (date_s.equals("")) {
            Toast.makeText(service_details.this, "please enter date", Toast.LENGTH_SHORT).show();
            return;
        }

        JSONObject job = new JSONObject();

        SharedPreferences sp = getSharedPreferences("driver_info" , MODE_PRIVATE);
        String driver_id = sp.getString("driver_id" , "");
        {
            try {
                job.put("vehnum_key" , vehnum_s);
                job.put("bill_key" , bill_s);
                job.put("amount_key" , amount_s);
                job.put("service_key" , service_s);
                job.put("date_key" , date_s);
                job.put("driver_key",driver_id);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            System.out.println(job);
            JsonObjectRequest jobreq = new JsonObjectRequest("http://"+ip_adress.ip+"/servicedetails.php", job, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if (response.getString("key").equals("1")) {
                            Toast.makeText(service_details.this, "done", Toast.LENGTH_SHORT).show();
                        }
                        if (response.getString("key").equals("0")) {
                            Toast.makeText(service_details.this, "This bill number already exists", Toast.LENGTH_SHORT).show();
                        }

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
            jobreq.setRetryPolicy(new DefaultRetryPolicy(20000 , 3 , 2));
            AppController app = new AppController(service_details.this);
            app.addToRequestQueue(jobreq);

        }
    }


    int year;
    int month;
    int day;
    EditText datecalender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_details);

        datecalender = (EditText) findViewById(R.id.date);
        Calendar c = Calendar.getInstance();
        datecalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar mcurrentDate = Calendar.getInstance();
                year = mcurrentDate.get(Calendar.YEAR);
                month = mcurrentDate.get(Calendar.MONTH);
                day = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                final DatePickerDialog mDatePicker = new DatePickerDialog(service_details.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        datecalender.setText(new StringBuilder().append(day).append("/").append(month + 1).append("/").append(year));
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
    }
}



