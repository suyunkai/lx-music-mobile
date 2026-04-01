package com.arcvideo.ivi.res.sdk.data;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class GraphCategory {
    private final List<CategoryValue> categoryValue;
    private final String desc;
    private final String fieldName;

    public GraphCategory(List<CategoryValue> list, String str, String str2) {
        this.categoryValue = list;
        this.desc = str;
        this.fieldName = str2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ GraphCategory copy$default(GraphCategory graphCategory, List list, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = graphCategory.categoryValue;
        }
        if ((i & 2) != 0) {
            str = graphCategory.desc;
        }
        if ((i & 4) != 0) {
            str2 = graphCategory.fieldName;
        }
        return graphCategory.copy(list, str, str2);
    }

    public final List<CategoryValue> component1() {
        return this.categoryValue;
    }

    public final String component2() {
        return this.desc;
    }

    public final String component3() {
        return this.fieldName;
    }

    public final GraphCategory copy(List<CategoryValue> list, String str, String str2) {
        return new GraphCategory(list, str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GraphCategory)) {
            return false;
        }
        GraphCategory graphCategory = (GraphCategory) obj;
        return Intrinsics.areEqual(this.categoryValue, graphCategory.categoryValue) && Intrinsics.areEqual(this.desc, graphCategory.desc) && Intrinsics.areEqual(this.fieldName, graphCategory.fieldName);
    }

    public final List<CategoryValue> getCategoryValue() {
        return this.categoryValue;
    }

    public final String getDesc() {
        return this.desc;
    }

    public final String getFieldName() {
        return this.fieldName;
    }

    public int hashCode() {
        List<CategoryValue> list = this.categoryValue;
        int iHashCode = (list == null ? 0 : list.hashCode()) * 31;
        String str = this.desc;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.fieldName;
        return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "GraphCategory(categoryValue=" + this.categoryValue + ", desc=" + this.desc + ", fieldName=" + this.fieldName + ')';
    }
}
