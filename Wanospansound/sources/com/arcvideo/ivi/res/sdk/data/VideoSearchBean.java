package com.arcvideo.ivi.res.sdk.data;

import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class VideoSearchBean {
    private IntentDatas intentData;
    private Integer total;
    private List<VideoInfo> videoInfoList;

    public VideoSearchBean() {
        this(null, null, null, 7, null);
    }

    public VideoSearchBean(List<VideoInfo> list, IntentDatas intentDatas, Integer num) {
        this.videoInfoList = list;
        this.intentData = intentDatas;
        this.total = num;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ VideoSearchBean copy$default(VideoSearchBean videoSearchBean, List list, IntentDatas intentDatas, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            list = videoSearchBean.videoInfoList;
        }
        if ((i & 2) != 0) {
            intentDatas = videoSearchBean.intentData;
        }
        if ((i & 4) != 0) {
            num = videoSearchBean.total;
        }
        return videoSearchBean.copy(list, intentDatas, num);
    }

    public final List<VideoInfo> component1() {
        return this.videoInfoList;
    }

    public final IntentDatas component2() {
        return this.intentData;
    }

    public final Integer component3() {
        return this.total;
    }

    public final VideoSearchBean copy(List<VideoInfo> list, IntentDatas intentDatas, Integer num) {
        return new VideoSearchBean(list, intentDatas, num);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VideoSearchBean)) {
            return false;
        }
        VideoSearchBean videoSearchBean = (VideoSearchBean) obj;
        return Intrinsics.areEqual(this.videoInfoList, videoSearchBean.videoInfoList) && Intrinsics.areEqual(this.intentData, videoSearchBean.intentData) && Intrinsics.areEqual(this.total, videoSearchBean.total);
    }

    public final IntentDatas getIntentData() {
        return this.intentData;
    }

    public final Integer getTotal() {
        return this.total;
    }

    public final List<VideoInfo> getVideoInfoList() {
        return this.videoInfoList;
    }

    public int hashCode() {
        List<VideoInfo> list = this.videoInfoList;
        int iHashCode = (list == null ? 0 : list.hashCode()) * 31;
        IntentDatas intentDatas = this.intentData;
        int iHashCode2 = (iHashCode + (intentDatas == null ? 0 : intentDatas.hashCode())) * 31;
        Integer num = this.total;
        return iHashCode2 + (num != null ? num.hashCode() : 0);
    }

    public final void setIntentData(IntentDatas intentDatas) {
        this.intentData = intentDatas;
    }

    public final void setTotal(Integer num) {
        this.total = num;
    }

    public final void setVideoInfoList(List<VideoInfo> list) {
        this.videoInfoList = list;
    }

    public String toString() {
        return "VideoSearchBean(videoInfoList=" + this.videoInfoList + ", intentData=" + this.intentData + ", total=" + this.total + ')';
    }

    public /* synthetic */ VideoSearchBean(List list, IntentDatas intentDatas, Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : list, (i & 2) != 0 ? null : intentDatas, (i & 4) != 0 ? 0 : num);
    }
}
