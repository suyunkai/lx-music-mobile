package com.ecarx.eas.sdk.device.api;

import android.content.Context;
import com.ecarx.eas.framework.sdk.ECarXAPIBase;
import com.ecarx.eas.sdk.device.f;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DeviceAPI extends ECarXAPIBase implements InternalDeviceAPI {
    public static DeviceAPI get(Context context) {
        return f.a();
    }
}
