package com.ecarx.eas.framework.sdk.common.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.UserManager;
import android.text.TextUtils;
import android.util.Log;
import com.ecarx.eas.framework.sdk.Singleton;
import com.ecarx.eas.framework.sdk.common.exception.EASFrameworkException;
import com.ecarx.eas.framework.sdk.common.exception.IllegalArgumentEASException;
import com.ecarx.eas.framework.sdk.common.internal.safeparcel.ServiceVersionInfo;
import com.ecarx.eas.framework.sdk.common.internal.safeparcel.StrMsg;
import com.ecarx.eas.framework.sdk.common.internal.safeparcel.VersionInfo;
import com.ecarx.eas.framework.sdk.common.wrappers.PackageManagerWrapper;
import com.ecarx.eas.sdk.ECarXApiClient;
import com.ecarx.eas.sdk.IServiceManager;
import com.ecarx.sdk.openapi.msg.EASFrameworkMessage;
import com.ecarx.sdk.openapi.msg.EASFrameworkRetMessage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes2.dex */
public final class EASFrameworkApiClient {
    private static final int MAX_CONNECT_TWICE = 30;
    private static final int RECONNECT_DELAY = 1000;
    private static final int STATUS_BINDED_SUCCESS = 1;
    private static final int STATUS_INITING = 0;
    private static final int STATUS_START = -1;
    private volatile Context mContext;
    private final AtomicInteger mCurrentServiceTwice;
    private com.ecarx.eas.framework.sdk.common.internal.a<?> mEASClient;
    private Intent mEASFrameworkIntent;
    private final a mH;
    private b mNotifity;
    private Intent mOpenAPIIntent;
    private PackageManagerWrapper mPackageWrapper;
    private volatile int mType;
    private BroadcastReceiver mUserUnLockedReceiver;
    private volatile int mVersion;
    private final a noticeH;
    private static Map<ECarXApiClient.Callback, String> mCallbacks = new ConcurrentHashMap();
    private static Map<String, IServiceConnectionCallback> mConnectionCallbacks = new ConcurrentHashMap();
    private static volatile CopyOnWriteArraySet<String> mInitCallbacks = new CopyOnWriteArraySet<>();
    private static Singleton<EASFrameworkApiClient> gProxy = new Singleton<EASFrameworkApiClient>() { // from class: com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient.1
        @Override // com.ecarx.eas.framework.sdk.Singleton
        protected /* synthetic */ EASFrameworkApiClient create() {
            return new EASFrameworkApiClient();
        }
    };

    public interface IServiceConnectionCallback {
        boolean onConnected(String str, ClientType clientType, ServiceVersionInfo serviceVersionInfo, boolean z);

        boolean onConnected(String str, ClientType clientType, boolean z);
    }

    public interface b {
        void a();

        void a(String str);

        void b();

        void b(String str);

        void c();
    }

    public static EASFrameworkApiClient getInstance() {
        EASFrameworkApiClient eASFrameworkApiClient;
        synchronized (EASFrameworkApiClient.class) {
            eASFrameworkApiClient = gProxy.get();
        }
        return eASFrameworkApiClient;
    }

    private EASFrameworkApiClient() {
        this.mCurrentServiceTwice = new AtomicInteger(0);
        this.mOpenAPIIntent = new Intent("ecarx.intent.action.OpenAPIService").setPackage("com.ecarx.sdk.openapi");
        this.mEASFrameworkIntent = new Intent("com.ecarx.easframework.intent.action.EASFRAMEWORK").setPackage("com.ecarx.sdk.openapi");
        this.mUserUnLockedReceiver = new BroadcastReceiver() { // from class: com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient.2
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context, Intent intent) {
                if (intent == null) {
                    return;
                }
                String action = intent.getAction();
                if (!TextUtils.isEmpty(action) && "android.intent.action.USER_UNLOCKED".equals(action)) {
                    EASFrameworkApiClient.this.mContext.unregisterReceiver(this);
                    if (EASFrameworkApiClient.this.mH.hasMessages(0)) {
                        return;
                    }
                    EASFrameworkApiClient.this.mH.sendEmptyMessage(0);
                    Log.d(Constant.TAG, "H send msg : 0");
                }
            }
        };
        this.mNotifity = new b() { // from class: com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient.3
            @Override // com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient.b
            public final void a() {
                if (EASFrameworkApiClient.this.mEASClient == null) {
                    return;
                }
                EASFrameworkApiClient.this.mCurrentServiceTwice.set(0);
                EASFrameworkApiClient.this.mH.removeMessages(1);
                EASFrameworkApiClient.this.mH.sendMessage(EASFrameworkApiClient.this.mH.obtainMessage(1));
                Log.d(Constant.TAG, "H send msg1");
            }

            @Override // com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient.b
            public final void b() {
                EASFrameworkApiClient.this.mH.removeMessages(2);
                EASFrameworkApiClient.this.mH.removeMessages(1);
                EASFrameworkApiClient.this.mH.removeMessages(3);
                Message messageObtainMessage = EASFrameworkApiClient.this.mH.obtainMessage(2);
                Log.d(Constant.TAG, "H send msg : 2");
                EASFrameworkApiClient.this.mH.sendMessage(messageObtainMessage);
            }

            @Override // com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient.b
            public final void c() {
                EASFrameworkApiClient.this.mH.removeMessages(2);
                EASFrameworkApiClient.this.mH.removeMessages(1);
                EASFrameworkApiClient.this.mH.removeMessages(3);
                Message messageObtainMessage = EASFrameworkApiClient.this.mH.obtainMessage(3);
                Log.d(Constant.TAG, "H send msg : 3");
                EASFrameworkApiClient.this.mH.sendMessage(messageObtainMessage);
            }

            @Override // com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient.b
            public final void a(String str) {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                Log.d(Constant.TAG, "onServiceConnected() service = " + str);
                EASFrameworkApiClient.this.noticeH.removeMessages(4, str);
                EASFrameworkApiClient.this.noticeH.removeMessages(5, str);
                Message messageObtainMessage = EASFrameworkApiClient.this.noticeH.obtainMessage(4);
                messageObtainMessage.obj = str;
                EASFrameworkApiClient.this.noticeH.sendMessage(messageObtainMessage);
                Log.d(Constant.TAG, "H send msg : 4");
            }

            @Override // com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient.b
            public final void b(String str) {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                Log.d(Constant.TAG, "onServiceDisconnected() service = " + str);
                EASFrameworkApiClient.this.noticeH.removeMessages(4, str);
                EASFrameworkApiClient.this.noticeH.removeMessages(5, str);
                Message messageObtainMessage = EASFrameworkApiClient.this.noticeH.obtainMessage(5);
                messageObtainMessage.obj = str;
                EASFrameworkApiClient.this.noticeH.sendMessage(messageObtainMessage);
                Log.d(Constant.TAG, "H send msg : 5");
            }
        };
        HandlerThread handlerThread = new HandlerThread("EASFrameworkClientBg", 10);
        handlerThread.start();
        this.mH = new a(handlerThread.getLooper());
        Log.e(Constant.TAG, "EASFrameworkApiClient()");
        HandlerThread handlerThread2 = new HandlerThread("EASFrameworkClientNotice", 0);
        handlerThread2.start();
        this.noticeH = new a(handlerThread2.getLooper());
    }

    public final void init(Context context, ECarXApiClient.Callback callback, String str, IServiceConnectionCallback iServiceConnectionCallback) throws EASFrameworkException {
        boolean zInitConnectOpenAPISpecifyService;
        try {
            create(context);
            Log.d(Constant.TAG, "init service =" + str);
            mInitCallbacks.add(str);
            com.ecarx.eas.framework.sdk.common.internal.a<?> aVar = this.mEASClient;
            if (aVar == null || aVar.e()) {
                mCallbacks.put(callback, str);
                mConnectionCallbacks.put(str, iServiceConnectionCallback);
                if (this.mH.hasMessages(0)) {
                    return;
                }
                this.mH.sendEmptyMessage(0);
                return;
            }
            if (this.mEASClient.d()) {
                mCallbacks.put(callback, str);
                mConnectionCallbacks.put(str, iServiceConnectionCallback);
                return;
            }
            mCallbacks.put(callback, str);
            mConnectionCallbacks.put(str, iServiceConnectionCallback);
            int i = AnonymousClass4.f21a[this.mEASClient.j().ordinal()];
            if (i == 1) {
                zInitConnectOpenAPISpecifyService = initConnectOpenAPISpecifyService(callback, str, iServiceConnectionCallback);
            } else {
                zInitConnectOpenAPISpecifyService = i != 2 ? false : initConnectEASFrameworkSpecifyService(callback, str, iServiceConnectionCallback);
            }
            String str2 = this.mEASClient.j() == ClientType.OpenAPI ? String.format("initConnectOpenAPISpecifyService() = %s,%s", String.valueOf(zInitConnectOpenAPISpecifyService), str) : String.format("initConnectEASFrameworkSpecifyService() = %s,%s", String.valueOf(zInitConnectOpenAPISpecifyService), str);
            if (!zInitConnectOpenAPISpecifyService) {
                Log.e(Constant.TAG, str2);
            } else {
                Log.i(Constant.TAG, str2);
            }
        } catch (IllegalArgumentEASException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: renamed from: com.ecarx.eas.framework.sdk.common.internal.EASFrameworkApiClient$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f21a;

        static {
            int[] iArr = new int[ClientType.values().length];
            f21a = iArr;
            try {
                iArr[ClientType.OpenAPI.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f21a[ClientType.EASFramework.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public final int getVersion() {
        return this.mVersion;
    }

    public final String geCoreServiceVersionCode() {
        String string = "-1";
        if (this.mContext == null) {
            return "-1";
        }
        Cursor cursorQuery = null;
        try {
            cursorQuery = this.mContext.getContentResolver().query(Uri.parse("content://com.ecarx.sdk.openapi.contentprovider/core_service_version_code"), new String[]{"id"}, null, null, null);
            if (cursorQuery != null && cursorQuery.moveToFirst()) {
                string = cursorQuery.getString(0);
            }
            if (cursorQuery != null && !cursorQuery.isClosed()) {
                cursorQuery.close();
            }
            return string;
        } catch (Exception unused) {
            if (cursorQuery != null && !cursorQuery.isClosed()) {
                cursorQuery.close();
            }
            return "-1";
        } catch (Throwable th) {
            if (cursorQuery != null && !cursorQuery.isClosed()) {
                cursorQuery.close();
            }
            throw th;
        }
    }

    private boolean initConnectEASFrameworkSpecifyService(ECarXApiClient.Callback callback, String str, IServiceConnectionCallback iServiceConnectionCallback) {
        List<String> availableServices;
        try {
            IEASFrameworkService iEASFrameworkServiceF = ((d) this.mEASClient).f();
            if (iEASFrameworkServiceF == null) {
                return false;
            }
            try {
                iEASFrameworkServiceF.a(new String[]{str});
                List<String> availableEASServices = iEASFrameworkServiceF.getAvailableEASServices();
                if (availableEASServices == null) {
                    Log.i(Constant.TAG, String.format("getAvailableEASServices() is NULL!!!", new Object[0]));
                    return false;
                }
                if (!availableEASServices.contains(str)) {
                    Log.i(Constant.TAG, String.format("getAvailableEASServices() list  %s!!!", availableEASServices.toString()));
                    Log.i(Constant.TAG, String.format("getAvailableEASServices() not Contain  %s!!!", str));
                    handleOtherInitializedServices(availableEASServices, iEASFrameworkServiceF, 1);
                    try {
                        availableServices = iEASFrameworkServiceF.getAvailableServices();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        availableServices = null;
                    }
                    if (availableServices != null && !availableServices.contains(str)) {
                        return false;
                    }
                }
                if (!mInitCallbacks.contains(str)) {
                    Log.e(Constant.TAG, "initConnectEASFrameworkSpecifyService has already called back before = " + str);
                    handleOtherInitializedServices(availableEASServices, iEASFrameworkServiceF, 2);
                    return false;
                }
                mInitCallbacks.remove(str);
                ServiceVersionInfo serviceVersionInfo = getServiceVersionInfo(str, iEASFrameworkServiceF);
                AtomicBoolean atomicBoolean = new AtomicBoolean(false);
                if (iServiceConnectionCallback != null) {
                    if (serviceVersionInfo == null || serviceVersionInfo.versionInfos == null || serviceVersionInfo.versionInfos.isEmpty()) {
                        atomicBoolean.set(iServiceConnectionCallback.onConnected(str, this.mEASClient.j(), true));
                    } else {
                        atomicBoolean.set(iServiceConnectionCallback.onConnected(str, this.mEASClient.j(), serviceVersionInfo, true));
                        if (str.equals(serviceVersionInfo.name)) {
                            Iterator<VersionInfo> it = serviceVersionInfo.versionInfos.iterator();
                            while (it.hasNext()) {
                                printLogVersionInfo(it.next(), str);
                            }
                        }
                    }
                }
                try {
                    Log.i(Constant.TAG, String.format(">> onAPIReady(%s, %s) <<", str, String.valueOf(atomicBoolean)));
                    callback.onAPIReady(atomicBoolean.get());
                    handleOtherInitializedServices(availableEASServices, iEASFrameworkServiceF, 3);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    Log.e(Constant.TAG, "1Notify Module API [%s] is Fail,  because Module API interanl error!!!", e2);
                }
                return true;
            } catch (RemoteException e3) {
                e3.printStackTrace();
                return false;
            }
        } catch (DeadObjectException e4) {
            e4.printStackTrace();
            this.mH.sendEmptyMessage(0);
            return false;
        } catch (NullPointerException e5) {
            e5.printStackTrace();
            this.mH.sendEmptyMessage(0);
            return false;
        } catch (Exception e6) {
            e6.printStackTrace();
            this.mH.sendEmptyMessage(0);
            return false;
        }
    }

    private void handleOtherInitializedServices(List<String> list, IEASFrameworkService iEASFrameworkService, int i) {
        Log.e(Constant.TAG, "handleOtherInitializedServices where =" + i);
        if (!isEASFrameworkServiceConnected()) {
            Log.w(Constant.TAG, "EASFrameworkService is disconnected !!!");
            return;
        }
        for (Map.Entry<String, IServiceConnectionCallback> entry : mConnectionCallbacks.entrySet()) {
            String key = entry.getKey();
            if (!list.contains(key)) {
                Log.e(Constant.TAG, "handleOtherInitializedServices " + key + " unavailable in this time ");
            } else {
                IServiceConnectionCallback value = entry.getValue();
                if (value != null) {
                    if (!mInitCallbacks.contains(key)) {
                        Log.e(Constant.TAG, "handleOtherInitializedServices has already called back before = " + key);
                    } else {
                        mInitCallbacks.remove(key);
                        ServiceVersionInfo serviceVersionInfo = getServiceVersionInfo(key, iEASFrameworkService);
                        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
                        if (serviceVersionInfo == null || serviceVersionInfo.versionInfos == null || serviceVersionInfo.versionInfos.isEmpty()) {
                            Log.e(Constant.TAG, "handleOtherInitializedServices VersionInfo is NULL");
                            atomicBoolean.set(value.onConnected(key, this.mEASClient.j(), true));
                        } else {
                            atomicBoolean.set(value.onConnected(key, this.mEASClient.j(), serviceVersionInfo, true));
                            if (key.equals(serviceVersionInfo.name)) {
                                Iterator<VersionInfo> it = serviceVersionInfo.versionInfos.iterator();
                                while (it.hasNext()) {
                                    printLogVersionInfo(it.next(), key);
                                }
                            }
                        }
                        Log.e(Constant.TAG, String.format(">>handleOtherInitializedServices Moudle [%s] 's noticeClient V4 <<", key));
                        noticeClient(key, atomicBoolean.get());
                    }
                }
            }
        }
    }

    private boolean initConnectOpenAPISpecifyService(ECarXApiClient.Callback callback, String str, IServiceConnectionCallback iServiceConnectionCallback) {
        try {
            IServicePool iServicePoolF = ((h) this.mEASClient).f();
            if (iServicePoolF == null) {
                return false;
            }
            try {
                List<String> availableServices = iServicePoolF.getAvailableServices();
                if (availableServices == null) {
                    Log.i(Constant.TAG, String.format("getAvailableServices() is NULL!!!", new Object[0]));
                    return false;
                }
                if (!availableServices.contains(str)) {
                    Log.i(Constant.TAG, String.format("getAvailableServices() not Contain  %s!!!", str));
                }
                AtomicBoolean atomicBoolean = new AtomicBoolean(false);
                if (iServiceConnectionCallback != null) {
                    atomicBoolean.set(iServiceConnectionCallback.onConnected(str, this.mEASClient.j(), true));
                }
                try {
                    Log.i(Constant.TAG, String.format(">> onAPIReady(%s, %s) <<", str, String.valueOf(atomicBoolean)));
                    callback.onAPIReady(atomicBoolean.get());
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(Constant.TAG, "Notify Module API [%s] is Fail,  because Module API interanl error!!!", e);
                }
                return true;
            } catch (RemoteException e2) {
                e2.printStackTrace();
                return false;
            }
        } catch (DeadObjectException e3) {
            e3.printStackTrace();
            this.mH.sendEmptyMessage(0);
            return false;
        } catch (NullPointerException e4) {
            e4.printStackTrace();
            this.mH.sendEmptyMessage(0);
            return false;
        } catch (Exception e5) {
            e5.printStackTrace();
            this.mH.sendEmptyMessage(0);
            return false;
        }
    }

    private void create(Context context) throws IllegalArgumentEASException {
        synchronized (EASFrameworkApiClient.class) {
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

    public final IEASFrameworkService getEASServiceManager() throws DeadObjectException {
        com.ecarx.eas.framework.sdk.common.internal.a<?> aVar = this.mEASClient;
        if (aVar == null || aVar.e() || this.mEASClient.d() || this.mEASClient.j() != ClientType.EASFramework) {
            return null;
        }
        try {
            return ((d) this.mEASClient).f();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public final boolean isEASFrameworkServiceConnected() {
        com.ecarx.eas.framework.sdk.common.internal.a<?> aVar = this.mEASClient;
        if (aVar == null) {
            return false;
        }
        return aVar.c();
    }

    public final IServicePool getServiceManager() throws DeadObjectException {
        com.ecarx.eas.framework.sdk.common.internal.a<?> aVar = this.mEASClient;
        if (aVar == null || aVar.e() || this.mEASClient.d() || this.mEASClient.j() != ClientType.OpenAPI) {
            return null;
        }
        try {
            return ((h) this.mEASClient).f();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean connectService() {
        if (this.mPackageWrapper == null) {
            return false;
        }
        com.ecarx.eas.framework.sdk.common.internal.a<?> aVar = this.mEASClient;
        if (aVar != null && aVar.d()) {
            return true;
        }
        if (this.mPackageWrapper.findService(this.mEASFrameworkIntent) != null) {
            com.ecarx.eas.framework.sdk.common.internal.a<?> aVar2 = this.mEASClient;
            if (aVar2 != null) {
                aVar2.k();
            }
            this.mEASClient = new d(this.mContext, this.mPackageWrapper, this.mNotifity);
            this.mCurrentServiceTwice.getAndIncrement();
            return this.mEASClient.b();
        }
        if (!((UserManager) this.mContext.getSystemService(IServiceManager.SERVICE_MEMBERCENTER)).isUserUnlocked()) {
            IntentFilter intentFilter = new IntentFilter("android.intent.action.USER_UNLOCKED");
            intentFilter.setPriority(1000);
            this.mContext.unregisterReceiver(this.mUserUnLockedReceiver);
            this.mContext.registerReceiver(this.mUserUnLockedReceiver, intentFilter);
            return true;
        }
        if (this.mPackageWrapper.findService(this.mOpenAPIIntent) == null) {
            this.mCurrentServiceTwice.getAndIncrement();
            if (this.mCurrentServiceTwice.get() > 30) {
                Log.e(Constant.TAG, String.format(">> package=%s, action=%s service not found!!!<<", this.mOpenAPIIntent.getPackage(), this.mOpenAPIIntent.getAction()));
                return false;
            }
            this.mH.sendEmptyMessageDelayed(0, 1000L);
            return false;
        }
        com.ecarx.eas.framework.sdk.common.internal.a<?> aVar3 = this.mEASClient;
        if (aVar3 != null) {
            aVar3.k();
        }
        this.mEASClient = new h(this.mContext, this.mPackageWrapper, this.mNotifity);
        this.mCurrentServiceTwice.getAndIncrement();
        return this.mEASClient.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onServiceConnected() {
        ApplicationInfo applicationInfo;
        Log.d(Constant.TAG, "onServiceConnected()");
        com.ecarx.eas.framework.sdk.common.internal.a<?> aVar = this.mEASClient;
        if (aVar == null) {
            return;
        }
        if (!aVar.c()) {
            Log.w(Constant.TAG, "EASClient is not connected");
            return;
        }
        ClientType clientTypeJ = this.mEASClient.j();
        if (clientTypeJ == null) {
            return;
        }
        if (this.mContext != null) {
            try {
                applicationInfo = this.mContext.getPackageManager().getApplicationInfo("com.ecarx.sdk.openapi", 128);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                applicationInfo = null;
            }
            Bundle bundle = applicationInfo != null ? applicationInfo.metaData : null;
            if (bundle != null && bundle.containsKey("com.ecarx.sdk.openapi.version")) {
                this.mVersion = bundle.getInt("com.ecarx.sdk.openapi.version", 0);
            }
            if (bundle != null && bundle.containsKey("com.ecarx.sdk.openapi.type")) {
                this.mType = bundle.getInt("com.ecarx.sdk.openapi.type", 0);
            }
        }
        this.mEASClient.l();
        if (ClientType.OpenAPI == clientTypeJ) {
            onOpenAPIServiceConnected((h) this.mEASClient);
        } else if (ClientType.EASFramework == clientTypeJ) {
            onEASFrameworkServiceConnected((d) this.mEASClient);
        } else {
            Log.e(Constant.TAG, String.format(">> 异常的ClientType = %s <<", clientTypeJ.toString()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onServiceDisconnected() {
        ClientType clientTypeJ;
        com.ecarx.eas.framework.sdk.common.internal.a<?> aVar = this.mEASClient;
        if (aVar == null || (clientTypeJ = aVar.j()) == null) {
            return;
        }
        for (Map.Entry<String, IServiceConnectionCallback> entry : mConnectionCallbacks.entrySet()) {
            String key = entry.getKey();
            mInitCallbacks.add(key);
            Log.d(Constant.TAG, "onServiceDisconnected service =  " + key);
            IServiceConnectionCallback value = entry.getValue();
            if (value != null && !TextUtils.isEmpty(key)) {
                try {
                    value.onConnected(key, clientTypeJ, false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (this.mH.hasMessages(0)) {
            Log.e(Constant.TAG, "hasMessages(H.SERVICE_CONNECT_TODO)");
            return;
        }
        com.ecarx.eas.framework.sdk.common.internal.a<?> aVar2 = this.mEASClient;
        if (aVar2 == null || !aVar2.d()) {
            this.mH.sendMessage(this.mH.obtainMessage(0));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onBindingDied() {
        onServiceDisconnected();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onServiceConnected(String str) {
        ClientType clientTypeJ;
        IServiceConnectionCallback iServiceConnectionCallback;
        IEASFrameworkService eASServiceManager;
        com.ecarx.eas.framework.sdk.common.internal.a<?> aVar = this.mEASClient;
        if (aVar == null || (clientTypeJ = aVar.j()) == null || TextUtils.isEmpty(str) || !mConnectionCallbacks.containsKey(str) || (iServiceConnectionCallback = mConnectionCallbacks.get(str)) == null) {
            return;
        }
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        if (clientTypeJ == ClientType.EASFramework) {
            try {
                eASServiceManager = getEASServiceManager();
            } catch (DeadObjectException e) {
                e.printStackTrace();
                return;
            }
        } else {
            eASServiceManager = null;
        }
        if (eASServiceManager == null) {
            return;
        }
        if (!mInitCallbacks.contains(str)) {
            Log.e(Constant.TAG, "has  already called back before =" + str);
            return;
        }
        mInitCallbacks.remove(str);
        ServiceVersionInfo serviceVersionInfo = getServiceVersionInfo(str, eASServiceManager);
        if (iServiceConnectionCallback != null) {
            if (serviceVersionInfo == null || serviceVersionInfo.versionInfos == null || serviceVersionInfo.versionInfos.isEmpty()) {
                Log.e(Constant.TAG, "VersionInfo is NULL");
                atomicBoolean.set(iServiceConnectionCallback.onConnected(str, this.mEASClient.j(), true));
            } else {
                atomicBoolean.set(iServiceConnectionCallback.onConnected(str, this.mEASClient.j(), serviceVersionInfo, true));
                if (str.equals(serviceVersionInfo.name)) {
                    Iterator<VersionInfo> it = serviceVersionInfo.versionInfos.iterator();
                    while (it.hasNext()) {
                        printLogVersionInfo(it.next(), str);
                    }
                }
            }
        }
        Log.i(Constant.TAG, String.format(">> onServiceConnected(String serviceName) onAPIReady(%s, %s) <<", str, String.valueOf(atomicBoolean.get())));
        Log.e(Constant.TAG, String.format(">> Moudle [%s] 's noticeClient start <<", str));
        noticeClient(str, atomicBoolean.get());
    }

    private void printLogVersionInfo(VersionInfo versionInfo, String str) {
        if (versionInfo != null) {
            Log.e(Constant.TAG, "service name ： " + str + "\n OpenAPI Version=4.6.20(3f)\n type=" + versionInfo.type + "\n service Version : " + versionInfo.versionCode + "\n service VersionName : " + versionInfo.versionName + "\n EAS core Version : " + geCoreServiceVersionCode());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onServiceDisconnected(String str) {
        ClientType clientTypeJ;
        com.ecarx.eas.framework.sdk.common.internal.a<?> aVar = this.mEASClient;
        if (aVar == null || (clientTypeJ = aVar.j()) == null || TextUtils.isEmpty(str) || !mConnectionCallbacks.containsKey(str)) {
            return;
        }
        mInitCallbacks.add(str);
        Log.d(Constant.TAG, "onServiceDisconnected == " + str);
        IServiceConnectionCallback iServiceConnectionCallback = mConnectionCallbacks.get(str);
        if (iServiceConnectionCallback == null) {
            return;
        }
        try {
            iServiceConnectionCallback.onConnected(str, clientTypeJ, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onOpenAPIServiceConnected(h hVar) {
        List<String> availableServices;
        IServiceConnectionCallback value;
        try {
            IServicePool iServicePoolF = hVar.f();
            if (iServicePoolF == null) {
                return;
            }
            try {
                availableServices = iServicePoolF.getAvailableServices();
            } catch (RemoteException e) {
                e.printStackTrace();
                availableServices = null;
            }
            if (availableServices == null || availableServices.isEmpty()) {
                return;
            }
            for (Map.Entry<String, IServiceConnectionCallback> entry : mConnectionCallbacks.entrySet()) {
                String key = entry.getKey();
                if (availableServices.contains(key) && (value = entry.getValue()) != null) {
                    AtomicBoolean atomicBoolean = new AtomicBoolean(false);
                    atomicBoolean.set(value.onConnected(key, this.mEASClient.j(), true));
                    Log.e(Constant.TAG, String.format(">> Moudle [%s] 's noticeClient V3  <<", key));
                    noticeClient(key, atomicBoolean.get());
                }
            }
        } catch (DeadObjectException e2) {
            e2.printStackTrace();
        } catch (Exception unused) {
        }
    }

    private void onEASFrameworkServiceConnected(d dVar) {
        List<String> arrayList;
        Log.d(Constant.TAG, "onEASFrameworkServiceConnected()");
        try {
            IEASFrameworkService iEASFrameworkServiceF = dVar.f();
            if (iEASFrameworkServiceF == null) {
                return;
            }
            try {
                Log.d(Constant.TAG, "invoke EASFrameworkService init ");
                iEASFrameworkServiceF.a((String[]) mConnectionCallbacks.keySet().toArray(new String[0]));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            List<String> availableServices = null;
            try {
                arrayList = iEASFrameworkServiceF.getAvailableEASServices();
            } catch (RemoteException e2) {
                e2.printStackTrace();
                arrayList = null;
            }
            if (arrayList == null || arrayList.isEmpty()) {
                arrayList = new ArrayList<>();
            }
            try {
                availableServices = iEASFrameworkServiceF.getAvailableServices();
            } catch (RemoteException e3) {
                e3.printStackTrace();
            }
            if (availableServices != null && !availableServices.isEmpty()) {
                for (String str : availableServices) {
                    if (!arrayList.contains(str)) {
                        arrayList.add(str);
                    }
                }
            }
            handleOtherInitializedServices(arrayList, iEASFrameworkServiceF, 4);
        } catch (DeadObjectException e4) {
            e4.printStackTrace();
        } catch (Exception e5) {
            e5.printStackTrace();
        }
    }

    private void noticeClient(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        for (Map.Entry<ECarXApiClient.Callback, String> entry : mCallbacks.entrySet()) {
            if (str.equals(entry.getValue())) {
                ECarXApiClient.Callback key = entry.getKey();
                if (key == null) {
                    Log.e(Constant.TAG, String.format(">> Moudle [%s] 's ECarXApiClient.Callback is NULL!!! <<", str));
                } else {
                    try {
                        Log.i(Constant.TAG, String.format(">> onAPIReady(%s, %s) <<", str, Boolean.valueOf(z)));
                        key.onAPIReady(z);
                    } catch (Exception e) {
                        Log.e(Constant.TAG, "4Notify Module API [%s] is Fail,  because Module API interanl error!!!", e);
                    }
                }
            }
        }
    }

    public final ServiceVersionInfo getServiceVersionInfo(String str, IEASFrameworkService iEASFrameworkService) {
        Log.d(Constant.TAG, "getServiceVersionInfo()");
        ServiceVersionInfo serviceVersionInfoCreateFromParcel = null;
        if (iEASFrameworkService == null || TextUtils.isEmpty(str)) {
            return null;
        }
        if ((this.mType == 1 && this.mVersion > 0) || (this.mType == 0 && this.mVersion > 2)) {
            StrMsg strMsg = new StrMsg();
            strMsg.param = str;
            Parcel parcelObtain = Parcel.obtain();
            strMsg.writeToParcel(parcelObtain, 0);
            try {
                try {
                    EASFrameworkRetMessage eASFrameworkRetMessageCall = iEASFrameworkService.call(new EASFrameworkMessage("eascore", "master", "getEasServiceInfo", parcelObtain.marshall(), new byte[0]));
                    if (eASFrameworkRetMessageCall.mCode == 200 && eASFrameworkRetMessageCall.mRetMsg != null && eASFrameworkRetMessageCall.mRetMsg.mCode == 200 && eASFrameworkRetMessageCall.mRetMsg.mData != null) {
                        parcelObtain.recycle();
                        parcelObtain = Parcel.obtain();
                        parcelObtain.unmarshall(eASFrameworkRetMessageCall.mRetMsg.mData, 0, eASFrameworkRetMessageCall.mRetMsg.mData.length);
                        parcelObtain.setDataPosition(0);
                        serviceVersionInfoCreateFromParcel = ServiceVersionInfo.CREATOR.createFromParcel(parcelObtain);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } finally {
                parcelObtain.recycle();
            }
        }
        return serviceVersionInfoCreateFromParcel;
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
            Log.d(Constant.TAG, "H received msg  :" + message.what);
            super.handleMessage(message);
            int i = message.what;
            if (i == 0) {
                if (EASFrameworkApiClient.this.mEASClient == null || !EASFrameworkApiClient.this.mEASClient.c()) {
                    EASFrameworkApiClient.this.connectService();
                    try {
                        Thread.sleep(200L);
                        return;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                return;
            }
            if (i == 1) {
                EASFrameworkApiClient.this.onServiceConnected();
                return;
            }
            if (i == 2) {
                EASFrameworkApiClient.this.onServiceDisconnected();
                return;
            }
            if (i == 3) {
                EASFrameworkApiClient.this.onBindingDied();
                return;
            }
            if (i == 4) {
                Object obj2 = message.obj;
                if (obj2 == null || !(obj2 instanceof String)) {
                    return;
                }
                String str = (String) obj2;
                Log.e(Constant.TAG, "SERVICE_CONNECTED_SUPPORT_NOTIFITY >> " + str);
                EASFrameworkApiClient.this.onServiceConnected(str);
                return;
            }
            if (i == 5 && (obj = message.obj) != null && (obj instanceof String)) {
                String str2 = (String) obj;
                Log.e(Constant.TAG, "SERVICE_DISCONNECTED_SUPPORT_NOTIFITY >> " + str2);
                EASFrameworkApiClient.this.onServiceDisconnected(str2);
            }
        }
    }
}
