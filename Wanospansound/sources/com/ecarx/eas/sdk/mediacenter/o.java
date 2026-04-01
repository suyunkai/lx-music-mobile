package com.ecarx.eas.sdk.mediacenter;

import android.os.RemoteException;
import android.util.Log;
import ecarx.xsf.mediacenter.IVideoClient;

/* JADX INFO: loaded from: classes2.dex */
public final class o extends IVideoClient.Stub {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private IVideoClient f185a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private VideoClient f186b;

    o(IVideoClient iVideoClient) {
        this.f185a = iVideoClient;
    }

    o(VideoClient videoClient) {
        this.f186b = videoClient;
    }

    private IVideoClient a() {
        IVideoClient iVideoClient = this.f185a;
        if (iVideoClient == null) {
            return null;
        }
        return iVideoClient;
    }

    private VideoClient b() {
        VideoClient videoClient = this.f186b;
        if (videoClient == null) {
            return null;
        }
        return videoClient;
    }

    @Override // ecarx.xsf.mediacenter.IVideoClient
    public final boolean onPlay() throws RemoteException {
        if (a() != null) {
            return a().onPlay();
        }
        if (b() != null) {
            return b().onPlay();
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.IVideoClient
    public final boolean onPause() throws RemoteException {
        if (a() != null) {
            return a().onPause();
        }
        if (b() != null) {
            return b().onPause();
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.IVideoClient
    public final boolean onNext() throws RemoteException {
        if (a() != null) {
            return a().onNext();
        }
        if (b() != null) {
            return b().onNext();
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.IVideoClient
    public final boolean onPrevious() throws RemoteException {
        if (a() != null) {
            return a().onPrevious();
        }
        if (b() != null) {
            return b().onPrevious();
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.IVideoClient
    public final boolean onForward() throws RemoteException {
        if (a() != null) {
            return a().onForward();
        }
        if (b() != null) {
            return b().onForward();
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.IVideoClient
    public final boolean onRewind() throws RemoteException {
        if (a() != null) {
            return a().onRewind();
        }
        if (b() != null) {
            return b().onRewind();
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.IVideoClient
    public final ecarx.xsf.mediacenter.IVideoPlaybackInfo getVideoPlaybackInfo() throws RemoteException {
        if (a() != null) {
            return new p(a().getVideoPlaybackInfo());
        }
        if (b() != null) {
            return new p(b().getVideoPlaybackInfo());
        }
        return null;
    }

    @Override // ecarx.xsf.mediacenter.IVideoClient
    public final boolean onCollect(int i, boolean z) throws RemoteException {
        if (a() != null) {
            Log.e("VideoClientWrapper", "old interface not support");
            return false;
        }
        if (b() != null) {
            return b().onCollect(i, z);
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.IVideoClient
    public final boolean onDownload(int i, boolean z) throws RemoteException {
        if (a() != null) {
            Log.e("VideoClientWrapper", "old interface not support");
            return false;
        }
        if (b() != null) {
            return b().onDownload(i, z);
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.IVideoClient
    public final boolean onReplay() throws RemoteException {
        if (a() != null) {
            Log.e("VideoClientWrapper", "old interface not support");
            return false;
        }
        if (b() != null) {
            return b().onReplay();
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.IVideoClient
    public final void onMediaCenterFocusChanged(String str) throws RemoteException {
        if (a() != null) {
            Log.e("VideoClientWrapper", "old interface not support");
        } else if (b() != null) {
            b().onMediaCenterFocusChanged(str);
        }
    }

    @Override // ecarx.xsf.mediacenter.IVideoClient
    public final boolean onExit() throws RemoteException {
        if (a() != null) {
            Log.e("VideoClientWrapper", "old interface not support");
            return false;
        }
        if (b() != null) {
            return b().onExit();
        }
        return false;
    }
}
