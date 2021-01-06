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

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.kpitb.zakattandusherr.utility.CustomTextView;

public class GuzaraAllowancePashto extends AppCompatActivity /*implements TextToSpeech.OnInitListener*/ {
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
        setContentView(R.layout.activity_guzara_allowance_pashto);

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

                final Dialog dialog = new Dialog(GuzaraAllowancePashto.this);
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
                            startActivity(new Intent(GuzaraAllowancePashto.this,GuzaraAllownceUrdu.class));
                            finish();
                            dialog.dismiss();
                        }
                        else if (radioPashto.isChecked() == true)
                        {
                            dialog.dismiss();
                        }
                        else {
                            startActivity(new Intent(GuzaraAllowancePashto.this,GuzaraAllownce.class));
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

    @Override
    public boolean onSupportNavigateUp() {
        mediaPlayer.start();
        onBackPressed();
        return true;
    }

    public void DownloadForm(View view){
        mediaPlayer.start();
        YoYo.with(Techniques.Landing)
                .duration(200)
                .repeat(0)
                .playOn(view);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://swkpk.gov.pk/wp-content/uploads/2018/01/Guzara_Allow-Form.pdf"));
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

//    private void isTTSSpeaking() {
//        final Handler h =new Handler();
//
//        Runnable r = new Runnable() {
//
//            public void run() {
//
//                if (!textToSpeech.isSpeaking()) {
//                    onTTSSpeechFinished();
//                }
//                h.postDelayed(this, 1000);
//            }
//        };
//        h.postDelayed(r, 1000);
//    }
//
//    private void onTTSSpeechFinished() {
//        textToSpeech.stop();
//        stop.setVisibility(View.INVISIBLE);
//        play.setVisibility(View.VISIBLE);
//    }
//
//    private void speakWords(String one, String two) {
//        //textToSpeech.setPitch(1f);
//        textToSpeech.setSpeechRate(1f);
//
//        textToSpeech.speak(one,TextToSpeech.QUEUE_ADD,null);
//        textToSpeech.speak(two,TextToSpeech.QUEUE_ADD,null);
//    }


//    @Override
//    public void onInit(int status) {
//        if (status == TextToSpeech.SUCCESS) {
//            if (textToSpeech.isLanguageAvailable(Locale.US) == TextToSpeech.LANG_AVAILABLE)
//                textToSpeech.setLanguage(new Locale("ps"));
//            textToSpeech.setLanguage(new Locale("ps"));
//        } else if (status == TextToSpeech.ERROR) {
//            Toast.makeText(this, "Sorry! Text To Speech failed...", Toast.LENGTH_LONG).show();
//        }
//
//   /*     if (status == TextToSpeech.SUCCESS) {
//            if (textToSpeech.isLanguageAvailable(Locale.US) == TextToSpeech.LANG_AVAILABLE)
//                textToSpeech.setLanguage(new Locale("urd"));
//            textToSpeech.setLanguage(new Locale("urd"));
//        } else if (status == TextToSpeech.ERROR) {
//            Toast.makeText(this, "Sorry! Text To Speech failed...", Toast.LENGTH_LONG).show();
//        }*/
//    }
//
//    @Override
//    protected void onDestroy() {
//        if (textToSpeech != null)
//        {
//            textToSpeech.stop();
//            textToSpeech.shutdown();
//        }
//        super.onDestroy();
//    }
}
