package com.prography.musicana.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.prography.musicana.databinding.FragmentFavoriteBinding;
import com.prography.musicana.adapter.FavoriteAdapter;
import com.prography.musicana.viewmodel.FavoiteViewModel;


public class FavoriteFragment extends Fragment {
    private FragmentFavoriteBinding binding;
    private FavoiteViewModel favofiteViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFavoriteBinding.inflate(getLayoutInflater());
        favofiteViewModel=new ViewModelProvider(this).get(FavoiteViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.rvFavorite.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvFavorite.setAdapter(new FavoriteAdapter());

        MobileAds.initialize(getContext(), initializationStatus -> {

        });
        AdRequest request = new AdRequest.Builder().build();
        binding.adView.loadAd(request);
        binding.adView.setAdListener(new AdListener(){

        });
    }



}