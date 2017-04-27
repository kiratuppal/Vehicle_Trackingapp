package com.example.lenovo.vehicle_trackingapp.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.lenovo.vehicle_trackingapp.R;

/**
 * Created by lenovo on 4/27/2017.
 */

public class viewholder_service extends RecyclerView.ViewHolder {

    public TextView car_id , bill_id , amt_id , ser_id , date_id ;
    public viewholder_service(View itemView) {
        super(itemView);

        car_id  = (TextView) itemView.findViewById(R.id.carid);
        bill_id = (TextView) itemView.findViewById(R.id.billid);
        amt_id = (TextView) itemView.findViewById(R.id.amtid);
        ser_id = (TextView) itemView.findViewById(R.id.serid);
        date_id = (TextView) itemView.findViewById(R.id.dateid);

    }
}


