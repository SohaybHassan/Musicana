package com.prography.musicana.custem.bottomSeetAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prography.musicana.R;

public class ListSemelarMusicProfileAdapter extends RecyclerView.Adapter<ListSemelarMusicProfileAdapter.MyHolder> {
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_channels, parent, false);
        return new MyHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public MyHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
