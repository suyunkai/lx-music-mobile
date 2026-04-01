package ecarx.xsf.mediacenter.vr;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public class QMusicResult implements Parcelable {
    public static final int COLLECTION_PLAY_TYPE = 1;
    public static final Parcelable.Creator<QMusicResult> CREATOR = new Parcelable.Creator<QMusicResult>() { // from class: ecarx.xsf.mediacenter.vr.QMusicResult.1
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ QMusicResult[] newArray(int i) {
            return new QMusicResult[i];
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ QMusicResult createFromParcel(Parcel parcel) {
            return new QMusicResult(parcel);
        }
    };
    public static final int DEFAULT_PLAY_TYPE = -1;
    public static final int OPERATION_CLOSE = 2;
    public static final int OPERATION_PLAY = 1;
    public static final int OPERATION_SEARCH = 0;
    public static final int QUERY_TYPE_NAME = 0;
    public static final int QUERY_TYPE_SINGER = 1;
    public static final int QUERY_TYPE_UNKNOWN = -1;
    public static final int RECOMMEND_PLAY_TYPE = 2;
    public static final int SOURCE_TYPE_BT = 2;
    public static final int SOURCE_TYPE_LOCAL = 0;
    public static final int SOURCE_TYPE_ONLINE = 6;
    public static final int SOURCE_TYPE_STATION = 8;
    public static final int SOURCE_TYPE_UNKNOWN = -2;
    public static final int SOURCE_TYPE_USB = 1;
    public static final int SOURCE_TYPE_USB2 = 7;
    public static final int TYPE_QUALITY_HIGHER = 2;
    public static final int TYPE_QUALITY_HIGHEST = 3;
    public static final int TYPE_QUALITY_NON_DESTRUCTIVE = 4;
    public static final int TYPE_QUALITY_STANDARD = 1;
    public String album;
    public int albumIndex;
    public String artist;
    public String author;
    public String categoryStr;
    public String composer;
    public String description;
    public String items;
    public String mediaCp;
    public String mediaId;
    public int mediaType;
    public int operation;
    public int qualityType;
    public String rating;
    public String rawText;
    public String song;
    public int sourceType;
    public String subCategoryStr;
    public String subtitle;
    public int targetPlayType;
    public String targetType;
    public String weakmatch;
    public String year;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("QMusicResult{operation=");
        sb.append(this.operation);
        sb.append(", rawText='").append(this.rawText).append("', song='");
        sb.append(this.song).append("', weakmatch='");
        sb.append(this.weakmatch).append("', sourceType=");
        sb.append(this.sourceType);
        sb.append(", artist='").append(this.artist).append("', album='");
        sb.append(this.album).append("', author='");
        sb.append(this.author).append("', composer='");
        sb.append(this.composer).append("', description='");
        sb.append(this.description).append("', subtitle='");
        sb.append(this.subtitle).append("', rating='");
        sb.append(this.rating).append("', year='");
        sb.append(this.year).append("', albumIndex=");
        sb.append(this.albumIndex);
        sb.append(", categoryStr='").append(this.categoryStr).append("', subCategoryStr='");
        sb.append(this.subCategoryStr).append("', mediaType=");
        sb.append(this.mediaType);
        sb.append(", mediaId='").append(this.mediaId).append("', mediaCp='");
        sb.append(this.mediaCp).append("', targetType='");
        sb.append(this.targetType).append("', qualityType=");
        sb.append(this.qualityType);
        sb.append(", targetPlayType=").append(this.targetPlayType);
        sb.append(", items='").append(this.items).append("'}");
        return sb.toString();
    }

    public QMusicResult() {
        this.operation = 1;
        this.rawText = "";
        this.song = "";
        this.weakmatch = "";
        this.sourceType = -2;
        this.artist = "";
        this.album = "";
        this.albumIndex = -1;
        this.categoryStr = "";
        this.subCategoryStr = "";
        this.mediaType = -1;
        this.mediaId = "";
        this.mediaCp = "";
        this.targetType = "";
        this.qualityType = 1;
        this.targetPlayType = -1;
        this.items = "";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.operation);
        parcel.writeString(this.rawText);
        parcel.writeString(this.song);
        parcel.writeString(this.weakmatch);
        parcel.writeInt(this.sourceType);
        parcel.writeString(this.artist);
        parcel.writeString(this.album);
        parcel.writeString(this.author);
        parcel.writeString(this.composer);
        parcel.writeString(this.description);
        parcel.writeString(this.subtitle);
        parcel.writeString(this.rating);
        parcel.writeString(this.year);
        parcel.writeInt(this.albumIndex);
        parcel.writeString(this.categoryStr);
        parcel.writeString(this.subCategoryStr);
        parcel.writeInt(this.mediaType);
        parcel.writeString(this.mediaId);
        parcel.writeString(this.mediaCp);
        parcel.writeString(this.targetType);
        parcel.writeInt(this.qualityType);
        parcel.writeInt(this.targetPlayType);
        parcel.writeString(this.items);
    }

    protected QMusicResult(Parcel parcel) {
        this.operation = 1;
        this.rawText = "";
        this.song = "";
        this.weakmatch = "";
        this.sourceType = -2;
        this.artist = "";
        this.album = "";
        this.albumIndex = -1;
        this.categoryStr = "";
        this.subCategoryStr = "";
        this.mediaType = -1;
        this.mediaId = "";
        this.mediaCp = "";
        this.targetType = "";
        this.qualityType = 1;
        this.targetPlayType = -1;
        this.items = "";
        this.operation = parcel.readInt();
        this.rawText = parcel.readString();
        this.song = parcel.readString();
        this.weakmatch = parcel.readString();
        this.sourceType = parcel.readInt();
        this.artist = parcel.readString();
        this.album = parcel.readString();
        this.author = parcel.readString();
        this.composer = parcel.readString();
        this.description = parcel.readString();
        this.subtitle = parcel.readString();
        this.rating = parcel.readString();
        this.year = parcel.readString();
        this.albumIndex = parcel.readInt();
        this.categoryStr = parcel.readString();
        this.subCategoryStr = parcel.readString();
        this.mediaType = parcel.readInt();
        this.mediaId = parcel.readString();
        this.mediaCp = parcel.readString();
        this.targetType = parcel.readString();
        this.qualityType = parcel.readInt();
        this.targetPlayType = parcel.readInt();
        this.items = parcel.readString();
    }
}
