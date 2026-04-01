package com.wanos.careditproject.view.opengl;

import android.content.res.AssetManager;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: loaded from: classes3.dex */
public class PosRightGLRender implements BasePosGLRender {
    AssetManager assetManager;

    public static native void init(int i, int i2);

    public static native void setBasicPath(String str);

    public native void destroy();

    public native void ndkDrawFrame();

    public native void ndkSurfaceChanged(int i, int i2);

    public native void ndkSurfaceCreated(AssetManager assetManager);

    public native boolean pointerDown(float f, float f2);

    public native void pointerMove(float f, float f2);

    @Override // com.wanos.careditproject.view.opengl.BasePosGLRender
    public void setChannleNumJ(int i) {
    }

    public native void setLineVisible(boolean z);

    public PosRightGLRender(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        ndkSurfaceCreated(this.assetManager);
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        ndkSurfaceChanged(i, i2);
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl10) {
        ndkDrawFrame();
    }

    @Override // com.wanos.careditproject.view.opengl.BasePosGLRender
    public boolean pointerDownJ(float f, float f2) {
        return pointerDown(f, f2);
    }

    @Override // com.wanos.careditproject.view.opengl.BasePosGLRender
    public void pointerMoveJ(float f, float f2) {
        pointerMove(f, f2);
    }

    @Override // com.wanos.careditproject.view.opengl.BasePosGLRender
    public void showLineJ(boolean z) {
        setLineVisible(z);
    }
}
