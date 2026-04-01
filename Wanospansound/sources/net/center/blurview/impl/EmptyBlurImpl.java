package net.center.blurview.impl;

import android.content.Context;
import android.graphics.Bitmap;

/* JADX INFO: loaded from: classes3.dex */
public class EmptyBlurImpl implements BlurImpl {
    @Override // net.center.blurview.impl.BlurImpl
    public void blur(Bitmap bitmap, Bitmap bitmap2) {
    }

    @Override // net.center.blurview.impl.BlurImpl
    public boolean prepare(Context context, Bitmap bitmap, float f) {
        return false;
    }

    @Override // net.center.blurview.impl.BlurImpl
    public void release() {
    }
}
