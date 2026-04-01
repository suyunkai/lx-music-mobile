package com.ecarx.openapi.sdk.carproperty;

import android.content.Context;
import android.util.Log;
import com.ecarx.eas.framework.sdk.ECarXAPIBase;
import com.ecarx.eas.framework.sdk.Singleton;
import com.ecarx.eas.framework.sdk.common.exception.EASFrameworkException;
import com.ecarx.eas.framework.sdk.common.exception.NotSupportedException;
import com.ecarx.eas.framework.sdk.common.internal.ClientType;
import com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.framework.sdk.common.internal.safeparcel.ServiceVersionInfo;
import com.ecarx.eas.sdk.ECarXApiClient;
import com.ecarx.openapi.sdk.carproperty.impl.a;

/* JADX INFO: loaded from: classes2.dex */
public class CarPropertyAPI extends ECarXAPIBase implements ICarProperty {
    private static final String TAG = "CarPropertyAPI";
    private static final Singleton<CarPropertyAPI> singleton = new Singleton<CarPropertyAPI>() { // from class: com.ecarx.openapi.sdk.carproperty.CarPropertyAPI.1
        @Override // com.ecarx.eas.framework.sdk.Singleton
        protected /* synthetic */ CarPropertyAPI create() {
            return new CarPropertyAPI();
        }
    };
    private final EASFrameworkApiClient.IServiceConnectionCallback connectionCallback;
    private a impl;

    private CarPropertyAPI() {
        this.connectionCallback = new EASFrameworkApiClient.IServiceConnectionCallback() { // from class: com.ecarx.openapi.sdk.carproperty.CarPropertyAPI.2
            @Override // com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient.IServiceConnectionCallback
            public final boolean onConnected(String str, ClientType clientType, boolean z) {
                Log.d(CarPropertyAPI.TAG, "onConnected() called with: service = [" + str + "], clientType = [" + clientType + "], isConnected = [" + z + "]");
                return false;
            }

            @Override // com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient.IServiceConnectionCallback
            public final boolean onConnected(String str, ClientType clientType, ServiceVersionInfo serviceVersionInfo, boolean z) {
                Log.d(CarPropertyAPI.TAG, "onConnected() called with: service = [" + str + "], openAPIServiceType = [" + clientType + "], serviceVersionInfo = [" + serviceVersionInfo + "], isConnected = [" + z + "]");
                if (!"carproperty".equals(str) || clientType != ClientType.EASFramework || serviceVersionInfo == null || !z) {
                    Log.w(CarPropertyAPI.TAG, "onConnected return false");
                    return false;
                }
                try {
                    IEASFrameworkService eASServiceManager = EASFrameworkApiClient.getInstance().getEASServiceManager();
                    if (eASServiceManager == null) {
                        Log.w(CarPropertyAPI.TAG, "onConnected: easFrameworkService is null");
                        return false;
                    }
                    CarPropertyAPI carPropertyAPI = CarPropertyAPI.this;
                    Context unused = carPropertyAPI.context;
                    carPropertyAPI.impl = a.a();
                    CarPropertyAPI.this.impl.init(eASServiceManager);
                    return true;
                } catch (Throwable th) {
                    th.printStackTrace();
                    return false;
                }
            }
        };
    }

    public static CarPropertyAPI get(Context context) {
        return singleton.get();
    }

    @Override // com.ecarx.eas.framework.sdk.ECarXAPIBase
    public void init(Context context, ECarXApiClient.Callback callback) {
        Log.d(TAG, "init() called");
        super.init(context, callback);
        try {
            EASFrameworkApiClient.getInstance().init(context, callback, "carproperty", this.connectionCallback);
        } catch (EASFrameworkException e) {
            e.printStackTrace();
        }
    }

    @Override // com.ecarx.openapi.sdk.carproperty.ICarProperty
    public int getPropertyId(int i, int i2) throws EASFrameworkException {
        a aVar = this.impl;
        if (aVar == null) {
            throw new NotSupportedException("impl is null");
        }
        return aVar.getPropertyId(i, i2);
    }

    @Override // com.ecarx.openapi.sdk.carproperty.ICarProperty
    public int getPropertyValue(int i, int i2, int i3) throws EASFrameworkException {
        a aVar = this.impl;
        if (aVar == null) {
            throw new NotSupportedException("impl is null");
        }
        return aVar.getPropertyValue(i, i2, i3);
    }

    @Override // com.ecarx.openapi.sdk.carproperty.ICarProperty
    public int getPropertyAdaptValue(int i, int i2, int i3) throws EASFrameworkException {
        a aVar = this.impl;
        if (aVar == null) {
            throw new NotSupportedException("impl is null");
        }
        return aVar.getPropertyAdaptValue(i, i2, i3);
    }
}
