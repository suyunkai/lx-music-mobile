package com.wanos.zero;

import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes3.dex */
public class ZeroStateMate {
    static final int BALL_FADE_IN_END = 103;
    static final int BALL_FADE_IN_ING = 102;
    static final int BALL_FADE_IN_WAIT = 101;
    static final int BALL_FADE_OUT_END = 203;
    static final int BALL_FADE_OUT_ING = 202;
    static final int BALL_FADE_OUT_WAIT = 201;
    static final int FADE_MAX_COUNT = 4;
    static final int FADE_SPEED = 1;
    private static final String TAG = "wanos[Zero]-ZeroStateMate";
    int mFadeOutCount = 0;
    private int mState = 101;
    private boolean isDeleteState = false;

    @Retention(RetentionPolicy.SOURCE)
    @interface BallState {
    }

    public void setState(int i) {
        if (this.isDeleteState && (i == 101 || i == 102 || i == 103)) {
            Log.e(TAG, "setState: 该音源为删除状态，无法设置除停止之外的其他状态。state = " + i);
        } else {
            this.mState = i;
        }
    }

    public int getState() {
        return this.mState;
    }

    public void setDeleteState() {
        this.isDeleteState = true;
    }

    public boolean isDeleteState() {
        return this.isDeleteState;
    }

    public boolean isCanDelete() {
        return this.isDeleteState && this.mState == 203;
    }

    int getCount() {
        return this.mFadeOutCount;
    }

    void addCount() {
        this.mFadeOutCount++;
    }

    void resetCount() {
        this.mFadeOutCount = 0;
    }
}
