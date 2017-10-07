package com.intigral.settings;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.common.base.Optional;
import com.intigral.IntigralApplication;

import static com.google.common.base.Preconditions.checkNotNull;
/**
 * Created by Retheesh CN
 * @since 06.10.2017
 */
public class AppSettings  {

    private static final String SETTINGS_NAME = "appSettings";
    public static final String SETTINGS_STORE_ID = "store_id";
    private static final String IMAGE_BASE_URL = "image_base_url";
    protected static final String IS_CONFIGURATION_DONE = "is_configuration";
    protected static final String POSTER_SIZE = "poster_size";
    private static final long INVALID_VALUE = -1;


    private static AppSettings sInstance;
    private SharedPreferences mSettings;

    private AppSettings() {
        mSettings = IntigralApplication.getAppContext().getSharedPreferences(SETTINGS_NAME, Activity.MODE_PRIVATE);
    }

    public
    @NonNull
    static AppSettings getInstance() {
        if (sInstance == null) {
            sInstance = new AppSettings();
        }
        return sInstance;
    }

    public AppSettings(@NonNull SharedPreferences sharedPreferences) {
        mSettings = checkNotNull(sharedPreferences);
    }

    public void saveToSettings(@NonNull String key, @NonNull String value) {

        Log.e("APP SETTINGS","KEy = "+key +"VALUE = "+value);
        SharedPreferences.Editor preferencesEditor = mSettings.edit();
        preferencesEditor.putString(checkNotNull(key), checkNotNull(value));
        preferencesEditor.apply();
    }

    public void saveToSettings(@NonNull String key, long value) {
        SharedPreferences.Editor preferencesEditor = mSettings.edit();
        preferencesEditor.putLong(checkNotNull(key), value);
        preferencesEditor.apply();
    }

    public void saveToSettings(@NonNull String key, boolean value) {
        checkNotNull(key, "key cannot be null");

        SharedPreferences.Editor preferencesEditor = mSettings.edit();
        preferencesEditor.putBoolean(key, value);

        preferencesEditor.apply();
    }

    protected
    @NonNull
    Optional<String> loadFromSettings(@NonNull String key) {
        String value = mSettings.getString(checkNotNull(key), null);
        return Optional.fromNullable(value);
    }


    public boolean loadBoolFromSettings(@NonNull String key) {
        checkNotNull(key, "key cannot be null");

        return mSettings.getBoolean(key, false);
    }

    public long loadLongFromSettings(@NonNull String key) {
        return mSettings.getLong(checkNotNull(key), INVALID_VALUE);
    }

    protected void clearSetting(@NonNull String key) {
        SharedPreferences.Editor editor = mSettings.edit();
        editor.remove(checkNotNull(key));
        editor.apply();
    }

    public void clearSettings() {
        SharedPreferences.Editor editor = mSettings.edit();
        editor.clear();
        editor.apply();
    }



    public void saveImageBaseUrl(@NonNull String refreshToken) {
        saveToSettings(IMAGE_BASE_URL, checkNotNull(refreshToken));
    }

    public void savePosterSize(@NonNull String poster) {
        saveToSettings(POSTER_SIZE, checkNotNull(poster));
    }

    public void saveStoreId(long storeId) {
        saveToSettings(SETTINGS_STORE_ID, storeId);
    }


    public void setConfigurationAdded(@NonNull Boolean status) {
        saveToSettings(IS_CONFIGURATION_DONE, checkNotNull(status));
    }



    public
    @NonNull
    Optional<String> getImageBaseUrl() {
        return loadFromSettings(IMAGE_BASE_URL);
    }


    public
    @NonNull
    Optional<String> loadPosterSize() {
        return loadFromSettings(POSTER_SIZE);
    }

    public long loadStoreId() {
        return loadLongFromSettings(SETTINGS_STORE_ID);
    }


    public boolean isConfigurationAdded() {
        return loadBoolFromSettings(IS_CONFIGURATION_DONE);
    }


    public void clearSpecificSettings() {

        clearSetting(SETTINGS_STORE_ID);
        clearSetting(IMAGE_BASE_URL);
        clearSetting(IS_CONFIGURATION_DONE);


    }
}
