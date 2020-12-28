package com.prography.musicana.feature.bottomNavigationViewFragment.home.PlayListFragment;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.prography.musicana.databinding.FragmentPlayListBinding;

public class playListFragment extends Fragment {
    private FragmentPlayListBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentPlayListBinding.inflate(inflater, container, false);


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.imagePlayList.setVisibility(View.GONE);
        binding.tvAddPlayList.setVisibility(View.GONE);

        binding.rvPlayList.setVisibility(View.VISIBLE);
        binding.rvPlayList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvPlayList.setAdapter(new PlayListAdapter());


    }
}