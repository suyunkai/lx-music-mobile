package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class DialogProtocolBinding implements ViewBinding {
    public final ImageView btnBack;
    private final FrameLayout rootView;
    public final TextView tvContent;
    public final TextView tvTitle;

    private DialogProtocolBinding(FrameLayout rootView, ImageView btnBack, TextView tvContent, TextView tvTitle) {
        this.rootView = rootView;
        this.btnBack = btnBack;
        this.tvContent = tvContent;
        this.tvTitle = tvTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static DialogProtocolBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogProtocolBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.dialog_protocol, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static DialogProtocolBinding bind(View rootView) {
        int i = R.id.btn_back;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.btn_back);
        if (imageView != null) {
            i = R.id.tv_content;
            TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_content);
            if (textView != null) {
                i = R.id.tv_title;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(rootView, R.id.tv_title);
                if (textView2 != null) {
                    return new DialogProtocolBinding((FrameLayout) rootView, imageView, textView, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
