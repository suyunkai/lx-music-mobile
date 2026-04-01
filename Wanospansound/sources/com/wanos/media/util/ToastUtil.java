package com.wanos.media.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Toast;
import com.blankj.utilcode.util.Utils;
import com.wanos.commonlibrary.databinding.ToastLayoutBinding;
import com.wanos.media.ComResCenter;

/* JADX INFO: loaded from: classes3.dex */
public class ToastUtil {
    private static Toast toast;

    public static void showMsg(int i) {
        Toast toast2 = toast;
        if (toast2 != null) {
            toast2.cancel();
        }
        if (ComResCenter.getInstance() == null || ComResCenter.getInstance().mainServer.getTopActivity() != null) {
            ToastLayoutBinding toastLayoutBindingInflate = ToastLayoutBinding.inflate(LayoutInflater.from(Utils.getApp().getApplicationContext()));
            toast = new Toast(ComResCenter.getInstance().mainServer.getTopActivity());
            toastLayoutBindingInflate.tvToast.setText(i);
            toast.setView(toastLayoutBindingInflate.getRoot());
            toast.setGravity(48, 0, 120);
            toast.setDuration(0);
            toast.show();
        }
    }

    public static void showMsg(String str) {
        if (ComResCenter.getInstance() == null || ComResCenter.getInstance().mainServer.getTopActivity() != null) {
            showMsg(str, ComResCenter.getInstance().mainServer.getTopActivity());
        }
    }

    public static void showMsg(String str, Context context) {
        Toast toast2 = toast;
        if (toast2 != null) {
            toast2.cancel();
        }
        if (context == null) {
            return;
        }
        ToastLayoutBinding toastLayoutBindingInflate = ToastLayoutBinding.inflate(LayoutInflater.from(Utils.getApp().getApplicationContext()));
        toast = new Toast(context);
        toastLayoutBindingInflate.tvToast.setText(str);
        toast.setView(toastLayoutBindingInflate.getRoot());
        toast.setGravity(48, 0, 120);
        toast.setDuration(0);
        toast.show();
    }

    public static void showSystemToast(int i) {
        Toast toast2 = toast;
        if (toast2 != null) {
            toast2.cancel();
        }
        if (ComResCenter.getInstance() == null || ComResCenter.getInstance().mainServer.getTopActivity() != null) {
            Toast toast3 = new Toast(ComResCenter.getInstance().mainServer.getTopActivity());
            toast = toast3;
            toast3.setText(i);
            toast.setDuration(0);
            toast.show();
        }
    }
}
