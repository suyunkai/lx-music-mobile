package com.wanos.media.ui.info;

import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes3.dex */
public class ZeroImmersionHelp implements Runnable {
    public static final int SPACE_IMMERSION = 10000;
    private final WeakReference<ZeroInfoActivity> mActivity;

    public ZeroImmersionHelp(ZeroInfoActivity zeroInfoActivity) {
        this.mActivity = new WeakReference<>(zeroInfoActivity);
    }

    @Override // java.lang.Runnable
    public void run() {
        ZeroInfoActivity zeroInfoActivity = this.mActivity.get();
        if (zeroInfoActivity == null || zeroInfoActivity.isFinishing() || zeroInfoActivity.isDestroyed()) {
            return;
        }
        zeroInfoActivity.setImmersionState();
    }
}
