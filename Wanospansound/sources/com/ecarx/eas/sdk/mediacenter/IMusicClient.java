package com.ecarx.eas.sdk.mediacenter;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public interface IMusicClient {
    public static final int LOOP_MODE_ALL = 0;
    public static final int LOOP_MODE_SHUFFLE = 2;
    public static final int LOOP_MODE_SINGLE = 1;

    long getCurrentProgress();

    int getCurrentSourceType();

    int[] getMediaSourceTypeList();

    IMusicPlaybackInfo getMusicPlaybackInfo();

    List<IMediaInfo> getPlaylist(int i);

    boolean onForward();

    boolean onLoopModeChange(int i);

    boolean onMediaSelected(IMediaInfo iMediaInfo);

    boolean onNext();

    boolean onPause();

    boolean onPlay();

    boolean onPrevious();

    boolean onRewind();

    boolean onSourceSelected(int i);
}
