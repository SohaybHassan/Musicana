package com.prography.musicana.ui.fragment;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.google.android.material.tabs.TabLayout;
import com.prography.musicana.R;
import com.prography.musicana.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    AdView adView;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());

        return binding.getRoot();


    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //fb ads
        adView = new AdView(getContext(), getResources().getString(R.string.fb_eg), AdSize.BANNER_HEIGHT_50);
        binding.bannerContainer.addView(adView);
        adView.loadAd();

        getChildFragmentManager().beginTransaction().replace(R.id.home_contener, new OnlineFragment()).commit();

        binding.tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        getChildFragmentManager().beginTransaction().replace(R.id.home_contener, new OnlineFragment()).commit();
                        break;
                    case 1:
                        getChildFragmentManager().beginTransaction().replace(R.id.home_contener, new PhoneFragment()).commit();
                        break;
                    case 2:
                        getChildFragmentManager().beginTransaction().replace(R.id.home_contener, new playListFragment()).commit();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


}