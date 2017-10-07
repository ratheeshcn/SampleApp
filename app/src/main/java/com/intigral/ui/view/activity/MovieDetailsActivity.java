package com.intigral.ui.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.intigral.R;
import com.intigral.databinding.ActivityMovieDetailsBinding;
import com.intigral.model.IntigralMovie;
import com.intigral.ui.model.activity.MovieDetailsActivityViewModel;
import com.intigral.ui.util.instrumentation.ActivityInstrumentationProvider;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Retheesh CN
 * @since 07.10.2017
 */

public class MovieDetailsActivity extends BaseActivity {
    public static final String EXTRA_MOVIE = "extra_product";

    private ActivityMovieDetailsBinding mBinding;
    private MovieDetailsActivityViewModel mModel;
    private IntigralMovie mMovie;

    @NonNull
    public static Intent createIntent(@NonNull Context context, @NonNull IntigralMovie movie) {
        Intent intent = new Intent(checkNotNull(context), MovieDetailsActivity.class);
        intent.putExtra(EXTRA_MOVIE,movie );
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadFromIntent();

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);
        mBinding.setModel(mModel = new MovieDetailsActivityViewModel(ActivityInstrumentationProvider.from(this), mMovie));

    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    private void loadFromIntent() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        mMovie = extras.getParcelable(EXTRA_MOVIE);
    }


}
