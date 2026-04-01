package com.ecarx.eas.framework.sdk.common.internal.safeparcel;

import android.os.Parcelable;
import com.ecarx.eas.framework.sdk.common.internal.safeparcel.AutoSafeParcelable;
import com.ecarx.eas.framework.sdk.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes2.dex */
public class VersionInfo extends AutoSafeParcelable {
    public static final Parcelable.Creator<VersionInfo> CREATOR = new AutoSafeParcelable.AutoCreator(VersionInfo.class);

    @SafeParcelable.Field(5)
    public String easVersionFingerprint;

    @SafeParcelable.Field(1)
    public int type;

    @SafeParcelable.Field(2)
    public int version;

    @SafeParcelable.Field(3)
    public int versionCode;

    @SafeParcelable.Field(4)
    public String versionName;

    public String toString() {
        return "VersionInfo{type=" + this.type + ", version=" + this.version + ", versionCode=" + this.versionCode + ", versionName='" + this.versionName + "', easVersionFingerprint='" + this.easVersionFingerprint + "'}";
    }
}
