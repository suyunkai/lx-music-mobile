package ecarx.xsf.mediacenter.session;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public class PlaybackInfo implements Parcelable {
    public static final Parcelable.Creator<PlaybackInfo> CREATOR = new Parcelable.Creator<PlaybackInfo>() { // from class: ecarx.xsf.mediacenter.session.PlaybackInfo.1
        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ PlaybackInfo[] newArray(int i) {
            return new PlaybackInfo[i];
        }

        @Override // android.os.Parcelable.Creator
        public /* synthetic */ PlaybackInfo createFromParcel(Parcel parcel) {
            return new PlaybackInfo(parcel);
        }
    };
    private String appName;
    private Uri iconUri;
    private long initialProgress;
    private PendingIntent launchIntent;
    private MediaItem mediaItem;
    private String pkgName;
    private PendingIntent playerIntent;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getPkgName() {
        return this.pkgName;
    }

    public Uri getIconUri() {
        return this.iconUri;
    }

    public String getAppName() {
        return this.appName;
    }

    public PendingIntent getLaunchIntent() {
        return this.launchIntent;
    }

    public PendingIntent getPlayerIntent() {
        return this.playerIntent;
    }

    public long getInitialProgress() {
        return this.initialProgress;
    }

    public MediaItem getMediaItem() {
        return this.mediaItem;
    }

    public String toString() {
        return "PlaybackInfo{pkgName='" + this.pkgName + "', iconUri=" + this.iconUri + ", appName='" + this.appName + "', launchIntent=" + this.launchIntent + ", playerIntent=" + this.playerIntent + ", initialProgress=" + this.initialProgress + ", mediaItem=" + this.mediaItem + '}';
    }

    private PlaybackInfo(Builder builder) {
        this.pkgName = builder.pkgName;
        this.iconUri = builder.iconUri;
        this.appName = builder.appName;
        this.launchIntent = builder.launchIntent;
        this.playerIntent = builder.playerIntent;
        this.initialProgress = builder.initialProgress;
        this.mediaItem = builder.mediaItem;
    }

    public static final class Builder {
        private String appName;
        private Uri iconUri;
        private long initialProgress;
        private PendingIntent launchIntent;
        private MediaItem mediaItem;
        private String pkgName;
        private PendingIntent playerIntent;

        public Builder() {
        }

        public Builder(PlaybackInfo playbackInfo) {
            if (playbackInfo == null) {
                return;
            }
            this.pkgName = playbackInfo.pkgName;
            this.iconUri = playbackInfo.iconUri;
            this.appName = playbackInfo.appName;
            this.initialProgress = playbackInfo.initialProgress;
            this.mediaItem = playbackInfo.mediaItem;
        }

        public final Builder pkgName(String str) {
            this.pkgName = str;
            return this;
        }

        public final Builder iconUri(Uri uri) {
            this.iconUri = uri;
            return this;
        }

        public final Builder appName(String str) {
            this.appName = str;
            return this;
        }

        public final Builder launchIntent(PendingIntent pendingIntent) {
            this.launchIntent = pendingIntent;
            return this;
        }

        public final Builder playerIntent(PendingIntent pendingIntent) {
            this.playerIntent = pendingIntent;
            return this;
        }

        public final Builder initialProgress(long j) {
            this.initialProgress = j;
            return this;
        }

        public final Builder mediaItem(MediaItem mediaItem) {
            this.mediaItem = mediaItem;
            return this;
        }

        public final PlaybackInfo build() {
            return new PlaybackInfo(this);
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.pkgName);
        parcel.writeParcelable(this.iconUri, i);
        parcel.writeString(this.appName);
        parcel.writeParcelable(this.launchIntent, i);
        parcel.writeParcelable(this.playerIntent, i);
        parcel.writeLong(this.initialProgress);
        parcel.writeParcelable(this.mediaItem, i);
    }

    public void readFromParcel(Parcel parcel) {
        this.pkgName = parcel.readString();
        this.iconUri = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.appName = parcel.readString();
        this.launchIntent = (PendingIntent) parcel.readParcelable(PendingIntent.class.getClassLoader());
        this.playerIntent = (PendingIntent) parcel.readParcelable(PendingIntent.class.getClassLoader());
        this.initialProgress = parcel.readLong();
        this.mediaItem = (MediaItem) parcel.readParcelable(MediaItem.class.getClassLoader());
    }

    protected PlaybackInfo(Parcel parcel) {
        this.pkgName = parcel.readString();
        this.iconUri = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.appName = parcel.readString();
        this.launchIntent = (PendingIntent) parcel.readParcelable(PendingIntent.class.getClassLoader());
        this.playerIntent = (PendingIntent) parcel.readParcelable(PendingIntent.class.getClassLoader());
        this.initialProgress = parcel.readLong();
        this.mediaItem = (MediaItem) parcel.readParcelable(MediaItem.class.getClassLoader());
    }
}
