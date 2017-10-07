package com.intigral.ui.util.binding;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;
/**
 * Created by Retheesh CN
 * @since 06.10.2017
 */
public class ObservableCompareString extends ObservableString {
    private String mInitialText;
    private OnPropertyChangedCallback mCallback;
    private boolean mIsChanged;

    public ObservableCompareString() {
        super();
    }

    public ObservableCompareString(@Nullable String text) {
        super(text);
    }

    public void setCallback(@NonNull OnPropertyChangedCallback callback) {
        mInitialText = get();
        mCallback = checkNotNull(callback);
        addOnPropertyChangedCallback(mCallback);
    }

    public boolean hasChanged() {
        boolean hasChanged = get().equals(mInitialText) == mIsChanged;
        mIsChanged = !get().equals(mInitialText);
        return hasChanged;
    }

    public boolean isStringDifferent() {
        return mIsChanged;
    }

    public void onDestroy() {
        mIsChanged = false;
        if (mCallback != null) {
            removeOnPropertyChangedCallback(mCallback);
        }
    }
}
