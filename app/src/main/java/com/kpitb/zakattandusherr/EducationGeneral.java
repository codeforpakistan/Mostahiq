package com.kpitb.zakattandusherr;

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

import com.google.firebase.analytics.FirebaseAnalytics;

public class EducationGeneral extends AppCompatActivity {

    private static final String TAG = "DeeniMadaris";
    String abc = "";

    private FirebaseAnalytics firebaseAnalytics;

    String btnNAME, value;

    MediaPlayer mediaPlayer;

    private boolean flagmale = false;
    private boolean flagfemale = false;
    private boolean flagfemale3 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_general);

        firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mediaPlayer = MediaPlayer.create(this,R.raw.click_sound);

        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ImageView change_lang = (ImageView) toolbar.findViewById(R.id.change_language);

        change_lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                DisplayMetrics metrics = getResources().getDisplayMetrics();
                int width = metrics.widthPixels;
                int height = metrics.heightPixels;

                final Dialog dialog = new Dialog(EducationGeneral.this);
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
                            startActivity(new Intent(EducationGeneral.this,EducationGeneralUrdu.class));
                            finish();
                            dialog.dismiss();
                        }
                        else if (radioPashto.isChecked() == true)
                        {
                            startActivity(new Intent(EducationGeneral.this,EducationGeneralPashto.class));
                            finish();
                            dialog.dismiss();
                        }
                        else {
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

    @Override
    public boolean onSupportNavigateUp() {
        mediaPlayer.start();
        onBackPressed();
        return true;
    }

    public void DownloadForm(View view){
        mediaPlayer.start();
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://swkpk.gov.pk/wp-content/uploads/2018/01/Form-2%20Educational%20Stipends%20(Colleges,%20Universities%20etc).pdf"));
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

}