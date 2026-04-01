package com.arcvideo.ivi.res.sdk.data;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class AuthInfo {
    private final String appInfo;
    private final String clientActivateTime;
    private final String createTime;
    private final String expirationTime;
    private final String forcedActivateTime;
    private final boolean inProductWhitelist;
    private final boolean inRenewalWhitelist;
    private final String preAuthActivateTime;
    private final String productActivateTime;
    private final String productID;
    private final String renewalActivateTime;
    private final String sn;
    private final int status;
    private final String testAuthInfo;
    private final String userId;
    private final String vin;

    public AuthInfo(String productID, String vin, String sn, int i, String productActivateTime, String preAuthActivateTime, String forcedActivateTime, String clientActivateTime, String renewalActivateTime, boolean z, boolean z2, String userId, String str, String expirationTime, String createTime, String testAuthInfo) {
        Intrinsics.checkNotNullParameter(productID, "productID");
        Intrinsics.checkNotNullParameter(vin, "vin");
        Intrinsics.checkNotNullParameter(sn, "sn");
        Intrinsics.checkNotNullParameter(productActivateTime, "productActivateTime");
        Intrinsics.checkNotNullParameter(preAuthActivateTime, "preAuthActivateTime");
        Intrinsics.checkNotNullParameter(forcedActivateTime, "forcedActivateTime");
        Intrinsics.checkNotNullParameter(clientActivateTime, "clientActivateTime");
        Intrinsics.checkNotNullParameter(renewalActivateTime, "renewalActivateTime");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(expirationTime, "expirationTime");
        Intrinsics.checkNotNullParameter(createTime, "createTime");
        Intrinsics.checkNotNullParameter(testAuthInfo, "testAuthInfo");
        this.productID = productID;
        this.vin = vin;
        this.sn = sn;
        this.status = i;
        this.productActivateTime = productActivateTime;
        this.preAuthActivateTime = preAuthActivateTime;
        this.forcedActivateTime = forcedActivateTime;
        this.clientActivateTime = clientActivateTime;
        this.renewalActivateTime = renewalActivateTime;
        this.inProductWhitelist = z;
        this.inRenewalWhitelist = z2;
        this.userId = userId;
        this.appInfo = str;
        this.expirationTime = expirationTime;
        this.createTime = createTime;
        this.testAuthInfo = testAuthInfo;
    }

    public final String component1() {
        return this.productID;
    }

    public final boolean component10() {
        return this.inProductWhitelist;
    }

    public final boolean component11() {
        return this.inRenewalWhitelist;
    }

    public final String component12() {
        return this.userId;
    }

    public final String component13() {
        return this.appInfo;
    }

    public final String component14() {
        return this.expirationTime;
    }

    public final String component15() {
        return this.createTime;
    }

    public final String component16() {
        return this.testAuthInfo;
    }

    public final String component2() {
        return this.vin;
    }

    public final String component3() {
        return this.sn;
    }

    public final int component4() {
        return this.status;
    }

    public final String component5() {
        return this.productActivateTime;
    }

    public final String component6() {
        return this.preAuthActivateTime;
    }

    public final String component7() {
        return this.forcedActivateTime;
    }

    public final String component8() {
        return this.clientActivateTime;
    }

    public final String component9() {
        return this.renewalActivateTime;
    }

    public final AuthInfo copy(String productID, String vin, String sn, int i, String productActivateTime, String preAuthActivateTime, String forcedActivateTime, String clientActivateTime, String renewalActivateTime, boolean z, boolean z2, String userId, String str, String expirationTime, String createTime, String testAuthInfo) {
        Intrinsics.checkNotNullParameter(productID, "productID");
        Intrinsics.checkNotNullParameter(vin, "vin");
        Intrinsics.checkNotNullParameter(sn, "sn");
        Intrinsics.checkNotNullParameter(productActivateTime, "productActivateTime");
        Intrinsics.checkNotNullParameter(preAuthActivateTime, "preAuthActivateTime");
        Intrinsics.checkNotNullParameter(forcedActivateTime, "forcedActivateTime");
        Intrinsics.checkNotNullParameter(clientActivateTime, "clientActivateTime");
        Intrinsics.checkNotNullParameter(renewalActivateTime, "renewalActivateTime");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(expirationTime, "expirationTime");
        Intrinsics.checkNotNullParameter(createTime, "createTime");
        Intrinsics.checkNotNullParameter(testAuthInfo, "testAuthInfo");
        return new AuthInfo(productID, vin, sn, i, productActivateTime, preAuthActivateTime, forcedActivateTime, clientActivateTime, renewalActivateTime, z, z2, userId, str, expirationTime, createTime, testAuthInfo);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AuthInfo)) {
            return false;
        }
        AuthInfo authInfo = (AuthInfo) obj;
        return Intrinsics.areEqual(this.productID, authInfo.productID) && Intrinsics.areEqual(this.vin, authInfo.vin) && Intrinsics.areEqual(this.sn, authInfo.sn) && this.status == authInfo.status && Intrinsics.areEqual(this.productActivateTime, authInfo.productActivateTime) && Intrinsics.areEqual(this.preAuthActivateTime, authInfo.preAuthActivateTime) && Intrinsics.areEqual(this.forcedActivateTime, authInfo.forcedActivateTime) && Intrinsics.areEqual(this.clientActivateTime, authInfo.clientActivateTime) && Intrinsics.areEqual(this.renewalActivateTime, authInfo.renewalActivateTime) && this.inProductWhitelist == authInfo.inProductWhitelist && this.inRenewalWhitelist == authInfo.inRenewalWhitelist && Intrinsics.areEqual(this.userId, authInfo.userId) && Intrinsics.areEqual(this.appInfo, authInfo.appInfo) && Intrinsics.areEqual(this.expirationTime, authInfo.expirationTime) && Intrinsics.areEqual(this.createTime, authInfo.createTime) && Intrinsics.areEqual(this.testAuthInfo, authInfo.testAuthInfo);
    }

    public final String getAppInfo() {
        return this.appInfo;
    }

    public final String getClientActivateTime() {
        return this.clientActivateTime;
    }

    public final String getCreateTime() {
        return this.createTime;
    }

    public final String getExpirationTime() {
        return this.expirationTime;
    }

    public final String getForcedActivateTime() {
        return this.forcedActivateTime;
    }

    public final boolean getInProductWhitelist() {
        return this.inProductWhitelist;
    }

    public final boolean getInRenewalWhitelist() {
        return this.inRenewalWhitelist;
    }

    public final String getPreAuthActivateTime() {
        return this.preAuthActivateTime;
    }

    public final String getProductActivateTime() {
        return this.productActivateTime;
    }

    public final String getProductID() {
        return this.productID;
    }

    public final String getRenewalActivateTime() {
        return this.renewalActivateTime;
    }

    public final String getSn() {
        return this.sn;
    }

    public final int getStatus() {
        return this.status;
    }

    public final String getTestAuthInfo() {
        return this.testAuthInfo;
    }

    public final String getUserId() {
        return this.userId;
    }

    public final String getVin() {
        return this.vin;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v17, types: [int] */
    /* JADX WARN: Type inference failed for: r1v31 */
    /* JADX WARN: Type inference failed for: r1v32 */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [int] */
    /* JADX WARN: Type inference failed for: r2v2 */
    public int hashCode() {
        int iHashCode = ((((((((((((((((this.productID.hashCode() * 31) + this.vin.hashCode()) * 31) + this.sn.hashCode()) * 31) + Integer.hashCode(this.status)) * 31) + this.productActivateTime.hashCode()) * 31) + this.preAuthActivateTime.hashCode()) * 31) + this.forcedActivateTime.hashCode()) * 31) + this.clientActivateTime.hashCode()) * 31) + this.renewalActivateTime.hashCode()) * 31;
        boolean z = this.inProductWhitelist;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        int i = (iHashCode + r1) * 31;
        boolean z2 = this.inRenewalWhitelist;
        int iHashCode2 = (((i + (z2 ? 1 : z2)) * 31) + this.userId.hashCode()) * 31;
        String str = this.appInfo;
        return ((((((iHashCode2 + (str == null ? 0 : str.hashCode())) * 31) + this.expirationTime.hashCode()) * 31) + this.createTime.hashCode()) * 31) + this.testAuthInfo.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("AuthInfo(productID=");
        sb.append(this.productID).append(", vin=").append(this.vin).append(", sn=").append(this.sn).append(", status=").append(this.status).append(", productActivateTime=").append(this.productActivateTime).append(", preAuthActivateTime=").append(this.preAuthActivateTime).append(", forcedActivateTime=").append(this.forcedActivateTime).append(", clientActivateTime=").append(this.clientActivateTime).append(", renewalActivateTime=").append(this.renewalActivateTime).append(", inProductWhitelist=").append(this.inProductWhitelist).append(", inRenewalWhitelist=").append(this.inRenewalWhitelist).append(", userId=");
        sb.append(this.userId).append(", appInfo=").append(this.appInfo).append(", expirationTime=").append(this.expirationTime).append(", createTime=").append(this.createTime).append(", testAuthInfo=").append(this.testAuthInfo).append(')');
        return sb.toString();
    }
}
