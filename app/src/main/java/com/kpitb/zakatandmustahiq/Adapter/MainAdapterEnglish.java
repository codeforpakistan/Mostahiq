package com.kpitb.zakatandmustahiq.Adapter;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.kpitb.zakatandmustahiq.DistrictsActivity;
import com.kpitb.zakatandmustahiq.Modal.MainPageModelEnglish;
import com.kpitb.zakatandmustahiq.ProvincialActivity;
import com.kpitb.zakatandmustahiq.R;
import com.kpitb.zakatandmustahiq.ZakatSchemes;

import java.util.ArrayList;

/**
 * Created by Manxoor on 30/10/2018.
 */

public class MainAdapterEnglish extends RecyclerView.Adapter<MainAdapterEnglish.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    int from;
    ArrayList<MainPageModelEnglish> data, filterlist;
    String btnNam, values;
    private MediaPlayer mediaPlayer;

    // create constructor to initialize context and data sent from MainActivity
    public MainAdapterEnglish(Context context, ArrayList<MainPageModelEnglish> data) {
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
        private CardView cardview;

        MainPageModelEnglish dirObj;

        public ViewHolder(View v) {
            super(v);

            request_label =  itemView.findViewById(R.id.tvTitle);
            request_label_urdu =  itemView.findViewById(R.id.tvTitleUrdu);
            status_image  = itemView.findViewById(R.id.ivImage);
            backgroud = itemView.findViewById(R.id.bg);
            cardview = itemView.findViewById(R.id.cardview);
        }

        public void bindData(MainPageModelEnglish c) {
            dirObj = c;
            request_label.setText(c.getRequestStatusEng());
            //request_label_urdu.setText(c.getRequestStatusUrdu());
            status_image.setImageResource(c.getStatus_icon());
            backgroud.setBackgroundColor(ContextCompat.getColor(context, c.getColor()));

            cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                   mediaPlayer.start();
                    YoYo.with(Techniques.Landing)
                            .duration(200)
                            .interpolate(new AccelerateDecelerateInterpolator())
                            .withListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    String lable = request_label.getText().toString();
                                    if(request_label.getText().equals("Zakat Schemes")){
                                        Intent i = new Intent(context,ZakatSchemes.class);
                                        i.putExtra("LANG",lable);
                                        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(context);
                                        Bundle params = new Bundle();
                                        params.putInt("ButtonID",v.getId());
                                        btnNam = "Schemes";
                                        setStatus("Schemes_Info");
                                        Log.d( "LOGZZZ: ", btnNam);
                                        firebaseAnalytics.logEvent(btnNam,params);
                                        context.startActivity(i);
                                    }else if(request_label.getText().equals("Provincial Hospitals")){
                                        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(context);
                                        Bundle params = new Bundle();
                                        params.putInt("ButtonID",v.getId());
                                        btnNam = "Hospitals";
                                        setStatus("Hospitals_Info");
                                        Log.d( "LOGZZZ: ", btnNam);
                                        firebaseAnalytics.logEvent(btnNam,params);
                                        context.startActivity(new Intent(context, ProvincialActivity.class));
                                    }else if(request_label.getText().equals("District Offices")){
                                        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(context);
                                        Bundle params = new Bundle();
                                        params.putInt("ButtonID",v.getId());
                                        btnNam = "Districts";
                                        setStatus("District_Info");
                                        Log.d( "LOGZZZ: ", btnNam);
                                        firebaseAnalytics.logEvent(btnNam,params);
                                        context.startActivity(new Intent(context, DistrictsActivity.class));
                                    }
                                }

                                @Override
                                public void onAnimationCancel(Animator animation) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animation) {

                                }
                            }).playOn(cardview);
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
        MainPageModelEnglish cases = data.get(position);
        holder.bindData(cases);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}