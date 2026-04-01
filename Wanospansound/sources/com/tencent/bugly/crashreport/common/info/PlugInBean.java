package com.tencent.bugly.crashreport.common.info;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public class PlugInBean implements Parcelable {
    public static final Parcelable.Creator<PlugInBean> CREATOR = new Parcelable.Creator<PlugInBean>() { // from class: com.tencent.bugly.crashreport.common.info.PlugInBean.1
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ PlugInBean[] newArray(int i) {
            return new PlugInBean[i];
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ PlugInBean createFromParcel(Parcel parcel) {
            return new PlugInBean(parcel);
        }
    };

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f385a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f386b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f387c;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PlugInBean(String str, String str2, String str3) {
        this.f385a = str;
        this.f386b = str2;
        this.f387c = str3;
    }

    public String toString() {
        return "plid:" + this.f385a + " plV:" + this.f386b + " plUUID:" + this.f387c;
    }

    public PlugInBean(Parcel parcel) {
        this.f385a = parcel.readString();
        this.f386b = parcel.readString();
        this.f387c = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f385a);
        parcel.writeString(this.f386b);
        parcel.writeString(this.f387c);
    }
}
