package com.prography.musicana.feature;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.Settings.Secure;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.prography.musicana.AppConstants;
import com.prography.musicana.R;
import com.prography.musicana.SharedPreferencesHelper;
import com.prography.musicana.databinding.ActivityMainBinding;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.phoneFragment.model.MusicService;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.phoneFragment.model.PhoneModelFragmentList;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.search.SearchActivity;
import com.prography.musicana.feature.onboard.Onboarding;
import com.prography.musicana.utils.SWStaticMethods;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static com.prography.musicana.utils.MusicaApp.CHANNEL_ID;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener, ListItemClick {
    private ActivityMainBinding binding;
    private NavController navController;
    private NotificationManagerCompat notificationManagerCompat;
    private double startTime;
    private Handler myHandler;
    private int myNewPosition;


    private MediaPlayer mainMediaPlayer;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        notificationManagerCompat = NotificationManagerCompat.from(this);
        mainMediaPlayer = new MediaPlayer();
        changMode();

        //divise ID
        String android_id = Secure.getString(getContentResolver(), Secure.ANDROID_ID);

        Log.d("android_id", "onCreate: " + android_id);

        myHandler = new Handler();

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(binding.bottomNavigation, navHostFragment.getNavController());


        navController = navHostFragment.getNavController();
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                switch (destination.getId()) {
                    case R.id.home:

                        binding.toolbar.setVisibility(View.VISIBLE);
                        binding.cardInclude.getRoot().setVisibility(View.VISIBLE);

                        binding.tvTitle.setVisibility(View.VISIBLE);
                        binding.tvTitle2.setVisibility(View.GONE);
                        binding.tvTitle.setText("Musicana");

                        break;
                    case R.id.favorite:

                        binding.toolbar.setVisibility(View.VISIBLE);
                        binding.cardInclude.getRoot().setVisibility(View.VISIBLE);

                        binding.tvTitle2.setText("Favorite");
                        binding.tvTitle.setVisibility(View.GONE);
                        binding.tvTitle2.setVisibility(View.VISIBLE);

                        break;
                    case R.id.download:

                        binding.toolbar.setVisibility(View.VISIBLE);
                        binding.cardInclude.getRoot().setVisibility(View.VISIBLE);

                        binding.tvTitle2.setText("download");
                        binding.tvTitle.setVisibility(View.GONE);
                        binding.tvTitle2.setVisibility(View.VISIBLE);

                        break;
                    case R.id.profile:

                        binding.toolbar.setVisibility(View.VISIBLE);
                        binding.cardInclude.getRoot().setVisibility(View.GONE);

                        binding.tvTitle2.setText("Profile");
                        binding.tvTitle.setVisibility(View.GONE);
                        binding.tvTitle2.setVisibility(View.VISIBLE);

                        break;
                    case R.id.editProfileFragment:

                        binding.toolbar.setVisibility(View.GONE);
                        binding.cardInclude.getRoot().setVisibility(View.GONE);

                        break;
                }
            }
        });


        binding.imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SWStaticMethods.intentWithoutData(
                        MainActivity.this, SearchActivity.class);


            }
        });
        binding.cardInclude.colasCard.setOnClickListener(view -> binding.contenerCard.setVisibility(View.GONE));
    }


    @Override
    public void onFragmentInteraction(int id) {
        switch (id) {
            case 0:
                navController.popBackStack();
                break;
            case R.id.editProfileFragment:
                navController.navigate(R.id.editProfileFragment);
                break;
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d("TAG", "onPause: ");
        cretamyCustemNotification();
    }


    public void cretamyCustemNotification() {


        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification_icon)
                .setContentTitle("Track title")
                .setContentText("Artist - Album")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.image_music_play))
                .addAction(R.drawable.ic_repeat, "rebet", null)
                .addAction(R.drawable.ic_backe_music, "back", null)
                .addAction(R.drawable.ic_start_stop, "start", null)
                .addAction(R.drawable.ic_naext_music, "next", null)
                .addAction(R.drawable.ic_favofite, "like", null)


                .setColor(getResources().getColor(R.color.tab_top_bottom, null))
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(1, 2, 3)
                )
                .setSubText("sub Text")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build();
        notificationManagerCompat.notify(1, notification);

//Token	getMediaSession
    }


    public void runMusic() {
        binding.reRunMusicBottomSheet.setAdapter(new MainAdapter(SWStaticMethods.getList(), new MainAdapter.ListItemClick() {
            @Override
            public void itemViewClick(int position) {
                Log.d("TAG", "itemViewClick: "+position);
            }

            @Override
            public void start_stop_music(int position) {
                SWStaticMethods.stopAndStartMusic(mainMediaPlayer);
            }

            @Override
            public void repet(int position) {
                Log.d("TAG", "repet: "+position);
            }

            @Override
            public void menu(int position) {
                Log.d("TAG", "menu: "+position);
            }
        }));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        SnapHelper snapHelper=new PagerSnapHelper();
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        snapHelper.attachToRecyclerView(binding.reRunMusicBottomSheet);
        binding.reRunMusicBottomSheet.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void itemClick(ArrayList<PhoneModelFragmentList> items, final int position, PhoneModelFragmentList phoneModel) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                myNewPosition = MusicService.getSongPosn();
                mainMediaPlayer = SWStaticMethods.getMediaPlayer();
                binding.cardInclude.progressBar.setMax(MusicService.getDuration());
                binding.contenerCard.setVisibility(View.VISIBLE);
                binding.cardInclude.start.setText(MusicService.startTime());
                binding.cardInclude.end.setText(MusicService.EndTime());
                binding.cardInclude.progressBar.setProgress(MusicService.getCurrentPosition());
                myHandler.postDelayed(UpdateSongTime, 100);
                binding.cardInclude.progressBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (mainMediaPlayer != null && fromUser) {
                            mainMediaPlayer.seekTo(progress);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
                binding.cardInclude.imNextMusic.setOnClickListener(v -> {
                    SWStaticMethods.NextMusic(++myNewPosition, items);

                });
                binding.cardInclude.preImage.setOnClickListener(v -> {
                    SWStaticMethods.backMusic(--myNewPosition);
                });
                binding.cardInclude.cardStartStop.setOnClickListener(v -> {
                    SWStaticMethods.stopAndStartMusic(mainMediaPlayer);

                });
                binding.cardInclude.nameMusec.setText(phoneModel.getName());
                binding.cardInclude.nameMusec.setSelected(true);
                binding.cardInclude.typeMusec.setText(phoneModel.getAlpom());
                binding.cardInclude.typeMusec.setSelected(true);
            }
        }, 100);
    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mainMediaPlayer.getCurrentPosition();
            binding.cardInclude.start.setText(String.format("%02d:%02d ", TimeUnit.MILLISECONDS.toMinutes((long) startTime), TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime)))
            );
            binding.cardInclude.progressBar.setProgress((int) startTime);
            myHandler.postDelayed(this, 100);
        }
    };


    public void changMode() {
        switch (SharedPreferencesHelper.getMode(this)) {
            case AppConstants.DarkMode:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case AppConstants.MoonMode:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;
            case AppConstants.LightMode:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
        }
    }

}