package com.arcvideo.ivi.res.sdk.innerbean;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class PingBack {
    private final String abtest;
    private final String bkt;
    private final String e;
    private final String r_area;

    public PingBack(String bkt, String e, String abtest, String r_area) {
        Intrinsics.checkNotNullParameter(bkt, "bkt");
        Intrinsics.checkNotNullParameter(e, "e");
        Intrinsics.checkNotNullParameter(abtest, "abtest");
        Intrinsics.checkNotNullParameter(r_area, "r_area");
        this.bkt = bkt;
        this.e = e;
        this.abtest = abtest;
        this.r_area = r_area;
    }

    public static /* synthetic */ PingBack copy$default(PingBack pingBack, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = pingBack.bkt;
        }
        if ((i & 2) != 0) {
            str2 = pingBack.e;
        }
        if ((i & 4) != 0) {
            str3 = pingBack.abtest;
        }
        if ((i & 8) != 0) {
            str4 = pingBack.r_area;
        }
        return pingBack.copy(str, str2, str3, str4);
    }

    public final String component1() {
        return this.bkt;
    }

    public final String component2() {
        return this.e;
    }

    public final String component3() {
        return this.abtest;
    }

    public final String component4() {
        return this.r_area;
    }

    public final PingBack copy(String bkt, String e, String abtest, String r_area) {
        Intrinsics.checkNotNullParameter(bkt, "bkt");
        Intrinsics.checkNotNullParameter(e, "e");
        Intrinsics.checkNotNullParameter(abtest, "abtest");
        Intrinsics.checkNotNullParameter(r_area, "r_area");
        return new PingBack(bkt, e, abtest, r_area);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PingBack)) {
            return false;
        }
        PingBack pingBack = (PingBack) obj;
        return Intrinsics.areEqual(this.bkt, pingBack.bkt) && Intrinsics.areEqual(this.e, pingBack.e) && Intrinsics.areEqual(this.abtest, pingBack.abtest) && Intrinsics.areEqual(this.r_area, pingBack.r_area);
    }

    public final String getAbtest() {
        return this.abtest;
    }

    public final String getBkt() {
        return this.bkt;
    }

    public final String getE() {
        return this.e;
    }

    public final String getR_area() {
        return this.r_area;
    }

    public int hashCode() {
        return (((((this.bkt.hashCode() * 31) + this.e.hashCode()) * 31) + this.abtest.hashCode()) * 31) + this.r_area.hashCode();
    }

    public String toString() {
        return "PingBack(bkt=" + this.bkt + ", e=" + this.e + ", abtest=" + this.abtest + ", r_area=" + this.r_area + ')';
    }
}
