package com.wanos.careditproject.utils;

import android.os.Handler;
import android.view.View;

/* JADX INFO: loaded from: classes3.dex */
public class DebounceClick implements View.OnClickListener {
    private final long mDelayMillis;
    private boolean mIsClicked = false;
    private final View.OnClickListener mListener;

    public DebounceClick(View.OnClickListener onClickListener, long j) {
        this.mListener = onClickListener;
        this.mDelayMillis = j;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.mIsClicked) {
            return;
        }
        this.mIsClicked = true;
        this.mListener.onClick(view);
        new Handler().postDelayed(new Runnable() { // from class: com.wanos.careditproject.utils.DebounceClick.1
            @Override // java.lang.Runnable
            public void run() {
                DebounceClick.this.mIsClicked = false;
            }
        }, this.mDelayMillis);
    }
}
