package ecarx.xsf.mediacenter;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class IMediaList implements Parcelable {
    public static final Parcelable.Creator<IMediaList> CREATOR = new Parcelable.Creator<IMediaList>() { // from class: ecarx.xsf.mediacenter.IMediaList.1
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ IMediaList[] newArray(int i) {
            return new IMediaList[i];
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ IMediaList createFromParcel(Parcel parcel) {
            return new IMediaList(parcel);
        }
    };
    private List<IMedia> mediaList;
    private String mediaListId;
    private int mediaListType;
    private PendingIntent pendingIntent;
    private int sourceType;
    private String title;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IMediaList() {
    }

    public IMediaList(Parcel parcel) {
        this.title = parcel.readString();
        this.sourceType = parcel.readInt();
        this.mediaListId = parcel.readString();
        this.mediaListType = parcel.readInt();
        this.mediaList = parcel.createTypedArrayList(IMedia.CREATOR);
        this.pendingIntent = (PendingIntent) parcel.readParcelable(PendingIntent.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeInt(this.sourceType);
        parcel.writeString(this.mediaListId);
        parcel.writeInt(this.mediaListType);
        parcel.writeTypedList(this.mediaList);
        parcel.writeParcelable(this.pendingIntent, i);
    }

    public String getTitle() {
        return this.title;
    }

    public int getSourceType() {
        return this.sourceType;
    }

    public String getMediaListId() {
        return this.mediaListId;
    }

    public List<IMedia> getMediaList() {
        return this.mediaList;
    }

    public int getMediaListType() {
        return this.mediaListType;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setSourceType(int i) {
        this.sourceType = i;
    }

    public void setMediaListId(String str) {
        this.mediaListId = str;
    }

    public void setMediaList(List<IMedia> list) {
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
