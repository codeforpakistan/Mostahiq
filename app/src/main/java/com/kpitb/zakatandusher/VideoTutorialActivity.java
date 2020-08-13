package com.kpitb.zakatandusher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import static android.provider.MediaStore.Video.Thumbnails.VIDEO_ID;
import static com.kpitb.zakatandusher.MainActivity.MY_PREFS_NAME;

public class VideoTutorialActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    YouTubePlayerView youTubePlayerView;
    Button button_skip,never_show_again;

    Boolean introFlag;

    public static final String API_KEY = "AIzaSyA5EgAnFEdQBpzIoxRSTLdsKeni2T5iCLM";

    String VIDEO_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_tutorial);

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        introFlag = prefs.getBoolean("IntroFlagVideo", true); //"No name defined" is the default value.

        youTubePlayerView = findViewById(R.id.my_video_view);
        youTubePlayerView.initialize(API_KEY,this);
        VIDEO_URL = "d4BwJz0Q9oQ";

        button_skip = findViewById(R.id.skip_btn);
        never_show_again = findViewById(R.id.never_show_again);

        button_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VideoTutorialActivity.this,MainActivity.class));
                finish();
            }
        });

        never_show_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putBoolean("IntroFlagVideo", false);
                editor.commit();

                startActivity(new Intent(VideoTutorialActivity.this,MainActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        /** add listeners to YouTubePlayer instance **/
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        youTubePlayer.setPlaybackEventListener(playbackEventListener);

        /** Start buffering **/
        if (!wasRestored) {
            if (introFlag){
                youTubePlayer.cueVideo(VIDEO_URL);
            }
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Failured to Initialize!", Toast.LENGTH_LONG).show();
    }

    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onBuffering(boolean arg0) {
        }
        @Override
        public void onPaused() {
        }
        @Override
        public void onPlaying() {
        }
        @Override
        public void onSeekTo(int arg0) {
        }
        @Override
        public void onStopped() {
        }
    };

    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onAdStarted() {
        }
        @Override
        public void onError(YouTubePlayer.ErrorReason arg0) {
        }
        @Override
        public void onLoaded(String arg0) {
        }
        @Override
        public void onLoading() {
        }
        @Override
        public void onVideoEnded() {
            startActivity(new Intent(VideoTutorialActivity.this,MainActivity.class));
            finish();
        }
        @Override
        public void onVideoStarted() {
        }
    };
}
