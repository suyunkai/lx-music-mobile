package com.ecarx.eas.sdk.mediacenter;

import android.net.Uri;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public interface IMediaInfo {
    String getAlbum();

    String getArtist();

    Uri getArtwork();

    long getDuration();

    Uri getLyric();

    String getLyricContent();

    Uri getMediaPath();

    int getPlayingItemPositionInQueue();

    String getRadioFrequency();

    String getRadioStationName();

    int getSourceType();

    String getTitle();
}
