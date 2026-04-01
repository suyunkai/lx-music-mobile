package com.wanos.media.ui.widget;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes3.dex */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    int mSpace;

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = 0;
            outRect.bottom = 0;
        } else {
            outRect.top = this.mSpace;
            outRect.bottom = 0;
        }
    }

    public SpaceItemDecoration(int space) {
        this.mSpace = space;
    }
}
