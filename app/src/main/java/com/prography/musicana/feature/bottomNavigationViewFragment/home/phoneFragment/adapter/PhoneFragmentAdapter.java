package com.prography.musicana.feature.bottomNavigationViewFragment.home.phoneFragment.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
        holder.imageView.setImageURI(phoneModelFragmentList.getUri());
        holder.bind(clickItems, position, phoneModelFragmentList);
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView name, alpom;
        ImageView imageView, image_more;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_musec);
            alpom = itemView.findViewById(R.id.type_musec);
            imageView = itemView.findViewById(R.id.image_music);
            image_more = itemView.findViewById(R.id.image_more);

        }

        public void bind(ClickItems listener, int position, PhoneModelFragmentList phoneModel) {
            itemView.setOnClickListener(v -> listener.onClickItem(position, phoneModel));

            image_more.setOnClickListener(v -> {
                listener.onClickMore(phoneModel);
            });

        }


    }

    public interface ClickItems {
        void onClickItem(int position, PhoneModelFragmentList phoneModel);

        void onClickMore(PhoneModelFragmentList phoneMusic);
    }


}
