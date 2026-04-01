package com.arcvideo.ivi.res.sdk.data;

import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class ResultInfo {
    private Integer mixedCount;
    private Long sourceCode;
    private List<VideoTagInfo> tags;
    private Integer total;
    private List<VideoInfo> videoInfoList;

    public ResultInfo() {
        this(null, null, null, null, null, 31, null);
    }

    public ResultInfo(Integer num, Integer num2, Long l, List<VideoInfo> list, List<VideoTagInfo> list2) {
        this.total = num;
        this.mixedCount = num2;
        this.sourceCode = l;
        this.videoInfoList = list;
        this.tags = list2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ResultInfo copy$default(ResultInfo resultInfo, Integer num, Integer num2, Long l, List list, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            num = resultInfo.total;
        }
        if ((i & 2) != 0) {
            num2 = resultInfo.mixedCount;
        }
        Integer num3 = num2;
        if ((i & 4) != 0) {
            l = resultInfo.sourceCode;
        }
        Long l2 = l;
        if ((i & 8) != 0) {
            list = resultInfo.videoInfoList;
        }
        List list3 = list;
        if ((i & 16) != 0) {
            list2 = resultInfo.tags;
        }
        return resultInfo.copy(num, num3, l2, list3, list2);
    }

    public final Integer component1() {
        return this.total;
    }

    public final Integer component2() {
        return this.mixedCount;
    }

    public final Long component3() {
        return this.sourceCode;
    }

    public final List<VideoInfo> component4() {
        return this.videoInfoList;
    }

    public final List<VideoTagInfo> component5() {
        return this.tags;
    }

    public final ResultInfo copy(Integer num, Integer num2, Long l, List<VideoInfo> list, List<VideoTagInfo> list2) {
        return new ResultInfo(num, num2, l, list, list2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResultInfo)) {
            return false;
        }
        ResultInfo resultInfo = (ResultInfo) obj;
        return Intrinsics.areEqual(this.total, resultInfo.total) && Intrinsics.areEqual(this.mixedCount, resultInfo.mixedCount) && Intrinsics.areEqual(this.sourceCode, resultInfo.sourceCode) && Intrinsics.areEqual(this.videoInfoList, resultInfo.videoInfoList) && Intrinsics.areEqual(this.tags, resultInfo.tags);
    }

    public final Integer getMixedCount() {
        return this.mixedCount;
    }

    public final Long getSourceCode() {
        return this.sourceCode;
    }

    public final List<VideoTagInfo> getTags() {
        return this.tags;
    }

    public final Integer getTotal() {
        return this.total;
    }

    public final List<VideoInfo> getVideoInfoList() {
        return this.videoInfoList;
    }

    public int hashCode() {
        Integer num = this.total;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        Integer num2 = this.mixedCount;
        int iHashCode2 = (iHashCode + (num2 == null ? 0 : num2.hashCode())) * 31;
        Long l = this.sourceCode;
        int iHashCode3 = (iHashCode2 + (l == null ? 0 : l.hashCode())) * 31;
        List<VideoInfo> list = this.videoInfoList;
        int iHashCode4 = (iHashCode3 + (list == null ? 0 : list.hashCode())) * 31;
        List<VideoTagInfo> list2 = this.tags;
        return iHashCode4 + (list2 != null ? list2.hashCode() : 0);
    }

    public final void setMixedCount(Integer num) {
        this.mixedCount = num;
    }

    public final void setSourceCode(Long l) {
        this.sourceCode = l;
    }

    public final void setTags(List<VideoTagInfo> list) {
        this.tags = list;
    }

    public final void setTotal(Integer num) {
        this.total = num;
    }

    public final void setVideoInfoList(List<VideoInfo> list) {
        this.videoInfoList = list;
    }

    public String toString() {
        return "ResultInfo(total=" + this.total + ", mixedCount=" + this.mixedCount + ", sourceCode=" + this.sourceCode + ", videoInfoList=" + this.videoInfoList + ", tags=" + this.tags + ')';
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ ResultInfo(Integer num, Integer num2, Long l, List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? num : num, (i & 2) == 0 ? num2 : 0, (i & 4) != 0 ? 0L : l, (i & 8) != 0 ? null : list, (i & 16) != 0 ? null : list2);
    }
}
