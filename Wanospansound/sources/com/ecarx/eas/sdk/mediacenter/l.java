package com.ecarx.eas.sdk.mediacenter;

import android.os.RemoteException;
import android.util.Log;
import ecarx.xsf.mediacenter.INewsPlaybackInfo;

/* JADX INFO: loaded from: classes2.dex */
public final class l extends INewsPlaybackInfo.Stub {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private INewsPlaybackInfo f180a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private NewsPlaybackInfo f181b;

    l(INewsPlaybackInfo iNewsPlaybackInfo) {
        this.f180a = iNewsPlaybackInfo;
    }

    l(NewsPlaybackInfo newsPlaybackInfo) {
        this.f181b = newsPlaybackInfo;
    }

    private INewsPlaybackInfo a() {
        INewsPlaybackInfo iNewsPlaybackInfo = this.f180a;
        if (iNewsPlaybackInfo == null) {
            return null;
        }
        return iNewsPlaybackInfo;
    }

    private NewsPlaybackInfo b() {
        NewsPlaybackInfo newsPlaybackInfo = this.f181b;
        if (newsPlaybackInfo == null) {
            return null;
        }
        return newsPlaybackInfo;
    }

    @Override // ecarx.xsf.mediacenter.INewsPlaybackInfo
    public final int getPlaybackStatus() throws RemoteException {
        if (a() != null) {
            return a().getPlaybackStatus();
        }
        if (b() != null) {
            return b().getPlaybackStatus();
        }
        return 0;
    }

    @Override // ecarx.xsf.mediacenter.INewsPlaybackInfo
    public final boolean isSupportCollect() throws RemoteException {
        if (a() != null) {
            Log.e("NewsPlaybackInfoWrapper", "old interface not support");
            return false;
        }
        if (b() != null) {
            return b().isSupportCollect();
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.INewsPlaybackInfo
    public final boolean isCollected() throws RemoteException {
        if (a() != null) {
            Log.e("NewsPlaybackInfoWrapper", "old interface not support");
            return false;
        }
        if (b() != null) {
            return b().isCollected();
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.INewsPlaybackInfo
    public final boolean isSupportDownload() throws RemoteException {
        if (a() != null) {
            Log.e("NewsPlaybackInfoWrapper", "old interface not support");
            return false;
        }
        if (b() != null) {
            return b().isSupportDownload();
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.INewsPlaybackInfo
    public final boolean isDownloaded() throws RemoteException {
        if (a() != null) {
            Log.e("NewsPlaybackInfoWrapper", "old interface not support");
            return false;
        }
        if (b() != null) {
            return b().isDownloaded();
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.INewsPlaybackInfo
    public final boolean isSupportVrCtrlPlayStatus() throws RemoteException {
        if (a() != null) {
            Log.e("NewsPlaybackInfoWrapper", "old interface not support");
            return true;
        }
        if (b() != null) {
            return b().isSupportVrCtrlPlayStatus();
        }
        return true;
    }
}
