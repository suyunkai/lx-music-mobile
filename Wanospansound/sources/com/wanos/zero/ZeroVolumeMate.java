package com.wanos.zero;

import com.wanos.util.Constant;

/* JADX INFO: loaded from: classes3.dex */
class ZeroVolumeMate {
    private static final String TAG = "wanos[Zero]-ZeroVolumeMate";
    private final float SPACE = 1.953125E-4f;
    private float mCurrentLeftProgress = 0.0f;
    private float mCurrentRightProgress = 0.0f;
    private float mTargetVolume;

    ZeroVolumeMate() {
    }

    void setVolume(float f) {
        this.mTargetVolume = Constant.getVolumeConfig().getVolume((int) f);
    }

    float getLeftTestValue() {
        return this.mCurrentLeftProgress;
    }

    float getVolume() {
        return this.mTargetVolume;
    }

    float getFadeOutLeftVolume() {
        float f = this.mCurrentLeftProgress;
        if (f < 0.0f) {
            this.mCurrentLeftProgress = 0.0f;
        } else {
            this.mCurrentLeftProgress = f - 1.953125E-4f;
        }
        if (this.mCurrentLeftProgress < 0.0f) {
            this.mCurrentLeftProgress = 0.0f;
        }
        return this.mCurrentLeftProgress;
    }

    float getFadeOutRightVolume() {
        float f = this.mCurrentRightProgress;
        if (f < 0.0f) {
            this.mCurrentRightProgress = 0.0f;
        } else {
            this.mCurrentRightProgress = f - 1.953125E-4f;
        }
        if (this.mCurrentRightProgress < 0.0f) {
            this.mCurrentRightProgress = 0.0f;
        }
        return this.mCurrentRightProgress;
    }

    float getFadeInLeftVolume() {
        float f = this.mCurrentLeftProgress;
        if (f > 1.0f) {
            this.mCurrentLeftProgress = 1.0f;
        } else {
            this.mCurrentLeftProgress = f + 1.953125E-4f;
        }
        if (this.mCurrentLeftProgress > 1.0f) {
            this.mCurrentLeftProgress = 1.0f;
        }
        return this.mCurrentLeftProgress;
    }

    float getFadeInRightVolume() {
        float f = this.mCurrentRightProgress;
        if (f > 1.0f) {
            this.mCurrentRightProgress = 1.0f;
        } else {
            this.mCurrentRightProgress = f + 1.953125E-4f;
        }
        if (this.mCurrentRightProgress > 1.0f) {
            this.mCurrentRightProgress = 1.0f;
        }
        return this.mCurrentRightProgress;
    }

    boolean isFadeOutEnd() {
        return this.mCurrentRightProgress == 0.0f && this.mCurrentLeftProgress == 0.0f;
    }

    boolean isFadeInEnd() {
        return this.mCurrentRightProgress == 1.0f && this.mCurrentLeftProgress == 1.0f;
    }

    void resetFadeInState() {
        this.mCurrentLeftProgress = 0.0f;
        this.mCurrentRightProgress = 0.0f;
    }
}
