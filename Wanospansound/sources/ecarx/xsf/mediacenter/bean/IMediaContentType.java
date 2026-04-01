package ecarx.xsf.mediacenter.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public class IMediaContentType implements Parcelable {
    public static final Parcelable.Creator<IMediaContentType> CREATOR = new Parcelable.Creator<IMediaContentType>() { // from class: ecarx.xsf.mediacenter.bean.IMediaContentType.1
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ IMediaContentType[] newArray(int i) {
            return new IMediaContentType[i];
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ IMediaContentType createFromParcel(Parcel parcel) {
            return new IMediaContentType(parcel);
        }
    };
    private String id;
    private String name;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.name);
    }

    public IMediaContentType() {
    }

    protected IMediaContentType(Parcel parcel) {
        this.id = parcel.readString();
        this.name = parcel.readString();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String toString() {
        return "IMediaContentType{id='" + this.id + "', name='" + this.name + "'}";
    }
}
