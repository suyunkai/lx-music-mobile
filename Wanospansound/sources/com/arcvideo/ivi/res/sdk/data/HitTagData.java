package com.arcvideo.ivi.res.sdk.data;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class HitTagData {
    private final List<CustomCategory> customCategories;
    private final List<GraphCategory> graphCategories;

    public HitTagData(List<CustomCategory> list, List<GraphCategory> list2) {
        this.customCategories = list;
        this.graphCategories = list2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ HitTagData copy$default(HitTagData hitTagData, List list, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = hitTagData.customCategories;
        }
        if ((i & 2) != 0) {
            list2 = hitTagData.graphCategories;
        }
        return hitTagData.copy(list, list2);
    }

    public final List<CustomCategory> component1() {
        return this.customCategories;
    }

    public final List<GraphCategory> component2() {
        return this.graphCategories;
    }

    public final HitTagData copy(List<CustomCategory> list, List<GraphCategory> list2) {
        return new HitTagData(list, list2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HitTagData)) {
            return false;
        }
        HitTagData hitTagData = (HitTagData) obj;
        return Intrinsics.areEqual(this.customCategories, hitTagData.customCategories) && Intrinsics.areEqual(this.graphCategories, hitTagData.graphCategories);
    }

    public final List<CustomCategory> getCustomCategories() {
        return this.customCategories;
    }

    public final List<GraphCategory> getGraphCategories() {
        return this.graphCategories;
    }

    public int hashCode() {
        List<CustomCategory> list = this.customCategories;
        int iHashCode = (list == null ? 0 : list.hashCode()) * 31;
        List<GraphCategory> list2 = this.graphCategories;
        return iHashCode + (list2 != null ? list2.hashCode() : 0);
    }

    public String toString() {
        return "HitTagData(customCategories=" + this.customCategories + ", graphCategories=" + this.graphCategories + ')';
    }
}
