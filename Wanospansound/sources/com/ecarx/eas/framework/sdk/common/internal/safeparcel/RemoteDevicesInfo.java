package com.ecarx.eas.framework.sdk.common.internal.safeparcel;

import android.os.Parcelable;
import com.ecarx.eas.framework.sdk.common.internal.safeparcel.AutoSafeParcelable;
import com.ecarx.eas.framework.sdk.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes2.dex */
public class RemoteDevicesInfo extends AutoSafeParcelable {
    public static final Parcelable.Creator<RemoteDevicesInfo> CREATOR = new AutoSafeParcelable.AutoCreator(RemoteDevicesInfo.class);

    @SafeParcelable.Field(1)
    public String serviceName;

    public String toString() {
        return "RemoteDevicesInfo{serviceName='" + this.serviceName + "'}";
    }
}
