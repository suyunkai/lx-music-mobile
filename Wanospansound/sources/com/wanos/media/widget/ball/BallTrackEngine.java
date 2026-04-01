package com.wanos.media.widget.ball;

/* JADX INFO: loaded from: classes3.dex */
public class BallTrackEngine {
    private static final String TAG = "BallTrackEngine";
    private float mDistanceX;
    private float mDistanceY;
    private final ITrackCallback mITrackCallback;
    private float mStartX;
    private float mStartY;
    private int mDuration = -1;
    private int mCurrentDuration = 0;

    public interface ITrackCallback {
        void onEnd();

        void onFrame(float f, float f2);
    }

    public BallTrackEngine(ITrackCallback iTrackCallback) {
        this.mITrackCallback = iTrackCallback;
    }

    public void setStop() {
        this.mDuration = -1;
    }

    public void setTrackInfo(float f, float f2, float f3, float f4, int i) {
        this.mCurrentDuration = 0;
        this.mDuration = i;
        this.mStartX = f;
        this.mStartY = f2;
        this.mDistanceX = f3;
        this.mDistanceY = f4;
    }

    public void onDrawFrame() {
        int i = this.mDuration;
        if (i < 0) {
            return;
        }
        int i2 = this.mCurrentDuration + 8;
        this.mCurrentDuration = i2;
        float f = i2 / i;
        float f2 = this.mDistanceX * f;
        float f3 = this.mDistanceY * f;
        if (f >= 1.0f) {
            this.mDuration = -1;
            this.mITrackCallback.onEnd();
        } else {
            this.mITrackCallback.onFrame(f2 + this.mStartX, f3 + this.mStartY);
        }
    }
}
