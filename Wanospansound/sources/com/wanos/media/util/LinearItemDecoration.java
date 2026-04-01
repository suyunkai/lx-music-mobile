package com.wanos.media.util;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes3.dex */
public class LinearItemDecoration extends RecyclerView.ItemDecoration {
    private final int mDividerSize;
    private int mOrientation;

    public LinearItemDecoration(int i, int i2) {
        this.mOrientation = i;
        this.mDividerSize = i2;
    }

    public LinearItemDecoration(int i) {
        this.mOrientation = 1;
        this.mDividerSize = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        if (recyclerView.getChildAdapterPosition(view) == 0) {
            rect.set(0, 0, 0, 0);
            return;
        }
        if (1 == this.mOrientation) {
            rect.set(0, this.mDividerSize, 0, 0);
        }
        if (this.mOrientation == 0) {
            rect.set(this.mDividerSize, 0, 0, 0);
        }
    }
}
