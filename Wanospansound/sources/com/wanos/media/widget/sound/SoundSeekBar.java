package com.wanos.media.widget.sound;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public class SoundSeekBar extends View {
    private static final float MAX_PROGRESS = 1.0f;
    private static final float MIN_PROGRESS = -1.0f;
    private float mCy;
    private final float mFlagLineCx;
    private final float mFlagLineCy;
    private final Paint mLinePaint;
    private final RectF mLineRectf;
    private OnSeekChangeListener mOnSeekChangeListener;
    private float mProgress;
    private final Paint mProgressPaint;
    private final RectF mProgressTrackRectF;
    private float mSlideCxy;
    private final RectF mSlideLineRectf;
    private final Paint mSlidePaint;
    private final RectF mSlideRectF;
    private float mSlideSize;
    private float mTrackLeft;
    private final Paint mTrackPaint;
    private final float mTrackRadius;
    private final RectF mTrackRectF;
    private float mTrackRight;
    private final float mTrackWidth;
    private float mValueScale;
    private int mViewWidth;
    private static final int TRACK_COLOR = Color.argb(153, 255, 255, 255);
    private static final int PROGRESS_TRACK_COLOR = Color.rgb(255, 141, 106);
    private static final int SLIDE_COLOR = Color.rgb(242, 79, 28);
    private static final int FLAT_COLOR = Color.rgb(255, 255, 255);

    public interface OnSeekChangeListener {
        void onSeekChange(float f, BallMoveWay ballMoveWay);

        void onSeekDown();

        void onSeekUp();
    }

    public SoundSeekBar(Context context) {
        this(context, null);
    }

    public SoundSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SoundSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mTrackRectF = new RectF();
        this.mTrackPaint = new Paint();
        this.mProgressTrackRectF = new RectF();
        this.mProgressPaint = new Paint();
        this.mSlideRectF = new RectF();
        this.mSlideLineRectf = new RectF();
        this.mSlidePaint = new Paint();
        this.mLinePaint = new Paint();
        this.mLineRectf = new RectF();
        this.mProgress = 0.0f;
        this.mValueScale = 1.0f;
        this.mFlagLineCx = getResources().getDimension(R.dimen.zero_sound_seek_flag_width) / 2.0f;
        this.mFlagLineCy = getResources().getDimension(R.dimen.zero_sound_seek_flag_height) / 2.0f;
        this.mTrackWidth = getResources().getDimension(R.dimen.zero_sound_seek_track_width);
        this.mTrackRadius = getResources().getDimension(R.dimen.zero_sound_seek_track_radio);
        initPaintParams();
    }

    private void initPaintParams() {
        this.mTrackPaint.setAntiAlias(true);
        this.mTrackPaint.setColor(TRACK_COLOR);
        this.mTrackPaint.setStyle(Paint.Style.FILL);
        this.mProgressPaint.setAntiAlias(true);
        this.mProgressPaint.setColor(PROGRESS_TRACK_COLOR);
        this.mProgressPaint.setStyle(Paint.Style.FILL);
        this.mSlidePaint.setAntiAlias(true);
        this.mSlidePaint.setColor(SLIDE_COLOR);
        this.mSlidePaint.setStyle(Paint.Style.FILL);
        this.mLinePaint.setAntiAlias(true);
        this.mLinePaint.setColor(FLAT_COLOR);
        this.mSlidePaint.setStyle(Paint.Style.FILL);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = this.mTrackRectF;
        float f = this.mTrackRadius;
        canvas.drawRoundRect(rectF, f, f, this.mTrackPaint);
        RectF rectF2 = this.mProgressTrackRectF;
        float f2 = this.mTrackRadius;
        canvas.drawRoundRect(rectF2, f2, f2, this.mProgressPaint);
        RectF rectF3 = this.mLineRectf;
        float f3 = this.mTrackRadius;
        canvas.drawRoundRect(rectF3, f3, f3, this.mLinePaint);
        RectF rectF4 = this.mSlideRectF;
        float f4 = this.mSlideCxy;
        canvas.drawRoundRect(rectF4, f4, f4, this.mSlidePaint);
        RectF rectF5 = this.mSlideLineRectf;
        float f5 = this.mTrackRadius;
        canvas.drawRoundRect(rectF5, f5, f5, this.mLinePaint);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.mViewWidth = View.MeasureSpec.getSize(i);
        float size = View.MeasureSpec.getSize(i2);
        this.mCy = size / 2.0f;
        int i3 = this.mViewWidth;
        this.mSlideSize = i3;
        float f = this.mTrackWidth / 2.0f;
        float f2 = i3 / 2.0f;
        float f3 = f2 - f;
        this.mTrackLeft = f3;
        float f4 = f + f2;
        this.mTrackRight = f4;
        float f5 = i3 / 2.0f;
        this.mSlideCxy = f5;
        this.mTrackRectF.set(f3, f5, f4, size - f5);
        RectF rectF = this.mLineRectf;
        float f6 = this.mFlagLineCx;
        float f7 = this.mCy;
        float f8 = this.mFlagLineCy;
        rectF.set(f2 - f6, f7 - f8, f2 + f6, f7 + f8);
        initAttrs();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        ZeroLogcatTools.w("ZeroSoundSeekBar", "onTouchEvent: " + motionEvent.getAction());
        float y = motionEvent.getY();
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action == 1) {
                OnSeekChangeListener onSeekChangeListener = this.mOnSeekChangeListener;
                if (onSeekChangeListener != null) {
                    onSeekChangeListener.onSeekUp();
                }
            } else if (action == 2) {
            }
            return true;
        }
        OnSeekChangeListener onSeekChangeListener2 = this.mOnSeekChangeListener;
        if (onSeekChangeListener2 != null) {
            onSeekChangeListener2.onSeekDown();
        }
        float f = this.mCy;
        if (y > f) {
            this.mProgressTrackRectF.set(this.mTrackLeft, f, this.mTrackRight, y);
            if (this.mProgressTrackRectF.bottom >= this.mTrackRectF.bottom) {
                this.mProgressTrackRectF.bottom = this.mTrackRectF.bottom;
            }
            float f2 = (this.mViewWidth - this.mSlideSize) / 2.0f;
            this.mSlideRectF.set(f2, this.mProgressTrackRectF.bottom - this.mSlideCxy, this.mSlideSize + f2, this.mProgressTrackRectF.bottom + this.mSlideCxy);
            this.mSlideLineRectf.set(this.mSlideRectF.centerX() - this.mFlagLineCx, this.mSlideRectF.centerY() - this.mFlagLineCy, this.mSlideRectF.centerX() + this.mFlagLineCx, this.mSlideRectF.centerY() + this.mFlagLineCy);
            OnSeekChangeListener onSeekChangeListener3 = this.mOnSeekChangeListener;
            if (onSeekChangeListener3 != null) {
                onSeekChangeListener3.onSeekChange(-((this.mProgressTrackRectF.bottom - this.mCy) * this.mValueScale), BallMoveWay.WAY_USER_TOUCH);
            }
        } else if (y < f) {
            this.mProgressTrackRectF.set(this.mTrackLeft, y, this.mTrackRight, f);
            if (this.mProgressTrackRectF.top <= this.mTrackRectF.top) {
                this.mProgressTrackRectF.top = this.mTrackRectF.top;
            }
            float f3 = (this.mViewWidth - this.mSlideSize) / 2.0f;
            this.mSlideRectF.set(f3, this.mProgressTrackRectF.top - this.mSlideCxy, this.mSlideSize + f3, this.mProgressTrackRectF.top + this.mSlideCxy);
            this.mSlideLineRectf.set(this.mSlideRectF.centerX() - this.mFlagLineCx, this.mSlideRectF.centerY() - this.mFlagLineCy, this.mSlideRectF.centerX() + this.mFlagLineCx, this.mSlideRectF.centerY() + this.mFlagLineCy);
            OnSeekChangeListener onSeekChangeListener4 = this.mOnSeekChangeListener;
            if (onSeekChangeListener4 != null) {
                onSeekChangeListener4.onSeekChange((this.mCy - this.mProgressTrackRectF.top) * this.mValueScale, BallMoveWay.WAY_USER_TOUCH);
            }
        } else {
            this.mProgressTrackRectF.set(this.mTrackLeft, f, this.mTrackRight, f);
            float f4 = (this.mViewWidth - this.mSlideSize) / 2.0f;
            this.mSlideRectF.set(f4, this.mProgressTrackRectF.top - this.mSlideCxy, this.mSlideSize + f4, this.mProgressTrackRectF.top + this.mSlideCxy);
            this.mSlideLineRectf.set(this.mSlideRectF.centerX() - this.mFlagLineCx, this.mSlideRectF.centerY() - this.mFlagLineCy, this.mSlideRectF.centerX() + this.mFlagLineCx, this.mSlideRectF.centerY() + this.mFlagLineCy);
            OnSeekChangeListener onSeekChangeListener5 = this.mOnSeekChangeListener;
            if (onSeekChangeListener5 != null) {
                onSeekChangeListener5.onSeekChange(0.0f, BallMoveWay.WAY_USER_TOUCH);
            }
        }
        invalidate();
        return true;
    }

    public void setOnSeekChangeListener(OnSeekChangeListener onSeekChangeListener) {
        this.mOnSeekChangeListener = onSeekChangeListener;
    }

    private void initAttrs() {
        float fHeight = 2.0f / this.mTrackRectF.height();
        this.mValueScale = fHeight;
        float f = this.mProgress;
        if (f > 0.0f) {
            float f2 = f / fHeight;
            float f3 = this.mCy;
            this.mProgressTrackRectF.set(this.mTrackLeft, f3 - f2, this.mTrackRight, f3);
            float f4 = (this.mViewWidth - this.mSlideSize) / 2.0f;
            this.mSlideRectF.set(f4, this.mProgressTrackRectF.top - this.mSlideCxy, this.mSlideSize + f4, this.mProgressTrackRectF.top + this.mSlideCxy);
        } else if (f < 0.0f) {
            float fAbs = Math.abs(f / fHeight);
            float f5 = this.mCy;
            this.mProgressTrackRectF.set(this.mTrackLeft, f5, this.mTrackRight, fAbs + f5);
            float f6 = (this.mViewWidth - this.mSlideSize) / 2.0f;
            this.mSlideRectF.set(f6, this.mProgressTrackRectF.bottom - this.mSlideCxy, this.mSlideSize + f6, this.mProgressTrackRectF.bottom + this.mSlideCxy);
        } else {
            RectF rectF = this.mProgressTrackRectF;
            float f7 = this.mTrackLeft;
            float f8 = this.mCy;
            rectF.set(f7, f8, this.mTrackRight, f8);
            float f9 = this.mViewWidth;
            float f10 = this.mSlideSize;
            float f11 = (f9 - f10) / 2.0f;
            RectF rectF2 = this.mSlideRectF;
            float f12 = this.mCy;
            float f13 = this.mSlideCxy;
            rectF2.set(f11, f12 - f13, f10 + f11, f12 + f13);
        }
        this.mSlideLineRectf.set(this.mSlideRectF.centerX() - this.mFlagLineCx, this.mSlideRectF.centerY() - this.mFlagLineCy, this.mSlideRectF.centerX() + this.mFlagLineCx, this.mSlideRectF.centerY() + this.mFlagLineCy);
    }

    public void setProgress(float f) {
        if (f == this.mProgress) {
            return;
        }
        this.mProgress = f;
        initAttrs();
        invalidate();
    }

    public void initProgress(float f) {
        if (this.mProgress == f) {
            return;
        }
        this.mProgress = f;
        initAttrs();
        invalidate();
    }

    public float getProgress() {
        return this.mProgress;
    }
}
