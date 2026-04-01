package ecarx.xsf.mediacenter;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public class IMedia implements Parcelable {
    public static final Parcelable.Creator<IMedia> CREATOR = new Parcelable.Creator<IMedia>() { // from class: ecarx.xsf.mediacenter.IMedia.1
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ IMedia[] newArray(int i) {
            return new IMedia[i];
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ IMedia createFromParcel(Parcel parcel) {
            return new IMedia(parcel);
        }
    };
    private String album;
    private int albumIndex;
    private Uri artWork;
    private String artist;
    private String author;
    private String categoryStr;
    private int collected;
    private String composer;
    private String description;
    private long duration;
    private Uri lyric;
    private String lyricContent;
    private String mediaCp;
    private String mediaId;
    private Uri mediaPath;
    private String playingMediaListId;
    private int playingMediaListType;
    private int positionInQueue;
    private String radioFrequency;
    private String radioStationName;
    private String rating;
    private int sourceType;
    private String subCategoryStr;
    private String subtitle;
    private int supportCollect;
    private String targetType;
    private String title;
    private String uuid;
    private int vip;
    private String year;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IMedia() {
    }

    public IMedia(Parcel parcel) {
        this.uuid = parcel.readString();
        this.title = parcel.readString();
        this.artist = parcel.readString();
        this.album = parcel.readString();
        this.author = parcel.readString();
        this.composer = parcel.readString();
        this.description = parcel.readString();
        this.subtitle = parcel.readString();
        this.rating = parcel.readString();
        this.year = parcel.readString();
        this.duration = parcel.readLong();
        this.positionInQueue = parcel.readInt();
        this.albumIndex = parcel.readInt();
        this.categoryStr = parcel.readString();
        this.subCategoryStr = parcel.readString();
        this.mediaId = parcel.readString();
        this.mediaCp = parcel.readString();
        this.targetType = parcel.readString();
        this.sourceType = parcel.readInt();
        this.mediaPath = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.lyricContent = parcel.readString();
        this.lyric = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.artWork = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.radioFrequency = parcel.readString();
        this.radioStationName = parcel.readString();
        this.vip = parcel.readInt();
        this.playingMediaListId = parcel.readString();
        this.playingMediaListType = parcel.readInt();
        this.supportCollect = parcel.readInt();
        this.collected = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.uuid);
        parcel.writeString(this.title);
        parcel.writeString(this.artist);
        parcel.writeString(this.album);
        parcel.writeString(this.author);
        parcel.writeString(this.composer);
        parcel.writeString(this.description);
        parcel.writeString(this.subtitle);
        parcel.writeString(this.rating);
        parcel.writeString(this.year);
        parcel.writeLong(this.duration);
        parcel.writeInt(this.positionInQueue);
        parcel.writeInt(this.albumIndex);
        parcel.writeString(this.categoryStr);
        parcel.writeString(this.subCategoryStr);
        parcel.writeString(this.mediaId);
        parcel.writeString(this.mediaCp);
        parcel.writeString(this.targetType);
        parcel.writeInt(this.sourceType);
        parcel.writeParcelable(this.mediaPath, i);
        parcel.writeString(this.lyricContent);
        parcel.writeParcelable(this.lyric, i);
        parcel.writeParcelable(this.artWork, i);
        parcel.writeString(this.radioFrequency);
        parcel.writeString(this.radioStationName);
        parcel.writeInt(this.vip);
        parcel.writeString(this.playingMediaListId);
        parcel.writeInt(this.playingMediaListType);
        parcel.writeInt(this.supportCollect);
        parcel.writeInt(this.collected);
    }

    public String getUuid() {
        return this.uuid;
    }

    public String getTitle() {
        return this.title;
    }

    public String getArtist() {
        return this.artist;
    }

    public String getAlbum() {
        return this.album;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getComposer() {
        return this.composer;
    }

    public String getDescription() {
        return this.description;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public String getRating() {
        return this.rating;
    }

    public String getYear() {
        return this.year;
    }

    public long getDuration() {
        return this.duration;
    }

    public int getPlayingItemPositionInQueue() {
        return this.positionInQueue;
    }

    public int getAlbumIndex() {
        return this.albumIndex;
    }

    public String getCategoryStr() {
        return this.categoryStr;
    }

    public String getSubCategoryStr() {
        return this.subCategoryStr;
    }

    public String getMediaId() {
        return this.mediaId;
    }

    public String getMediaCp() {
        return this.mediaCp;
    }

    public String getTargetType() {
        return this.targetType;
    }

    public int getSourceType() {
        return this.sourceType;
    }

    public Uri getMediaPath() {
        return this.mediaPath;
    }

    public String getLyricContent() {
        return this.lyricContent;
    }

    public Uri getLyric() {
        return this.lyric;
    }

    public Uri getArtwork() {
        return this.artWork;
    }

    public String getRadioFrequency() {
        return this.radioFrequency;
    }

    public String getRadioStationName() {
        return this.radioStationName;
    }

    public void setUuid(String str) {
        this.uuid = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setArtist(String str) {
        this.artist = str;
    }

    public void setAlbum(String str) {
        this.album = str;
    }

    public void setDuration(long j) {
        this.duration = j;
    }

    public void setPositionInQueue(int i) {
        this.positionInQueue = i;
    }

    public void setSourceType(int i) {
        this.sourceType = i;
    }

    public void setMediaPath(Uri uri) {
        this.mediaPath = uri;
    }

    public void setLyricContent(String str) {
        this.lyricContent = str;
    }

    public void setLyric(Uri uri) {
        this.lyric = uri;
    }

    public void setArtWork(Uri uri) {
        this.artWork = uri;
    }

    public void setRadioFrequency(String str) {
        this.radioFrequency = str;
    }

    public void setRadioStationName(String str) {
        this.radioStationName = str;
    }

    public void setAuthor(String str) {
        this.author = str;
    }

    public void setComposer(String str) {
        this.composer = str;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setSubtitle(String str) {
        this.subtitle = str;
    }

    public void setRating(String str) {
        this.rating = str;
    }

    public void setYear(String str) {
        this.year = str;
    }

    public int getPositionInQueue() {
        return this.positionInQueue;
    }

    public void setAlbumIndex(int i) {
        this.albumIndex = i;
    }

    public void setCategoryStr(String str) {
        this.categoryStr = str;
    }

    public void setSubCategoryStr(String str) {
        this.subCategoryStr = str;
    }

    public void setMediaId(String str) {
        this.mediaId = str;
    }

    public void setMediaCp(String str) {
        this.mediaCp = str;
    }

    public void setTargetType(String str) {
        this.targetType = str;
    }

    public Uri getArtWork() {
        return this.artWork;
    }

    public int getPlayingMediaListType() {
        return this.playingMediaListType;
    }

    public void setPlayingMediaListType(int i) {
        this.playingMediaListType = i;
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

    public int getSupportCollect() {
        return this.supportCollect;
    }

    public void setSupportCollect(int i) {
        this.supportCollect = i;
    }

    public int getCollected() {
        return this.collected;
    }

    public void setCollected(int i) {
        this.collected = i;
    }
}
