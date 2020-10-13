package com.kpitb.zakattandusherr.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.kpitb.zakattandusherr.DistrictsActivity;
import com.kpitb.zakattandusherr.Modal.MainPageModelPashto;
import com.kpitb.zakattandusherr.ProvincialActivity;
import com.kpitb.zakattandusherr.R;
import com.kpitb.zakattandusherr.ZakatSchemes;

import java.util.ArrayList;

/**
 * Created by Manxoor on 30/10/2018.
 */

public class MainAdapterPashto extends RecyclerView.Adapter<MainAdapterPashto.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    int from;
    ArrayList<MainPageModelPashto> data, filterlist;
    String btnNam, values;
    MediaPlayer mediaPlayer;

    // create constructor to initialize context and data sent from MainActivity
    public MainAdapterPashto(Context context, ArrayList<MainPageModelPashto> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
        mediaPlayer = MediaPlayer.create(context,R.raw.click_sound);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private String request_status;
        private int status_icon;
        private ImageView status_image;
        private TextView request_label,request_label_urdu;
        private RelativeLayout backgroud;

        MainPageModelPashto dirObj;

        public ViewHolder(View v) {
            super(v);

            request_label =  itemView.findViewById(R.id.tvTitle);
            request_label_urdu =  itemView.findViewById(R.id.tvTitleUrdu);
            status_image  = itemView.findViewById(R.id.ivImage);
            backgroud = itemView.findViewById(R.id.bg);
        }

        public void bindData(MainPageModelPashto c) {
            dirObj = c;
            request_label.setText(c.getRequestStatusPashto());
            //request_label_urdu.setText(c.getRequestStatusUrdu());
            status_image.setImageResource(c.getStatus_icon());
            backgroud.setBackgroundColor(ContextCompat.getColor(context, c.getColor()));

            backgroud.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  mediaPlayer.start();
                    String lable = request_label.getText().toString();
                    if(request_label.getText().equals("د زکات پلانونه")){
                        Intent i = new Intent(context,ZakatSchemes.class);
                        i.putExtra("LANG",lable);
                        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(context);
                        Bundle params = new Bundle();
                        params.putInt("ButtonID",v.getId());
                        btnNam = "د زکات پلانونه";
                        setStatus("د زکات پلانونه");
                        Log.d( "LOGZZZ: ", btnNam);
                        firebaseAnalytics.logEvent(btnNam,params);
                        context.startActivity(i);
                    }else if(request_label.getText().equals("د زکات دفترونه")){
                        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(context);
                        Bundle params = new Bundle();
                        params.putInt("ButtonID",v.getId());
                        btnNam = "ولسوالۍ";
                        setStatus("ولسوالۍ");
                        Log.d( "LOGZZZ: ", btnNam);
                        firebaseAnalytics.logEvent(btnNam,params);
                        context.startActivity(new Intent(context, DistrictsActivity.class));
                    }else if(request_label.getText().equals("ولايتي روغتون")){
                        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(context);
                        Bundle params = new Bundle();
                        params.putInt("ButtonID",v.getId());
                        btnNam = "ولايتي روغتون";
                        setStatus("ولايتي روغتون");
                        Log.d( "LOGZZZ: ", btnNam);
                        firebaseAnalytics.logEvent(btnNam,params);
                        context.startActivity(new Intent(context, ProvincialActivity.class));
                    }
                }
            });
        }
    }

    private void setStatus(String text) {
        values = text;

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
        MainPageModelPashto cases = data.get(position);
        holder.bindData(cases);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}