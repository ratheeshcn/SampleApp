package com.intigral.ui.util.binding;

import android.databinding.BindingAdapter;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.common.base.Strings;
import com.intigral.BuildConfig;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Retheesh CN
 * @since 06.10.2017
 */
public final class BindingAdapters {

    /**
     * Sets refreshing.
     *
     * @param layout     the layout
     * @param refreshing the refreshing
     */
    @BindingAdapter("refreshing")
    public static void setRefreshing(@NonNull SwipeRefreshLayout layout, boolean refreshing) {
        if (layout.isRefreshing()) {
            layout.post(() -> layout.setRefreshing(refreshing));
        }

    }

    /**
     * Sets bolded or light.
     *
     * @param textView the text view
     * @param isBold   the is bold
     */
    @BindingAdapter("bold")
    public static void setBoldedOrLight(@NonNull TextView textView, boolean isBold) {
        if (isBold) {
            textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
        } else {
            textView.setTypeface(Typeface.create("sans-serif-light", Typeface.NORMAL));
        }
    }



    /**
     * Load photo.
     *
     * @param imageView the image view
     * @param filepath  the filepath
     */

    @BindingAdapter("photo")
    public static void loadPhoto(@NonNull ImageView imageView, @Nullable String filepath) {
        Glide.with(imageView.getContext()).load(filepath).into(imageView);
    }


    /**
     * Scroll to.
     *
     * @param scrollView the scroll view
     * @param isEnable   the is enable
     */
    @BindingAdapter("scrollToTop")
    public static void scrollToTop(@NonNull NestedScrollView scrollView, @Nullable boolean isEnable) {
        if (isEnable) {

            scrollView.postDelayed(new Runnable() {
                public void run() {
                    scrollView.smoothScrollTo(0, scrollView.getBottom());

                }
            }, 500L);

        }
    }


    /**
     * Load photo url.
     *
     * @param imageView the image view
     * @param url       the url
     */
    @BindingAdapter("photoUrl")
    public static void loadPhotoUrl(@NonNull ImageView imageView, @Nullable String url) {
        if (!Strings.isNullOrEmpty(url)) {
            if (!url.startsWith(BuildConfig.API_URL)) {
                url = BuildConfig.API_URL + url;
            }
            Glide.with(imageView.getContext())
                    .load(url)
                    .asBitmap()
                    .centerCrop()
                    .into(checkNotNull(imageView));
        }
    }











    /**
     * Sets text clickable.
     *
     * @param view     the view
     * @param isEnable the is enable
     */
    @BindingAdapter("clickable")
    public static void setTextClickable(@NonNull TextView view, boolean isEnable) {
        view.setClickable(isEnable);
    }

    /**
     * Sets typeface.
     *
     * @param textView the text view
     * @param typeface the typeface
     */
    @BindingAdapter("typeface")
    public static void setTypeface(@NonNull TextView textView, @NonNull Typeface typeface) {
        textView.setTypeface(checkNotNull(typeface));
    }



    /**
     * Sets image background qty.
     *
     * @param imageView      the text view
     * @param resId the products count
     */
    @BindingAdapter({"imageSrc"})
    public static void setBackgroundImage(@NonNull ImageView imageView, int resId) {
        imageView.setImageResource(resId);
    }








}

