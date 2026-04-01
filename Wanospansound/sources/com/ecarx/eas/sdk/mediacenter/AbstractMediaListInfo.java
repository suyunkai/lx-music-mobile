package com.ecarx.eas.sdk.mediacenter;

import android.app.PendingIntent;
import android.net.Uri;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractMediaListInfo {
    public abstract Uri getArtwork();

    public abstract List<MediaInfo> getMediaList();

    public abstract String getMediaListId();

    public abstract int getMediaListType();

    public abstract PendingIntent getPendingIntent();

    public abstract int getSourceType();

    public abstract String getTitle();
}
