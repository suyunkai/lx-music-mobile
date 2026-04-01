package com.wanos.media.base;

import android.content.DialogInterface;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.wanos.media.base.BaseDialog;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseAdDialog extends DialogFragment {
    private static BaseAdDialog instance;
    protected BaseAdDialog nextDialog;

    public abstract void showDialog();

    public void setNextDialog(BaseAdDialog baseAdDialog) {
        this.nextDialog = baseAdDialog;
    }

    public static BaseAdDialog getInstance() {
        return instance;
    }

    public static void setInstance(BaseAdDialog baseAdDialog) {
        instance = baseAdDialog;
    }

    public BaseDialog.DialogShowType getType() {
        return BaseDialog.DialogShowType.NO;
    }

    @Override // androidx.fragment.app.DialogFragment
    public void show(FragmentManager fragmentManager, String str) {
        setInstance(this);
        fragmentManager.beginTransaction().add(this, str).commitAllowingStateLoss();
    }

    @Override // androidx.fragment.app.DialogFragment
    public void dismiss() {
        setInstance(null);
        super.dismiss();
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        setInstance(null);
        super.onCancel(dialogInterface);
    }

    @Override // androidx.fragment.app.DialogFragment
    public void dismissAllowingStateLoss() {
        setInstance(null);
        super.dismissAllowingStateLoss();
    }
}
