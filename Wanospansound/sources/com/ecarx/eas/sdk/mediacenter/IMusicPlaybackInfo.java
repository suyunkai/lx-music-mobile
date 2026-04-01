package com.ecarx.eas.sdk.mediacenter;

import android.app.PendingIntent;
import android.net.Uri;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public interface IMusicPlaybackInfo {
    public static final int LOOP_MODE_ALL = 0;
    public static final int LOOP_MODE_SHUFFLE = 2;
    public static final int LOOP_MODE_SINGLE = 1;
    public static final int PLAYBACK_STATUS_INTERRUPT = 2;
    public static final int PLAYBACK_STATUS_PAUSED = 0;
    public static final int PLAYBACK_STATUS_PLAYING = 1;
    public static final int PLAYBACK_STATUS_PREPARE = 3;
    public static final int RADIO_MODE_CAROUSEL = 1;
    public static final int RADIO_MODE_PLAYING = 0;
    public static final int RADIO_MODE_SCAN = 4;
    public static final int RADIO_MODE_SEEK_NEXT = 3;
    public static final int RADIO_MODE_SEEK_PREV = 2;
    public static final int SOURCE_TYPE_AM = 4;
    public static final int SOURCE_TYPE_AUX = 5;
    public static final int SOURCE_TYPE_BT = 2;
    public static final int SOURCE_TYPE_FM = 3;
    public static final int SOURCE_TYPE_LOCAL = 0;
    public static final int SOURCE_TYPE_ONLINE = 6;
    public static final int SOURCE_TYPE_STATION = 8;
    public static final int SOURCE_TYPE_USB = 1;
    public static final int SOURCE_TYPE_USB2 = 7;

    String getAlbum();

    String getArtist();

    Uri getArtwork();

    String getCurrentLyricSentence();

    long getDuration();

    PendingIntent getLaunchIntent();

    int getLoopMode();

    Uri getLyric();

    String getLyricContent();

    Uri getMediaPath();

    Uri getNextArtwork();

    int getPlaybackStatus();

    int getPlayingItemPositionInQueue();

    Uri getPreviousArtwork();

    String getRadioFrequency();

    int getRadioMode();

    String getRadioStationName();

    int getSourceType();

    String getTitle();
}
