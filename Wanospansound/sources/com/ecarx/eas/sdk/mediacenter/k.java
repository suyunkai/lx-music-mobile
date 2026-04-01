package com.ecarx.eas.sdk.mediacenter;

import android.os.RemoteException;
import android.util.Log;
import ecarx.xsf.mediacenter.INewsClient;

/* JADX INFO: loaded from: classes2.dex */
public final class k extends INewsClient.Stub {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private INewsClient f178a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private NewsClient f179b;

    k(INewsClient iNewsClient) {
        this.f178a = iNewsClient;
    }

    k(NewsClient newsClient) {
        this.f179b = newsClient;
    }

    private INewsClient a() {
        INewsClient iNewsClient = this.f178a;
        if (iNewsClient == null) {
            return null;
        }
        return iNewsClient;
    }

    private NewsClient b() {
        NewsClient newsClient = this.f179b;
        if (newsClient == null) {
            return null;
        }
        return newsClient;
    }

    @Override // ecarx.xsf.mediacenter.INewsClient
    public final boolean onPlay() throws RemoteException {
        if (a() != null) {
            return a().onPlay();
        }
        if (b() != null) {
            return b().onPlay();
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.INewsClient
    public final boolean onPause() throws RemoteException {
        if (a() != null) {
            return a().onPause();
        }
        if (b() != null) {
            return b().onPause();
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.INewsClient
    public final boolean onNext() throws RemoteException {
        if (a() != null) {
            return a().onNext();
        }
        if (b() != null) {
            return b().onNext();
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.INewsClient
    public final boolean onPrevious() throws RemoteException {
        if (a() != null) {
            return a().onPrevious();
        }
        if (b() != null) {
            return b().onPrevious();
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.INewsClient
    public final ecarx.xsf.mediacenter.INewsPlaybackInfo getNewsPlaybackInfo() throws RemoteException {
        if (a() != null) {
            return new l(a().getNewsPlaybackInfo());
        }
        if (b() != null) {
            return new l(b().getNewsPlaybackInfo());
        }
        return null;
    }

    @Override // ecarx.xsf.mediacenter.INewsClient
    public final boolean onCollect(int i, boolean z) throws RemoteException {
        if (a() != null) {
            Log.e("NewsClientWrapper", "old interface not support");
            return false;
        }
        if (b() != null) {
            return b().onCollect(i, z);
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.INewsClient
    public final boolean onDownload(int i, boolean z) throws RemoteException {
        if (a() != null) {
            Log.e("NewsClientWrapper", "old interface not support");
            return false;
        }
        if (b() != null) {
            return b().onDownload(i, z);
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.INewsClient
    public final boolean onReplay() throws RemoteException {
        if (a() != null) {
            Log.e("NewsClientWrapper", "old interface not support");
            return false;
        }
        if (b() != null) {
            return b().onReplay();
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.INewsClient
    public final void onMediaCenterFocusChanged(String str) throws RemoteException {
        if (a() != null) {
            Log.e("NewsClientWrapper", "old interface not support");
        } else if (b() != null) {
            b().onMediaCenterFocusChanged(str);
        }
    }

    @Override // ecarx.xsf.mediacenter.INewsClient
    public final boolean onExit() throws RemoteException {
        if (a() != null) {
            Log.e("NewsClientWrapper", "old interface not support");
            return false;
        }
        if (b() != null) {
            return b().onExit();
        }
        return false;
    }
}
