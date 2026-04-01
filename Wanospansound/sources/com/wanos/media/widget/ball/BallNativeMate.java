package com.wanos.media.widget.ball;

import android.content.res.AssetManager;

/* JADX INFO: loaded from: classes3.dex */
public class BallNativeMate {
    public native boolean deleteBall(int i);

    public native float[] findBallPosition(int i);

    public native boolean insertBall(int i, String str, float f, float f2, float f3, float f4, float f5, float f6, float f7);

    public native void onDestroyView();

    public native void onDrawFrame();

    public native void onSurfaceChanged(int i, int i2, int i3, int i4);

    public native void onSurfaceCreated(AssetManager assetManager);

    public native int onViewTouchDown(float f, float f2);

    public native void setBackgroundImage(String str);

    public native void setBallPositionXY(int i, float f, float f2);

    public native void setBallPositionZ(int i, float f);

    public native void setDefaultDrawable(String str, String str2, String str3);

    public native void setPositionFlagState(boolean z);
}
