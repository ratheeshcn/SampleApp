package com.intigral.ui.util.instrumentation;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.google.common.eventbus.EventBus;
import com.intigral.net.IntigralApi;

/**
 * Created by Marcin Przepiórkowski
 * @since 17.05.16.
 */
public interface InstrumentationProvider {
    @NonNull Context getActivityContext();
    @NonNull Resources getResources();
    @NonNull String getResString(@StringRes int id);
    @NonNull
    IntigralApi getIntigralApi();
    @NonNull EventBus getEventBus();
    @NonNull FragmentManager getFragmentManager();


    int checkPermission(@NonNull String permission);
    void requestPermissions(@NonNull String[] permissions, int requestCode);
    void startService(@NonNull Intent intent);
    void startActivityForResult(@NonNull Intent intent, int requestCode);
    void startActivity(@NonNull Class<? extends AppCompatActivity> activity);
    void startActivity(@NonNull Intent intent);
    void setResult(int resultCode, @NonNull Intent intent);
    void finishActivity();
    void replaceFragment(@IdRes int layout, @NonNull Fragment fragment);
}
