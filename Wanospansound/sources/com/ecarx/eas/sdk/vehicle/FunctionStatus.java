package com.ecarx.eas.sdk.vehicle;

/* JADX INFO: loaded from: classes2.dex */
public enum FunctionStatus {
    active,
    notactive,
    notavailable,
    error;

    public static FunctionStatus getFunctionStatus(int i) {
        if (i == 0) {
            return active;
        }
        if (i == 1) {
            return notactive;
        }
        if (i == 2) {
            return notavailable;
        }
        return error;
    }
}
