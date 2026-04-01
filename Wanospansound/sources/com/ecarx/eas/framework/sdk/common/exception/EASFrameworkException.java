package com.ecarx.eas.framework.sdk.common.exception;

/* JADX INFO: loaded from: classes2.dex */
public class EASFrameworkException extends Exception {
    private int code;
    private String msg;

    public EASFrameworkException(int i, String str) {
        super(str);
        this.code = i;
        this.msg = str;
    }

    public EASFrameworkException(int i, String str, Throwable th) {
        super(str, th);
        this.code = i;
        this.msg = str;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
