package com.wanos.media.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public class WanosVolumeView extends View {
    private static final int MAX_VOLUME = 0;
    private static final int MIN_VOLUME = -60;
    private static final String TAG = "WanosVolumeView";
    private int mDefaultColor;
    private int mHeight;
    private float mNowProgress;
    private OnVolumeViewListener mOnVolumeViewListener;
    private final Paint mPaint;
    private final float mRadius;
    private final RectF mRectF;
    private int mSelectColor;
    private int mWidth;

    public interface OnVolumeViewListener {
        void onViewTouch();

        void onVolumeChange(float f);
    }

    public WanosVolumeView(Context context) {
        this(context, null);
    }

    public WanosVolumeView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WanosVolumeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mDefaultColor = Color.argb(76, 255, 255, 255);
        this.mSelectColor = Color.argb(255, 19, 104, 251);
        this.mRectF = new RectF();
        Paint paint = new Paint();
        this.mPaint = paint;
        this.mNowProgress = 50.0f;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.WanosVolumeView, i, 0);
        this.mDefaultColor = typedArrayObtainStyledAttributes.getColor(R.styleable.WanosVolumeView_wanos_volume_default_color, this.mDefaultColor);
        this.mSelectColor = typedArrayObtainStyledAttributes.getColor(R.styleable.WanosVolumeView_wanos_volume_select_color, this.mSelectColor);
        this.mRadius = typedArrayObtainStyledAttributes.getDimension(R.styleable.WanosVolumeView_wanos_volume_radius, TypedValue.applyDimension(1, 8.0f, context.getResources().getDisplayMetrics()));
        typedArrayObtainStyledAttributes.recycle();
        paint.setAntiAlias(true);
        paint.setColor(this.mDefaultColor);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        RectF rectF = this.mRectF;
        float f = this.mRadius;
        canvas.drawRoundRect(rectF, f, f, this.mPaint);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        getParent().requestDisallowInterceptTouchEvent(true);
        int action = motionEvent.getAction();
        if (action == 0) {
            OnVolumeViewListener onVolumeViewListener = this.mOnVolumeViewListener;
            if (onVolumeViewListener != null) {
                onVolumeViewListener.onViewTouch();
                float y = motionEvent.getY();
                int i = this.mHeight;
                if (y > i) {
                    y = i;
                }
                if (y < 0.0f) {
                    y = 0.0f;
                }
                this.mRectF.set(0.0f, y, this.mWidth, i);
                this.mNowProgress = (this.mRectF.height() / this.mHeight) * 100.0f;
                this.mOnVolumeViewListener.onVolumeChange(((r0 / 100.0f) * 60.0f) - 60.0f);
            }
        } else if (action == 2) {
            float y2 = motionEvent.getY();
            int i2 = this.mHeight;
            if (y2 > i2) {
                y2 = i2;
            }
            if (y2 < 0.0f) {
                y2 = 0.0f;
            }
            this.mRectF.set(0.0f, y2, this.mWidth, i2);
            float fHeight = (this.mRectF.height() / this.mHeight) * 100.0f;
            if (fHeight == this.mNowProgress) {
                return true;
            }
            this.mNowProgress = fHeight;
            OnVolumeViewListener onVolumeViewListener2 = this.mOnVolumeViewListener;
            if (onVolumeViewListener2 != null) {
                onVolumeViewListener2.onVolumeChange(((fHeight / 100.0f) * 60.0f) - 60.0f);
            }
            invalidate();
        }
        this.mRectF.set(0.0f, Math.max(motionEvent.getY(), 0.0f), this.mWidth, this.mHeight);
        invalidate();
        return true;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.mWidth = View.MeasureSpec.getSize(i);
        int size = View.MeasureSpec.getSize(i2);
        this.mHeight = size;
        this.mRectF.set(0.0f, size * (1.0f - (this.mNowProgress / 100.0f)), this.mWidth, size);
    }

    @Override // android.view.View
    public void setSelected(boolean z) {
        super.setSelected(z);
        ZeroLogcatTools.d(TAG, "setSelected: selected = " + z);
        if (z) {
            this.mPaint.setColor(this.mSelectColor);
        } else {
            this.mPaint.setColor(this.mDefaultColor);
        }
        invalidate();
    }

    public void setVolume(float f) {
        ZeroLogcatTools.d(TAG, "setVolume: " + f);
        if (f < -60.0f || f > 0.0f) {
            ZeroLogcatTools.e(TAG, "setVolume: 超出音量设置范围,min = +-60 max = 0 volume = " + f);
            return;
        }
        float f2 = ((f - (-60.0f)) / 60.0f) * 100.0f;
        this.mNowProgress = f2;
        RectF rectF = this.mRectF;
        int i = this.mHeight;
        rectF.set(0.0f, i * (1.0f - (f2 / 100.0f)), this.mWidth, i);
        invalidate();
    }

    public void setListener(OnVolumeViewListener onVolumeViewListener) {
        this.mOnVolumeViewListener = onVolumeViewListener;
    }
}
