package com.ecarx.eas.framework.sdk.common.exception;

/* JADX INFO: loaded from: classes2.dex */
public class UnknownException extends EASFrameworkException {
    public UnknownException(String str) {
        super(-1, str);
    }

    public UnknownException(String str, Throwable th) {
        super(-1, str, th);
    }
}
