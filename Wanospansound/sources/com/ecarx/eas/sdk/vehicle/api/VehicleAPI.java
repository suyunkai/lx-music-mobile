package com.ecarx.eas.sdk.vehicle.api;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import com.ecarx.eas.framework.sdk.common.internal.ClientType;
import com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient;
import com.ecarx.eas.framework.sdk.common.internal.IApi;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.framework.sdk.common.internal.IServicePool;
import com.ecarx.eas.sdk.IServiceManager;
import com.ecarx.eas.sdk.vehicle.f.B;
import com.ecarx.eas.sdk.vehicle.f.g;
import com.ecarx.eas.sdk.vehicle.v3.e;

/* JADX INFO: loaded from: classes2.dex */
public abstract class VehicleAPI extends b implements InternalVehicleAPI {
    public static final String TAG = "VehicleAPI";
    protected InternalVehicleAPI mApi;

    public static VehicleAPI get(Context context) {
        return com.ecarx.eas.sdk.vehicle.a.a();
    }

    @Deprecated
    public static VehicleAPI createVehicleAPI(Context context) {
        return com.ecarx.eas.sdk.vehicle.a.a();
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.b
    public IVehicleAPI initVehicle(ClientType clientType) {
        InternalVehicleAPI internalVehicleAPIB;
        if (clientType == ClientType.OpenAPI) {
            internalVehicleAPIB = B.b();
        } else {
            internalVehicleAPIB = g.b();
        }
        this.mApi = internalVehicleAPIB;
        if (clientType == ClientType.OpenAPI) {
            try {
                IServicePool serviceManager = EASFrameworkApiClient.getInstance().getServiceManager();
                if (serviceManager == null) {
                    this.L.e(TAG, "servicePool == null");
                    return this.mApi;
                }
                IBinder service = serviceManager.getService(Process.myPid(), Process.myUid(), this.context.getPackageName(), IServiceManager.SERVICE_VEHICLE);
                if (service == null) {
                    this.L.e(TAG, "binder == null");
                    return this.mApi;
                }
                e eVarA = e.a.a(service);
                Object obj = this.mApi;
                if (obj instanceof IApi) {
                    ((IApi) obj).init(eVarA);
                }
            } catch (DeadObjectException e) {
                e.printStackTrace();
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        } else {
            try {
                IEASFrameworkService eASServiceManager = EASFrameworkApiClient.getInstance().getEASServiceManager();
                if (eASServiceManager == null) {
                    this.L.e(TAG, "ieasFrameworkService == null");
                    return this.mApi;
                }
                Object obj2 = this.mApi;
                if (obj2 instanceof IApi) {
                    ((IApi) obj2).init(eASServiceManager);
                }
            } catch (DeadObjectException e3) {
                e3.printStackTrace();
            }
        }
        return this.mApi;
    }
}
