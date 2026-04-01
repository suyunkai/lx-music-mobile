package com.ecarx.eas.sdk.mediacenter;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.RemoteException;
import android.util.Log;
import ecarx.xsf.mediacenter.IMusicPlaybackInfo;

/* JADX INFO: loaded from: classes2.dex */
public final class m extends IMusicPlaybackInfo.Stub {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private IMusicPlaybackInfo f182a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private MusicPlaybackInfo f183b;

    public m(IMusicPlaybackInfo iMusicPlaybackInfo) {
        this.f182a = iMusicPlaybackInfo;
    }

    public m(MusicPlaybackInfo musicPlaybackInfo) {
        this.f183b = musicPlaybackInfo;
    }

    @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
    public final PendingIntent getLaunchIntent() throws RemoteException {
        IMusicPlaybackInfo iMusicPlaybackInfo = this.f182a;
        if (iMusicPlaybackInfo != null) {
            return iMusicPlaybackInfo.getLaunchIntent();
        }
        MusicPlaybackInfo musicPlaybackInfo = this.f183b;
        if (musicPlaybackInfo != null) {
            return musicPlaybackInfo.getLaunchIntent();
        }
        return null;
    }

    @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
    public final String getTitle() {
        IMusicPlaybackInfo iMusicPlaybackInfo = this.f182a;
        if (iMusicPlaybackInfo != null) {
            return iMusicPlaybackInfo.getTitle();
        }
        MusicPlaybackInfo musicPlaybackInfo = this.f183b;
        if (musicPlaybackInfo != null) {
            return musicPlaybackInfo.getTitle();
        }
        Log.d("PlaybackInfoWrapper", "title is null");
        return "";
    }

    @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
    public final String getArtist() {
        IMusicPlaybackInfo iMusicPlaybackInfo = this.f182a;
        if (iMusicPlaybackInfo != null) {
            return iMusicPlaybackInfo.getArtist();
        }
        MusicPlaybackInfo musicPlaybackInfo = this.f183b;
        if (musicPlaybackInfo != null) {
            return musicPlaybackInfo.getArtist();
        }
        Log.d("PlaybackInfoWrapper", "artist is null");
        return "";
    }

    @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
    public final String getAlbum() throws RemoteException {
        IMusicPlaybackInfo iMusicPlaybackInfo = this.f182a;
        if (iMusicPlaybackInfo != null) {
            return iMusicPlaybackInfo.getAlbum();
        }
        MusicPlaybackInfo musicPlaybackInfo = this.f183b;
        if (musicPlaybackInfo != null) {
            return musicPlaybackInfo.getAlbum();
        }
        Log.d("PlaybackInfoWrapper", "album is null");
        return "";
    }

    @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
    public final long getDuration() {
        IMusicPlaybackInfo iMusicPlaybackInfo = this.f182a;
        if (iMusicPlaybackInfo != null) {
            return iMusicPlaybackInfo.getDuration();
        }
        MusicPlaybackInfo musicPlaybackInfo = this.f183b;
        if (musicPlaybackInfo != null) {
            return musicPlaybackInfo.getDuration();
        }
        Log.d("PlaybackInfoWrapper", "duration is null");
        return 0L;
    }

    @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
    public final int getPlayingItemPositionInQueue() throws RemoteException {
        IMusicPlaybackInfo iMusicPlaybackInfo = this.f182a;
        if (iMusicPlaybackInfo != null) {
            return iMusicPlaybackInfo.getPlayingItemPositionInQueue();
        }
        MusicPlaybackInfo musicPlaybackInfo = this.f183b;
        if (musicPlaybackInfo != null) {
            return musicPlaybackInfo.getPlayingItemPositionInQueue();
        }
        Log.d("PlaybackInfoWrapper", "PlayingItemPositionInQueue is null");
        return 0;
    }

    @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
    public final int getSourceType() throws RemoteException {
        IMusicPlaybackInfo iMusicPlaybackInfo = this.f182a;
        if (iMusicPlaybackInfo != null) {
            return iMusicPlaybackInfo.getSourceType();
        }
        MusicPlaybackInfo musicPlaybackInfo = this.f183b;
        if (musicPlaybackInfo != null) {
            return musicPlaybackInfo.getSourceType();
        }
        Log.d("PlaybackInfoWrapper", "source type is null");
        return 0;
    }

    @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
    public final Uri getMediaPath() throws RemoteException {
        IMusicPlaybackInfo iMusicPlaybackInfo = this.f182a;
        if (iMusicPlaybackInfo != null) {
            return iMusicPlaybackInfo.getMediaPath();
        }
        MusicPlaybackInfo musicPlaybackInfo = this.f183b;
        if (musicPlaybackInfo != null) {
            return musicPlaybackInfo.getMediaPath();
        }
        Log.d("PlaybackInfoWrapper", "MediaPath type is null");
        return null;
    }

    @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
    public final int getPlaybackStatus() throws RemoteException {
        IMusicPlaybackInfo iMusicPlaybackInfo = this.f182a;
        if (iMusicPlaybackInfo != null) {
            return iMusicPlaybackInfo.getPlaybackStatus();
        }
        MusicPlaybackInfo musicPlaybackInfo = this.f183b;
        if (musicPlaybackInfo != null) {
            return musicPlaybackInfo.getPlaybackStatus();
        }
        Log.d("PlaybackInfoWrapper", "PlaybackStatus type is null");
        return 0;
    }

    @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
    public final String getLyricContent() throws RemoteException {
        IMusicPlaybackInfo iMusicPlaybackInfo = this.f182a;
        if (iMusicPlaybackInfo != null) {
            return iMusicPlaybackInfo.getLyricContent();
        }
        MusicPlaybackInfo musicPlaybackInfo = this.f183b;
        return musicPlaybackInfo != null ? musicPlaybackInfo.getLyricContent() : "";
    }

    @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
    public final Uri getLyric() throws RemoteException {
        IMusicPlaybackInfo iMusicPlaybackInfo = this.f182a;
        if (iMusicPlaybackInfo != null) {
            return iMusicPlaybackInfo.getLyric();
        }
        MusicPlaybackInfo musicPlaybackInfo = this.f183b;
        if (musicPlaybackInfo != null) {
            return musicPlaybackInfo.getLyric();
        }
        return null;
    }

    @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
    public final String getCurrentLyricSentence() throws RemoteException {
        IMusicPlaybackInfo iMusicPlaybackInfo = this.f182a;
        if (iMusicPlaybackInfo != null) {
            return iMusicPlaybackInfo.getCurrentLyricSentence();
        }
        MusicPlaybackInfo musicPlaybackInfo = this.f183b;
        return musicPlaybackInfo != null ? musicPlaybackInfo.getCurrentLyricSentence() : "";
    }

    @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
    public final Uri getPreviousArtwork() throws RemoteException {
        IMusicPlaybackInfo iMusicPlaybackInfo = this.f182a;
        if (iMusicPlaybackInfo != null) {
            return iMusicPlaybackInfo.getPreviousArtwork();
        }
        MusicPlaybackInfo musicPlaybackInfo = this.f183b;
        if (musicPlaybackInfo != null) {
            return musicPlaybackInfo.getPreviousArtwork();
        }
        return null;
    }

    @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
    public final Uri getArtwork() throws RemoteException {
        IMusicPlaybackInfo iMusicPlaybackInfo = this.f182a;
        if (iMusicPlaybackInfo != null) {
            return iMusicPlaybackInfo.getArtwork();
        }
        MusicPlaybackInfo musicPlaybackInfo = this.f183b;
        if (musicPlaybackInfo != null) {
            return musicPlaybackInfo.getArtwork();
        }
        Log.d("PlaybackInfoWrapper", "artwork is null");
        return null;
    }

    @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
    public final Uri getNextArtwork() throws RemoteException {
        IMusicPlaybackInfo iMusicPlaybackInfo = this.f182a;
        if (iMusicPlaybackInfo != null) {
            return iMusicPlaybackInfo.getNextArtwork();
        }
        MusicPlaybackInfo musicPlaybackInfo = this.f183b;
        if (musicPlaybackInfo != null) {
            return musicPlaybackInfo.getNextArtwork();
        }
        return null;
    }

    @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
    public final int getLoopMode() throws RemoteException {
        IMusicPlaybackInfo iMusicPlaybackInfo = this.f182a;
        if (iMusicPlaybackInfo != null) {
            return iMusicPlaybackInfo.getLoopMode();
        }
        MusicPlaybackInfo musicPlaybackInfo = this.f183b;
        if (musicPlaybackInfo != null) {
            return musicPlaybackInfo.getLoopMode();
        }
        return 0;
    }

    @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
    public final int getRadioMode() throws RemoteException {
        IMusicPlaybackInfo iMusicPlaybackInfo = this.f182a;
        if (iMusicPlaybackInfo != null) {
            return iMusicPlaybackInfo.getRadioMode();
        }
        MusicPlaybackInfo musicPlaybackInfo = this.f183b;
        if (musicPlaybackInfo != null) {
            return musicPlaybackInfo.getRadioMode();
        }
        return 0;
    }

    @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
    public final boolean isSupportCollect() throws RemoteException {
        if (this.f182a != null) {
            Log.e("PlaybackInfoWrapper", "old interface not support");
            return false;
        }
        MusicPlaybackInfo musicPlaybackInfo = this.f183b;
        if (musicPlaybackInfo != null) {
            return musicPlaybackInfo.isSupportCollect();
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
    public final boolean isCollected() throws RemoteException {
        if (this.f182a != null) {
            Log.e("PlaybackInfoWrapper", "old interface not support");
            return false;
        }
        MusicPlaybackInfo musicPlaybackInfo = this.f183b;
        if (musicPlaybackInfo != null) {
            return musicPlaybackInfo.isCollected();
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
    public final boolean isSupportDownload() throws RemoteException {
        if (this.f182a != null) {
            Log.e("PlaybackInfoWrapper", "old interface not support");
            return false;
        }
        MusicPlaybackInfo musicPlaybackInfo = this.f183b;
        if (musicPlaybackInfo != null) {
            return musicPlaybackInfo.isSupportDownload();
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
    public final boolean isDownloaded() throws RemoteException {
        if (this.f182a != null) {
            Log.e("PlaybackInfoWrapper", "old interface not support");
            return false;
        }
        MusicPlaybackInfo musicPlaybackInfo = this.f183b;
        if (musicPlaybackInfo != null) {
            return musicPlaybackInfo.isDownloaded();
        }
        return false;
    }

    @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
    public final String getUuid() throws RemoteException {
        if (this.f182a != null) {
            Log.e("PlaybackInfoWrapper", "old interface not support");
            return null;
        }
        MusicPlaybackInfo musicPlaybackInfo = this.f183b;
        if (musicPlaybackInfo != null) {
            return musicPlaybackInfo.getUuid();
        }
        Log.d("PlaybackInfoWrapper", "uuid is null");
        return null;
    }

    @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
    public final String getAppName() throws RemoteException {
        if (this.f182a != null) {
            Log.e("PlaybackInfoWrapper", "old interface not support");
            return null;
        }
        MusicPlaybackInfo musicPlaybackInfo = this.f183b;
        if (musicPlaybackInfo != null) {
            return musicPlaybackInfo.getAppName();
        }
        Log.d("PlaybackInfoWrapper", "app name is null");
        return null;
    }

    @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
    public final String getAppIcon() throws RemoteException {
        if (this.f182a != null) {
            Log.e("PlaybackInfoWrapper", "old interface not support");
            return null;
        }
        MusicPlaybackInfo musicPlaybackInfo = this.f183b;
        if (musicPlaybackInfo != null) {
            return musicPlaybackInfo.getAppIcon();
        }
        return null;
    }

    @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
    public final String getPackageName() throws RemoteException {
        if (this.f182a != null) {
            Log.e("PlaybackInfoWrapper", "old interface not support");
            return null;
        }
        MusicPlaybackInfo musicPlaybackInfo = this.f183b;
        if (musicPlaybackInfo != null) {
            return musicPlaybackInfo.getPackageName();
        }
        return null;
    }

    @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
    public final boolean isSupportLoopModeSwitch() throws RemoteException {
        if (this.f182a != null) {
            Log.e("PlaybackInfoWrapper", "old interface not support");
            return true;
        }
        MusicPlaybackInfo musicPlaybackInfo = this.f183b;
        if (musicPlaybackInfo != null) {
            return musicPlaybackInfo.isSupportLoopModeSwitch();
        }
        return true;
    }

    @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
    public final boolean isSupportVrCtrlPlayStatus() throws RemoteException {
        if (this.f182a != null) {
            Log.e("PlaybackInfoWrapper", "old interface not support");
            return true;
        }
        MusicPlaybackInfo musicPlaybackInfo = this.f183b;
        if (musicPlaybackInfo != null) {
            return musicPlaybackInfo.isSupportVrCtrlPlayStatus();
        }
        return true;
    }

    @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
    public final String getRadioFrequency() {
        IMusicPlaybackInfo iMusicPlaybackInfo = this.f182a;
        if (iMusicPlaybackInfo != null) {
            return iMusicPlaybackInfo.getRadioFrequency();
        }
        MusicPlaybackInfo musicPlaybackInfo = this.f183b;
        return musicPlaybackInfo != null ? musicPlaybackInfo.getRadioFrequency() : "";
    }

    @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
    public final String getRadioStationName() throws RemoteException {
        IMusicPlaybackInfo iMusicPlaybackInfo = this.f182a;
        if (iMusicPlaybackInfo != null) {
            return iMusicPlaybackInfo.getRadioStationName();
        }
        MusicPlaybackInfo musicPlaybackInfo = this.f183b;
        if (musicPlaybackInfo != null) {
            return musicPlaybackInfo.getRadioStationName();
        }
        return null;
    }

    @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
    public final String getPlayingMediaListId() throws RemoteException {
        if (this.f182a != null) {
            Log.e("PlaybackInfoWrapper", "old interface not support");
            return null;
        }
        MusicPlaybackInfo musicPlaybackInfo = this.f183b;
        if (musicPlaybackInfo != null) {
            return musicPlaybackInfo.getPlayingMediaListId();
        }
        return null;
    }

    @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
    public final int getVip() throws RemoteException {
        if (this.f182a != null) {
            Log.e("PlaybackInfoWrapper", "old interface not support");
            return -1;
        }
        MusicPlaybackInfo musicPlaybackInfo = this.f183b;
        if (musicPlaybackInfo != null) {
            return musicPlaybackInfo.getVip();
        }
        return -1;
    }

    @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
    public final int getPlayingMediaListType() throws RemoteException {
        if (this.f182a != null) {
            Log.e("PlaybackInfoWrapper", "old interface not support");
            return -1;
        }
        MusicPlaybackInfo musicPlaybackInfo = this.f183b;
        if (musicPlaybackInfo != null) {
            return musicPlaybackInfo.getPlayingMediaListType();
        }
        return -1;
    }

    @Override // ecarx.xsf.mediacenter.IMusicPlaybackInfo
    public final PendingIntent getPlayerIntent() throws RemoteException {
        if (this.f182a != null) {
            Log.e("PlaybackInfoWrapper", "old interface not support");
            return null;
        }
        MusicPlaybackInfo musicPlaybackInfo = this.f183b;
        if (musicPlaybackInfo != null) {
            return musicPlaybackInfo.getPlayerIntent();
        }
        return null;
    }
}
