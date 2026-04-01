package com.wanos.media.ui.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public class BottomItemDecoration extends RecyclerView.ItemDecoration {
    private static final String TAG = "BottomItemDecoration";
    private int mItemHeight = -1;
    private final int mPlayBarHeight;

    public BottomItemDecoration(Context context) {
        this.mPlayBarHeight = (int) (context.getResources().getDimension(R.dimen.playbar_h) + TypedValue.applyDimension(1, 48.0f, context.getResources().getDisplayMetrics()));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        RecyclerView.Adapter adapter = parent.getAdapter();
        if (adapter == null || !(layoutManager instanceof GridLayoutManager)) {
            return;
        }
        int childAdapterPosition = parent.getChildAdapterPosition(view);
        if (isLastLine(((GridLayoutManager) layoutManager).getSpanCount(), childAdapterPosition, adapter.getItemCount())) {
            int iCeil = (int) Math.ceil((childAdapterPosition + 1.0f) / r6);
            int height = parent.getHeight() - this.mPlayBarHeight;
            if (this.mItemHeight == -1) {
                view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
                this.mItemHeight = view.getMeasuredHeight();
            }
            if (iCeil * this.mItemHeight > height) {
                outRect.bottom = this.mPlayBarHeight - view.getPaddingBottom();
            }
        }
    }

    private boolean isLastLine(int spanCount, int position, int itemCode) {
        return position == itemCode + (-1) || position >= itemCode - (itemCode % spanCount);
    }
}
