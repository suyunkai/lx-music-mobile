package com.arcvideo.ivi.res.sdk.data;

/* JADX INFO: loaded from: classes.dex */
public final class RegisterInfo {
    private boolean success;
    private Long sysTime = 0L;
    private String token;

    public final boolean getSuccess() {
        return this.success;
    }

    public final Long getSysTime() {
        return this.sysTime;
    }

    public final String getToken() {
        return this.token;
    }

    public final void setSuccess(boolean z) {
        this.success = z;
    }

    public final void setSysTime(Long l) {
        this.sysTime = l;
    }

    public final void setToken(String str) {
        this.token = str;
    }
}
