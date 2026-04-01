package com.wanos.commonlibrary.fragment;

import android.R;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.wanos.commonlibrary.utils.LoadingInterface;
import com.wanos.commonlibrary.utils.Util;

/* JADX INFO: loaded from: classes3.dex */
public class LoadingController implements LoadingInterface {
    private final Context context;
    private int currentViewIndex;
    private final String emptyMessage;
    private final String emptyTodoText;
    private View emptyView;
    private final Drawable emptyViewImageDrawable;
    private final int emptyViewImageResource;
    private final Drawable errorImageDrawable;
    private final int errorImageResource;
    private final String errorMessage;
    private final String errorRetryText;
    public View errorView;
    private LayoutInflater inflater;
    private AnimationDrawable loadingAnimationDrawable;
    private final Drawable loadingImageDrawable;
    private final int loadingImageResource;
    private final String loadingMessage;
    private final View loadingTargetView;
    private View loadingView;
    private final String networkErrorRetryText;
    private View networkErrorView;
    private final LoadingInterface.OnClickListener onEmptyTodoClickListener;
    private final LoadingInterface.OnClickListener onErrorRetryClickListener;
    private final LoadingInterface.OnClickListener onNetworkErrorRetryClickListener;
    private ViewGroup.LayoutParams params;
    private ViewGroup parentView;

    private LoadingController(Builder builder) {
        this.context = builder.context;
        this.loadingTargetView = builder.loadingTargetView;
        this.loadingImageResource = builder.loadingImageResource;
        this.loadingImageDrawable = builder.loadingImageDrawable;
        this.loadingMessage = builder.loadingMessage;
        this.errorImageResource = builder.errorImageResoruce;
        this.errorImageDrawable = builder.errorImageDrawable;
        this.errorMessage = builder.errorMessage;
        this.emptyViewImageResource = builder.emptyViewImageResource;
        this.emptyViewImageDrawable = builder.emptyViewImageDrawable;
        this.emptyMessage = builder.emptyMessage;
        this.networkErrorRetryText = builder.networkErrorRetryText;
        this.onNetworkErrorRetryClickListener = builder.onNetworkErrorRetryClickListener;
        this.errorRetryText = builder.errorRetryText;
        this.onErrorRetryClickListener = builder.onErrorRetryClickListener;
        this.emptyTodoText = builder.emptyTodoText;
        this.onEmptyTodoClickListener = builder.onEmptyTodoClickListener;
        if (builder.customLoadingView != null) {
            this.loadingView = builder.customLoadingView;
        }
        if (builder.customNetworkErrorView != null) {
            this.networkErrorView = builder.customNetworkErrorView;
        }
        if (builder.customErrorView != null) {
            this.errorView = builder.customErrorView;
        }
        if (builder.customEmptyView != null) {
            this.emptyView = builder.customEmptyView;
        }
        init();
    }

    private void init() {
        this.inflater = LayoutInflater.from(this.context);
        this.params = this.loadingTargetView.getLayoutParams();
        if (this.loadingTargetView.getParent() != null) {
            this.parentView = (ViewGroup) this.loadingTargetView.getParent();
        } else {
            this.parentView = (ViewGroup) this.loadingTargetView.getRootView().findViewById(R.id.content);
        }
        int childCount = this.parentView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (this.loadingTargetView == this.parentView.getChildAt(i)) {
                this.currentViewIndex = i;
                return;
            }
        }
    }

    private void showView(View view) {
        if (this.parentView.getChildAt(this.currentViewIndex) != view) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(view);
            }
            this.parentView.removeViewAt(this.currentViewIndex);
            this.parentView.addView(view, this.currentViewIndex, this.params);
            AnimationDrawable animationDrawable = this.loadingAnimationDrawable;
            if (animationDrawable != null) {
                if (view == this.loadingView) {
                    animationDrawable.start();
                } else {
                    animationDrawable.stop();
                }
            }
        }
    }

    private void showQrView(View view) {
        if (this.parentView.getChildAt(this.currentViewIndex) != view) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(view);
            }
            this.parentView.addView(view, this.currentViewIndex + 1, this.params);
            AnimationDrawable animationDrawable = this.loadingAnimationDrawable;
            if (animationDrawable != null) {
                if (view == this.loadingView) {
                    animationDrawable.start();
                } else {
                    animationDrawable.stop();
                }
            }
        }
    }

    @Override // com.wanos.commonlibrary.utils.LoadingInterface
    public void showLoading() {
        View view = this.loadingView;
        if (view != null) {
            showView(view);
            return;
        }
        View viewInflate = this.inflater.inflate(com.wanos.commonlibrary.R.layout.fragment_loading, (ViewGroup) null);
        this.loadingView = viewInflate;
        ProgressBar progressBar = (ProgressBar) viewInflate.findViewById(com.wanos.commonlibrary.R.id.pb_loading);
        ImageView imageView = (ImageView) this.loadingView.findViewById(com.wanos.commonlibrary.R.id.iv_loading);
        TextView textView = (TextView) this.loadingView.findViewById(com.wanos.commonlibrary.R.id.tv_loading);
        Util.setTextWeight(textView, 500);
        progressBar.setVisibility(8);
        imageView.setVisibility(8);
        textView.setVisibility(8);
        if (this.loadingImageResource != 0) {
            imageView.setVisibility(0);
            imageView.setImageResource(this.loadingImageResource);
            Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AnimationDrawable) {
                this.loadingAnimationDrawable = (AnimationDrawable) drawable;
            }
        } else if (this.loadingImageDrawable != null) {
            imageView.setVisibility(0);
            imageView.setImageResource(this.loadingImageResource);
            Drawable drawable2 = imageView.getDrawable();
            if (drawable2 instanceof AnimationDrawable) {
                this.loadingAnimationDrawable = (AnimationDrawable) drawable2;
            }
        } else {
            progressBar.setVisibility(0);
        }
        if (!TextUtils.isEmpty(this.loadingMessage)) {
            textView.setVisibility(0);
            textView.setText(this.loadingMessage);
        }
        showView(this.loadingView);
    }

    @Override // com.wanos.commonlibrary.utils.LoadingInterface
    public void showQrLoading() {
        View view = this.loadingView;
        if (view != null) {
            showQrView(view);
            return;
        }
        View viewInflate = this.inflater.inflate(com.wanos.commonlibrary.R.layout.fragment_loading, (ViewGroup) null);
        this.loadingView = viewInflate;
        ProgressBar progressBar = (ProgressBar) viewInflate.findViewById(com.wanos.commonlibrary.R.id.pb_loading);
        ImageView imageView = (ImageView) this.loadingView.findViewById(com.wanos.commonlibrary.R.id.iv_loading);
        TextView textView = (TextView) this.loadingView.findViewById(com.wanos.commonlibrary.R.id.tv_loading);
        Util.setTextWeight(textView, 500);
        progressBar.setVisibility(8);
        imageView.setVisibility(8);
        textView.setVisibility(8);
        if (this.loadingImageResource != 0) {
            imageView.setVisibility(0);
            imageView.setImageResource(this.loadingImageResource);
            Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AnimationDrawable) {
                this.loadingAnimationDrawable = (AnimationDrawable) drawable;
            }
        } else if (this.loadingImageDrawable != null) {
            imageView.setVisibility(0);
            imageView.setImageResource(this.loadingImageResource);
            Drawable drawable2 = imageView.getDrawable();
            if (drawable2 instanceof AnimationDrawable) {
                this.loadingAnimationDrawable = (AnimationDrawable) drawable2;
            }
        } else {
            progressBar.setVisibility(0);
        }
        if (!TextUtils.isEmpty(this.loadingMessage)) {
            textView.setVisibility(0);
            textView.setText(this.loadingMessage);
        }
        showQrView(this.loadingView);
    }

    @Override // com.wanos.commonlibrary.utils.LoadingInterface
    public void showNetworkError() {
        View view = this.networkErrorView;
        if (view != null) {
            showView(view);
            return;
        }
        View viewInflate = this.inflater.inflate(com.wanos.commonlibrary.R.layout.error, (ViewGroup) null);
        this.networkErrorView = viewInflate;
        ImageView imageView = (ImageView) viewInflate.findViewById(com.wanos.commonlibrary.R.id.iv_error);
        TextView textView = (TextView) this.networkErrorView.findViewById(com.wanos.commonlibrary.R.id.tv_errorMessage);
        TextView textView2 = (TextView) this.networkErrorView.findViewById(com.wanos.commonlibrary.R.id.btn_retry);
        Util.setTextWeight(textView, 500);
        imageView.setImageResource(com.wanos.commonlibrary.R.drawable.icon_error);
        int i = this.errorImageResource;
        if (i != 0) {
            imageView.setImageResource(i);
        } else {
            Drawable drawable = this.errorImageDrawable;
            if (drawable != null) {
                imageView.setImageDrawable(drawable);
            }
        }
        if (!TextUtils.isEmpty(this.errorMessage)) {
            textView.setText(this.errorMessage);
        } else {
            textView.setText(this.context.getResources().getString(com.wanos.commonlibrary.R.string.wanos_error_message));
        }
        if (!TextUtils.isEmpty(this.networkErrorRetryText)) {
            textView2.setText(this.networkErrorRetryText);
        }
        if (this.onNetworkErrorRetryClickListener != null) {
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.commonlibrary.fragment.LoadingController$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f$0.m447xe2a0658d(view2);
                }
            });
        }
        showView(this.networkErrorView);
    }

    /* JADX INFO: renamed from: lambda$showNetworkError$0$com-wanos-commonlibrary-fragment-LoadingController, reason: not valid java name */
    /* synthetic */ void m447xe2a0658d(View view) {
        this.onNetworkErrorRetryClickListener.onClick();
    }

    @Override // com.wanos.commonlibrary.utils.LoadingInterface
    public void showError() {
        View view = this.errorView;
        if (view != null) {
            showView(view);
            return;
        }
        View viewInflate = this.inflater.inflate(com.wanos.commonlibrary.R.layout.error, (ViewGroup) null);
        this.errorView = viewInflate;
        ImageView imageView = (ImageView) viewInflate.findViewById(com.wanos.commonlibrary.R.id.iv_error);
        TextView textView = (TextView) this.errorView.findViewById(com.wanos.commonlibrary.R.id.tv_errorMessage);
        TextView textView2 = (TextView) this.errorView.findViewById(com.wanos.commonlibrary.R.id.btn_retry);
        Util.setTextWeight(textView, 500);
        imageView.setImageResource(com.wanos.commonlibrary.R.drawable.icon_error);
        int i = this.errorImageResource;
        if (i != 0) {
            imageView.setImageResource(i);
        } else {
            Drawable drawable = this.errorImageDrawable;
            if (drawable != null) {
                imageView.setImageDrawable(drawable);
            }
        }
        if (!TextUtils.isEmpty(this.errorMessage)) {
            textView.setText(this.errorMessage);
        } else {
            textView.setText(this.context.getResources().getString(com.wanos.commonlibrary.R.string.wanos_error_message));
        }
        if (!TextUtils.isEmpty(this.errorRetryText)) {
            textView2.setText(this.errorRetryText);
        }
        if (this.onErrorRetryClickListener != null) {
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.commonlibrary.fragment.LoadingController$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f$0.m446xc37a0b12(view2);
                }
            });
        }
        showView(this.errorView);
    }

    /* JADX INFO: renamed from: lambda$showError$1$com-wanos-commonlibrary-fragment-LoadingController, reason: not valid java name */
    /* synthetic */ void m446xc37a0b12(View view) {
        this.onErrorRetryClickListener.onClick();
    }

    @Override // com.wanos.commonlibrary.utils.LoadingInterface
    public void showEmpty() {
        View view = this.emptyView;
        if (view != null) {
            showView(view);
            return;
        }
        View viewInflate = this.inflater.inflate(com.wanos.commonlibrary.R.layout.empty_view, (ViewGroup) null);
        this.emptyView = viewInflate;
        ImageView imageView = (ImageView) viewInflate.findViewById(com.wanos.commonlibrary.R.id.iv_empty);
        TextView textView = (TextView) this.emptyView.findViewById(com.wanos.commonlibrary.R.id.tv_empty);
        Util.setTextWeight(textView, 500);
        int i = this.emptyViewImageResource;
        if (i != 0) {
            imageView.setImageResource(i);
        } else {
            Drawable drawable = this.emptyViewImageDrawable;
            if (drawable != null) {
                imageView.setImageDrawable(drawable);
            } else {
                imageView.setImageResource(com.wanos.commonlibrary.R.drawable.icon_empty);
            }
        }
        if (!TextUtils.isEmpty(this.emptyMessage)) {
            textView.setText(this.emptyMessage);
        } else {
            textView.setText(com.wanos.commonlibrary.R.string.empty_msg);
        }
        showView(this.emptyView);
    }

    @Override // com.wanos.commonlibrary.utils.LoadingInterface
    public void showEmpty(boolean z) {
        View view = this.emptyView;
        if (view != null) {
            showView(view);
            return;
        }
        View viewInflate = this.inflater.inflate(com.wanos.commonlibrary.R.layout.empty_view, (ViewGroup) null);
        this.emptyView = viewInflate;
        ImageView imageView = (ImageView) viewInflate.findViewById(com.wanos.commonlibrary.R.id.iv_empty);
        TextView textView = (TextView) this.emptyView.findViewById(com.wanos.commonlibrary.R.id.tv_empty);
        Util.setTextWeight(textView, 500);
        if (z) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
            marginLayoutParams.setMargins(0, -160, 0, 0);
            imageView.setLayoutParams(marginLayoutParams);
        }
        int i = this.emptyViewImageResource;
        if (i != 0) {
            imageView.setImageResource(i);
        } else {
            Drawable drawable = this.emptyViewImageDrawable;
            if (drawable != null) {
                imageView.setImageDrawable(drawable);
            } else {
                imageView.setImageResource(com.wanos.commonlibrary.R.drawable.icon_empty);
            }
        }
        if (!TextUtils.isEmpty(this.emptyMessage)) {
            textView.setText(this.emptyMessage);
        } else {
            textView.setText(com.wanos.commonlibrary.R.string.empty_msg);
        }
        showView(this.emptyView);
    }

    @Override // com.wanos.commonlibrary.utils.LoadingInterface
    public void dismissLoading() {
        showView(this.loadingTargetView);
    }

    @Override // com.wanos.commonlibrary.utils.LoadingInterface
    public void dismissQrLoading() {
        if (this.parentView.getChildAt(this.currentViewIndex + 1) == this.loadingView) {
            this.parentView.removeViewAt(this.currentViewIndex + 1);
        }
    }

    public void close() {
        if (this.parentView.getChildAt(this.currentViewIndex) != this.loadingTargetView) {
            ViewGroup viewGroup = this.parentView;
            viewGroup.removeView(viewGroup.getChildAt(this.currentViewIndex));
        }
    }

    public static class Builder {
        private final Context context;
        private View customEmptyView;
        private View customErrorView;
        private View customLoadingView;
        private View customNetworkErrorView;
        private String emptyMessage;
        private String emptyTodoText;
        private Drawable emptyViewImageDrawable;
        private int emptyViewImageResource;
        private Drawable errorImageDrawable;
        private int errorImageResoruce;
        private String errorMessage;
        private String errorRetryText;
        private Drawable loadingImageDrawable;
        private int loadingImageResource;
        private String loadingMessage;
        private final View loadingTargetView;
        private String networkErrorRetryText;
        private LoadingInterface.OnClickListener onEmptyTodoClickListener;
        private LoadingInterface.OnClickListener onErrorRetryClickListener;
        private LoadingInterface.OnClickListener onNetworkErrorRetryClickListener;

        public Builder(Context context, View view) {
            this.context = context;
            this.loadingTargetView = view;
        }

        public Builder setLoadingImageResource(int i) {
            this.loadingImageResource = i;
            return this;
        }

        public Builder setLoadingImageDrawable(Drawable drawable) {
            this.loadingImageDrawable = drawable;
            return this;
        }

        public Builder setLoadingMessage(String str) {
            this.loadingMessage = str;
            return this;
        }

        public Builder setLoadingView(View view) {
            this.customLoadingView = view;
            return this;
        }

        public Builder setNetworkErrorView(View view) {
            this.customNetworkErrorView = view;
            return this;
        }

        public Builder setErrorImageResoruce(int i) {
            this.errorImageResoruce = i;
            return this;
        }

        public Builder setErrorImageDrawable(Drawable drawable) {
            this.errorImageDrawable = drawable;
            return this;
        }

        public Builder setErrorMessage(String str) {
            this.errorMessage = str;
            return this;
        }

        public Builder setErrorView(View view) {
            this.customErrorView = view;
            return this;
        }

        public Builder setEmptyViewImageResource(int i) {
            this.emptyViewImageResource = i;
            return this;
        }

        public Builder setEmptyViewImageDrawable(Drawable drawable) {
            this.emptyViewImageDrawable = drawable;
            return this;
        }

        public Builder setEmptyMessage(String str) {
            this.emptyMessage = str;
            return this;
        }

        public Builder setEmptyView(View view) {
            this.customEmptyView = view;
            return this;
        }

        public Builder setOnNetworkErrorRetryClickListener(LoadingInterface.OnClickListener onClickListener) {
            this.onNetworkErrorRetryClickListener = onClickListener;
            return this;
        }

        public Builder setOnNetworkErrorRetryClickListener(String str, LoadingInterface.OnClickListener onClickListener) {
            this.networkErrorRetryText = str;
            this.onNetworkErrorRetryClickListener = onClickListener;
            return this;
        }

        public Builder setOnErrorRetryClickListener(LoadingInterface.OnClickListener onClickListener) {
            this.onErrorRetryClickListener = onClickListener;
            return this;
        }

        public Builder setOnErrorRetryClickListener(String str, LoadingInterface.OnClickListener onClickListener) {
            this.errorRetryText = str;
            this.onErrorRetryClickListener = onClickListener;
            return this;
        }

        public Builder setOnEmptyTodoClickListener(LoadingInterface.OnClickListener onClickListener) {
            this.onEmptyTodoClickListener = onClickListener;
            return this;
        }

        public Builder setOnEmptyTodoClickListener(String str, LoadingInterface.OnClickListener onClickListener) {
            this.emptyTodoText = str;
            this.onEmptyTodoClickListener = onClickListener;
            return this;
        }

        public LoadingController build() {
            return new LoadingController(this);
        }
    }
}
