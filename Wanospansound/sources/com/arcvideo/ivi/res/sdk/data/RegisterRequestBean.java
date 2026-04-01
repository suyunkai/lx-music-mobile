package com.arcvideo.ivi.res.sdk.data;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class RegisterRequestBean {
    private String apkVersion;
    private String deviceUUID;
    private String hardware;
    private String model;
    private String sn;
    private long timestamp;

    public RegisterRequestBean(String sn, String deviceUUID, long j, String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(sn, "sn");
        Intrinsics.checkNotNullParameter(deviceUUID, "deviceUUID");
        this.sn = sn;
        this.deviceUUID = deviceUUID;
        this.timestamp = j;
        this.apkVersion = str;
        this.model = str2;
        this.hardware = str3;
    }

    public static /* synthetic */ RegisterRequestBean copy$default(RegisterRequestBean registerRequestBean, String str, String str2, long j, String str3, String str4, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            str = registerRequestBean.sn;
        }
        if ((i & 2) != 0) {
            str2 = registerRequestBean.deviceUUID;
        }
        String str6 = str2;
        if ((i & 4) != 0) {
            j = registerRequestBean.timestamp;
        }
        long j2 = j;
        if ((i & 8) != 0) {
            str3 = registerRequestBean.apkVersion;
        }
        String str7 = str3;
        if ((i & 16) != 0) {
            str4 = registerRequestBean.model;
        }
        String str8 = str4;
        if ((i & 32) != 0) {
            str5 = registerRequestBean.hardware;
        }
        return registerRequestBean.copy(str, str6, j2, str7, str8, str5);
    }

    public final String component1() {
        return this.sn;
    }

    public final String component2() {
        return this.deviceUUID;
    }

    public final long component3() {
        return this.timestamp;
    }

    public final String component4() {
        return this.apkVersion;
    }

    public final String component5() {
        return this.model;
    }

    public final String component6() {
        return this.hardware;
    }

    public final RegisterRequestBean copy(String sn, String deviceUUID, long j, String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(sn, "sn");
        Intrinsics.checkNotNullParameter(deviceUUID, "deviceUUID");
        return new RegisterRequestBean(sn, deviceUUID, j, str, str2, str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RegisterRequestBean)) {
            return false;
        }
        RegisterRequestBean registerRequestBean = (RegisterRequestBean) obj;
        return Intrinsics.areEqual(this.sn, registerRequestBean.sn) && Intrinsics.areEqual(this.deviceUUID, registerRequestBean.deviceUUID) && this.timestamp == registerRequestBean.timestamp && Intrinsics.areEqual(this.apkVersion, registerRequestBean.apkVersion) && Intrinsics.areEqual(this.model, registerRequestBean.model) && Intrinsics.areEqual(this.hardware, registerRequestBean.hardware);
    }

    public final String getApkVersion() {
        return this.apkVersion;
    }

    public final String getDeviceUUID() {
        return this.deviceUUID;
    }

    public final String getHardware() {
        return this.hardware;
    }

    public final String getModel() {
        return this.model;
    }

    public final String getSn() {
        return this.sn;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public int hashCode() {
        int iHashCode = ((((this.sn.hashCode() * 31) + this.deviceUUID.hashCode()) * 31) + Long.hashCode(this.timestamp)) * 31;
        String str = this.apkVersion;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.model;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.hardware;
        return iHashCode3 + (str3 != null ? str3.hashCode() : 0);
    }

    public final void setApkVersion(String str) {
        this.apkVersion = str;
    }

    public final void setDeviceUUID(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.deviceUUID = str;
    }

    public final void setHardware(String str) {
        this.hardware = str;
    }

    public final void setModel(String str) {
        this.model = str;
    }

    public final void setSn(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.sn = str;
    }

    public final void setTimestamp(long j) {
        this.timestamp = j;
    }

    public String toString() {
        return "RegisterRequestBean(sn=" + this.sn + ", deviceUUID=" + this.deviceUUID + ", timestamp=" + this.timestamp + ", apkVersion=" + this.apkVersion + ", model=" + this.model + ", hardware=" + this.hardware + ')';
    }

    public /* synthetic */ RegisterRequestBean(String str, String str2, long j, String str3, String str4, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, j, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : str4, (i & 32) != 0 ? null : str5);
    }
}
