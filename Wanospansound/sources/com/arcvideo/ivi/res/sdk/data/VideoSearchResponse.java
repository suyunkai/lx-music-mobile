package com.arcvideo.ivi.res.sdk.data;

import com.arcvideo.ivi.res.sdk.innerbean.VideoResponse;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class VideoSearchResponse {
    private final String bkt;
    private final List<VideoResponse> epgs;
    private final String eventId;
    private final IntentData intentData;
    private final Integer total;

    public VideoSearchResponse(String bkt, List<VideoResponse> list, String eventId, IntentData intentData, Integer num) {
        Intrinsics.checkNotNullParameter(bkt, "bkt");
        Intrinsics.checkNotNullParameter(eventId, "eventId");
        this.bkt = bkt;
        this.epgs = list;
        this.eventId = eventId;
        this.intentData = intentData;
        this.total = num;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ VideoSearchResponse copy$default(VideoSearchResponse videoSearchResponse, String str, List list, String str2, IntentData intentData, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            str = videoSearchResponse.bkt;
        }
        if ((i & 2) != 0) {
            list = videoSearchResponse.epgs;
        }
        List list2 = list;
        if ((i & 4) != 0) {
            str2 = videoSearchResponse.eventId;
        }
        String str3 = str2;
        if ((i & 8) != 0) {
            intentData = videoSearchResponse.intentData;
        }
        IntentData intentData2 = intentData;
        if ((i & 16) != 0) {
            num = videoSearchResponse.total;
        }
        return videoSearchResponse.copy(str, list2, str3, intentData2, num);
    }

    public final String component1() {
        return this.bkt;
    }

    public final List<VideoResponse> component2() {
        return this.epgs;
    }

    public final String component3() {
        return this.eventId;
    }

    public final IntentData component4() {
        return this.intentData;
    }

    public final Integer component5() {
        return this.total;
    }

    public final VideoSearchResponse copy(String bkt, List<VideoResponse> list, String eventId, IntentData intentData, Integer num) {
        Intrinsics.checkNotNullParameter(bkt, "bkt");
        Intrinsics.checkNotNullParameter(eventId, "eventId");
        return new VideoSearchResponse(bkt, list, eventId, intentData, num);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VideoSearchResponse)) {
            return false;
        }
        VideoSearchResponse videoSearchResponse = (VideoSearchResponse) obj;
        return Intrinsics.areEqual(this.bkt, videoSearchResponse.bkt) && Intrinsics.areEqual(this.epgs, videoSearchResponse.epgs) && Intrinsics.areEqual(this.eventId, videoSearchResponse.eventId) && Intrinsics.areEqual(this.intentData, videoSearchResponse.intentData) && Intrinsics.areEqual(this.total, videoSearchResponse.total);
    }

    public final String getBkt() {
        return this.bkt;
    }

    public final List<VideoResponse> getEpgs() {
        return this.epgs;
    }

    public final String getEventId() {
        return this.eventId;
    }

    public final IntentData getIntentData() {
        return this.intentData;
    }

    public final Integer getTotal() {
        return this.total;
    }

    public int hashCode() {
        int iHashCode = this.bkt.hashCode() * 31;
        List<VideoResponse> list = this.epgs;
        int iHashCode2 = (((iHashCode + (list == null ? 0 : list.hashCode())) * 31) + this.eventId.hashCode()) * 31;
        IntentData intentData = this.intentData;
        int iHashCode3 = (iHashCode2 + (intentData == null ? 0 : intentData.hashCode())) * 31;
        Integer num = this.total;
        return iHashCode3 + (num != null ? num.hashCode() : 0);
    }

    public String toString() {
        return "VideoSearchResponse(bkt=" + this.bkt + ", epgs=" + this.epgs + ", eventId=" + this.eventId + ", intentData=" + this.intentData + ", total=" + this.total + ')';
    }
}
