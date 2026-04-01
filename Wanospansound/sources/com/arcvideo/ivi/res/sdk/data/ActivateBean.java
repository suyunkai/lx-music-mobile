package com.arcvideo.ivi.res.sdk.data;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class ActivateBean {
    private String productId;
    private String sn;
    private String vehicleModelId;
    private String vin;

    public ActivateBean(String vin, String sn, String productId, String vehicleModelId) {
        Intrinsics.checkNotNullParameter(vin, "vin");
        Intrinsics.checkNotNullParameter(sn, "sn");
        Intrinsics.checkNotNullParameter(productId, "productId");
        Intrinsics.checkNotNullParameter(vehicleModelId, "vehicleModelId");
        this.vin = vin;
        this.sn = sn;
        this.productId = productId;
        this.vehicleModelId = vehicleModelId;
    }

    public static /* synthetic */ ActivateBean copy$default(ActivateBean activateBean, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = activateBean.vin;
        }
        if ((i & 2) != 0) {
            str2 = activateBean.sn;
        }
        if ((i & 4) != 0) {
            str3 = activateBean.productId;
        }
        if ((i & 8) != 0) {
            str4 = activateBean.vehicleModelId;
        }
        return activateBean.copy(str, str2, str3, str4);
    }

    public final String component1() {
        return this.vin;
    }

    public final String component2() {
        return this.sn;
    }

    public final String component3() {
        return this.productId;
    }

    public final String component4() {
        return this.vehicleModelId;
    }

    public final ActivateBean copy(String vin, String sn, String productId, String vehicleModelId) {
        Intrinsics.checkNotNullParameter(vin, "vin");
        Intrinsics.checkNotNullParameter(sn, "sn");
        Intrinsics.checkNotNullParameter(productId, "productId");
        Intrinsics.checkNotNullParameter(vehicleModelId, "vehicleModelId");
        return new ActivateBean(vin, sn, productId, vehicleModelId);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ActivateBean)) {
            return false;
        }
        ActivateBean activateBean = (ActivateBean) obj;
        return Intrinsics.areEqual(this.vin, activateBean.vin) && Intrinsics.areEqual(this.sn, activateBean.sn) && Intrinsics.areEqual(this.productId, activateBean.productId) && Intrinsics.areEqual(this.vehicleModelId, activateBean.vehicleModelId);
    }

    public final String getProductId() {
        return this.productId;
    }

    public final String getSn() {
        return this.sn;
    }

    public final String getVehicleModelId() {
        return this.vehicleModelId;
    }

    public final String getVin() {
        return this.vin;
    }

    public int hashCode() {
        return (((((this.vin.hashCode() * 31) + this.sn.hashCode()) * 31) + this.productId.hashCode()) * 31) + this.vehicleModelId.hashCode();
    }

    public final void setProductId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.productId = str;
    }

    public final void setSn(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.sn = str;
    }

    public final void setVehicleModelId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.vehicleModelId = str;
    }

    public final void setVin(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.vin = str;
    }

    public String toString() {
        return "ActivateBean(vin=" + this.vin + ", sn=" + this.sn + ", productId=" + this.productId + ", vehicleModelId=" + this.vehicleModelId + ')';
    }
}
