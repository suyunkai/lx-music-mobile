package com.wanos.careditproject.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public class AiOutEntity implements Parcelable {
    public static final Parcelable.Creator<AiOutEntity> CREATOR = new Parcelable.Creator<AiOutEntity>() { // from class: com.wanos.careditproject.data.bean.AiOutEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AiOutEntity createFromParcel(Parcel parcel) {
            return new AiOutEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AiOutEntity[] newArray(int i) {
            return new AiOutEntity[i];
        }
    };
    private final String md5;
    private final String midiUrl;
    private final String name;
    private final String url;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AiOutEntity(String str, String str2, String str3, String str4) {
        this.name = str;
        this.url = str2;
        this.md5 = str3;
        this.midiUrl = str4;
    }

    public String getName() {
        return this.name;
    }

    public String getUrl() {
        return this.url;
    }

    public String getMd5() {
        return this.md5;
    }

    public String getMidiUrl() {
        return this.midiUrl;
    }

    public String toString() {
        return "AiOutEntity{name='" + this.name + "', url='" + this.url + "', md5='" + this.md5 + "', midiUrl='" + this.midiUrl + "'}";
    }

    protected AiOutEntity(Parcel parcel) {
        this.name = parcel.readString();
        this.url = parcel.readString();
        this.md5 = parcel.readString();
        this.midiUrl = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.url);
        parcel.writeString(this.md5);
        parcel.writeString(this.midiUrl);
    }
}
