package com.intigral.ui.util.binding;

import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class SimpleTextWatcher implements TextWatcher {

    private final Observable<String> mTextObservable = Observable.create(new Observable.OnSubscribe<String>() {
        @Override
        public void call(@NonNull Subscriber<? super String> subscriber) {
            try {
                mTextListeners.add(subscriber::onNext);
            }catch (Exception e){
                subscriber.onError(e);
            }
        }
    });

    private List<TextChangeListener> mTextListeners = new ArrayList<>();

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // no-op
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // no-op
    }

    @Override
    public void afterTextChanged(Editable s) {
        onTextChanged(s.toString());

        for (TextChangeListener listener : mTextListeners) {
            listener.onTextChanged(s.toString());
        }
    }

    public void onTextChanged(@NonNull String newText) {
        // no-op
    }

    public @NonNull Observable<String> getTextObservable() {
        return mTextObservable;
    }

    private interface TextChangeListener {

        void onTextChanged(@NonNull String newText);
    }
}
