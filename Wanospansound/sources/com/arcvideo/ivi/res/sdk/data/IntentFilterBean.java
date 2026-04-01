package com.arcvideo.ivi.res.sdk.data;

import com.arcvideo.ivi.res.sdk.innerbean.VideoResponse;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class IntentFilterBean {
    private final String bkt;
    private final List<VideoResponse> epgs;
    private final String eventId;
    private final HitTagData hitTagData;
    private final int total;

    public IntentFilterBean(String bkt, List<VideoResponse> list, String eventId, HitTagData hitTagData, int i) {
        Intrinsics.checkNotNullParameter(bkt, "bkt");
        Intrinsics.checkNotNullParameter(eventId, "eventId");
        Intrinsics.checkNotNullParameter(hitTagData, "hitTagData");
        this.bkt = bkt;
        this.epgs = list;
        this.eventId = eventId;
        this.hitTagData = hitTagData;
        this.total = i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ IntentFilterBean copy$default(IntentFilterBean intentFilterBean, String str, List list, String str2, HitTagData hitTagData, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = intentFilterBean.bkt;
        }
        if ((i2 & 2) != 0) {
            list = intentFilterBean.epgs;
        }
        List list2 = list;
        if ((i2 & 4) != 0) {
            str2 = intentFilterBean.eventId;
        }
        String str3 = str2;
        if ((i2 & 8) != 0) {
            hitTagData = intentFilterBean.hitTagData;
        }
        HitTagData hitTagData2 = hitTagData;
        if ((i2 & 16) != 0) {
            i = intentFilterBean.total;
        }
        return intentFilterBean.copy(str, list2, str3, hitTagData2, i);
    }

    public final String component1() {
        return this.bkt;
    }

    public final List<VideoResponse> component2() {
        return this.epgs;
    }

    public final String component3() {
        return this.eventId;
    }

    public final HitTagData component4() {
        return this.hitTagData;
    }

    public final int component5() {
        return this.total;
    }

    public final IntentFilterBean copy(String bkt, List<VideoResponse> list, String eventId, HitTagData hitTagData, int i) {
        Intrinsics.checkNotNullParameter(bkt, "bkt");
        Intrinsics.checkNotNullParameter(eventId, "eventId");
        Intrinsics.checkNotNullParameter(hitTagData, "hitTagData");
        return new IntentFilterBean(bkt, list, eventId, hitTagData, i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IntentFilterBean)) {
            return false;
        }
        IntentFilterBean intentFilterBean = (IntentFilterBean) obj;
        return Intrinsics.areEqual(this.bkt, intentFilterBean.bkt) && Intrinsics.areEqual(this.epgs, intentFilterBean.epgs) && Intrinsics.areEqual(this.eventId, intentFilterBean.eventId) && Intrinsics.areEqual(this.hitTagData, intentFilterBean.hitTagData) && this.total == intentFilterBean.total;
    }

    public final String getBkt() {
        return this.bkt;
    }

    public final List<VideoResponse> getEpgs() {
        return this.epgs;
    }

    public final String getEventId() {
        return this.eventId;
    }

    public final HitTagData getHitTagData() {
        return this.hitTagData;
    }

    public final int getTotal() {
        return this.total;
    }

    public int hashCode() {
        int iHashCode = this.bkt.hashCode() * 31;
        List<VideoResponse> list = this.epgs;
        return ((((((iHashCode + (list == null ? 0 : list.hashCode())) * 31) + this.eventId.hashCode()) * 31) + this.hitTagData.hashCode()) * 31) + Integer.hashCode(this.total);
    }

    public String toString() {
        return "IntentFilterBean(bkt=" + this.bkt + ", epgs=" + this.epgs + ", eventId=" + this.eventId + ", hitTagData=" + this.hitTagData + ", total=" + this.total + ')';
    }
}
