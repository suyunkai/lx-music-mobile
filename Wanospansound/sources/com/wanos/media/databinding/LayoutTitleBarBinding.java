package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;
import com.wanos.media.util.SearchEditText;

/* JADX INFO: loaded from: classes3.dex */
public final class LayoutTitleBarBinding implements ViewBinding {
    public final LinearLayoutCompat btnAddSoundByShareCode;
    public final TextView btnSearch;
    public final LinearLayoutCompat llZeroTitleBar;
    private final LinearLayout rootView;
    public final SearchEditText searchEdit;
    public final ImageView titleLeftBtn;
    public final TextView tvOrderInfo;
    public final TextView tvPersonInfo;
    public final AppCompatTextView tvShareCodeAdd;
    public final TextView tvTitle;

    private LayoutTitleBarBinding(LinearLayout linearLayout, LinearLayoutCompat linearLayoutCompat, TextView textView, LinearLayoutCompat linearLayoutCompat2, SearchEditText searchEditText, ImageView imageView, TextView textView2, TextView textView3, AppCompatTextView appCompatTextView, TextView textView4) {
        this.rootView = linearLayout;
        this.btnAddSoundByShareCode = linearLayoutCompat;
        this.btnSearch = textView;
        this.llZeroTitleBar = linearLayoutCompat2;
        this.searchEdit = searchEditText;
        this.titleLeftBtn = imageView;
        this.tvOrderInfo = textView2;
        this.tvPersonInfo = textView3;
        this.tvShareCodeAdd = appCompatTextView;
        this.tvTitle = textView4;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static LayoutTitleBarBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutTitleBarBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.layout_title_bar, viewGroup, false);
        if (z) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static LayoutTitleBarBinding bind(View view) {
        int i = R.id.btn_add_sound_by_share_code;
        LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, i);
        if (linearLayoutCompat != null) {
            i = R.id.btn_search;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView != null) {
                i = R.id.ll_zero_title_bar;
                LinearLayoutCompat linearLayoutCompat2 = (LinearLayoutCompat) ViewBindings.findChildViewById(view, i);
                if (linearLayoutCompat2 != null) {
                    i = R.id.search_edit;
                    SearchEditText searchEditText = (SearchEditText) ViewBindings.findChildViewById(view, i);
                    if (searchEditText != null) {
                        i = R.id.title_left_btn;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                        if (imageView != null) {
                            i = R.id.tv_order_info;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView2 != null) {
                                i = R.id.tv_person_info;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                                if (textView3 != null) {
                                    i = R.id.tv_share_code_add;
                                    AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
                                    if (appCompatTextView != null) {
                                        i = R.id.tv_title;
                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                                        if (textView4 != null) {
                                            return new LayoutTitleBarBinding((LinearLayout) view, linearLayoutCompat, textView, linearLayoutCompat2, searchEditText, imageView, textView2, textView3, appCompatTextView, textView4);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
