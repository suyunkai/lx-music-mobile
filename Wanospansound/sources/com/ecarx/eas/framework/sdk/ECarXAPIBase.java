package com.ecarx.eas.framework.sdk;

import android.content.Context;
import com.ecarx.eas.sdk.ECarXApiClient;
import com.ecarx.eas.sdk.log.ILog;
import com.ecarx.eas.sdk.log.LogProxy;

/* JADX INFO: loaded from: classes2.dex */
public class ECarXAPIBase {
    public static final int ERROR_CODE = -1;
    public static final int SUPPORT_AUTH_VERSION = 330;
    public static final String VERSION = "4.6.20(3f)";
    public static final int VERSION_INT = 445;
    protected LogProxy L = new LogProxy();
    protected Context context;

    public final String getPublishDesc() {
        return "新方案迁移";
    }

    public final String getVersion() {
        return "4.6.20(3f)";
    }

    public final int getVersionInt() {
        return 445;
    }

    public void release() {
    }

    public void init(Context context, ECarXApiClient.Callback callback) {
        if (context != null && context.getApplicationContext() != null) {
            this.context = context.getApplicationContext();
        } else {
            this.context = context;
        }
    }

    public void initRemote(Context context, ECarXApiClient.RemoteCallback remoteCallback) {
        if (context != null && context.getApplicationContext() != null) {
            this.context = context.getApplicationContext();
        } else {
            this.context = context;
        }
    }

    public void setLogEnable(boolean z) {
        this.L.setLogEnable(z);
    }

    public void setLogLevel(int i) {
        this.L.setLogLevel(i);
    }

    public void setLogImpl(ILog iLog) {
        this.L.setLogImpl(iLog);
    }
}
