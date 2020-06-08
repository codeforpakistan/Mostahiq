package com.kpitb.zakatandusher;

import android.content.Intent;
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

public class SplashActivity extends AppCompatActivity {

    private ImageView ivSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        ivSplash = (ImageView) findViewById(R.id.ivSplash);
        scaleAnimation();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                      startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
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

