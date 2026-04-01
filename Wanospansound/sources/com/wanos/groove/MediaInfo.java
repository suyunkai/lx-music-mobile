package com.wanos.groove;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public class MediaInfo implements Parcelable {
    public static final Parcelable.Creator<MediaInfo> CREATOR = new Parcelable.Creator<MediaInfo>() { // from class: com.wanos.groove.MediaInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MediaInfo createFromParcel(Parcel parcel) {
            return new MediaInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MediaInfo[] newArray(int i) {
            return new MediaInfo[i];
        }
    };
    long currentDuration;
    long duration;
    int itemIndex;
    String itemType;
    String itemUUID;
    String mediaAuthor;
    String mediaImage;
    String mediaName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MediaInfo() {
    }

    protected MediaInfo(Parcel parcel) {
        this.itemIndex = parcel.readInt();
        this.itemUUID = parcel.readString();
        this.mediaName = parcel.readString();
        this.mediaAuthor = parcel.readString();
        this.mediaImage = parcel.readString();
        this.itemType = parcel.readString();
        this.duration = parcel.readLong();
        this.currentDuration = parcel.readLong();
    }

    public int getItemIndex() {
        return this.itemIndex;
    }

    public void setItemIndex(int i) {
        this.itemIndex = i;
    }

    public String getItemUUID() {
        return this.itemUUID;
    }

    public void setItemUUID(String str) {
        this.itemUUID = str;
    }

    public String getMediaName() {
        return this.mediaName;
    }

    public void setMediaName(String str) {
        this.mediaName = str;
    }

    public String getMediaAuthor() {
        return this.mediaAuthor;
    }

    public void setMediaAuthor(String str) {
        this.mediaAuthor = str;
    }

    public String getMediaImage() {
        return this.mediaImage;
    }

    public void setMediaImage(String str) {
        this.mediaImage = str;
    }

    public String getItemType() {
        return this.itemType;
    }

    public void setItemType(String str) {
        this.itemType = str;
    }

    public long getDuration() {
        return this.duration;
    }

    public void setDuration(long j) {
        this.duration = j;
    }

    public long getCurrentDuration() {
        return this.currentDuration;
    }

    public void setCurrentDuration(long j) {
        this.currentDuration = j;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.itemIndex);
        parcel.writeString(this.itemUUID);
        parcel.writeString(this.mediaName);
        parcel.writeString(this.mediaAuthor);
        parcel.writeString(this.mediaImage);
        parcel.writeString(this.itemType);
        parcel.writeLong(this.duration);
        parcel.writeLong(this.currentDuration);
    }
}
