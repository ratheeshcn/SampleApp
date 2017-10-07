package com.intigral.util;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.animation.AlphaAnimation;

import com.intigral.IntigralApplication;
/**
 * Created by Retheesh CN
 * @since 06.10.2017
 */

public final class Helper {

    public static void setBackground(View v, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            v.setBackground(drawable);
        } else {
            v.setBackgroundDrawable(drawable);
        }
    }

    public static boolean hasZero(int... args) {
        for (int num : args) {
            if (num == 0) {
                return true;
            }
        }
        return false;
    }

    public static void animate(View v, int duration) {
        AlphaAnimation alpha = new AlphaAnimation(0f, 1f);
        alpha.setDuration(duration);
        v.startAnimation(alpha);
    }

    public static String getVersion(){

        String versionName = "";
        try {
            PackageManager manager = IntigralApplication.getAppContext().getPackageManager();
            PackageInfo info = manager.getPackageInfo(
                    IntigralApplication.getAppContext().getPackageName(), 0);
            versionName = "v"+info.versionName;
        }catch (Exception e){
            versionName = "";
        }


        return versionName;
    }

    public static int getVersionCode(){

        int versionCode = 0;
        try {
            PackageManager manager = IntigralApplication.getAppContext().getPackageManager();
            PackageInfo info = manager.getPackageInfo(
                    IntigralApplication.getAppContext().getPackageName(), 0);
            versionCode  = info.versionCode;
        }catch (Exception e){
            versionCode = 0;
        }


        return versionCode;
    }
}