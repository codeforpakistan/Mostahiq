package com.kpitb.zakattandusherr.Adapter;

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
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.kpitb.zakattandusherr.DeeniMadaris;
import com.kpitb.zakattandusherr.DistrictsActivity;
import com.kpitb.zakattandusherr.EducationGeneral;
import com.kpitb.zakattandusherr.EducationProfessional;
import com.kpitb.zakattandusherr.GuzaraAllownce;
import com.kpitb.zakattandusherr.HealthCare;
import com.kpitb.zakattandusherr.MarriageAllownce;
import com.kpitb.zakattandusherr.Modal.HomePageModel;
import com.kpitb.zakattandusherr.ProvincialActivity;
import com.kpitb.zakattandusherr.R;
import com.kpitb.zakattandusherr.ZakatSchemes;

import java.util.ArrayList;

import androidx.cardview.widget.CardView;
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
    String btnNam, values;
    private FirebaseAnalytics firebaseAnalytics;
    private MediaPlayer mediaPlayer;

    // create constructor to initialize context and data sent from MainActivity
    public MainPageAdapter(Context context, ArrayList<HomePageModel> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
        firebaseAnalytics = FirebaseAnalytics.getInstance(context);
        mediaPlayer = MediaPlayer.create(context,R.raw.click_sound);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private String request_status;
        private int status_icon;
        private ImageView status_image;
        private TextView request_label;
        private ConstraintLayout backgroud;
        private CardView cardView;

        HomePageModel dirObj;

        public ViewHolder(View v) {
            super(v);

            request_label =  itemView.findViewById(R.id.tvTitle);
            status_image  = itemView.findViewById(R.id.ivImage);
            backgroud = itemView.findViewById(R.id.bg);
            cardView = itemView.findViewById(R.id.cardview);
        }

        public void bindData(HomePageModel c) {
            dirObj = c;
            request_label.setText(c.getRequestStatus());
            status_image.setImageResource(c.getStatus_icon());
            backgroud.setBackgroundColor(ContextCompat.getColor(context, c.getColor()));

            cardView.setOnClickListener(new View.OnClickListener() {
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
                                    if(request_label.getText().equals("Guzzara Allowance")){
                                        firebaseAnalytics = FirebaseAnalytics.getInstance(context);
                                        Bundle params = new Bundle();
                                        params.putInt("ButtonID",v.getId());
                                        btnNam = "Guzzara";
                                        setStatus("Guzzara");
                                        Log.d( "LOG: ", btnNam);
                                        firebaseAnalytics.logEvent(btnNam,params);
                                        context.startActivity(new Intent(context, GuzaraAllownce.class));
                                    }else if(request_label.getText().equals("Marriage Assistance")){
                                        firebaseAnalytics = FirebaseAnalytics.getInstance(context);
                                        Bundle params = new Bundle();
                                        params.putInt("ButtonID",v.getId());
                                        btnNam = "Marriage";
                                        setStatus("Marriage");
                                        Log.d( "LOGZZZ: ", btnNam);
                                        firebaseAnalytics.logEvent(btnNam,params);
                                        context.startActivity(new Intent(context, MarriageAllownce.class));
                                    }else if(request_label.getText().equals("Educational Stipends (General)")){
                                        firebaseAnalytics = FirebaseAnalytics.getInstance(context);
                                        Bundle params = new Bundle();
                                        params.putInt("ButtonID",v.getId());
                                        btnNam = "Educational";
                                        setStatus("Educational");
                                        Log.d( "LOGZZZ: ", btnNam);
                                        firebaseAnalytics.logEvent(btnNam,params);
                                        context.startActivity(new Intent(context, EducationGeneral.class));
                                    }else if(request_label.getText().equals("Educational Stipends (Technical)")){
                                        firebaseAnalytics = FirebaseAnalytics.getInstance(context);
                                        Bundle params = new Bundle();
                                        params.putInt("ButtonID",v.getId());
                                        btnNam = "Educational";
                                        setStatus("Educational");
                                        Log.d( "LOGZZZ: ", btnNam);
                                        firebaseAnalytics.logEvent(btnNam,params);
                                        context.startActivity(new Intent(context, EducationProfessional.class));
                                    }else if(request_label.getText().equals("Deeni Madaris")) {
                                        firebaseAnalytics = FirebaseAnalytics.getInstance(context);
                                        Bundle params = new Bundle();
                                        params.putInt("ButtonID",v.getId());
                                        btnNam = "Madaris";
                                        setStatus("Madaris");
                                        Log.d( "LOGZZZ: ", btnNam);
                                        firebaseAnalytics.logEvent(btnNam,params);
                                        context.startActivity(new Intent(context, DeeniMadaris.class));
                                    }else if(request_label.getText().equals("Health Care")) {
                                        firebaseAnalytics = FirebaseAnalytics.getInstance(context);
                                        Bundle params = new Bundle();
                                        params.putInt("ButtonID",v.getId());
                                        btnNam = "Health";
                                        setStatus("Health");
                                        Log.d( "LOGZZZ: ", btnNam);
                                        firebaseAnalytics.logEvent(btnNam,params);
                                        context.startActivity(new Intent(context, HealthCare.class));
                                    }
                                }

                                @Override
                                public void onAnimationCancel(Animator animation) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animation) {

                                }
                            }).playOn(cardView);
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