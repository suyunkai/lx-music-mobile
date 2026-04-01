package com.arcvideo.ivi.res.sdk.innerbean;

import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class ChannelResponse {
    private final List<VideoResponse> epg;
    private final String session;
    private final List<TagResponse> tags;
    private final int total;
    private final List<VideoResponse> videos;

    public ChannelResponse(int i, String str, List<VideoResponse> list, List<VideoResponse> list2, List<TagResponse> list3) {
        this.total = i;
        this.session = str;
        this.videos = list;
        this.epg = list2;
        this.tags = list3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ChannelResponse copy$default(ChannelResponse channelResponse, int i, String str, List list, List list2, List list3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = channelResponse.total;
        }
        if ((i2 & 2) != 0) {
            str = channelResponse.session;
        }
        String str2 = str;
        if ((i2 & 4) != 0) {
            list = channelResponse.videos;
        }
        List list4 = list;
        if ((i2 & 8) != 0) {
            list2 = channelResponse.epg;
        }
        List list5 = list2;
        if ((i2 & 16) != 0) {
            list3 = channelResponse.tags;
        }
        return channelResponse.copy(i, str2, list4, list5, list3);
    }

    public final int component1() {
        return this.total;
    }

    public final String component2() {
        return this.session;
    }

    public final List<VideoResponse> component3() {
        return this.videos;
    }

    public final List<VideoResponse> component4() {
        return this.epg;
    }

    public final List<TagResponse> component5() {
        return this.tags;
    }

    public final ChannelResponse copy(int i, String str, List<VideoResponse> list, List<VideoResponse> list2, List<TagResponse> list3) {
        return new ChannelResponse(i, str, list, list2, list3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ChannelResponse)) {
            return false;
        }
        ChannelResponse channelResponse = (ChannelResponse) obj;
        return this.total == channelResponse.total && Intrinsics.areEqual(this.session, channelResponse.session) && Intrinsics.areEqual(this.videos, channelResponse.videos) && Intrinsics.areEqual(this.epg, channelResponse.epg) && Intrinsics.areEqual(this.tags, channelResponse.tags);
    }

    public final List<VideoResponse> getEpg() {
        return this.epg;
    }

    public final String getSession() {
        return this.session;
    }

    public final List<TagResponse> getTags() {
        return this.tags;
    }

    public final int getTotal() {
        return this.total;
    }

    public final List<VideoResponse> getVideos() {
        return this.videos;
    }

    public int hashCode() {
        int iHashCode = Integer.hashCode(this.total) * 31;
        String str = this.session;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        List<VideoResponse> list = this.videos;
        int iHashCode3 = (iHashCode2 + (list == null ? 0 : list.hashCode())) * 31;
        List<VideoResponse> list2 = this.epg;
        int iHashCode4 = (iHashCode3 + (list2 == null ? 0 : list2.hashCode())) * 31;
        List<TagResponse> list3 = this.tags;
        return iHashCode4 + (list3 != null ? list3.hashCode() : 0);
    }

    public String toString() {
        return "ChannelResponse(total=" + this.total + ", session=" + this.session + ", videos=" + this.videos + ", epg=" + this.epg + ", tags=" + this.tags + ')';
    }

    public /* synthetic */ ChannelResponse(int i, String str, List list, List list2, List list3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? "" : str, (i2 & 4) != 0 ? null : list, (i2 & 8) != 0 ? null : list2, (i2 & 16) != 0 ? null : list3);
    }
}
