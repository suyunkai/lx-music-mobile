package com.arcvideo.ivi.res.sdk.data;

import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class IntentDatas {
    private List<CustomCategory> customCategories;
    private List<GraphCategory> graphCategories;
    private Integer intentResultNum;
    private Integer mode;
    private List<TermQuery> termQuery;
    private List<VideoInfo> videoInfo;

    public IntentDatas() {
        this(null, null, null, null, null, null, 63, null);
    }

    public IntentDatas(List<CustomCategory> list, List<VideoInfo> list2, List<GraphCategory> list3, Integer num, Integer num2, List<TermQuery> list4) {
        this.customCategories = list;
        this.videoInfo = list2;
        this.graphCategories = list3;
        this.intentResultNum = num;
        this.mode = num2;
        this.termQuery = list4;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ IntentDatas copy$default(IntentDatas intentDatas, List list, List list2, List list3, Integer num, Integer num2, List list4, int i, Object obj) {
        if ((i & 1) != 0) {
            list = intentDatas.customCategories;
        }
        if ((i & 2) != 0) {
            list2 = intentDatas.videoInfo;
        }
        List list5 = list2;
        if ((i & 4) != 0) {
            list3 = intentDatas.graphCategories;
        }
        List list6 = list3;
        if ((i & 8) != 0) {
            num = intentDatas.intentResultNum;
        }
        Integer num3 = num;
        if ((i & 16) != 0) {
            num2 = intentDatas.mode;
        }
        Integer num4 = num2;
        if ((i & 32) != 0) {
            list4 = intentDatas.termQuery;
        }
        return intentDatas.copy(list, list5, list6, num3, num4, list4);
    }

    public final List<CustomCategory> component1() {
        return this.customCategories;
    }

    public final List<VideoInfo> component2() {
        return this.videoInfo;
    }

    public final List<GraphCategory> component3() {
        return this.graphCategories;
    }

    public final Integer component4() {
        return this.intentResultNum;
    }

    public final Integer component5() {
        return this.mode;
    }

    public final List<TermQuery> component6() {
        return this.termQuery;
    }

    public final IntentDatas copy(List<CustomCategory> list, List<VideoInfo> list2, List<GraphCategory> list3, Integer num, Integer num2, List<TermQuery> list4) {
        return new IntentDatas(list, list2, list3, num, num2, list4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IntentDatas)) {
            return false;
        }
        IntentDatas intentDatas = (IntentDatas) obj;
        return Intrinsics.areEqual(this.customCategories, intentDatas.customCategories) && Intrinsics.areEqual(this.videoInfo, intentDatas.videoInfo) && Intrinsics.areEqual(this.graphCategories, intentDatas.graphCategories) && Intrinsics.areEqual(this.intentResultNum, intentDatas.intentResultNum) && Intrinsics.areEqual(this.mode, intentDatas.mode) && Intrinsics.areEqual(this.termQuery, intentDatas.termQuery);
    }

    public final List<CustomCategory> getCustomCategories() {
        return this.customCategories;
    }

    public final List<GraphCategory> getGraphCategories() {
        return this.graphCategories;
    }

    public final Integer getIntentResultNum() {
        return this.intentResultNum;
    }

    public final Integer getMode() {
        return this.mode;
    }

    public final List<TermQuery> getTermQuery() {
        return this.termQuery;
    }

    public final List<VideoInfo> getVideoInfo() {
        return this.videoInfo;
    }

    public int hashCode() {
        List<CustomCategory> list = this.customCategories;
        int iHashCode = (list == null ? 0 : list.hashCode()) * 31;
        List<VideoInfo> list2 = this.videoInfo;
        int iHashCode2 = (iHashCode + (list2 == null ? 0 : list2.hashCode())) * 31;
        List<GraphCategory> list3 = this.graphCategories;
        int iHashCode3 = (iHashCode2 + (list3 == null ? 0 : list3.hashCode())) * 31;
        Integer num = this.intentResultNum;
        int iHashCode4 = (iHashCode3 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.mode;
        int iHashCode5 = (iHashCode4 + (num2 == null ? 0 : num2.hashCode())) * 31;
        List<TermQuery> list4 = this.termQuery;
        return iHashCode5 + (list4 != null ? list4.hashCode() : 0);
    }

    public final void setCustomCategories(List<CustomCategory> list) {
        this.customCategories = list;
    }

    public final void setGraphCategories(List<GraphCategory> list) {
        this.graphCategories = list;
    }

    public final void setIntentResultNum(Integer num) {
        this.intentResultNum = num;
    }

    public final void setMode(Integer num) {
        this.mode = num;
    }

    public final void setTermQuery(List<TermQuery> list) {
        this.termQuery = list;
    }

    public final void setVideoInfo(List<VideoInfo> list) {
        this.videoInfo = list;
    }

    public String toString() {
        return "IntentDatas(customCategories=" + this.customCategories + ", videoInfo=" + this.videoInfo + ", graphCategories=" + this.graphCategories + ", intentResultNum=" + this.intentResultNum + ", mode=" + this.mode + ", termQuery=" + this.termQuery + ')';
    }

    public /* synthetic */ IntentDatas(List list, List list2, List list3, Integer num, Integer num2, List list4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : list, (i & 2) != 0 ? null : list2, (i & 4) != 0 ? null : list3, (i & 8) != 0 ? 0 : num, (i & 16) != 0 ? 0 : num2, (i & 32) != 0 ? null : list4);
    }
}
