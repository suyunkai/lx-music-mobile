package com.arcvideo.ivi.res.sdk.innerbean;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
public final class SystemTime {
    private final long time;

    public SystemTime() {
        this(0L, 1, null);
    }

    public SystemTime(long j) {
        this.time = j;
    }

    public static /* synthetic */ SystemTime copy$default(SystemTime systemTime, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = systemTime.time;
        }
        return systemTime.copy(j);
    }

    public final long component1() {
        return this.time;
    }

    public final SystemTime copy(long j) {
        return new SystemTime(j);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof SystemTime) && this.time == ((SystemTime) obj).time;
    }

    public final long getTime() {
        return this.time;
    }

    public int hashCode() {
        return Long.hashCode(this.time);
    }

    public String toString() {
        return "SystemTime(time=" + this.time + ')';
    }

    public /* synthetic */ SystemTime(long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 0L : j);
    }
}
