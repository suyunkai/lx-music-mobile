package com.ecarx.eas.sdk.mediacenter.control;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public interface IMediaControlClientAPI {
    Object register(String str, MediaControlClient mediaControlClient);

    boolean requestControlled(Object obj);

    boolean unregister(Object obj);
}
