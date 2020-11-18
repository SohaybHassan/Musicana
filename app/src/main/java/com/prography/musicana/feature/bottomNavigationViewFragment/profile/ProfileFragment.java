package com.prography.musicana.feature.bottomNavigationViewFragment.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.prography.musicana.AppConstants;
import com.prography.musicana.R;
import com.prography.musicana.SharedPreferencesHelper;
import com.prography.musicana.databinding.FragmentProfileBinding;
import com.prography.musicana.feature.OnFragmentInteractionListener;
import com.prography.musicana.feature.onboard.PrivacyPolicyActivity;
import com.prography.musicana.feature.onboard.TermsConditionsActivity;

import static android.content.Context.MODE_PRIVATE;


public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private BottomSheetDialog sheetDialog;
    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        sheetDialog = new BottomSheetDialog(getActivity());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.profileUserArrow.setOnClickListener(v -> {
            //1
            mListener.onFragmentInteraction(R.id.editProfileFragment);
        });

        ////*************/////
        binding.profileEntryPermitArrow.setOnClickListener(v -> {
            //2
            ProfileCustomBottomSheet sheet = new ProfileCustomBottomSheet(getActivity(), sheetDialog, new ProfileCustomBottomSheet.BottomSheetListener() {
                @Override
                public void onSwitchClicked(int id, boolean checked, Switch s1, Switch s2, Switch s3) {
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
        binding.profileMoodArrow.setOnClickListener(v -> {
            //3
            ProfileCustomBottomSheet sheet = new ProfileCustomBottomSheet(getActivity(), sheetDialog, new ProfileCustomBottomSheet.BottomSheetListener() {
                @Override
                public void onSwitchClicked(int id, boolean checked, Switch s1, Switch s2, Switch s3) {
                    SharedPreferences.Editor modeEditor = getActivity().getSharedPreferences(AppConstants.Mode, MODE_PRIVATE).edit();

                    switch (id) {
                        case 1:
                            s1.setChecked(checked);
                            s2.setChecked(!checked);
                            if (checked) {
                                modeEditor.putInt(AppConstants.Mode, 1);
                                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                            }else{
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
                            }else{
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
                    SharedPreferencesHelper.getMode(getContext())==1,getResources().getString(R.string.DarkMode),
                    SharedPreferencesHelper.getMode(getContext())==2, getResources().getString(R.string.MoonMode),
                    null,null,
                    false);
        });
        binding.profileLanguageArrow.setOnClickListener(v -> {
            //4
            ProfileCustomBottomSheet sheet = new ProfileCustomBottomSheet(getActivity(), sheetDialog, new ProfileCustomBottomSheet.BottomSheetListener() {
                @Override
                public void onSwitchClicked(int id, boolean checked, Switch s1, Switch s2, Switch s3) {
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
        binding.profilePrivacyPolicyArrow.setOnClickListener(v -> {
            //5
            startActivity(new Intent(getActivity(), PrivacyPolicyActivity.class));
        });
        binding.profileTermsConditionsArrow.setOnClickListener(v -> {
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
        binding.profileLogoffIcon.setOnClickListener(v -> {
            //log off
            Toast.makeText(getActivity(), "log off", Toast.LENGTH_SHORT).show();
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

}