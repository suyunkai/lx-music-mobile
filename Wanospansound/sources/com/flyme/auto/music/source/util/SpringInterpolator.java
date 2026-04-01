package com.flyme.auto.music.source.util;

import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes2.dex */
public class SpringInterpolator implements Interpolator {
    private float factor;

    public SpringInterpolator(float f) {
        this.factor = f;
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f) {
        if (Float.compare(f, 0.0f) == 0 || Float.compare(f, 1.0f) == 0) {
            return f;
        }
        double dPow = Math.pow(2.0d, (-10.0f) * f);
        double d2 = f;
        float f2 = this.factor;
        return (float) ((dPow * Math.sin(((d2 - (((double) f2) / 4.0d)) * 6.283185307179586d) / ((double) f2))) + 1.0d);
    }
}
