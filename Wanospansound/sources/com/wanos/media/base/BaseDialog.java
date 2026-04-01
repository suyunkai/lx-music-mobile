package com.wanos.media.base;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

/* JADX INFO: loaded from: classes3.dex */
public class BaseDialog extends Dialog {
    private static BaseDialog instance;

    public enum DialogShowType {
        NO,
        Login,
        Logout,
        speedSet,
        AD,
        History,
        Code
    }

    public static BaseDialog getInstance() {
        return instance;
    }

    public static void setInstance(BaseDialog baseDialog) {
        instance = baseDialog;
    }

    public DialogShowType getType() {
        return DialogShowType.NO;
    }

    public BaseDialog(Context context) {
        super(context);
    }

    public BaseDialog(Context context, int i) {
        super(context, i);
    }

    protected BaseDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
    }

    @Override // android.app.Dialog
    public void show() {
        setInstance(this);
        super.show();
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        setInstance(null);
        super.dismiss();
    }
}
