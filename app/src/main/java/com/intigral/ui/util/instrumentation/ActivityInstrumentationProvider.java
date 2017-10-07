package com.intigral.ui.util.instrumentation;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.google.common.eventbus.EventBus;
import com.intigral.IntigralApplication;
import com.intigral.net.IntigralApi;
import com.intigral.net.IntigralApiFactory;
import com.intigral.ui.view.activity.BaseActivity;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Marcin Przepiórkowski
 * @since 17.05.16.
 */
public class ActivityInstrumentationProvider implements InstrumentationProvider {
    private final BaseActivity mActivity;

    private ActivityInstrumentationProvider(@NonNull BaseActivity a) {
        mActivity = checkNotNull(a);
    }



    @Override
    public @NonNull Context getActivityContext() {
        return mActivity;
    }

    @Override
    public @NonNull Resources getResources() {
        return mActivity.getResources();
    }

    @Override
    public @NonNull String getResString(@StringRes int id) {
        return mActivity.getString(id);
    }

    @Override
    public @NonNull
    IntigralApi getIntigralApi() {
        return IntigralApiFactory.getInstance().getApiClient();
    }

    @Override
    public void startActivityForResult(@NonNull Intent intent, int requestCode) {
        mActivity.startActivityForResult(checkNotNull(intent), requestCode);
    }

    @Override
    public void startActivity(@NonNull Class<? extends AppCompatActivity> activity) {
        Intent intent = new Intent(mActivity, checkNotNull(activity));
        mActivity.startActivity(intent);
    }

    @Override
    public void setResult( int resultCode,@NonNull Intent intent) {
        mActivity.setResult(resultCode,checkNotNull(intent));
    }
    @Override
    public void startActivity(@NonNull Intent intent) {
        mActivity.startActivity(checkNotNull(intent));
    }

    @Override
    public void startService(@NonNull Intent intent) {
        mActivity.startService(checkNotNull(intent));
    }

    @Override
    public int checkPermission(@NonNull String permission) {
        return ContextCompat.checkSelfPermission(mActivity, checkNotNull(permission));
    }

    @Override
    public void requestPermissions(@NonNull String[] permissions, int requestCode) {
        ActivityCompat.requestPermissions(mActivity, checkNotNull(permissions), requestCode);
    }

    @Override
    public @NonNull EventBus getEventBus() {
        return IntigralApplication.getEventBus();
    }

    @NonNull
    @Override
    public FragmentManager getFragmentManager() {
        return getFragmentManager();
    }

    @Override
    public void finishActivity() {
        mActivity.finish();
    }

    @Override
    public void replaceFragment(@IdRes int layout, @NonNull Fragment fragment) {
        mActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(layout, checkNotNull(fragment))
                .commit();
    }

    public static @NonNull ActivityInstrumentationProvider from(@NonNull BaseActivity a) {
        return new ActivityInstrumentationProvider(a);
    }
}
