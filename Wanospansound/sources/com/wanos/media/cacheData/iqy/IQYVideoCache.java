package com.wanos.media.cacheData.iqy;

import com.arcvideo.ivi.res.sdk.data.VideoInfo;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class IQYVideoCache {
    private static List<VideoInfo> moviesList;
    private static List<VideoInfo> tvList;

    public static List<VideoInfo> getMoviesList() {
        return moviesList;
    }

    public static void setMoviesList(List<VideoInfo> list) {
        moviesList = list;
    }

    public static List<VideoInfo> getTvList() {
        return tvList;
    }

    public static void setTvList(List<VideoInfo> list) {
        tvList = list;
    }
}
