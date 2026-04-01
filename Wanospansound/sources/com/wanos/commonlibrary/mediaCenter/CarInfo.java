package com.wanos.commonlibrary.mediaCenter;

import android.content.Context;

/* JADX INFO: loaded from: classes3.dex */
public interface CarInfo {

    public interface OnVinLis {
        void onGetVin(String str);
    }

    String getVin();

    void init(Context context, OnVinLis onVinLis);
}
