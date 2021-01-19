package com.prography.musicana.feature.bottomNavigationViewFragment.home.PlayListFragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.prography.musicana.custem.SWInterface.PlayListName;
import com.prography.musicana.databinding.FragmentPlayListBinding;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.phoneFragment.model.AddToList;

import java.util.ArrayList;

public class playListFragment extends Fragment implements PlayListName {
    private FragmentPlayListBinding binding;
    private ArrayList<String> itemPlayList;
    private PlayListAdapter playListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPlayListBinding.inflate(inflater, container, false);
        itemPlayList = new ArrayList<>();
        Log.d("TAG", "onCreateView: " + itemPlayList.size());

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AddToList addToList = AddToList.getInstance();
        itemPlayList = addToList.getAdditem();

        if (itemPlayList != null) {
            Log.d("TAG", "onViewCreated: " + "list not null");
            Log.d("TAG", "onViewCreated: " + itemPlayList.size());
            binding.rvPlayList.setLayoutManager(new LinearLayoutManager(getContext()));
            playListAdapter = new PlayListAdapter(itemPlayList);
            binding.rvPlayList.setAdapter(playListAdapter);
            playListAdapter.notifyDataSetChanged();
            binding.imagePlayList.setVisibility(View.GONE);
            binding.tvAddPlayList.setVisibility(View.GONE);
            binding.rvPlayList.setVisibility(View.VISIBLE);
        } else {
            Log.d("TAG", "onViewCreated: " + "list  null");
            binding.imagePlayList.setVisibility(View.VISIBLE);
            binding.tvAddPlayList.setVisibility(View.VISIBLE);
            binding.rvPlayList.setVisibility(View.GONE);
        }


    }

    @Override
    public void playListName(EditText name) {
        if (name == null) {
            Log.d("TAG", "playListName: " + "name null");
        } else {
            ArrayList<String> mydata = new ArrayList<>();
            mydata.add(name.getText().toString());
            itemPlayList = mydata;
            Log.d("TAG", "playListName: " + itemPlayList.size());
        }

    }
}