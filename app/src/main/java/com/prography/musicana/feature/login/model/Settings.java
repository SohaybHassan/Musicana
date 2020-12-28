package com.prography.musicana.feature.login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Settings {

    @SerializedName("permissions")
    @Expose
    private Permissions permissions;
    @SerializedName("additional_screen")
    @Expose
    private boolean additionalScreen;
    @SerializedName("language")
    @Expose
    private Language language;
    @SerializedName("theme")
    @Expose
    private Theme theme;
    @SerializedName("auto_update")
    @Expose
    private boolean autoUpdate;
    private final static long serialVersionUID = 7502131597959671555L;

    public Permissions getPermissions() {
        return permissions;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    public boolean isAdditionalScreen() {
        return additionalScreen;
    }

    public void setAdditionalScreen(boolean additionalScreen) {
        this.additionalScreen = additionalScreen;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public boolean isAutoUpdate() {
        return autoUpdate;
    }

    public void setAutoUpdate(boolean autoUpdate) {
        this.autoUpdate = autoUpdate;
    }

}