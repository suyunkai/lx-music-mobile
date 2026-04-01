package com.ecarx.eas.framework.sdk.common.exception;

/* JADX INFO: loaded from: classes2.dex */
public class SupportServerErrorException extends EASFrameworkException {
    public SupportServerErrorException(String str) {
        super(502, str);
    }

    public SupportServerErrorException(String str, Throwable th) {
        super(502, str, th);
    }
}
