package com.wanos.careditproject.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;

/* JADX INFO: loaded from: classes3.dex */
public class PlayerPageSeekBar extends SeekBar {
    private boolean isAllowedSeek;

    public PlayerPageSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isAllowedSeek = false;
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.isAllowedSeek) {
            return super.dispatchTouchEvent(motionEvent);
        }
        return false;
    }

    public void setAllowedSeek(boolean z) {
        this.isAllowedSeek = z;
    }
}
