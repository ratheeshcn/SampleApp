package com.intigral.ui.model.row;

import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.util.Log;

import com.intigral.R;
import com.intigral.model.IntigralMovie;
import com.intigral.settings.AppSettings;
import com.intigral.ui.util.binding.ObservableString;

/**
 * Created by Retheesh CN
 * @since 06.10.2017
 */

public class MovieRowViewModel extends BaseRowViewModel {
    public final ObservableString mName = new ObservableString();
    public final ObservableString mImage = new ObservableString();
    public final ObservableString mDescription = new ObservableString();
    public final ObservableInt mPlaceholder = new ObservableInt(R.drawable.ic_default_pic);
    private final IntigralMovie mMovie;
    AppSettings mAppSettings = AppSettings.getInstance();

    public MovieRowViewModel(@NonNull IntigralMovie movie) {
        mMovie = movie;
        mName.set(mMovie.title());
        mDescription.set(mMovie.overview());
        if (mAppSettings.getImageBaseUrl().isPresent() && mAppSettings.loadThumbnailSize().isPresent()) {
            mImage.set(mAppSettings.getImageBaseUrl().get() + mAppSettings.loadThumbnailSize().get()+mMovie.poster_path());
            Log.d("poster",mImage.get());
        }

    }

    @NonNull
    public IntigralMovie getIntigralMovie(){
        return mMovie;
    }
}
