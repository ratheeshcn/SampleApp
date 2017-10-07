package com.intigral.model;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Retheesh CN
 * @since 06.10.2017
 */

@AutoValue
public abstract class MovieImage implements Parcelable {

    @SerializedName("poster_sizes")
    @Nullable
    public abstract  List<String> poster_sizes();

    @SerializedName("base_url")
    @Nullable
    public abstract @NonNull String base_url();


    public static @NonNull TypeAdapter<MovieImage> typeAdapter(@NonNull Gson gson) {
        return new AutoValue_MovieImage.GsonTypeAdapter(checkNotNull(gson));
    }

    public static @NonNull Builder builder() {
        return new AutoValue_MovieImage.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder poster_sizes(@Nullable List<String> poster_sizes);
        public abstract Builder base_url(@Nullable String base_url);

        public abstract MovieImage build();
    }
}
