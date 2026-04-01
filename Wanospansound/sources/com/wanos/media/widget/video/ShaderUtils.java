package com.wanos.media.widget.video;

import android.opengl.GLES30;

/* JADX INFO: loaded from: classes3.dex */
public class ShaderUtils {
    private static final String TAG = "ShaderUtils";

    public static int compileVertexShader(String str) {
        return compileShader(35633, str);
    }

    public static int compileFragmentShader(String str) {
        return compileShader(35632, str);
    }

    private static int compileShader(int i, String str) {
        int iGlCreateShader = GLES30.glCreateShader(i);
        if (iGlCreateShader == 0) {
            return 0;
        }
        GLES30.glShaderSource(iGlCreateShader, str);
        GLES30.glCompileShader(iGlCreateShader);
        int[] iArr = new int[1];
        GLES30.glGetShaderiv(iGlCreateShader, 35713, iArr, 0);
        if (iArr[0] != 0) {
            return iGlCreateShader;
        }
        System.err.println(GLES30.glGetShaderInfoLog(iGlCreateShader));
        GLES30.glDeleteShader(iGlCreateShader);
        return 0;
    }

    public static int linkProgram(int i, int i2) {
        int iGlCreateProgram = GLES30.glCreateProgram();
        if (iGlCreateProgram == 0) {
            return 0;
        }
        GLES30.glAttachShader(iGlCreateProgram, i);
        GLES30.glAttachShader(iGlCreateProgram, i2);
        GLES30.glLinkProgram(iGlCreateProgram);
        GLES30.glDeleteShader(i);
        GLES30.glDeleteShader(i2);
        int[] iArr = new int[1];
        GLES30.glGetProgramiv(iGlCreateProgram, 35714, iArr, 0);
        if (iArr[0] != 0) {
            return iGlCreateProgram;
        }
        System.err.println(GLES30.glGetProgramInfoLog(iGlCreateProgram));
        GLES30.glDeleteProgram(iGlCreateProgram);
        return 0;
    }

    public static boolean validProgram(int i) {
        GLES30.glValidateProgram(i);
        int[] iArr = new int[1];
        GLES30.glGetProgramiv(i, 35715, iArr, 0);
        return iArr[0] != 0;
    }
}
