package com.ecarx.eas.sdk.mediacenter.control;

import android.os.RemoteException;
import ecarx.xsf.mediacenter.IMedia;
import ecarx.xsf.mediacenter.IMusicPlaybackInfo;
import ecarx.xsf.mediacenter.bean.IMediaContentType;
import ecarx.xsf.mediacenter.control.IMediaController;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class d extends IMediaController.Stub {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private MediaController f141a;

    d(MediaController mediaController) {
        this.f141a = mediaController;
    }

    private MediaController a() {
        MediaController mediaController = this.f141a;
        if (mediaController == null) {
            return null;
        }
        return mediaController;
    }

    @Override // ecarx.xsf.mediacenter.control.IMediaController
    public final void onControllerChanged(String str) throws RemoteException {
        MediaController mediaControllerA = a();
        if (mediaControllerA != null) {
            mediaControllerA.onControllerChanged(str);
        }
    }

    @Override // ecarx.xsf.mediacenter.control.IMediaController
    public final void updatePlaylist(int i, List<IMedia> list) throws RemoteException {
        MediaController mediaControllerA = a();
        if (mediaControllerA != null) {
            mediaControllerA.updatePlaylist(i, c.a(list));
        }
    }

    @Override // ecarx.xsf.mediacenter.control.IMediaController
    public final void updatePlaybackInfo(IMusicPlaybackInfo iMusicPlaybackInfo) throws RemoteException {
        MediaController mediaControllerA = a();
        if (mediaControllerA != null) {
            mediaControllerA.updatePlaybackInfo(c.a(iMusicPlaybackInfo));
        }
    }

    @Override // ecarx.xsf.mediacenter.control.IMediaController
    public final void updateErrorMsg(int i, String str) throws RemoteException {
        MediaController mediaControllerA = a();
        if (mediaControllerA != null) {
            mediaControllerA.updateErrorMsg(i, str);
        }
    }

    @Override // ecarx.xsf.mediacenter.control.IMediaController
    public final void updateCurrentProgress(long j) throws RemoteException {
        MediaController mediaControllerA = a();
        if (mediaControllerA != null) {
            mediaControllerA.updateCurrentProgress(j);
        }
    }

    @Override // ecarx.xsf.mediacenter.control.IMediaController
    public final void updateMediaContentTypeList(List<IMediaContentType> list) throws RemoteException {
        MediaController mediaControllerA = a();
        if (mediaControllerA != null) {
            mediaControllerA.updateMediaContentTypeList(list);
        }
    }
}
