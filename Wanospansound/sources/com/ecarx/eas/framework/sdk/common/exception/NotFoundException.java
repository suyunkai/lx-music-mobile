package com.ecarx.eas.framework.sdk.common.exception;

/* JADX INFO: loaded from: classes2.dex */
public class NotFoundException extends EASFrameworkException {
    public NotFoundException(String str) {
        super(404, str);
    }

    public NotFoundException(String str, Throwable th) {
        super(404, str, th);
    }
}
