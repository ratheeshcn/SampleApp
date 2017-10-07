package com.intigral.ui.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.intigral.IntigralApplication;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Retheesh CN
 * @since 06.10.2017
 */
public class BaseActivity extends AppCompatActivity {
    public static class ShowSnackbarEvent {
        private  int mMessageResId;

        private  String mMessage="";
        private int mActionResId;
        private Runnable mAction;
        private Runnable mEndAction;


        public ShowSnackbarEvent(@StringRes int messageResId) {
            mMessageResId = messageResId;
        }

        public ShowSnackbarEvent(@StringRes int messageResId, @StringRes int actionResId,
                                 @NonNull Runnable action, @NonNull Runnable endAction) {
            mMessageResId = messageResId;
            mActionResId = actionResId;
            mAction = checkNotNull(action);
            mEndAction = checkNotNull(endAction);
        }

        public ShowSnackbarEvent( String messageResId) {
            mMessage = messageResId;
        }



        @StringRes
        public int getMessageResId() {
            return mMessageResId;
        }

        public String getmMessage(){
            return  mMessage;
        }

        @StringRes
        public int getActionResId() {
            return mActionResId;
        }

        @Nullable
        public Runnable getAction() {
            return mAction;
        }

        @Nullable
        public Runnable getEndAction() {
            return mEndAction;
        }
    }

    public static class HideKeyboardEvent {
    }

    public static class ShowKeyboardEvent {
    }

    public static class ClearTextEvent {
    }

    public static class ScrollToLastEvent {
    }





    protected EventBus mEventBus;
    protected final CompositeSubscription mSubscriptions = new CompositeSubscription();

    protected void subscribe(@NonNull Subscription s) {
        mSubscriptions.add(checkNotNull(s));
    }

    protected void showSnackbarMessage(@StringRes int message) {
        showSnackbarMessage(message, Snackbar.LENGTH_LONG);
    }

    protected void showSnackbarMessageStr( String message) {
        showSnackbarMessage(message, Snackbar.LENGTH_LONG);
    }

    protected void showSnackbarMessage(@StringRes int message, int duration) {
        View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar.make(rootView, message, duration).show();
    }


    protected void showSnackbarMessage( String message, int duration) {
        View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar.make(rootView, message, duration).show();
    }

    protected void showSnackbarMessage(@StringRes int message, int duration, @NonNull View root) {
        checkNotNull(root);
        Snackbar.make(root, message, duration).show();
    }

    protected void showSnackbarMessage(@StringRes int message, @StringRes int actionId,
                                       @NonNull Runnable action, @NonNull Runnable endAction) {
        View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar.make(rootView, message, Snackbar.LENGTH_LONG)
                .setAction(actionId, v -> action.run())
                .setCallback(new Snackbar.Callback() {
                    @Override
                    public void onDismissed(Snackbar snackbar, int event) {
                        if (event != Snackbar.Callback.DISMISS_EVENT_ACTION) {
                            endAction.run();
                        }
                    }
                }).show();
    }

    protected void hideKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    protected void showKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEventBus = IntigralApplication.getEventBus();

    }

    @Override
    public void onStart() {
        super.onStart();
        mEventBus.register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        mEventBus.unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSubscriptions.clear();
    }

    @Subscribe
    public void onHideKeyboardEvent(@NonNull HideKeyboardEvent event) {
        hideKeyboard();
    }

    @Subscribe
    public void onShowKeyboardEvent(@NonNull ShowKeyboardEvent event) {
        showKeyboard();
    }


}