package com.wanos.media.zero_p.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.wanos.media.widget.CustomDialogTitle;
import com.wanos.media.widget.WanosTextView;
import com.wanos.media.zero_p.R;
import net.lucode.hackware.magicindicator.MagicIndicator;

/* JADX INFO: loaded from: classes3.dex */
public final class DialogHelpBinding implements ViewBinding {
    public final MagicIndicator indicator;
    public final WanosTextView ivDialogBack;
    private final LinearLayoutCompat rootView;
    public final CustomDialogTitle title;
    public final ViewPager2 viewPager;

    private DialogHelpBinding(LinearLayoutCompat linearLayoutCompat, MagicIndicator magicIndicator, WanosTextView wanosTextView, CustomDialogTitle customDialogTitle, ViewPager2 viewPager2) {
        this.rootView = linearLayoutCompat;
        this.indicator = magicIndicator;
        this.ivDialogBack = wanosTextView;
        this.title = customDialogTitle;
        this.viewPager = viewPager2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayoutCompat getRoot() {
        return this.rootView;
    }

    public static DialogHelpBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogHelpBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.dialog_help, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogHelpBinding bind(View view) {
        int i = R.id.indicator;
        MagicIndicator magicIndicator = (MagicIndicator) ViewBindings.findChildViewById(view, i);
        if (magicIndicator != null) {
            i = R.id.iv_dialog_back;
            WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(view, i);
            if (wanosTextView != null) {
                i = R.id.title;
                CustomDialogTitle customDialogTitle = (CustomDialogTitle) ViewBindings.findChildViewById(view, i);
                if (customDialogTitle != null) {
                    i = R.id.view_pager;
                    ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, i);
                    if (viewPager2 != null) {
                        return new DialogHelpBinding((LinearLayoutCompat) view, magicIndicator, wanosTextView, customDialogTitle, viewPager2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
