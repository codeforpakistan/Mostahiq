package com.kpitb.zakatandmustahiq.Adapter;

import android.animation.Animator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.kpitb.zakatandmustahiq.Hospital_DetailPageUrdu;
import com.kpitb.zakatandmustahiq.Modal.SourceList;
import com.kpitb.zakatandmustahiq.R;

import java.util.ArrayList;

public class HospitalListAdapterUrdu extends RecyclerView.Adapter<HospitalListAdapterUrdu.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    int from;
    ArrayList<SourceList> data, filterlist;
    Dialog clickToCall;
    //ArrayList <SourceList> source;
    MediaPlayer mediaPlayer;

    public HospitalListAdapterUrdu(Context context, ArrayList<SourceList> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.filterlist = data;
        this.filterlist = new ArrayList<SourceList>();
        this.filterlist.addAll(this.data);
        mediaPlayer = MediaPlayer.create(context, R.raw.click_sound);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.hospital_page_item_urdu, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SourceList cases = filterlist.get(position);
        //SourceList sList = source.get(position);
        holder.bindData(cases);
    }

    @Override
    public int getItemCount() {
        return (null != filterlist ? filterlist.size(): 0);
    }

    public void filter(final String text) {

        // Searching could be complex..so we will dispatch it to a different thread...
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Clear the filter list
                filterlist.clear();
                // If there is no search value, then add all original list items to filter list
                if (TextUtils.isEmpty(text)) {
                    filterlist.addAll(data);
                } else {
                    // Iterate in the original List and add it to filter list...
                    for (SourceList item : data) {
                        if (item.getHos_name().toLowerCase().contains(text.toLowerCase())) {
                            // Adding Matched items
                            filterlist.add(item);
                        }
                    }
                }
                // Set on UI Thread
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Notify the List that the DataSet has changed...
                        notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private String request_status;
        private int status_icon;
        private ImageView status_image;
        private TextView hospitalName, phoneNumber, focalPersonName;
        private CardView card_hospital_item;
        //private ImageView location;

        SourceList dirObj;

        public ViewHolder(View v) {
            super(v);

            hospitalName =  itemView.findViewById(R.id.tvTitle);
            //status_image  = itemView.findViewById(R.id.ivImage);
            card_hospital_item = itemView.findViewById(R.id.card_hospital_item);
            //phoneNumber = itemView.findViewById(R.id.tvTitle3);
            //focalPersonName = itemView.findViewById(R.id.tvTitle2);
            //location = itemView.findViewById(R.id.location_img);
        }

        public void bindData(final SourceList c) {
            dirObj = c;

//            final String hospital = c.getId();
//            final String fperson = c.getName();
//            final String phoneNo = c.getDescription();

            hospitalName.setText(c.getHos_name_urdu());
            //phoneNumber.setText(c.getPhoneNumber());
            //focalPersonName.setText(c.getDzo_name());

            card_hospital_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
                                    //showClickToCallDialog(hospital,fperson,phoneNo);
                                    Intent intent = new Intent(context, Hospital_DetailPageUrdu.class);
/*                    intent.putExtra("HOSP_NAME",c.getHos_name());
                    intent.putExtra("DZO_NAME",c.getHos_focal_person());
                    intent.putExtra("DZO_PHONE",c.getFocal_person_phone());
                    intent.putExtra("HOS_LAT",c.getHos_district_latitude());
                    intent.putExtra("HOS_LONG",c.getHos_district_longitude());*/
                                    Bundle args = new Bundle();
                                    args.putString("HOSP_NAME",c.getHos_name());
                                    args.putString("HOSP_NAME_URD",c.getHos_name_urdu());
                                    args.putString("HOSP_NAME_PST",c.getHos_name_pashto());
                                    args.putString("DZO_NAME",c.getHos_focal_person());
                                    args.putString("DZO_NAME_URD",c.getHos_focal_person_urdu());
                                    args.putString("DZO_NAME_PST",c.getHos_focal_person_pashto());
                                    args.putString("DZO_PHONE",c.getFocal_person_phone());
                                    args.putString("HOS_LAT",c.getHos_district_latitude());
                                    args.putString("HOS_LONG", c.getHos_district_longitude());
                                    intent.putExtras(args);
                                    context.startActivity(intent);
                                }

                                @Override
                                public void onAnimationCancel(Animator animation) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animation) {

                                }
                            }).playOn(card_hospital_item);
                }
            });

        }
    }
}
