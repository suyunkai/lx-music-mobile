package com.ecarx.eas.sdk.mediacenter;

import android.net.Uri;

/* JADX INFO: loaded from: classes2.dex */
public final class c implements IMediaInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f132a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f133b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f134c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private long f135d;
    private int e;
    private int f;
    private Uri g;
    private String h;
    private Uri i;
    private Uri j;
    private String k;
    private String l;

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaInfo
    public final String getTitle() {
        return this.f132a;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaInfo
    public final String getArtist() {
        return this.f133b;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaInfo
    public final String getAlbum() {
        return this.f134c;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaInfo
    public final long getDuration() {
        return this.f135d;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaInfo
    public final int getPlayingItemPositionInQueue() {
        return this.e;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaInfo
    public final int getSourceType() {
        return this.f;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaInfo
    public final Uri getMediaPath() {
        return this.g;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaInfo
    public final String getLyricContent() {
        return this.h;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaInfo
    public final Uri getLyric() {
        return this.i;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaInfo
    public final Uri getArtwork() {
        return this.j;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaInfo
    public final String getRadioFrequency() {
        return this.k;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.IMediaInfo
    public final String getRadioStationName() {
        return this.l;
    }

    public final void a(String str) {
        this.f132a = str;
    }

    public final void b(String str) {
        this.f133b = str;
    }

    public final void c(String str) {
        this.f134c = str;
    }

    public final void a(long j) {
        this.f135d = j;
    }

    public final void a(int i) {
        this.e = i;
    }

    public final void b(int i) {
        this.f = i;
    }

    public final void a(Uri uri) {
        this.g = uri;
    }

    public final void d(String str) {
        this.h = str;
    }

    public final void b(Uri uri) {
        this.i = uri;
    }

    public final void c(Uri uri) {
        this.j = uri;
    }

    public final void e(String str) {
        this.k = str;
    }

    public final void f(String str) {
        this.l = str;
    }
}
