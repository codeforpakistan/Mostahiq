package com.kpitb.mustahiq.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kpitb.mustahiq.DistrictsActivity;
import com.kpitb.mustahiq.Modal.MainPageModel;
import com.kpitb.mustahiq.ProvincialActivity;
import com.kpitb.mustahiq.R;
import com.kpitb.mustahiq.ZakatSchemes;

import java.util.ArrayList;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Manxoor on 30/10/2018.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    int from;
    ArrayList<MainPageModel> data, filterlist;

    // create constructor to initialize context and data sent from MainActivity
    public MainAdapter(Context context, ArrayList<MainPageModel> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private String request_status;
        private int status_icon;
        private ImageView status_image;
        private TextView request_label,request_label_urdu;
        private RelativeLayout backgroud;

        MainPageModel dirObj;

        public ViewHolder(View v) {
            super(v);

            request_label =  itemView.findViewById(R.id.tvTitle);
            request_label_urdu =  itemView.findViewById(R.id.tvTitleUrdu);
            status_image  = itemView.findViewById(R.id.ivImage);
            backgroud = itemView.findViewById(R.id.bg);
        }

        public void bindData(MainPageModel c) {
            dirObj = c;
            request_label.setText(c.getRequestStatus());
            request_label_urdu.setText(c.getRequestStatusUrdu());
            status_image.setImageResource(c.getStatus_icon());
            backgroud.setBackgroundColor(ContextCompat.getColor(context, c.getColor()));

            backgroud.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String lable = request_label.getText().toString();
                    if(request_label.getText().equals("Zakat Schemes")
                        || request_label_urdu.getText().equals("زکوٰۃ اسکیمیں")){
                        context.startActivity(new Intent(context, ZakatSchemes.class));
                    }else if(request_label.getText().equals("Districts")
                        || request_label_urdu.getText().equals("اضلاع")){
                        context.startActivity(new Intent(context, DistrictsActivity.class));
                    }else if(request_label.getText().equals("Provincial Hospitals")
                        || request_label_urdu.getText().equals("صوبائی ہسپتال")){
                        context.startActivity(new Intent(context, ProvincialActivity.class));
                    }
                }
            });
        }
    }

    // Inflate the layout when ViewHolder created
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.location_page_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MainPageModel cases = data.get(position);
        holder.bindData(cases);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}