package com.prography.musicana.data.createplaylist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlaylistData {

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