package com.wanos.media.base;

/* JADX INFO: loaded from: classes3.dex */
public interface IDialogStateCallback {

    public enum DialogState {
        DISPLAY,
        DISMISS
    }

    void onDialogStateChanged(DialogState dialogState);
}
