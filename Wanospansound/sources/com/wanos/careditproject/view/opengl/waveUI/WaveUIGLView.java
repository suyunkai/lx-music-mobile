package com.wanos.careditproject.view.opengl.waveUI;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/* JADX INFO: loaded from: classes3.dex */
public class WaveUIGLView extends GLSurfaceView {
    WaveUIGLRender waveUIGLRender;

    public WaveUIGLView(Context context) {
        super(context, null);
    }

    public WaveUIGLView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    public void initView(Context context) {
        WaveUIGLRender waveUIGLRender = new WaveUIGLRender(context.getAssets());
        this.waveUIGLRender = waveUIGLRender;
        setRenderer(waveUIGLRender);
    }
}
