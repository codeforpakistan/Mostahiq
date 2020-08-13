package com.kpitb.zakatandusher.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kpitb.zakatandusher.DeeniMadaris;
import com.kpitb.zakatandusher.EducationGeneral;
import com.kpitb.zakatandusher.EducationProfessional;
import com.kpitb.zakatandusher.GuzaraAllownce;
import com.kpitb.zakatandusher.HealthCare;
import com.kpitb.zakatandusher.MarriageAllownce;
import com.kpitb.zakatandusher.Modal.HomePageModel;
import com.kpitb.zakatandusher.R;

import java.util.ArrayList;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Manxoor on 30/10/2018.
 */

public class MainPageAdapter extends RecyclerView.Adapter<MainPageAdapter.ViewHolder> {
        private Context context;
        private LayoutInflater inflater;
        int from;
        ArrayList<HomePageModel> data, filterlist;

    // create constructor to initialize context and data sent from MainActivity
    public MainPageAdapter(Context context, ArrayList<HomePageModel> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private String request_status;
        private int status_icon;
        private ImageView status_image;
        private TextView request_label;
        private ConstraintLayout backgroud;

        HomePageModel dirObj;

        public ViewHolder(View v) {
            super(v);

            request_label =  itemView.findViewById(R.id.tvTitle);
            status_image  = itemView.findViewById(R.id.ivImage);
            backgroud = itemView.findViewById(R.id.bg);
        }

        public void bindData(HomePageModel c) {
            dirObj = c;
            request_label.setText(c.getRequestStatus());
            status_image.setImageResource(c.getStatus_icon());
            backgroud.setBackgroundColor(ContextCompat.getColor(context, c.getColor()));

            backgroud.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String lable = request_label.getText().toString();
                    if(request_label.getText().equals("Guzzara Allowance")){
                        context.startActivity(new Intent(context, GuzaraAllownce.class));
                    }else if(request_label.getText().equals("Marriage Assistance")){
                        context.startActivity(new Intent(context, MarriageAllownce.class));
                    }else if(request_label.getText().equals("Educational Stipends (General)")){
                        context.startActivity(new Intent(context, EducationGeneral.class));
                    }else if(request_label.getText().equals("Educational Stipends (Technical)")){
                        context.startActivity(new Intent(context, EducationProfessional.class));
                    }else if(request_label.getText().equals("Deeni Madaris")) {
                        context.startActivity(new Intent(context, DeeniMadaris.class));
                    }else if(request_label.getText().equals("Health Care")) {
                        context.startActivity(new Intent(context, HealthCare.class));
                    }
                }
            });
        }
    }

    // Inflate the layout when ViewHolder created
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.main_page_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HomePageModel cases = data.get(position);
        holder.bindData(cases);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}