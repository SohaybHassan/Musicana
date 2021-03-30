package com.prography.musicana.feature.bottomNavigationViewFragment.profile.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.prography.musicana.AppConstants;
import com.prography.musicana.R;
import com.prography.musicana.SharedPreferencesHelper;
import com.prography.musicana.custem.SWInterface.OnFragmentInteractionListener;
import com.prography.musicana.databinding.FragmentProfileBinding;
import com.prography.musicana.feature.bottomNavigationViewFragment.profile.view.ProfileCustomBottomSheet;
import com.prography.musicana.feature.bottomNavigationViewFragment.profile.viewmodel.ProfileViewModel;
import com.prography.musicana.feature.onboard.view.PrivacyPolicyActivity;
import com.prography.musicana.feature.onboard.view.TermsConditionsActivity;

import static android.content.Context.MODE_PRIVATE;


public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private BottomSheetDialog sheetDialog;
    private OnFragmentInteractionListener mListener;
    AdView adView;
    private ProfileViewModel profileViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        sheetDialog = new BottomSheetDialog(getActivity());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //fb ads
        adView = new AdView(getContext(), getResources().getString(R.string.fb_eg), AdSize.BANNER_HEIGHT_50);
        binding.bannerContainer.addView(adView);
        adView.loadAd();

        binding.profileUserName.setOnClickListener(v -> {
            //1
            mListener.onFragmentInteraction(R.id.editProfileFragment);
        });

        ////*************/////
        binding.profileEntryPermit.setOnClickListener(v -> {
            //2
            ProfileCustomBottomSheet sheet = new ProfileCustomBottomSheet(getActivity(), sheetDialog, new ProfileCustomBottomSheet.BottomSheetListener() {
                @Override
                public void onSwitchClicked(int id, boolean checked, SwitchCompat s1, SwitchCompat s2, SwitchCompat s3) {
                    switch (id) {
                        case 1:
                            System.out.println("switch 1 is " + checked);
                            break;
                        case 2:
                            System.out.println("switch 2 is " + checked);
                            break;
                        case 3:
                            System.out.println("switch 3 is " + checked);
                            break;
                    }
                    System.out.println("switch" + id + " is " + checked);
                }
            });
            sheet.openDialog(getResources().getString(R.string.EntryPermit),
                    null, getResources().getString(R.string.RunningBackground),
                    null, getResources().getString(R.string.EntryPermitAudioFiles),
                    null, getResources().getString(R.string.Location),
                    true);
        });
        binding.profileMood.setOnClickListener(v -> {
            //3
            ProfileCustomBottomSheet sheet = new ProfileCustomBottomSheet(getActivity(), sheetDialog, new ProfileCustomBottomSheet.BottomSheetListener() {
                @Override
                public void onSwitchClicked(int id, boolean checked, SwitchCompat s1, SwitchCompat s2, SwitchCompat s3) {
                    SharedPreferences.Editor modeEditor = getActivity().getSharedPreferences(AppConstants.Mode, MODE_PRIVATE).edit();

                    switch (id) {
                        case 1:
                            s1.setChecked(checked);
                            s2.setChecked(!checked);
                            if (checked) {
                                modeEditor.putInt(AppConstants.Mode, 1);
                                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                            } else {
                                modeEditor.putInt(AppConstants.Mode, 2);
                                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                            }
                            modeEditor.apply();
                            modeEditor.commit();
                            break;
                        case 2:
                            s1.setChecked(!checked);
                            s2.setChecked(checked);
                            if (checked) {
                                modeEditor.putInt(AppConstants.Mode, 2);
                                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                            } else {
                                modeEditor.putInt(AppConstants.Mode, 1);
                                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                            }
                            modeEditor.apply();
                            modeEditor.commit();
                            break;
                    }
                }
            });
            sheet.openDialog(getResources().getString(R.string.Mood),
                    SharedPreferencesHelper.getMode(getContext()) == 1, getResources().getString(R.string.DarkMode),
                    SharedPreferencesHelper.getMode(getContext()) == 2, getResources().getString(R.string.MoonMode),
                    null, null,
                    false);
        });
        binding.profileLanguage.setOnClickListener(v -> {
            //4
            ProfileCustomBottomSheet sheet = new ProfileCustomBottomSheet(getActivity(), sheetDialog, new ProfileCustomBottomSheet.BottomSheetListener() {
                @Override
                public void onSwitchClicked(int id, boolean checked, SwitchCompat s1, SwitchCompat s2, SwitchCompat s3) {
                    switch (id) {
                        case 1:
                            System.out.println("switch 1 is " + checked);
                            break;
                        case 2:
                            System.out.println("switch 2 is " + checked);
                            break;
                    }
                    System.out.println("switch" + id + " is " + checked);
                }
            });
            sheet.openDialog(getResources().getString(R.string.Language),
                    null, getResources().getString(R.string.English),
                    null, getResources().getString(R.string.Arabic),
                    null, null,
                    false);
        });

        ////***********////
        binding.profileAdditionScreenSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            //switch 1
            if (isChecked) {
                Toast.makeText(getActivity(), "checked", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "unchecked", Toast.LENGTH_SHORT).show();
            }
        });

        ////***********////
        binding.profilePrivacyPolicy.setOnClickListener(v -> {
            //5
            startActivity(new Intent(getActivity(), PrivacyPolicyActivity.class));
        });
        binding.profileTermsConditions.setOnClickListener(v -> {
            //6
            startActivity(new Intent(getActivity(), TermsConditionsActivity.class));
        });

        ////********////
        binding.profileAutomaticUpdatesSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            //switch 1
            if (isChecked) {
                Toast.makeText(getActivity(), "checked", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "unchecked", Toast.LENGTH_SHORT).show();
            }
        });

        //////********///
        binding.profileLogoff.setOnClickListener(v -> {
            //log off
            Toast.makeText(getActivity(), "log off", Toast.LENGTH_SHORT).show();
        });

        // settings requests
        getAllSettings();
        changeSettings("1","1","1","1","1","1","1");
    }

    public void getAllSettings() {
        profileViewModel.getAllSettings().observe(getActivity(), settingsResponse -> {
            if (settingsResponse != null) {
                Log.d("settingsResponse", "update profile Not null");
            } else {
                Log.d("settingsResponse", "update profile null");
            }
        });
    }

    public void changeSettings(String mood, String language, String additional_screen, String auto_update, String background, String audio, String location) {
        profileViewModel.changeSettings(mood, language, additional_screen, auto_update, background, audio, location)
                .observe(getActivity(), settingsResponse -> {
                    if (settingsResponse != null) {
                        Log.d("settingsResponse", "update profile Not null");
                    } else {
                        Log.d("settingsResponse", "update profile null");
                    }
                });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
}