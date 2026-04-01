package com.ecarx.eas.sdk.vehicle.v3;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class IMileageInfo implements Parcelable, com.ecarx.eas.sdk.vehicle.api.dashboard.IMileageInfo {
    public static final Parcelable.Creator<IMileageInfo> CREATOR = new Parcelable.Creator<IMileageInfo>() { // from class: com.ecarx.eas.sdk.vehicle.v3.IMileageInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final IMileageInfo createFromParcel(Parcel parcel) {
            return new IMileageInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final IMileageInfo[] newArray(int i) {
            return new IMileageInfo[i];
        }
    };

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private long f319a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f320b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private long f321c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private long f322d;
    private long e;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IMileageInfo(long j, long j2, long j3, long j4, long j5) {
        this.f319a = j;
        this.f320b = j2;
        this.f321c = j3;
        this.f322d = j4;
        this.e = j5;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IMileageInfo
    public long getTotalMileage() {
        return this.f319a;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IMileageInfo
    public long getTripMileage() {
        return this.f320b;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IMileageInfo
    public long getTripDuration() {
        return this.f321c;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IMileageInfo
    public long getEnduranceMileage() {
        return this.f322d;
    }

    @Override // com.ecarx.eas.sdk.vehicle.api.dashboard.IMileageInfo
    public long getNextMaintenanceMileage() {
        return this.e;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.f319a);
        parcel.writeLong(this.f320b);
        parcel.writeLong(this.f321c);
        parcel.writeLong(this.f322d);
        parcel.writeLong(this.e);
    }

    public IMileageInfo() {
    }

    protected IMileageInfo(Parcel parcel) {
        this.f319a = parcel.readLong();
        this.f320b = parcel.readLong();
        this.f321c = parcel.readLong();
        this.f322d = parcel.readLong();
        this.e = parcel.readLong();
    }
}
