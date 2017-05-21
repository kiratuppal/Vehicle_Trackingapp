package com.example.lenovo.vehicle_trackingapp.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lenovo.vehicle_trackingapp.R;

/**
 * Created by lenovo on 4/17/2017.
 */

public class view_holder extends RecyclerView.ViewHolder {


    public TextView name_id , contnum_id , email_id , age_id , vehicle_id , admin_id ;

    public Button delete_btn ;

    public view_holder(View itemView) {
        super(itemView);


        // here itemView contain the cell layout
        // finding view by id , here we use itemView.findViewById becoz itemView contains cell layout
        name_id  = (TextView) itemView.findViewById(R.id.name_id_text);
        contnum_id = (TextView) itemView.findViewById(R.id.contnum_id_text);
        email_id = (TextView) itemView.findViewById(R.id.email_id_text);
        age_id = (TextView) itemView.findViewById(R.id.age_id_text);
        vehicle_id = (TextView) itemView.findViewById(R.id.vehicle_id_text);
        admin_id = (TextView) itemView.findViewById(R.id.admin_id_text);
        delete_btn = (Button) itemView.findViewById(R.id.delete_driver);

    }
}

