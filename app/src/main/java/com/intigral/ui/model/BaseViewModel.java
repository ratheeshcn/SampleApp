package com.intigral.ui.model;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.common.base.Strings;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

import static com.google.common.base.Preconditions.checkNotNull;
/**
 * Created by Retheesh CN
 * @since 06.10.2017
 */

public class BaseViewModel extends BaseObservable  {
    private static final float NON_TRANSPARENT = 1.0f;
    private static final float MEDIUM_TRANSPARENT = 0.2f;
    private static final float OVERSHOOTR_VALUE = 1.05f;
    public static final String FACEBOOK_CREATE_PAGE = "https://m.facebook.com/pages/creation";
    private static final float ANIMATION_DURATION = 3.05f;
    /**
     * The M subscriptions.
     */

    protected final CompositeSubscription mSubscriptions = new CompositeSubscription();
    private static final String GIF = ".gif";
    private static final String GIF_PNG = "_n.png";


    /**
     * Subscribe.
     *
     * @param s the s
     */
    protected void subscribe(@NonNull Subscription s) {
        mSubscriptions.add(checkNotNull(s));
    }

    /**
     * Change view visibility.
     *
     * @param view    the view
     * @param visible the visible
     */
    @BindingAdapter("visibleIf")
    public static void changeViewVisibility(@NonNull View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }


    /**
     * Change visibility.
     *
     * @param view    the view
     * @param visible the visible
     */
    @BindingAdapter("VisibleIfNotGone")
    public static void changeVisibility(@NonNull View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    }


    /**
     * Bitmap source.
     *
     * @param view the view
     * @param uri  the uri
     */

    @BindingAdapter("imageUrl")
    public static void bitmapSource(@NonNull ImageView view, @NonNull String uri) {
        Glide.with(view.getContext()).load(uri).into(view);
    }


    /**
     * Load customer avatar.
     *
     * @param view the view
     * @param uri  the uri
     */
    @BindingAdapter("customerAvatar")
    public static void loadCustomerAvatar(@NonNull ImageView view, @Nullable String uri) {
        Glide.with(view.getContext())
                .load(uri)
                .asBitmap()
                .transform(new CropCircleTransformation(view.getContext()))
                .listener(new RequestListener<String, Bitmap>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<Bitmap> target,
                                               boolean isFirstResource) {
                        view.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, String model,
                                                   Target<Bitmap> target, boolean isFromMemoryCache,
                                                   boolean isFirstResource) {
                        view.setVisibility(View.VISIBLE);
                        return false;
                    }
                })
                .into(view);
    }

    /**
     * Load image with placeholder.
     *
     * @param imageView   the image view
     * @param url         the url
     * @param placeholder the placeholder
     */
    @BindingAdapter({"bind:imageUrl", "bind:placeholder"})
    public static void loadImageWithPlaceholder(@NonNull ImageView imageView, @Nullable String url,
                                                @DrawableRes int placeholder) {
        if (!Strings.isNullOrEmpty(url)) {
            Glide.with(imageView.getContext())
                    .load(url)
                    .centerCrop()
                    .error(placeholder)
                    .into(imageView);
        } else {
            imageView.setImageResource(placeholder);
        }
    }


    /**
     * On destroy.
     */
    public void onDestroy() {
        mSubscriptions.clear();
    }
}











