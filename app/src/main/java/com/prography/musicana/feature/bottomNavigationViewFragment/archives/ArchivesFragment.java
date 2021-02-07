package com.prography.musicana.feature.bottomNavigationViewFragment.archives;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.prography.musicana.R;
import com.prography.musicana.databinding.FragmentArchivesBinding;
import com.prography.musicana.feature.bottomNavigationViewFragment.archives.adapter.ArchivesAdapter;


public class ArchivesFragment extends Fragment {


    private FragmentArchivesBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentArchivesBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.rvArchives.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvArchives.setAdapter(new ArchivesAdapter());

        MobileAds.initialize(getContext(), initializationStatus -> {

        });
        AdRequest request = new AdRequest.Builder().build();
        binding.adView.loadAd(request);
    }
}