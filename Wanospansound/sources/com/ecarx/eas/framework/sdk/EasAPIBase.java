package com.ecarx.eas.framework.sdk;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Process;
import android.os.RemoteException;
import com.ecarx.eas.framework.sdk.common.exception.EASFrameworkException;
import com.ecarx.eas.framework.sdk.common.internal.ClientType;
import com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient;
import com.ecarx.eas.framework.sdk.common.internal.IApi;
import com.ecarx.eas.framework.sdk.common.internal.IEASFrameworkService;
import com.ecarx.eas.framework.sdk.common.internal.IServicePool;
import com.ecarx.eas.framework.sdk.common.internal.safeparcel.ServiceVersionInfo;
import com.ecarx.eas.framework.sdk.common.internal.safeparcel.VersionInfo;
import com.ecarx.eas.sdk.ECarXApiClient;

/* JADX INFO: loaded from: classes2.dex */
public abstract class EasAPIBase extends ECarXAPIBase {
    private final String TAG = "EasAPIBase";

    protected abstract IInterface asInterface(String str, IBinder iBinder);

    protected abstract IApi createAPIImpl(String str, ClientType clientType, ServiceVersionInfo serviceVersionInfo);

    protected abstract String getServiceName();

    @Override // com.ecarx.eas.framework.sdk.ECarXAPIBase
    public void init(Context context, ECarXApiClient.Callback callback) {
        super.init(context, callback);
        String serviceName = getServiceName();
        if (serviceName == null || serviceName.equals("")) {
            onAPIReady(callback, false);
            return;
        }
        try {
            EASFrameworkApiClient.getInstance().init(context, callback, serviceName, new EASFrameworkApiClient.IServiceConnectionCallback() { // from class: com.ecarx.eas.framework.sdk.EasAPIBase.1
                @Override // com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient.IServiceConnectionCallback
                public final boolean onConnected(String str, ClientType clientType, boolean z) {
                    return EasAPIBase.this.onConnected(str, clientType, null, z);
                }

                @Override // com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient.IServiceConnectionCallback
                public final boolean onConnected(String str, ClientType clientType, ServiceVersionInfo serviceVersionInfo, boolean z) {
                    return EasAPIBase.this.onConnected(str, clientType, serviceVersionInfo, z);
                }
            });
        } catch (EASFrameworkException e) {
            e.printStackTrace();
            onAPIReady(callback, false);
        }
    }

    private void onAPIReady(ECarXApiClient.Callback callback, boolean z) {
        if (callback != null) {
            callback.onAPIReady(z);
        }
    }

    protected ClientType asServiceClientType(ClientType clientType, ServiceVersionInfo serviceVersionInfo) {
        if (serviceVersionInfo == null || serviceVersionInfo.versionInfos == null) {
            return ClientType.OpenAPI;
        }
        for (VersionInfo versionInfo : serviceVersionInfo.versionInfos) {
            if (2 == versionInfo.type || 4 == versionInfo.type) {
                return ClientType.EASFramework;
            }
        }
        return ClientType.OpenAPI;
    }

    private IInterface getServiceBinder(String str, IInterface iInterface) throws RemoteException {
        IBinder service;
        if (iInterface == null) {
            this.L.e(this.TAG, "getServiceBinder service == null");
            return null;
        }
        if (iInterface instanceof IServicePool) {
            service = ((IServicePool) iInterface).getService(Process.myPid(), Process.myUid(), this.context.getPackageName(), getServiceName());
        } else {
            service = iInterface instanceof IEASFrameworkService ? ((IEASFrameworkService) iInterface).getService(Process.myPid(), Process.myUid(), this.context.getPackageName(), getServiceName()) : null;
        }
        if (service == null) {
            return null;
        }
        return asInterface(str, service);
    }

    protected boolean onConnected(String str, ClientType clientType, ServiceVersionInfo serviceVersionInfo, boolean z) {
        if (!z) {
            return false;
        }
        IInterface eASServiceManager = null;
        try {
            if (ClientType.OpenAPI == clientType) {
                eASServiceManager = getServiceBinder(str, EASFrameworkApiClient.getInstance().getServiceManager());
            } else {
                eASServiceManager = EASFrameworkApiClient.getInstance().getEASServiceManager();
                if (ClientType.OpenAPI == asServiceClientType(clientType, serviceVersionInfo)) {
                    eASServiceManager = getServiceBinder(str, eASServiceManager);
                }
            }
        } catch (DeadObjectException e) {
            e.printStackTrace();
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
        if (eASServiceManager == null) {
            this.L.e(this.TAG, "onConnected iInterface == null");
            return false;
        }
        IApi iApiCreateAPIImpl = createAPIImpl(str, clientType, serviceVersionInfo);
        if (iApiCreateAPIImpl == null) {
            this.L.e(this.TAG, "onConnected apiImpl == null");
            return false;
        }
        iApiCreateAPIImpl.init(eASServiceManager);
        return true;
    }
}
