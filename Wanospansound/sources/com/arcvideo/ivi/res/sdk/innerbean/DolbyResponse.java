package com.arcvideo.ivi.res.sdk.innerbean;

import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DolbyResponse {
    private final List<VideoResponse> epg;
    private final TagResponse tag;
    private final int total;

    public DolbyResponse(int i, TagResponse tagResponse, List<VideoResponse> list) {
        this.total = i;
        this.tag = tagResponse;
        this.epg = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DolbyResponse copy$default(DolbyResponse dolbyResponse, int i, TagResponse tagResponse, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = dolbyResponse.total;
        }
        if ((i2 & 2) != 0) {
            tagResponse = dolbyResponse.tag;
        }
        if ((i2 & 4) != 0) {
            list = dolbyResponse.epg;
        }
        return dolbyResponse.copy(i, tagResponse, list);
    }

    public final int component1() {
        return this.total;
    }

    public final TagResponse component2() {
        return this.tag;
    }

    public final List<VideoResponse> component3() {
        return this.epg;
    }

    public final DolbyResponse copy(int i, TagResponse tagResponse, List<VideoResponse> list) {
        return new DolbyResponse(i, tagResponse, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DolbyResponse)) {
            return false;
        }
        DolbyResponse dolbyResponse = (DolbyResponse) obj;
        return this.total == dolbyResponse.total && Intrinsics.areEqual(this.tag, dolbyResponse.tag) && Intrinsics.areEqual(this.epg, dolbyResponse.epg);
    }

    public final List<VideoResponse> getEpg() {
        return this.epg;
    }

    public final TagResponse getTag() {
        return this.tag;
    }

    public final int getTotal() {
        return this.total;
    }

    public int hashCode() {
        int iHashCode = Integer.hashCode(this.total) * 31;
        TagResponse tagResponse = this.tag;
        int iHashCode2 = (iHashCode + (tagResponse == null ? 0 : tagResponse.hashCode())) * 31;
        List<VideoResponse> list = this.epg;
        return iHashCode2 + (list != null ? list.hashCode() : 0);
    }

    public String toString() {
        return "DolbyResponse(total=" + this.total + ", tag=" + this.tag + ", epg=" + this.epg + ')';
    }

    public /* synthetic */ DolbyResponse(int i, TagResponse tagResponse, List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? null : tagResponse, (i2 & 4) != 0 ? null : list);
    }
}
