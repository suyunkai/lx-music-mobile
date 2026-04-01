package com.ecarx.eas.sdk.mediacenter.control;

import android.os.RemoteException;
import ecarx.xsf.mediacenter.bean.IMediaContentType;
import ecarx.xsf.mediacenter.control.IMediaControlClient;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class b extends IMediaControlClient.Stub {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private MediaControlClient f138a;

    b(MediaControlClient mediaControlClient) {
        this.f138a = mediaControlClient;
    }

    private MediaControlClient a() {
        MediaControlClient mediaControlClient = this.f138a;
        if (mediaControlClient == null) {
            return null;
        }
        return mediaControlClient;
    }

    @Override // ecarx.xsf.mediacenter.control.IMediaControlClient
    public final void onControlledChanged(String str) throws RemoteException {
        MediaControlClient mediaControlClientA = a();
        if (mediaControlClientA != null) {
            mediaControlClientA.onControlledChanged(str);
        }
    }

    @Override // ecarx.xsf.mediacenter.control.IMediaControlClient
    public final List<IMediaContentType> getMediaContentTypeList() throws RemoteException {
        MediaControlClient mediaControlClientA = a();
        if (mediaControlClientA != null) {
            return mediaControlClientA.getMediaContentTypeList();
        }
        return null;
    }

    @Override // ecarx.xsf.mediacenter.control.IMediaControlClient
    public final boolean onPlay(int i, String str) throws RemoteException {
        MediaControlClient mediaControlClientA = a();
        if (mediaControlClientA != null) {
            return mediaControlClientA.onPlay(i, str);
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.control.IMediaControlClient
    public final boolean onPlayByContent(int i, String str) throws RemoteException {
        MediaControlClient mediaControlClientA = a();
        if (mediaControlClientA != null) {
            return mediaControlClientA.onPlayByContent(i, str);
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.control.IMediaControlClient
    public final boolean onPause(int i) throws RemoteException {
        MediaControlClient mediaControlClientA = a();
        if (mediaControlClientA != null) {
            return mediaControlClientA.onPause(i);
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.control.IMediaControlClient
    public final boolean onResumeNow() throws RemoteException {
        MediaControlClient mediaControlClientA = a();
        if (mediaControlClientA != null) {
            return mediaControlClientA.onResumeNow();
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.control.IMediaControlClient
    public final boolean onPauseNow() throws RemoteException {
        MediaControlClient mediaControlClientA = a();
        if (mediaControlClientA != null) {
            return mediaControlClientA.onPauseNow();
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.control.IMediaControlClient
    public final boolean onPlayByMediaId(int i, String str) throws RemoteException {
        MediaControlClient mediaControlClientA = a();
        if (mediaControlClientA != null) {
            return mediaControlClientA.onPlayByMediaId(i, str);
        }
        return false;
    }
}
