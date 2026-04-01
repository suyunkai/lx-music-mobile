package com.wanos.media.widget;

import android.content.Context;
import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.Utils;
import com.wanos.media.zero_p.R;
import net.lucode.hackware.magicindicator.buildins.ArgbEvaluatorHolder;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;

/* JADX INFO: loaded from: classes3.dex */
public class TableTextView extends com.wanos.media.ui.widget.WanosTextView implements IPagerTitleView {
    private final int mNormalTextColor;
    private final int mNormalTextSize;
    private final int mSelectTextColor;
    private final int mSelectTextSize;

    public TableTextView(Context context) {
        this(context, 48, ColorUtils.getColor(R.color.zero_me_table_default), ColorUtils.getColor(R.color.zero_me_table_select));
    }

    public TableTextView(Context context, int i, int i2, int i3) {
        super(context);
        this.mSelectTextSize = i;
        this.mNormalTextSize = i;
        this.mNormalTextColor = i2;
        this.mSelectTextColor = i3;
        setTextWeight(500);
        setPadding(40, 40);
    }

    @Override // net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
    public void onSelected(int i, int i2) {
        setTextSize(this.mSelectTextSize);
    }

    @Override // net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
    public void onDeselected(int i, int i2) {
        setTextSize(this.mNormalTextSize);
    }

    @Override // net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
    public void onLeave(int i, int i2, float f, boolean z) {
        setTextColor(ArgbEvaluatorHolder.eval(f, this.mSelectTextColor, this.mNormalTextColor));
    }

    @Override // net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
    public void onEnter(int i, int i2, float f, boolean z) {
        setTextColor(ArgbEvaluatorHolder.eval(f, this.mNormalTextColor, this.mSelectTextColor));
    }

    public void setPadding(int i, int i2) {
        setPadding(UIUtil.dip2px(Utils.getApp(), i), 0, UIUtil.dip2px(Utils.getApp(), i2), 0);
    }
}
