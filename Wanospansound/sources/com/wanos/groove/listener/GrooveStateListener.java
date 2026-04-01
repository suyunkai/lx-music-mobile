package com.wanos.groove.listener;

import com.wanos.groove.LyricInfo;
import com.wanos.groove.LyricLine;
import com.wanos.groove.MediaInfo;

/* JADX INFO: loaded from: classes3.dex */
public interface GrooveStateListener {
    void getLyricChanged(LyricInfo lyricInfo);

    void lyricChanged(LyricLine lyricLine);

    void onIndexChanged(MediaInfo mediaInfo);

    void onListAdded();

    void onListChanged();

    void onPlayModeChanged(int i);

    void playState(int i);
}
