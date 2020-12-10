package com.prography.musicana.feature;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prography.musicana.R;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.phoneFragment.model.MusicService;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.phoneFragment.model.PhoneModelFragmentList;
import com.prography.musicana.feature.onboard.Onboarding;
import com.prography.musicana.utils.SWStaticMethods;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyHolder> implements ListItemClick {
    private ArrayList<PhoneModelFragmentList> items;
    private ListItemClick listener;
    private Handler handler;

    public MainAdapter(ArrayList<PhoneModelFragmentList> items, ListItemClick listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.bottom_sheet_main, parent, false);
        handler = new Handler();
        return new MyHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.bind(position, listener);
        nextPage(holder.seekBar, holder.start, holder.end);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void itemClick(ArrayList<PhoneModelFragmentList> items, int position, PhoneModelFragmentList phoneModel) {

    }


    public class MyHolder extends RecyclerView.ViewHolder {

        private ImageView start_stop, repet, menu;
        private SeekBar seekBar;
        TextView start, end;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            start_stop = itemView.findViewById(R.id.im_stop_start_main);
            repet = itemView.findViewById(R.id.pre_image_main);
            menu = itemView.findViewById(R.id.menu_List_main);
            seekBar = itemView.findViewById(R.id.seekBar);
            start = itemView.findViewById(R.id.start);
            end = itemView.findViewById(R.id.end);
        }

        public void bind(int position, ListItemClick listener) {
            itemView.setOnClickListener(v ->
            {
                listener.itemViewClick(position);

            });
            start_stop.setOnClickListener(v -> {
                listener.start_stop_music(position);

            });
            repet.setOnClickListener(v -> {
                listener.repet(position);

            });
            menu.setOnClickListener(v -> {
                listener.menu(position);
            });


        }
    }

    public interface ListItemClick {
        void itemViewClick(int position);

        void start_stop_music(int position);

        void repet(int position);

        void menu(int position);
    }


    public void nextPage(SeekBar seekBar, TextView start, TextView end) {
        Thread background = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for 5 seconds
                    sleep(100);

                    // After 5 seconds redirect to another intent
                    end.setText(MusicService.getDuration());
                    seekBar.setMax(MusicService.getDuration());
                    Log.d("TAG", "run: " +(MusicService.getDuration()+":::"+MusicService.getCurrentPosition()));
                    seekBar.setProgress(MusicService.getCurrentPosition());
                    handler.postDelayed(SWStaticMethods.updateSeekPar(seekBar, handler, start), 100);
                    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            if (SWStaticMethods.getMediaPlayer() != null && fromUser) {
                                SWStaticMethods.getMediaPlayer().seekTo(progress);
                            }
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {

                        }
                    });


                } catch (Exception e) {
                }
            }
        };
        // start thread
        background.start();

    }


}
