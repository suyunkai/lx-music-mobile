package com.wanos.commonlibrary.mediaCenter;

import com.wanos.commonlibrary.mediaPlayer.listener.OnMediaInfoCallbackListener;
import com.wanos.commonlibrary.mediaPlayer.listener.OnStatusCallbackListener;
import com.wanos.commonlibrary.mediaPlayer.listener.OnUpdateSDKLrcListener;

/* JADX INFO: loaded from: classes3.dex */
public interface MediaCenterClient {
    boolean currentFocusIsEmpty();

    default OnMediaInfoCallbackListener getOnMediaInfoCallbackListener() {
        return null;
    }

    default OnStatusCallbackListener getOnStatusCallbackListener() {
        return null;
    }

    default OnUpdateSDKLrcListener getOnUpdateSDKLrcListener() {
        return null;
    }

    boolean hasCurrentFocus();

    default void onBackground() {
    }

    default void onFront() {
    }

    default void showNeedLogin() {
    }

    default void showNeedVip() {
    }

    default void updateCurrentProgress(long j) {
    }

    default void updateMusicPlayState(boolean z) {
    }
}
