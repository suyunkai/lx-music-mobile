package com.wanos.media.entity;

import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface IMenuLibCallback {
    boolean addAudioSound(ThemeAudioInfoEntity themeAudioInfoEntity);

    boolean deleteAudioSound(long j);

    List<Long> getSceneAudioId();
}
