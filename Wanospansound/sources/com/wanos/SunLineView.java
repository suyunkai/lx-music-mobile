package com.wanos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import androidx.core.internal.view.SupportMenu;

/* JADX INFO: loaded from: classes3.dex */
public class SunLineView extends View {
    private static final String Tag = "SunLineView";
    private Rect debugRect;
    private DrawFilter mDrawFilter;
    private int mFixLineHeight;
    private int mHeight;
    private int mLineBottom;
    private int mLineColor;
    private int mLineHeight;
    private int mLineLeft;
    private int mLineLevel;
    private Paint mLinePaint;
    private int mLineTop;
    private int mLineWidth;
    private int mSunRadius;
    private int mWidth;
    private RectF mouthRect;

    public SunLineView(Context context) {
        this(context, null);
    }

    public SunLineView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SunLineView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        Log.i(Tag, "init");
        this.mLineWidth = changeDp(1);
        this.mLineHeight = changeDp(3);
        this.mFixLineHeight = changeDp(6);
        this.mSunRadius = changeDp(12);
        this.mLineColor = SupportMenu.CATEGORY_MASK;
        this.mLineLevel = 30;
        Paint paint = new Paint(1);
        this.mLinePaint = paint;
        paint.setColor(this.mLineColor);
        this.mLinePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.mLinePaint.setStrokeWidth(this.mLineWidth);
        this.mDrawFilter = new PaintFlagsDrawFilter(0, 3);
        this.debugRect = new Rect();
        this.mouthRect = new RectF();
    }

    public void setLineColor(int i) {
        this.mLineColor = i;
        invalidate();
    }

    public void setLineWidth(int i) {
        this.mLineWidth = changeDp(i);
        invalidate();
    }

    public void setLineHeight(int i) {
        int iChangeDp = changeDp(i);
        this.mLineHeight = iChangeDp;
        this.mFixLineHeight = iChangeDp * 2;
        invalidate();
    }

    public void setSunRadius(int i) {
        this.mSunRadius = changeDp(i);
        invalidate();
    }

    public void setLineLevel(int i) {
        this.mLineLevel = i;
        invalidate();
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        Log.i(Tag, "w---->" + i + "  -------  h----->" + i2);
        this.mWidth = i;
        this.mHeight = i2;
        this.mLineLeft = (i / 2) - (this.mLineWidth / 2);
        int i5 = this.mSunRadius;
        int i6 = this.mFixLineHeight;
        int i7 = ((i2 / 2) - i5) - i6;
        this.mLineTop = i7;
        this.mLineBottom = i7 + this.mLineHeight;
        this.debugRect.left = ((i / 2) - i5) - i6;
        this.debugRect.right = (this.mWidth / 2) + this.mSunRadius + this.mFixLineHeight;
        this.debugRect.top = ((this.mHeight / 2) - this.mSunRadius) - this.mFixLineHeight;
        this.debugRect.bottom = (this.mHeight / 2) + this.mSunRadius + this.mFixLineHeight;
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
            size = ((this.mSunRadius + this.mFixLineHeight + this.mLineHeight) * 2) + getPaddingRight() + getPaddingLeft();
        }
        if (mode2 != 1073741824) {
            size2 = ((this.mSunRadius + this.mFixLineHeight + this.mLineHeight) * 2) + getPaddingTop() + getPaddingBottom();
        }
        setMeasuredDimension(size, size2);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        canvas.setDrawFilter(this.mDrawFilter);
        super.onDraw(canvas);
        drawLines(canvas);
    }

    private void drawLines(Canvas canvas) {
        for (int i = 0; i <= 360; i++) {
            if (i % this.mLineLevel == 0) {
                canvas.save();
                canvas.rotate(i, this.mWidth / 2, this.mHeight / 2);
                int i2 = this.mLineLeft;
                canvas.drawLine(i2, this.mLineTop, i2, this.mLineBottom, this.mLinePaint);
                canvas.restore();
            }
        }
    }

    public int changeDp(int i) {
        return (int) TypedValue.applyDimension(1, i, getResources().getDisplayMetrics());
    }
}
