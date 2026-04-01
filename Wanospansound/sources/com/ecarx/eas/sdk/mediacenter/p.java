package com.ecarx.eas.sdk.mediacenter;

import android.os.RemoteException;
import android.util.Log;
import ecarx.xsf.mediacenter.IVideoPlaybackInfo;

/* JADX INFO: loaded from: classes2.dex */
public final class p extends IVideoPlaybackInfo.Stub {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private IVideoPlaybackInfo f187a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private VideoPlaybackInfo f188b;

    p(IVideoPlaybackInfo iVideoPlaybackInfo) {
        this.f187a = iVideoPlaybackInfo;
    }

    p(VideoPlaybackInfo videoPlaybackInfo) {
        this.f188b = videoPlaybackInfo;
    }

    private IVideoPlaybackInfo a() {
        IVideoPlaybackInfo iVideoPlaybackInfo = this.f187a;
        if (iVideoPlaybackInfo == null) {
            return null;
        }
        return iVideoPlaybackInfo;
    }

    private VideoPlaybackInfo b() {
        VideoPlaybackInfo videoPlaybackInfo = this.f188b;
        if (videoPlaybackInfo == null) {
            return null;
        }
        return videoPlaybackInfo;
    }

    @Override // ecarx.xsf.mediacenter.IVideoPlaybackInfo
    public final int getPlaybackStatus() throws RemoteException {
        if (a() != null) {
            return a().getPlaybackStatus();
        }
        if (b() != null) {
            return b().getPlaybackStatus();
        }
        return 0;
    }

    @Override // ecarx.xsf.mediacenter.IVideoPlaybackInfo
    public final boolean isSupportCollect() throws RemoteException {
        if (a() != null) {
            Log.e("VideoPlaybackInfo", "old interface not support");
            return false;
        }
        if (b() != null) {
            return b().isSupportCollect();
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.IVideoPlaybackInfo
    public final boolean isCollected() throws RemoteException {
        if (a() != null) {
            Log.e("VideoPlaybackInfo", "old interface not support");
            return false;
        }
        if (b() != null) {
            return b().isCollected();
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.IVideoPlaybackInfo
    public final boolean isSupportDownload() throws RemoteException {
        if (a() != null) {
            Log.e("VideoPlaybackInfo", "old interface not support");
            return false;
        }
        if (b() != null) {
            return b().isSupportDownload();
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.IVideoPlaybackInfo
    public final boolean isDownloaded() throws RemoteException {
        if (a() != null) {
            Log.e("VideoPlaybackInfo", "old interface not support");
            return false;
        }
        if (b() != null) {
            return b().isDownloaded();
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.IVideoPlaybackInfo
    public final boolean isSupportVrCtrlPlayStatus() throws RemoteException {
        if (a() != null) {
            Log.e("VideoPlaybackInfo", "old interface not support");
            return true;
        }
        if (b() != null) {
            return b().isSupportVrCtrlPlayStatus();
        }
        return true;
    }
}
