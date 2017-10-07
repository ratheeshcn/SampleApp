package com.intigral.model;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import static com.google.common.base.Preconditions.checkNotNull;
/**
 * Created by Retheesh CN
 * @since 06.10.2017
 */


@AutoValue
public abstract class IntigralMovie implements Parcelable {
    @SerializedName("id")
    public abstract @Nullable Integer id();

    @SerializedName("vote_average")
    public abstract @Nullable String vote_average();

    @SerializedName("title")
    public abstract @Nullable String title();


    @SerializedName("poster_path")
    public abstract @Nullable String poster_path();

    @SerializedName("overview")
    public abstract @Nullable String overview();



    public static @NonNull TypeAdapter<IntigralMovie> typeAdapter(@NonNull Gson gson) {
        return new AutoValue_IntigralMovie.GsonTypeAdapter(checkNotNull(gson));
    }

    public static @NonNull Builder builder() {
        return new AutoValue_IntigralMovie.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(@Nullable Integer value);

        public abstract Builder vote_average(@Nullable String value);

        public abstract Builder poster_path(@Nullable String poster_path );

        public abstract Builder overview(@Nullable String overview );
        public abstract Builder title(@Nullable String title );

     public abstract IntigralMovie build();
    }



}
