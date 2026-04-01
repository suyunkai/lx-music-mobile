package com.arcvideo.ivi.res.sdk.innerbean;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class ChildrenResponse {
    private final String id;
    private final String name;

    public ChildrenResponse(String id, String name) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        this.id = id;
        this.name = name;
    }

    public static /* synthetic */ ChildrenResponse copy$default(ChildrenResponse childrenResponse, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = childrenResponse.id;
        }
        if ((i & 2) != 0) {
            str2 = childrenResponse.name;
        }
        return childrenResponse.copy(str, str2);
    }

    public final String component1() {
        return this.id;
    }

    public final String component2() {
        return this.name;
    }

    public final ChildrenResponse copy(String id, String name) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        return new ChildrenResponse(id, name);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ChildrenResponse)) {
            return false;
        }
        ChildrenResponse childrenResponse = (ChildrenResponse) obj;
        return Intrinsics.areEqual(this.id, childrenResponse.id) && Intrinsics.areEqual(this.name, childrenResponse.name);
    }

    public final String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public int hashCode() {
        return (this.id.hashCode() * 31) + this.name.hashCode();
    }

    public String toString() {
        return "ChildrenResponse(id=" + this.id + ", name=" + this.name + ')';
    }
}
