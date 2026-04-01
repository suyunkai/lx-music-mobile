package com.ecarx.eas.sdk.vehicle.able;

/* JADX INFO: loaded from: classes2.dex */
public interface ICallbackable<T> {
    boolean registerCallback(T t) throws UnsupportedOperationException;

    boolean unregisterCallback(T t) throws UnsupportedOperationException;
}
