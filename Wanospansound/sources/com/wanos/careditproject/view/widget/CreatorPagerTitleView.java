package com.wanos.careditproject.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.wanos.careditproject.databinding.ViewCreatorTabBinding;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;

/* JADX INFO: loaded from: classes3.dex */
public class CreatorPagerTitleView extends ConstraintLayout implements IPagerTitleView {
    private ViewCreatorTabBinding binding;
    private int mNormalColor;
    private int mSelectedColor;

    @Override // net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
    public void onEnter(int i, int i2, float f, boolean z) {
    }

    @Override // net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
    public void onLeave(int i, int i2, float f, boolean z) {
    }

    public CreatorPagerTitleView(Context context) {
        super(context);
        init();
    }

    public CreatorPagerTitleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public CreatorPagerTitleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public CreatorPagerTitleView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init();
    }

    private void init() {
        this.binding = ViewCreatorTabBinding.inflate(LayoutInflater.from(getContext()), this, true);
    }

    @Override // net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
    public void onSelected(int i, int i2) {
        this.binding.indicator.setVisibility(0);
        this.binding.textView.setTextColor(this.mSelectedColor);
    }

    @Override // net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
    public void onDeselected(int i, int i2) {
        this.binding.indicator.setVisibility(4);
        this.binding.textView.setTextColor(this.mNormalColor);
    }

    public void setText(String str) {
        ViewCreatorTabBinding viewCreatorTabBinding = this.binding;
        if (viewCreatorTabBinding != null) {
            viewCreatorTabBinding.textView.setText(str);
        }
    }

    public void setNormalColor(int i) {
        this.mNormalColor = i;
    }

    public void setSelectedColor(int i) {
        this.mSelectedColor = i;
    }
}
