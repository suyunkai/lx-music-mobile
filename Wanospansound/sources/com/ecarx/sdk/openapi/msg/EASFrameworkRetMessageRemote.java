package com.ecarx.sdk.openapi.msg;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class EASFrameworkRetMessageRemote implements Parcelable {
    public static final Parcelable.Creator<EASFrameworkRetMessageRemote> CREATOR = new Parcelable.Creator<EASFrameworkRetMessageRemote>() { // from class: com.ecarx.sdk.openapi.msg.EASFrameworkRetMessageRemote.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EASFrameworkRetMessageRemote createFromParcel(Parcel parcel) {
            return new EASFrameworkRetMessageRemote(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EASFrameworkRetMessageRemote[] newArray(int i) {
            return new EASFrameworkRetMessageRemote[i];
        }
    };
    public int mCode;
    public String mMsg;
    public byte[] mResult;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public EASFrameworkRetMessageRemote(int i, String str) {
        this.mCode = i;
        this.mMsg = str;
    }

    public EASFrameworkRetMessageRemote(int i, String str, byte[] bArr) {
        this.mCode = i;
        this.mMsg = str;
        this.mResult = bArr;
    }

    protected EASFrameworkRetMessageRemote(Parcel parcel) {
        this.mCode = parcel.readInt();
        this.mMsg = parcel.readString();
        this.mResult = parcel.createByteArray();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mCode);
        parcel.writeString(this.mMsg);
        parcel.writeByteArray(this.mResult);
    }
}
