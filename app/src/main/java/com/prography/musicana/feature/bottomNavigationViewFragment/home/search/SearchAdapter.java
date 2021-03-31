package com.prography.musicana.feature.bottomNavigationViewFragment.home.search;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.prography.musicana.R;
import com.prography.musicana.feature.home.model.search.Result;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_AUDIO = 1;
    private static final int TYPE_CHANNEL = 2;
    private static final int TYPE_PLAYLIST = 3;
    private static final int TYPE_LOADING = 4;
    private boolean isLoaderVisible = false;

    private ArrayList<Result> items;
    private Context context;


    public SearchAdapter(ArrayList<Result> items, Context context) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {
            case TYPE_AUDIO:
                return new Audio(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_audio, parent, false));
            case TYPE_CHANNEL:
                return new Channel(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_channel, parent, false));

            case TYPE_PLAYLIST:
                return new PlayList(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_playlast, parent, false));

            case TYPE_LOADING:
                return new Loading(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_footer, parent, false));
            default:
                return null;

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == TYPE_AUDIO) {
            ((Audio) holder).onbind(position);
        } else if (getItemViewType(position) == TYPE_CHANNEL) {
            ((Channel) holder).onbind(position);
        } else if (getItemViewType(position) == TYPE_PLAYLIST) {
            ((PlayList) holder).onbind(position);

        }

    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public int getItemViewType(int position) {


        if (items.get(position).getType().equals("audio")) {
            return TYPE_AUDIO;
        } else if (items.get(position).getType().equals("channel")) {
            return TYPE_CHANNEL;
        } else if (items.get(position).getType().equals("playlist")) {
            return TYPE_PLAYLIST;
        } else {
            return TYPE_LOADING;

        }
    }

    public void addLoading() {
        isLoaderVisible = true;
        items.add(new Result());
        notifyItemInserted(items.size() - 1);
    }

    public void removeLoading() {
        isLoaderVisible = false;
        int position = items.size() - 1;
        Result item = items.get(position);
        if (item != null) {
            items.remove(position);
            notifyItemRemoved(position);
        }
    }


    public class Audio extends RecyclerView.ViewHolder {
        TextView name, type;
        ImageView image;

        public Audio(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_musec);
            type = itemView.findViewById(R.id.type_musec);
            image = itemView.findViewById(R.id.image_music);
        }


        public void onbind(int position) {

            Result result = items.get(position);
            name.setText(result.getName());
            type.setText(result.getType());
            Glide.with(context).load(result.getImg()).into(image);
        }
    }

    public class Channel extends RecyclerView.ViewHolder {
        TextView name, type;
        CircleImageView image;

        public Channel(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_musec);
            type = itemView.findViewById(R.id.type_musec);
            image = itemView.findViewById(R.id.image_music);
        }


        public void onbind(int position) {

            Result result = items.get(position);
            name.setText(result.getName());
            type.setText(result.getType());
            Glide.with(context).load(result.getImg()).into(image);
        }
    }

    public class PlayList extends RecyclerView.ViewHolder {

        TextView name, type;
        ImageButton image;

        public PlayList(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name_play_list);
            type = itemView.findViewById(R.id.tv_type);
            image = itemView.findViewById(R.id.image_play_list);
        }


        public void onbind(int position) {

            Result result = items.get(position);
            name.setText(result.getName());
            type.setText(result.getType());
            Glide.with(context).load(result.getImg()).into(image);
        }
    }

    public class Loading extends RecyclerView.ViewHolder {


        ProgressBar loading;

        public Loading(@NonNull View itemView) {
            super(itemView);
            loading = itemView.findViewById(R.id.ProgressBar);
        }


    }


}
