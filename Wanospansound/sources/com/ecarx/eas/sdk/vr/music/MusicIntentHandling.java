package com.ecarx.eas.sdk.vr.music;

import com.ecarx.eas.sdk.vr.common.IResponse;
import com.ecarx.eas.sdk.vr.common.MediaCtrlIntent;

/* JADX INFO: loaded from: classes2.dex */
abstract class MusicIntentHandling {
    public abstract void handleCtrlApp(MediaCtrlIntent mediaCtrlIntent, IResponse iResponse);

    public abstract void handlePlayMusic(MusicPlayIntent musicPlayIntent, IResponse iResponse);

    public abstract void handleSearchMusic(MusicSearchIntent musicSearchIntent, IResponse iResponse);

    MusicIntentHandling() {
    }
}
