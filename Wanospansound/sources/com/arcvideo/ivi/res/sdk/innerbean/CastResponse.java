package com.arcvideo.ivi.res.sdk.innerbean;

import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class CastResponse {
    private final List<MainActorResponse> actor;
    private final List<DirectorResponse> director;
    private final String dolby;
    private final int hot;
    private final int isAudioMode;
    private final List<MainActorResponse> mainActor;
    private final int qiyiProd;
    private final String type4k;
    private final int unlockable;

    public CastResponse(List<DirectorResponse> list, List<MainActorResponse> list2, List<MainActorResponse> list3, String type4k, String dolby, int i, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(type4k, "type4k");
        Intrinsics.checkNotNullParameter(dolby, "dolby");
        this.director = list;
        this.mainActor = list2;
        this.actor = list3;
        this.type4k = type4k;
        this.dolby = dolby;
        this.qiyiProd = i;
        this.isAudioMode = i2;
        this.unlockable = i3;
        this.hot = i4;
    }

    public final List<DirectorResponse> component1() {
        return this.director;
    }

    public final List<MainActorResponse> component2() {
        return this.mainActor;
    }

    public final List<MainActorResponse> component3() {
        return this.actor;
    }

    public final String component4() {
        return this.type4k;
    }

    public final String component5() {
        return this.dolby;
    }

    public final int component6() {
        return this.qiyiProd;
    }

    public final int component7() {
        return this.isAudioMode;
    }

    public final int component8() {
        return this.unlockable;
    }

    public final int component9() {
        return this.hot;
    }

    public final CastResponse copy(List<DirectorResponse> list, List<MainActorResponse> list2, List<MainActorResponse> list3, String type4k, String dolby, int i, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(type4k, "type4k");
        Intrinsics.checkNotNullParameter(dolby, "dolby");
        return new CastResponse(list, list2, list3, type4k, dolby, i, i2, i3, i4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CastResponse)) {
            return false;
        }
        CastResponse castResponse = (CastResponse) obj;
        return Intrinsics.areEqual(this.director, castResponse.director) && Intrinsics.areEqual(this.mainActor, castResponse.mainActor) && Intrinsics.areEqual(this.actor, castResponse.actor) && Intrinsics.areEqual(this.type4k, castResponse.type4k) && Intrinsics.areEqual(this.dolby, castResponse.dolby) && this.qiyiProd == castResponse.qiyiProd && this.isAudioMode == castResponse.isAudioMode && this.unlockable == castResponse.unlockable && this.hot == castResponse.hot;
    }

    public final List<MainActorResponse> getActor() {
        return this.actor;
    }

    public final List<DirectorResponse> getDirector() {
        return this.director;
    }

    public final String getDolby() {
        return this.dolby;
    }

    public final int getHot() {
        return this.hot;
    }

    public final List<MainActorResponse> getMainActor() {
        return this.mainActor;
    }

    public final int getQiyiProd() {
        return this.qiyiProd;
    }

    public final String getType4k() {
        return this.type4k;
    }

    public final int getUnlockable() {
        return this.unlockable;
    }

    public int hashCode() {
        List<DirectorResponse> list = this.director;
        int iHashCode = (list == null ? 0 : list.hashCode()) * 31;
        List<MainActorResponse> list2 = this.mainActor;
        int iHashCode2 = (iHashCode + (list2 == null ? 0 : list2.hashCode())) * 31;
        List<MainActorResponse> list3 = this.actor;
        return ((((((((((((iHashCode2 + (list3 != null ? list3.hashCode() : 0)) * 31) + this.type4k.hashCode()) * 31) + this.dolby.hashCode()) * 31) + Integer.hashCode(this.qiyiProd)) * 31) + Integer.hashCode(this.isAudioMode)) * 31) + Integer.hashCode(this.unlockable)) * 31) + Integer.hashCode(this.hot);
    }

    public final int isAudioMode() {
        return this.isAudioMode;
    }

    public String toString() {
        return "CastResponse(director=" + this.director + ", mainActor=" + this.mainActor + ", actor=" + this.actor + ", type4k=" + this.type4k + ", dolby=" + this.dolby + ", qiyiProd=" + this.qiyiProd + ", isAudioMode=" + this.isAudioMode + ", unlockable=" + this.unlockable + ", hot=" + this.hot + ')';
    }

    public /* synthetic */ CastResponse(List list, List list2, List list3, String str, String str2, int i, int i2, int i3, int i4, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, list2, list3, (i5 & 8) != 0 ? "" : str, (i5 & 16) != 0 ? "" : str2, (i5 & 32) != 0 ? 0 : i, (i5 & 64) != 0 ? 0 : i2, (i5 & 128) != 0 ? 0 : i3, (i5 & 256) != 0 ? 0 : i4);
    }
}
