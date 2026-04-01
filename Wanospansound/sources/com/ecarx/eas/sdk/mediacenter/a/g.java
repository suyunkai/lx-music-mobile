package com.ecarx.eas.sdk.mediacenter.a;

import android.os.RemoteException;
import ecarx.xsf.mediacenter.vr.IRadioIntentObserver;
import ecarx.xsf.mediacenter.vr.QRadioResult;

/* JADX INFO: loaded from: classes2.dex */
public class g extends IRadioIntentObserver.Stub {
    @Override // ecarx.xsf.mediacenter.vr.IRadioIntentObserver
    public boolean handleCtrlApp(int i) throws RemoteException {
        return false;
    }

    @Override // ecarx.xsf.mediacenter.vr.IRadioIntentObserver
    public boolean handleCtrlMediaClient(int i, int i2) throws RemoteException {
        return false;
    }

    @Override // ecarx.xsf.mediacenter.vr.IRadioIntentObserver
    public boolean handlePlayRadio(QRadioResult qRadioResult) throws RemoteException {
        return false;
    }
}
