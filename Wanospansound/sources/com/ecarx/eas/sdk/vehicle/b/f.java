package com.ecarx.eas.sdk.vehicle.b;

import android.os.RemoteException;
import android.util.Log;
import com.ecarx.eas.sdk.vehicle.api.carinfo.audio.ICarAudioManager;
import com.ecarx.eas.sdk.vehicle.api.carinfo.audio.ICarVolumeChangeCallback;
import com.ecarx.eas.sdk.vehicle.v3.b.b.a.b;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes2.dex */
public class f implements ICarAudioManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected volatile com.ecarx.eas.sdk.vehicle.v3.b.b.a.a f195a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f196b = f.class.getName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ConcurrentHashMap<Integer, a> f197c = new ConcurrentHashMap<>();

    public f(com.ecarx.eas.sdk.vehicle.v3.b.b.a.a aVar) {
        this.f195a = aVar;
    }

    public final void a(com.ecarx.eas.sdk.vehicle.v3.b.b.a.a aVar) {
        Collection<a> collectionValues;
        this.f195a = aVar;
        if (this.f197c.isEmpty() || (collectionValues = this.f197c.values()) == null || collectionValues.isEmpty()) {
            return;
        }
        Iterator<a> it = collectionValues.iterator();
        while (it.hasNext()) {
            try {
                if (!this.f195a.a(it.next())) {
                    Log.d(this.f196b, "V3 registerCarVolumeChangeCallback update false");
                }
            } catch (Throwable th) {
                th.printStackTrace();
                Log.d(this.f196b, "V3 registerCarVolumeChangeCallback update false");
            }
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.carinfo.audio.ICarAudioManager
    public int getUsageMaxVolume(String str) {
        try {
            return this.f195a.a(str);
        } catch (Throwable th) {
            th.printStackTrace();
            return -1;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.carinfo.audio.ICarAudioManager
    public int getUsageMinVolume(String str) {
        try {
            return this.f195a.b(str);
        } catch (Throwable th) {
            th.printStackTrace();
            return -1;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.carinfo.audio.ICarAudioManager
    public int getUsageVolume(String str) {
        try {
            return this.f195a.c(str);
        } catch (Throwable th) {
            th.printStackTrace();
            return -1;
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.carinfo.audio.ICarAudioManager
    public boolean registerCarVolumeChangeCallback(ICarVolumeChangeCallback iCarVolumeChangeCallback) {
        if (iCarVolumeChangeCallback == null) {
            return false;
        }
        int iHashCode = iCarVolumeChangeCallback.hashCode();
        if (this.f197c.get(Integer.valueOf(iHashCode)) == null) {
            a aVar = new a(iCarVolumeChangeCallback);
            try {
                if (!this.f195a.a(aVar)) {
                    Log.d(this.f196b, "registerCarVolumeChangeCallback false");
                    return false;
                }
                this.f197c.put(Integer.valueOf(iHashCode), aVar);
            } catch (Throwable th) {
                th.printStackTrace();
                Log.d(this.f196b, "registerCarVolumeChangeCallback false");
                return false;
            }
        }
        Log.d(this.f196b, "registerCarVolumeChangeCallback true");
        return true;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.carinfo.audio.ICarAudioManager
    public boolean unRegisterCarVolumeChangeCallback(ICarVolumeChangeCallback iCarVolumeChangeCallback) {
        if (iCarVolumeChangeCallback == null) {
            return false;
        }
        int iHashCode = iCarVolumeChangeCallback.hashCode();
        a aVar = this.f197c.get(Integer.valueOf(iHashCode));
        if (aVar != null) {
            try {
                if (this.f195a.b(aVar)) {
                    this.f197c.remove(Integer.valueOf(iHashCode));
                    Log.d(this.f196b, "unRegisterCarVolumeChangeCallback true");
                    return true;
                }
            } catch (Throwable th) {
                th.printStackTrace();
                return false;
            }
        }
        Log.d(this.f196b, "unRegisterCarVolumeChangeCallback false");
        return false;
    }

    static class a extends b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private ICarVolumeChangeCallback f198a;

        public a(ICarVolumeChangeCallback iCarVolumeChangeCallback) {
            this.f198a = iCarVolumeChangeCallback;
        }

        @Override // com.ecarx.eas.sdk.vehicle.v3.b.b.a.b
        public final void a(String str, int i) throws RemoteException {
            this.f198a.onUsageVolumeChanged(str, i);
        }
    }
}
