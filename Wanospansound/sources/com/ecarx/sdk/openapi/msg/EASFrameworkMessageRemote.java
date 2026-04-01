package com.ecarx.sdk.openapi.msg;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class EASFrameworkMessageRemote implements Parcelable {
    public static final Parcelable.Creator<EASFrameworkMessageRemote> CREATOR = new Parcelable.Creator<EASFrameworkMessageRemote>() { // from class: com.ecarx.sdk.openapi.msg.EASFrameworkMessageRemote.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EASFrameworkMessageRemote createFromParcel(Parcel parcel) {
            return new EASFrameworkMessageRemote(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EASFrameworkMessageRemote[] newArray(int i) {
            return new EASFrameworkMessageRemote[i];
        }
    };
    public String mAttachParam;
    public String mMethod;
    public String mMethodParam;
    public String mModuleName;
    public String mServiceName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public EASFrameworkMessageRemote(String str, String str2, String str3, String str4, String str5) {
        this.mServiceName = str;
        this.mModuleName = str2;
        this.mMethod = str3;
        this.mMethodParam = str4;
        this.mAttachParam = str5;
    }

    protected EASFrameworkMessageRemote(Parcel parcel) {
        this.mServiceName = parcel.readString();
        this.mModuleName = parcel.readString();
        this.mMethod = parcel.readString();
        this.mMethodParam = parcel.readString();
        this.mAttachParam = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mServiceName);
        parcel.writeString(this.mModuleName);
        parcel.writeString(this.mMethod);
        parcel.writeString(this.mMethodParam);
        parcel.writeString(this.mAttachParam);
    }
}
