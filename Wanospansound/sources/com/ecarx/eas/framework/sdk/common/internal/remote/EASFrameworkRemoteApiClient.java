package com.ecarx.eas.framework.sdk.common.internal.remote;

import android.content.Context;
import android.content.Intent;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.ecarx.eas.framework.sdk.Singleton;
import com.ecarx.eas.framework.sdk.common.exception.EASFrameworkException;
import com.ecarx.eas.framework.sdk.common.exception.IllegalArgumentEASException;
import com.ecarx.eas.framework.sdk.common.internal.Constant;
import com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient;
import com.ecarx.eas.framework.sdk.common.internal.safeparcel.RemoteDevicesInfo;
import com.ecarx.eas.framework.sdk.common.wrappers.PackageManagerWrapper;
import com.ecarx.eas.sdk.ECarXApiClient;
import com.ecarx.sdk.openapi.msg.EASFrameworkMessageRemote;
import com.ecarx.sdk.openapi.msg.EASFrameworkRetMessageRemote;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes2.dex */
public final class EASFrameworkRemoteApiClient {
    private volatile Context mContext;
    private final AtomicInteger mCurrentServiceTwice;
    private Intent mEASFrameworkIntent;
    private final a mH;
    private EASFrameworkApiClient.b mNotifity;
    private PackageManagerWrapper mPackageWrapper;
    private b remoteClient;
    private static Map<ECarXApiClient.RemoteCallback, String> mCallbacks = new ConcurrentHashMap();
    private static Map<String, IServiceNoticeImplCallback> mConnectionCallbacks = new ConcurrentHashMap();
    private static Singleton<EASFrameworkRemoteApiClient> gProxy = new Singleton<EASFrameworkRemoteApiClient>() { // from class: com.ecarx.eas.framework.sdk.common.internal.remote.EASFrameworkRemoteApiClient.2
        @Override // com.ecarx.eas.framework.sdk.Singleton
        protected final /* synthetic */ EASFrameworkRemoteApiClient create() {
            return new EASFrameworkRemoteApiClient();
        }
    };

    private EASFrameworkRemoteApiClient() {
        this.mCurrentServiceTwice = new AtomicInteger(0);
        this.mEASFrameworkIntent = new Intent("com.ecarx.easframework.intent.action.EASFRAMEWORKREMOTE").setPackage("com.ecarx.sdk.openapi");
        this.mNotifity = new EASFrameworkApiClient.b() { // from class: com.ecarx.eas.framework.sdk.common.internal.remote.EASFrameworkRemoteApiClient.1
            @Override // com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient.b
            public final void a() {
                if (EASFrameworkRemoteApiClient.this.remoteClient == null) {
                    return;
                }
                EASFrameworkRemoteApiClient.this.mCurrentServiceTwice.set(0);
                EASFrameworkRemoteApiClient.this.mH.removeMessages(1);
                EASFrameworkRemoteApiClient.this.mH.sendMessage(EASFrameworkRemoteApiClient.this.mH.obtainMessage(1));
            }

            @Override // com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient.b
            public final void b() {
                EASFrameworkRemoteApiClient.this.mH.removeMessages(2);
                EASFrameworkRemoteApiClient.this.mH.removeMessages(1);
                EASFrameworkRemoteApiClient.this.mH.removeMessages(3);
                EASFrameworkRemoteApiClient.this.mH.sendMessage(EASFrameworkRemoteApiClient.this.mH.obtainMessage(2));
            }

            @Override // com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient.b
            public final void c() {
                EASFrameworkRemoteApiClient.this.mH.removeMessages(2);
                EASFrameworkRemoteApiClient.this.mH.removeMessages(1);
                EASFrameworkRemoteApiClient.this.mH.removeMessages(3);
                EASFrameworkRemoteApiClient.this.mH.sendMessage(EASFrameworkRemoteApiClient.this.mH.obtainMessage(3));
            }

            @Override // com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient.b
            public final void a(String str) {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                Log.d(Constant.TAG, "onServiceConnected() service = " + str);
                EASFrameworkRemoteApiClient.this.mH.removeMessages(4, str);
                EASFrameworkRemoteApiClient.this.mH.removeMessages(5, str);
                Message messageObtainMessage = EASFrameworkRemoteApiClient.this.mH.obtainMessage(4);
                messageObtainMessage.obj = str;
                EASFrameworkRemoteApiClient.this.mH.sendMessage(messageObtainMessage);
            }

            @Override // com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient.b
            public final void b(String str) {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                EASFrameworkRemoteApiClient.this.mH.removeMessages(4, str);
                EASFrameworkRemoteApiClient.this.mH.removeMessages(5, str);
                Message messageObtainMessage = EASFrameworkRemoteApiClient.this.mH.obtainMessage(5);
                messageObtainMessage.obj = str;
                EASFrameworkRemoteApiClient.this.mH.sendMessage(messageObtainMessage);
            }
        };
        HandlerThread handlerThread = new HandlerThread("EASFrameworkClientRemoteBg", 10);
        handlerThread.start();
        this.mH = new a(handlerThread.getLooper());
    }

    public static EASFrameworkRemoteApiClient getInstance() {
        EASFrameworkRemoteApiClient eASFrameworkRemoteApiClient;
        synchronized (EASFrameworkRemoteApiClient.class) {
            eASFrameworkRemoteApiClient = gProxy.get();
        }
        return eASFrameworkRemoteApiClient;
    }

    public final void init(Context context, ECarXApiClient.RemoteCallback remoteCallback, String str, IServiceNoticeImplCallback iServiceNoticeImplCallback) throws EASFrameworkException {
        try {
            create(context);
            b bVar = this.remoteClient;
            if (bVar == null || bVar.e()) {
                mCallbacks.put(remoteCallback, str);
                mConnectionCallbacks.put(str, iServiceNoticeImplCallback);
                connectService();
            } else if (this.remoteClient.d()) {
                mCallbacks.put(remoteCallback, str);
                mConnectionCallbacks.put(str, iServiceNoticeImplCallback);
            } else {
                mCallbacks.put(remoteCallback, str);
                mConnectionCallbacks.put(str, iServiceNoticeImplCallback);
                initConnectRemoteService(remoteCallback, str, iServiceNoticeImplCallback);
            }
        } catch (IllegalArgumentEASException e) {
            e.printStackTrace();
        }
    }

    private boolean initConnectRemoteService(ECarXApiClient.RemoteCallback remoteCallback, String str, IServiceNoticeImplCallback iServiceNoticeImplCallback) {
        try {
            IEASFrameworkRemoteService iEASFrameworkRemoteServiceF = this.remoteClient.f();
            if (iEASFrameworkRemoteServiceF == null) {
                return false;
            }
            try {
                iEASFrameworkRemoteServiceF.a(new String[]{str});
                List<String> remoteServices = iEASFrameworkRemoteServiceF.getRemoteServices();
                if (remoteServices == null || remoteServices.isEmpty()) {
                    Log.i(Constant.TAG, "RemoteServices is NULL!!!");
                    return false;
                }
                RemoteDevicesInfo serviceVersionInfo = getServiceVersionInfo(str, iEASFrameworkRemoteServiceF);
                Log.e(Constant.TAG, ">> Moudle [" + str + "] sdk 215 init result " + iServiceNoticeImplCallback.onConnected(str, serviceVersionInfo, true) + "]<<");
                if (remoteCallback != null) {
                    if (serviceVersionInfo == null) {
                        remoteCallback.onAPIReady(false, "not found service");
                    } else {
                        remoteCallback.onAPIReady(true, "");
                    }
                }
                return true;
            } catch (RemoteException e) {
                e.printStackTrace();
                return false;
            }
        } catch (DeadObjectException e2) {
            e2.printStackTrace();
            connectService();
            return false;
        } catch (NullPointerException e3) {
            e3.printStackTrace();
            connectService();
            return false;
        } catch (Exception e4) {
            e4.printStackTrace();
            connectService();
            return false;
        }
    }

    private void create(Context context) throws IllegalArgumentEASException {
        synchronized (EASFrameworkRemoteApiClient.class) {
            if (this.mContext != null) {
                return;
            }
            if (context == null) {
                throw new IllegalArgumentEASException("parameter Context must not NULL");
            }
            Context applicationContext = context.getApplicationContext();
            if (applicationContext == null) {
                this.mContext = context;
            } else {
                this.mContext = applicationContext;
            }
            this.mPackageWrapper = new PackageManagerWrapper(this.mContext);
        }
    }

    public final IEASFrameworkRemoteService getRemoteServiceManager() throws Exception {
        b bVar = this.remoteClient;
        if (bVar == null || bVar.e() || this.remoteClient.d()) {
            return null;
        }
        try {
            return this.remoteClient.f();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean connectService() {
        if (this.mPackageWrapper == null) {
            return false;
        }
        b bVar = this.remoteClient;
        if (bVar != null && bVar.d()) {
            return true;
        }
        if (this.mPackageWrapper.findService(this.mEASFrameworkIntent) != null) {
            b bVar2 = this.remoteClient;
            if (bVar2 != null) {
                bVar2.k();
            }
            this.remoteClient = new b(this.mContext, this.mPackageWrapper, this.mNotifity);
            this.mCurrentServiceTwice.getAndIncrement();
            return this.remoteClient.b();
        }
        Log.e(Constant.TAG, "CoreService version is low");
        return false;
    }

    public final Context getAppContext() {
        return this.mContext;
    }

    public final class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            Object obj;
            super.handleMessage(message);
            int i = message.what;
            if (i == 1) {
                EASFrameworkRemoteApiClient.this.onServiceConnected();
                return;
            }
            if (i == 2) {
                EASFrameworkRemoteApiClient.this.onServiceDisconnected();
                return;
            }
            if (i == 3) {
                EASFrameworkRemoteApiClient.this.onBindingDied();
                return;
            }
            if (i == 4) {
                Object obj2 = message.obj;
                if (obj2 == null || !(obj2 instanceof String)) {
                    return;
                }
                String str = (String) obj2;
                Log.e(Constant.TAG, "SERVICE_CONNECTED_SUPPORT_NOTIFITY >> " + str);
                EASFrameworkRemoteApiClient.this.onServiceConnected(str);
                return;
            }
            if (i == 5 && (obj = message.obj) != null && (obj instanceof String)) {
                String str2 = (String) obj;
                Log.e(Constant.TAG, "SERVICE_DISCONNECTED_SUPPORT_NOTIFITY >> " + str2);
                EASFrameworkRemoteApiClient.this.onServiceDisconnected(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onServiceConnected() {
        List<String> remoteServices;
        b bVar = this.remoteClient;
        if (bVar != null && bVar.c()) {
            this.remoteClient.l();
            try {
                IEASFrameworkRemoteService iEASFrameworkRemoteServiceF = this.remoteClient.f();
                if (iEASFrameworkRemoteServiceF == null) {
                    return;
                }
                try {
                    iEASFrameworkRemoteServiceF.a((String[]) mConnectionCallbacks.keySet().toArray(new String[0]));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                try {
                    remoteServices = iEASFrameworkRemoteServiceF.getRemoteServices();
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                    remoteServices = null;
                }
                if (remoteServices == null || remoteServices.isEmpty()) {
                    Log.i(Constant.TAG, "Not Found service");
                    return;
                }
                for (Map.Entry<String, IServiceNoticeImplCallback> entry : mConnectionCallbacks.entrySet()) {
                    String key = entry.getKey();
                    Log.e(Constant.TAG, "ServiceName=" + key);
                    if (!remoteServices.contains(key)) {
                        Log.e(Constant.TAG, "service is not start");
                    } else {
                        IServiceNoticeImplCallback value = entry.getValue();
                        if (value != null) {
                            RemoteDevicesInfo serviceVersionInfo = getServiceVersionInfo(key, iEASFrameworkRemoteServiceF);
                            Log.e(Constant.TAG, ">> Moudle [" + key + "] sdk 374 init result " + value.onConnected(key, serviceVersionInfo, true) + "]<<");
                            noticeClient(key, serviceVersionInfo);
                        }
                    }
                }
            } catch (DeadObjectException e3) {
                e3.printStackTrace();
            } catch (Exception e4) {
                e4.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onServiceDisconnected() {
        if (this.remoteClient == null) {
            return;
        }
        for (Map.Entry<String, IServiceNoticeImplCallback> entry : mConnectionCallbacks.entrySet()) {
            String key = entry.getKey();
            IServiceNoticeImplCallback value = entry.getValue();
            if (value != null && !TextUtils.isEmpty(key)) {
                try {
                    value.onConnected(key, null, false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (this.remoteClient.d()) {
            return;
        }
        connectService();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onBindingDied() {
        onServiceDisconnected();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onServiceConnected(String str) {
        if (this.remoteClient == null || TextUtils.isEmpty(str)) {
            Log.i(Constant.TAG, "onServiceConnected remoteClient == null || serviceName is empty");
            return;
        }
        if (!mConnectionCallbacks.containsKey(str)) {
            Log.i(Constant.TAG, "mConnectionCallbacks containsKey == false ");
            return;
        }
        IServiceNoticeImplCallback iServiceNoticeImplCallback = mConnectionCallbacks.get(str);
        if (iServiceNoticeImplCallback == null) {
            return;
        }
        try {
            IEASFrameworkRemoteService iEASFrameworkRemoteServiceF = this.remoteClient.f();
            if (iEASFrameworkRemoteServiceF == null) {
                Log.i(Constant.TAG, " ieasFrameworkService == null");
                return;
            }
            RemoteDevicesInfo serviceVersionInfo = getServiceVersionInfo(str, iEASFrameworkRemoteServiceF);
            Log.e(Constant.TAG, ">> Moudle [" + str + "] sdk 444 init result " + iServiceNoticeImplCallback.onConnected(str, serviceVersionInfo, true) + "]<<");
            noticeClient(str, serviceVersionInfo);
        } catch (DeadObjectException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onServiceDisconnected(String str) {
        IServiceNoticeImplCallback iServiceNoticeImplCallback;
        if (this.remoteClient == null || TextUtils.isEmpty(str)) {
            Log.i(Constant.TAG, "onServiceDisconnected remoteClient == null || serviceName is empty");
            return;
        }
        if (mConnectionCallbacks.containsKey(str) && (iServiceNoticeImplCallback = mConnectionCallbacks.get(str)) != null) {
            try {
                iServiceNoticeImplCallback.onConnected(str, null, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void noticeClient(String str, RemoteDevicesInfo remoteDevicesInfo) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        for (Map.Entry<ECarXApiClient.RemoteCallback, String> entry : mCallbacks.entrySet()) {
            if (str.equals(entry.getValue())) {
                ECarXApiClient.RemoteCallback key = entry.getKey();
                if (key == null) {
                    Log.e(Constant.TAG, String.format(">> Moudle [%s] 's ECarXApiClient.Callback is NULL!!! <<", str));
                } else if (remoteDevicesInfo == null) {
                    try {
                        Log.i(Constant.TAG, String.format(">> onAPIReady(false , %s) <<", str));
                        key.onAPIReady(false, "not found service2");
                    } catch (Exception e) {
                        Log.e(Constant.TAG, "4Notify Module API [%s] is Fail,  because Module API interanl error!!!", e);
                    }
                } else {
                    Log.i(Constant.TAG, String.format(">> onAPIReady(true, %s)  <<", str));
                    key.onAPIReady(true, "");
                }
            }
        }
    }

    public final boolean isEASFrameworkRemoteServiceConnected() {
        b bVar = this.remoteClient;
        return bVar != null && bVar.c();
    }

    private RemoteDevicesInfo getServiceVersionInfo(String str, IEASFrameworkRemoteService iEASFrameworkRemoteService) {
        RemoteDevicesInfo remoteDevicesInfoCreateFromParcel = null;
        if (iEASFrameworkRemoteService == null || TextUtils.isEmpty(str)) {
            return null;
        }
        Parcel parcelObtain = Parcel.obtain();
        try {
            try {
                EASFrameworkRetMessageRemote eASFrameworkRetMessageRemoteAsyncCall = iEASFrameworkRemoteService.asyncCall(new EASFrameworkMessageRemote("eascore", "master", "getRemoteServiceInfo", str, null), null);
                if (eASFrameworkRetMessageRemoteAsyncCall.mCode == 200 && eASFrameworkRetMessageRemoteAsyncCall.mResult != null) {
                    parcelObtain.unmarshall(eASFrameworkRetMessageRemoteAsyncCall.mResult, 0, eASFrameworkRetMessageRemoteAsyncCall.mResult.length);
                    parcelObtain.setDataPosition(0);
                    remoteDevicesInfoCreateFromParcel = RemoteDevicesInfo.CREATOR.createFromParcel(parcelObtain);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return remoteDevicesInfoCreateFromParcel;
        } finally {
            parcelObtain.recycle();
        }
    }
}
