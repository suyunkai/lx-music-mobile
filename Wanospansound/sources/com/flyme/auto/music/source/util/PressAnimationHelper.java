package com.flyme.auto.music.source.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatValueHolder;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.flyme.auto.music.source.R;
import com.google.android.material.shape.Shapeable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class PressAnimationHelper {
    private static final int ALPHA_DURATION = 200;
    private static final float ALPHA_FROM = 1.0f;
    private static final float ALPHA_TO = 0.75f;
    private static final float DAMPING = 0.89f;
    private static final float SCALE_FROM = 1.0f;
    private static final float SCALE_TO = 0.95f;
    private static final float SPRING_ANIMATION_FLOAT_TO_INT_PROP = 100.0f;
    private static final float STIFFNESS = 250.0f;
    private boolean mPressedAlphaEnabled;
    private boolean mPressedAnimationEnabled;
    private float mScaleTo;
    private View mTargetView;
    private boolean mUseHardwareLayer;
    private final List<PressAnimationInfo> spaInfoList;

    public static class PressAnimationInfo {
        public Animator alphaAnimator;
        public Drawable drawable;
        public float originAlpha;
        public SpringAnimation springAnimationX;
        public SpringAnimation springAnimationY;
        public View view;
    }

    public void updateTargetViewAlpha(float f) {
    }

    public PressAnimationHelper() {
        this.spaInfoList = new ArrayList();
        this.mPressedAlphaEnabled = false;
        this.mPressedAnimationEnabled = true;
        this.mScaleTo = SCALE_TO;
    }

    public PressAnimationHelper(Context context, AttributeSet attributeSet, int i, int i2, View view) {
        this.spaInfoList = new ArrayList();
        this.mPressedAlphaEnabled = false;
        this.mPressedAnimationEnabled = true;
        this.mScaleTo = SCALE_TO;
        this.mTargetView = view;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FlymeView, i, i2);
        this.mPressedAlphaEnabled = typedArrayObtainStyledAttributes.getBoolean(R.styleable.FlymeView_pressedAlphaEnabled, false);
        this.mPressedAnimationEnabled = typedArrayObtainStyledAttributes.getBoolean(R.styleable.FlymeView_pressedAnimationEnabled, true);
        typedArrayObtainStyledAttributes.recycle();
    }

    public void addTarget(View view) {
        addTargetView(view, false);
    }

    public void addTargetView(final View view, boolean z) {
        if (view == null) {
            return;
        }
        if (!view.isClickable()) {
            view.setClickable(true);
        }
        if (Build.VERSION.SDK_INT >= 29) {
            tryToRemovePressColor(view);
        }
        this.mUseHardwareLayer = useHardwareLayer(view);
        if (z) {
            PressAnimationInfo pressAnimationInfo = new PressAnimationInfo();
            pressAnimationInfo.view = view;
            this.spaInfoList.add(pressAnimationInfo);
            return;
        }
        view.setOnTouchListener(new View.OnTouchListener() { // from class: com.flyme.auto.music.source.util.PressAnimationHelper$$ExternalSyntheticLambda3
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view2, MotionEvent motionEvent) {
                return this.f$0.m243xfb6c7a42(view, view2, motionEvent);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$addTargetView$0$com-flyme-auto-music-source-util-PressAnimationHelper, reason: not valid java name */
    /* synthetic */ boolean m243xfb6c7a42(View view, View view2, MotionEvent motionEvent) {
        doScaleAndAlpha(view, motionEvent);
        return false;
    }

    public void handleCustomTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0 || motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
            Iterator<PressAnimationInfo> it = this.spaInfoList.iterator();
            while (it.hasNext()) {
                if (view == it.next().view) {
                    doScaleAndAlpha(view, motionEvent);
                }
            }
        }
    }

    public void setScaleTo(float f) {
        this.mScaleTo = f;
    }

    private boolean useHardwareLayer(View view) {
        return !(view instanceof Shapeable);
    }

    private void dealGradientDrawable(int i, StateListDrawable stateListDrawable, int i2) {
        for (int i3 = 0; i3 < i; i3++) {
            int[] stateSet = stateListDrawable.getStateSet(i3);
            if (!(stateSet == null || stateSet.length == 0)) {
                for (int i4 : stateSet) {
                    if (i4 == 16842919) {
                        setGradientDrawableColor(stateListDrawable, i3, i2);
                    }
                }
            }
        }
    }

    private void setGradientDrawableColor(StateListDrawable stateListDrawable, int i, int i2) {
        Drawable stateDrawable = stateListDrawable.getStateDrawable(i);
        if (stateDrawable instanceof GradientDrawable) {
            ((GradientDrawable) stateDrawable).setColor(i2);
        }
    }

    private int getNormalStateColor(int i, StateListDrawable stateListDrawable) {
        int defaultColor = 0;
        for (int i2 = 0; i2 < i; i2++) {
            int[] stateSet = stateListDrawable.getStateSet(i2);
            if (stateSet == null || stateSet.length == 0) {
                defaultColor = getDefaultColor(stateListDrawable.getStateDrawable(i2));
            }
        }
        return defaultColor;
    }

    private int getDefaultColor(Drawable drawable) {
        if (drawable instanceof GradientDrawable) {
            GradientDrawable gradientDrawable = (GradientDrawable) drawable;
            if (gradientDrawable.getColor() != null) {
                return gradientDrawable.getColor().getDefaultColor();
            }
        }
        return 0;
    }

    private void tryToRemovePressColor(View view) {
        Drawable background = view.getBackground();
        if (background instanceof StateListDrawable) {
            StateListDrawable stateListDrawable = (StateListDrawable) background;
            int stateCount = stateListDrawable.getStateCount();
            int normalStateColor = getNormalStateColor(stateCount, stateListDrawable);
            if (normalStateColor == 0) {
                return;
            }
            dealGradientDrawable(stateCount, stateListDrawable, normalStateColor);
            return;
        }
        if (background instanceof RippleDrawable) {
            ((RippleDrawable) background).setColor(ColorStateList.valueOf(0));
        }
    }

    private void doScaleAndAlpha(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            handleDown(view);
        } else if (action == 1 || action == 3) {
            handleUp(view);
        }
    }

    private void handleDown(final View view) {
        PressAnimationInfo pressAnimationInfo;
        if (this.mPressedAnimationEnabled) {
            Object tag = view.getTag(R.id.fa_key_set_pressed_animation);
            if (tag == null) {
                SpringForce stiffness = new SpringForce().setDampingRatio(DAMPING).setStiffness(STIFFNESS);
                SpringAnimation minimumVisibleChange = new SpringAnimation(view, DynamicAnimation.SCALE_X).setSpring(stiffness).setMinimumVisibleChange(0.002f);
                SpringAnimation minimumVisibleChange2 = new SpringAnimation(view, DynamicAnimation.SCALE_Y).setSpring(stiffness).setMinimumVisibleChange(0.002f);
                minimumVisibleChange.addEndListener(new DynamicAnimation.OnAnimationEndListener() { // from class: com.flyme.auto.music.source.util.PressAnimationHelper$$ExternalSyntheticLambda1
                    @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener
                    public final void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f, float f2) {
                        this.f$0.m244x4cf25f30(view, dynamicAnimation, z, f, f2);
                    }
                });
                pressAnimationInfo = new PressAnimationInfo();
                pressAnimationInfo.view = view;
                pressAnimationInfo.originAlpha = view.getAlpha();
                pressAnimationInfo.springAnimationX = minimumVisibleChange;
                pressAnimationInfo.springAnimationY = minimumVisibleChange2;
            } else {
                pressAnimationInfo = (PressAnimationInfo) tag;
            }
            pressAnimationInfo.springAnimationX.getSpring().setFinalPosition(this.mScaleTo);
            pressAnimationInfo.springAnimationY.getSpring().setFinalPosition(this.mScaleTo);
            if (view.getLayerType() != 2 && this.mUseHardwareLayer) {
                view.setLayerType(2, null);
            }
            pressAnimationInfo.springAnimationX.start();
            pressAnimationInfo.springAnimationY.start();
            if (this.mPressedAlphaEnabled) {
                float f = pressAnimationInfo.originAlpha;
                if (pressAnimationInfo.alphaAnimator != null) {
                    pressAnimationInfo.alphaAnimator.cancel();
                }
                ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(1.0f * f, f * 0.75f);
                valueAnimatorOfFloat.setDuration(200L);
                valueAnimatorOfFloat.setInterpolator(getAlphaInterpolator());
                addDownListener(valueAnimatorOfFloat, view);
                valueAnimatorOfFloat.start();
                pressAnimationInfo.alphaAnimator = valueAnimatorOfFloat;
            }
            view.setTag(R.id.fa_key_set_pressed_animation, pressAnimationInfo);
        }
    }

    /* JADX INFO: renamed from: lambda$handleDown$1$com-flyme-auto-music-source-util-PressAnimationHelper, reason: not valid java name */
    /* synthetic */ void m244x4cf25f30(View view, DynamicAnimation dynamicAnimation, boolean z, float f, float f2) {
        if (view.getLayerType() == 2 && this.mUseHardwareLayer) {
            view.setLayerType(0, null);
        }
    }

    private void addDownListener(final ValueAnimator valueAnimator, final View view) {
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.flyme.auto.music.source.util.PressAnimationHelper.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                if (!PressAnimationHelper.this.isDisabledAlphaSet(view)) {
                    view.setAlpha(((Float) valueAnimator2.getAnimatedValue()).floatValue());
                } else {
                    valueAnimator.cancel();
                }
            }
        });
    }

    private void handleUp(final View view) {
        Object tag;
        if (this.mPressedAnimationEnabled && (tag = view.getTag(R.id.fa_key_set_pressed_animation)) != null) {
            PressAnimationInfo pressAnimationInfo = (PressAnimationInfo) tag;
            SpringAnimation springAnimation = pressAnimationInfo.springAnimationX;
            SpringAnimation springAnimation2 = pressAnimationInfo.springAnimationY;
            springAnimation.getSpring().setFinalPosition(1.0f);
            springAnimation2.getSpring().setFinalPosition(1.0f);
            if (view.getLayerType() != 2 && this.mUseHardwareLayer) {
                view.setLayerType(2, null);
            }
            springAnimation.start();
            springAnimation2.start();
            if (this.mPressedAlphaEnabled) {
                float f = pressAnimationInfo.originAlpha;
                if (pressAnimationInfo.alphaAnimator != null) {
                    pressAnimationInfo.alphaAnimator.cancel();
                }
                final ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.75f * f, 1.0f * f);
                valueAnimatorOfFloat.setDuration(200L);
                valueAnimatorOfFloat.setInterpolator(getAlphaInterpolator());
                valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.flyme.auto.music.source.util.PressAnimationHelper$$ExternalSyntheticLambda2
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                        this.f$0.m245x17e0acaa(view, valueAnimatorOfFloat, valueAnimator);
                    }
                });
                addUpListener(valueAnimatorOfFloat, view, f);
                valueAnimatorOfFloat.start();
                pressAnimationInfo.alphaAnimator = valueAnimatorOfFloat;
            }
        }
    }

    /* JADX INFO: renamed from: lambda$handleUp$2$com-flyme-auto-music-source-util-PressAnimationHelper, reason: not valid java name */
    /* synthetic */ void m245x17e0acaa(View view, ValueAnimator valueAnimator, ValueAnimator valueAnimator2) {
        if (!isDisabledAlphaSet(view)) {
            view.setAlpha(((Float) valueAnimator2.getAnimatedValue()).floatValue());
        } else {
            valueAnimator.cancel();
        }
    }

    private void addUpListener(ValueAnimator valueAnimator, final View view, final float f) {
        valueAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.flyme.auto.music.source.util.PressAnimationHelper.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                if (!PressAnimationHelper.this.isDisabledAlphaSet(view)) {
                    view.setAlpha(f);
                }
                view.setTag(R.id.fa_key_set_pressed_animation, null);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (!PressAnimationHelper.this.isDisabledAlphaSet(view)) {
                    view.setAlpha(f);
                }
                view.setTag(R.id.fa_key_set_pressed_animation, null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDisabledAlphaSet(View view) {
        return (view.isEnabled() || view.getTag(R.id.fa_key_set_enabled_with_alpha) == null) ? false : true;
    }

    public void addTargetDrawable(final Drawable drawable) {
        SpringAnimation spring = new SpringAnimation(new FloatValueHolder()).setSpring(new SpringForce().setDampingRatio(DAMPING).setStiffness(STIFFNESS));
        spring.setStartValue(SPRING_ANIMATION_FLOAT_TO_INT_PROP);
        Rect bounds = drawable.getBounds();
        final int iCenterX = bounds.centerX();
        final int iCenterY = bounds.centerY();
        final int iHeight = bounds.height();
        final int iWidth = bounds.width();
        spring.addUpdateListener(new DynamicAnimation.OnAnimationUpdateListener() { // from class: com.flyme.auto.music.source.util.PressAnimationHelper$$ExternalSyntheticLambda0
            @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationUpdateListener
            public final void onAnimationUpdate(DynamicAnimation dynamicAnimation, float f, float f2) {
                PressAnimationHelper.lambda$addTargetDrawable$3(drawable, iCenterX, iWidth, iCenterY, iHeight, dynamicAnimation, f, f2);
            }
        });
        PressAnimationInfo pressAnimationInfo = new PressAnimationInfo();
        pressAnimationInfo.drawable = drawable;
        pressAnimationInfo.springAnimationX = spring;
        this.spaInfoList.add(pressAnimationInfo);
    }

    static /* synthetic */ void lambda$addTargetDrawable$3(Drawable drawable, int i, int i2, int i3, int i4, DynamicAnimation dynamicAnimation, float f, float f2) {
        float f3 = f / SPRING_ANIMATION_FLOAT_TO_INT_PROP;
        float f4 = i;
        float f5 = (i2 / 2) * f3;
        float f6 = i3;
        float f7 = (i4 / 2) * f3;
        drawable.setBounds((int) (f4 - f5), (int) (f6 - f7), (int) (f4 + f5), (int) (f6 + f7));
    }

    public void changeDrawableState(Drawable drawable, boolean z) {
        for (PressAnimationInfo pressAnimationInfo : this.spaInfoList) {
            if (drawable == pressAnimationInfo.drawable) {
                SpringAnimation springAnimation = pressAnimationInfo.springAnimationX;
                if (z) {
                    springAnimation.getSpring().setFinalPosition(95.0f);
                    springAnimation.start();
                } else {
                    springAnimation.getSpring().setFinalPosition(SPRING_ANIMATION_FLOAT_TO_INT_PROP);
                    springAnimation.start();
                }
            }
        }
    }

    private static Interpolator getAlphaInterpolator() {
        return new PathInterpolator(0.0f, 0.33f, 0.67f, 1.0f);
    }

    public void setPressedAlphaEnabled(boolean z) {
        this.mPressedAlphaEnabled = z;
    }

    public void setPressedAnimationEnabled(boolean z) {
        this.mPressedAnimationEnabled = z;
    }

    public void setPressed(boolean z) {
        if (z) {
            handleDown(this.mTargetView);
        } else {
            handleUp(this.mTargetView);
        }
    }
}
