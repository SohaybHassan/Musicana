package com.prography.musicana.feature.bottomNavigationViewFragment.home;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.prography.musicana.R;
import com.prography.musicana.databinding.FragmentHomeBinding;
import com.prography.musicana.feature.MainActivity;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.mapFragment.MapFragment;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.onlineFragment.OnlineFragment;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.phoneFragment.PhoneFragment;


public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        return binding.getRoot();


    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


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
                        getChildFragmentManager().beginTransaction().replace(R.id.home_contener, new MapFragment()).commit();
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