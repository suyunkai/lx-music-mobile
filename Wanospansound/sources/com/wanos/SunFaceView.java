package com.wanos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import androidx.core.internal.view.SupportMenu;

/* JADX INFO: loaded from: classes3.dex */
public class SunFaceView extends View {
    private static final int DEFAULT_EYES_RADIUS = 2;
    private static final int DEFAULT_SUN_RADIUS = 12;
    private static final String Tag = "SunFaceView";
    private Rect debugRect;
    private boolean isDrawFace;
    private Paint mCirclePaint;
    private int mEyesRadius;
    private int mHeight;
    private int mSunColor;
    private int mSunRadius;
    private int mWidth;
    private RectF mouthRect;
    private int mouthStro;

    public SunFaceView(Context context) {
        this(context, null);
    }

    public SunFaceView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SunFaceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mEyesRadius = 2;
        this.isDrawFace = true;
        this.mouthStro = 3;
        init();
    }

    private void init() {
        Log.i(Tag, "init");
        this.mSunRadius = changeDp(12);
        this.mEyesRadius = changeDp(2);
        Paint paint = new Paint(1);
        this.mCirclePaint = paint;
        paint.setColor(SupportMenu.CATEGORY_MASK);
        this.mCirclePaint.setStyle(Paint.Style.FILL);
        this.debugRect = new Rect();
        this.mouthRect = new RectF();
    }

    public void setSunRadius(int i) {
        this.mSunRadius = changeDp(i);
        invalidate();
    }

    public void setEyesSize(int i) {
        this.mEyesRadius = changeDp(i);
        invalidate();
    }

    public void setMouthStro(int i) {
        this.mouthStro = i;
        invalidate();
    }

    public void setPerView(int i, float f) {
        int iChangeDp = changeDp(i);
        this.isDrawFace = ((double) f) >= 0.8d;
        float fMin = Math.min(f, 1.0f);
        this.mSunRadius = (int) (iChangeDp * fMin);
        this.mCirclePaint.setAlpha(((int) fMin) * 255);
        invalidate();
    }

    public void setSunColor(int i) {
        this.mSunColor = i;
        invalidate();
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        Log.i(Tag, "w---->" + i + "  -------  h----->" + i2);
        this.mWidth = i;
        this.mHeight = i2;
        this.debugRect.left = (i / 2) - this.mSunRadius;
        this.debugRect.right = (this.mWidth / 2) + this.mSunRadius;
        this.debugRect.top = (this.mHeight / 2) - this.mSunRadius;
        this.debugRect.bottom = (this.mHeight / 2) + this.mSunRadius;
        this.mouthRect.left = (this.mWidth / 2) - (this.mSunRadius / 2);
        this.mouthRect.right = (this.mWidth / 2) + (this.mSunRadius / 2);
        this.mouthRect.top = (this.mHeight / 2) - (this.mSunRadius / 2);
        this.mouthRect.bottom = (this.mHeight / 2) + (this.mSunRadius / 2);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        Log.i(Tag, "onMeasure");
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode != 1073741824) {
            size = (this.mSunRadius * 2) + getPaddingRight() + getPaddingLeft();
        }
        if (mode2 != 1073741824) {
            size2 = (this.mSunRadius * 2) + getPaddingTop() + getPaddingBottom();
        }
        setMeasuredDimension(size, size2);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircle(canvas);
        this.mCirclePaint.setStyle(Paint.Style.STROKE);
    }

    private void drawCircle(Canvas canvas) {
        this.mCirclePaint.setColor(this.mSunColor);
        this.mCirclePaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(this.mWidth / 2, this.mHeight / 2, this.mSunRadius, this.mCirclePaint);
        this.mCirclePaint.setColor(-1);
        if (this.isDrawFace) {
            canvas.save();
            int i = this.mWidth / 2;
            int i2 = this.mSunRadius;
            canvas.drawCircle((i - (i2 / 2)) + r2, ((this.mHeight / 2) - (i2 / 2)) + r2, this.mEyesRadius, this.mCirclePaint);
            int i3 = this.mWidth / 2;
            int i4 = this.mSunRadius;
            canvas.drawCircle((i3 + (i4 / 2)) - r2, ((this.mHeight / 2) - (i4 / 2)) + r2, this.mEyesRadius, this.mCirclePaint);
            this.mCirclePaint.setStyle(Paint.Style.STROKE);
            this.mCirclePaint.setStrokeWidth(this.mouthStro);
            canvas.drawArc(this.mouthRect, 20.0f, 140.0f, false, this.mCirclePaint);
            canvas.restore();
        }
    }

    public int changeDp(int i) {
        return (int) TypedValue.applyDimension(1, i, getResources().getDisplayMetrics());
    }
}
