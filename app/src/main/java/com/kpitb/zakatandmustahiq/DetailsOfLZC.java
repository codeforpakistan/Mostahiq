package com.kpitb.zakatandmustahiq;

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
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.kpitb.zakatandmustahiq.utility.CustomTextView;
import com.kpitb.zakatandmustahiq.utility.CustomTextViewForMainPage;

public class DetailsOfLZC extends AppCompatActivity {

    String LZCName;
    String Tehsil;
    String Chairman;
    String PhoneNum;

    private MediaPlayer mediaPlayer;

    private CustomTextViewForMainPage district_name,lzc_name,tehsil_name,chairman_name,chairman_number,district_details;
    private RelativeLayout call_layout;

    Dialog clickToCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_of_l_z_c);

        mediaPlayer = MediaPlayer.create(this,R.raw.click_sound);

        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //INIT DATA
        initData();

        //GETTING DATA
        final Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        LZCName = (String) bundle.get("LZCName");
        Tehsil = (String) bundle.get("TehsilName");
        Chairman = (String) bundle.get("ChairmanName");
        PhoneNum = (String) bundle.get("PhoneNo");

        //MAKE UNDERLINE BELOW TEXT
        //district_details.setPaintFlags(district_details.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        //Set Data
        lzc_name.setText(LZCName);
        tehsil_name.setText(Tehsil);
        chairman_name.setText(Chairman);
        chairman_number.setText(PhoneNum);

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
                                showClickToCallDialog(Chairman,PhoneNum);
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

    private void showClickToCallDialog(String chairman, final String phoneNum) {
        mediaPlayer.start();
        clickToCall = new Dialog(this, android.R.style.Theme_Black_NoTitleBar);
        clickToCall.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
        clickToCall.setContentView(R.layout.click_to_call_dialog);
        clickToCall.setCancelable(true);
        clickToCall.show();

        ImageView closedialog = clickToCall.findViewById(R.id.dialog_close);
        CustomTextView focalPersonName = clickToCall.findViewById(R.id.person_name);
        final CustomTextView contactNo = clickToCall.findViewById(R.id.person_no);
        RelativeLayout call = clickToCall.findViewById(R.id.btn_yes);
        RelativeLayout dont_call = clickToCall.findViewById(R.id.btn_no);

        focalPersonName.setText(chairman);
        contactNo.setText(phoneNum);

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
                    phoneCall(phoneNum);
                }else {

                    if (ActivityCompat.checkSelfPermission(DetailsOfLZC.this,
                            Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {

                        phoneCall(phoneNum);
                    }else {
                        final String[] PERMISSIONS_STORAGE = {Manifest.permission.CALL_PHONE};
                        //Asking request Permissions
                        ActivityCompat.requestPermissions((Activity) DetailsOfLZC.this,
                                PERMISSIONS_STORAGE, 9);
                    }
                }
            }
        });

    }

    private void phoneCall(String phoneNum) {
        if (ActivityCompat.checkSelfPermission(DetailsOfLZC.this,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:"+phoneNum));
            DetailsOfLZC.this.startActivity(callIntent);
        }else{
            Toast.makeText(DetailsOfLZC.this, "You don't assign permission.", Toast.LENGTH_SHORT).show();
        }

    }

    private void initData() {
        lzc_name = findViewById(R.id.lzc_name);
        tehsil_name = findViewById(R.id.tehsil_name);
        chairman_name = findViewById(R.id.chairman_name);
        chairman_number = findViewById(R.id.chairman_number);
        district_details = findViewById(R.id.district_details);
        call_layout = findViewById(R.id.call_layout);
    }

    @Override
    public boolean onSupportNavigateUp() {
        mediaPlayer.start();
        onBackPressed();
        return true;
    }
}