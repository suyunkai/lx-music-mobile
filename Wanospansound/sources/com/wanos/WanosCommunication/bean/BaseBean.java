package com.wanos.WanosCommunication.bean;

/* JADX INFO: loaded from: classes3.dex */
public class BaseBean<T> {
    private T data;
    private String errorcode;
    private String msg;

    public T getData() {
        return this.data;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public String getErrorcode() {
        return this.errorcode;
    }

    public void setErrorcode(String str) {
        this.errorcode = str;
    }
}
