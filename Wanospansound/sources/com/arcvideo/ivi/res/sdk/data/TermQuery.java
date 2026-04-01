package com.arcvideo.ivi.res.sdk.data;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class TermQuery {
    private final String fieldName;
    private final String term;

    public TermQuery(String str, String str2) {
        this.fieldName = str;
        this.term = str2;
    }

    public static /* synthetic */ TermQuery copy$default(TermQuery termQuery, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = termQuery.fieldName;
        }
        if ((i & 2) != 0) {
            str2 = termQuery.term;
        }
        return termQuery.copy(str, str2);
    }

    public final String component1() {
        return this.fieldName;
    }

    public final String component2() {
        return this.term;
    }

    public final TermQuery copy(String str, String str2) {
        return new TermQuery(str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TermQuery)) {
            return false;
        }
        TermQuery termQuery = (TermQuery) obj;
        return Intrinsics.areEqual(this.fieldName, termQuery.fieldName) && Intrinsics.areEqual(this.term, termQuery.term);
    }

    public final String getFieldName() {
        return this.fieldName;
    }

    public final String getTerm() {
        return this.term;
    }

    public int hashCode() {
        String str = this.fieldName;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.term;
        return iHashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "TermQuery(fieldName=" + this.fieldName + ", term=" + this.term + ')';
    }
}
