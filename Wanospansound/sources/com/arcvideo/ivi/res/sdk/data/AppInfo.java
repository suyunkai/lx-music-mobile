package com.arcvideo.ivi.res.sdk.data;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class AppInfo {
    private final String behaviorAfterExpired;

    public AppInfo(String behaviorAfterExpired) {
        Intrinsics.checkNotNullParameter(behaviorAfterExpired, "behaviorAfterExpired");
        this.behaviorAfterExpired = behaviorAfterExpired;
    }

    public static /* synthetic */ AppInfo copy$default(AppInfo appInfo, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = appInfo.behaviorAfterExpired;
        }
        return appInfo.copy(str);
    }

    public final String component1() {
        return this.behaviorAfterExpired;
    }

    public final AppInfo copy(String behaviorAfterExpired) {
        Intrinsics.checkNotNullParameter(behaviorAfterExpired, "behaviorAfterExpired");
        return new AppInfo(behaviorAfterExpired);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof AppInfo) && Intrinsics.areEqual(this.behaviorAfterExpired, ((AppInfo) obj).behaviorAfterExpired);
    }

    public final String getBehaviorAfterExpired() {
        return this.behaviorAfterExpired;
    }

    public int hashCode() {
        return this.behaviorAfterExpired.hashCode();
    }

    public String toString() {
        return "AppInfo(behaviorAfterExpired=" + this.behaviorAfterExpired + ')';
    }
}
