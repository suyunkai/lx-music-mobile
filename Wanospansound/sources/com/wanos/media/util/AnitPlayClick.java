package com.wanos.media.util;

import android.view.View;
import com.wanos.commonlibrary.utils.CommonUtils;
import java.lang.ref.SoftReference;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AnitPlayClick implements View.OnClickListener {
    private long mInterval;
    private long mLastClickTime;
    private SoftReference<View> sf;

    public abstract void onAnitClick(View view);

    public AnitPlayClick() {
        this.mLastClickTime = 0L;
        this.mInterval = 200L;
    }

    public AnitPlayClick(long j) {
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
        } else if (CommonUtils.isIs371()) {
            this.mLastClickTime = jCurrentTimeMillis;
        }
    }
}
