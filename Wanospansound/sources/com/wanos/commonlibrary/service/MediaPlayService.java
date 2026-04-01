package com.wanos.commonlibrary.service;

import android.content.Context;
import com.wanos.commonlibrary.bean.MediaInfo;

/* JADX INFO: loaded from: classes3.dex */
public interface MediaPlayService {
    void pause();

    <T> void playAudioBook(T t, Context context);

    void playMediaInfo(MediaInfo mediaInfo);

    void playMusicAlbum(String str);

    void resume();
}
