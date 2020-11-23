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

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.Notification;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Build;
import android.provider.Settings.Secure;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.prography.musicana.AppConstants;
import com.prography.musicana.R;
import com.prography.musicana.SharedPreferencesHelper;
import com.prography.musicana.databinding.ActivityMainBinding;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.phoneFragment.model.PhoneModelFragmentList;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.search.SearchActivity;
import com.prography.musicana.utils.SWStaticMethods;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static com.prography.musicana.utils.MusicaApp.CHANNEL_ID;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener, ListItemClick {
    private ActivityMainBinding binding;
    private NavController navController;
    private NotificationManagerCompat notificationManagerCompat;
    private double endTime;
    private double startTime;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        notificationManagerCompat = NotificationManagerCompat.from(this);

        switch (SharedPreferencesHelper.getMode(this)) {
            case AppConstants.DarkMode:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case AppConstants.MoonMode:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
        }

        //divise ID
        String android_id = Secure.getString(getContentResolver(), Secure.ANDROID_ID);

        Log.d("android_id", "onCreate: " + android_id);

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
                        binding.imgCatagory.setVisibility(View.VISIBLE);
                        break;
                    case R.id.favorite:

                        binding.toolbar.setVisibility(View.VISIBLE);
                        binding.cardInclude.getRoot().setVisibility(View.VISIBLE);

                        binding.tvTitle2.setText("Favorite");
                        binding.tvTitle.setVisibility(View.GONE);
                        binding.tvTitle2.setVisibility(View.VISIBLE);
                        binding.imgCatagory.setVisibility(View.GONE);
                        break;
                    case R.id.archives:

                        binding.toolbar.setVisibility(View.VISIBLE);
                        binding.cardInclude.getRoot().setVisibility(View.VISIBLE);

                        binding.tvTitle2.setText("Archives");
                        binding.tvTitle.setVisibility(View.GONE);
                        binding.tvTitle2.setVisibility(View.VISIBLE);
                        binding.imgCatagory.setVisibility(View.GONE);
                        break;
                    case R.id.profile:

                        binding.toolbar.setVisibility(View.VISIBLE);
                        binding.cardInclude.getRoot().setVisibility(View.GONE);

                        binding.tvTitle2.setText("Profile");
                        binding.tvTitle.setVisibility(View.GONE);
                        binding.tvTitle2.setVisibility(View.VISIBLE);
                        binding.imgCatagory.setVisibility(View.GONE);
                        break;
                    case R.id.editProfileFragment:

                        binding.toolbar.setVisibility(View.GONE);
                        binding.cardInclude.getRoot().setVisibility(View.GONE);

                        break;
                }
            }
        });

        binding.cardInclude.profileImage.setOnClickListener(viewww -> {

        });

        binding.imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SWStaticMethods.intentWithoutData(
                        MainActivity.this, SearchActivity.class);


            }
        });
        binding.cardInclude.colasCard.setOnClickListener(view -> binding.contenerCard.setVisibility(View.GONE));
        binding.cardInclude.imStopStart.setOnClickListener(view -> {
            Animator animator = AnimatorInflater.loadAnimator(MainActivity.this, R.animator.my_animation);
            animator.setTarget(binding.cardInclude.profileImage);
            animator.start();
        });




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


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onPause() {
        super.onPause();
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

    @Override
    public void itemClick(MediaPlayer mediaPlayer, ArrayList<PhoneModelFragmentList> items) {

        setTextstartAndEndTime(mediaPlayer.getCurrentPosition(), mediaPlayer.getDuration(), binding.cardInclude.startTime, binding.cardInclude.end);

        Toast.makeText(this, startTime + " _ " + endTime, Toast.LENGTH_SHORT).show();


    }


    public void setTextstartAndEndTime(int start, int End, TextView tvStart, TextView tvEnd) {

        tvEnd.setText(String.format("%d  %d ", TimeUnit.MILLISECONDS.toMinutes((long) End),
                TimeUnit.MILLISECONDS.toSeconds((long) End),
                TimeUnit.MILLISECONDS.toMinutes((long) End)));

        tvStart.setText(String.format("%d , %d ",
                TimeUnit.MILLISECONDS.toMinutes((long) start),
                TimeUnit.MILLISECONDS.toSeconds((long) start) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                start)))
        );
    }

}