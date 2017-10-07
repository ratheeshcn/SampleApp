package com.intigral.net.response;

import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.intigral.model.IntigralMovie;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
/**
 * Created by Retheesh CN
 * @since 06.10.2017
 */


@AutoValue
public abstract class GetMoviesResponse {
    @SerializedName("page")
    public abstract int page();

    @SerializedName("total_results")
    public abstract
    long total_results();

    @SerializedName("total_pages")
    public abstract long total_pages();

    @SerializedName("results")
    public abstract @NonNull
    List<IntigralMovie> IntigralMovies();

    public static @NonNull
    TypeAdapter<GetMoviesResponse> typeAdapter(@NonNull Gson gson) {
        return new AutoValue_GetMoviesResponse.GsonTypeAdapter(checkNotNull(gson));
    }

    public static @NonNull Builder builder() {
        return new AutoValue_GetMoviesResponse.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder page(int value);
        public abstract Builder total_results( long value);
        public abstract Builder total_pages( long value);
        public abstract Builder IntigralMovies(@NonNull List<IntigralMovie> value);
        public abstract GetMoviesResponse build();
    }
}
