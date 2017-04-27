package com.example.lenovo.vehicle_trackingapp.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.lenovo.vehicle_trackingapp.R;

/**
 * Created by lenovo on 4/24/2017.
 */

public class viewholder_fuel extends RecyclerView.ViewHolder {

    public TextView car_id , bill_id , vol_id , amt_id , date_id ;
    public viewholder_fuel(View itemView) {
        super(itemView);

        car_id  = (TextView) itemView.findViewById(R.id.carnum);
        bill_id = (TextView) itemView.findViewById(R.id.billnum);
        vol_id = (TextView) itemView.findViewById(R.id.volnum);
        amt_id = (TextView) itemView.findViewById(R.id.amtnum);
        date_id = (TextView) itemView.findViewById(R.id.datenum);

    }
    }

