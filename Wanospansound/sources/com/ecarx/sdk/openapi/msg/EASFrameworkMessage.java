package com.ecarx.sdk.openapi.msg;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class EASFrameworkMessage implements Parcelable {
    public static final Parcelable.Creator<EASFrameworkMessage> CREATOR = new Parcelable.Creator<EASFrameworkMessage>() { // from class: com.ecarx.sdk.openapi.msg.EASFrameworkMessage.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EASFrameworkMessage createFromParcel(Parcel parcel) {
            return new EASFrameworkMessage(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EASFrameworkMessage[] newArray(int i) {
            return new EASFrameworkMessage[i];
        }
    };
    public byte[] mAttachParam;
    public String mMethod;
    public byte[] mMethodParam;
    public String mMoudleName;
    public String mServiceName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public EASFrameworkMessage(String str, String str2, String str3, byte[] bArr, byte[] bArr2) {
        this.mServiceName = str;
        this.mMoudleName = str2;
        this.mMethod = str3;
        this.mMethodParam = bArr;
        this.mAttachParam = bArr2;
    }

    protected EASFrameworkMessage(Parcel parcel) {
        this.mServiceName = parcel.readString();
        this.mMoudleName = parcel.readString();
        this.mMethod = parcel.readString();
        this.mMethodParam = parcel.createByteArray();
        this.mAttachParam = parcel.createByteArray();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mServiceName);
        parcel.writeString(this.mMoudleName);
        parcel.writeString(this.mMethod);
        parcel.writeByteArray(this.mMethodParam);
        parcel.writeByteArray(this.mAttachParam);
    }
}
