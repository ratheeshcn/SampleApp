package com.intigral.net;

import android.support.annotation.NonNull;

import com.intigral.net.response.GetConfigurationResponse;
import com.intigral.net.response.GetMoviesResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Retheesh CN
 * @since 06.10.2017
 */
public interface IntigralApi {


    @GET("discover/movie?")
    @NonNull Observable<GetMoviesResponse> getMoviesList(@Query("api_key")String apiKey,
      @Query("language") @NonNull String language, @Query("sort_by")
    @NonNull String sort_By, @Query("include_adult") boolean include_Adult, @Query("include_video") boolean include_video,
                                                         @Query("page") int page);


    @GET("configuration?")
    @NonNull Observable<GetConfigurationResponse> getConfigurations(@Query("api_key")String apiKey);


}