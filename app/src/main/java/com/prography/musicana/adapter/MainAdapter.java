package com.prography.musicana.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prography.musicana.R;
import com.prography.musicana.listener.ListItemClick;
import com.prography.musicana.custem.CreateMediaPlayer;
import com.prography.musicana.model.PhoneModelFragmentList;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyHolder> implements ListItemClick {
    public ArrayList<PhoneModelFragmentList> items = new ArrayList<>();
    private ListItemClick listener;
    private int mPosition;
    private CreateMediaPlayer createMediaPlayer;
    private ImageView imageView;


    public MainAdapter(ListItemClick listener) {
        this.listener = listener;
        createMediaPlayer = CreateMediaPlayer.getInstance();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.bottom_sheet_main, parent, false);

        return new MyHolder(root);
    }

    public PhoneModelFragmentList getdata(int mPosition) {
        Log.d("TAG", "onScrolled: " + mPosition);
        return items.get(mPosition);

    }

    @Override

    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        PhoneModelFragmentList phoneModelFragmentList = items.get(position);
        holder.name_musec.setText(phoneModelFragmentList.getName());
        holder.name_musec.setSelected(true);
        mPosition = position;
        holder.bind(position, listener);
        setImage(holder.start_stop);

    }


    public int getPosition() {
        return mPosition;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setList(ArrayList<PhoneModelFragmentList> items) {
        this.items = items;
    }

    @Override
    public void itemClick(ArrayList<PhoneModelFragmentList> items, int position, PhoneModelFragmentList phoneModel) {

    }

    public void setImage(ImageView image) {
        imageView = image;
    }

    public ImageView getImage() {
        return imageView;
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private ImageView start_stop, repet, menu;
        private SeekBar seekBar;
        private TextView tvStart, tvEnd, name_musec;
        private int count = 0;
        private int count2 = 0;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            start_stop = itemView.findViewById(R.id.im_stop_start_main);
            repet = itemView.findViewById(R.id.pre_image_main);
            seekBar = itemView.findViewById(R.id.seekBar);
            tvStart = itemView.findViewById(R.id.start);
            tvEnd = itemView.findViewById(R.id.end);
            name_musec = itemView.findViewById(R.id.name_musec);
        }

        public void bind(int position, ListItemClick listener) {
            itemView.setOnClickListener(v ->
            {
                listener.itemViewClick(position);
            });
            start_stop.setOnClickListener(v -> {

                switch (count) {
                    case 0:
                        Log.d("TAG", "bind: " + count);
                        start_stop.setImageResource(R.drawable.ic_start_stop);
                        listener.start_stop_music(position, 0, tvStart, tvEnd, seekBar, start_stop);
                        ++count;
                        Log.d("TAG", "bind: " + count);
                        break;
                    case 1:
                        Log.d("TAG", "bind: " + count);
                        start_stop.setImageResource(R.drawable.ic_play);
                        listener.start_stop_music(position, 1, tvStart, tvEnd, seekBar, start_stop);
                        count--;
                        Log.d("TAG", "bind: " + count);
                        break;
                }
            });
            repet.setOnClickListener(v -> {
                switch (count2) {
                    case 0:
                        repet.setImageResource(R.drawable.ic_replas0);
                        listener.repet(position, 0);
                        ++count2;
                        Log.d("TAG", "bind: " + count);
                        break;
                    case 1:
                        repet.setImageResource(R.drawable.ic_repet2);
                        listener.repet(position, 1);
                        count2++;
                        Log.d("TAG", "bind: " + count);
                        break;
                    case 2:
                        repet.setImageResource(R.drawable.ic_repeat);
                        listener.repet(position, 2);
                        count2 = 0;
                        Log.d("TAG", "bind: " + count);
                        break;
                }
            });
//            menu.setOnClickListener(v -> {
//                listener.menu(position);
//            });
        }
    }

    public interface ListItemClick {
        void itemViewClick(int position);

        void start_stop_music(int position, int isplay, TextView start, TextView end, SeekBar seekBar, ImageView imagView);

        void repet(int position, int connt);

        void menu(int position);
    }


}
