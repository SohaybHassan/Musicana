package com.prography.musicana.custem.bottomsheet;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.prography.musicana.R;
import com.prography.musicana.adapter.ListMusicProfileAdapter;
import com.prography.musicana.custem.CreateMediaPlayer;
import com.prography.musicana.model.MusicService;
import com.prography.musicana.model.PhoneModelFragmentList;
import com.prography.musicana.utils.MusicaApp;

import java.util.ArrayList;


public class BottomSheetRunMusic {

    private BottomSheetDialog mDialog;
    private bottomSheetSearsh mListener;
    private CreateMediaPlayer createMediaPlayer;


    private TextView musicName, musicAlpom, startTime, endtTime;
    private ImageView im_next, im_back, im_startStop, repet, repet2, menu;
    private RecyclerView rv_musicList, rv_music_semelar;
    private SeekBar seekBar;


    public BottomSheetRunMusic(BottomSheetDialog dialog, bottomSheetSearsh listener) {
        this.mListener = listener;
        this.mDialog = dialog;
        createMediaPlayer = CreateMediaPlayer.getInstance();
    }


    public void openDialog(String name, String alpom) {

        View view = LayoutInflater.from(MusicaApp.getIstant()).inflate(R.layout.music_profril_layout_sheet, null);
        mDialog = new BottomSheetDialog(MusicaApp.getIstant());
        mDialog.setContentView(view);

        findView(view);
        rvLsitMusicCreated();
        rvLsitMusicsamelerCreated();
        setDate(name, alpom);
        onClickimage();
        mDialog.show();


    }


    public interface bottomSheetSearsh {

        void searshByNumber(String num_facility);

    }

    public void onClickimage() {
        im_next.setOnClickListener(v -> createMediaPlayer.NextMusic(createMediaPlayer.getPosition(), createMediaPlayer.getLsi()));
        im_back.setOnClickListener(v -> createMediaPlayer.backMusic(createMediaPlayer.getPosition()));
        im_startStop.setOnClickListener(v -> createMediaPlayer.stopAndStartMusic(createMediaPlayer.getMediaPlayer()));
    }

    public void findView(View view) {
        //text view
        musicName = view.findViewById(R.id.tv_music_name);
        musicAlpom = view.findViewById(R.id.tv_alpom);
        startTime = view.findViewById(R.id.start);
        endtTime = view.findViewById(R.id.end);
        //image view
        im_next = view.findViewById(R.id.next_image);
        im_back = view.findViewById(R.id.im_backe_music);
        im_startStop = view.findViewById(R.id.im_stop_start);
        repet = view.findViewById(R.id.im_repet);
        repet2 = view.findViewById(R.id.im_repet2);
        menu = view.findViewById(R.id.menu);
        //recycler View
        rv_musicList = view.findViewById(R.id.rv_music_profile);
        rv_music_semelar = view.findViewById(R.id.rv_semear_profile);
        //seekpar
        seekBar = view.findViewById(R.id.seekBar);
    }


    public void rvLsitMusicCreated() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MusicaApp.getIstant());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rv_musicList.setLayoutManager(linearLayoutManager);
        rv_musicList.setAdapter(new ListMusicProfileAdapter());
    }

    public void rvLsitMusicsamelerCreated() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MusicaApp.getIstant());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rv_music_semelar.setLayoutManager(linearLayoutManager);
        rv_music_semelar.setAdapter(new ListMusicProfileAdapter());
    }

    public void setDate(String name, String alpom) {
        musicName.setText(name);
        musicAlpom.setText(alpom);
        startTime.setText(MusicService.startTime());
        endtTime.setText(MusicService.EndTime());
    }


    public void nextMusic() {
        int position = createMediaPlayer.getPosition();
        ArrayList<PhoneModelFragmentList> items = createMediaPlayer.getLsi();
        createMediaPlayer.NextMusic(++position, items);
    }


    public  void backMusic() {
        int position = createMediaPlayer.getPosition();
       createMediaPlayer .backMusic(position);

    }

    public  void StopStartMusic() {
        createMediaPlayer.stopAndStartMusic(createMediaPlayer.getMediaPlayer());
    }


}
