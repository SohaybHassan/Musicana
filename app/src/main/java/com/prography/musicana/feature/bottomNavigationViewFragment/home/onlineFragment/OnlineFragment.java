package com.prography.musicana.feature.bottomNavigationViewFragment.home.onlineFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prography.musicana.R;
import com.prography.musicana.databinding.FragmentOnlineBinding;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.onlineFragment.adapter.ChannelsAdapter;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.onlineFragment.adapter.MusicAdapter;


public class OnlineFragment extends Fragment {

    FragmentOnlineBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentOnlineBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        binding.recyclerViewChannels.setLayoutManager(linearLayoutManager);
        ChannelsAdapter channelsAdapter = new ChannelsAdapter();
        binding.recyclerViewChannels.setAdapter(channelsAdapter);


        binding.recyclerViewMusic.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerViewMusic.setAdapter(new MusicAdapter());

    }
}