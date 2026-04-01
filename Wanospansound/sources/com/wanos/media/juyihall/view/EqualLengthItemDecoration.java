package com.wanos.media.juyihall.view;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes3.dex */
public class EqualLengthItemDecoration extends RecyclerView.ItemDecoration {
    private int space = 0;

    public void setItemSpace(int i) {
        this.space = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        if ((recyclerView.getLayoutManager() instanceof LinearLayoutManager) && ((LinearLayoutManager) recyclerView.getLayoutManager()).getOrientation() == 0 && recyclerView.getChildAdapterPosition(view) != 0) {
            rect.left = this.space;
        }
    }
}
