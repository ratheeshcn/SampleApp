package com.intigral.net;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.intigral.BuildConfig;
import com.intigral.util.RxErrorHandlingCallAdapterFactory;
import com.ryanharter.auto.value.gson.AutoValueGsonTypeAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Retheesh CN
 * @since 06.10.2017
 */
public class IntigralApiFactory {

    private static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ssZZZZZ";
    private static IntigralApiFactory sFactory;
    private IntigralApi mIntigralApi;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static
    @NonNull
    IntigralApiFactory getInstance() {
        if (sFactory == null) {
            sFactory = new IntigralApiFactory();
        }
        return sFactory;
    }

    /**
     * Gets api client.
     *
     * @return the api client
     */
    public
    @NonNull
    IntigralApi getApiClient() {
        if (mIntigralApi == null) {
            createintigralApi();
        }
        return mIntigralApi;
    }



    private void createintigralApi() {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL + BuildConfig.API_VERSION)
                //.addConverterFactory(GsonConverterFactory.create(new Gson()))
                //.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(createGsonForConverter()))
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .client(createClient());

        Retrofit retrofit = retrofitBuilder.build();

        mIntigralApi = retrofit.create(IntigralApi.class);
    }

    private IntigralApiFactory() {
    }

    private
    @NonNull
    OkHttpClient createClient() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            clientBuilder.addInterceptor(logging);
        }
        clientBuilder.readTimeout(60, TimeUnit.SECONDS);
        clientBuilder.writeTimeout(60, TimeUnit.SECONDS);
        clientBuilder.connectTimeout(60, TimeUnit.SECONDS);
        clientBuilder.retryOnConnectionFailure(false);
        clientBuilder.addInterceptor(chain -> {
            Request original = chain.request();
            Request requestLogin = original.newBuilder()
                        .header("User-Agent", "AndroidApp")
                        .header("Accept-Language", getLanguageCode())
                        .build();
                return chain.proceed(requestLogin);

        });

        return clientBuilder.build();
    }




    private
    @NonNull
    Gson createGsonForConverter() {
        return new GsonBuilder()
                .setDateFormat(DATE_PATTERN)
                .registerTypeAdapterFactory(new AutoValueGsonTypeAdapterFactory())
                .create();
    }

    private String getLanguageCode(){
        return "en-US";
    }
}
