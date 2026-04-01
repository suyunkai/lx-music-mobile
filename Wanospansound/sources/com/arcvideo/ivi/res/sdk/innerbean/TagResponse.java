package com.arcvideo.ivi.res.sdk.innerbean;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class TagResponse {
    private final List<ChildrenResponse> children;
    private final String id;
    private final String name;

    public TagResponse(String id, String name, List<ChildrenResponse> children) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(children, "children");
        this.id = id;
        this.name = name;
        this.children = children;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TagResponse copy$default(TagResponse tagResponse, String str, String str2, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = tagResponse.id;
        }
        if ((i & 2) != 0) {
            str2 = tagResponse.name;
        }
        if ((i & 4) != 0) {
            list = tagResponse.children;
        }
        return tagResponse.copy(str, str2, list);
    }

    public final String component1() {
        return this.id;
    }

    public final String component2() {
        return this.name;
    }

    public final List<ChildrenResponse> component3() {
        return this.children;
    }

    public final TagResponse copy(String id, String name, List<ChildrenResponse> children) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(children, "children");
        return new TagResponse(id, name, children);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TagResponse)) {
            return false;
        }
        TagResponse tagResponse = (TagResponse) obj;
        return Intrinsics.areEqual(this.id, tagResponse.id) && Intrinsics.areEqual(this.name, tagResponse.name) && Intrinsics.areEqual(this.children, tagResponse.children);
    }

    public final List<ChildrenResponse> getChildren() {
        return this.children;
    }

    public final String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public int hashCode() {
        return (((this.id.hashCode() * 31) + this.name.hashCode()) * 31) + this.children.hashCode();
    }

    public String toString() {
        return "TagResponse(id=" + this.id + ", name=" + this.name + ", children=" + this.children + ')';
    }
}
