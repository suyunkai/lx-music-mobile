package com.ecarx.eas.sdk.device;

import android.os.RemoteException;
import com.ecarx.eas.sdk.device.a.a.d;
import com.ecarx.eas.sdk.device.api.JoyLimitListener;

/* JADX INFO: loaded from: classes2.dex */
final class m extends d.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private JoyLimitListener f93a;

    @Override // com.ecarx.eas.sdk.device.a.a.d
    public final void a(int i) throws RemoteException {
    }

    public m(JoyLimitListener joyLimitListener) {
        this.f93a = joyLimitListener;
    }

    @Override // com.ecarx.eas.sdk.device.a.a.d
    public final void a(int i, int i2) throws RemoteException {
        JoyLimitListener joyLimitListener = this.f93a;
        if (joyLimitListener == null) {
            return;
        }
        joyLimitListener.onJoyLimitStateChanged(i, i2);
    }
}
