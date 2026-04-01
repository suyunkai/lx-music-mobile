package com.wanos;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;

/* JADX INFO: loaded from: classes3.dex */
public class MaterialWaveView extends View implements MaterialHeadListener {
    public static int DefaulHeadHeight = 0;
    public static int DefaulWaveHeight = 0;
    public static final String TAG = "wanos:[MaterialWaveView]";
    private int color;
    private int headHeight;
    private Paint paint;
    private Path path;
    private int waveHeight;

    @Override // com.wanos.MaterialHeadListener
    public void onBegin(MaterialRefreshLayout materialRefreshLayout) {
    }

    @Override // com.wanos.MaterialHeadListener
    public void onRelease(MaterialRefreshLayout materialRefreshLayout, float f) {
    }

    public MaterialWaveView(Context context) {
        this(context, null, 0);
    }

    public MaterialWaveView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MaterialWaveView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        setWillNotDraw(false);
        this.path = new Path();
        Paint paint = new Paint();
        this.paint = paint;
        paint.setAntiAlias(true);
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int i) {
        this.color = i;
        invalidate();
    }

    public int getHeadHeight() {
        return this.headHeight;
    }

    public void setHeadHeight(int i) {
        this.headHeight = i;
    }

    public int getWaveHeight() {
        return this.waveHeight;
    }

    public void setWaveHeight(int i) {
        this.waveHeight = i;
    }

    public int getDefaulWaveHeight() {
        return DefaulWaveHeight;
    }

    public void setDefaulWaveHeight(int i) {
        DefaulWaveHeight = i;
    }

    public int getDefaulHeadHeight() {
        return DefaulHeadHeight;
    }

    public void setDefaulHeadHeight(int i) {
        DefaulHeadHeight = i;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.path.reset();
        this.paint.setColor(this.color);
        this.path.lineTo(0.0f, this.headHeight);
        this.path.quadTo(getMeasuredWidth() / 2, this.headHeight + this.waveHeight, getMeasuredWidth(), this.headHeight);
        this.path.lineTo(getMeasuredWidth(), 0.0f);
        canvas.drawPath(this.path, this.paint);
    }

    @Override // com.wanos.MaterialHeadListener
    public void onComlete(MaterialRefreshLayout materialRefreshLayout) {
        this.waveHeight = 0;
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(this.headHeight, 0);
        valueAnimatorOfInt.setDuration(200L);
        valueAnimatorOfInt.setInterpolator(new DecelerateInterpolator());
        valueAnimatorOfInt.start();
        valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.wanos.MaterialWaveView.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                MaterialWaveView.this.headHeight = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                MaterialWaveView.this.invalidate();
            }
        });
    }

    @Override // com.wanos.MaterialHeadListener
    public void onPull(MaterialRefreshLayout materialRefreshLayout, float f) {
        setHeadHeight((int) (Util.dip2px(getContext(), DefaulHeadHeight) * Util.limitValue(1.0f, f)));
        setWaveHeight((int) (Util.dip2px(getContext(), DefaulWaveHeight) * Math.max(0.0f, f - 1.0f)));
        invalidate();
    }

    @Override // com.wanos.MaterialHeadListener
    public void onRefreshing(MaterialRefreshLayout materialRefreshLayout) {
        setHeadHeight(Util.dip2px(getContext(), DefaulHeadHeight));
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(getWaveHeight(), 0);
        valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.wanos.MaterialWaveView.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Log.i(MaterialWaveView.TAG, "value--->" + ((Integer) valueAnimator.getAnimatedValue()).intValue());
                MaterialWaveView.this.setWaveHeight(((Integer) valueAnimator.getAnimatedValue()).intValue());
                MaterialWaveView.this.invalidate();
            }
        });
        valueAnimatorOfInt.setInterpolator(new BounceInterpolator());
        valueAnimatorOfInt.setDuration(200L);
        valueAnimatorOfInt.start();
    }
}
