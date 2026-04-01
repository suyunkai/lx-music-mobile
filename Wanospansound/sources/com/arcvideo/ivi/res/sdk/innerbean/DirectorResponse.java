package com.arcvideo.ivi.res.sdk.innerbean;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DirectorResponse {
    private final String id;
    private final String n;

    public DirectorResponse(String id, String n) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(n, "n");
        this.id = id;
        this.n = n;
    }

    public static /* synthetic */ DirectorResponse copy$default(DirectorResponse directorResponse, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = directorResponse.id;
        }
        if ((i & 2) != 0) {
            str2 = directorResponse.n;
        }
        return directorResponse.copy(str, str2);
    }

    public final String component1() {
        return this.id;
    }

    public final String component2() {
        return this.n;
    }

    public final DirectorResponse copy(String id, String n) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(n, "n");
        return new DirectorResponse(id, n);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DirectorResponse)) {
            return false;
        }
        DirectorResponse directorResponse = (DirectorResponse) obj;
        return Intrinsics.areEqual(this.id, directorResponse.id) && Intrinsics.areEqual(this.n, directorResponse.n);
    }

    public final String getId() {
        return this.id;
    }

    public final String getN() {
        return this.n;
    }

    public int hashCode() {
        return (this.id.hashCode() * 31) + this.n.hashCode();
    }

    public String toString() {
        return "DirectorResponse(id=" + this.id + ", n=" + this.n + ')';
    }
}
