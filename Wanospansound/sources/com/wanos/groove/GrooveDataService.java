package com.wanos.groove;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/* JADX INFO: loaded from: classes3.dex */
public class GrooveDataService extends Service {
    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return new GrooveDataBinder();
    }
}
