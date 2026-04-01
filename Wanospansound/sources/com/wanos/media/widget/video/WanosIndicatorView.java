package com.wanos.media.widget.video;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public class WanosIndicatorView extends View {
    private static final int MAX_SHOW_CODE = 5;
    private static final String TAG = "WanosIndicatorView";
    private float mCircleRadius;
    private int mCode;
    private int mCurrentIndex;
    private final int mDefaultColor;
    private final float mIndicatorCurrentWidth;
    private int mIndicatorSize;
    private final float mIndicatorSpace;
    private float mOffset;
    private final Paint mPaint;
    private final RectF mRectf;
    private final int mSelectColor;

    public WanosIndicatorView(Context context) {
        this(context, null);
    }

    public WanosIndicatorView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WanosIndicatorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Paint paint = new Paint();
        this.mPaint = paint;
        this.mIndicatorCurrentWidth = getResources().getDimension(R.dimen.zero_indicator_width);
        this.mIndicatorSpace = getResources().getDimension(R.dimen.zero_indicator_space);
        this.mRectf = new RectF();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.mDefaultColor = ContextCompat.getColor(context, R.color.zero_indicator_def);
        this.mSelectColor = ContextCompat.getColor(context, R.color.zero_indicator_sel);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int i = this.mCode;
        if (i <= 1) {
            return;
        }
        if (i <= 5) {
            onDrawScrollIndicator(canvas, i, this.mCurrentIndex);
            return;
        }
        int i2 = (i - 1) - 2;
        int i3 = this.mCurrentIndex;
        if (i3 < 2) {
            onDrawScrollIndicator(canvas, 5, i3);
        } else if (i3 >= i2) {
            onDrawScrollIndicator(canvas, 5, 5 - (i - i3));
        } else {
            onDrawIndicator(canvas);
        }
    }

    private void onDrawIndicator(Canvas canvas) {
        float f = this.mCircleRadius - ((this.mIndicatorSize + this.mIndicatorSpace) * this.mOffset);
        for (int i = 0; i < this.mCode; i++) {
            float f2 = this.mCircleRadius + f;
            this.mPaint.setColor(this.mDefaultColor);
            float f3 = this.mCircleRadius;
            canvas.drawCircle(f2, f3, f3, this.mPaint);
            f = f + this.mIndicatorSize + this.mIndicatorSpace;
        }
        float f4 = this.mCircleRadius;
        float f5 = ((this.mIndicatorSize + this.mIndicatorSpace) * 2.0f) + f4 + f4;
        this.mPaint.setColor(this.mSelectColor);
        RectF rectF = this.mRectf;
        float f6 = this.mIndicatorCurrentWidth;
        rectF.set(f5 - (f6 / 2.0f), 0.0f, f5 + (f6 / 2.0f), this.mIndicatorSize);
        RectF rectF2 = this.mRectf;
        float f7 = this.mCircleRadius;
        canvas.drawRoundRect(rectF2, f7, f7, this.mPaint);
    }

    private void onDrawScrollIndicator(Canvas canvas, int i, int i2) {
        float f = this.mCircleRadius;
        float f2 = 0.0f;
        for (int i3 = 0; i3 < i; i3++) {
            float f3 = this.mCircleRadius + f;
            this.mPaint.setColor(this.mDefaultColor);
            float f4 = this.mCircleRadius;
            canvas.drawCircle(f3, f4, f4, this.mPaint);
            if (i3 == i2) {
                f2 = (f - this.mCircleRadius) + ((this.mIndicatorSize + this.mIndicatorSpace) * this.mOffset);
            }
            f = f + this.mIndicatorSize + this.mIndicatorSpace;
        }
        this.mPaint.setColor(this.mSelectColor);
        this.mRectf.set(f2, 0.0f, this.mIndicatorCurrentWidth + f2, this.mIndicatorSize);
        RectF rectF = this.mRectf;
        float f5 = this.mCircleRadius;
        canvas.drawRoundRect(rectF, f5, f5, this.mPaint);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        super.onMeasure(i, i2);
        int size = View.MeasureSpec.getSize(i2);
        this.mIndicatorSize = size;
        this.mCircleRadius = size / 2.0f;
        if (this.mCode > 5) {
            i3 = (int) ((size * 5) + (this.mIndicatorSpace * 4.0f) + size);
        } else {
            i3 = (int) ((r4 * size) + ((r4 - 1) * this.mIndicatorSpace) + size);
        }
        setMeasuredDimension(i3, size);
    }

    public void setCode(int i) {
        this.mCode = i;
        requestLayout();
    }

    public void onPageScrolled(int i, float f, int i2) {
        this.mCurrentIndex = i;
        this.mOffset = f;
        invalidate();
    }
}
