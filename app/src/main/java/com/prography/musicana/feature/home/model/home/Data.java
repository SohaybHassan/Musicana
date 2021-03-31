package com.prography.musicana.feature.home.model.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("videos")
    @Expose
    private List<Video> videos = null;
    @SerializedName("channels")
    @Expose
    private List<Channel> channels = null;
    @SerializedName("video_count")
    @Expose
    private int videoCount;
    @SerializedName("channel_count")
    @Expose
    private int channelCount;

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    public int getVideoCount() {
        return videoCount;
    }

    public void setVideoCount(int videoCount) {
        this.videoCount = videoCount;
    }

    public int getChannelCount() {
        return channelCount;
    }

    public void setChannelCount(int channelCount) {
        this.channelCount = channelCount;
    }

}