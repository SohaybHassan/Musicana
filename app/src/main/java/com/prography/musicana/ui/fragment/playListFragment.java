package com.prography.musicana.ui.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prography.musicana.custem.dialog.SWDialog;
import com.prography.musicana.data.getallplaylist.Data;
import com.prography.musicana.databinding.FragmentPlayListBinding;
import com.prography.musicana.adapter.PlayListAdapter;
import com.prography.musicana.data.getallplaylist.GetAllPlayList;
import com.prography.musicana.data.getallplaylist.Playlist;
import com.prography.musicana.ui.activity.ShowPlaylisttSongAndData;
import com.prography.musicana.viewmodel.PlaylsitViewModel;
import com.prography.musicana.utils.SWStaticMethods;

import java.util.ArrayList;

public class playListFragment extends Fragment {
    private FragmentPlayListBinding binding;
    private ArrayList<Playlist> itemPlayList;
    private PlayListAdapter playListAdapter;
    private PlaylsitViewModel playlsitViewModel;
    private static final String TAG = playListFragment.class.getSimpleName();
    private SWDialog swDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPlayListBinding.inflate(inflater, container, false);
        itemPlayList = new ArrayList<>();
        playlsitViewModel = new ViewModelProvider(this).get(PlaylsitViewModel.class);
        Log.d("TAG", "onCreateView: " + itemPlayList.size());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getAllPlaylist();
        binding.rvPlayList.setLayoutManager(new LinearLayoutManager(getContext()));
        playListAdapter = new PlayListAdapter(itemPlayList, (position, playlist) -> {

            Log.d(TAG, "itemClicked:  clicled" + position);
            Bundle bundle = new Bundle();
            bundle.putSerializable("playlist", playlist);
            SWStaticMethods.intentWithDataWithoutfinish(getActivity(), ShowPlaylisttSongAndData.class, bundle);

        });
        binding.rvPlayList.setAdapter(playListAdapter);
        playListAdapter.notifyDataSetChanged();


        binding.tvAddPlayList.setOnClickListener(v -> {
            dialogshow();
        });
        binding.addMorePlaylist.setOnClickListener(v -> {
            dialogshow();
            playListAdapter.notifyDataSetChanged();
        });

    }

    public void createPLayList(String name) {
        playlsitViewModel.creatPlaylist(name).observe(getActivity(), createPlayList -> {
            if (createPlayList != null) {
                Log.d(TAG, "onChanged: " + createPlayList.getResponse().getData().getPlaylist().getName());
                Log.d(TAG, "onChanged: ");
            } else {
                Log.d(TAG, "onChanged:  no data");
            }
        });
    }

    public void getAllPlaylist() {
        playlsitViewModel.getAllPlayListLiveData().observe(getViewLifecycleOwner(), new Observer<Data>() {
            @Override
            public void onChanged(Data getAllPlayList) {
                if (getAllPlayList != null) {
                    itemPlayList.clear();
                    for (int i = 0; i < getAllPlayList.getPlaylists().size(); i++) {
                        itemPlayList.add(getAllPlayList.getPlaylists().get(i));
                        Log.d(TAG, "onChanged: " + getAllPlayList.getPlaylists().get(i).getName());
                    }


                    binding.rvPlayList.setLayoutManager(new LinearLayoutManager(getContext()));
                    binding.rvPlayList.setAdapter(playListAdapter);
                    playListAdapter.notifyDataSetChanged();

                    if (itemPlayList.size() == 0) {
                        binding.rvPlayList.setVisibility(View.GONE);
                        binding.tvAddPlayList.setVisibility(View.VISIBLE);
                        binding.imagePlayList.setVisibility(View.VISIBLE);
                    } else {
                        binding.rvPlayList.setVisibility(View.VISIBLE);
                        binding.tvAddPlayList.setVisibility(View.GONE);
                        binding.imagePlayList.setVisibility(View.GONE);
                        binding.addMorePlaylist.setVisibility(View.VISIBLE);
                    }


                } else {
                    Log.d(TAG, "onChanged: no data");
                }

            }
        });
    }

    public void dialogshow() {
        swDialog = new SWDialog(platListName -> {
            itemPlayList.clear();
            createPLayList(platListName.getText().toString());
            getAllPlaylist();
            swDialog.dismiss();

        });
        swDialog.show(getParentFragmentManager(), "hi thir");

        playListAdapter.notifyDataSetChanged();
    }
}