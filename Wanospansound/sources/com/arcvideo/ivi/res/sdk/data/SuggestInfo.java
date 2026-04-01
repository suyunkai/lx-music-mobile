package com.arcvideo.ivi.res.sdk.data;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class SuggestInfo {
    private String name;

    public SuggestInfo(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
    }

    public static /* synthetic */ SuggestInfo copy$default(SuggestInfo suggestInfo, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = suggestInfo.name;
        }
        return suggestInfo.copy(str);
    }

    public final String component1() {
        return this.name;
    }

    public final SuggestInfo copy(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new SuggestInfo(name);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof SuggestInfo) && Intrinsics.areEqual(this.name, ((SuggestInfo) obj).name);
    }

    public final String getName() {
        return this.name;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public String toString() {
        return "SuggestInfo(name=" + this.name + ')';
    }
}
