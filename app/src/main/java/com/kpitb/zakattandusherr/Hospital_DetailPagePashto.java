package com.kpitb.zakattandusherr;

import android.Manifest;
import android.animation.Animator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.kpitb.zakattandusherr.utility.CustomTextView;

public class Hospital_DetailPagePashto extends AppCompatActivity {

    TextView hospital_details;
    TextView hospital_name, hospital_focal_person_name, hospital_focal_person_contact;

    RelativeLayout call_layout, location_layout;

    String HospitalName;
    String DZOName;
    String DZOPhone;

    Dialog clickToCall;

    private MediaPlayer mediaPlayer;

    double lat, longi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital__detail_page_pashto);
        mediaPlayer = MediaPlayer.create(this, R.raw.click_sound);

        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initViews();

        //GETTING DATA
        //final Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        final String HospitalName = (String) bundle.get("HOSP_NAME");
        final String HospitalNameURD = (String) bundle.get("HOSP_NAME_URD");
        final String HospitalNamePST = (String) bundle.get("HOSP_NAME_PST");
        final String DZOName = (String) bundle.get("DZO_NAME");
        final String DZONameURD = (String) bundle.get("DZO_NAME_URD");
        final String DZONamePST = (String) bundle.get("DZO_NAME_PST");
        final String DZOPhone = (String) bundle.get("DZO_PHONE");
        final String HOSP_LATI = (String) bundle.get("HOS_LAT");
        final String HOSP_LONGI = (String) bundle.get("HOS_LONG");

        //MAKE UNDERLINE BELOW TEXT
        //hospital_details.setPaintFlags(hospital_details.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        //SETTING DATA
        hospital_name.setText(HospitalNamePST);
        hospital_focal_person_name.setText(DZONamePST);
        hospital_focal_person_contact.setText(DZOPhone);

        location_layout.setOnClickListener(new View.OnClickListener() {
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
                                Intent intent1 = new Intent(Hospital_DetailPagePashto.this,
                                        MapsLocation.class);
                                intent1.putExtra("myLat", HOSP_LATI);
                                intent1.putExtra("myLong", HOSP_LONGI);
                                startActivity(intent1);
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        }).playOn(location_layout);

               /* if (HospitalName.equals("IRNUM Hospital, Peshawar"))
                {
                    Intent intent = new Intent(Hospital_DetailPage.this, MapsLocation.class);
                  *//*  intent.putExtra("myLat","33.998574");
                    intent.putExtra("myLong","71.485219");*//*
                    intent.putExtra("myLat",HOSP_LATITUDE);
                    intent.putExtra("myLong",HOSP_LONGITUDE);
                    startActivity(intent);
                }
                else if (HospitalName.equals("Lady Reading Hospital (General), Peshawar")){
                    Intent intent = new Intent(Hospital_DetailPage.this, MapsLocation.class);
                    intent.putExtra("myLat","34.027148");
                    intent.putExtra("myLong","71.559042");
                    startActivity(intent);
                }
                else if (HospitalName.equals("Lady Reading Hospital (Cardiology), Peshawar")){
                    Intent intent = new Intent(Hospital_DetailPage.this, MapsLocation.class);
                    intent.putExtra("myLat","34.027148");
                    intent.putExtra("myLong","71.559042");
                    startActivity(intent);
                }
                else if (HospitalName.equals("Khyber Teaching Hospital Peshawar")){
                    Intent intent = new Intent(Hospital_DetailPage.this, MapsLocation.class);
                    intent.putExtra("myLat","33.996726");
                    intent.putExtra("myLong","71.486039");
                    startActivity(intent);
                }
                else if (HospitalName.equals("Hayatabad Medical Complex Peshawar")){
                    Intent intent = new Intent(Hospital_DetailPage.this, MapsLocation.class);
                    intent.putExtra("myLat","33.992268");
                    intent.putExtra("myLong","71.449696");
                    startActivity(intent);
                }
                else if (HospitalName.equals("Cath Lab HMC (Cardiology)  Peshawar")){
                    Intent intent = new Intent(Hospital_DetailPage.this, MapsLocation.class);
                    intent.putExtra("myLat","33.992268");
                    intent.putExtra("myLong","71.449696");
                    startActivity(intent);
                }
                else if (HospitalName.equals("Institute of Nuclear Medicine Oncology & Radiotherapy (INOR), Abbottabad")){
                    Intent intent = new Intent(Hospital_DetailPage.this, MapsLocation.class);
                    intent.putExtra("myLat","34.206371");
                    intent.putExtra("myLong","73.239231");
                    startActivity(intent);
                }
                else if (HospitalName.equals("Institute Of Kidney Diseases, Hayatabad")){
                    Intent intent = new Intent(Hospital_DetailPage.this, MapsLocation.class);
                    intent.putExtra("myLat","33.992180");
                    intent.putExtra("myLong","71.446605");
                    startActivity(intent);
                }
                else if (HospitalName.equals("Sarhad Jail Hospital for Psychiatric Diseases, Peshawar")){
                    Intent intent = new Intent(Hospital_DetailPage.this, MapsLocation.class);
                    intent.putExtra("myLat","34.012507");
                    intent.putExtra("myLong","71.562025");
                    startActivity(intent);
                }
                else if (HospitalName.equals("Khyber Eye Foundation Hospital Peshawar")){
                    Intent intent = new Intent(Hospital_DetailPage.this, MapsLocation.class);
                    intent.putExtra("myLat","34.009955");
                    intent.putExtra("myLong","71.610479");
                    startActivity(intent);
                }
                else if (HospitalName.equals("Bannu Institute of Nuclear Medicine Oncology & Radiotherapy (BINOR)")){
                    Intent intent = new Intent(Hospital_DetailPage.this, MapsLocation.class);
                    intent.putExtra("myLat","33.017140");
                    intent.putExtra("myLong","70.707367");
                    startActivity(intent);
                }
                else if (HospitalName.equals("Dera Ismail Khan Institute of Nuclear  Medicine & Radiotherapy (DINAR)")){
                    Intent intent = new Intent(Hospital_DetailPage.this, MapsLocation.class);
                    intent.putExtra("myLat","31.842374");
                    intent.putExtra("myLong","70.895234");
                    startActivity(intent);
                }
                else if (HospitalName.equals("ShoukatKhanam Hospital, Peshawar")){
                    Intent intent = new Intent(Hospital_DetailPage.this, MapsLocation.class);
                    intent.putExtra("myLat","33.992982");
                    intent.putExtra("myLong","71.440169");
                    startActivity(intent);
                }
                else if (HospitalName.equals("Swat Institute of Nuclear Medicine, Oncology & Radiology Swat (SINOR)")){
                    Intent intent = new Intent(Hospital_DetailPage.this, MapsLocation.class);
                    intent.putExtra("myLat","34.747327");
                    intent.putExtra("myLong","72.352642");
                    startActivity(intent);
                }
                else if (HospitalName.equals("Al-Khidmat Hospital Nishtarabad Peshawar")){
                    Intent intent = new Intent(Hospital_DetailPage.this, MapsLocation.class);
                    intent.putExtra("myLat","34.014652");
                    intent.putExtra("myLong","71.586809");
                    startActivity(intent);
                }
                else if (HospitalName.equals("Ayub Teaching Hospital, Abbottabad")){
                    Intent intent = new Intent(Hospital_DetailPage.this, MapsLocation.class);
                    intent.putExtra("myLat","34.203969");
                    intent.putExtra("myLong","73.236496");
                    startActivity(intent);
                }
                else if (HospitalName.equals("AIMS Diabetes Hospital & Research Center,Hayatabad Peshawar")){
                    Intent intent = new Intent(Hospital_DetailPage.this, MapsLocation.class);
                    intent.putExtra("myLat","33.989778");
                    intent.putExtra("myLong","71.435606");
                    startActivity(intent);
                }
                else if (HospitalName.equals("Frontier Foundation and Blood Transfusion, Peshawar")){
                    Intent intent = new Intent(Hospital_DetailPage.this, MapsLocation.class);
                    intent.putExtra("myLat","33.998125");
                    intent.putExtra("myLong","71.500478");
                    startActivity(intent);
                }
                else if (HospitalName.equals("Fatimid Foundation Blood Bank Peshawar")){
                    Intent intent = new Intent(Hospital_DetailPage.this, MapsLocation.class);
                    intent.putExtra("myLat","33.978208");
                    intent.putExtra("myLong","71.440006");
                    startActivity(intent);
                }
                else if (HospitalName.equals("Hamza Foundation and Blood Transfusion, Peshawar")){
                    Intent intent = new Intent(Hospital_DetailPage.this, MapsLocation.class);
                    intent.putExtra("myLat","33.994040");
                    intent.putExtra("myLong","71.503336");
                    startActivity(intent);
                }
                else if (HospitalName.equals("Kuwait Teaching Hospital Peshawar")){
                    Intent intent = new Intent(Hospital_DetailPage.this, MapsLocation.class);
                    intent.putExtra("myLat","34.003170");
                    intent.putExtra("myLong","71.500496");
                    startActivity(intent);
                }
                else if (HospitalName.equals("Pakistan Kidney Hospital Abbottabad")){
                    Intent intent = new Intent(Hospital_DetailPage.this, MapsLocation.class);
                    intent.putExtra("myLat","34.097246");
                    intent.putExtra("myLong","73.168761");
                    startActivity(intent);
                }*/
            }
        });

        call_layout.setOnClickListener(new View.OnClickListener() {
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
                                showClickToCallDialog(DZONamePST, DZOPhone);

                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        }).playOn(call_layout);
            }
        });
    }

    private void showClickToCallDialog(String dzoName, final String dzoPhone) {
        clickToCall = new Dialog(this, android.R.style.Theme_Black_NoTitleBar);
        clickToCall.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0,
                0, 0)));
        clickToCall.setContentView(R.layout.click_to_call_dialog_pashto);
        clickToCall.setCancelable(true);
        clickToCall.show();

        ImageView closedialog = clickToCall.findViewById(R.id.dialog_close);
        CustomTextView focalPersonName = clickToCall.findViewById(R.id.person_name);
        final CustomTextView contactNo = clickToCall.findViewById(R.id.person_no);
        RelativeLayout call = clickToCall.findViewById(R.id.btn_yes);
        RelativeLayout dont_call = clickToCall.findViewById(R.id.btn_no);

        focalPersonName.setText(dzoName);
        contactNo.setText(dzoPhone);

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
                    phoneCall(dzoPhone);
                } else {

                    if (ActivityCompat.checkSelfPermission(Hospital_DetailPagePashto.this,
                            Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {

                        phoneCall(dzoPhone);
                    } else {
                        final String[] PERMISSIONS_STORAGE = {Manifest.permission.CALL_PHONE};
                        //Asking request Permissions
                        ActivityCompat.requestPermissions((Activity) Hospital_DetailPagePashto.this,
                                PERMISSIONS_STORAGE, 9);
                    }
                }
            }
        });
    }

    private void phoneCall(String dzoPhone) {
        if (ActivityCompat.checkSelfPermission(Hospital_DetailPagePashto.this,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + dzoPhone));
            Hospital_DetailPagePashto.this.startActivity(callIntent);
        } else {
            Toast.makeText(Hospital_DetailPagePashto.this, "تاسو اجازه نه لرئ ۔", Toast.LENGTH_SHORT).show();
        }
    }


    private void initViews() {
        hospital_details = findViewById(R.id.hospital_details);
        hospital_name = findViewById(R.id.hospital_name);
        hospital_focal_person_name = findViewById(R.id.hospital_focal_person_name);
        hospital_focal_person_contact = findViewById(R.id.hospital_focal_person_contact);
        call_layout = findViewById(R.id.call_layout);
        location_layout = findViewById(R.id.location_layout);
    }
}