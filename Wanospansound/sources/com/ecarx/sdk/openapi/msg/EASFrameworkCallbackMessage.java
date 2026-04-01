package com.ecarx.sdk.openapi.msg;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class EASFrameworkCallbackMessage implements Parcelable {
    public static final Parcelable.Creator<EASFrameworkCallbackMessage> CREATOR = new Parcelable.Creator<EASFrameworkCallbackMessage>() { // from class: com.ecarx.sdk.openapi.msg.EASFrameworkCallbackMessage.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EASFrameworkCallbackMessage createFromParcel(Parcel parcel) {
            return new EASFrameworkCallbackMessage(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EASFrameworkCallbackMessage[] newArray(int i) {
            return new EASFrameworkCallbackMessage[i];
        }
    };
    public byte[] mAttachParam;
    public String mMethod;
    public byte[] mMethodParam;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public EASFrameworkCallbackMessage(String str, byte[] bArr, byte[] bArr2) {
        this.mMethod = str;
        this.mMethodParam = bArr;
        this.mAttachParam = bArr2;
    }

    protected EASFrameworkCallbackMessage(Parcel parcel) {
        this.mMethod = parcel.readString();
        this.mMethodParam = parcel.createByteArray();
        this.mAttachParam = parcel.createByteArray();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mMethod);
        parcel.writeByteArray(this.mMethodParam);
        parcel.writeByteArray(this.mAttachParam);
    }
}
