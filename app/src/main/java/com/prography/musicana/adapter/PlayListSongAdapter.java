package com.prography.musicana.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prography.musicana.R;
import com.prography.musicana.data.viewallsongtoplaylist.Datum;

import java.util.ArrayList;

public class PlayListSongAdapter extends RecyclerView.Adapter<PlayListSongAdapter.MyHolder> {
    private ArrayList<Datum> items;
    private ItemsSongClicked listener;

    public PlayListSongAdapter(ArrayList<Datum> items, ItemsSongClicked listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_music, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Datum data = items.get(position);
        holder.bind(listener, position, data);
    }

    @Override
    public int getItemCount() {
        return items != null ? 0 : items.size();

    }

    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView more;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            more = itemView.findViewById(R.id.image_favorite);
        }

        public void bind(ItemsSongClicked listener, int position, Datum data) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.clickedSongRun(position, data);
                }
            });
            more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.more(data);
                }
            });
        }

    }

    public interface ItemsSongClicked {
        void clickedSongRun(int position, Datum data);

        void more(Datum data);
    }
}
