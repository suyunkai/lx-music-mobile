package com.wanos.media.util;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import androidx.cardview.widget.CardView;
import com.blankj.utilcode.util.StringUtils;
import com.wanos.MaterialAutoRefreshLayout;
import com.wanos.commonlibrary.fragment.LoadingController;
import com.wanos.media.R;
import com.wanos.media.databinding.LoadingQrCodeBinding;

/* JADX INFO: loaded from: classes3.dex */
public class LoadingUtil {
    public static int STATE;

    public static LoadingController initQRLoadingView(Context context, LoadingQrCodeBinding binding, CardView iView) {
        return new LoadingController.Builder(context, iView).setLoadingView(binding.qrLoadingLl).build();
    }

    public static LoadingController initCommonLoadingView(Context context, MaterialAutoRefreshLayout view, int loadingMsg, int emptyIcon, int emptyMsg) {
        return new LoadingController.Builder(context, view).setLoadingMessage(StringUtils.getString(loadingMsg)).setEmptyViewImageResource(emptyIcon).setEmptyMessage(StringUtils.getString(emptyMsg)).build();
    }

    public static void showQrLoading(LoadingController controller, LoadingQrCodeBinding binding, Animation anim, int type) {
        hideQrLoading(controller, binding);
        initLoading(controller, binding, anim, type);
    }

    private static void initLoading(LoadingController controller, LoadingQrCodeBinding binding, Animation anim, int type) {
        if (controller == null || binding == null) {
            return;
        }
        STATE = type;
        binding.qrLoadingIm.setScaleType(ImageView.ScaleType.FIT_CENTER);
        binding.qrLoadingIm.setImageResource(R.drawable.ic_header_loading);
        anim.setInterpolator(new LinearInterpolator());
        binding.qrLoadingIm.startAnimation(anim);
        binding.qrLoadingTv.setVisibility(8);
        controller.showQrLoading();
    }

    public static void showQrError(LoadingController controller, LoadingQrCodeBinding binding, int icon, String msg, int type) {
        hideQrLoading(controller, binding);
        initError(controller, binding, icon, msg, type);
    }

    private static void initError(LoadingController controller, LoadingQrCodeBinding binding, int icon, String msg, int type) {
        if (controller != null) {
            STATE = type;
            binding.qrLoadingIm.clearAnimation();
            binding.qrLoadingIm.setScaleType(ImageView.ScaleType.CENTER);
            binding.qrLoadingIm.setImageResource(icon);
            binding.qrLoadingTv.setVisibility(0);
            binding.qrLoadingTv.setText(msg);
            controller.showQrLoading();
        }
    }

    public static void hideQrLoading(LoadingController controller, LoadingQrCodeBinding binding) {
        if (controller == null || binding == null) {
            return;
        }
        binding.qrLoadingIm.clearAnimation();
        controller.dismissQrLoading();
    }
}
