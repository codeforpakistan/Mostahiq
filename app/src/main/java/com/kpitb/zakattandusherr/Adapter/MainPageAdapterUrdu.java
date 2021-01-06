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

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.kpitb.zakattandusherr.DeeniMadarisPashto;
import com.kpitb.zakattandusherr.DeeniMadarisUrdu;
import com.kpitb.zakattandusherr.EducationGeneralPashto;
import com.kpitb.zakattandusherr.EducationGeneralUrdu;
import com.kpitb.zakattandusherr.EducationProfessionalPashto;
import com.kpitb.zakattandusherr.EducationProfessionalUrdu;
import com.kpitb.zakattandusherr.GuzaraAllowancePashto;
import com.kpitb.zakattandusherr.GuzaraAllownceUrdu;
import com.kpitb.zakattandusherr.HealthCarePashto;
import com.kpitb.zakattandusherr.HealthCareUrdu;
import com.kpitb.zakattandusherr.Marriage_Allowance_Pashto;
import com.kpitb.zakattandusherr.Marriage_Allowance_Urdu;
import com.kpitb.zakattandusherr.Modal.HomePageModel;
import com.kpitb.zakattandusherr.R;

import java.util.ArrayList;

public class MainPageAdapterUrdu extends RecyclerView.Adapter<MainPageAdapterUrdu.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    int from;
    ArrayList<HomePageModel> data, filterlist;
    String btnNam, values;
    private MediaPlayer mediaPlayer;

    // create constructor to initialize context and data sent from MainActivity
    public MainPageAdapterUrdu(Context context, ArrayList<HomePageModel> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
        mediaPlayer = MediaPlayer.create(context,R.raw.click_sound);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
                                    if(request_label.getText().equals("گزارا الاؤنس")){
                                        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(context);
                                        Bundle params = new Bundle();
                                        params.putInt("ButtonID",v.getId());
                                        btnNam = "گزارا";
                                        setStatus("گزارا");
                                        Log.d( "LOGZZZ: ", btnNam);
                                        firebaseAnalytics.logEvent(btnNam,params);
                                        context.startActivity(new Intent(context, GuzaraAllownceUrdu.class));
                                    }else if(request_label.getText().equals("شادی الاؤنس")){
                                        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(context);
                                        Bundle params = new Bundle();
                                        params.putInt("ButtonID",v.getId());
                                        btnNam = "گزارا";
                                        setStatus("گزارا");
                                        Log.d( "LOGZZZ: ", btnNam);
                                        firebaseAnalytics.logEvent(btnNam,params);
                                        context.startActivity(new Intent(context, Marriage_Allowance_Urdu.class));
                                    }else if(request_label.getText().equals("تعلیمی وظائف (عام)")){
                                        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(context);
                                        Bundle params = new Bundle();
                                        params.putInt("ButtonID",v.getId());
                                        btnNam = "تعلیمی";
                                        setStatus("تعلیمی");
                                        Log.d( "LOGZZZ: ", btnNam);
                                        firebaseAnalytics.logEvent(btnNam,params);
                                        context.startActivity(new Intent(context, EducationGeneralUrdu.class));
                                    }else if(request_label.getText().equals("تعلیمی وظیفہ (ٹیکنیکل)")){
                                        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(context);
                                        Bundle params = new Bundle();
                                        params.putInt("ButtonID",v.getId());
                                        btnNam = "تعلیمی";
                                        setStatus("تعلیمی");
                                        Log.d( "LOGZZZ: ", btnNam);
                                        firebaseAnalytics.logEvent(btnNam,params);
                                        context.startActivity(new Intent(context, EducationProfessionalUrdu.class));
                                    }else if(request_label.getText().equals("دینی مدارس")) {
                                        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(context);
                                        Bundle params = new Bundle();
                                        params.putInt("ButtonID",v.getId());
                                        btnNam = "مدارس";
                                        setStatus("مدارس");
                                        Log.d( "LOGZZZ: ", btnNam);
                                        firebaseAnalytics.logEvent(btnNam,params);
                                        context.startActivity(new Intent(context, DeeniMadarisUrdu.class));
                                    }else if(request_label.getText().equals("صحت کی دیکھ بال")) {
                                        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(context);
                                        Bundle params = new Bundle();
                                        params.putInt("ButtonID",v.getId());
                                        btnNam = "صحت";
                                        setStatus("صحت");
                                        Log.d( "LOGZZZ: ", btnNam);
                                        firebaseAnalytics.logEvent(btnNam,params);
                                        context.startActivity(new Intent(context, HealthCareUrdu.class));
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
}
