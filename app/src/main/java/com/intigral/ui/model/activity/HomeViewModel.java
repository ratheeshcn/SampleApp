package com.intigral.ui.model.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.common.eventbus.EventBus;
import com.intigral.BuildConfig;
import com.intigral.IntigralApplication;
import com.intigral.R;
import com.intigral.databinding.RowMovieBinding;
import com.intigral.model.MovieImage;
import com.intigral.net.IntigralApi;
import com.intigral.net.response.GetConfigurationResponse;
import com.intigral.net.response.GetMoviesResponse;
import com.intigral.settings.AppSettings;
import com.intigral.ui.model.row.MovieRowViewModel;
import com.intigral.ui.util.instrumentation.InstrumentationProvider;
import com.intigral.ui.view.activity.BaseActivity;
import com.intigral.ui.view.activity.MovieDetailsActivity;
import com.intigral.util.MovieConstants;
import com.intigral.util.ObservableTransformers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Retheesh CN
 * @since 06.10.2017
 */
public class HomeViewModel extends BaseActivityViewModel implements Serializable ,MovieConstants{
    private static final int NO_ID = 0;
    public final ObservableField<RecyclerView.Adapter> mAdditionalAdapter = new ObservableField<>();
    public MoviesAdapter mMoviesAdapter;
    private final List<MovieRowViewModel> mProducts = new ArrayList<>();
    int PAGE = 1;
    AppSettings mAppSettings = AppSettings.getInstance();
    public final ObservableBoolean mIsLoading = new ObservableBoolean();


    public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

   @NonNull
        private List<MovieRowViewModel> getCurrentList() {
              return mProducts;

        }



        /**
         * Refill.
         *
         * @param models the models
         * @param clear  the clear

         */
        public void refill(@NonNull List<MovieRowViewModel> models, boolean clear
                         ) {
            if (clear) {
                getCurrentList().clear();
            }

            getCurrentList().addAll(models);
            notifyDataSetChanged();
        }

        /**
         * Re draw list.
         */
        public void reDrawList() {

            List<MovieRowViewModel> chatsList = getCurrentList();

            getCurrentList().clear();

            mAdditionalDataCurrentPage = FIRST_PAGE;
            loadMovies(SORT_BY_POPULARITY_DESC,mAdditionalDataCurrentPage);
        }



        /**
         * The type View holder.
         */
        public class ViewHolder extends RecyclerView.ViewHolder {
            private RowMovieBinding mBinding;

            /**
             * Instantiates a new View holder.
             *
             * @param itemView the item view
             * @param binding  the binding
             */
            public ViewHolder(@NonNull View itemView, @NonNull RowMovieBinding binding) {
                super(itemView);
                mBinding = binding;
            }

            /**
             * Bind.
             *
             * @param model the model
             */
            public void bind(@NonNull MovieRowViewModel model) {
                mBinding.setModel(checkNotNull(model));
                mBinding.executePendingBindings();
            }
        }

        @Override
        @NonNull
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            RowMovieBinding binding = DataBindingUtil.inflate(inflater,
                    R.layout.row_movie, parent, false);
            return new ViewHolder(binding.getRoot(), binding);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.bind(getCurrentList().get(position));
            setOnClickListener(holder.itemView, position);

            if (position == getCurrentList().size() / 2) {
                loadMovies(SORT_BY_POPULARITY_DESC,PAGE++);
            }
        }

        @Override
        public int getItemCount() {
             return getCurrentList().size();
        }

        /**
         * Is empty boolean.
         *
         * @return the boolean
         */
        public boolean isEmpty() {

            return getCurrentList().isEmpty();
        }

        private void setOnClickListener(@NonNull View view, int position) {
            view.setOnClickListener(v -> startActivity(MovieDetailsActivity.createIntent(getActivityContext(),
                    getCurrentList().get(position).getIntigralMovie())));
        }
    }

    public static final int FIRST_PAGE = 1;
    public final ObservableBoolean mIsAdditionalDataLoading = new ObservableBoolean();
    public final ObservableBoolean mIsEmptyAdditionalData = new ObservableBoolean();
    /*package*/ int mAdditionalDataCurrentPage = FIRST_PAGE;

    /**
     * The M event bus.
     */
/*package*/ EventBus mEventBus;
    /**
     * The M IntigralApi api.
     */
/*package*/ IntigralApi mIntigralApi;
    /**
     * The M observable transformers.
     */
/*package*/ ObservableTransformers mObservableTransformers;


    public HomeViewModel(@NonNull InstrumentationProvider provider) {
        super(provider);
        mIntigralApi = getmIntigralApi();
        mEventBus = IntigralApplication.getEventBus();
        initializeModels();
        //check config added or not
        if(mAppSettings.isConfigurationAdded()){
            loadProductsIfNecessary();
        }else {
            fetchConfigurations();
        }

    }


    /**
     * Initialize models.
     */
    public void initializeModels() {
        createAdapters();
        mObservableTransformers = ObservableTransformers.getInstance();

    }


    public void fetchConfigurations(){
        subscribe(mIntigralApi.getConfigurations(BuildConfig.API_KEY)
                .compose(ObservableTransformers.getInstance().networkOperation())
                .doOnSubscribe(() -> mIsLoading.set(true))
                .doOnTerminate(() -> mIsLoading.set(false))
                .subscribe(this::handleConfigurationResponse, this::handleConfigurationError));
    }

    private void handleConfigurationResponse(@NonNull GetConfigurationResponse response) {
       checkNotNull(response);
        mAppSettings.setConfigurationAdded(true);
        MovieImage image = response.images();
        mAppSettings.saveImageBaseUrl(image.base_url());
        mAppSettings.savePosterSize(image.poster_sizes().get(2));
        mAppSettings.saveThumbnailSize(image.poster_sizes().get(1));
        loadProductsIfNecessary();

    }

    void handleConfigurationError(@Nullable Throwable throwable) {
        mIsLoading.set(false);
        IntigralApplication.getEventBus().post(
                new BaseActivity.ShowSnackbarEvent(getString(R.string.errorBackendSide)));

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    /**
     * Load products if necessary.
     */
    protected void loadProductsIfNecessary() {

        if (mMoviesAdapter.isEmpty()) {
            mAdditionalDataCurrentPage = FIRST_PAGE;
            loadMovies(SORT_BY_POPULARITY_DESC,mAdditionalDataCurrentPage);
        } else {
            mMoviesAdapter.reDrawList();

        }
    }


    /**
     * Load products.
     */
    protected void loadMovies(String sortBy,int page) {

        subscribe(mIntigralApi.getMoviesList(com.intigral.BuildConfig.API_KEY,
                com.intigral.BuildConfig.LANGUAGE,
                sortBy,false,false,page)
                .compose(mObservableTransformers.networkOperation())
                .map(GetMoviesResponse::IntigralMovies)
                .flatMap(Observable::from)
                .map(MovieRowViewModel::new)
                .toList()
                .doOnSubscribe(this::showLoadingAdditionalProgressIfNecessary)
                .doOnTerminate(() -> mIsAdditionalDataLoading.set(false))
                .subscribe(this::handleMoviesResponse,
                        this::getMoviesError));
    }



    /**
     * Show loading additional progress if necessary.
     */
    protected void showLoadingAdditionalProgressIfNecessary() {
        if (mAdditionalDataCurrentPage == FIRST_PAGE) {
            mIsAdditionalDataLoading.set(true);
        }
    }
    /**
     * Gets movies error.
     *
     * @param throwable the throwable
     */
    protected void getMoviesError(@Nullable Throwable throwable) {
        mIsEmptyAdditionalData.set(mMoviesAdapter.isEmpty());
    }


    /**
     * Handle movie response.
     *
     * @param movies the products
     */
    protected void handleMoviesResponse(@NonNull List<MovieRowViewModel> movies) {
        mMoviesAdapter.refill(movies, false);
        mAdditionalAdapter.set(mMoviesAdapter);
        mIsEmptyAdditionalData.set(mMoviesAdapter.isEmpty());
        mAdditionalDataCurrentPage++;
    }

    /**
     * Handle searched products response.
     *
     * @param products the products
     */
    protected void handleSearchedProductsResponse(@NonNull List<MovieRowViewModel> products) {
        mMoviesAdapter.refill(products, true);
        mIsEmptyAdditionalData.set(mMoviesAdapter.isEmpty());
    }

    private void createAdapters() {
        mMoviesAdapter = new MoviesAdapter();

    }



}
