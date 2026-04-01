package com.arcvideo.ivi.res.sdk.data;

import com.arcvideo.ivi.res.sdk.innerbean.VideoResponse;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class IntentData {
    private final List<CustomCategory> customCategories;
    private final List<VideoResponse> epg;
    private final List<GraphCategory> graphCategories;
    private final Integer intentResultNum;
    private final Integer mode;
    private final List<TermQuery> termQuery;

    public IntentData(List<CustomCategory> list, List<VideoResponse> list2, List<GraphCategory> list3, Integer num, Integer num2, List<TermQuery> list4) {
        this.customCategories = list;
        this.epg = list2;
        this.graphCategories = list3;
        this.intentResultNum = num;
        this.mode = num2;
        this.termQuery = list4;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ IntentData copy$default(IntentData intentData, List list, List list2, List list3, Integer num, Integer num2, List list4, int i, Object obj) {
        if ((i & 1) != 0) {
            list = intentData.customCategories;
        }
        if ((i & 2) != 0) {
            list2 = intentData.epg;
        }
        List list5 = list2;
        if ((i & 4) != 0) {
            list3 = intentData.graphCategories;
        }
        List list6 = list3;
        if ((i & 8) != 0) {
            num = intentData.intentResultNum;
        }
        Integer num3 = num;
        if ((i & 16) != 0) {
            num2 = intentData.mode;
        }
        Integer num4 = num2;
        if ((i & 32) != 0) {
            list4 = intentData.termQuery;
        }
        return intentData.copy(list, list5, list6, num3, num4, list4);
    }

    public final List<CustomCategory> component1() {
        return this.customCategories;
    }

    public final List<VideoResponse> component2() {
        return this.epg;
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

    public final IntentData copy(List<CustomCategory> list, List<VideoResponse> list2, List<GraphCategory> list3, Integer num, Integer num2, List<TermQuery> list4) {
        return new IntentData(list, list2, list3, num, num2, list4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IntentData)) {
            return false;
        }
        IntentData intentData = (IntentData) obj;
        return Intrinsics.areEqual(this.customCategories, intentData.customCategories) && Intrinsics.areEqual(this.epg, intentData.epg) && Intrinsics.areEqual(this.graphCategories, intentData.graphCategories) && Intrinsics.areEqual(this.intentResultNum, intentData.intentResultNum) && Intrinsics.areEqual(this.mode, intentData.mode) && Intrinsics.areEqual(this.termQuery, intentData.termQuery);
    }

    public final List<CustomCategory> getCustomCategories() {
        return this.customCategories;
    }

    public final List<VideoResponse> getEpg() {
        return this.epg;
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

    public int hashCode() {
        List<CustomCategory> list = this.customCategories;
        int iHashCode = (list == null ? 0 : list.hashCode()) * 31;
        List<VideoResponse> list2 = this.epg;
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

    public String toString() {
        return "IntentData(customCategories=" + this.customCategories + ", epg=" + this.epg + ", graphCategories=" + this.graphCategories + ", intentResultNum=" + this.intentResultNum + ", mode=" + this.mode + ", termQuery=" + this.termQuery + ')';
    }
}
