package com.intigral.util;

import android.support.annotation.NonNull;

import com.intigral.net.IntigralApi;
import com.intigral.net.IntigralApiFactory;
import com.intigral.settings.AppSettings;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Created by Retheesh CN
 * @since 06.10.2017
 */


public class ObservableTransformers {
    private static final int DEFAULT_RETRY_COUNT = 3;
    private static final int DEFAULT_RETRY_COUNT_SEARCH = 1;
    private static final String REFRESH_GRANT_TYPE = "refresh_token";
    private static ObservableTransformers sInstance;
    private AppSettings mAppSettings;
    private IntigralApi mIntigralApi;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    @NonNull
    public static ObservableTransformers getInstance() {
        if (sInstance == null) {
            sInstance = new ObservableTransformers();
        }
        return sInstance;
    }

    public ObservableTransformers(@NonNull AppSettings appSettings, @NonNull IntigralApi intigralApi) {
        mAppSettings = checkNotNull(appSettings);
        mIntigralApi = checkNotNull(intigralApi);
    }

    private ObservableTransformers() {
        mIntigralApi = IntigralApiFactory.getInstance().getApiClient();
    }


    /**
     * Network operation without token observable . transformer.
     *
     * @param <T> the type parameter
     * @return the observable . transformer
     */
    @NonNull
    public <T> Observable.Transformer<T, T> networkOperation() {
        return observable -> observable.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).retry(DEFAULT_RETRY_COUNT);
    }







}