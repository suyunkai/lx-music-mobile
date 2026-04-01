package com.ecarx.eas.sdk.mediacenter;

import android.net.Uri;

/* JADX INFO: loaded from: classes2.dex */
public final class h extends MediaInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f166a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f167b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f168c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f169d;
    private long e;
    private int f;
    private int g;
    private Uri h;
    private String i;
    private Uri j;
    private Uri k;
    private String l;
    private String m;
    private String n;
    private int o;
    private int p;

    @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
    public final int getCollected() {
        return 0;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
    public final int getSupportCollect() {
        return 0;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
    public final String getUuid() {
        return this.f166a;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
    public final String getTitle() {
        return this.f167b;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
    public final String getArtist() {
        return this.f168c;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
    public final String getAlbum() {
        return this.f169d;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
    public final long getDuration() {
        return this.e;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
    public final int getPlayingItemPositionInQueue() {
        return this.f;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
    public final int getSourceType() {
        return this.g;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
    public final Uri getMediaPath() {
        return this.h;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
    public final String getLyricContent() {
        return this.i;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
    public final Uri getLyric() {
        return this.j;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
    public final Uri getArtwork() {
        return this.k;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
    public final String getRadioFrequency() {
        return this.l;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
    public final String getRadioStationName() {
        return this.m;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
    public final String getPlayingMediaListId() {
        return this.n;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
    public final int getPlayingMediaListType() {
        return this.o;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MediaInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaInfo
    public final int getVip() {
        return this.p;
    }

    public final void a(String str) {
        this.f166a = str;
    }

    public final void b(String str) {
        this.f167b = str;
    }

    public final void c(String str) {
        this.f168c = str;
    }

    public final void d(String str) {
        this.f169d = str;
    }

    public final void a(long j) {
        this.e = j;
    }

    public final void a(int i) {
        this.f = i;
    }

    public final void b(int i) {
        this.g = i;
    }

    public final void a(Uri uri) {
        this.h = uri;
    }

    public final void e(String str) {
        this.i = str;
    }

    public final void b(Uri uri) {
        this.j = uri;
    }

    public final void c(Uri uri) {
        this.k = uri;
    }

    public final void f(String str) {
        this.l = str;
    }

    public final void g(String str) {
        this.m = str;
    }

    public final void c(int i) {
        this.p = i;
    }

    public final void h(String str) {
        this.n = str;
    }

    public final void d(int i) {
        this.o = i;
    }
}
