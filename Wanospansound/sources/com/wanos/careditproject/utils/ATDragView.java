package com.wanos.careditproject.utils;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

/* JADX INFO: loaded from: classes3.dex */
public class ATDragView extends View {
    private static final int BG_HEIGHT = 5;
    private static final int DEF_HEIGHT = 125;
    private static final int DEF_PADDING = 50;
    private static final float SEEK_BG_SCALE = 0.55f;
    private static final int SEEK_STROKE_SIZE = 1;
    private static final float SEEK_TEXT_SCALE = 0.2857143f;
    private int currentMovingType;
    private int downX;
    private OnDragFinishedListener dragFinishedListener;
    private int leftPosition;
    private int leftSeekBallX;
    private int limit;
    private int rightPosition;
    private int rightSeekBallX;
    private RectF seekBGRectF;
    private Paint seekBallEndPaint;
    private Paint seekBallPaint;
    private int seekBallRadio;
    private int seekBallSolidColor;
    private int seekBallStrokeColor;
    private Paint seekBallStrokePaint;
    private int seekBallY;
    private int seekBgColor;
    private Paint seekBgPaint;
    private int seekPbColor;
    private Paint seekPbPaint;
    private RectF seekPbRectF;
    private int seekTextColor;
    private Paint seekTextPaint;
    private int seekTextSize;
    private int seekTextY;
    private int viewHeight;
    private int viewWidth;

    public interface OnDragFinishedListener {
        void dragFinished(int i, int i2);
    }

    private void drawTexts(Canvas canvas) {
    }

    public ATDragView(Context context) {
        this(context, null);
    }

    public ATDragView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ATDragView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, com.wanos.careditproject.R.styleable.ATDragView, i, com.wanos.careditproject.R.style.def_dragview);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i2);
            if (index == com.wanos.careditproject.R.styleable.ATDragView_seek_bg_color) {
                this.seekBgColor = typedArrayObtainStyledAttributes.getColor(index, ViewCompat.MEASURED_STATE_MASK);
            } else if (index == com.wanos.careditproject.R.styleable.ATDragView_seek_pb_color) {
                this.seekPbColor = typedArrayObtainStyledAttributes.getColor(index, ViewCompat.MEASURED_STATE_MASK);
            } else if (index == com.wanos.careditproject.R.styleable.ATDragView_seek_ball_solid_color) {
                this.seekBallSolidColor = typedArrayObtainStyledAttributes.getColor(index, ViewCompat.MEASURED_STATE_MASK);
            } else if (index == com.wanos.careditproject.R.styleable.ATDragView_seek_ball_stroke_color) {
                this.seekBallStrokeColor = typedArrayObtainStyledAttributes.getColor(index, ViewCompat.MEASURED_STATE_MASK);
            } else if (index == com.wanos.careditproject.R.styleable.ATDragView_seek_text_color) {
                this.seekTextColor = typedArrayObtainStyledAttributes.getColor(index, ViewCompat.MEASURED_STATE_MASK);
            } else if (index == com.wanos.careditproject.R.styleable.ATDragView_seek_text_size) {
                this.seekTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
            }
        }
        typedArrayObtainStyledAttributes.recycle();
        init();
    }

    private void init() {
        this.currentMovingType = 99;
        this.seekTextPaint = creatPaint(this.seekTextColor, this.seekTextSize, Paint.Style.FILL, 0);
        this.seekBgPaint = creatPaint(this.seekBgColor, 0, Paint.Style.FILL, 0);
        this.seekBallPaint = creatPaint(this.seekBallSolidColor, 0, Paint.Style.FILL, 0);
        this.seekPbPaint = creatPaint(this.seekPbColor, 0, Paint.Style.FILL, 0);
        this.seekBallEndPaint = creatPaint(this.seekPbColor, 0, Paint.Style.FILL, 0);
        Paint paintCreatPaint = creatPaint(this.seekBallStrokeColor, 0, Paint.Style.FILL, 0);
        this.seekBallStrokePaint = paintCreatPaint;
        paintCreatPaint.setShadowLayer(5.0f, 2.0f, 2.0f, this.seekBallStrokeColor);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.viewHeight = i2;
        this.viewWidth = i;
        this.seekBallRadio = 30;
        this.seekBallY = (int) ((i2 * SEEK_BG_SCALE) + 2.5f);
        this.seekTextY = (int) (i2 * SEEK_TEXT_SCALE);
        this.leftSeekBallX = 30 + 50;
        this.rightSeekBallX = (i - 30) - 50;
        int i5 = this.seekBallRadio;
        int i6 = this.viewHeight;
        this.seekBGRectF = new RectF(i5 + 50, i6 * SEEK_BG_SCALE, (this.viewWidth - i5) - 50, (i6 * SEEK_BG_SCALE) + 5.0f);
        float f = this.leftSeekBallX;
        int i7 = this.viewHeight;
        this.seekPbRectF = new RectF(f, i7 * SEEK_BG_SCALE, this.rightSeekBallX, (i7 * SEEK_BG_SCALE) + 5.0f);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        if (mode == Integer.MIN_VALUE || mode == 0) {
            i2 = View.MeasureSpec.makeMeasureSpec((int) TypedValue.applyDimension(1, 125.0f, getContext().getResources().getDisplayMetrics()), 1073741824);
        }
        super.onMeasure(i, i2);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawSeekBG(canvas);
        drawSeekPB(canvas);
        drawLeftCircle(canvas);
        drawRightCircle(canvas);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            int x = (int) motionEvent.getX();
            this.downX = x;
            int movingLeftOrRight = getMovingLeftOrRight(x);
            this.currentMovingType = movingLeftOrRight;
            if (99 == movingLeftOrRight) {
                this.leftSeekBallX = this.downX;
            } else if (98 == movingLeftOrRight) {
                this.rightSeekBallX = this.downX;
            }
            float f = this.leftSeekBallX;
            int i = this.viewHeight;
            this.seekPbRectF = new RectF(f, i * SEEK_BG_SCALE, this.rightSeekBallX, (i * SEEK_BG_SCALE) + 5.0f);
        } else if (action == 1) {
            EditingUtils.log("MotionEvent.ACTION_UP movex=" + ((int) motionEvent.getX()));
            int i2 = this.currentMovingType;
            if (99 == i2) {
                this.leftPosition = getDataPosition((int) motionEvent.getX());
            } else if (98 == i2) {
                this.rightPosition = getDataPosition((int) motionEvent.getX());
            }
            OnDragFinishedListener onDragFinishedListener = this.dragFinishedListener;
            if (onDragFinishedListener != null) {
                onDragFinishedListener.dragFinished(this.leftPosition, this.rightPosition);
            }
        } else if (action == 2) {
            int x2 = (int) motionEvent.getX();
            EditingUtils.log("MotionEvent.ACTION_MOVE movex=" + x2);
            int i3 = this.leftSeekBallX;
            int i4 = this.rightSeekBallX;
            if (i3 == i4) {
                if (x2 - this.downX > 0) {
                    this.currentMovingType = 98;
                    this.rightSeekBallX = x2;
                } else {
                    this.currentMovingType = 99;
                    this.leftSeekBallX = x2;
                }
            } else {
                int i5 = this.currentMovingType;
                if (99 == i5) {
                    if (i3 - i4 >= 0) {
                        x2 = i4;
                    }
                    this.leftSeekBallX = x2;
                } else if (98 == i5) {
                    if (i4 - i3 <= 0) {
                        x2 = i3;
                    }
                    this.rightSeekBallX = x2;
                }
            }
            float f2 = this.leftSeekBallX;
            int i6 = this.viewHeight;
            this.seekPbRectF = new RectF(f2, i6 * SEEK_BG_SCALE, this.rightSeekBallX, (i6 * SEEK_BG_SCALE) + 5.0f);
        }
        int i7 = this.currentMovingType;
        if (99 == i7) {
            int i8 = this.leftSeekBallX;
            int i9 = this.seekBallRadio;
            if (i8 < i9 + 50) {
                this.leftSeekBallX = i9 + 50;
            }
            int i10 = this.leftSeekBallX;
            int i11 = this.viewWidth;
            if (i10 > (i11 - i9) - 50) {
                this.leftSeekBallX = (i11 - i9) - 50;
            }
        } else if (98 == i7) {
            int i12 = this.rightSeekBallX;
            int i13 = this.seekBallRadio;
            if (i12 < i13 + 50) {
                this.rightSeekBallX = i13 + 50;
            }
            int i14 = this.rightSeekBallX;
            int i15 = this.viewWidth;
            if (i14 > (i15 - i13) - 50) {
                this.rightSeekBallX = (i15 - i13) - 50;
            }
        }
        float f3 = this.leftSeekBallX;
        int i16 = this.viewHeight;
        this.seekPbRectF = new RectF(f3, i16 * SEEK_BG_SCALE, this.rightSeekBallX, (i16 * SEEK_BG_SCALE) + 5.0f);
        invalidate();
        return true;
    }

    private void drawSeekPB(Canvas canvas) {
        canvas.drawRect(this.seekPbRectF, this.seekPbPaint);
    }

    private void drawRightCircle(Canvas canvas) {
        setLayerType(1, null);
        canvas.drawCircle(this.rightSeekBallX, this.seekBallY, this.seekBallRadio, this.seekBallStrokePaint);
        canvas.drawCircle(this.rightSeekBallX, this.seekBallY, this.seekBallRadio - 1, this.seekBallEndPaint);
    }

    private void drawLeftCircle(Canvas canvas) {
        setLayerType(1, null);
        canvas.drawCircle(this.leftSeekBallX, this.seekBallY, this.seekBallRadio, this.seekBallStrokePaint);
        canvas.drawCircle(this.leftSeekBallX, this.seekBallY, this.seekBallRadio - 1, this.seekBallPaint);
    }

    private void drawSeekBG(Canvas canvas) {
        canvas.drawRect(this.seekBGRectF, this.seekBgPaint);
    }

    private Paint creatPaint(int i, int i2, Paint.Style style, int i3) {
        Paint paint = new Paint();
        paint.setColor(i);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(i3);
        paint.setDither(true);
        paint.setTextSize(i2);
        paint.setStyle(style);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        return paint;
    }

    private float getUnitWidth(int i) {
        return (((this.viewWidth - 100) - (this.seekBallRadio * 2)) * 1.0f) / i;
    }

    private int getCurrentSeekX(int i) {
        float unitWidth = getUnitWidth(this.limit);
        return (int) (unitWidth * (i / unitWidth));
    }

    private int getDataPosition(int i) {
        if (i <= 50) {
            return 0;
        }
        int i2 = this.viewWidth;
        if (i >= i2 - 50) {
            return 100;
        }
        return ((i - 50) * this.limit) / ((i2 - 100) - (this.seekBallRadio * 2));
    }

    public void setData(int i, OnDragFinishedListener onDragFinishedListener) {
        this.dragFinishedListener = onDragFinishedListener;
        this.limit = i;
        this.leftPosition = 0;
        this.rightPosition = i;
    }

    private int getMovingLeftOrRight(int i) {
        return Math.abs(this.leftSeekBallX - i) - Math.abs(this.rightSeekBallX - i) > 0 ? 98 : 99;
    }

    private static class BallType {
        private static final int LEFT = 99;
        private static final int RIGHT = 98;

        private BallType() {
        }
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        invalidate();
    }
}
