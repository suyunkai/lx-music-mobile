package com.ecarx.eas.sdk;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ECarXApiClient {

    public interface Callback {
        void onAPIReady(boolean z);
    }

    public interface RemoteCallback {
        void onAPIReady(boolean z, String str);
    }
}
