package com.ecarx.eas.xsf.mediacenter;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class IExContent implements Parcelable {
    public static final Parcelable.Creator<IExContent> CREATOR = new Parcelable.Creator<IExContent>() { // from class: com.ecarx.eas.xsf.mediacenter.IExContent.1
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ IExContent[] newArray(int i) {
            return new IExContent[i];
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ IExContent createFromParcel(Parcel parcel) {
            return new IExContent(parcel);
        }
    };
    private String data;
    private List<PendingIntent> pendingIntents;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IExContent() {
    }

    public String getData() {
        return this.data;
    }

    public void setData(String str) {
        this.data = str;
    }

    public List<PendingIntent> getPendingIntents() {
        return this.pendingIntents;
    }

    public void setPendingIntents(List<PendingIntent> list) {
        this.pendingIntents = list;
    }

    protected IExContent(Parcel parcel) {
        this.data = parcel.readString();
        this.pendingIntents = parcel.createTypedArrayList(PendingIntent.CREATOR);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.data);
        parcel.writeTypedList(this.pendingIntents);
    }
}
