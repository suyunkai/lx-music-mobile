package com.ecarx.eas.framework.sdk.common.exception;

/* JADX INFO: loaded from: classes2.dex */
public class IllegalArgumentEASException extends EASFrameworkException {
    public IllegalArgumentEASException(String str) {
        super(403, str);
    }

    public IllegalArgumentEASException(String str, Throwable th) {
        super(403, str, th);
    }
}
