package com.ecarx.eas.framework.sdk.common.exception;

/* JADX INFO: loaded from: classes2.dex */
public class NotExistSupportServerException extends EASFrameworkException {
    public NotExistSupportServerException(String str) {
        super(501, str);
    }

    public NotExistSupportServerException(String str, Throwable th) {
        super(501, str, th);
    }
}
