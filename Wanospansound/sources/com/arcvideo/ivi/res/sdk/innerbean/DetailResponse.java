package com.arcvideo.ivi.res.sdk.innerbean;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class DetailResponse {
    private VideoResponse data;

    public DetailResponse(VideoResponse data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.data = data;
    }

    public static /* synthetic */ DetailResponse copy$default(DetailResponse detailResponse, VideoResponse videoResponse, int i, Object obj) {
        if ((i & 1) != 0) {
            videoResponse = detailResponse.data;
        }
        return detailResponse.copy(videoResponse);
    }

    public final VideoResponse component1() {
        return this.data;
    }

    public final DetailResponse copy(VideoResponse data) {
        Intrinsics.checkNotNullParameter(data, "data");
        return new DetailResponse(data);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof DetailResponse) && Intrinsics.areEqual(this.data, ((DetailResponse) obj).data);
    }

    public final VideoResponse getData() {
        return this.data;
    }

    public int hashCode() {
        return this.data.hashCode();
    }

    public final void setData(VideoResponse videoResponse) {
        Intrinsics.checkNotNullParameter(videoResponse, "<set-?>");
        this.data = videoResponse;
    }

    public String toString() {
        return "DetailResponse(data=" + this.data + ')';
    }
}
