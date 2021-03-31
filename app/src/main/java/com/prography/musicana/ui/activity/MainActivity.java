package com.prography.musicana.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.prography.musicana.data.getallplaylist.Data;
import com.prography.musicana.utils.AppConstants;
import com.prography.musicana.R;
import com.prography.musicana.utils.SharedPreferencesHelper;
import com.prography.musicana.custem.bottomsheet.BottmSheetMain;
import com.prography.musicana.custem.bottomsheet.BottomSheetAddToPlayList;
import com.prography.musicana.custem.bottomsheet.BottomSheetMore;
import com.prography.musicana.custem.dialog.SWDialog;
import com.prography.musicana.listener.ListItemClick;
import com.prography.musicana.listener.OnFragmentInteractionListener;
import com.prography.musicana.databinding.ActivityMainBinding;
import com.prography.musicana.custem.CreateMediaPlayer;
import com.prography.musicana.data.getallplaylist.GetAllPlayList;
import com.prography.musicana.data.getallplaylist.Playlist;
import com.prography.musicana.viewmodel.PlaylsitViewModel;
import com.prography.musicana.adapter.MainAdapter;
import com.prography.musicana.model.MusicService;
import com.prography.musicana.model.PhoneModelFragmentList;
import com.prography.musicana.viewmodel.StatusViewModel;
import com.prography.musicana.utils.SWStaticMethods;

import java.util.ArrayList;

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
    private SharedPreferencesHelper sharedPreferencesHelper;
    private StatusViewModel statusViewModel;
    //rv_items need
    private TextView mStart, mEnd;
    private SeekBar mSeekBar;
    private ImageView mImageView;
    private BottomSheetAddToPlayList bottomSheetAddToPlayList;
    private static final String TAG = MainActivity.class.getSimpleName();
    private SWDialog swDialog;
    private ArrayList<String> addItem;
    private PlaylsitViewModel playlsitViewModel;
    private ArrayList<Playlist> getPlaylistName;
    private int pos = -1;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPreferencesHelper = new SharedPreferencesHelper();
        statusViewModel = new ViewModelProvider(this).get(StatusViewModel.class);
        playlsitViewModel = new ViewModelProvider(this).get(PlaylsitViewModel.class);
        notificationManagerCompat = NotificationManagerCompat.from(this);
        createMediaPlayer = CreateMediaPlayer.getInstance();
        mainMediaPlayer = createMediaPlayer.getMediaPlayer();
        items = createMediaPlayer.getLsi();
        addItem = new ArrayList<>();
        getPlaylistName = new ArrayList<>();
        getPlaylistName = getAllplaylist();
        changMode();
        runMusic();

        //divise ID
        String android_id = Secure.getString(getContentResolver(), Secure.ANDROID_ID);
        Log.d("android_id", "onCreate: " + android_id);

        if (!sharedPreferencesHelper.getToken().equals("")) {
            myStatus(android_id);
        }


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


        binding.imgSearch.setOnClickListener(view -> SWStaticMethods.intentWithOutDataAndFinish(
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
                BottmSheetMain bottmSheetMain = new BottmSheetMain(MainActivity.this, new BottomSheetDialog(MainActivity.this));
                bottmSheetMain.opendialog();
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

                BottomSheetMore bottomSheetMore = new BottomSheetMore(MainActivity.this, new BottomSheetDialog(MainActivity.this), new BottomSheetMore.BottomSheetMoreMethode() {
                    @Override
                    public void addtoplayList() {
//                        Log.d(TAG, "addtoplayList: addtoplayList ");
//                        bottomSheetAddToPlayList = new BottomSheetAddToPlayList(MainActivity.this, new BottomSheetDialog(MainActivity.this), new BottomSheetAddToPlayList.BottomSheetAddToPlayListMethode() {
//                            @Override
//                            public void addtoplayList() {
//                                swDialog = new SWDialog(lsitName -> {
//                                    addItem.clear();
//                                    createPLayList(lsitName.getText().toString());
//                                    getPlaylistName = getAllplaylist();
//                                    bottomSheetAddToPlayList.setList(getPlaylistName);
//                                    swDialog.dismiss();
//                                });
//                                swDialog.show(getSupportFragmentManager(), "hi thir");
//                            }
//
//                            @Override
//                            public void addsongToplayList(String playListid) {
////                                addsong(String.valueOf(phoneModel.getId()), playListid);
//                            }
//                        });
//
//                        bottomSheetAddToPlayList.openDialog();
//
//                        Log.d(TAG, "addtoplayList: " + getPlaylistName.size());
//                        bottomSheetAddToPlayList.setList(getPlaylistName);
                    }


                    @Override
                    public void downlode() {
                        Log.d(TAG, "downlode: ");
                    }

                    @Override
                    public void share() {
                        Log.d(TAG, "share: ");
                        SWStaticMethods.shareApp(MainActivity.this);
                    }

                    @Override
                    public void addToFavorite() {
                        Log.d(TAG, "addToFavorite: ");
                    }
                });
                bottomSheetMore.openDialog();
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


        binding.reRunMusicBottomSheet.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

//                Log.d(TAG, "onScrolled: " + (1 + linearLayoutManager.findFirstVisibleItemPosition()));
                if (pos != linearLayoutManager.findFirstVisibleItemPosition()) {

                    pos = linearLayoutManager.findFirstVisibleItemPosition();
                    System.out.println("test" + pos);

//                    PhoneModelFragmentList data = mainAdapter.getdata((linearLayoutManager.findFirstVisibleItemPosition()));

                    musicService.setSong(linearLayoutManager.findFirstVisibleItemPosition());
                    System.out.println("data" + pos);
                    System.out.println("media" + createMediaPlayer.getMediaPlayer().isPlaying());

                    if (createMediaPlayer.getMediaPlayer().isPlaying()) {

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
    public void itemClick(ArrayList<PhoneModelFragmentList> items,
                          final int position, PhoneModelFragmentList phoneModel) {
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

//    public void updataSeekpar(TextView tv_start, SeekBar seekBar) {
//        startTime = mainMediaPlayer.getCurrentPosition();
////        tv_start.setText(String.format("%02d:%02d ", TimeUnit.MILLISECONDS.toMinutes((long) startTime), TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
////                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime))));
//        seekBar.setProgress((int) startTime);
//    }

    public void inti(TextView tv_end, TextView tv_start, SeekBar seekBar) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

//                Log.d("TAG", "getDuration: " + MusicService.EndTime());
////                tv_end.setText(MusicService.EndTime());
//                Log.d("TAG", "start_stop_music: " + MusicService.startTime());
//                tv_start.setText(MusicService.startTime());
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
                //updataSeekpar(tv_start, seekBar);
                myHandler.postDelayed(this, 100);
            }
        }, 100);
    }


    public void myStatus(String uuid) {
        statusViewModel.setnewStatus(uuid).observe(this, newStatus -> {

            if (newStatus != null) {
                Log.d(TAG, "myStatus: " + newStatus.getStatus().getStatus());
            } else {
                Log.d(TAG, "myStatus: no data");
            }


        });
    }

    public void myChangeStatus(String Change_to) {
        statusViewModel.setChangeStatus(Change_to).observe(this, newStatus -> {

            if (newStatus != null) {
                Log.d(TAG, "myStatus: " + newStatus.getActiveStatus().getStatus());
            } else {
                Log.d(TAG, "myStatus: no data");
            }


        });
    }

    public void myCloseStatus() {
        statusViewModel.setCloseStatus().observe(this, newStatus -> {

            if (newStatus != null) {
                Log.d(TAG, "myStatus: " + newStatus);
            } else {
                Log.d(TAG, "myStatus: no data");
            }


        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!sharedPreferencesHelper.getToken().equals("")) {
            myCloseStatus();
        }
        Log.d(TAG, "onDestroy: 0000000000000000000000000000000000000000000000000");
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (!sharedPreferencesHelper.getToken().equals("")) {
            myChangeStatus("Background");
        }
        Log.d(TAG, "onPause: 0000000000000000000000000000000000000000000000000");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!sharedPreferencesHelper.getToken().equals("")) {
            myChangeStatus("Active");
        }

        Log.d(TAG, "onResume: 0000000000000000000000000000000000000000000000000");
    }

    public void createPLayList(String name) {
        playlsitViewModel.creatPlaylist(name).observe(this, createPlayList -> {
            if (createPlayList != null) {
                Log.d(TAG, "onChanged: " + createPlayList.getPlaylist().getName());
                Log.d(TAG, "onChanged: ");
            } else {
                Log.d(TAG, "onChanged:  no data");
            }
        });
    }


    public ArrayList<Playlist> getAllplaylist() {
        Log.d(TAG, "getAllplaylist: ");
        playlsitViewModel.getAllPlayListLiveData().observe(this, new Observer<Data>() {
            @Override
            public void onChanged(Data getAllPlayList) {
                if (getAllPlayList != null) {
                    getPlaylistName.clear();
                    for (int i = 0; i < getAllPlayList.getPlaylists().size(); i++) {
                        getPlaylistName.add(getAllPlayList.getPlaylists().get(i));
                        Log.d("TAG", "onChanged getAllplaylist: " + getAllPlayList.getPlaylists().get(i).getName());
                    }
                    Log.d(TAG, "onChanged: " + getPlaylistName.size());

                } else {
                    Log.d("TAG", "onChanged: no data");
                }

            }
        });
        return getPlaylistName;
    }


}
