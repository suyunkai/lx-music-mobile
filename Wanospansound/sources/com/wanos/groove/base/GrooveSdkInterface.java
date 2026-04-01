package com.wanos.groove.base;

import com.wanos.groove.listener.GrooveStateListener;

/* JADX INFO: loaded from: classes3.dex */
public interface GrooveSdkInterface {
    void registerCallback(GrooveStateListener grooveStateListener);

    void unregisterCallback(GrooveStateListener grooveStateListener);
}
