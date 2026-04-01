package com.arcvideo.ivi.res.sdk.innerbean;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class VipInfoResponse {
    private final int isTvod;
    private final int isVip;
    private final String payMark;
    private final String payMarkUrl;

    public VipInfoResponse(String payMarkUrl, int i, String payMark, int i2) {
        Intrinsics.checkNotNullParameter(payMarkUrl, "payMarkUrl");
        Intrinsics.checkNotNullParameter(payMark, "payMark");
        this.payMarkUrl = payMarkUrl;
        this.isTvod = i;
        this.payMark = payMark;
        this.isVip = i2;
    }

    public static /* synthetic */ VipInfoResponse copy$default(VipInfoResponse vipInfoResponse, String str, int i, String str2, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = vipInfoResponse.payMarkUrl;
        }
        if ((i3 & 2) != 0) {
            i = vipInfoResponse.isTvod;
        }
        if ((i3 & 4) != 0) {
            str2 = vipInfoResponse.payMark;
        }
        if ((i3 & 8) != 0) {
            i2 = vipInfoResponse.isVip;
        }
        return vipInfoResponse.copy(str, i, str2, i2);
    }

    public final String component1() {
        return this.payMarkUrl;
    }

    public final int component2() {
        return this.isTvod;
    }

    public final String component3() {
        return this.payMark;
    }

    public final int component4() {
        return this.isVip;
    }

    public final VipInfoResponse copy(String payMarkUrl, int i, String payMark, int i2) {
        Intrinsics.checkNotNullParameter(payMarkUrl, "payMarkUrl");
        Intrinsics.checkNotNullParameter(payMark, "payMark");
        return new VipInfoResponse(payMarkUrl, i, payMark, i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VipInfoResponse)) {
            return false;
        }
        VipInfoResponse vipInfoResponse = (VipInfoResponse) obj;
        return Intrinsics.areEqual(this.payMarkUrl, vipInfoResponse.payMarkUrl) && this.isTvod == vipInfoResponse.isTvod && Intrinsics.areEqual(this.payMark, vipInfoResponse.payMark) && this.isVip == vipInfoResponse.isVip;
    }

    public final String getPayMark() {
        return this.payMark;
    }

    public final String getPayMarkUrl() {
        return this.payMarkUrl;
    }

    public int hashCode() {
        return (((((this.payMarkUrl.hashCode() * 31) + Integer.hashCode(this.isTvod)) * 31) + this.payMark.hashCode()) * 31) + Integer.hashCode(this.isVip);
    }

    public final int isTvod() {
        return this.isTvod;
    }

    public final int isVip() {
        return this.isVip;
    }

    public String toString() {
        return "VipInfoResponse(payMarkUrl=" + this.payMarkUrl + ", isTvod=" + this.isTvod + ", payMark=" + this.payMark + ", isVip=" + this.isVip + ')';
    }
}
