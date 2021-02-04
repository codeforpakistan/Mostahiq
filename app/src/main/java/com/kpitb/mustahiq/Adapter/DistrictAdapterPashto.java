package com.kpitb.mustahiq.Adapter;

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
import android.os.Bundle;
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
import com.kpitb.mustahiq.DetailsOfDistrictsPashto;
import com.kpitb.mustahiq.Modal.DistrictModel;
import com.kpitb.mustahiq.R;
import com.kpitb.mustahiq.utility.CustomTextView;

import java.util.ArrayList;

public class DistrictAdapterPashto extends RecyclerView.Adapter<DistrictAdapterPashto.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    int from;
    ArrayList<DistrictModel> data, filterlist;
    private Dialog clickToCall;
    private MediaPlayer mediaPlayer;

    // create constructor to initialize context and data sent from MainActivity
    public DistrictAdapterPashto(Context context, ArrayList<DistrictModel> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.filterlist = new ArrayList<DistrictModel>();
        this.filterlist.addAll(this.data);
        mediaPlayer = MediaPlayer.create(context, R.raw.click_sound);
//        this.filterlist = data;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private String request_status;
        private int status_icon;
        private ImageView status_image,CallDZO,locationIcon;
        private TextView request_label,districtDetails,dzo_name,dzoPhoneNo;
        private CardView backgroud;

        DistrictModel dirObj;

        public ViewHolder(View v) {
            super(v);

            request_label =  itemView.findViewById(R.id.tvTitle);
            status_image  = itemView.findViewById(R.id.ivImage);
            backgroud = itemView.findViewById(R.id.bg);
            districtDetails = itemView.findViewById(R.id.tvTitle3);
            dzo_name = itemView.findViewById(R.id.tvTitle2);
         /*   dzoPhoneNo = itemView.findViewById(R.id.dzo_phoneNo);
            CallDZO = itemView.findViewById(R.id.call_dzo);
            locationIcon = itemView.findViewById(R.id.location_icon);*/

        }

        public void bindData(final DistrictModel c) {
            dirObj = c;
            request_label.setText(c.getD_name_pashto());
            //districtDetails.setText(c.getNoOfLzcs());
            //dzo_name.setText(c.getDzo_name());
            //dzoPhoneNo.setText(c.getPhoneNumber());
//            status_image.setImageResource(c.getStatus_icon());
            //backgroud.setBackgroundColor(ContextCompat.getColor(context, c.getColor()));

        /*    CallDZO.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mediaPlayer.start();
                    showClickToCallDialog(c.getDzo_name(),c.getPhoneNumber());
                }
            });*/

         /*   locationIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.start();
                    if (request_label.getText().equals("Mansehra"))
                    {
                        Intent intent = new Intent(context, MapsLocation.class);
                        intent.putExtra("myLat","34.335667");
                        intent.putExtra("myLong","73.205500");
                        context.startActivity(intent);
                    }
                    else if (request_label.getText().equals("Buner"))
                    {
                        Intent intent = new Intent(context, MapsLocation.class);
                        intent.putExtra("myLat","34.502774");
                        intent.putExtra("myLong","72.445641");
                        context.startActivity(intent);
                    }
                    else if (request_label.getText().equals("Malakand"))
                    {
                        Intent intent = new Intent(context, MapsLocation.class);
                        intent.putExtra("myLat","34.607194");
                        intent.putExtra("myLong","71.954694");
                        context.startActivity(intent);
                    }
                    else if (request_label.getText().equals("Abbottabad"))
                    {
                        Intent intent = new Intent(context, MapsLocation.class);
                        intent.putExtra("myLat","34.149985");
                        intent.putExtra("myLong","73.211023");
                        context.startActivity(intent);
                    }
                    else if (request_label.getText().equals("Dir (Lower)"))
                    {
                        Intent intent = new Intent(context, MapsLocation.class);
                        intent.putExtra("myLat","34.831261");
                        intent.putExtra("myLong","71.830852");
                        context.startActivity(intent);
                    }
                    else if (request_label.getText().equals("Dir (Upper)"))
                    {
                        Intent intent = new Intent(context, MapsLocation.class);
                        intent.putExtra("myLat","35.199216");
                        intent.putExtra("myLong","71.872485");
                        context.startActivity(intent);
                    }
                    else if (request_label.getText().equals("Mardan"))
                    {
                        Intent intent = new Intent(context, MapsLocation.class);
                        intent.putExtra("myLat","34.192955");
                        intent.putExtra("myLong","72.039185");
                        context.startActivity(intent);
                    }
                    else if (request_label.getText().equals("Lakki Marwat"))
                    {
                        Intent intent = new Intent(context, MapsLocation.class);
                        intent.putExtra("myLat","32.609471");
                        intent.putExtra("myLong","70.899913");
                        context.startActivity(intent);
                    }
                    else if (request_label.getText().equals("Bajaur"))
                    {
                        Intent intent = new Intent(context, MapsLocation.class);
                        intent.putExtra("myLat","34.729195");
                        intent.putExtra("myLong","71.511625");
                        context.startActivity(intent);
                    }
                    else if (request_label.getText().equals("Karak"))
                    {
                        Intent intent = new Intent(context, MapsLocation.class);
                        intent.putExtra("myLat","33.108551");
                        intent.putExtra("myLong","71.080082");
                        context.startActivity(intent);
                    }
                    else if (request_label.getText().equals("Hangu"))
                    {
                        Intent intent = new Intent(context, MapsLocation.class);
                        intent.putExtra("myLat","33.511700");
                        intent.putExtra("myLong","71.040305");
                        context.startActivity(intent);
                    }
                    else if (request_label.getText().equals("D. I. Khan"))
                    {
                        Intent intent = new Intent(context, MapsLocation.class);
                        intent.putExtra("myLat","31.820409");
                        intent.putExtra("myLong","70.909108");
                        context.startActivity(intent);
                    }
                    else if (request_label.getText().equals("Shangla"))
                    {
                        Intent intent = new Intent(context, MapsLocation.class);
                        intent.putExtra("myLat","34.923448");
                        intent.putExtra("myLong","72.634200");
                        context.startActivity(intent);
                    }
                    else if (request_label.getText().equals("Mohmand"))
                    {
                        Intent intent = new Intent(context, MapsLocation.class);
                        intent.putExtra("myLat","34.320128");
                        intent.putExtra("myLong","71.409913");
                        context.startActivity(intent);
                    }
                    else if (request_label.getText().equals("Charsadda"))
                    {
                        Intent intent = new Intent(context, MapsLocation.class);
                        intent.putExtra("myLat","34.155433");
                        intent.putExtra("myLong","71.742109");
                        context.startActivity(intent);
                    }
                    else if (request_label.getText().equals("North Waziristan"))
                    {
                        Intent intent = new Intent(context, MapsLocation.class);
                        intent.putExtra("myLat","33.001261");
                        intent.putExtra("myLong","70.069174");
                        context.startActivity(intent);
                    }
                    else if (request_label.getText().equals("Chitral"))
                    {
                        Intent intent = new Intent(context, MapsLocation.class);
                        intent.putExtra("myLat","35.852444");
                        intent.putExtra("myLong","71.785306");
                        context.startActivity(intent);
                    }
                    else if (request_label.getText().equals("Tank"))
                    {
                        Intent intent = new Intent(context, MapsLocation.class);
                        intent.putExtra("myLat","32.212193");
                        intent.putExtra("myLong","70.382055");
                        context.startActivity(intent);
                    }
                    else if (request_label.getText().equals("Swat"))
                    {
                        Intent intent = new Intent(context, MapsLocation.class);
                        intent.putExtra("myLat","34.764796");
                        intent.putExtra("myLong","72.361187");
                        context.startActivity(intent);
                    }
                    else if (request_label.getText().equals("Khyber"))
                    {
                        Intent intent = new Intent(context, MapsLocation.class);
                        intent.putExtra("myLat","34.001500");
                        intent.putExtra("myLong","71.379889");
                        context.startActivity(intent);
                    }
                    else if (request_label.getText().equals("Kurram"))
                    {
                        Intent intent = new Intent(context, MapsLocation.class);
                        intent.putExtra("myLat","33.706583");
                        intent.putExtra("myLong","70.351083");
                        context.startActivity(intent);
                    }
                    else if (request_label.getText().equals("Peshawar"))
                    {
                        Intent intent = new Intent(context, MapsLocation.class);
                        intent.putExtra("myLat","34.013900");
                        intent.putExtra("myLong","71.590778");
                        context.startActivity(intent);
                    }
                    else if (request_label.getText().equals("Orakzai"))
                    {
                        Intent intent = new Intent(context, MapsLocation.class);
                        intent.putExtra("myLat","33.551847");
                        intent.putExtra("myLong","71.112711");
                        context.startActivity(intent);
                    }
                    else {

                    }
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
                                    Intent intent = new Intent(context, DetailsOfDistrictsPashto.class);
                                    Bundle args = new Bundle();
                                    args.putString("D_ID",c.getD_id());
                                    args.putString("D_NAME",c.getD_name());
                                    args.putString("D_NAME_URD",c.getD_name_urdu());
                                    args.putString("D_NAME_PSH",c.getD_name_pashto());
                                    args.putString("DZO_NAME",c.getD_officer_name());
                                    args.putString("DZO_NAME_URD",c.getD_officer_urdu());
                                    args.putString("DZO_NAME_PSH",c.getD_officer_pashto());
                                    args.putString("D_NO_OF_LZC",c.getD_no_lzc());
                                    args.putString("DZO_NUM",c.getD_officer_phone());
                                    args.putString("D_CHAIRMAN_NUM",c.getD_chairman_phone());
                                    args.putString("D_CHAIRMAN",c.getD_chairman_name());
                                    args.putString("D_CHAIRMAN_URD",c.getD_chairman_name_urdu());
                                    args.putString("D_CHAIRMAN_PSH",c.getD_chairman_name_pashto());
                                    args.putString("D_LAT", c.getD_latitude());
                                    args.putString("D_LONG", c.getD_longitude());
                                    intent.putExtras(args);
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
    /*        backgroud.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.start();
                    if(request_label.getText().toString().contains("Buner")||
                            request_label.getText().toString().contains("Mansehra") ||
                            request_label.getText().toString().contains("Malakand") ||
                            request_label.getText().toString().contains("Abbottabad")||
                            request_label.getText().toString().contains("Dir (Lower)")||
                            request_label.getText().toString().contains("Dir (Upper)")||
                            request_label.getText().toString().contains("Mardan")||
                            request_label.getText().toString().contains("Lakki Marwat")||
                            request_label.getText().toString().contains("Bajaur")||
                            request_label.getText().toString().contains("Karak")||
                            request_label.getText().toString().contains("Hangu")||
                            request_label.getText().toString().contains("D. I. Khan")||
                            request_label.getText().toString().contains("Shangla")||
                            request_label.getText().toString().contains("Mohmand")||
                            request_label.getText().toString().contains("Charsadda")||
                            request_label.getText().toString().contains("North Waziristan")||
                            request_label.getText().toString().contains("Chitral")||
                            request_label.getText().toString().contains("Tank")||
                            request_label.getText().toString().contains("Swat")||
                            request_label.getText().toString().contains("Khyber")||
                            request_label.getText().toString().contains("Kurram")||
                            request_label.getText().toString().contains("Peshawar")||
                            request_label.getText().toString().contains("Orakzai")){
                    Intent intent = new Intent(context, DetailsOfDistricts.class);
                    intent.putExtra("District",request_label.getText().toString());
                    intent.putExtra("DZO",c.getDzo_name());
                    intent.putExtra("DZO_PHONE_NUMBER",c.getPhoneNumber());
                    intent.putExtra("NUM_OF_DLZ",c.getNoOfLzcs());
                        if (request_label.getText().equals("Mansehra"))
                        {
                            intent.putExtra("myLat","34.335667");
                            intent.putExtra("myLong","73.205500");
                        }
                        else if (request_label.getText().equals("Buner"))
                        {
                            intent.putExtra("myLat","34.502774");
                            intent.putExtra("myLong","72.445641");
                        }
                        else if (request_label.getText().equals("Malakand"))
                        {
                            intent.putExtra("myLat","34.607194");
                            intent.putExtra("myLong","71.954694");
                        }
                        else if (request_label.getText().equals("Abbottabad"))
                        {
                            intent.putExtra("myLat","34.149985");
                            intent.putExtra("myLong","73.211023");
                        }
                        else if (request_label.getText().equals("Dir (Lower)"))
                        {
                            intent.putExtra("myLat","34.831261");
                            intent.putExtra("myLong","71.830852");
                        }
                        else if (request_label.getText().equals("Dir (Upper)"))
                        {
                            intent.putExtra("myLat","35.199216");
                            intent.putExtra("myLong","71.872485");
                        }
                        else if (request_label.getText().equals("Mardan"))
                        {
                            intent.putExtra("myLat","34.192955");
                            intent.putExtra("myLong","72.039185");
                        }
                        else if (request_label.getText().equals("Lakki Marwat"))
                        {
                            intent.putExtra("myLat","32.609471");
                            intent.putExtra("myLong","70.899913");
                        }
                        else if (request_label.getText().equals("Bajaur"))
                        {
                            intent.putExtra("myLat","34.729195");
                            intent.putExtra("myLong","71.511625");
                        }
                        else if (request_label.getText().equals("Karak"))
                        {
                            intent.putExtra("myLat","33.108551");
                            intent.putExtra("myLong","71.080082");
                        }
                        else if (request_label.getText().equals("Hangu"))
                        {
                            intent.putExtra("myLat","33.511700");
                            intent.putExtra("myLong","71.040305");
                        }
                        else if (request_label.getText().equals("D. I. Khan"))
                        {
                            intent.putExtra("myLat","31.820409");
                            intent.putExtra("myLong","70.909108");
                        }
                        else if (request_label.getText().equals("Shangla"))
                        {
                            intent.putExtra("myLat","34.923448");
                            intent.putExtra("myLong","72.634200");
                        }
                        else if (request_label.getText().equals("Mohmand"))
                        {
                            intent.putExtra("myLat","34.320128");
                            intent.putExtra("myLong","71.409913");
                        }
                        else if (request_label.getText().equals("Charsadda"))
                        {
                            intent.putExtra("myLat","34.155433");
                            intent.putExtra("myLong","71.742109");
                        }
                        else if (request_label.getText().equals("North Waziristan"))
                        {
                            intent.putExtra("myLat","33.001261");
                            intent.putExtra("myLong","70.069174");
                        }
                        else if (request_label.getText().equals("Chitral"))
                        {
                            intent.putExtra("myLat","35.852444");
                            intent.putExtra("myLong","71.785306");
                        }
                        else if (request_label.getText().equals("Tank"))
                        {
                            intent.putExtra("myLat","32.212193");
                            intent.putExtra("myLong","70.382055");
                        }
                        else if (request_label.getText().equals("Swat"))
                        {
                            intent.putExtra("myLat","34.764796");
                            intent.putExtra("myLong","72.361187");
                        }
                        else if (request_label.getText().equals("Khyber"))
                        {
                            intent.putExtra("myLat","34.001500");
                            intent.putExtra("myLong","71.379889");
                        }
                        else if (request_label.getText().equals("Kurram"))
                        {
                            intent.putExtra("myLat","33.706583");
                            intent.putExtra("myLong","70.351083");
                        }
                        else if (request_label.getText().equals("Peshawar"))
                        {
                            intent.putExtra("myLat","34.013900");
                            intent.putExtra("myLong","71.590778");
                        }
                        else if (request_label.getText().equals("Orakzai"))
                        {
                            intent.putExtra("myLat","33.551847");
                            intent.putExtra("myLong","71.112711");
                        }
                    context.startActivity(intent);
                    }
                    else{
                        Toast.makeText(context, "No Listed Records yet ", Toast.LENGTH_SHORT).show();
                    }
                }
            });*/
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
            public void onClick(View v) {
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
        View view = inflater.inflate(R.layout.district_page_rv_item_urdu, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DistrictModel cases = filterlist.get(position);
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
                    for (DistrictModel item : data) {
                        if (item.getD_name().toLowerCase().contains(text.toLowerCase())) {
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
