package com.prography.musicana.custem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.prography.musicana.R;
import com.prography.musicana.feature.CreateMediaPlayer;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.phoneFragment.model.PhoneModelFragmentList;
import com.prography.musicana.utils.MusicaApp;

public class BottomSheetMore {

    private Context application;
    private BottomSheetDialog mDialog;
    private BottomSheetMoreMethode mListener;


    private TextView tv_add_to_play_list, tv_downlode, tv_share, tv_add_to_favorite;


    public BottomSheetMore(Context context, BottomSheetDialog dialog, BottomSheetMoreMethode listener) {
        this.application = context;
        this.mListener = listener;
        this.mDialog = dialog;

    }


    public void openDialog() {

        View root = LayoutInflater.from(application).inflate(R.layout.bottomsheet_more, null);
        mDialog = new BottomSheetDialog(application);

        mDialog.setContentView(root);
        mDialog.show();
        find(root);
        lisener();


    }


    public void find(View v) {

        tv_add_to_play_list = v.findViewById(R.id.tv_add_to_play_list);
        tv_downlode = v.findViewById(R.id.tv_downlode);
        tv_share = v.findViewById(R.id.tv_share);
        tv_add_to_favorite = v.findViewById(R.id.tv_add_to_favorite);

    }

    public void lisener() {
        tv_add_to_play_list.setOnClickListener(v -> {
            mListener.addtoplayList();
            mDialog.dismiss();
        });
        tv_downlode.setOnClickListener(v -> {
            mListener.downlode();
            mDialog.dismiss();
        });
        tv_share.setOnClickListener(v -> {
            mListener.share();
            mDialog.dismiss();
        });
        tv_add_to_favorite.setOnClickListener(v -> {
            mListener.addToFavorite();
            mDialog.dismiss();
        });
    }


    public interface BottomSheetMoreMethode {

        void addtoplayList();

        void downlode();

        void share();

        void addToFavorite();


    }


}
