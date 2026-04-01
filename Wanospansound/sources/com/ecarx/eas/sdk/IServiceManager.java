package com.ecarx.eas.sdk;

import android.content.Context;
import android.os.IBinder;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IServiceManager {
    public static final String SERVICE_AWARENESS = "awareness";
    public static final String SERVICE_BT = "bt";
    public static final String SERVICE_CLOUDSTORAGE = "cloudstorage";
    public static final String SERVICE_DAEMON = "daemon";
    public static final String SERVICE_DCSERVICES = "DcsUpload";
    public static final String SERVICE_DEFAULTSETTING = "smartscene";
    public static final String SERVICE_DEVICE = "device";
    public static final String SERVICE_DEVICESERVICE = "drivingevent";
    public static final String SERVICE_DEVICESERVICE_ALL = "deviceservice";
    public static final String SERVICE_DRIVE_POLICY = "drivepolicy";
    public static final String SERVICE_EVS = "evs";
    public static final String SERVICE_GKUI = "gkuiservice";
    public static final String SERVICE_HAVC = "havc";
    public static final String SERVICE_MEDIACENTER = "mediacenter";
    public static final String SERVICE_MEMBERCENTER = "user";
    public static final String SERVICE_MULTIMEDIA = "multimedia";
    public static final String SERVICE_NAVI = "navi";
    public static final String SERVICE_OPEN = "open";
    public static final String SERVICE_PKI = "pki";
    public static final String SERVICE_PLUGIN = "plugin";
    public static final String SERVICE_POLICY = "policy";
    public static final String SERVICE_POS = "pos";
    public static final String SERVICE_PUSH = "push";
    public static final String SERVICE_SECURITYSERVICE_ALL = "securityservice";
    public static final String SERVICE_STATE = "state";
    public static final String SERVICE_VEHICLE = "vehicle";
    public static final String SERVICE_VR = "vr_all";

    public @interface ServiceName {
    }

    List<String> getAvailableServices();

    IBinder getService(Context context, String str);

    void setPoolBinder(IBinder iBinder);
}
