package com.wanos.careditproject.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.text.TextPaint;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.internal.view.SupportMenu;
import com.wanos.careditproject.R;

/* JADX INFO: loaded from: classes3.dex */
public class GradientTextView extends AppCompatTextView {
    private int[] colors;
    private int gradientDirection;

    public GradientTextView(Context context) {
        super(context);
        this.gradientDirection = 0;
        init(context, null);
    }

    public GradientTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.gradientDirection = 0;
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        this.colors = new int[]{SupportMenu.CATEGORY_MASK, -16776961};
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.GradientTextView);
            int resourceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.GradientTextView_gradientColors, 0);
            if (resourceId != 0) {
                TypedArray typedArrayObtainTypedArray = getResources().obtainTypedArray(resourceId);
                this.colors = new int[typedArrayObtainTypedArray.length()];
                for (int i = 0; i < typedArrayObtainTypedArray.length(); i++) {
                    this.colors[i] = typedArrayObtainTypedArray.getColor(i, 0);
                }
                typedArrayObtainTypedArray.recycle();
            }
            this.gradientDirection = typedArrayObtainStyledAttributes.getInt(R.styleable.GradientTextView_gradientDirection, 0);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        float width;
        LinearGradient linearGradient;
        TextPaint paint = getPaint();
        float fMeasureText = paint.measureText(getText().toString());
        int gravity = getGravity() & 7;
        if (gravity == 1) {
            width = (getWidth() - fMeasureText) / 2.0f;
        } else if (gravity == 5) {
            width = (getWidth() - fMeasureText) - getPaddingRight();
        } else {
            width = getPaddingLeft();
        }
        float f = width;
        if (this.gradientDirection == 0) {
            linearGradient = new LinearGradient(f, 0.0f, f + fMeasureText, 0.0f, this.colors, (float[]) null, Shader.TileMode.CLAMP);
        } else {
            linearGradient = new LinearGradient(0.0f, 0.0f, 0.0f, getTextSize(), this.colors, (float[]) null, Shader.TileMode.CLAMP);
        }
        paint.setShader(linearGradient);
        super.onDraw(canvas);
        paint.setShader(null);
    }
}
