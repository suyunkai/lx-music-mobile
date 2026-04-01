package com.arcvideo.ivi.res.sdk.data;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class ErrorBean {
    private String code;
    private String msg;

    public ErrorBean(String code, String msg) {
        Intrinsics.checkNotNullParameter(code, "code");
        Intrinsics.checkNotNullParameter(msg, "msg");
        this.code = code;
        this.msg = msg;
    }

    public static /* synthetic */ ErrorBean copy$default(ErrorBean errorBean, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = errorBean.code;
        }
        if ((i & 2) != 0) {
            str2 = errorBean.msg;
        }
        return errorBean.copy(str, str2);
    }

    public final String component1() {
        return this.code;
    }

    public final String component2() {
        return this.msg;
    }

    public final ErrorBean copy(String code, String msg) {
        Intrinsics.checkNotNullParameter(code, "code");
        Intrinsics.checkNotNullParameter(msg, "msg");
        return new ErrorBean(code, msg);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ErrorBean)) {
            return false;
        }
        ErrorBean errorBean = (ErrorBean) obj;
        return Intrinsics.areEqual(this.code, errorBean.code) && Intrinsics.areEqual(this.msg, errorBean.msg);
    }

    public final String getCode() {
        return this.code;
    }

    public final String getMsg() {
        return this.msg;
    }

    public int hashCode() {
        return (this.code.hashCode() * 31) + this.msg.hashCode();
    }

    public final void setCode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.code = str;
    }

    public final void setMsg(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.msg = str;
    }

    public String toString() {
        return "ErrorBean(code=" + this.code + ", msg=" + this.msg + ')';
    }

    public /* synthetic */ ErrorBean(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "-1" : str, str2);
    }
}
