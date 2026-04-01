package com.wanos.media.viewmodel;

import androidx.lifecycle.ViewModel;
import com.wanos.media.view.AlarmDialog;

/* JADX INFO: loaded from: classes3.dex */
public class AlarmDialogViewModel extends ViewModel {
    private AlarmDialog.IDialogDismissCallback iDialogDismissCallback;

    public void setDialogDismissCallback(AlarmDialog.IDialogDismissCallback iDialogDismissCallback) {
        if (this.iDialogDismissCallback == null) {
            this.iDialogDismissCallback = iDialogDismissCallback;
        }
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        AlarmDialog.IDialogDismissCallback iDialogDismissCallback = this.iDialogDismissCallback;
        if (iDialogDismissCallback != null) {
            iDialogDismissCallback.onAlarmDialogDismiss();
        }
    }
}
