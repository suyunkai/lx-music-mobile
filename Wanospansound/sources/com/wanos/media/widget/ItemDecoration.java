package com.wanos.media.widget;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes3.dex */
public class ItemDecoration extends RecyclerView.ItemDecoration {
    private final int mSpaceSize;

    public ItemDecoration(float f) {
        this.mSpaceSize = (int) f;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
            int i = childAdapterPosition % spanCount;
            int iCeil = (int) Math.ceil(((double) recyclerView.getAdapter().getItemCount()) / ((double) spanCount));
            if (iCeil <= 0) {
                iCeil = 1;
            }
            int i2 = childAdapterPosition / spanCount;
            rect.left = (this.mSpaceSize * i) / spanCount;
            int i3 = this.mSpaceSize;
            rect.right = i3 - (((i + 1) * i3) / spanCount);
            rect.top = (this.mSpaceSize * i2) / iCeil;
            int i4 = this.mSpaceSize;
            rect.bottom = i4 - (((i2 + 1) * i4) / iCeil);
            return;
        }
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            int childAdapterPosition2 = recyclerView.getChildAdapterPosition(view);
            if (linearLayoutManager.getOrientation() == 0) {
                if (childAdapterPosition2 != 0) {
                    rect.set(this.mSpaceSize, 0, 0, 0);
                }
            } else {
                if (1 != linearLayoutManager.getOrientation() || childAdapterPosition2 == 0) {
                    return;
                }
                rect.set(0, this.mSpaceSize, 0, 0);
            }
        }
    }
}
