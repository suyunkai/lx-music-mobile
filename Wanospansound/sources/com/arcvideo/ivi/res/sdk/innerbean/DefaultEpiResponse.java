package com.arcvideo.ivi.res.sdk.innerbean;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DefaultEpiResponse {
    private final int contentType;
    private final int is3D;
    private final int len;
    private final String name;
    private final String publishTime;
    private final String qipuId;

    public DefaultEpiResponse(String qipuId, String name, int i, int i2, String publishTime, int i3) {
        Intrinsics.checkNotNullParameter(qipuId, "qipuId");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(publishTime, "publishTime");
        this.qipuId = qipuId;
        this.name = name;
        this.is3D = i;
        this.contentType = i2;
        this.publishTime = publishTime;
        this.len = i3;
    }

    public static /* synthetic */ DefaultEpiResponse copy$default(DefaultEpiResponse defaultEpiResponse, String str, String str2, int i, int i2, String str3, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            str = defaultEpiResponse.qipuId;
        }
        if ((i4 & 2) != 0) {
            str2 = defaultEpiResponse.name;
        }
        String str4 = str2;
        if ((i4 & 4) != 0) {
            i = defaultEpiResponse.is3D;
        }
        int i5 = i;
        if ((i4 & 8) != 0) {
            i2 = defaultEpiResponse.contentType;
        }
        int i6 = i2;
        if ((i4 & 16) != 0) {
            str3 = defaultEpiResponse.publishTime;
        }
        String str5 = str3;
        if ((i4 & 32) != 0) {
            i3 = defaultEpiResponse.len;
        }
        return defaultEpiResponse.copy(str, str4, i5, i6, str5, i3);
    }

    public final String component1() {
        return this.qipuId;
    }

    public final String component2() {
        return this.name;
    }

    public final int component3() {
        return this.is3D;
    }

    public final int component4() {
        return this.contentType;
    }

    public final String component5() {
        return this.publishTime;
    }

    public final int component6() {
        return this.len;
    }

    public final DefaultEpiResponse copy(String qipuId, String name, int i, int i2, String publishTime, int i3) {
        Intrinsics.checkNotNullParameter(qipuId, "qipuId");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(publishTime, "publishTime");
        return new DefaultEpiResponse(qipuId, name, i, i2, publishTime, i3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DefaultEpiResponse)) {
            return false;
        }
        DefaultEpiResponse defaultEpiResponse = (DefaultEpiResponse) obj;
        return Intrinsics.areEqual(this.qipuId, defaultEpiResponse.qipuId) && Intrinsics.areEqual(this.name, defaultEpiResponse.name) && this.is3D == defaultEpiResponse.is3D && this.contentType == defaultEpiResponse.contentType && Intrinsics.areEqual(this.publishTime, defaultEpiResponse.publishTime) && this.len == defaultEpiResponse.len;
    }

    public final int getContentType() {
        return this.contentType;
    }

    public final int getLen() {
        return this.len;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPublishTime() {
        return this.publishTime;
    }

    public final String getQipuId() {
        return this.qipuId;
    }

    public int hashCode() {
        return (((((((((this.qipuId.hashCode() * 31) + this.name.hashCode()) * 31) + Integer.hashCode(this.is3D)) * 31) + Integer.hashCode(this.contentType)) * 31) + this.publishTime.hashCode()) * 31) + Integer.hashCode(this.len);
    }

    public final int is3D() {
        return this.is3D;
    }

    public String toString() {
        return "DefaultEpiResponse(qipuId=" + this.qipuId + ", name=" + this.name + ", is3D=" + this.is3D + ", contentType=" + this.contentType + ", publishTime=" + this.publishTime + ", len=" + this.len + ')';
    }
}
