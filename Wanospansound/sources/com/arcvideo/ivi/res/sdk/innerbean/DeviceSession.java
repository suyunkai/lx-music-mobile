package com.arcvideo.ivi.res.sdk.innerbean;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DeviceSession {
    private final String expiredIn;
    private final String token;

    public DeviceSession(String token, String expiredIn) {
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(expiredIn, "expiredIn");
        this.token = token;
        this.expiredIn = expiredIn;
    }

    public static /* synthetic */ DeviceSession copy$default(DeviceSession deviceSession, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = deviceSession.token;
        }
        if ((i & 2) != 0) {
            str2 = deviceSession.expiredIn;
        }
        return deviceSession.copy(str, str2);
    }

    public final String component1() {
        return this.token;
    }

    public final String component2() {
        return this.expiredIn;
    }

    public final DeviceSession copy(String token, String expiredIn) {
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(expiredIn, "expiredIn");
        return new DeviceSession(token, expiredIn);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DeviceSession)) {
            return false;
        }
        DeviceSession deviceSession = (DeviceSession) obj;
        return Intrinsics.areEqual(this.token, deviceSession.token) && Intrinsics.areEqual(this.expiredIn, deviceSession.expiredIn);
    }

    public final String getExpiredIn() {
        return this.expiredIn;
    }

    public final String getToken() {
        return this.token;
    }

    public int hashCode() {
        return (this.token.hashCode() * 31) + this.expiredIn.hashCode();
    }

    public String toString() {
        return "DeviceSession(token=" + this.token + ", expiredIn=" + this.expiredIn + ')';
    }
}
