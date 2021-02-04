package com.kpitb.mustahiq;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.firebase.analytics.FirebaseAnalytics;

import static com.kpitb.mustahiq.MainActivity.MY_PREFS_NAME;

public class SplashActivity extends AppCompatActivity {

    private ImageView ivSplash;

    Boolean introFlag;

    private final String TAG = "SPLASH_SCREEN";

    private FirebaseAnalytics firebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        firebaseAnalytics = FirebaseAnalytics.getInstance(this);

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        introFlag = prefs.getBoolean("IntroFlagVideo",true);

        ivSplash = (ImageView) findViewById(R.id.ivSplash);
        scaleAnimation();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (introFlag)
                {
                    startActivity(new Intent(SplashActivity.this, VideoTutorialActivity.class));
                    finish();
                }
                else {
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    finish();
                }
            }
        }, 3000);
    }


    public void translateAnimation(){
        final Animation mAnimation = new TranslateAnimation(
                TranslateAnimation.ABSOLUTE, 0f,
                0, 0f,
                TranslateAnimation.RELATIVE_TO_PARENT, -0.5f,
                TranslateAnimation.RELATIVE_TO_PARENT, 0f);
        mAnimation.setDuration(2000);
        mAnimation.setInterpolator(new LinearInterpolator());
        mAnimation.setFillAfter(true);
        mAnimation.setFillBefore(true);
        ivSplash.setAnimation(mAnimation);
        mAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.e("Animm...", "Endd....");
                ivSplash.setImageDrawable(ContextCompat.getDrawable(SplashActivity.this, R.drawable.splash));
                scaleAnimation();
            }
        });
    }

    public void scaleAnimation(){
        final Animation scaleAnim = new ScaleAnimation(
                1f, 5f,
                1f, 5f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        scaleAnim.setDuration(1500);
//        scaleAnim.setRepeatCount(2);
//        scaleAnim.setRepeatMode(Animation.REVERSE);
        scaleAnim.setInterpolator(this, android.R.anim.bounce_interpolator);
        scaleAnim.setFillAfter(true);
        scaleAnim.setFillBefore(true);
        ivSplash.setAnimation(scaleAnim);
        scaleAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.e("Animm...", "Endd....");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        String name = "SPLASH SCREEN";

//        Bundle bundle = new Bundle();
//        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
//        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "IMAGE");
//        analytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

    }
}

