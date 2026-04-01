package com.ecarx.eas.sdk.mediacenter.a;

import android.os.RemoteException;
import ecarx.xsf.mediacenter.vr.IMusicIntentObserver;
import ecarx.xsf.mediacenter.vr.QMusicResult;

/* JADX INFO: loaded from: classes2.dex */
public class c extends IMusicIntentObserver.Stub {
    @Override // ecarx.xsf.mediacenter.vr.IMusicIntentObserver
    public boolean handleCtrlApp(int i) throws RemoteException {
        return false;
    }

    @Override // ecarx.xsf.mediacenter.vr.IMusicIntentObserver
    public boolean handleCtrlMediaClient(int i, int i2) throws RemoteException {
        return false;
    }

    @Override // ecarx.xsf.mediacenter.vr.IMusicIntentObserver
    public boolean handlePlayMusic(QMusicResult qMusicResult) throws RemoteException {
        return false;
    }

    @Override // ecarx.xsf.mediacenter.vr.IMusicIntentObserver
    public boolean handleSearchMusic(QMusicResult qMusicResult) throws RemoteException {
        return false;
    }
}
