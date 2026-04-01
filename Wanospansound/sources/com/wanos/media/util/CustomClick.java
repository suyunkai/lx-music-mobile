package com.wanos.media.util;

import android.view.View;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes3.dex */
public abstract class CustomClick implements View.OnClickListener {
    private static final long mInterval = 500;
    private long mLastClickTime = 0;
    private WeakReference<View> mView;

    public abstract void onAnitClick(View view);

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        WeakReference<View> weakReference;
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.mLastClickTime > 500 || (weakReference = this.mView) == null || view != weakReference.get()) {
            this.mLastClickTime = jCurrentTimeMillis;
            this.mView = new WeakReference<>(view);
            onAnitClick(view);
        }
    }
}
