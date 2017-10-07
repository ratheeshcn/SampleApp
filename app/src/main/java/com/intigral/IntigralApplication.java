package com.intigral;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDexApplication;

import com.google.common.eventbus.EventBus;


/**
 * Created by Retheesh CN
 * @since 06.10.2017
 */
public class IntigralApplication extends MultiDexApplication {
    private static Context sContext;
    private static EventBus sEventBus;

    @Override
    public void onCreate() {
        super.onCreate();

        sContext = getApplicationContext();
        sEventBus = new EventBus("Intigral event bus");
    }


    public static
    @NonNull
    Context getAppContext() {
        return sContext;
    }

    public static @NonNull EventBus getEventBus() {
        return sEventBus;
    }

}


