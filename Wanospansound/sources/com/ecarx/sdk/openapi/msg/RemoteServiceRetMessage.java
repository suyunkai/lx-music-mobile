package com.ecarx.sdk.openapi.msg;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class RemoteServiceRetMessage implements Parcelable {
    public static final Parcelable.Creator<RemoteServiceRetMessage> CREATOR = new Parcelable.Creator<RemoteServiceRetMessage>() { // from class: com.ecarx.sdk.openapi.msg.RemoteServiceRetMessage.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RemoteServiceRetMessage createFromParcel(Parcel parcel) {
            return new RemoteServiceRetMessage(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RemoteServiceRetMessage[] newArray(int i) {
            return new RemoteServiceRetMessage[i];
        }
    };
    public int mCode;
    public String mMsg;
    public String mResult;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RemoteServiceRetMessage(int i, String str) {
        this.mCode = i;
        this.mMsg = str;
    }

    public RemoteServiceRetMessage(int i, String str, String str2) {
        this.mCode = i;
        this.mMsg = str;
        this.mResult = str2;
    }

    protected RemoteServiceRetMessage(Parcel parcel) {
        this.mCode = parcel.readInt();
        this.mMsg = parcel.readString();
        this.mResult = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mCode);
        parcel.writeString(this.mMsg);
        parcel.writeString(this.mResult);
    }
}
