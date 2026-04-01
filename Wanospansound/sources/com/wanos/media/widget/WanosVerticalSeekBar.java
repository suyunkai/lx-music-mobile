package com.wanos.media.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public class WanosVerticalSeekBar extends View {
    private static final int MAX_VALUE = 12;
    private static final int MIN_VALUE = -60;
    private static final String TAG = "wanos[VerticalSeekBar]";
    private OnSeekChangeListener mOnSeekChangeListener;
    private int mProgress;
    private final Paint mProgressPaint;
    private final RectF mProgressTrackRectF;
    private float mTrackLeft;
    private final Paint mTrackPaint;
    private final float mTrackRadio;
    private final RectF mTrackRectF;
    private float mTrackRight;
    private final float mTrackWidth;
    private final int mViewWidth;
    private static final int TRACK_COLOR = Color.argb(26, 255, 255, 255);
    private static final int PROGRESS_TRACK_COLOR = Color.rgb(19, 104, 251);

    public interface OnSeekChangeListener {
        void onSeekChange(float f, boolean z);

        void onSeekTouchUp(float f);
    }

    public WanosVerticalSeekBar(Context context) {
        this(context, null);
    }

    public WanosVerticalSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WanosVerticalSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mTrackRectF = new RectF();
        Paint paint = new Paint();
        this.mTrackPaint = paint;
        this.mProgressTrackRectF = new RectF();
        Paint paint2 = new Paint();
        this.mProgressPaint = paint2;
        this.mProgress = 0;
        setBackgroundResource(R.drawable.bg_zero_volume);
        float dimension = getResources().getDimension(R.dimen.zero_volume_width);
        this.mTrackWidth = dimension;
        this.mTrackRadio = getResources().getDimension(R.dimen.zero_volume_radio);
        this.mViewWidth = (int) (dimension * 3.0f);
        paint.setAntiAlias(true);
        paint.setColor(TRACK_COLOR);
        paint.setStyle(Paint.Style.FILL);
        paint2.setAntiAlias(true);
        paint2.setColor(PROGRESS_TRACK_COLOR);
        paint2.setStyle(Paint.Style.FILL);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        RectF rectF = this.mTrackRectF;
        float f = this.mTrackRadio;
        canvas.drawRoundRect(rectF, f, f, this.mTrackPaint);
        RectF rectF2 = this.mProgressTrackRectF;
        float f2 = this.mTrackRadio;
        canvas.drawRoundRect(rectF2, f2, f2, this.mProgressPaint);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        float y = motionEvent.getY();
        int action = motionEvent.getAction();
        if (action == 1) {
            OnSeekChangeListener onSeekChangeListener = this.mOnSeekChangeListener;
            if (onSeekChangeListener != null) {
                onSeekChangeListener.onSeekTouchUp(toVolumedB(this.mProgress));
            }
        } else if (action == 2) {
            this.mProgressTrackRectF.set(this.mTrackLeft, Math.min(y, this.mTrackRectF.height() + this.mTrackWidth), this.mTrackRight, this.mTrackRectF.bottom);
            if (this.mProgressTrackRectF.top <= this.mTrackRectF.top) {
                this.mProgressTrackRectF.top = this.mTrackRectF.top;
            }
            int iHeight = (int) ((this.mProgressTrackRectF.height() / this.mTrackRectF.height()) * 100.0f);
            if (iHeight != this.mProgress) {
                this.mProgress = iHeight;
                OnSeekChangeListener onSeekChangeListener2 = this.mOnSeekChangeListener;
                if (onSeekChangeListener2 != null) {
                    onSeekChangeListener2.onSeekChange(toVolumedB(iHeight), true);
                }
            }
            invalidate();
        }
        return true;
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        WanosVerticalSeekBarState wanosVerticalSeekBarState = new WanosVerticalSeekBarState(super.onSaveInstanceState());
        wanosVerticalSeekBarState.mProgress = this.mProgress;
        return wanosVerticalSeekBarState;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof WanosVerticalSeekBarState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        WanosVerticalSeekBarState wanosVerticalSeekBarState = (WanosVerticalSeekBarState) parcelable;
        super.onRestoreInstanceState(wanosVerticalSeekBarState.getSuperState());
        this.mProgress = wanosVerticalSeekBarState.mProgress;
        this.mProgressTrackRectF.set(this.mTrackLeft, this.mTrackWidth + (this.mTrackRectF.height() * (1.0f - (this.mProgress / 100.0f))), this.mTrackRight, this.mProgressTrackRectF.bottom);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(this.mViewWidth, View.MeasureSpec.getSize(i2));
        float f = this.mTrackWidth / 2.0f;
        float f2 = this.mViewWidth / 2.0f;
        this.mTrackLeft = f2 - f;
        this.mTrackRight = f2 + f;
        float measuredHeight = getMeasuredHeight();
        float f3 = this.mTrackWidth;
        float f4 = (int) (measuredHeight - f3);
        this.mTrackRectF.set(this.mTrackLeft, f3, this.mTrackRight, f4);
        this.mProgressTrackRectF.set(this.mTrackLeft, this.mTrackWidth + (this.mTrackRectF.height() * (1.0f - (this.mProgress / 100.0f))), this.mTrackRight, f4);
    }

    public void setProgressFordB(float f) {
        if (f < -60.0f || f > 12.0f) {
            ZeroLogcatTools.e(TAG, "设置进度==> 非正常范围的音量");
            return;
        }
        float f2 = f - (-60.0f);
        ZeroLogcatTools.d(TAG, "设置进度==> a1 = " + f2 + "| a2 = 72.0");
        this.mProgress = (int) ((f2 / 72.0f) * 100.0f);
        ZeroLogcatTools.e(TAG, "设置进度==> 当前进度:" + this.mProgress);
        this.mProgressTrackRectF.set(this.mTrackLeft, this.mTrackWidth + (this.mTrackRectF.height() * (1.0f - (this.mProgress / 100.0f))), this.mTrackRight, this.mProgressTrackRectF.bottom);
        OnSeekChangeListener onSeekChangeListener = this.mOnSeekChangeListener;
        if (onSeekChangeListener != null) {
            onSeekChangeListener.onSeekChange(f, false);
        }
        invalidate();
    }

    public int getProgressDB() {
        return toVolumedB(this.mProgress);
    }

    public void setOnSeekChangeListener(OnSeekChangeListener onSeekChangeListener) {
        this.mOnSeekChangeListener = onSeekChangeListener;
    }

    private int toVolumedB(int i) {
        int i2 = ((int) (i * 0.72f)) + MIN_VALUE;
        return i2 < MIN_VALUE ? MIN_VALUE : Math.min(i2, 12);
    }

    public static class WanosVerticalSeekBarState extends View.BaseSavedState {
        public static final Parcelable.Creator<WanosVerticalSeekBarState> CREATOR = new Parcelable.Creator<WanosVerticalSeekBarState>() { // from class: com.wanos.media.widget.WanosVerticalSeekBar.WanosVerticalSeekBarState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public WanosVerticalSeekBarState createFromParcel(Parcel parcel) {
                return new WanosVerticalSeekBarState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public WanosVerticalSeekBarState[] newArray(int i) {
                return new WanosVerticalSeekBarState[i];
            }
        };
        int mProgress;

        public WanosVerticalSeekBarState(Parcel parcel) {
            super(parcel);
            this.mProgress = parcel.readInt();
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.mProgress);
        }

        public WanosVerticalSeekBarState(Parcelable parcelable) {
            super(parcelable);
        }
    }
}
