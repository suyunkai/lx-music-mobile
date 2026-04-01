package com.arcvideo.ivi.res.sdk.innerbean;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class AtomsEpgResponse {
    private final String name;
    private final String qipuId;
    private final String videoFormat;

    public AtomsEpgResponse(String name, String qipuId, String videoFormat) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(qipuId, "qipuId");
        Intrinsics.checkNotNullParameter(videoFormat, "videoFormat");
        this.name = name;
        this.qipuId = qipuId;
        this.videoFormat = videoFormat;
    }

    public static /* synthetic */ AtomsEpgResponse copy$default(AtomsEpgResponse atomsEpgResponse, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = atomsEpgResponse.name;
        }
        if ((i & 2) != 0) {
            str2 = atomsEpgResponse.qipuId;
        }
        if ((i & 4) != 0) {
            str3 = atomsEpgResponse.videoFormat;
        }
        return atomsEpgResponse.copy(str, str2, str3);
    }

    public final String component1() {
        return this.name;
    }

    public final String component2() {
        return this.qipuId;
    }

    public final String component3() {
        return this.videoFormat;
    }

    public final AtomsEpgResponse copy(String name, String qipuId, String videoFormat) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(qipuId, "qipuId");
        Intrinsics.checkNotNullParameter(videoFormat, "videoFormat");
        return new AtomsEpgResponse(name, qipuId, videoFormat);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AtomsEpgResponse)) {
            return false;
        }
        AtomsEpgResponse atomsEpgResponse = (AtomsEpgResponse) obj;
        return Intrinsics.areEqual(this.name, atomsEpgResponse.name) && Intrinsics.areEqual(this.qipuId, atomsEpgResponse.qipuId) && Intrinsics.areEqual(this.videoFormat, atomsEpgResponse.videoFormat);
    }

    public final String getName() {
        return this.name;
    }

    public final String getQipuId() {
        return this.qipuId;
    }

    public final String getVideoFormat() {
        return this.videoFormat;
    }

    public int hashCode() {
        return (((this.name.hashCode() * 31) + this.qipuId.hashCode()) * 31) + this.videoFormat.hashCode();
    }

    public String toString() {
        return "AtomsEpgResponse(name=" + this.name + ", qipuId=" + this.qipuId + ", videoFormat=" + this.videoFormat + ')';
    }
}
