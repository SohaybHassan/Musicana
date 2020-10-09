package com.prography.musicana.feature;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.prography.musicana.R;
import com.prography.musicana.feature.onboard.Onboarding;

public class SplashActivity extends AppCompatActivity {
    public static final int The_time_of_the_start_activity = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window=this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, Onboarding.class));
                finish();
            }
        }, The_time_of_the_start_activity);


    }
}