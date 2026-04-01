package com.ecarx.eas.sdk.mediacenter.a.a;

import android.os.RemoteException;
import com.ecarx.eas.sdk.vr.channel.VrChannelDataListener;
import ecarx.xsf.mediacenter.vr.channel.IVrChannelObserver;

/* JADX INFO: loaded from: classes2.dex */
public final class a extends IVrChannelObserver.Stub {
    @Override // ecarx.xsf.mediacenter.vr.channel.IVrChannelObserver
    public final void handleVrChannelSemantic(int i, int i2, String str) throws RemoteException {
    }

    public a(VrChannelDataListener vrChannelDataListener) {
    }
}
