package com.wanos.careditproject.data.bean;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes3.dex */
public class AiCreateFail implements IAiCreateState {
    public static final int CANCEL = 102;
    public static final int FAIL = 101;
    public static final int TIME_OUT = 103;
    private final int code;
    private final String msg;

    @Retention(RetentionPolicy.SOURCE)
    public @interface FailCode {
    }

    public AiCreateFail(int i, String str) {
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
