package com.ecarx.eas.sdk.mediacenter.control.bean;

import android.app.PendingIntent;
import android.net.Uri;

/* JADX INFO: loaded from: classes2.dex */
public class MusicPlaybackInfo {
    private Uri Lyric;
    private String album;
    private String appIcon;
    private String appName;
    private String artist;
    private Uri artwork;
    private boolean collected;
    private String currentLyricSentence;
    private boolean downloaded;
    private long duration;
    private PendingIntent launchIntent;
    private int loopMode;
    private String lyricContent;
    private Uri mediaPath;
    private Uri nextArtwork;
    private int playbackStatus;
    private PendingIntent playerIntent;
    private int playingItemPositionInQueue;
    private String playingMediaListId;
    private int playingMediaListType;
    private Uri previousArtwork;
    private String radioFrequency;
    private int radioMode;
    private String radioStationName;
    private int sourceType;
    private boolean supportCollect;
    private boolean supportDownload;
    private String title;
    private String uuid;
    private int vip;

    public PendingIntent getLaunchIntent() {
        return this.launchIntent;
    }

    public void setLaunchIntent(PendingIntent pendingIntent) {
        this.launchIntent = pendingIntent;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getArtist() {
        return this.artist;
    }

    public void setArtist(String str) {
        this.artist = str;
    }

    public String getAlbum() {
        return this.album;
    }

    public void setAlbum(String str) {
        this.album = str;
    }

    public String getRadioFrequency() {
        return this.radioFrequency;
    }

    public void setRadioFrequency(String str) {
        this.radioFrequency = str;
    }

    public String getRadioStationName() {
        return this.radioStationName;
    }

    public void setRadioStationName(String str) {
        this.radioStationName = str;
    }

    public long getDuration() {
        return this.duration;
    }

    public void setDuration(long j) {
        this.duration = j;
    }

    public int getPlayingItemPositionInQueue() {
        return this.playingItemPositionInQueue;
    }

    public void setPlayingItemPositionInQueue(int i) {
        this.playingItemPositionInQueue = i;
    }

    public int getSourceType() {
        return this.sourceType;
    }

    public void setSourceType(int i) {
        this.sourceType = i;
    }

    public Uri getMediaPath() {
        return this.mediaPath;
    }

    public void setMediaPath(Uri uri) {
        this.mediaPath = uri;
    }

    public int getPlaybackStatus() {
        return this.playbackStatus;
    }

    public void setPlaybackStatus(int i) {
        this.playbackStatus = i;
    }

    public String getLyricContent() {
        return this.lyricContent;
    }

    public void setLyricContent(String str) {
        this.lyricContent = str;
    }

    public Uri getLyric() {
        return this.Lyric;
    }

    public void setLyric(Uri uri) {
        this.Lyric = uri;
    }

    public String getCurrentLyricSentence() {
        return this.currentLyricSentence;
    }

    public void setCurrentLyricSentence(String str) {
        this.currentLyricSentence = str;
    }

    public Uri getPreviousArtwork() {
        return this.previousArtwork;
    }

    public void setPreviousArtwork(Uri uri) {
        this.previousArtwork = uri;
    }

    public Uri getArtwork() {
        return this.artwork;
    }

    public void setArtwork(Uri uri) {
        this.artwork = uri;
    }

    public Uri getNextArtwork() {
        return this.nextArtwork;
    }

    public void setNextArtwork(Uri uri) {
        this.nextArtwork = uri;
    }

    public int getLoopMode() {
        return this.loopMode;
    }

    public void setLoopMode(int i) {
        this.loopMode = i;
    }

    public int getRadioMode() {
        return this.radioMode;
    }

    public void setRadioMode(int i) {
        this.radioMode = i;
    }

    public boolean isSupportCollect() {
        return this.supportCollect;
    }

    public void setSupportCollect(boolean z) {
        this.supportCollect = z;
    }

    public boolean isCollected() {
        return this.collected;
    }

    public void setCollected(boolean z) {
        this.collected = z;
    }

    public boolean isSupportDownload() {
        return this.supportDownload;
    }

    public void setSupportDownload(boolean z) {
        this.supportDownload = z;
    }

    public boolean isDownloaded() {
        return this.downloaded;
    }

    public void setDownloaded(boolean z) {
        this.downloaded = z;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String str) {
        this.uuid = str;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String str) {
        this.appName = str;
    }

    public String getAppIcon() {
        return this.appIcon;
    }

    public void setAppIcon(String str) {
        this.appIcon = str;
    }

    public String getPlayingMediaListId() {
        return this.playingMediaListId;
    }

    public void setPlayingMediaListId(String str) {
        this.playingMediaListId = str;
    }

    public int getVip() {
        return this.vip;
    }

    public void setVip(int i) {
        this.vip = i;
    }

    public int getPlayingMediaListType() {
        return this.playingMediaListType;
    }

    public void setPlayingMediaListType(int i) {
        this.playingMediaListType = i;
    }

    public PendingIntent getPlayerIntent() {
        return this.playerIntent;
    }

    public void setPlayerIntent(PendingIntent pendingIntent) {
        this.playerIntent = pendingIntent;
    }
}
