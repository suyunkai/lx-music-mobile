package com.wanos.careditproject.view.opengl.waveUI;

import android.content.res.AssetManager;
import android.opengl.GLSurfaceView;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: loaded from: classes3.dex */
public class WaveUIGLRender implements GLSurfaceView.Renderer {
    private static WaveUIGLRender glRender = null;
    private static boolean isInit = false;
    AssetManager assetManager;

    protected static native void destroy();

    public static native long getCurSampleNum();

    public static native int getCurSelectTrackIndex();

    protected static native void init(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9);

    public static native void pcmAdd(String str, short s);

    public static native void pcmClear();

    public static native void pcmInit(int i);

    public static native void pcmUrlChange(String str, String str2);

    public static native void setBasicPath(String str);

    public static native void setBeatSpeed(int i);

    public static native void setBeatSwitch(boolean z);

    public static native void setCurSampleNum(long j);

    public native void changeTrackSM();

    public native void cmdUndoRedo();

    public native void delWaveId(String str);

    public native void deleteTrackIndex(int i);

    public native void drawBallViewById(String str);

    public native void drawClipViewById(String str);

    public native void drawTrackByIndex(int i);

    protected native void flushData();

    public native void loadEnd();

    public native void moveY(float f);

    public native void ndkDrawFrame();

    public native void ndkSurfaceChanged(int i, int i2);

    public native void ndkSurfaceCreated(AssetManager assetManager);

    public native void pointerDown(float f, float f2);

    public native int pointerMove(float f, float f2, boolean z);

    public native void pointerScale(float f, boolean z);

    public native int pointerUp(float f, float f2, boolean z, boolean z2);

    public native void selectTrack(int i);

    public native void showAllUI(long j);

    public WaveUIGLRender(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        glRender = this;
        ndkSurfaceCreated(this.assetManager);
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        ndkSurfaceChanged(i, i2);
    }

    public void flushWaveData() {
        if (isInit) {
            flushData();
        }
    }

    public static void initRender(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        init(i, i2, i3, i4, i5, i6, i7, i8, i9);
        isInit = true;
    }

    public static void destroyRender() {
        glRender = null;
        destroy();
        isInit = false;
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl10) {
        ndkDrawFrame();
    }

    public static void sDeleteTrackIndex(int i) {
        WaveUIGLRender waveUIGLRender = glRender;
        if (waveUIGLRender != null) {
            waveUIGLRender.deleteTrackIndex(i);
        }
    }

    public static void sSelectTrack(int i) {
        WaveUIGLRender waveUIGLRender = glRender;
        if (waveUIGLRender != null) {
            waveUIGLRender.selectTrack(i);
        }
    }

    public static void sChangeTrackSM() {
        WaveUIGLRender waveUIGLRender = glRender;
        if (waveUIGLRender != null) {
            waveUIGLRender.changeTrackSM();
        }
    }

    public static void sCmdUndoRedo() {
        WaveUIGLRender waveUIGLRender = glRender;
        if (waveUIGLRender != null) {
            waveUIGLRender.cmdUndoRedo();
        }
    }

    public static void sLoadEnd() {
        WaveUIGLRender waveUIGLRender = glRender;
        if (waveUIGLRender != null) {
            waveUIGLRender.loadEnd();
        }
    }
}
