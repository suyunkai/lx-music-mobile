package com.ecarx.eas.framework.sdk.common.internal.safeparcel;

import android.os.Parcelable;
import com.ecarx.eas.framework.sdk.common.internal.safeparcel.AutoSafeParcelable;
import com.ecarx.eas.framework.sdk.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ServiceVersionInfo extends AutoSafeParcelable {
    public static final Parcelable.Creator<ServiceVersionInfo> CREATOR = new AutoSafeParcelable.AutoCreator(ServiceVersionInfo.class);
    public static final int PLUGIN_SERVICE_V3 = 3;
    public static final int PLUGIN_SERVICE_V4 = 4;
    public static final int SYSTEM_SERVICE_V3 = 1;
    public static final int SYSTEM_SERVICE_V4 = 2;
    static final int UNKNOWN = -1;

    @SafeParcelable.Field(1)
    public String name;

    @SafeParcelable.Field(subClass = VersionInfo.class, value = 2)
    public List<VersionInfo> versionInfos = new ArrayList();

    public String toString() {
        return "ServiceVersionInfo{name='" + this.name + "', versionInfos=" + this.versionInfos + '}';
    }
}
