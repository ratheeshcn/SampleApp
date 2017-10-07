package com.intigral.net.response;

import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.intigral.model.MovieImage;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Retheesh CN
 * @since 06.10.2017
 */


@AutoValue
public abstract class GetConfigurationResponse {
    @SerializedName("images")
    public abstract MovieImage images();


    public static @NonNull
    TypeAdapter<GetConfigurationResponse> typeAdapter(@NonNull Gson gson) {
        return new AutoValue_GetConfigurationResponse.GsonTypeAdapter(checkNotNull(gson));
    }

    public static @NonNull Builder builder() {
        return new AutoValue_GetConfigurationResponse.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder images(MovieImage value);
        public abstract GetConfigurationResponse build();
    }
}
