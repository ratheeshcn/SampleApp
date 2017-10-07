package com.intigral.ui.model.activity;

import android.databinding.ObservableLong;
import android.support.annotation.NonNull;

import com.intigral.model.IntigralMovie;
import com.intigral.settings.AppSettings;
import com.intigral.ui.util.binding.ObservableString;
import com.intigral.ui.util.instrumentation.InstrumentationProvider;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Retheesh CN
 * @since 07.10.2017
 */

public class MovieDetailsActivityViewModel extends BaseActivityViewModel {

    public final ObservableLong mMovieId = new ObservableLong();
    public final ObservableString mVoteAverage = new ObservableString();
    public final ObservableString mTitle = new ObservableString();
    public final ObservableString mOverview = new ObservableString();
    public final ObservableString mImage = new ObservableString();
    AppSettings mAppSettings = AppSettings.getInstance();
    /*package*/IntigralMovie mMovie;
    /**
     * Instantiates a new movie activity view model.
     *
     * @param provider the provider
     * @param movie  the movie
     */
    public MovieDetailsActivityViewModel(@NonNull InstrumentationProvider provider,
                                         @NonNull IntigralMovie movie) {
        super(provider);
        seMovie(checkNotNull(movie));
    }


    /**
     * Sets movie.
     *
     * @param movie the movie
     */
    public void seMovie(@NonNull IntigralMovie movie) {
        mMovie = checkNotNull(movie);
        mMovieId.set(mMovie.id());
        mVoteAverage.set(mMovie.vote_average());
        mTitle.set(mMovie.title());
        mOverview.set(mMovie.overview());
        getMoviePoster(mMovie.poster_path());

    }

    public void getMoviePoster(String posterPath) {
        if (mAppSettings.getImageBaseUrl().isPresent() && mAppSettings.loadPosterSize().isPresent()) {
            mImage.set(mAppSettings.getImageBaseUrl().get() + mAppSettings.loadPosterSize().get()+posterPath);
        }
    }

    public  void navigateBack(){
        finishActivity();
    }
}



