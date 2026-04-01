package com.wanos.careditproject.utils;

/* JADX INFO: loaded from: classes3.dex */
public class WanosPlayerParamUtils {
    private static WanosPlayerParamUtils wanosPlayerParamUtils;
    private long curSampleNum = 0;

    public static WanosPlayerParamUtils getInstance() {
        if (wanosPlayerParamUtils == null) {
            wanosPlayerParamUtils = new WanosPlayerParamUtils();
        }
        return wanosPlayerParamUtils;
    }

    public long getCurSampleNum() {
        return this.curSampleNum;
    }

    public void setCurSampleNum(long j) {
        this.curSampleNum = j;
    }
}
