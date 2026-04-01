package com.wanos.commonlibrary.utils;

/* JADX INFO: loaded from: classes3.dex */
public class ImageUtils {
    private static final int albumImageSize = 280;

    public static String getUrlPath(String str, int i, int i2) {
        return str;
    }

    public static String getUrlPath(String str) {
        return str + "?imageMogr2/format/webp/thumbnail/";
    }

    public static String getAudioBookAlbumUrl(String str) {
        return getUrlPath(str, albumImageSize, albumImageSize);
    }
}
