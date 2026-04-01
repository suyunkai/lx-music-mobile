package com.wanos.careditproject.view.playerUI;

import android.content.res.AssetManager;
import android.opengl.GLSurfaceView;
import com.wanos.careditproject.model.web.WebBallInfoModel;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: loaded from: classes3.dex */
public class Player3dGLRender implements GLSurfaceView.Renderer {
    AssetManager assetManager;
    IGlCreatedCallback callback;

    public interface IGlCreatedCallback {
        void onCallback();
    }

    public static native void setBasicPath(String str);

    public static native void setPosNameVisible(boolean z);

    public native void destory();

    public native void ndkDrawFrame();

    public native void ndkSurfaceChanged(int i, int i2);

    public native void ndkSurfaceCreated(AssetManager assetManager, IGlCreatedCallback iGlCreatedCallback);

    public native void pointerDown(int i, int i2);

    public native void pointerMove(int i, int i2);

    public native void pointerScale(float f);

    public native void pointerUp(int i, int i2);

    public native boolean setPlayerPos(String str);

    public native boolean setPlayerPosV2(WebBallInfoModel[] webBallInfoModelArr, int i);

    public native void setShowType(int i);

    public Player3dGLRender(AssetManager assetManager, IGlCreatedCallback iGlCreatedCallback) {
        this.assetManager = assetManager;
        this.callback = iGlCreatedCallback;
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        ndkSurfaceCreated(this.assetManager, this.callback);
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        ndkSurfaceChanged(i, i2);
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl10) {
        ndkDrawFrame();
    }
}
