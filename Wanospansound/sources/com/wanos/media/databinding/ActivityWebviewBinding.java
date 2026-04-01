package com.wanos.media.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ActivityWebviewBinding implements ViewBinding {
    public final ImageView ivBack;
    private final ConstraintLayout rootView;
    public final WebView webView;

    private ActivityWebviewBinding(ConstraintLayout rootView, ImageView ivBack, WebView webView) {
        this.rootView = rootView;
        this.ivBack = ivBack;
        this.webView = webView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityWebviewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityWebviewBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View viewInflate = inflater.inflate(R.layout.activity_webview, parent, false);
        if (attachToParent) {
            parent.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    public static ActivityWebviewBinding bind(View rootView) {
        int i = R.id.iv_back;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.iv_back);
        if (imageView != null) {
            i = R.id.web_view;
            WebView webView = (WebView) ViewBindings.findChildViewById(rootView, R.id.web_view);
            if (webView != null) {
                return new ActivityWebviewBinding((ConstraintLayout) rootView, imageView, webView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}
