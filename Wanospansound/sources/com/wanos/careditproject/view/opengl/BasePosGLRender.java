package com.wanos.careditproject.view.opengl;

import android.opengl.GLSurfaceView;

/* JADX INFO: loaded from: classes3.dex */
public interface BasePosGLRender extends GLSurfaceView.Renderer {
    boolean pointerDownJ(float f, float f2);

    void pointerMoveJ(float f, float f2);

    void setChannleNumJ(int i);

    void showLineJ(boolean z);
}
