package com.arcvideo.ivi.res.sdk.data;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class CategoryValue {
    private final String name;
    private final String value;

    public CategoryValue(String str, String str2) {
        this.name = str;
        this.value = str2;
    }

    public static /* synthetic */ CategoryValue copy$default(CategoryValue categoryValue, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = categoryValue.name;
        }
        if ((i & 2) != 0) {
            str2 = categoryValue.value;
        }
        return categoryValue.copy(str, str2);
    }

    public final String component1() {
        return this.name;
    }

    public final String component2() {
        return this.value;
    }

    public final CategoryValue copy(String str, String str2) {
        return new CategoryValue(str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CategoryValue)) {
            return false;
        }
        CategoryValue categoryValue = (CategoryValue) obj;
        return Intrinsics.areEqual(this.name, categoryValue.name) && Intrinsics.areEqual(this.value, categoryValue.value);
    }

    public final String getName() {
        return this.name;
    }

    public final String getValue() {
        return this.value;
    }

    public int hashCode() {
        String str = this.name;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.value;
        return iHashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "CategoryValue(name=" + this.name + ", value=" + this.value + ')';
    }
}
