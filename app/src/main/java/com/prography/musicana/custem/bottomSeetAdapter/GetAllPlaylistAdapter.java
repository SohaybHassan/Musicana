package com.prography.musicana.custem.bottomSeetAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prography.musicana.R;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.PlayListFragment.model.getallplaylist.Playlist;

import java.util.ArrayList;

public class GetAllPlaylistAdapter extends RecyclerView.Adapter<GetAllPlaylistAdapter.MyHodel> {
    private ArrayList<Playlist> items = new ArrayList<>();
    private AddSongToPlaylist listener;

    public GetAllPlaylistAdapter(ArrayList<Playlist> items, AddSongToPlaylist listener) {
        this.listener = listener;
        this.items = items;
    }

    @NonNull
    @Override
    public MyHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHodel(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_getallplaylist_bpttomsheet, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHodel holder, int position) {
        Playlist name = items.get(position);
        holder.name.setText(name.getName());
        holder.bind(listener, name);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setList(ArrayList<Playlist> myListItem) {
        this.items = myListItem;
    }

    public class MyHodel extends RecyclerView.ViewHolder {
        TextView name;

        public MyHodel(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameplaylist);
        }

        public void bind(AddSongToPlaylist listener, Playlist playlist) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.addSong(playlist.getId());
                }
            });
        }
    }

    public interface AddSongToPlaylist {
        void addSong(String playlistid);
    }
}
