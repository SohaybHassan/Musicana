
package com.prography.musicana.data;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.prography.musicana.data.Settings;


public class DataSettings implements Serializable
{

    @SerializedName("settings")
    @Expose
    private Settings settings;
    @SerializedName("default")
    @Expose
    private boolean _default;
    private final static long serialVersionUID = 6281724290759301038L;

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public boolean isDefault() {
        return _default;
    }

    public void setDefault(boolean _default) {
        this._default = _default;
    }

}
