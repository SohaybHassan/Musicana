
package com.prography.musicana.feature.bottomNavigationViewFragment.profile.model.allsettings;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Permissions implements Serializable
{

    @SerializedName("Running In Background")
    @Expose
    private boolean runningInBackground;
    @SerializedName("Entry Permit Audio Files")
    @Expose
    private boolean entryPermitAudioFiles;
    @SerializedName("Location ")
    @Expose
    private boolean location;
    private final static long serialVersionUID = -5358474374396448138L;

    public boolean isRunningInBackground() {
        return runningInBackground;
    }

    public void setRunningInBackground(boolean runningInBackground) {
        this.runningInBackground = runningInBackground;
    }

    public boolean isEntryPermitAudioFiles() {
        return entryPermitAudioFiles;
    }

    public void setEntryPermitAudioFiles(boolean entryPermitAudioFiles) {
        this.entryPermitAudioFiles = entryPermitAudioFiles;
    }

    public boolean isLocation() {
        return location;
    }

    public void setLocation(boolean location) {
        this.location = location;
    }

}
