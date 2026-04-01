package com.wanos.commonlibrary.mediaCenter;

import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;

/* JADX INFO: loaded from: classes3.dex */
public interface MediaCenterServer {
    default void addFavor() {
    }

    default void cancelFavor() {
    }

    default MediaInfo getCurrentMusicInfo() {
        return null;
    }

    default void getMediaPlayerServiceAndSetListener() {
    }

    default boolean isPlaying() {
        return false;
    }

    default boolean isPreparing() {
        return false;
    }

    default void isUpdateMusicPlaybackState(boolean z) {
    }

    default void next() {
    }

    default void pause() {
    }

    default boolean play() {
        return false;
    }

    default void pre() {
    }

    default void requestRankMusicListAndPlayFirst() {
    }

    default void seek(long j) {
    }

    default void setPlayMode(int i) {
    }

    default void setPlayerInterceptorFlag(String str) {
    }

    default void setPlayerInterceptorFlag(boolean z) {
    }

    default void setPlayerStatus(MediaPlayerEnum.CallBackState callBackState) {
    }

    default void updateLrc(String str, MediaPlayerEnum.AppType appType) {
    }
}
