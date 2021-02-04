package com.kpitb.mustahiq.Adapter;

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
import com.kpitb.mustahiq.DistrictsActivityUrdu;
import com.kpitb.mustahiq.Modal.MainPageModelUrdu;
import com.kpitb.mustahiq.ProvincialActivityUrdu;
import com.kpitb.mustahiq.R;
import com.kpitb.mustahiq.ZakatSchemes;

import java.util.ArrayList;

/**
 * Created by Manxoor on 30/10/2018.
 */

public class MainAdapterUrdu extends RecyclerView.Adapter<MainAdapterUrdu.ViewHolder> {
    private Context contexts;
    private LayoutInflater inflater;
    int from;
    ArrayList<MainPageModelUrdu> data, filterlist;
    String btnNam, values;
    private MediaPlayer mediaPlayer;

    // create constructor to initialize context and data sent from MainActivity
    public MainAdapterUrdu(Context context, ArrayList<MainPageModelUrdu> data) {
        this.contexts = context;
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
        private CardView cardView;

        MainPageModelUrdu dirObj;

        public ViewHolder(View v) {
            super(v);

            request_label =  itemView.findViewById(R.id.tvTitle);
            request_label_urdu =  itemView.findViewById(R.id.tvTitleUrdu);
            status_image  = itemView.findViewById(R.id.ivImage);
            backgroud = itemView.findViewById(R.id.bg);
            cardView = itemView.findViewById(R.id.cardview);
        }

        public void bindData(MainPageModelUrdu c) {
            dirObj = c;
            request_label.setText(c.getRequestStatusUrdu());
            //request_label_urdu.setText(c.getRequestStatusUrdu());
            status_image.setImageResource(c.getStatus_icon());
            backgroud.setBackgroundColor(ContextCompat.getColor(contexts, c.getColor()));

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
                                    if(request_label.getText().equals("زکوٰۃ اسکیمیں")){
                                        Intent i = new Intent(contexts,ZakatSchemes.class);
                                        i.putExtra("LANG",lable);
                                        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(contexts);
                                        Bundle params = new Bundle();
                                        params.putInt("ButtonID",v.getId());
                                        btnNam = "زکوٰۃ اسکیمیں";
                                        setStatus("زکوٰۃ اسکیمیں");
                                        Log.d( "LOGZZZ: ", btnNam);
                                        firebaseAnalytics.logEvent(btnNam,params);
                                        contexts.startActivity(i);
                                    }else if(request_label.getText().equals("ضلعی زکوٰۃ دفاتر")){
                                        Intent i = new Intent(contexts, DistrictsActivityUrdu.class);
                                        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(contexts);
                                        Bundle params = new Bundle();
                                        params.putInt("ButtonID",v.getId());
                                        btnNam = "اضلاع";
                                        setStatus("اضلاع");
                                        Log.d( "LOGZZZ: ", btnNam);
                                        firebaseAnalytics.logEvent(btnNam,params);
                                        contexts.startActivity(i);
                                    }else if(request_label.getText().equals("صوبائی ہسپتال")){
                                        Intent i = new Intent(contexts, ProvincialActivityUrdu.class);
                                        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(contexts);
                                        Bundle params = new Bundle();
                                        params.putInt("ButtonID",v.getId());
                                        btnNam = "صوبائی ہسپتال";
                                        setStatus("صوبائی ہسپتال");
                                        Log.d( "LOGZZZ: ", btnNam);
                                        firebaseAnalytics.logEvent(btnNam,params);
                                        contexts.startActivity(i);
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
        View view = inflater.inflate(R.layout.location_page_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MainPageModelUrdu cases = data.get(position);
        holder.bindData(cases);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}