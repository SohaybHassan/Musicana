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

import android.app.Notification;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings.Secure;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.prography.musicana.AppConstants;
import com.prography.musicana.R;
import com.prography.musicana.SharedPreferencesHelper;
import com.prography.musicana.custem.SWInterface.ListItemClick;
import com.prography.musicana.custem.SWInterface.OnFragmentInteractionListener;
import com.prography.musicana.databinding.ActivityMainBinding;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.phoneFragment.model.MusicService;
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
    private double startTime;
    private Handler myHandler;
    private int myNewPosition;
    private ArrayList<PhoneModelFragmentList> items;
    private Intent playIntent;
    private MediaPlayer mainMediaPlayer;
    private MusicService musicService;
    private MainAdapter mainAdapter;
    private CreateMediaPlayer createMediaPlayer;

    //rv_items need
    private TextView mStart, mEnd;
    SeekBar mSeekBar;
    ImageView mImageView;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        notificationManagerCompat = NotificationManagerCompat.from(this);
        createMediaPlayer = CreateMediaPlayer.getInstance();
        mainMediaPlayer = createMediaPlayer.getMediaPlayer();
        items = createMediaPlayer.getLsi();
        changMode();
        runMusic();

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
                        binding.imgSearch.setVisibility(View.VISIBLE);
                        break;
                    case R.id.favorite:

                        binding.toolbar.setVisibility(View.VISIBLE);
                        binding.cardInclude.getRoot().setVisibility(View.VISIBLE);

                        binding.tvTitle2.setText("Favorite");
                        binding.tvTitle.setVisibility(View.GONE);
                        binding.tvTitle2.setVisibility(View.VISIBLE);
                        binding.imgSearch.setVisibility(View.VISIBLE);
                        break;
                    case R.id.download:

                        binding.toolbar.setVisibility(View.VISIBLE);
                        binding.cardInclude.getRoot().setVisibility(View.VISIBLE);

                        binding.tvTitle2.setText("download");
                        binding.tvTitle.setVisibility(View.GONE);
                        binding.tvTitle2.setVisibility(View.VISIBLE);
                        binding.imgSearch.setVisibility(View.VISIBLE);
                        break;
                    case R.id.profile:

                        binding.toolbar.setVisibility(View.VISIBLE);
                        binding.cardInclude.getRoot().setVisibility(View.GONE);

                        binding.tvTitle2.setText("Profile");
                        binding.tvTitle.setVisibility(View.GONE);
                        binding.tvTitle2.setVisibility(View.VISIBLE);
                        binding.imgSearch.setVisibility(View.GONE);
                        break;
                    case R.id.editProfileFragment:

                        binding.toolbar.setVisibility(View.GONE);
                        binding.cardInclude.getRoot().setVisibility(View.GONE);

                        break;
                }
            }
        });


        binding.imgSearch.setOnClickListener(view -> SWStaticMethods.intentWithoutData(
                MainActivity.this, SearchActivity.class));
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
        mainAdapter = new MainAdapter(new MainAdapter.ListItemClick() {
            @Override
            public void itemViewClick(int position) {
                Log.d("TAG", "itemViewClick: " + position);
            }

            @Override
            public void start_stop_music(int position, int isPlaying, TextView tv_start, TextView tv_end, SeekBar seekBar, ImageView start) {
                mStart = tv_start;
                mEnd = tv_end;
                mSeekBar = seekBar;
                mImageView = start;
                if (isPlaying == 0) {
                    musicService.setSong(mainAdapter.getPosition());
                    musicService.playSong(MainActivity.this);
                    inti(tv_end, tv_start, seekBar);
                } else if (isPlaying == 1) {
                    createMediaPlayer.stopAndStartMusic(mainMediaPlayer);
                }
            }

            @Override
            public void repet(int position, int cont) {

                Log.d("TAG", "repet: " + position);
            }

            @Override
            public void menu(int position) {
                Log.d("TAG", "menu: " + position);
            }

        });
        items = createMediaPlayer.getLsi();
        mainAdapter.items = items;
        binding.reRunMusicBottomSheet.setAdapter(mainAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        SnapHelper snapHelper = new PagerSnapHelper();
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        linearLayoutManager.getInitialPrefetchItemCount();
        binding.reRunMusicBottomSheet.computeHorizontalScrollOffset();


        binding.reRunMusicBottomSheet.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                if (createMediaPlayer.getMediaPlayer() != null) {
                    if (createMediaPlayer.getMediaPlayer().isPlaying()) {
                        musicService.setSong(recyclerView.computeHorizontalScrollOffset() / 1000);
                        musicService.playSong(MainActivity.this);
                        mImageView = mainAdapter.getImage();
                        mImageView.setImageResource(R.drawable.ic_start_stop);

                    }

                }

            }
        });

        snapHelper.attachToRecyclerView(binding.reRunMusicBottomSheet);
        binding.reRunMusicBottomSheet.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void itemClick(ArrayList<PhoneModelFragmentList> items, final int position, PhoneModelFragmentList phoneModel) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                myNewPosition = MusicService.getSongPosn();
                mainMediaPlayer = createMediaPlayer.getMediaPlayer();
                binding.cardInclude.progressBar.setMax(MusicService.getDuration());
                //    binding.contenerCard.setVisibility(View.VISIBLE);
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
                    createMediaPlayer.NextMusic(++myNewPosition, items);

                });
                binding.cardInclude.preImage.setOnClickListener(v -> {
                    createMediaPlayer.backMusic(--myNewPosition);
                });
                binding.cardInclude.cardStartStop.setOnClickListener(v -> {
                    createMediaPlayer.stopAndStartMusic(mainMediaPlayer);

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

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (playIntent == null) {
            playIntent = new Intent(this, MusicService.class);
            //  getActivity().bindService(playIntent, serviceConnection, ContextThemeWrapper.BIND_AUTO_CREATE);
            startService(playIntent);
        }
    }

    public void updataSeekpar(TextView tv_start, SeekBar seekBar) {
        startTime = mainMediaPlayer.getCurrentPosition();
        tv_start.setText(String.format("%02d:%02d ", TimeUnit.MILLISECONDS.toMinutes((long) startTime), TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime))));
        seekBar.setProgress((int) startTime);
    }

    public void inti(TextView tv_end, TextView tv_start, SeekBar seekBar) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Log.d("TAG", "getDuration: " + MusicService.EndTime());
                tv_end.setText(MusicService.EndTime());
                Log.d("TAG", "start_stop_music: " + MusicService.startTime());
                tv_start.setText(MusicService.startTime());
                seekBar.setMax(MusicService.getDuration());
                seekBar.setProgress(mainMediaPlayer.getCurrentPosition());
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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
                updataSeekpar(tv_start, seekBar);
                myHandler.postDelayed(this, 100);
            }
        }, 100);
    }
}
