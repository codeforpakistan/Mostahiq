package com.kpitb.zakatandusher.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.kpitb.zakatandusher.DeeniMadaris;
import com.kpitb.zakatandusher.DeeniMadarisPashto;
import com.kpitb.zakatandusher.EducationGeneral;
import com.kpitb.zakatandusher.EducationGeneralPashto;
import com.kpitb.zakatandusher.EducationProfessional;
import com.kpitb.zakatandusher.EducationProfessionalPashto;
import com.kpitb.zakatandusher.GuzaraAllowancePashto;
import com.kpitb.zakatandusher.GuzaraAllownceUrdu;
import com.kpitb.zakatandusher.HealthCare;
import com.kpitb.zakatandusher.HealthCarePashto;
import com.kpitb.zakatandusher.MarriageAllownce;
import com.kpitb.zakatandusher.Marriage_Allowance_Pashto;
import com.kpitb.zakatandusher.Modal.HomePageModel;
import com.kpitb.zakatandusher.R;

import java.util.ArrayList;

public class MainPageAdapterPashto extends RecyclerView.Adapter<MainPageAdapterPashto.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    int from;
    ArrayList<HomePageModel> data, filterlist;

    // create constructor to initialize context and data sent from MainActivity
    public MainPageAdapterPashto(Context context, ArrayList<HomePageModel> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.main_page_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomePageModel cases = data.get(position);
        holder.bindData(cases);
    }

    @Override
    public int getItemCount() {
        return data.size();
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
                    if(request_label.getText().equals("ګزاره مرسته")){
                        context.startActivity(new Intent(context, GuzaraAllowancePashto.class));
                    }else if(request_label.getText().equals("واده مرسته")){
                        context.startActivity(new Intent(context, Marriage_Allowance_Pashto.class));
                    }else if(request_label.getText().equals("تعليمى ګامونه (عمومي)")){
                        context.startActivity(new Intent(context, EducationGeneralPashto.class));
                    }else if(request_label.getText().equals("تعليمى ګامونه (هنري)")){
                        context.startActivity(new Intent(context, EducationProfessionalPashto.class));
                    }else if(request_label.getText().equals("دينى مدرسې")) {
                        context.startActivity(new Intent(context, DeeniMadarisPashto.class));
                    }else if(request_label.getText().equals("روغتیایی پاملرنه")) {
                        context.startActivity(new Intent(context, HealthCarePashto.class));
                    }
                }
            });
        }
    }
}
