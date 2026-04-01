package com.ecarx.sdk.openapi.msg;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class EASFrameworkRemoteCallbackMessage implements Parcelable {
    public static final Parcelable.Creator<EASFrameworkRemoteCallbackMessage> CREATOR = new Parcelable.Creator<EASFrameworkRemoteCallbackMessage>() { // from class: com.ecarx.sdk.openapi.msg.EASFrameworkRemoteCallbackMessage.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EASFrameworkRemoteCallbackMessage createFromParcel(Parcel parcel) {
            return new EASFrameworkRemoteCallbackMessage(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EASFrameworkRemoteCallbackMessage[] newArray(int i) {
            return new EASFrameworkRemoteCallbackMessage[i];
        }
    };
    public int mCode;
    public String mMethod;
    public String mMsg;
    public String mResult;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public EASFrameworkRemoteCallbackMessage(int i, String str) {
        this.mCode = i;
        this.mMsg = str;
    }

    public EASFrameworkRemoteCallbackMessage(int i, String str, String str2, String str3) {
        this.mCode = i;
        this.mMsg = str;
        this.mMethod = str2;
        this.mResult = str3;
    }

    protected EASFrameworkRemoteCallbackMessage(Parcel parcel) {
        this.mCode = parcel.readInt();
        this.mMsg = parcel.readString();
        this.mMethod = parcel.readString();
        this.mResult = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mCode);
        parcel.writeString(this.mMsg);
        parcel.writeString(this.mMethod);
        parcel.writeString(this.mResult);
    }
}
