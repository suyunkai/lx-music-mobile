package com.ecarx.eas.sdk.mediacenter;

import android.app.PendingIntent;
import android.net.Uri;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractContent {
    public abstract Uri getBackground();

    public abstract String getId();

    public abstract PendingIntent getPendingIntent();

    public abstract String getTitle();
}
