package com.ecarx.eas.sdk.vehicle.v3;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class IWarningInfo implements Parcelable, com.ecarx.eas.sdk.vehicle.api.dashboard.IWarningInfo {
    public static final Parcelable.Creator<IWarningInfo> CREATOR = new Parcelable.Creator<IWarningInfo>() { // from class: com.ecarx.eas.sdk.vehicle.v3.IWarningInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final IWarningInfo createFromParcel(Parcel parcel) {
            return new IWarningInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final IWarningInfo[] newArray(int i) {
            return new IWarningInfo[i];
        }
    };

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f323a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f324b;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IWarningInfo(int i, int i2) {
        this.f323a = i;
        this.f324b = i2;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IWarningInfo
    public int getWarningId() {
        return this.f323a;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IWarningInfo
    public int getWarningPriority() {
        return this.f324b;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f323a);
        parcel.writeInt(this.f324b);
    }

    protected IWarningInfo(Parcel parcel) {
        this.f323a = parcel.readInt();
        this.f324b = parcel.readInt();
    }
}
