package com.kpitb.zakatandmustahiq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.kpitb.zakatandmustahiq.utility.CustomTextView;

public class HealthCareUrdu extends AppCompatActivity {
    private static final String TAG = "DeeniMadaris";
    String abc = "";

    private FirebaseAnalytics firebaseAnalytics;
    MediaPlayer mediaPlayer;

    String btnNAME, value;

    private boolean flagmale = false;
    private boolean flagfemale = false;
    private boolean flagfemale3 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_care_urdu);

        firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mediaPlayer = MediaPlayer.create(this,R.raw.click_sound);


        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final ImageView change_lang = (ImageView) toolbar.findViewById(R.id.change_language);
        final CustomTextView coming_soon = (CustomTextView) findViewById(R.id.coming_soon);
        coming_soon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                YoYo.with(Techniques.Landing)
                        .duration(200)
                        .repeat(0)
                        .playOn(coming_soon);
            }
        });
        change_lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                YoYo.with(Techniques.Landing)
                        .duration(200)
                        .repeat(0)
                        .playOn(change_lang);
                DisplayMetrics metrics = getResources().getDisplayMetrics();
                int width = metrics.widthPixels;
                int height = metrics.heightPixels;

                final Dialog dialog = new Dialog(HealthCareUrdu.this);
                dialog.setContentView(R.layout.language_dialog_two);
                final RadioButton radioUrdu = (RadioButton)dialog.findViewById(R.id.lang1);
                final RadioButton radioPashto = (RadioButton)dialog.findViewById(R.id.lang2);
                final RadioButton radioEng = (RadioButton)dialog.findViewById(R.id.lang3);
                dialog.getWindow().setLayout((6 * width)/7, ActionBar.LayoutParams.WRAP_CONTENT);

                radioUrdu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mediaPlayer.start();
                        if (radioUrdu.isChecked())
                        {
                            if (!flagmale)
                            {
                                radioUrdu.setChecked(true);
                                radioPashto.setChecked(false);
                                radioEng.setChecked(false);
                                flagmale = true;
                                flagfemale = false;
                                flagfemale3 = false;
                            }
                            else {
                                flagmale = false;
                                radioUrdu.setChecked(false);
                                radioPashto.setChecked(false);
                                radioEng.setChecked(false);
                            }
                        }
                    }
                });

                radioPashto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mediaPlayer.start();
                        if (radioPashto.isChecked())
                        {
                            if (!flagfemale)
                            {
                                radioPashto.setChecked(true);
                                radioUrdu.setChecked(false);
                                radioEng.setChecked(false);
                                flagmale = false;
                                flagfemale = true;
                                flagfemale3 = false;
                            }
                            else {
                                flagfemale = false;
                                radioPashto.setChecked(false);
                                radioUrdu.setChecked(false);
                                radioEng.setChecked(false);
                            }
                        }
                    }
                });

                radioEng.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mediaPlayer.start();
                        if (radioEng.isChecked())
                        {
                            if (!flagfemale3)
                            {
                                radioEng.setChecked(true);
                                radioUrdu.setChecked(false);
                                radioPashto.setChecked(false);
                                flagmale = false;
                                flagfemale = false;
                                flagfemale3 = true;
                            }
                            else {
                                flagfemale3 = false;
                                radioEng.setChecked(false);
                                radioUrdu.setChecked(false);
                                radioPashto.setChecked(false);
                            }
                        }
                    }
                });

                Button btnOK = (Button)dialog.findViewById(R.id.btn_yes);
                Button btnCancel = (Button)dialog.findViewById(R.id.btn_no);

                btnOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mediaPlayer.start();
                        if (radioUrdu.isChecked() == true)
                        {
                            dialog.dismiss();
                        }
                        else if (radioPashto.isChecked() == true)
                        {
                            startActivity(new Intent(HealthCareUrdu.this,HealthCarePashto.class));
                            finish();
                            dialog.dismiss();
                        }
                        else {
                            startActivity(new Intent(HealthCareUrdu.this,HealthCare.class));
                            finish();
                            dialog.dismiss();
                        }
                    }
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mediaPlayer.start();
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }

    public void DownloadForm(View view){
        mediaPlayer.start();
        YoYo.with(Techniques.Landing)
                .duration(200)
                .repeat(0)
                .playOn(view);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://swkpk.gov.pk/wp-content/uploads/2018/01/Form-6-SHCP-Estimate-Cost.pdf"));
        Bundle params = new Bundle();
        params.putInt("ButtonID",view.getId());
        btnNAME = "FORM";
        setStatus("Download_form");
        Log.d(TAG, "LOGZZZ: " + btnNAME);
        firebaseAnalytics.logEvent(btnNAME,params);
        startActivity(browserIntent);
    }

    private void setStatus(String text) {
        value = text;
    }

    @Override
    public boolean onSupportNavigateUp() {
        mediaPlayer.start();
        onBackPressed();
        return true;
    }
}
