package com.flyme.auto.music.source;

import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes2.dex */
public class PopRecyclerView extends RecyclerView {
    private PopupWindowFocusChanged onWindowFocusChanged;

    public interface PopupWindowFocusChanged {
        void onWindowFocusChanged(boolean z);
    }

    public PopRecyclerView(Context context) {
        this(context, null);
    }

    public PopRecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public PopRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        PopupWindowFocusChanged popupWindowFocusChanged = this.onWindowFocusChanged;
        if (popupWindowFocusChanged != null) {
            popupWindowFocusChanged.onWindowFocusChanged(z);
        }
    }

    public void addOnWindowFocusChanged(PopupWindowFocusChanged popupWindowFocusChanged) {
        this.onWindowFocusChanged = popupWindowFocusChanged;
    }
}
