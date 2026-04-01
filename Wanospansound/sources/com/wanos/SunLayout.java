package com.wanos;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import androidx.constraintlayout.motion.widget.Key;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;

/* JADX INFO: loaded from: classes3.dex */
public class SunLayout extends FrameLayout implements MaterialHeadListener {
    private static final int DEFAULT_LINE_COLOR = -65536;
    private static final int DEFAULT_LINE_HEIGHT = 3;
    private static final int DEFAULT_LINE_LEVEL = 30;
    private static final int DEFAULT_LINE_WIDTH = 1;
    private static final int DEFAULT_MOUTH_WIDTH = 3;
    private static final int DEFAULT_SUN_COLOR = -65536;
    private static final int DEFAULT_SUN_EYES_SIZE = 2;
    protected static final int DEFAULT_SUN_RADIUS = 12;
    private static final String Tag = "SunLayout";
    private ObjectAnimator mAnimator;
    private int mEyesSize;
    private int mLineColor;
    private int mLineHeight;
    private int mLineLevel;
    protected SunLineView mLineView;
    private int mLineWidth;
    private int mMouthStro;
    private int mSunColor;
    private int mSunRadius;
    protected SunFaceView mSunView;

    @Override // com.wanos.MaterialHeadListener
    public void onRelease(MaterialRefreshLayout materialRefreshLayout, float f) {
    }

    public SunLayout(Context context) {
        this(context, null);
    }

    public SunLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SunLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        this.mSunRadius = 12;
        this.mSunColor = SupportMenu.CATEGORY_MASK;
        this.mEyesSize = 2;
        this.mLineColor = SupportMenu.CATEGORY_MASK;
        this.mLineHeight = 3;
        this.mLineWidth = 1;
        this.mLineLevel = 30;
        this.mMouthStro = 3;
        Context context = getContext();
        SunFaceView sunFaceView = new SunFaceView(context);
        this.mSunView = sunFaceView;
        sunFaceView.setSunRadius(this.mSunRadius);
        this.mSunView.setSunColor(this.mSunColor);
        this.mSunView.setEyesSize(this.mEyesSize);
        this.mSunView.setMouthStro(this.mMouthStro);
        addView(this.mSunView);
        SunLineView sunLineView = new SunLineView(context);
        this.mLineView = sunLineView;
        sunLineView.setSunRadius(this.mSunRadius);
        this.mLineView.setLineLevel(this.mLineLevel);
        this.mLineView.setLineColor(this.mLineColor);
        this.mLineView.setLineHeight(this.mLineHeight);
        this.mLineView.setLineWidth(this.mLineWidth);
        addView(this.mLineView);
        startSunLineAnim(this.mLineView);
    }

    public void setSunRadius(int i) {
        this.mSunRadius = i;
        this.mSunView.setSunRadius(i);
        this.mLineView.setSunRadius(this.mSunRadius);
    }

    public void setSunColor(int i) {
        this.mSunColor = i;
        this.mSunView.setSunColor(i);
    }

    public void setEyesSize(int i) {
        this.mEyesSize = i;
        this.mSunView.setEyesSize(i);
    }

    public void setLineLevel(int i) {
        this.mLineLevel = i;
        this.mLineView.setLineLevel(i);
    }

    public void setLineColor(int i) {
        this.mLineColor = i;
        this.mLineView.setLineColor(i);
    }

    public void setLineWidth(int i) {
        this.mLineWidth = i;
        this.mLineView.setLineWidth(i);
    }

    public void setLineHeight(int i) {
        this.mLineHeight = i;
        this.mLineView.setLineHeight(i);
    }

    public void setMouthStro(int i) {
        this.mMouthStro = i;
        this.mSunView.setMouthStro(i);
    }

    public void startSunLineAnim(View view) {
        if (this.mAnimator == null) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, Key.ROTATION, 0.0f, 720.0f);
            this.mAnimator = objectAnimatorOfFloat;
            objectAnimatorOfFloat.setDuration(7000L);
            this.mAnimator.setInterpolator(new LinearInterpolator());
            this.mAnimator.setRepeatCount(-1);
        }
        if (this.mAnimator.isRunning()) {
            return;
        }
        this.mAnimator.start();
    }

    public void cancelSunLineAnim() {
        ObjectAnimator objectAnimator = this.mAnimator;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
    }

    @Override // com.wanos.MaterialHeadListener
    public void onComlete(MaterialRefreshLayout materialRefreshLayout) {
        cancelSunLineAnim();
        ViewCompat.setScaleX(this, 0.0f);
        ViewCompat.setScaleY(this, 0.0f);
    }

    @Override // com.wanos.MaterialHeadListener
    public void onBegin(MaterialRefreshLayout materialRefreshLayout) {
        ViewCompat.setScaleX(this, 0.001f);
        ViewCompat.setScaleY(this, 0.001f);
    }

    @Override // com.wanos.MaterialHeadListener
    public void onPull(MaterialRefreshLayout materialRefreshLayout, float f) {
        float fLimitValue = Util.limitValue(1.0f, f);
        if (fLimitValue >= 0.7d) {
            this.mLineView.setVisibility(0);
        } else {
            this.mLineView.setVisibility(8);
        }
        this.mSunView.setPerView(this.mSunRadius, fLimitValue);
        ViewCompat.setScaleX(this, fLimitValue);
        ViewCompat.setScaleY(this, fLimitValue);
        ViewCompat.setAlpha(this, fLimitValue);
    }

    @Override // com.wanos.MaterialHeadListener
    public void onRefreshing(MaterialRefreshLayout materialRefreshLayout) {
        startSunLineAnim(this.mLineView);
    }
}
