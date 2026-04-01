package ecarx.eas.xsf.mediacenter;

import android.app.PendingIntent;
import android.net.Uri;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class IMediaListEx {
    private Uri artwork;
    private List<IMediaEx> mediaList;
    private String mediaListId;
    private int mediaListType;
    private PendingIntent pendingIntent;
    private int sourceType;
    private String title;

    public String getTitle() {
        return this.title;
    }

    public int getSourceType() {
        return this.sourceType;
    }

    public String getMediaListId() {
        return this.mediaListId;
    }

    public Uri getArtwork() {
        return this.artwork;
    }

    public List<IMediaEx> getMediaList() {
        return this.mediaList;
    }

    public int getMediaListType() {
        return this.mediaListType;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setArtwork(Uri uri) {
        this.artwork = uri;
    }

    public void setSourceType(int i) {
        this.sourceType = i;
    }

    public void setMediaListId(String str) {
        this.mediaListId = str;
    }

    public void setMediaList(List<IMediaEx> list) {
        this.mediaList = list;
    }

    public void setMediaListType(int i) {
        this.mediaListType = i;
    }

    public PendingIntent getPendingIntent() {
        return this.pendingIntent;
    }

    public void setPendingIntent(PendingIntent pendingIntent) {
        this.pendingIntent = pendingIntent;
    }

    public String toString() {
        return "IMediaList: title = " + this.title + " , sourceType = " + this.sourceType + " ,listId = " + this.mediaListId + ", listType = " + this.mediaListType + ", MediaList:" + this.mediaList;
    }
}
