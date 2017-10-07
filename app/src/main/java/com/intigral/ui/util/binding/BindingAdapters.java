package com.intigral.ui.util.binding;

import android.databinding.BindingAdapter;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
    @BindingAdapter("scrollTo")
    public static void scrollTo(@NonNull NestedScrollView scrollView, @Nullable boolean isEnable) {
        if (isEnable) {

            scrollView.postDelayed(new Runnable() {
                public void run() {
                    scrollView.smoothScrollTo(0, scrollView.getBottom());

                }
            }, 500L);

        }else{
            scrollView.postDelayed(new Runnable() {
                public void run() {
                    scrollView.smoothScrollTo(0, scrollView.getTop());

                }
            }, 500L);
        }
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
     * Sets background color.
     *
     * @param view  the view
     * @param color the color
     */
    @BindingAdapter("bg_color")
    public static void setBackgroundColor(@NonNull View view, int color) {
        Drawable background = view.getBackground();
        if (background instanceof ShapeDrawable) {
            ((ShapeDrawable) background).getPaint().setColor(color);
        } else if (background instanceof GradientDrawable) {
            ((GradientDrawable) background).setColor(color);
        } else if (background instanceof ColorDrawable) {
            ((ColorDrawable) background).setColor(color);
        }
    }



    /**
     * Sets src color.
     *
     * @param view  the view
     * @param color the color
     */
    @BindingAdapter("src_color")
    public static void setSrcColor(@NonNull ImageView view, int color) {
        Drawable drawable = view.getDrawable();
        DrawableCompat.setTint(drawable, color);
        view.setImageDrawable(drawable);
    }

    /**
     * Sets text color.
     *
     * @param view  the view
     * @param color the color
     */
    @BindingAdapter("text_color")
    public static void setTextColor(@NonNull TextView view, int color) {
        view.setTextColor(color);
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
     * Sets view pager adapter.
     *
     * @param pager   the pager
     * @param adapter the adapter
     */
    @BindingAdapter("viewpager_adapter")
    public static void setViewPagerAdapter(@NonNull ClickableViewPager pager,
                                           @NonNull PagerAdapter adapter) {
        if(adapter !=null) {
            pager.setAdapter(checkNotNull(adapter));
            adapter.notifyDataSetChanged();
        }
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


    /**
     * Sets layout background color.
     *
     * @param view  the view
     * @param color the color
     */
    @BindingAdapter("layout_bg_color")
    public static void setLayoutBackgroundColor(@NonNull View view, int color) {
       view.setBackgroundColor(color);
    }

    /**
     * Sets list divider.
     *
     * @param view        the view
     * @param orientation the orientation
     */
    @BindingAdapter("list_divider")
    public static void setListDivider(@NonNull RecyclerView view, int orientation) {
        if(orientation == 1){
            view.addItemDecoration(new DividerItemDecoration(view.getContext(),
                    LinearLayoutManager.HORIZONTAL));
        }
    }





}

