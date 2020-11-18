package com.prography.musicana.feature.bottomNavigationViewFragment.home.phoneFragment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prography.musicana.R;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.phoneFragment.model.PhoneModelFragmentList;

import java.util.ArrayList;

public class PhoneFragmentAdapter extends RecyclerView.Adapter<PhoneFragmentAdapter.MyHolder> {

    private ArrayList<PhoneModelFragmentList> item;
    private ClickItems clickItems;

    public PhoneFragmentAdapter(ArrayList<PhoneModelFragmentList> item, ClickItems clickItems) {
        this.item = item;
        this.clickItems = clickItems;
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phone_list, parent, false);
        return new MyHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        PhoneModelFragmentList phoneModelFragmentList = item.get(position);
        holder.name.setText(phoneModelFragmentList.getName());
        holder.alpom.setText(phoneModelFragmentList.getAlpom());
        int musicNumber = position + 1;
        holder.number_music.setText(musicNumber + "");
        holder.bind(clickItems, position);
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView name, alpom, number_music;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_musec);
            alpom = itemView.findViewById(R.id.type_musec);
            number_music = itemView.findViewById(R.id.number_music);

        }

        public void bind(ClickItems listener, int position) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClickItem(position);
                }
            });
        }


    }

    public interface ClickItems {
        void onClickItem(int position);
    }
}
