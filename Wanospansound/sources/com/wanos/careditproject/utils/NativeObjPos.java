package com.wanos.careditproject.utils;

/* JADX INFO: loaded from: classes3.dex */
public class NativeObjPos {
    public static native float[] getDisplayPos(float f, float f2);

    static {
        EditingUtils.loadEditLibrariesOnce();
    }
}
