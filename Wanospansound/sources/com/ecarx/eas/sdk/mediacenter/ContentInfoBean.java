package com.ecarx.eas.sdk.mediacenter;

import android.app.PendingIntent;
import android.net.Uri;

/* JADX INFO: loaded from: classes2.dex */
public class ContentInfoBean extends ContentInfo {
    private Uri background;
    private String id;
    private PendingIntent pendingIntent;
    private String title;

    @Override // com.ecarx.eas.sdk.mediacenter.ContentInfo, com.ecarx.eas.sdk.mediacenter.AbstractContent
    public String getId() {
        return this.id;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.ContentInfo, com.ecarx.eas.sdk.mediacenter.AbstractContent
    public String getTitle() {
        return this.title;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.ContentInfo, com.ecarx.eas.sdk.mediacenter.AbstractContent
    public PendingIntent getPendingIntent() {
        return this.pendingIntent;
    }

    @Override // com.ecarx.eas.sdk.mediacenter.ContentInfo, com.ecarx.eas.sdk.mediacenter.AbstractContent
    public Uri getBackground() {
        return this.background;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setPendingIntent(PendingIntent pendingIntent) {
        this.pendingIntent = pendingIntent;
    }

    public void setBackground(Uri uri) {
        this.background = uri;
    }
}
