package com.arcvideo.ivi.res.sdk.innerbean;

import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class RecFallResponse {
    private final List<VideoResponse> epg;
    private final PingBack pingback;
    private final String session;

    public RecFallResponse(String session, PingBack pingback, List<VideoResponse> list) {
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(pingback, "pingback");
        this.session = session;
        this.pingback = pingback;
        this.epg = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ RecFallResponse copy$default(RecFallResponse recFallResponse, String str, PingBack pingBack, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = recFallResponse.session;
        }
        if ((i & 2) != 0) {
            pingBack = recFallResponse.pingback;
        }
        if ((i & 4) != 0) {
            list = recFallResponse.epg;
        }
        return recFallResponse.copy(str, pingBack, list);
    }

    public final String component1() {
        return this.session;
    }

    public final PingBack component2() {
        return this.pingback;
    }

    public final List<VideoResponse> component3() {
        return this.epg;
    }

    public final RecFallResponse copy(String session, PingBack pingback, List<VideoResponse> list) {
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(pingback, "pingback");
        return new RecFallResponse(session, pingback, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RecFallResponse)) {
            return false;
        }
        RecFallResponse recFallResponse = (RecFallResponse) obj;
        return Intrinsics.areEqual(this.session, recFallResponse.session) && Intrinsics.areEqual(this.pingback, recFallResponse.pingback) && Intrinsics.areEqual(this.epg, recFallResponse.epg);
    }

    public final List<VideoResponse> getEpg() {
        return this.epg;
    }

    public final PingBack getPingback() {
        return this.pingback;
    }

    public final String getSession() {
        return this.session;
    }

    public int hashCode() {
        int iHashCode = ((this.session.hashCode() * 31) + this.pingback.hashCode()) * 31;
        List<VideoResponse> list = this.epg;
        return iHashCode + (list == null ? 0 : list.hashCode());
    }

    public String toString() {
        return "RecFallResponse(session=" + this.session + ", pingback=" + this.pingback + ", epg=" + this.epg + ')';
    }

    public /* synthetic */ RecFallResponse(String str, PingBack pingBack, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, pingBack, (i & 4) != 0 ? null : list);
    }
}
