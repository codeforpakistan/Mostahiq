package com.kpitb.mustahiq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

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

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.kpitb.mustahiq.utility.CustomTextView;
import com.kpitb.mustahiq.utility.CustomTextViewForMainPage;

public class DetailsOfDistrictsUrdu extends AppCompatActivity {
    TextView district_details;
    CustomTextViewForMainPage district_name, dzo_name, dzo_contact_num, dlzcs_num,dzc,dzc_no;

    private RelativeLayout dlzcs_layout_btn, call_layout,location_layout;

    String DestrictID;
    String DestrictName;
    String DestrictNameUrd;
    String DestrictNamePsh;
    String DZOName;
    String DZONameUrd;
    String DZONamePsh;
    String DZOPhone;
    String NoOfDLZC;
    String ChairMan;
    String ChairMan_Urd;
    String ChairMan_Psh;
    String ChairManNumber;
    String D_LATI;
    String D_LONGI;

    Dialog clickToCall;

    private MediaPlayer mediaPlayer;
    private View separator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_of_districts_urdu);

        mediaPlayer = MediaPlayer.create(this,R.raw.click_sound);

        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initViews();

        //GETTING DATA
        //final Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        DestrictID = (String) bundle.get("D_ID");
        DestrictName = (String) bundle.get("D_NAME");
        DestrictNameUrd = (String) bundle.get("D_NAME_URD");
        DestrictNamePsh = (String) bundle.get("D_NAME_PSH");
        DZOName = (String) bundle.get("DZO_NAME");
        DZONameUrd = (String) bundle.get("DZO_NAME_URD");
        DZONamePsh = (String) bundle.get("DZO_NAME_PSH");
        NoOfDLZC = (String) bundle.get("D_NO_OF_LZC");
        DZOPhone = (String) bundle.get("DZO_NUM");
        ChairManNumber = (String) bundle.get("D_CHAIRMAN_NUM");
        ChairMan = (String) bundle.get("D_CHAIRMAN");
        ChairMan_Urd = (String) bundle.get("D_CHAIRMAN_URD");
        ChairMan_Psh = (String) bundle.get("D_CHAIRMAN_PSH");
        D_LATI = (String) bundle.get("D_LAT");
        D_LONGI = (String) bundle.get("D_LONG");

        //MAKE UNDERLINE BELOW TEXT
        //district_details.setPaintFlags(district_details.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        //district_name.setPaintFlags(district_name.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        //SETTING DATA
        district_name.setText(DestrictNameUrd);
        dzo_name.setText(DZONameUrd);
        dzo_contact_num.setText(DZOPhone);
        dlzcs_num.setText(NoOfDLZC);
        dzc.setText(ChairMan_Urd);
        dzc_no.setText(ChairManNumber);

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
                                Intent intent1 = new Intent(DetailsOfDistrictsUrdu.this,MapsLocation.class);
                                intent1.putExtra("myLat",D_LATI);
                                intent1.putExtra("myLong",D_LONGI);
                                startActivity(intent1);
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        }).playOn(location_layout);
               /* if (DestrictName.equals("Mansehra"))
                {
                    Intent intent = new Intent(DetailsOfDistricts.this, MapsLocation.class);
                    intent.putExtra("myLat","34.335667");
                    intent.putExtra("myLong","73.205500");
                    startActivity(intent);
                }
                else if (DestrictName.equals("Buner"))
                {
                    Intent intent = new Intent(DetailsOfDistricts.this, MapsLocation.class);
                    intent.putExtra("myLat","34.502774");
                    intent.putExtra("myLong","72.445641");
                    startActivity(intent);
                }
                else if (DestrictName.equals("Malakand"))
                {
                    Intent intent = new Intent(DetailsOfDistricts.this, MapsLocation.class);
                    intent.putExtra("myLat","34.607194");
                    intent.putExtra("myLong","71.954694");
                    startActivity(intent);
                }
                else if (DestrictName.equals("Abbottabad"))
                {
                    Intent intent = new Intent(DetailsOfDistricts.this, MapsLocation.class);
                    intent.putExtra("myLat","34.149985");
                    intent.putExtra("myLong","73.211023");
                    startActivity(intent);
                }
                else if (DestrictName.equals("Dir (Lower)"))
                {
                    Intent intent = new Intent(DetailsOfDistricts.this, MapsLocation.class);
                    intent.putExtra("myLat","34.831261");
                    intent.putExtra("myLong","71.830852");
                    startActivity(intent);
                }
                else if (DestrictName.equals("Dir (Upper)"))
                {
                    Intent intent = new Intent(DetailsOfDistricts.this, MapsLocation.class);
                    intent.putExtra("myLat","35.199216");
                    intent.putExtra("myLong","71.872485");
                    startActivity(intent);
                }
                else if (DestrictName.equals("Mardan"))
                {
                    Intent intent = new Intent(DetailsOfDistricts.this, MapsLocation.class);
                    intent.putExtra("myLat","34.192955");
                    intent.putExtra("myLong","72.039185");
                    startActivity(intent);
                }
                else if (DestrictName.equals("Lakki Marwat"))
                {
                    Intent intent = new Intent(DetailsOfDistricts.this, MapsLocation.class);
                    intent.putExtra("myLat","32.609471");
                    intent.putExtra("myLong","70.899913");
                    startActivity(intent);
                }
                else if (DestrictName.equals("Bajaur"))
                {
                    Intent intent = new Intent(DetailsOfDistricts.this, MapsLocation.class);
                    intent.putExtra("myLat","34.729195");
                    intent.putExtra("myLong","71.511625");
                    startActivity(intent);
                }
                else if (DestrictName.equals("Karak"))
                {
                    Intent intent = new Intent(DetailsOfDistricts.this, MapsLocation.class);
                    intent.putExtra("myLat","33.108551");
                    intent.putExtra("myLong","71.080082");
                    startActivity(intent);
                }
                else if (DestrictName.equals("Hangu"))
                {
                    Intent intent = new Intent(DetailsOfDistricts.this, MapsLocation.class);
                    intent.putExtra("myLat","33.511700");
                    intent.putExtra("myLong","71.040305");
                    startActivity(intent);
                }
                else if (DestrictName.equals("D. I. Khan"))
                {
                    Intent intent = new Intent(DetailsOfDistricts.this, MapsLocation.class);
                    intent.putExtra("myLat","31.820409");
                    intent.putExtra("myLong","70.909108");
                    startActivity(intent);
                }
                else if (DestrictName.equals("Shangla"))
                {
                    Intent intent = new Intent(DetailsOfDistricts.this, MapsLocation.class);
                    intent.putExtra("myLat","34.923448");
                    intent.putExtra("myLong","72.634200");
                    startActivity(intent);
                }
                else if (DestrictName.equals("Mohmand"))
                {
                    Intent intent = new Intent(DetailsOfDistricts.this, MapsLocation.class);
                    intent.putExtra("myLat","34.320128");
                    intent.putExtra("myLong","71.409913");
                    startActivity(intent);
                }
                else if (DestrictName.equals("Charsadda"))
                {
                    Intent intent = new Intent(DetailsOfDistricts.this, MapsLocation.class);
                    intent.putExtra("myLat","34.155433");
                    intent.putExtra("myLong","71.742109");
                    startActivity(intent);
                }
                else if (DestrictName.equals("North Waziristan"))
                {
                    Intent intent = new Intent(DetailsOfDistricts.this, MapsLocation.class);
                    intent.putExtra("myLat","33.001261");
                    intent.putExtra("myLong","70.069174");
                    startActivity(intent);
                }
                else if (DestrictName.equals("Chitral"))
                {
                    Intent intent = new Intent(DetailsOfDistricts.this, MapsLocation.class);
                    intent.putExtra("myLat","35.852444");
                    intent.putExtra("myLong","71.785306");
                    startActivity(intent);
                }
                else if (DestrictName.equals("Tank"))
                {
                    Intent intent = new Intent(DetailsOfDistricts.this, MapsLocation.class);
                    intent.putExtra("myLat","32.212193");
                    intent.putExtra("myLong","70.382055");
                    startActivity(intent);
                }
                else if (DestrictName.equals("Swat"))
                {
                    Intent intent = new Intent(DetailsOfDistricts.this, MapsLocation.class);
                    intent.putExtra("myLat","34.764796");
                    intent.putExtra("myLong","72.361187");
                    startActivity(intent);
                }
                else if (DestrictName.equals("Khyber"))
                {
                    Intent intent = new Intent(DetailsOfDistricts.this, MapsLocation.class);
                    intent.putExtra("myLat","34.001500");
                    intent.putExtra("myLong","71.379889");
                    startActivity(intent);
                }
                else if (DestrictName.equals("Kurram"))
                {
                    Intent intent = new Intent(DetailsOfDistricts.this, MapsLocation.class);
                    intent.putExtra("myLat","33.706583");
                    intent.putExtra("myLong","70.351083");
                    startActivity(intent);
                }
                else if (DestrictName.equals("Peshawar"))
                {
                    Intent intent = new Intent(DetailsOfDistricts.this, MapsLocation.class);
                    intent.putExtra("myLat","34.013900");
                    intent.putExtra("myLong","71.590778");
                    startActivity(intent);
                }
                else if (DestrictName.equals("Orakzai"))
                {
                    Intent intent = new Intent(DetailsOfDistricts.this, MapsLocation.class);
                    intent.putExtra("myLat","33.551847");
                    intent.putExtra("myLong","71.112711");
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
                                showClickToCallDialog(DZONameUrd,DZOPhone);
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

        dlzcs_layout_btn.setOnClickListener(new View.OnClickListener() {
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
                                Intent intent1 = new Intent(DetailsOfDistrictsUrdu.this,
                                        DistrictZakatComitteeUrdu.class);
                                intent1.putExtra("ID",DestrictID);
                                intent1.putExtra("District",district_name.getText().toString());
                                intent1.putExtra("DZO",dzo_name.getText().toString());
                                intent1.putExtra("DZO_PHONE_NUMBER",dzo_contact_num.getText().toString());
                                startActivity(intent1);
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        }).playOn(dlzcs_layout_btn);
            }
        });
    }

    private void initViews() {
        district_details = findViewById(R.id.district_details);
        district_name = findViewById(R.id.district_name);
        dzo_name = findViewById(R.id.dzo_name);
        dzo_contact_num = findViewById(R.id.dzo_contact_num);
        dlzcs_num = findViewById(R.id.dlzcs_num);
        dlzcs_layout_btn = findViewById(R.id.dlzcs_layout_btn);
        call_layout = findViewById(R.id.call_layout);
        location_layout = findViewById(R.id.location_layout);
        dzc = findViewById(R.id.dzc);
        dzc_no = findViewById(R.id.dzc_no);
        separator = findViewById(R.id.separator);
    }

    private void showClickToCallDialog(String dzoName, final String dzoPhone) {
        clickToCall = new Dialog(this, android.R.style.Theme_Black_NoTitleBar);
        clickToCall.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
        clickToCall.setContentView(R.layout.click_to_call_dialog_urdu);
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
                }else {

                    if (ActivityCompat.checkSelfPermission(DetailsOfDistrictsUrdu.this,
                            Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {

                        phoneCall(dzoPhone);
                    }else {
                        final String[] PERMISSIONS_STORAGE = {Manifest.permission.CALL_PHONE};
                        //Asking request Permissions
                        ActivityCompat.requestPermissions((Activity) DetailsOfDistrictsUrdu.this,
                                PERMISSIONS_STORAGE, 9);
                    }
                }
            }
        });
    }

    private void phoneCall(String dzoPhone) {
        if (ActivityCompat.checkSelfPermission(DetailsOfDistrictsUrdu.this,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:"+dzoPhone));
            DetailsOfDistrictsUrdu.this.startActivity(callIntent);
        }else{
            Toast.makeText(DetailsOfDistrictsUrdu.this, "آپ اجازت تفویض نہیں کرتے ۔", Toast.LENGTH_SHORT).show();
        }
    }
}