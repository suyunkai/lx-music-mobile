package com.wanos.careditproject.view.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.wanos.careditproject.R;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class AiFlowView extends ViewGroup {
    private static final int MAX_LINE = 2;
    private static final String TAG = "wanos[AI]-AiFlowView";
    private OnItemClickListener itemClickListener;
    private final int itemHeight;
    private final ArrayList<TextView> mChildTextViews;
    private final int[] mLabelsItemStrokeColors;
    private final SparseIntArray mLineMergeHorizontal;
    private final int mTextColor;
    private int mViewWidth;
    private final float radius;
    private final int space;
    private final int strokeWidth;
    private final List<String> tableList;

    public interface OnItemClickListener {
        void onItemClick(String str);
    }

    public AiFlowView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.tableList = new ArrayList();
        this.mChildTextViews = new ArrayList<>();
        this.mViewWidth = 0;
        this.mLineMergeHorizontal = new SparseIntArray();
        this.space = getResources().getDimensionPixelOffset(R.dimen.ai_flow_space);
        this.mLabelsItemStrokeColors = new int[]{Color.parseColor("#9746FF"), Color.parseColor("#0B99FF")};
        this.strokeWidth = getResources().getDimensionPixelOffset(R.dimen.ai_labels_item_stroke);
        this.radius = getResources().getDimension(R.dimen.ai_labels_item_height) / 2.0f;
        this.mTextColor = Color.parseColor("#9BB7E7");
        this.itemHeight = getResources().getDimensionPixelOffset(R.dimen.ai_labels_item_height);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        removeAllViews();
        int i5 = this.mLineMergeHorizontal.get(0, 0);
        int i6 = i5;
        int i7 = 0;
        int i8 = 0;
        for (int i9 = 0; i9 < this.mChildTextViews.size(); i9++) {
            TextView textView = this.mChildTextViews.get(i9);
            int measuredHeight = textView.getMeasuredHeight();
            int measuredWidth = textView.getMeasuredWidth();
            if ((this.mViewWidth - i5) - i6 < measuredWidth + (this.space / 2.0f)) {
                i7++;
                i5 = this.mLineMergeHorizontal.get(i7, 0);
                i8 += this.space + measuredHeight;
                i6 = i5;
            }
            int i10 = i5 + (this.space / 2);
            textView.layout(i10, i8, i10 + measuredWidth, measuredHeight + i8);
            i5 = i10 + measuredWidth + (this.space / 2);
            addView(textView);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.mChildTextViews.clear();
        this.mLineMergeHorizontal.clear();
        this.mViewWidth = View.MeasureSpec.getSize(i);
        int i3 = this.itemHeight;
        int i4 = 1;
        int i5 = 0;
        for (int i6 = 0; i6 < this.tableList.size(); i6++) {
            TextView childView = getChildView(i6, this.tableList.get(i6));
            childView.measure(0, 0);
            int measuredWidth = childView.getMeasuredWidth();
            int i7 = this.mViewWidth - i5;
            int i8 = this.space;
            if (i7 < measuredWidth + i8) {
                i4++;
                if (i4 > 2) {
                    break;
                }
                i3 += this.itemHeight + i8;
                i5 = 0;
            }
            i5 += measuredWidth + i8;
            this.mChildTextViews.add(childView);
            this.mLineMergeHorizontal.put(i4 - 1, (this.mViewWidth - i5) / 2);
        }
        Log.d(TAG, "onMeasure: mViewHeight = " + i3);
        setMeasuredDimension(this.mViewWidth, i3);
    }

    public void setTableList(List<String> list) {
        Log.d(TAG, "setTableList: " + list.size());
        if (list.isEmpty()) {
            return;
        }
        this.tableList.clear();
        this.tableList.addAll(list);
        requestLayout();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.itemClickListener = onItemClickListener;
    }

    private TextView getChildView(int i, String str) {
        TextView textView = new TextView(getContext());
        textView.setTextColor(this.mTextColor);
        textView.setTextSize(32.0f);
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.ai_flow_item_padding);
        textView.setPadding(dimensionPixelOffset, 0, dimensionPixelOffset, 0);
        textView.setHeight(this.itemHeight);
        textView.setBackground(new StrokeGradientDrawable(this.mLabelsItemStrokeColors, this.radius, this.strokeWidth));
        textView.setId(i);
        textView.setText(str);
        textView.setGravity(17);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.careditproject.view.widget.AiFlowView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m434x52a8c62d(view);
            }
        });
        return textView;
    }

    /* JADX INFO: renamed from: lambda$getChildView$0$com-wanos-careditproject-view-widget-AiFlowView, reason: not valid java name */
    /* synthetic */ void m434x52a8c62d(View view) {
        OnItemClickListener onItemClickListener = this.itemClickListener;
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(this.tableList.get(view.getId()));
        }
    }
}
