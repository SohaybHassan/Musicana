package com.prography.musicana.feature.bottomNavigationViewFragment.home.PlayListFragment.model.createplaylist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

@SerializedName("Playlist")
@Expose
private Playlist playlist;

public Playlist getPlaylist() {
return playlist;
}

public void setPlaylist(Playlist playlist) {
this.playlist = playlist;
}

}