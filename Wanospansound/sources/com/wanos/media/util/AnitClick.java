package com.wanos.media.util;

import android.content.Context;
import android.view.View;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AnitClick implements View.OnClickListener {
    private static WeakReference<View> itemClickView;
    private static long mOldClickTime;
    private Context context;
    private long mInterval;
    private long mLastClickTime;
    private SoftReference<View> sf;

    public abstract void onAnitClick(View view);

    public AnitClick() {
        this.mLastClickTime = 0L;
        this.mInterval = 200L;
    }

    public AnitClick(Context context) {
        this.mLastClickTime = 0L;
        this.mInterval = 200L;
        this.context = context;
    }

    public AnitClick(long j) {
        this.mLastClickTime = 0L;
        this.mInterval = j;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        SoftReference<View> softReference;
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.mLastClickTime > this.mInterval || (softReference = this.sf) == null || view != softReference.get()) {
            this.mLastClickTime = jCurrentTimeMillis;
            this.sf = new SoftReference<>(view);
            onAnitClick(view);
        }
    }

    public Context getContext() {
        return this.context;
    }

    public static boolean isRepeatClick(long j) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - mOldClickTime <= j) {
            return true;
        }
        mOldClickTime = jCurrentTimeMillis;
        return false;
    }

    public static boolean isRepeatClick(View view) {
        WeakReference<View> weakReference = itemClickView;
        if (weakReference == null || weakReference.get() == null || itemClickView.get() != view) {
            itemClickView = new WeakReference<>(view);
            return false;
        }
        return isRepeatClick(700L);
    }

    public static boolean isRepeatClick(View view, long j) {
        WeakReference<View> weakReference = itemClickView;
        if (weakReference == null || weakReference.get() == null || itemClickView.get() != view) {
            itemClickView = new WeakReference<>(view);
            return false;
        }
        return isRepeatClick(j);
    }
}
