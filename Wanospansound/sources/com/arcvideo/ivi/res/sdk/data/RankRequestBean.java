package com.arcvideo.ivi.res.sdk.data;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class RankRequestBean {
    private int chnId;
    private int isVip;
    private String type;

    public RankRequestBean() {
        this(0, 0, null, 7, null);
    }

    public RankRequestBean(int i, int i2, String type) {
        Intrinsics.checkNotNullParameter(type, "type");
        this.chnId = i;
        this.isVip = i2;
        this.type = type;
    }

    public static /* synthetic */ RankRequestBean copy$default(RankRequestBean rankRequestBean, int i, int i2, String str, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = rankRequestBean.chnId;
        }
        if ((i3 & 2) != 0) {
            i2 = rankRequestBean.isVip;
        }
        if ((i3 & 4) != 0) {
            str = rankRequestBean.type;
        }
        return rankRequestBean.copy(i, i2, str);
    }

    public final int component1() {
        return this.chnId;
    }

    public final int component2() {
        return this.isVip;
    }

    public final String component3() {
        return this.type;
    }

    public final RankRequestBean copy(int i, int i2, String type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return new RankRequestBean(i, i2, type);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RankRequestBean)) {
            return false;
        }
        RankRequestBean rankRequestBean = (RankRequestBean) obj;
        return this.chnId == rankRequestBean.chnId && this.isVip == rankRequestBean.isVip && Intrinsics.areEqual(this.type, rankRequestBean.type);
    }

    public final int getChnId() {
        return this.chnId;
    }

    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.chnId) * 31) + Integer.hashCode(this.isVip)) * 31) + this.type.hashCode();
    }

    public final int isVip() {
        return this.isVip;
    }

    public final void setChnId(int i) {
        this.chnId = i;
    }

    public final void setType(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.type = str;
    }

    public final void setVip(int i) {
        this.isVip = i;
    }

    public String toString() {
        return "RankRequestBean(chnId=" + this.chnId + ", isVip=" + this.isVip + ", type=" + this.type + ')';
    }

    public /* synthetic */ RankRequestBean(int i, int i2, String str, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? -1 : i, (i3 & 2) != 0 ? 0 : i2, (i3 & 4) != 0 ? "hot" : str);
    }
}
