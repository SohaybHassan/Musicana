package com.prography.musicana.feature;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.prography.musicana.R;
import com.prography.musicana.custem.SWInterface.ListItemClick;
import com.prography.musicana.custem.SWInterface.SendDataOnBording;
import com.prography.musicana.feature.onboard.model.OnPordingData;
import com.prography.musicana.feature.onboard.model.OnpordingModel;
import com.prography.musicana.feature.onboard.model.SendDtatToActivity;
import com.prography.musicana.feature.onboard.view.OnboardFragment;
import com.prography.musicana.feature.onboard.view.OnboardTowFragment;
import com.prography.musicana.feature.onboard.view.Onboarding;
import com.prography.musicana.feature.onboard.view.OnpoardFourFragment;
import com.prography.musicana.feature.onboard.view.OnpoardThreeFragment;
import com.prography.musicana.feature.onboard.viewModel.OnPoardingViewmodel;
import com.prography.musicana.utils.SWStaticMethods;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener {
    public static final int The_time_of_the_start_activity = 3000;
    public static final int REQUEST_CODE = 3000;
    private VideoView videoView;
    private TextView btn_skip;
    CreateMediaPlayer createMediaPlayer;
    private OnPoardingViewmodel onPoardingViewmodel;
    private ArrayList<OnPordingData> items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideTheStatusBar();
        setContentView(R.layout.activity_splash);
        createMediaPlayer = CreateMediaPlayer.getInstance();
        getData();
        btn_skip = findViewById(R.id.btn_skip);
        btn_skip.setEnabled(false);
        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SWStaticMethods.intentWithoutData(SplashActivity.this, Onboarding.class);
                finish();
            }
        });

        takePermission();
        videoView = findViewById(R.id.video_view);
        mRun();
        //   CreateMediaPlayer.getInstance().getplayListMusicFomDivice();

    }


    public void takePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            //  nextPage();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // nextPage();
            } else {

            }
        }


    }

    public void nextPage() {
        Thread background = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for 5 seconds
                    sleep(The_time_of_the_start_activity);

                    // After 5 seconds redirect to another intent
                    Intent i = new Intent(SplashActivity.this, Onboarding.class);
                    startActivity(i);

                    //Remove activity
                    finish();
                } catch (Exception e) {
                }
            }
        };
        // start thread
        background.start();

    }

    //hideTheStatusBar
    public void hideTheStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            final WindowInsetsController controller = getWindow().getInsetsController();

            if (controller != null)
                controller.hide(WindowInsets.Type.statusBars());
        } else {
            //noinspection deprecation
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }


    public void mRun() {
        String videopth = "android.resource://com.prography.musicana/" + R.raw.music;

        Uri uri = Uri.parse(videopth);
        videoView.setVideoURI(uri);
        videoView.start();
        if (!videoView.isPlaying()) {
            videoView.start();
        }
        videoView.setOnCompletionListener(this);

    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mRun();
    }


    public void getData() {
        onPoardingViewmodel = new ViewModelProvider(this).get(OnPoardingViewmodel.class);
        SendDtatToActivity sendDtatToActivity = SendDtatToActivity.getInstance();
        onPoardingViewmodel.getData().observe(this, new Observer<OnpordingModel>() {
            @Override
            public void onChanged(OnpordingModel onpordingModel) {
                if (onpordingModel != null) {
                    Log.d("TAG", "onChanged: " + " we have data");
                    btn_skip.setEnabled(true);
                    items = onpordingModel.getResponse().getData();
                    sendDtatToActivity.setItems(items);
                }else{
                    Log.d("TAG", "onChanged: "+"no data");
                }
            }
        });
    }


}