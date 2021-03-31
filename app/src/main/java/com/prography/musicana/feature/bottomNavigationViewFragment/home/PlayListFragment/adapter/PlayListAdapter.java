package com.prography.musicana.feature.bottomNavigationViewFragment.home.PlayListFragment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.prography.musicana.R;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.PlayListFragment.model.getallplaylist.Playlist;

import java.util.ArrayList;

public class PlayListAdapter extends RecyclerView.Adapter<PlayListAdapter.MyHolder> {

    private ArrayList<Playlist> items;
    private PlaylistClicked listener;

    public PlayListAdapter(ArrayList<Playlist> items, PlaylistClicked listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_play_list, parent, false);
        return new MyHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Playlist playlist = items.get(position);
        holder.tv_name.setText(playlist.getName());
        holder.bind(listener, playlist, position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        TextView tv_name;


        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name_play_list);

        }

        public void bind(PlaylistClicked listener, Playlist playlist, int position) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.itemClicked(position, playlist);
                }
            });
        }
    }


    public interface PlaylistClicked {
        void itemClicked(int position, Playlist playlist);
    }
}
