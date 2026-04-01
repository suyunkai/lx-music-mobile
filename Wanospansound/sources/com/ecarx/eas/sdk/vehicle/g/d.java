package com.ecarx.eas.sdk.vehicle.g;

import android.os.RemoteException;
import android.util.Log;
import com.ecarx.eas.framework.sdk.EASCallUtils;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.framework.sdk.proto.MessageNano;
import com.ecarx.eas.sdk.IServiceManager;
import com.ecarx.eas.sdk.vehicle.api.newenergy.IEpt;
import com.ecarx.eas.sdk.vehicle.api.newenergy.IEptState;
import com.ecarx.eas.sdk.vehicle.v3.a.i;
import com.ecarx.openapi.protobuf.ECARXCommon;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class d implements IEpt {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private c f278b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private IEASFrameworkService f280d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f277a = "d";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private HashMap<IEpt.IEptStateListener, a> f279c = new HashMap<>();

    public d(IEASFrameworkService iEASFrameworkService) {
        this.f280d = iEASFrameworkService;
        this.f278b = new c(iEASFrameworkService);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IEpt
    public IEptState getEptState() {
        return this.f278b;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IEpt
    public void setSOCPointLevel(int i) {
        if (this.f280d == null) {
            return;
        }
        ECARXCommon.IntMsg intMsg = new ECARXCommon.IntMsg();
        intMsg.value = i;
        EASCallUtils.call(this.f280d, IServiceManager.SERVICE_VEHICLE, "NewEnergyEpt", "setSOCPointLevel", MessageNano.toByteArray(intMsg), null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IEpt
    public int getSOCPointLevelMax() {
        IEASFrameworkService iEASFrameworkService = this.f280d;
        if (iEASFrameworkService == null) {
            return -1;
        }
        return EASCallUtils.callInt(iEASFrameworkService, IServiceManager.SERVICE_VEHICLE, "NewEnergyEpt", "getSOCPointLevelMax", null, null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IEpt
    public int getSOCPointLevelMin() {
        IEASFrameworkService iEASFrameworkService = this.f280d;
        if (iEASFrameworkService == null) {
            return -1;
        }
        return EASCallUtils.callInt(iEASFrameworkService, IServiceManager.SERVICE_VEHICLE, "NewEnergyEpt", "getSOCPointLevelMin", null, null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IEpt
    public int getSOCPointLevelStep() {
        IEASFrameworkService iEASFrameworkService = this.f280d;
        if (iEASFrameworkService == null) {
            return -1;
        }
        return EASCallUtils.callInt(iEASFrameworkService, IServiceManager.SERVICE_VEHICLE, "NewEnergyEpt", "getSOCPointLevelStep", null, null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IEpt
    public void setAvasDisable(boolean z) {
        if (this.f280d == null) {
            return;
        }
        ECARXCommon.BoolMsg boolMsg = new ECARXCommon.BoolMsg();
        boolMsg.value = z;
        EASCallUtils.call(this.f280d, IServiceManager.SERVICE_VEHICLE, "NewEnergyEpt", "setAvasDisable", MessageNano.toByteArray(boolMsg), null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IEpt
    public void setAvasVolumeType(int i) {
        if (this.f280d == null) {
            return;
        }
        ECARXCommon.IntMsg intMsg = new ECARXCommon.IntMsg();
        intMsg.value = i;
        EASCallUtils.call(this.f280d, IServiceManager.SERVICE_VEHICLE, "NewEnergyEpt", "setAvasVolumeType", MessageNano.toByteArray(intMsg), null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IEpt
    public void setInfrequentChargingMode(boolean z) {
        if (this.f280d == null) {
            return;
        }
        ECARXCommon.BoolMsg boolMsg = new ECARXCommon.BoolMsg();
        boolMsg.value = z;
        EASCallUtils.call(this.f280d, IServiceManager.SERVICE_VEHICLE, "NewEnergyEpt", "setInfrequentChargingMode", MessageNano.toByteArray(boolMsg), null);
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IEpt
    public void registerStateListener(IEpt.IEptStateListener iEptStateListener) {
        if (this.f280d == null) {
            return;
        }
        if (this.f279c.get(iEptStateListener) == null) {
            a aVar = new a(iEptStateListener, this.f280d);
            EASCallUtils.call(this.f280d, IServiceManager.SERVICE_VEHICLE, "NewEnergyEpt", "registerStateListener", null, aVar);
            this.f279c.put(iEptStateListener, aVar);
        }
        Log.d(this.f277a, "Has register IEptStateListener");
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.newenergy.IEpt
    public void unregisterStateListener(IEpt.IEptStateListener iEptStateListener) {
        a aVar;
        if (this.f280d == null || (aVar = this.f279c.get(iEptStateListener)) == null) {
            return;
        }
        EASCallUtils.call(this.f280d, IServiceManager.SERVICE_VEHICLE, "NewEnergyEpt", "unregisterStateListener", null, aVar);
        this.f279c.remove(iEptStateListener);
    }

    static class a extends i.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private IEpt.IEptStateListener f281a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private IEASFrameworkService f282b;

        public a(IEpt.IEptStateListener iEptStateListener, IEASFrameworkService iEASFrameworkService) {
            this.f281a = iEptStateListener;
            this.f282b = iEASFrameworkService;
        }

        @Override // com.ecarx.eas.sdk.vehicle.v3.a.i
        public final void a(com.ecarx.eas.sdk.vehicle.v3.a.h hVar) throws RemoteException {
            this.f281a.onEptStateChanged(new c(this.f282b));
        }
    }
}
