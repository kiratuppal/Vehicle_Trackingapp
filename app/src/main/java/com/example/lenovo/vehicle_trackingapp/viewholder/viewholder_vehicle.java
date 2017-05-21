package com.example.lenovo.vehicle_trackingapp.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lenovo.vehicle_trackingapp.R;

/**
 * Created by lenovo on 5/9/2017.
 */

public class viewholder_vehicle extends RecyclerView.ViewHolder {

    public TextView Name , company , color , type , mnyear , regdnum , fuel ;
    public Button delete;
    public viewholder_vehicle(View itemView) {
        super(itemView);

        Name = (TextView) itemView.findViewById(R.id.vehname);
        company  = (TextView) itemView.findViewById(R.id.compname);
        color = (TextView) itemView.findViewById(R.id.color);
        type  = (TextView) itemView.findViewById(R.id.vehtype);
        mnyear = (TextView) itemView.findViewById(R.id.manyer);
        regdnum  = (TextView) itemView.findViewById(R.id.regnumid);
        fuel = (TextView) itemView.findViewById(R.id.fueltypee);
        delete = (Button) itemView.findViewById(R.id.delete_vehicle);
    }
}
