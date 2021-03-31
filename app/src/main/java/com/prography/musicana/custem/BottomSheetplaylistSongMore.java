package com.prography.musicana.custem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.prography.musicana.R;

public class BottomSheetplaylistSongMore {

    private Context application;
    private BottomSheetDialog mDialog;
    private BottomSheetPlaylistSongMoreMethode listener;

    private TextView delete, tv_share, tv_add_to_favorite;

    public BottomSheetplaylistSongMore(Context application, BottomSheetDialog mDialog, BottomSheetPlaylistSongMoreMethode listener) {
        this.application = application;
        this.mDialog = mDialog;
        this.listener = listener;
    }


    public void openDialog() {
        View root = LayoutInflater.from(application).inflate(R.layout.bottomsheet_playlist_song_more, null);
        mDialog = new BottomSheetDialog(application);
        mDialog.setContentView(root);
        mDialog.show();
        find(root);
        lisener();
    }

    public void find(View v) {
        delete = v.findViewById(R.id.tv_delete);
        tv_share = v.findViewById(R.id.tv_share);
        tv_add_to_favorite = v.findViewById(R.id.tv_add_to_favorite);
    }

    public void lisener() {
        delete.setOnClickListener(v -> {
            listener.delete();
            mDialog.dismiss();
        });

        tv_share.setOnClickListener(v -> {
            listener.share();
            mDialog.dismiss();
        });
        tv_add_to_favorite.setOnClickListener(v -> {
            listener.addToFavorite();
            mDialog.dismiss();
        });
    }

    public interface BottomSheetPlaylistSongMoreMethode {

        void delete();

        void share();

        void addToFavorite();


    }
}
