package com.wanos.media.widget.video;

import android.content.Context;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import java.nio.Buffer;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseGLSurfaceViewRenderer implements GLSurfaceView.Renderer {
    protected int mProgram;

    protected void handleProgram(Context context, int i, int i2) {
        int iLinkProgram = ShaderUtils.linkProgram(ShaderUtils.compileVertexShader(ResReadUtils.readResource(context, i)), ShaderUtils.compileFragmentShader(ResReadUtils.readResource(context, i2)));
        this.mProgram = iLinkProgram;
        GLES30.glUseProgram(iLinkProgram);
    }

    protected void glViewport(int i, int i2, int i3, int i4) {
        GLES30.glViewport(i, i2, i3, i4);
    }

    protected void glClearColor(float f, float f2, float f3, float f4) {
        GLES30.glClearColor(f, f2, f3, f4);
    }

    protected void glClear(int i) {
        GLES30.glClear(i);
    }

    protected static void glEnableVertexAttribArray(int i) {
        GLES30.glEnableVertexAttribArray(i);
    }

    protected void glDisableVertexAttribArray(int i) {
        GLES30.glDisableVertexAttribArray(i);
    }

    protected int glGetAttribLocation(String str) {
        return GLES30.glGetAttribLocation(this.mProgram, str);
    }

    protected int glGetUniformLocation(String str) {
        return GLES30.glGetUniformLocation(this.mProgram, str);
    }

    protected void glUniformMatrix4fv(int i, int i2, boolean z, float[] fArr, int i3) {
        GLES30.glUniformMatrix4fv(i, i2, z, fArr, i3);
    }

    protected void glDrawArrays(int i, int i2, int i3) {
        GLES30.glDrawArrays(i, i2, i3);
    }

    protected void glDrawElements(int i, int i2, int i3, int i4) {
        GLES30.glDrawElements(i, i2, i3, i4);
    }

    protected void orthoM(String str, int i, int i2) {
        ProjectionMatrixUtil.orthoM(this.mProgram, i, i2, str);
    }

    protected void glVertexAttribPointer(int i, int i2, int i3, boolean z, int i4, Buffer buffer) {
        GLES30.glVertexAttribPointer(i, i2, i3, z, i4, buffer);
    }
}
