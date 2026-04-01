package com.wanos.careditproject.utils;

import com.wanos.wanosplayermodule.MediaPlayerHelper;

/* JADX INFO: loaded from: classes3.dex */
public class MediaPlayerHelperUtil {
    public static MediaPlayerHelper create() {
        return new MediaPlayerHelper(EditingUtils.getLongCacheDir(), EditingUtils.DEFAULT_LONG_FILE_MAX_CACHE_SIZE);
    }

    public static MediaPlayerHelper create(String str, long j) {
        return new MediaPlayerHelper(str, j);
    }
}
