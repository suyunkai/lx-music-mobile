package ecarx.xsf.mediacenter.session;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public class MediaItem implements Parcelable {
    public static final Parcelable.Creator<MediaItem> CREATOR = new Parcelable.Creator<MediaItem>() { // from class: ecarx.xsf.mediacenter.session.MediaItem.1
        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ MediaItem[] newArray(int i) {
            return new MediaItem[i];
        }

        @Override // android.os.Parcelable.Creator
        public /* synthetic */ MediaItem createFromParcel(Parcel parcel) {
            return new MediaItem(parcel);
        }
    };
    private String album;
    private String artist;
    private Uri artwork;
    private Uri artworkPath;
    private long dateTime;
    private boolean downloadSupported;
    private boolean downloaded;
    private long duration;
    private boolean favoriteSupported;
    private boolean favorited;
    private String id;
    private boolean loopModeSupported;
    private String lyricContent;
    private Uri lyricUri;
    private int radioBand;
    private String radioFrequency;
    private String radioName;
    private int sourceType;
    private String subtitle;
    private String title;
    private boolean vipNeeded;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getId() {
        return this.id;
    }

    public int getSourceType() {
        return this.sourceType;
    }

    public String getTitle() {
        return this.title;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public String getAlbum() {
        return this.album;
    }

    public String getArtist() {
        return this.artist;
    }

    public Uri getArtwork() {
        return this.artwork;
    }

    public Uri getLyricUri() {
        return this.lyricUri;
    }

    public String getLyricContent() {
        return this.lyricContent;
    }

    public long getDuration() {
        return this.duration;
    }

    public boolean isFavoriteSupported() {
        return this.favoriteSupported;
    }

    public boolean isDownloadSupported() {
        return this.downloadSupported;
    }

    public boolean isVipNeeded() {
        return this.vipNeeded;
    }

    public boolean isDownloaded() {
        return this.downloaded;
    }

    public boolean isFavorited() {
        return this.favorited;
    }

    public boolean isLoopModeSupported() {
        return this.loopModeSupported;
    }

    public String getRadioFrequency() {
        return this.radioFrequency;
    }

    public int getRadioBand() {
        return this.radioBand;
    }

    public String getRadioName() {
        return this.radioName;
    }

    public long getDateTime() {
        return this.dateTime;
    }

    public Uri getArtworkPath() {
        return this.artworkPath;
    }

    public String toString() {
        return "MediaItem{id='" + this.id + "', sourceType=" + this.sourceType + ", title='" + this.title + "', subtitle='" + this.subtitle + "', album='" + this.album + "', artist='" + this.artist + "', artwork=" + this.artwork + ", lyricUri=" + this.lyricUri + ", lyricContent='" + this.lyricContent + "', dateTime=" + this.dateTime + ", duration=" + this.duration + ", favoriteSupported=" + this.favoriteSupported + ", downloadSupported=" + this.downloadSupported + ", vipNeeded=" + this.vipNeeded + ", downloaded=" + this.downloaded + ", favorited=" + this.favorited + ", radioFrequency='" + this.radioFrequency + "', radioBand=" + this.radioBand + ", radioName='" + this.radioName + "', artworkPath='" + this.artworkPath + "', loopModeSupported=" + this.loopModeSupported + '}';
    }

    private MediaItem(Builder builder) {
        this.id = builder.id;
        this.sourceType = builder.sourceType;
        this.title = builder.title;
        this.subtitle = builder.subtitle;
        this.album = builder.album;
        this.artist = builder.artist;
        this.artwork = builder.artwork;
        this.lyricUri = builder.lyricUri;
        this.lyricContent = builder.lyricContent;
        this.duration = builder.duration;
        this.favoriteSupported = builder.favoriteSupported;
        this.downloadSupported = builder.downloadSupported;
        this.vipNeeded = builder.vipNeeded;
        this.downloaded = builder.downloaded;
        this.favorited = builder.favorited;
        this.radioFrequency = builder.radioFrequency;
        this.radioBand = builder.radioBand;
        this.radioName = builder.radioName;
        this.dateTime = builder.dateTime;
        this.artworkPath = builder.artworkPath;
        this.loopModeSupported = builder.loopModeSupported;
    }

    public static final class Builder {
        private String album;
        private String artist;
        private Uri artwork;
        private Uri artworkPath;
        private long dateTime;
        private boolean downloadSupported;
        private boolean downloaded;
        private long duration;
        private boolean favoriteSupported;
        private boolean favorited;
        private String id;
        private boolean loopModeSupported;
        private String lyricContent;
        private Uri lyricUri;
        private int radioBand;
        private String radioFrequency;
        private String radioName;
        private int sourceType;
        private String subtitle;
        private String title;
        private boolean vipNeeded;

        public final Builder id(String str) {
            this.id = str;
            return this;
        }

        public final Builder sourceType(int i) {
            this.sourceType = i;
            return this;
        }

        public final Builder title(String str) {
            this.title = str;
            return this;
        }

        public final Builder subtitle(String str) {
            this.subtitle = str;
            return this;
        }

        public final Builder album(String str) {
            this.album = str;
            return this;
        }

        public final Builder artist(String str) {
            this.artist = str;
            return this;
        }

        public final Builder artwork(Uri uri) {
            this.artwork = uri;
            return this;
        }

        public final Builder lyricUri(Uri uri) {
            this.lyricUri = uri;
            return this;
        }

        public final Builder lyricContent(String str) {
            this.lyricContent = str;
            return this;
        }

        public final Builder dateTime(long j) {
            this.dateTime = j;
            return this;
        }

        public final Builder duration(long j) {
            this.duration = j;
            return this;
        }

        public final Builder favoriteSupported(boolean z) {
            this.favoriteSupported = z;
            return this;
        }

        public final Builder downloadSupported(boolean z) {
            this.downloadSupported = z;
            return this;
        }

        public final Builder vipNeeded(boolean z) {
            this.vipNeeded = z;
            return this;
        }

        public final Builder downloaded(boolean z) {
            this.downloaded = z;
            return this;
        }

        public final Builder favorited(boolean z) {
            this.favorited = z;
            return this;
        }

        public final Builder radioFrequency(String str) {
            this.radioFrequency = str;
            return this;
        }

        public final Builder radioBand(int i) {
            this.radioBand = i;
            return this;
        }

        public final Builder radioName(String str) {
            this.radioName = str;
            return this;
        }

        public final Builder artworkPath(Uri uri) {
            this.artworkPath = uri;
            return this;
        }

        public final Builder loopModeSupported(boolean z) {
            this.loopModeSupported = z;
            return this;
        }

        public final MediaItem build() {
            return new MediaItem(this);
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeInt(this.sourceType);
        parcel.writeString(this.title);
        parcel.writeString(this.subtitle);
        parcel.writeString(this.album);
        parcel.writeString(this.artist);
        parcel.writeParcelable(this.artwork, i);
        parcel.writeParcelable(this.lyricUri, i);
        parcel.writeString(this.lyricContent);
        parcel.writeLong(this.dateTime);
        parcel.writeLong(this.duration);
        parcel.writeByte(this.favoriteSupported ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.downloadSupported ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.vipNeeded ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.downloaded ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.favorited ? (byte) 1 : (byte) 0);
        parcel.writeString(this.radioFrequency);
        parcel.writeInt(this.radioBand);
        parcel.writeString(this.radioName);
        parcel.writeParcelable(this.artworkPath, i);
        parcel.writeByte(this.loopModeSupported ? (byte) 1 : (byte) 0);
    }

    public void readFromParcel(Parcel parcel) {
        this.id = parcel.readString();
        this.sourceType = parcel.readInt();
        this.title = parcel.readString();
        this.subtitle = parcel.readString();
        this.album = parcel.readString();
        this.artist = parcel.readString();
        this.artwork = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.lyricUri = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.lyricContent = parcel.readString();
        this.dateTime = parcel.readLong();
        this.duration = parcel.readLong();
        this.favoriteSupported = parcel.readByte() != 0;
        this.downloadSupported = parcel.readByte() != 0;
        this.vipNeeded = parcel.readByte() != 0;
        this.downloaded = parcel.readByte() != 0;
        this.favorited = parcel.readByte() != 0;
        this.radioFrequency = parcel.readString();
        this.radioBand = parcel.readInt();
        this.radioName = parcel.readString();
        this.artworkPath = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.loopModeSupported = parcel.readByte() != 0;
    }

    protected MediaItem(Parcel parcel) {
        this.id = parcel.readString();
        this.sourceType = parcel.readInt();
        this.title = parcel.readString();
        this.subtitle = parcel.readString();
        this.album = parcel.readString();
        this.artist = parcel.readString();
        this.artwork = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.lyricUri = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.lyricContent = parcel.readString();
        this.dateTime = parcel.readLong();
        this.duration = parcel.readLong();
        this.favoriteSupported = parcel.readByte() != 0;
        this.downloadSupported = parcel.readByte() != 0;
        this.vipNeeded = parcel.readByte() != 0;
        this.downloaded = parcel.readByte() != 0;
        this.favorited = parcel.readByte() != 0;
        this.radioFrequency = parcel.readString();
        this.radioBand = parcel.readInt();
        this.radioName = parcel.readString();
        this.artworkPath = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.loopModeSupported = parcel.readByte() != 0;
    }
}
