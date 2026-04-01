package com.arcvideo.ivi.res.sdk.innerbean;

import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class EpgResponse {
    private final String albumId;
    private final String albumName;
    private final int chnId;
    private final String chnName;
    private final List<VideoResponse> epg;
    private final boolean hasMore;
    private final int mixedCount;
    private final int pos;
    private final String publishYear;
    private final long sourceCode;
    private final int total;

    public EpgResponse(int i, int i2, int i3, String publishYear, boolean z, int i4, String chnName, long j, String albumId, String albumName, List<VideoResponse> list) {
        Intrinsics.checkNotNullParameter(publishYear, "publishYear");
        Intrinsics.checkNotNullParameter(chnName, "chnName");
        Intrinsics.checkNotNullParameter(albumId, "albumId");
        Intrinsics.checkNotNullParameter(albumName, "albumName");
        this.total = i;
        this.mixedCount = i2;
        this.pos = i3;
        this.publishYear = publishYear;
        this.hasMore = z;
        this.chnId = i4;
        this.chnName = chnName;
        this.sourceCode = j;
        this.albumId = albumId;
        this.albumName = albumName;
        this.epg = list;
    }

    public final int component1() {
        return this.total;
    }

    public final String component10() {
        return this.albumName;
    }

    public final List<VideoResponse> component11() {
        return this.epg;
    }

    public final int component2() {
        return this.mixedCount;
    }

    public final int component3() {
        return this.pos;
    }

    public final String component4() {
        return this.publishYear;
    }

    public final boolean component5() {
        return this.hasMore;
    }

    public final int component6() {
        return this.chnId;
    }

    public final String component7() {
        return this.chnName;
    }

    public final long component8() {
        return this.sourceCode;
    }

    public final String component9() {
        return this.albumId;
    }

    public final EpgResponse copy(int i, int i2, int i3, String publishYear, boolean z, int i4, String chnName, long j, String albumId, String albumName, List<VideoResponse> list) {
        Intrinsics.checkNotNullParameter(publishYear, "publishYear");
        Intrinsics.checkNotNullParameter(chnName, "chnName");
        Intrinsics.checkNotNullParameter(albumId, "albumId");
        Intrinsics.checkNotNullParameter(albumName, "albumName");
        return new EpgResponse(i, i2, i3, publishYear, z, i4, chnName, j, albumId, albumName, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EpgResponse)) {
            return false;
        }
        EpgResponse epgResponse = (EpgResponse) obj;
        return this.total == epgResponse.total && this.mixedCount == epgResponse.mixedCount && this.pos == epgResponse.pos && Intrinsics.areEqual(this.publishYear, epgResponse.publishYear) && this.hasMore == epgResponse.hasMore && this.chnId == epgResponse.chnId && Intrinsics.areEqual(this.chnName, epgResponse.chnName) && this.sourceCode == epgResponse.sourceCode && Intrinsics.areEqual(this.albumId, epgResponse.albumId) && Intrinsics.areEqual(this.albumName, epgResponse.albumName) && Intrinsics.areEqual(this.epg, epgResponse.epg);
    }

    public final String getAlbumId() {
        return this.albumId;
    }

    public final String getAlbumName() {
        return this.albumName;
    }

    public final int getChnId() {
        return this.chnId;
    }

    public final String getChnName() {
        return this.chnName;
    }

    public final List<VideoResponse> getEpg() {
        return this.epg;
    }

    public final boolean getHasMore() {
        return this.hasMore;
    }

    public final int getMixedCount() {
        return this.mixedCount;
    }

    public final int getPos() {
        return this.pos;
    }

    public final String getPublishYear() {
        return this.publishYear;
    }

    public final long getSourceCode() {
        return this.sourceCode;
    }

    public final int getTotal() {
        return this.total;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v22 */
    /* JADX WARN: Type inference failed for: r1v23 */
    /* JADX WARN: Type inference failed for: r1v7, types: [int] */
    public int hashCode() {
        int iHashCode = ((((((Integer.hashCode(this.total) * 31) + Integer.hashCode(this.mixedCount)) * 31) + Integer.hashCode(this.pos)) * 31) + this.publishYear.hashCode()) * 31;
        boolean z = this.hasMore;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        int iHashCode2 = (((((((((((iHashCode + r1) * 31) + Integer.hashCode(this.chnId)) * 31) + this.chnName.hashCode()) * 31) + Long.hashCode(this.sourceCode)) * 31) + this.albumId.hashCode()) * 31) + this.albumName.hashCode()) * 31;
        List<VideoResponse> list = this.epg;
        return iHashCode2 + (list == null ? 0 : list.hashCode());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("EpgResponse(total=");
        sb.append(this.total).append(", mixedCount=").append(this.mixedCount).append(", pos=").append(this.pos).append(", publishYear=").append(this.publishYear).append(", hasMore=").append(this.hasMore).append(", chnId=").append(this.chnId).append(", chnName=").append(this.chnName).append(", sourceCode=").append(this.sourceCode).append(", albumId=").append(this.albumId).append(", albumName=").append(this.albumName).append(", epg=").append(this.epg).append(')');
        return sb.toString();
    }

    public /* synthetic */ EpgResponse(int i, int i2, int i3, String str, boolean z, int i4, String str2, long j, String str3, String str4, List list, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, i3, str, z, i4, str2, j, str3, str4, (i5 & 1024) != 0 ? null : list);
    }
}
