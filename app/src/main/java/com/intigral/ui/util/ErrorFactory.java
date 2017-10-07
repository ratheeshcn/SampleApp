package com.intigral.ui.util;

import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import com.intigral.R;

import java.io.IOException;
import java.net.HttpURLConnection;

import retrofit2.adapter.rxjava.HttpException;
/**
 * Created by Retheesh CN
 * @since 06.10.2017
 */

public class ErrorFactory {
    @StringRes
    public static int getError(@Nullable Throwable throwable) {
        int result = R.string.failure;

        if (throwable instanceof IOException) {
            result = R.string.errorInternetConnection;
        } else if (throwable instanceof HttpException) {
            HttpException exception = (HttpException) throwable;
            switch (exception.code()) {
                case HttpURLConnection.HTTP_UNAUTHORIZED:
                    result = R.string.errorAuthentication;
                    break;
                case HttpURLConnection.HTTP_INTERNAL_ERROR:
                    result = R.string.errorBackendSide;
                    break;
            }
        }

        return result;
    }




}
