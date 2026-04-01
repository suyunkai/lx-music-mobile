package com.ecarx.eas.sdk.vehicle.f;

import android.os.RemoteException;
import android.util.Log;
import com.ecarx.eas.framework.sdk.EASCallUtils;
import com.ecarx.eas.framework.sdk.common.internal.ClientType;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.framework.sdk.proto.MessageNano;
import com.ecarx.eas.sdk.IServiceManager;
import com.ecarx.eas.sdk.vehicle.FunctionStatus;
import com.ecarx.eas.sdk.vehicle.api.audio.IAudio;
import com.ecarx.eas.sdk.vehicle.api.audio.ICaeSwitchChangeCallback;
import com.ecarx.eas.sdk.vehicle.v3.b.a.b;
import com.ecarx.openapi.protobuf.ECARXCommon;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class q implements IAudio {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected IEASFrameworkService f233a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f234b = getClass().getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private HashMap<ICaeSwitchChangeCallback, a> f235c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private com.ecarx.eas.sdk.vehicle.api.a f236d;

    public q(com.ecarx.eas.sdk.vehicle.api.a aVar) {
        this.f236d = aVar;
    }

    public final void a(IEASFrameworkService iEASFrameworkService) {
        this.f233a = iEASFrameworkService;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.audio.IAudio
    public FunctionStatus isCaeSwitchSupported() {
        IEASFrameworkService iEASFrameworkService = this.f233a;
        if (iEASFrameworkService == null) {
            return FunctionStatus.error;
        }
        int iCallInt = EASCallUtils.callInt(iEASFrameworkService, IServiceManager.SERVICE_VEHICLE, "Audio", "isCaeSwitchSupported", null, null);
        if (iCallInt == 0) {
            return FunctionStatus.active;
        }
        if (iCallInt == 1) {
            return FunctionStatus.notactive;
        }
        if (iCallInt == 2) {
            return FunctionStatus.notavailable;
        }
        return FunctionStatus.error;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.audio.IAudio
    public int getCaeSwitchValue() {
        IEASFrameworkService iEASFrameworkService = this.f233a;
        if (iEASFrameworkService == null) {
            return Integer.MIN_VALUE;
        }
        return EASCallUtils.callInt(iEASFrameworkService, IServiceManager.SERVICE_VEHICLE, "Audio", "getCaeSwitchValue", null, null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.audio.IAudio
    public boolean setCaeSwitchValue(int i) {
        if (this.f233a == null) {
            Log.e(this.f234b, "setCaeSwitchValue  mService =  " + this.f233a + "  value = " + i);
            return false;
        }
        ECARXCommon.IntMsg intMsg = new ECARXCommon.IntMsg();
        intMsg.value = i;
        return EASCallUtils.callBoolean(this.f233a, IServiceManager.SERVICE_VEHICLE, "Audio", "setCaeSwitchValue", MessageNano.toByteArray(intMsg), null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.audio.IAudio
    public boolean registerCaeSwitchValueWatcher(ICaeSwitchChangeCallback iCaeSwitchChangeCallback) {
        if (this.f233a == null || iCaeSwitchChangeCallback == null) {
            return false;
        }
        if (this.f235c == null) {
            this.f235c = new HashMap<>();
        }
        if (this.f235c.get(iCaeSwitchChangeCallback) == null) {
            a aVar = new a(iCaeSwitchChangeCallback);
            boolean zCallBoolean = EASCallUtils.callBoolean(this.f233a, IServiceManager.SERVICE_VEHICLE, "Audio", "registerCaeSwitchValueWatcher", null, aVar);
            if (zCallBoolean) {
                this.f235c.put(iCaeSwitchChangeCallback, aVar);
            }
            Log.d(this.f234b, "Has register registerCaeSwitchValueWatcher" + zCallBoolean);
            return zCallBoolean;
        }
        Log.d(this.f234b, "Has register ISensorListener");
        return true;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.audio.IAudio
    public boolean unregisterCaeSwitchValueWatcher(ICaeSwitchChangeCallback iCaeSwitchChangeCallback) {
        HashMap<ICaeSwitchChangeCallback, a> map;
        a aVar;
        if (this.f233a == null || iCaeSwitchChangeCallback == null || (map = this.f235c) == null || (aVar = map.get(iCaeSwitchChangeCallback)) == null) {
            return false;
        }
        this.f235c.remove(iCaeSwitchChangeCallback);
        EASCallUtils.call(this.f233a, IServiceManager.SERVICE_VEHICLE, "Audio", "unregisterCaeSwitchValueWatcher", null, aVar);
        return true;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.audio.IAudio
    public int getAudioProvider() {
        com.ecarx.eas.sdk.vehicle.api.a aVar;
        if (this.f233a == null || (aVar = this.f236d) == null || !aVar.a(ClientType.EASFramework, 6)) {
            return Integer.MIN_VALUE;
        }
        return EASCallUtils.callInt(this.f233a, IServiceManager.SERVICE_VEHICLE, "Audio", "getAudioProvider", null, null);
    }

    static class a extends b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private ICaeSwitchChangeCallback f237a;

        public a(ICaeSwitchChangeCallback iCaeSwitchChangeCallback) {
            this.f237a = iCaeSwitchChangeCallback;
        }

        @Override // com.ecarx.eas.sdk.vehicle.v3.b.a.b
        public final void a(int i) throws RemoteException {
            ICaeSwitchChangeCallback iCaeSwitchChangeCallback = this.f237a;
            if (iCaeSwitchChangeCallback != null) {
                iCaeSwitchChangeCallback.onCaeSwitchChanged(i);
            }
        }
    }
}
