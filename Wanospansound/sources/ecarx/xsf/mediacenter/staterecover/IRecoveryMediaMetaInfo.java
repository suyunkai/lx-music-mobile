package ecarx.xsf.mediacenter.staterecover;

import android.os.Parcel;
import android.os.Parcelable;
import ecarx.xsf.mediacenter.IMedia;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class IRecoveryMediaMetaInfo implements Parcelable {
    public static final Parcelable.Creator<IRecoveryMediaMetaInfo> CREATOR = new Parcelable.Creator<IRecoveryMediaMetaInfo>() { // from class: ecarx.xsf.mediacenter.staterecover.IRecoveryMediaMetaInfo.1
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ IRecoveryMediaMetaInfo[] newArray(int i) {
            return new IRecoveryMediaMetaInfo[i];
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ IRecoveryMediaMetaInfo createFromParcel(Parcel parcel) {
            return new IRecoveryMediaMetaInfo(parcel);
        }
    };
    private List<IMedia> mediaList;
    private String mediaListId;
    private int mediaListType;
    private String packageName;
    private int sourceType;
    private String title;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IRecoveryMediaMetaInfo() {
    }

    public IRecoveryMediaMetaInfo(Parcel parcel) {
        this.title = parcel.readString();
        this.packageName = parcel.readString();
        this.sourceType = parcel.readInt();
        this.mediaListType = parcel.readInt();
        this.mediaListId = parcel.readString();
        this.mediaList = parcel.readArrayList(IMedia.class.getClassLoader());
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public int getSourceType() {
        return this.sourceType;
    }

    public void setSourceType(int i) {
        this.sourceType = i;
    }

    public int getMediaListType() {
        return this.mediaListType;
    }

    public void setMediaListType(int i) {
        this.mediaListType = i;
    }

    public List<IMedia> getMediaList() {
        return this.mediaList;
    }

    public void setMediaList(List<IMedia> list) {
        this.mediaList = list;
    }

    public String getMediaListId() {
        return this.mediaListId;
    }

    public void setMediaListId(String str) {
        this.mediaListId = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeString(this.packageName);
        parcel.writeInt(this.sourceType);
        parcel.writeInt(this.mediaListType);
        parcel.writeString(this.mediaListId);
        parcel.writeList(this.mediaList);
    }
}
