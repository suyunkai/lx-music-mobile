package com.wanos.groove.base;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.media3.exoplayer.ExoPlayer;
import com.wanos.groove.GrooveControlUtil;
import com.wanos.groove.GrooveSdkConstant;
import com.wanos.groove.utils.GrooveRemote;
import com.wanos.groove.utils.GrooveSdkAppGlobal;
import java.util.concurrent.LinkedBlockingQueue;

/* JADX INFO: loaded from: classes3.dex */
public abstract class GrooveSdk<T extends IInterface> {
    private static final String THREAD_NAME = "bindServiceThread";
    String action;
    String className;
    private Application mApplication;
    private Handler mChildThreadHandler;
    private boolean mIsConnected;
    private T mProxy;
    PlayServiceStateListener mServiceStateListener;
    String pageName;
    private final Runnable mBindServiceTask = new Runnable() { // from class: com.wanos.groove.base.GrooveSdk.1
        @Override // java.lang.Runnable
        public void run() {
            GrooveSdk.this.bindService();
        }
    };
    private final LinkedBlockingQueue<Runnable> mTaskQueue = new LinkedBlockingQueue<>();
    private final ServiceConnection mServiceConnection = new AnonymousClass2();
    private final IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() { // from class: com.wanos.groove.base.GrooveSdk.3
        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            GrooveSdk.this.mIsConnected = false;
            if (GrooveSdk.this.mServiceStateListener != null) {
                GrooveSdk.this.mServiceStateListener.onBinderDied();
            }
            if (GrooveSdk.this.mProxy != null) {
                GrooveSdk.this.mProxy.asBinder().unlinkToDeath(GrooveSdk.this.mDeathRecipient, 0);
                GrooveSdk.this.mProxy = null;
            }
            GrooveSdk.this.toRebindService();
        }
    };
    long mRetryBindTimeMillTime = ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS;

    protected abstract T asInterface(IBinder iBinder);

    /* JADX INFO: renamed from: com.wanos.groove.base.GrooveSdk$2, reason: invalid class name */
    class AnonymousClass2 implements ServiceConnection {
        AnonymousClass2() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, final IBinder iBinder) {
            GrooveSdk grooveSdk = GrooveSdk.this;
            grooveSdk.mProxy = grooveSdk.asInterface(iBinder);
            GrooveRemote.tryExec(new GrooveRemote.RemoteVoidFunction() { // from class: com.wanos.groove.base.GrooveSdk$2$$ExternalSyntheticLambda0
                @Override // com.wanos.groove.utils.GrooveRemote.RemoteVoidFunction
                public final void call() throws RemoteException {
                    this.f$0.m472lambda$onServiceConnected$0$comwanosgroovebaseGrooveSdk$2(iBinder);
                }
            });
            if (GrooveSdk.this.mServiceStateListener != null) {
                GrooveSdk.this.mServiceStateListener.onServiceConnected();
                System.out.println("链接成功" + GrooveSdk.this.mProxy);
                GrooveControlUtil.getInstance().getMediaPlayer();
                GrooveSdk.this.mIsConnected = true;
            }
            GrooveSdk.this.handleTask();
            GrooveSdk.this.mChildThreadHandler.removeCallbacks(GrooveSdk.this.mBindServiceTask);
        }

        /* JADX INFO: renamed from: lambda$onServiceConnected$0$com-wanos-groove-base-GrooveSdk$2, reason: not valid java name */
        /* synthetic */ void m472lambda$onServiceConnected$0$comwanosgroovebaseGrooveSdk$2(IBinder iBinder) throws RemoteException {
            if (iBinder != null) {
                iBinder.linkToDeath(GrooveSdk.this.mDeathRecipient, 0);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            GrooveSdk.this.mProxy = null;
            GrooveSdk.this.mIsConnected = false;
            if (GrooveSdk.this.mServiceStateListener != null) {
                GrooveSdk.this.mServiceStateListener.onServiceDisconnected();
            }
        }
    }

    public void init(PlayServiceStateListener playServiceStateListener) {
        init(GrooveSdkConstant.PKG, GrooveSdkConstant.CLASS_NAME, GrooveSdkConstant.ACTION, ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS);
        this.mServiceStateListener = playServiceStateListener;
    }

    public void init(String str, String str2, String str3, long j) {
        if (this.mApplication == null) {
            this.mApplication = GrooveSdkAppGlobal.getApplication();
            HandlerThread handlerThread = new HandlerThread(THREAD_NAME, 6);
            handlerThread.start();
            this.mChildThreadHandler = new Handler(handlerThread.getLooper());
            this.pageName = str;
            this.className = str2;
            this.action = str3;
            this.mRetryBindTimeMillTime = j;
        }
        if (isServiceConnected()) {
            return;
        }
        bindService();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindService() {
        if (TextUtils.isEmpty(this.pageName) || TextUtils.isEmpty(this.className) || this.mProxy != null) {
            return;
        }
        Intent intent = new Intent();
        if (!TextUtils.isEmpty(this.action)) {
            intent.setAction(this.action);
        }
        intent.setPackage("com.wanos.media");
        System.out.println("开始链接");
        if (this.mApplication.bindService(intent, this.mServiceConnection, 1)) {
            return;
        }
        toRebindService();
    }

    protected void toRebindService() {
        Runnable runnable;
        Handler handler = this.mChildThreadHandler;
        if (handler == null || (runnable = this.mBindServiceTask) == null) {
            return;
        }
        handler.postDelayed(runnable, this.mRetryBindTimeMillTime);
    }

    protected void handleTask() {
        while (true) {
            Runnable runnablePoll = this.mTaskQueue.poll();
            if (runnablePoll == null) {
                return;
            }
            Handler handler = this.mChildThreadHandler;
            if (handler != null) {
                handler.post(runnablePoll);
            }
        }
    }

    protected T getProxy() {
        return this.mProxy;
    }

    public boolean getConnectState() {
        return this.mIsConnected;
    }

    protected LinkedBlockingQueue<Runnable> getTaskQueue() {
        return this.mTaskQueue;
    }

    public void addServiceStateListener(PlayServiceStateListener playServiceStateListener) {
        this.mServiceStateListener = playServiceStateListener;
    }

    public void removeServiceStateListener() {
        this.mServiceStateListener = null;
    }

    public void release() {
        if (isServiceConnected()) {
            T t = this.mProxy;
            if (t != null) {
                t.asBinder().unlinkToDeath(this.mDeathRecipient, 0);
            }
            this.mProxy = null;
            Application application = this.mApplication;
            if (application != null) {
                application.unbindService(this.mServiceConnection);
            }
            this.mServiceStateListener = null;
        }
    }

    public boolean isServiceConnected() {
        return this.mProxy != null;
    }

    public void isRetryConnected() {
        toRebindService();
    }
}
