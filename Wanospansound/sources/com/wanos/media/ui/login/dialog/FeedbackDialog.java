package com.wanos.media.ui.login.dialog;

import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.fragment.app.DialogFragment;
import com.wanos.commonlibrary.fragment.LoadingController;
import com.wanos.commonlibrary.utils.QRCodeUtils;
import com.wanos.commonlibrary.utils.Util;
import com.wanos.media.R;
import com.wanos.media.databinding.DialogFeedbackBinding;
import com.wanos.media.databinding.LoadingQrCodeBinding;
import com.wanos.media.util.LoadingUtil;

/* JADX INFO: loaded from: classes3.dex */
public class FeedbackDialog extends DialogFragment {
    private Animation animation;
    private DialogFeedbackBinding binding;
    private LoadingController controller;
    private LoadingQrCodeBinding qrCodeBinding;
    private String url;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = DialogFeedbackBinding.inflate(inflater, container, false);
        this.qrCodeBinding = LoadingQrCodeBinding.inflate(inflater, container, false);
        initView();
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        setCancelable(false);
        return this.binding.getRoot();
    }

    private void initView() {
        initLoadingView();
        LoadingUtil.showQrLoading(this.controller, this.qrCodeBinding, this.animation, 1);
        this.binding.btnBack.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.ui.login.dialog.FeedbackDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m519lambda$initView$0$comwanosmediauilogindialogFeedbackDialog(view);
            }
        });
        Bitmap bitmapCreateQRCode = QRCodeUtils.createQRCode(this.url, Util.dip2px(getContext(), 640.0f), Util.dip2px(getContext(), 640.0f), 1, null);
        if (bitmapCreateQRCode != null) {
            this.binding.ivQr.setImageBitmap(bitmapCreateQRCode);
            LoadingUtil.hideQrLoading(this.controller, this.qrCodeBinding);
        }
    }

    /* JADX INFO: renamed from: lambda$initView$0$com-wanos-media-ui-login-dialog-FeedbackDialog, reason: not valid java name */
    /* synthetic */ void m519lambda$initView$0$comwanosmediauilogindialogFeedbackDialog(View view) {
        dismiss();
    }

    private void initLoadingView() {
        this.animation = AnimationUtils.loadAnimation(getContext(), R.anim.loading_anim);
        this.controller = LoadingUtil.initQRLoadingView(getContext(), this.qrCodeBinding, this.binding.layoutQr);
    }

    public void setQRUrl(String url) {
        this.url = url;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.dimAmount = 0.6f;
        window.setAttributes(attributes);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(getResources().getDimensionPixelOffset(R.dimen.p_dialog_feedback_w), getResources().getDimensionPixelOffset(R.dimen.p_dialog_feedback_h));
    }
}
