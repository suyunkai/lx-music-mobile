package com.wanos.WanosCommunication.service;

/* JADX INFO: loaded from: classes3.dex */
public class Result<T> {
    public T data;
    public String message;
    public Status status;

    public enum Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    public Result(Status status, T t, String str) {
        this.status = status;
        this.data = t;
        this.message = str;
    }

    public static <T> Result<T> success(T t) {
        return new Result<>(Status.SUCCESS, t, null);
    }

    public static <T> Result<T> error(String str) {
        return new Result<>(Status.ERROR, null, str);
    }

    public static <T> Result<T> loading() {
        return new Result<>(Status.LOADING, null, null);
    }
}
