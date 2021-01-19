package com.prography.musicana.feature.regester.model.verification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Settings {

    @SerializedName("permissions")
    @Expose
    private Permissions permissions;
    @SerializedName("theme")
    @Expose
    private Theme theme;
    @SerializedName("language")
    @Expose
    private Language language;
    @SerializedName("additional_screen")
    @Expose
    private boolean additionalScreen;
    @SerializedName("auto_update")
    @Expose
    private boolean autoUpdate;

    public Permissions getPermissions() {
        return permissions;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public boolean isAdditionalScreen() {
        return additionalScreen;
    }

    public void setAdditionalScreen(boolean additionalScreen) {
        this.additionalScreen = additionalScreen;
    }

    public boolean isAutoUpdate() {
        return autoUpdate;
    }

    public void setAutoUpdate(boolean autoUpdate) {
        this.autoUpdate = autoUpdate;
    }

}