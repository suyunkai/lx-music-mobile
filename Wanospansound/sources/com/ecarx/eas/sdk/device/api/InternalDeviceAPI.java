package com.ecarx.eas.sdk.device.api;

import android.content.ComponentName;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface InternalDeviceAPI extends IDeviceAPI {
    boolean getBooleanValue(String str);

    String getDVRID();

    double getDoubleValue(String str);

    String getICCID();

    String getIHUID();

    int getIntValue(String str);

    long getLongValue(String str);

    String getProjectCode();

    String getStringValue(String str);

    @Override // com.ecarx.eas.sdk.device.api.IDeviceAPI
    String getSupplierCode();

    List<ComponentName> getSupportedComponents();

    String getVIN();

    String getXDSN();
}
