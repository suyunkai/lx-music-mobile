package com.wanos.media.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.ViewCompat;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public class StrokeTextView extends AppCompatTextView {
    private int strokeColor;
    private Paint strokePaint;
    private int strokeWidth;

    public StrokeTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initAttr(context, attributeSet);
    }

    public StrokeTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initAttr(context, attributeSet);
    }

    private void initAttr(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.StrokeTextView);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            if (index == R.styleable.StrokeTextView_stroke_color) {
                this.strokeColor = typedArrayObtainStyledAttributes.getColor(index, ViewCompat.MEASURED_STATE_MASK);
            } else if (index == R.styleable.StrokeTextView_stroke_width) {
                this.strokeWidth = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, 0);
            }
        }
        typedArrayObtainStyledAttributes.recycle();
        this.strokePaint = new Paint();
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        this.strokePaint.setStyle(Paint.Style.STROKE);
        this.strokePaint.setStrokeWidth(this.strokeWidth);
        this.strokePaint.setColor(this.strokeColor);
        this.strokePaint.setAntiAlias(true);
        this.strokePaint.setTextSize(getTextSize());
        this.strokePaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(getText().toString(), getWidth() / 2.0f, getBaseline(), this.strokePaint);
        super.onDraw(canvas);
    }
}
