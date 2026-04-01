package com.ecarx.openapi.sdk.carproperty.impl;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
class WrapperRequest implements Parcelable {
    public static final Parcelable.Creator<WrapperRequest> CREATOR = new Parcelable.Creator<WrapperRequest>() { // from class: com.ecarx.openapi.sdk.carproperty.impl.WrapperRequest.1
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ WrapperRequest[] newArray(int i) {
            return new WrapperRequest[i];
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ WrapperRequest createFromParcel(Parcel parcel) {
            return new WrapperRequest(parcel);
        }
    };

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f357a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f358b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f359c;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected WrapperRequest(int i, int i2, int i3) {
        this.f357a = i;
        this.f358b = i2;
        this.f359c = i3;
    }

    protected WrapperRequest(Parcel parcel) {
        this.f357a = parcel.readInt();
        this.f358b = parcel.readInt();
        this.f359c = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f357a);
        parcel.writeInt(this.f358b);
        parcel.writeInt(this.f359c);
    }
}
