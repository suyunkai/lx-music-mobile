package com.wanos.media.zero_p.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.widget.WanosTextView;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public final class LayoutSpaceClockItemBinding implements ViewBinding {
    public final AppCompatImageView imCheckBtn;
    public final AppCompatImageView imSpaceBg;
    public final View imSpaceConverBg;
    public final AppCompatImageView ivDownloadFlag;
    public final ProgressBar loading;
    private final CardView rootView;
    public final WanosTextView tvSpaceName;

    private LayoutSpaceClockItemBinding(CardView cardView, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, View view, AppCompatImageView appCompatImageView3, ProgressBar progressBar, WanosTextView wanosTextView) {
        this.rootView = cardView;
        this.imCheckBtn = appCompatImageView;
        this.imSpaceBg = appCompatImageView2;
        this.imSpaceConverBg = view;
        this.ivDownloadFlag = appCompatImageView3;
        this.loading = progressBar;
        this.tvSpaceName = wanosTextView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CardView getRoot() {
        return this.rootView;
    }

    public static LayoutSpaceClockItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutSpaceClockItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.layout_space_clock_item, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutSpaceClockItemBinding bind(View view) {
        View viewFindChildViewById;
        int i = R.id.im_check_btn;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = R.id.im_space_bg;
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
            if (appCompatImageView2 != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i = R.id.im_space_conver_bg))) != null) {
                i = R.id.iv_download_flag;
                AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
                if (appCompatImageView3 != null) {
                    i = R.id.loading;
                    ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, i);
                    if (progressBar != null) {
                        i = R.id.tv_space_name;
                        WanosTextView wanosTextView = (WanosTextView) ViewBindings.findChildViewById(view, i);
                        if (wanosTextView != null) {
                            return new LayoutSpaceClockItemBinding((CardView) view, appCompatImageView, appCompatImageView2, viewFindChildViewById, appCompatImageView3, progressBar, wanosTextView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
