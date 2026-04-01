package com.wanos.media.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.content.res.ResourcesCompat;
import com.wanos.commonlibrary.utils.CommonUtils;
import com.wanos.media.R;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class FlowLayout extends ViewGroup {
    private int horizontalSpacing;
    private Line line;
    private int lineSize;
    private final List<Line> lines;
    private int textColor;
    private int textPaddingH;
    private int textPaddingV;
    private int textSize;
    private int verticalSpacing;

    public interface OnItemClickListener {
        void onItemClick(String content, boolean isClear);
    }

    @Override // android.view.View
    public void setBackgroundResource(int backgroundResource) {
    }

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.lines = new ArrayList();
        this.lineSize = 0;
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FlowLayoutAttrs, defStyleAttr, 0);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        this.horizontalSpacing = dp2px(this.horizontalSpacing);
        for (int i = 0; i < indexCount; i++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            switch (index) {
                case 1:
                    this.horizontalSpacing = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, dp2px(this.horizontalSpacing));
                    break;
                case 2:
                    this.textColor = typedArrayObtainStyledAttributes.getColor(index, this.textColor);
                    break;
                case 3:
                    this.textSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, sp2px(this.textSize));
                    break;
                case 4:
                    this.textPaddingH = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, dp2px(this.textPaddingH));
                    break;
                case 5:
                    this.textPaddingV = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, dp2px(this.textPaddingV));
                    break;
                case 6:
                    this.verticalSpacing = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, dp2px(this.verticalSpacing));
                    break;
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = (View.MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft()) - getPaddingRight();
        int size2 = (View.MeasureSpec.getSize(heightMeasureSpec) - getPaddingBottom()) - getPaddingTop();
        int mode = View.MeasureSpec.getMode(widthMeasureSpec);
        int mode2 = View.MeasureSpec.getMode(heightMeasureSpec);
        restoreLine();
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            childAt.measure(View.MeasureSpec.makeMeasureSpec(size, mode == 1073741824 ? Integer.MIN_VALUE : mode), View.MeasureSpec.makeMeasureSpec(size2, mode2 != 1073741824 ? mode2 : Integer.MIN_VALUE));
            if (this.line == null) {
                this.line = new Line();
            }
            int measuredWidth = this.lineSize + childAt.getMeasuredWidth();
            this.lineSize = measuredWidth;
            if (measuredWidth <= size) {
                this.line.addChild(childAt);
                this.lineSize += this.horizontalSpacing;
            } else {
                newLine();
                this.line.addChild(childAt);
                this.lineSize = this.lineSize + childAt.getMeasuredWidth() + this.horizontalSpacing;
            }
        }
        Line line = this.line;
        if (line != null && !this.lines.contains(line)) {
            this.lines.add(this.line);
        }
        int height = 0;
        for (int i2 = 0; i2 < this.lines.size(); i2++) {
            height += this.lines.get(i2).getHeight();
        }
        setMeasuredDimension(View.MeasureSpec.getSize(widthMeasureSpec), resolveSize(height + (this.verticalSpacing * (this.lines.size() - 1)) + getPaddingBottom() + getPaddingTop(), heightMeasureSpec));
    }

    private void restoreLine() {
        this.lines.clear();
        this.line = new Line();
        this.lineSize = 0;
    }

    private void newLine() {
        Line line = this.line;
        if (line != null) {
            this.lines.add(line);
        }
        this.line = new Line();
        this.lineSize = 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b2) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        for (int i = 0; i < this.lines.size(); i++) {
            Line line = this.lines.get(i);
            line.layout(paddingLeft, paddingTop);
            paddingTop = paddingTop + line.getHeight() + this.verticalSpacing;
        }
    }

    class Line {
        private final List<View> children = new ArrayList();
        int height;

        Line() {
        }

        public void addChild(View childView) {
            this.children.add(childView);
            if (this.height < childView.getMeasuredHeight()) {
                this.height = childView.getMeasuredHeight();
            }
        }

        public void layout(int left, int top) {
            FlowLayout.this.getMeasuredWidth();
            FlowLayout.this.getPaddingLeft();
            FlowLayout.this.getPaddingRight();
            for (int i = 0; i < this.children.size(); i++) {
                View view = this.children.get(i);
                view.layout(left, top, view.getMeasuredWidth() + left, view.getMeasuredHeight() + top);
                left = left + view.getMeasuredWidth() + FlowLayout.this.horizontalSpacing;
            }
        }

        public int getHeight() {
            return this.height;
        }

        public int getChildCount() {
            return this.children.size();
        }
    }

    public void setFlowLayout(boolean isClear, final List<String> beanlist, final OnItemClickListener onItemClickListener) {
        removeAllViews();
        if (beanlist == null || beanlist.size() <= 0) {
            return;
        }
        for (final int i = 0; i < beanlist.size(); i++) {
            final TextView textView = new TextView(getContext());
            textView.setVisibility(0);
            textView.setText(beanlist.get(i));
            textView.setMaxLines(1);
            textView.setMaxEms(12);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setTextSize(0, this.textSize);
            textView.setTextColor(ResourcesCompat.getColorStateList(getResources(), R.color.search_result_tab_text_selector, null));
            textView.setGravity(17);
            int i2 = this.textPaddingH;
            int i3 = this.textPaddingV;
            textView.setPadding(i2, i3, i2, i3);
            textView.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.search_result_tab_selector, null));
            textView.setClickable(true);
            if (isClear && i == beanlist.size() - 1) {
                Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_clear, null);
                textView.setTextColor(ResourcesCompat.getColorStateList(getResources(), R.color.search_edit_text_color, null));
                textView.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.search_result_clear_tab_normal, null));
                textView.setCompoundDrawablesWithIntrinsicBounds(drawable, textView.getCompoundDrawables()[1], textView.getCompoundDrawables()[2], textView.getCompoundDrawables()[3]);
                textView.setCompoundDrawablePadding(4);
                if (CommonUtils.isChannelNot245()) {
                    textView.setContentDescription(getResources().getString(R.string.clear_record));
                }
            }
            addView(textView, new ViewGroup.LayoutParams(-2, -2));
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.util.FlowLayout$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    onItemClickListener.onItemClick(textView.getText().toString(), i == beanlist.size() - 1);
                }
            });
        }
    }

    public void setHorizontalSpacing(int horizontalSpacing) {
        this.horizontalSpacing = dp2px(horizontalSpacing);
    }

    public void setVerticalSpacing(int verticalSpacing) {
        this.verticalSpacing = dp2px(verticalSpacing);
    }

    public void setTextSize(int textSize) {
        this.textSize = sp2px(textSize);
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public void setTextPaddingH(int textPaddingH) {
        this.textPaddingH = dp2px(textPaddingH);
    }

    public void setTextPaddingV(int textPaddingV) {
        this.textPaddingV = dp2px(textPaddingV);
    }

    private int dp2px(float dp) {
        return (int) TypedValue.applyDimension(1, dp, getResources().getDisplayMetrics());
    }

    private int sp2px(float sp) {
        return (int) TypedValue.applyDimension(2, sp, getResources().getDisplayMetrics());
    }
}
