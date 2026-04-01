package com.wanos.commonlibrary.utils;

import android.view.LayoutInflater;
import android.widget.Toast;
import com.blankj.utilcode.util.Utils;
import com.wanos.commonlibrary.databinding.ToastLayoutBinding;

/* JADX INFO: loaded from: classes3.dex */
public class ToastUtil {
    private static Toast toast;

    public static void showMsg(int i) {
        Toast toast2 = toast;
        if (toast2 != null) {
            toast2.cancel();
        }
        ToastLayoutBinding toastLayoutBindingInflate = ToastLayoutBinding.inflate(LayoutInflater.from(Utils.getApp().getBaseContext()));
        toast = new Toast(Utils.getApp().getBaseContext());
        toastLayoutBindingInflate.tvToast.setText(i);
        toast.setView(toastLayoutBindingInflate.getRoot());
        toast.setGravity(48, 0, 120);
        toast.setDuration(0);
        toast.show();
    }

    public static void showMsg(String str) {
        Toast toast2 = toast;
        if (toast2 != null) {
            toast2.cancel();
        }
        ToastLayoutBinding toastLayoutBindingInflate = ToastLayoutBinding.inflate(LayoutInflater.from(Utils.getApp().getBaseContext()));
        toastLayoutBindingInflate.tvToast.setText(str);
        Toast toast3 = new Toast(Utils.getApp().getBaseContext());
        toast = toast3;
        toast3.setView(toastLayoutBindingInflate.getRoot());
        toast.setGravity(48, 0, 120);
        toast.setDuration(0);
        toast.show();
    }

    public static void showSystemToast(int i) {
        Toast toast2 = toast;
        if (toast2 != null) {
            toast2.cancel();
        }
        Toast toast3 = new Toast(Utils.getApp().getBaseContext());
        toast = toast3;
        toast3.setText(i);
        toast.setDuration(0);
        toast.show();
    }
}
