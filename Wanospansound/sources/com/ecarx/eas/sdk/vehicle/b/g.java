package com.ecarx.eas.sdk.vehicle.b;

import android.os.RemoteException;
import android.util.Log;
import com.ecarx.eas.framework.sdk.EASCallUtils;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.framework.sdk.proto.MessageNano;
import com.ecarx.eas.sdk.IServiceManager;
import com.ecarx.eas.sdk.vehicle.api.carinfo.audio.ICarAudioManager;
import com.ecarx.eas.sdk.vehicle.api.carinfo.audio.ICarVolumeChangeCallback;
import com.ecarx.eas.sdk.vehicle.v3.b.b.a.b;
import com.ecarx.openapi.protobuf.ECARXCommon;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes2.dex */
public class g implements ICarAudioManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected volatile IEASFrameworkService f199a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f200b = g.class.getName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ConcurrentHashMap<Integer, a> f201c = new ConcurrentHashMap<>();

    public g(IEASFrameworkService iEASFrameworkService) {
        this.f199a = iEASFrameworkService;
    }

    public final void a(IEASFrameworkService iEASFrameworkService) {
        Collection<a> collectionValues;
        this.f199a = iEASFrameworkService;
        if (this.f201c.isEmpty() || (collectionValues = this.f201c.values()) == null || collectionValues.isEmpty()) {
            return;
        }
        Iterator<a> it = collectionValues.iterator();
        while (it.hasNext()) {
            try {
                if (!EASCallUtils.callBoolean(this.f199a, IServiceManager.SERVICE_VEHICLE, "CarInfo", "registerCarVolumeChangeCallback", null, it.next())) {
                    Log.d(this.f200b, "registerCarVolumeChangeCallback update false");
                }
            } catch (Throwable th) {
                th.printStackTrace();
                Log.d(this.f200b, "registerCarVolumeChangeCallback update false");
            }
        }
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.carinfo.audio.ICarAudioManager
    public int getUsageMaxVolume(String str) {
        if (this.f199a == null) {
            return -1;
        }
        ECARXCommon.StringMsg stringMsg = new ECARXCommon.StringMsg();
        stringMsg.value = str;
        return EASCallUtils.callInt(this.f199a, IServiceManager.SERVICE_VEHICLE, "CarInfo", "getUsageMaxVolume", MessageNano.toByteArray(stringMsg), null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.carinfo.audio.ICarAudioManager
    public int getUsageMinVolume(String str) {
        if (this.f199a == null) {
            return -1;
        }
        ECARXCommon.StringMsg stringMsg = new ECARXCommon.StringMsg();
        stringMsg.value = str;
        return EASCallUtils.callInt(this.f199a, IServiceManager.SERVICE_VEHICLE, "CarInfo", "getUsageMinVolume", MessageNano.toByteArray(stringMsg), null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.carinfo.audio.ICarAudioManager
    public int getUsageVolume(String str) {
        if (this.f199a == null) {
            return -1;
        }
        ECARXCommon.StringMsg stringMsg = new ECARXCommon.StringMsg();
        stringMsg.value = str;
        return EASCallUtils.callInt(this.f199a, IServiceManager.SERVICE_VEHICLE, "CarInfo", "getUsageVolume", MessageNano.toByteArray(stringMsg), null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.carinfo.audio.ICarAudioManager
    public boolean registerCarVolumeChangeCallback(ICarVolumeChangeCallback iCarVolumeChangeCallback) {
        if (iCarVolumeChangeCallback == null || this.f199a == null) {
            return false;
        }
        int iHashCode = iCarVolumeChangeCallback.hashCode();
        if (this.f201c.get(Integer.valueOf(iHashCode)) == null) {
            a aVar = new a(iCarVolumeChangeCallback);
            try {
                if (!EASCallUtils.callBoolean(this.f199a, IServiceManager.SERVICE_VEHICLE, "CarInfo", "registerCarVolumeChangeCallback", null, aVar)) {
                    Log.d(this.f200b, "registerCarVolumeChangeCallback false");
                    return false;
                }
                this.f201c.put(Integer.valueOf(iHashCode), aVar);
            } catch (Throwable th) {
                th.printStackTrace();
                Log.d(this.f200b, "registerCarVolumeChangeCallback false");
                return false;
            }
        }
        Log.d(this.f200b, "registerCarVolumeChangeCallback true");
        return true;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.carinfo.audio.ICarAudioManager
    public boolean unRegisterCarVolumeChangeCallback(ICarVolumeChangeCallback iCarVolumeChangeCallback) {
        if (iCarVolumeChangeCallback != null && this.f199a != null) {
            int iHashCode = iCarVolumeChangeCallback.hashCode();
            a aVar = this.f201c.get(Integer.valueOf(iHashCode));
            if (aVar != null) {
                try {
                    if (EASCallUtils.callBoolean(this.f199a, IServiceManager.SERVICE_VEHICLE, "CarInfo", "unRegisterCarVolumeChangeCallback", null, aVar)) {
                        this.f201c.remove(Integer.valueOf(iHashCode));
                        Log.d(this.f200b, "unRegisterCarVolumeChangeCallback true");
                        return true;
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                    return false;
                }
            }
            Log.d(this.f200b, "unRegisterCarVolumeChangeCallback false");
        }
        return false;
    }

    static class a extends b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private ICarVolumeChangeCallback f202a;

        public a(ICarVolumeChangeCallback iCarVolumeChangeCallback) {
            this.f202a = iCarVolumeChangeCallback;
        }

        @Override // com.ecarx.eas.sdk.vehicle.v3.b.b.a.b
        public final void a(String str, int i) throws RemoteException {
            this.f202a.onUsageVolumeChanged(str, i);
        }
    }
}
