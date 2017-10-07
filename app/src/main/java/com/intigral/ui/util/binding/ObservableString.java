package com.intigral.ui.util.binding;

import android.databinding.ObservableField;
import android.support.annotation.Nullable;
/**
 * Created by Retheesh CN
 * @since 06.10.2017
 */

public class ObservableString extends ObservableField<String> {
    public ObservableString() {
        super();
    }

    public ObservableString(@Nullable String text) {
        super(text);
    }
}
