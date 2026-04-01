package com.ecarx.eas.sdk.mediacenter;

import android.app.PendingIntent;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class i extends MediaListInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f170a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f171b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f172c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f173d;
    private List<MediaInfo> e;
    private PendingIntent f;

    @Override // com.ecarx.eas.sdk.mediacenter.MediaListInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaListInfo
    public final String getTitle() {
        return this.f171b;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MediaListInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaListInfo
    public final String getMediaListId() {
        return this.f170a;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MediaListInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaListInfo
    public final int getMediaListType() {
        return this.f173d;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MediaListInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaListInfo
    public final int getSourceType() {
        return this.f172c;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MediaListInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaListInfo
    public final List<MediaInfo> getMediaList() {
        return this.e;
    }

    public final void a(String str) {
        this.f171b = str;
    }

    public final void a(int i) {
        this.f172c = i;
    }

    public final void b(int i) {
        this.f173d = i;
    }

    public final void a(List<MediaInfo> list) {
        this.e = list;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.MediaListInfo, com.ecarx.eas.sdk.mediacenter.AbstractMediaListInfo
    public final PendingIntent getPendingIntent() {
        return this.f;
    }

    public final void a(PendingIntent pendingIntent) {
        this.f = pendingIntent;
    }
}
