package com.intigral.ui.util.binding;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Retheesh CN
 * @since 06.10.2017
 */
public class BindingUtils {
    private BindingUtils() {
    }

    public static void bind(@NonNull EditText edit, @NonNull ObservableField<String> str,
                            @NonNull ObservableBoolean bool) {
        edit.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(@NonNull String newText) {
                if (!newText.equals(str.get())) {
                    str.set(newText);
                    bool.set(TextUtils.isEmpty(newText.trim()));
                }
            }
        });
    }

    public static void bind(@NonNull EditText edit, @NonNull ObservableField<String> str) {
        checkNotNull(edit);
        checkNotNull(str);

        edit.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(@NonNull String newText) {
                if (!newText.equals(str.get())) {
                    str.set(newText);
                }
            }
        });
    }

    public static void bind(@NonNull CheckBox checkBox, @NonNull ObservableBoolean checked, @NonNull Runnable run) {
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            checked.set(isChecked);
            run.run();
        });
    }

    public static void bind(@NonNull EditText edit, @NonNull ObservableField<String> str, @NonNull Runnable run) {
        checkNotNull(edit);
        checkNotNull(str);
        checkNotNull(run);

        edit.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(@NonNull String newText) {
                if (!newText.equals(str.get())) {
                    str.set(newText);
                    run.run();
                }
            }
        });
    }

    public static void bind(@NonNull EditText edit, @NonNull TextView.OnEditorActionListener listener) {
        checkNotNull(edit);
        checkNotNull(listener);

        edit.setOnEditorActionListener(listener);
    }

    public static void bind(@NonNull Button btn, @NonNull Runnable action) {
        checkNotNull(btn);
        checkNotNull(action);

        btn.setOnClickListener(v -> action.run());
    }

    public static void bind(@NonNull ImageView btn, @NonNull Runnable action) {
        checkNotNull(btn);
        checkNotNull(action);

        btn.setOnClickListener(v -> action.run());
    }

    public static void bind(@NonNull TextView txtv, @NonNull Runnable action) {
        checkNotNull(txtv);
        checkNotNull(action);

        txtv.setOnClickListener(v -> action.run());
    }

    public static void bind(@NonNull ViewGroup vg, @NonNull Runnable action) {
        checkNotNull(vg);
        checkNotNull(action);

        vg.setOnClickListener(v -> action.run());
    }

    public static void bind(@NonNull View view, @NonNull Runnable action) {
        checkNotNull(view);
        checkNotNull(action);

        view.setOnClickListener(v -> action.run());
    }

    public static void bind(@NonNull Toolbar toolbar, @NonNull Runnable action) {
        checkNotNull(toolbar);
        checkNotNull(action);

        toolbar.setNavigationOnClickListener(v -> action.run());
    }

    public static void bind(@NonNull CheckBox chb, @NonNull ObservableBoolean state) {
        checkNotNull(chb);
        checkNotNull(state);

        chb.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (state.get() != isChecked) {
                state.set(isChecked);
            }
        });
    }

    public static void bind(@NonNull Switch switchView, @NonNull ObservableBoolean state) {
        checkNotNull(switchView);
        checkNotNull(state);

        switchView.setOnCheckedChangeListener((compoundButton, b) -> {
            if (state.get() != b) {
                state.set(b);
            }
        });
    }

    public static void bind(@NonNull SeekBar seekBar, @NonNull ObservableInt value) {
        checkNotNull(seekBar);
        checkNotNull(value);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                value.set(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public static void bind(@NonNull RadioButton rbtn, @NonNull ObservableBoolean state) {
        checkNotNull(rbtn);
        checkNotNull(state);

        rbtn.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (state.get() != isChecked) {
                state.set(isChecked);
            }
        });
    }

    public static void bind(@NonNull LinearLayout layout, @NonNull Runnable action) {
        checkNotNull(layout);
        checkNotNull(action);

        layout.setOnClickListener(v -> action.run());
    }

    public static void bind(@NonNull Button button, int paintFlag) {
        checkNotNull(button);

        button.setPaintFlags(button.getPaintFlags() | paintFlag);
    }


}
