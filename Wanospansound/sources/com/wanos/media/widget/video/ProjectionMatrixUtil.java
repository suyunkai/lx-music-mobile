package com.wanos.media.widget.video;

import android.opengl.GLES30;
import android.opengl.Matrix;

/* JADX INFO: loaded from: classes3.dex */
public class ProjectionMatrixUtil {
    private static final float[] mProjectionMatrix = {1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};

    public static void orthoM(int i, int i2, int i3, String str) {
        float f;
        float f2;
        int iGlGetUniformLocation = GLES30.glGetUniformLocation(i, str);
        if (i2 > i3) {
            f = i2;
            f2 = i3;
        } else {
            f = i3;
            f2 = i2;
        }
        float f3 = f / f2;
        if (i2 > i3) {
            Matrix.orthoM(mProjectionMatrix, 0, -f3, f3, -1.0f, 1.0f, -1.0f, 1.0f);
        } else {
            Matrix.orthoM(mProjectionMatrix, 0, -1.0f, 1.0f, -f3, f3, -1.0f, 1.0f);
        }
        GLES30.glUniformMatrix4fv(iGlGetUniformLocation, 1, false, mProjectionMatrix, 0);
    }
}
