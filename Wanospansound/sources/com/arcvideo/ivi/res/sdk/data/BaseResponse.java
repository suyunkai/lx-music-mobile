package com.arcvideo.ivi.res.sdk.data;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
public class BaseResponse<T> {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("result")
    private String result;

    @SerializedName("success")
    private T success;

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getResult() {
        return this.result;
    }

    public T getSuccess() {
        return this.success;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setResult(String str) {
        this.result = str;
    }

    public void setSuccess(T t) {
        this.success = t;
    }
}
