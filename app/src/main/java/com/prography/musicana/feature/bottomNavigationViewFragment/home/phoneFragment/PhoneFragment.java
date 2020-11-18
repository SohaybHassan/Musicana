package com.prography.musicana.feature.bottomNavigationViewFragment.home.phoneFragment;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.prography.musicana.R;
import com.prography.musicana.databinding.FragmentPhoneBinding;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.phoneFragment.adapter.PhoneFragmentAdapter;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.phoneFragment.model.PhoneModelFragmentList;

import java.util.ArrayList;


public class PhoneFragment extends Fragment {

    public static final String TAG = PhoneFragment.class.getSimpleName();
    private FragmentPhoneBinding binding;
    private String[] STAR = {"*"};
    private ArrayList<PhoneModelFragmentList> items;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPhoneBinding.inflate(getLayoutInflater());
        items = new ArrayList<>();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listAllSong();

        binding.rvPhoneFragment.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvPhoneFragment.setAdapter(new PhoneFragmentAdapter(items, new PhoneFragmentAdapter.ClickItems() {
            @Override
            public void onClickItem(int position) {
                Toast.makeText(getActivity(), "Clicked items"+ position, Toast.LENGTH_SHORT).show();
            }
        }));


        Log.d(TAG, "onViewCreated: " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC));

    }

    public void listAllSong() {
        Cursor cursor;
        Uri allsongUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        if (isSdPresent()) {
            cursor = getActivity().getContentResolver().query(allsongUri, STAR, selection, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        String songName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                        int songId = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
                        String albumname = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                       // String Uri = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.getContentUri()));

                        items.add(new PhoneModelFragmentList(songName, albumname));
                        Log.d(TAG, "listAllSong: " + songName + "_####### _" + songId + "_############### _" + albumname);
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }

        }
    }

    public static boolean isSdPresent() {
        return android.os.Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
}