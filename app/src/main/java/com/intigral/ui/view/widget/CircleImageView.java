package com.intigral.ui.view.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;



/**
 * Created by Bartosz Mądry
 * @since 21.09.16.
 */

public class CircleImageView extends AppCompatImageView {
    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(roundBitmap(bm));
    }

    private @NonNull Bitmap roundBitmap(@NonNull Bitmap bitmap) {
        Paint bitmapShaderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        bitmapShaderPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2, bitmapShaderPaint);

        return output;
    }
}
