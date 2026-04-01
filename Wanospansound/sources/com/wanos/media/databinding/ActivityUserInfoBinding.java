package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ActivityUserInfoBinding implements ViewBinding {
    public final View hintOrder;
    public final View hintPer;
    public final View hintQa;
    public final FrameLayout layoutRightBottom;
    private final LinearLayout rootView;
    public final TextView tvChannel;
    public final TextView tvOrderInfo;
    public final TextView tvPersonInfo;
    public final TextView tvQaList;
    public final TextView tvVersion;
    public final TextView tvVersion2;

    private ActivityUserInfoBinding(LinearLayout rootView, View hintOrder, View hintPer, View hintQa, FrameLayout layoutRightBottom, TextView tvChannel, TextView tvOrderInfo, TextView tvPersonInfo, TextView tvQaList, TextView tvVersion, TextView tvVersion2) {
        this.rootView = rootView;
        this.hintOrder = hintOrder;
        this.hintPer = hintPer;
        this.hintQa = hintQa;
        this.layoutRightBottom = layoutRightBottom;
        this.tvChannel = tvChannel;
        this.tvOrderInfo = tvOrderInfo;
        this.tvPersonInfo = tvPersonInfo;
        this.tvQaList = tvQaList;
        this.tvVersion = tvVersion;
        this.tvVersion2 = tvVersion2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityUserInfoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityUserInfoBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_user_info, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityUserInfoBinding bind(View rootView) {
        int i = R.id.hint_order;
        View viewFindChildViewById = ViewBindings.findChildViewById(rootView, R.id.hint_order);
        if (viewFindChildViewById != null) {
            i = R.id.hint_per;
            View viewFindChildViewById2 = ViewBindings.findChildViewById(rootView, R.id.hint_per);
            if (viewFindChildViewById2 != null) {
                i = R.id.hint_qa;
                View viewFindChildViewById3 = ViewBindings.findChildViewById(rootView, R.id.hint_qa);
                if (viewFindChildViewById3 != null) {
                    i = R.id.layout_right_bottom;
                    FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(rootView, R.id.layout_right_bottom);
                    if (frameLayout != null) {
                        i = R.id.tv_channel;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_channel);
                        if (textView != null) {
                            i = R.id.tv_order_info;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_order_info);
                            if (textView2 != null) {
                                i = R.id.tv_person_info;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_person_info);
                                if (textView3 != null) {
                                    i = R.id.tv_qa_list;
                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_qa_list);
                                    if (textView4 != null) {
                                        i = R.id.tv_version;
                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_version);
                                        if (textView5 != null) {
                                            i = R.id.tv_version_2;
                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_version_2);
                                            if (textView6 != null) {
                                                return new ActivityUserInfoBinding((LinearLayout) rootView, viewFindChildViewById, viewFindChildViewById2, viewFindChildViewById3, frameLayout, textView, textView2, textView3, textView4, textView5, textView6);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
