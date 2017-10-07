package com.intigral.ui.model.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.google.common.eventbus.EventBus;
import com.intigral.net.IntigralApi;
import com.intigral.ui.model.BaseViewModel;
import com.intigral.ui.util.binding.ObservableString;
import com.intigral.ui.util.instrumentation.InstrumentationProvider;

import static com.google.common.base.Preconditions.checkNotNull;
/**
 * Created by Retheesh CN
 * @since 06.10.2017
 */
public class BaseActivityViewModel extends BaseViewModel {
    private final InstrumentationProvider mInstrumentationProvider;
    protected final IntigralApi mIntigralApi;
    protected final EventBus mEventBus;

    protected BaseActivityViewModel(@NonNull InstrumentationProvider provider) {
        mInstrumentationProvider = provider;
        mIntigralApi = getmIntigralApi();
        mEventBus = getEventBus();
    }

    protected @NonNull
    IntigralApi getmIntigralApi() {
        return mInstrumentationProvider.getIntigralApi();
    }

    protected @Nullable Context getActivityContext() {
        return mInstrumentationProvider.getActivityContext();
    }

    protected  @NonNull EventBus getEventBus() {
        return mInstrumentationProvider.getEventBus();
    }

    protected @NonNull Resources getResources() {
        return mInstrumentationProvider.getResources();
    }

    protected @NonNull String getString(@StringRes int id) {
        return mInstrumentationProvider.getResString(id);
    }

    protected void startActivity(@NonNull Intent intent) {
        checkNotNull(intent);

        Context c = mInstrumentationProvider.getActivityContext();

        c.startActivity(intent);
    }


    protected void startService(@NonNull Intent intent) {
        mInstrumentationProvider.startService(checkNotNull(intent));
    }

    protected void requestPermissions(@NonNull String[] permissions, int requestCode) {
        mInstrumentationProvider.requestPermissions(checkNotNull(permissions), requestCode);
    }

    protected void startActivityForResult(@NonNull Intent intent, int requestCode) {
        mInstrumentationProvider.startActivityForResult(checkNotNull(intent), requestCode);
    }
    protected void setResult( int resultCode,@NonNull Intent intent) {
        mInstrumentationProvider.setResult(resultCode,intent);
    }

    @NonNull
    protected FragmentManager getFragmentManager(){
        return mInstrumentationProvider.getFragmentManager();
    }

    protected void replaceFragment(@IdRes int container, @NonNull Fragment fragment) {
        mInstrumentationProvider.replaceFragment(container, checkNotNull(fragment));
    }

    protected void startActivity(@NonNull Class<? extends AppCompatActivity> activity) {
        checkNotNull(activity);

        Context c = mInstrumentationProvider.getActivityContext();
        Intent intent = new Intent(c, activity);
        c.startActivity(intent);
    }

    protected void finishActivity(){
        mInstrumentationProvider.finishActivity();


    }
    protected void setText(ObservableString string, String text){
        if(text == null){
            string.set("");
        }else{
            string.set("");
            string.set(text);
        }

    }

}
