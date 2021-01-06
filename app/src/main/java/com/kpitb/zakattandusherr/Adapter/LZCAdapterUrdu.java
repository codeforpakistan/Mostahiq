package com.kpitb.zakattandusherr.Adapter;

import android.Manifest;
import android.animation.Animator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.kpitb.zakattandusherr.DetailsOfLZC;
import com.kpitb.zakattandusherr.DetailsOfLZC_Urdu;
import com.kpitb.zakattandusherr.Modal.LZCModel;
import com.kpitb.zakattandusherr.R;
import com.kpitb.zakattandusherr.utility.CustomTextView;

import java.util.ArrayList;

/**
 * Created by Manxoor on 30/10/2018.
 */

public class LZCAdapterUrdu extends RecyclerView.Adapter<LZCAdapterUrdu.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    int from;
    ArrayList<LZCModel> data, filterlist;
    Dialog clickToCall;
    private MediaPlayer mediaPlayer;

    // create constructor to initialize context and data sent from MainActivity
    public LZCAdapterUrdu(Context context, ArrayList<LZCModel> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.filterlist = new ArrayList<LZCModel>();
        this.filterlist.addAll(this.data);
        mediaPlayer = MediaPlayer.create(context,R.raw.click_sound);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private String request_status;
        private int status_icon;
        private ImageView status_image,clickToCall;
        private TextView request_label,districtDetails,dzo_name,phoneNumber;
        private CardView backgroud;

        LZCModel dirObj;

        public ViewHolder(View v) {
            super(v);

            request_label =  itemView.findViewById(R.id.tvTitle);
            status_image  = itemView.findViewById(R.id.ivImage);
            backgroud = itemView.findViewById(R.id.bg);
            districtDetails = itemView.findViewById(R.id.tvTitle3);
            dzo_name = itemView.findViewById(R.id.tvTitle2);
        }

        public void bindData(final LZCModel c) {
            dirObj = c;
            request_label.setText(c.getLzcNameUrd());
            //districtDetails.setText(c.getChairmanName());
            //dzo_name.setText(c.getTehsilName());
            //phoneNumber.setText(c.getPhoneNumber());

//            status_image.setImageResource(c.getStatus_icon());
//            backgroud.setBackgroundColor(ContextCompat.getColor(context, c.getColor()));

          /*  clickToCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mediaPlayer.start();
                    showClickToCallDialog(c.getChairmanName(),c.getPhoneNumber());
                }
            });*/

            backgroud.setOnClickListener(new View.OnClickListener() {
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
                                    Intent intent = new Intent(context, DetailsOfLZC_Urdu.class);
                                    intent.putExtra("LZCName",request_label.getText().toString());
                                    intent.putExtra("TehsilName",c.getTehsilNameUrd());
                                    intent.putExtra("ChairmanName",c.getLzcChairmanUrd());
                                    intent.putExtra("PhoneNo",c.getLzcPhone());
                                    context.startActivity(intent);
                                }

                                @Override
                                public void onAnimationCancel(Animator animation) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animation) {

                                }
                            }).playOn(backgroud);
                }
            });
        }
    }

    private void showClickToCallDialog(String fperson, final String phoneNo) {
        clickToCall = new Dialog(context, android.R.style.Theme_Black_NoTitleBar);
        clickToCall.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
        clickToCall.setContentView(R.layout.click_to_call_dialog);
        clickToCall.setCancelable(true);
        clickToCall.show();

        ImageView closedialog = clickToCall.findViewById(R.id.dialog_close);
        CustomTextView focalPersonName = clickToCall.findViewById(R.id.person_name);
        final CustomTextView contactNo = clickToCall.findViewById(R.id.person_no);
        RelativeLayout call = clickToCall.findViewById(R.id.btn_yes);
        RelativeLayout dont_call = clickToCall.findViewById(R.id.btn_no);

        focalPersonName.setText(fperson);
        contactNo.setText(phoneNo);

        closedialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
                clickToCall.dismiss();
            }
        });

        dont_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
                clickToCall.dismiss();
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
                if (Build.VERSION.SDK_INT < 23) {
                    phoneCall(phoneNo);
                }else {

                    if (ActivityCompat.checkSelfPermission(context,
                            Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {

                        phoneCall(phoneNo);
                    }else {
                        final String[] PERMISSIONS_STORAGE = {Manifest.permission.CALL_PHONE};
                        //Asking request Permissions
                        ActivityCompat.requestPermissions((Activity) context, PERMISSIONS_STORAGE, 9);
                    }
                }

            }
        });
    }

    private void phoneCall(String phoneNo) {
        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:"+phoneNo));
            context.startActivity(callIntent);
        }else{
            Toast.makeText(context, "You don't assign permission.", Toast.LENGTH_SHORT).show();
        }
    }

    // Inflate the layout when ViewHolder created
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.lzc_page_rv_item_urdu, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LZCModel cases =  filterlist.get(position);
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
                    for (LZCModel item : data) {
                        if (item.getLzcName().toLowerCase().contains(text.toLowerCase())) {
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
}