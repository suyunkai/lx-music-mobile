package com.ecarx.eas.framework.sdk.common.exception;

/* JADX INFO: loaded from: classes2.dex */
public class UnAuthorizedException extends EASFrameworkException {
    public UnAuthorizedException(String str) {
        super(401, str);
    }

    public UnAuthorizedException(String str, Throwable th) {
        super(401, str, th);
    }
}
