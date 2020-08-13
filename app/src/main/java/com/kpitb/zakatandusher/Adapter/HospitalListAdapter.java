package com.kpitb.zakatandusher.Adapter;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kpitb.zakatandusher.MapsLocation;
import com.kpitb.zakatandusher.Modal.DistrictPageModel;
import com.kpitb.zakatandusher.R;
import com.kpitb.zakatandusher.utility.CustomTextView;

import java.util.ArrayList;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Manxoor on 30/10/2018.
 */

public class HospitalListAdapter extends RecyclerView.Adapter<HospitalListAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    int from;
    ArrayList<DistrictPageModel> data, filterlist;
    Dialog clickToCall;

    // create constructor to initialize context and data sent from MainActivity
    public HospitalListAdapter(Context context, ArrayList<DistrictPageModel> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.filterlist = data;
        this.filterlist = new ArrayList<DistrictPageModel>();
        this.filterlist.addAll(this.data);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private String request_status;
        private int status_icon;
        private ImageView status_image;
        private TextView hospitalName, phoneNumber, focalPersonName;
        private ConstraintLayout backgroud;
        private TextView location;

        DistrictPageModel dirObj;

        public ViewHolder(View v) {
            super(v);

            hospitalName =  itemView.findViewById(R.id.tvTitle);
            status_image  = itemView.findViewById(R.id.ivImage);
            backgroud = itemView.findViewById(R.id.bg);
            phoneNumber = itemView.findViewById(R.id.tvTitle3);
            focalPersonName = itemView.findViewById(R.id.tvTitle2);
            location = itemView.findViewById(R.id.location);
        }

        public void bindData(final DistrictPageModel c) {
            dirObj = c;

            final String hospital = c.getDistrictTitle();
            final String fperson = c.getDzo_name();
            final String phoneNo = c.getPhoneNumber();

            hospitalName.setText(c.getDistrictTitle());
            phoneNumber.setText(c.getPhoneNumber());
            focalPersonName.setText(c.getDzo_name());

            location.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context, c.getDistrictTitle().equals("IRNUM Hospital, Peshawar"), Toast.LENGTH_SHORT).show();
                    if (c.getDistrictTitle().equals("IRNUM Hospital, Peshawar"))
                    {
                        Intent intent = new Intent(context, MapsLocation.class);
                        intent.putExtra("myLat","33.998735");
                        intent.putExtra("myLong","71.485564");
                        context.startActivity(intent);
                    }
                    else if (c.getDistrictTitle().equals("Lady Reading Hospital (General), Peshawar")){
                        Intent intent = new Intent(context, MapsLocation.class);
                        intent.putExtra("myLat","34.027339");
                        intent.putExtra("myLong","71.559010");
                        context.startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(context, MapsLocation.class);
                        context.startActivity(intent);
                    }
                }
            });

            backgroud.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showClickToCallDialog(hospital,fperson,phoneNo);
                }
            });

        }
    }

    private void showClickToCallDialog(String hospital, String fperson, final String phoneNo) {
        clickToCall = new Dialog(context, android.R.style.Theme_Black_NoTitleBar);
        clickToCall.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
        clickToCall.setContentView(R.layout.click_to_call_dialog);
        clickToCall.setCancelable(true);
        clickToCall.show();

        ImageView closedialog = clickToCall.findViewById(R.id.dialog_close);
        CustomTextView focalPersonName = clickToCall.findViewById(R.id.person_name);
        final CustomTextView contactNo = clickToCall.findViewById(R.id.person_no);
        RelativeLayout call = clickToCall.findViewById(R.id.btn_yes);

        focalPersonName.setText(fperson);
        contactNo.setText(phoneNo);

        closedialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickToCall.dismiss();
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

//                if (ActivityCompat.checkSelfPermission(context,
//                        Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
//                    //Creating intents for making a call
//                    Intent callIntent = new Intent(Intent.ACTION_CALL);
//                    callIntent.setData(Uri.parse(String.valueOf(contactNo)));
//                    context.startActivity(callIntent);
//
//                }else{
//                    Toast.makeText(context, "You don't assign permission.", Toast.LENGTH_SHORT).show();
//                }
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
        View view = inflater.inflate(R.layout.hospitals_page_rv_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DistrictPageModel cases = filterlist.get(position);
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
                    for (DistrictPageModel item : data) {
                        if (item.getDistrictTitle().toLowerCase().contains(text.toLowerCase())) {
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