package ecarx.xsf.mediacenter.vr.channel;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public class IVrChannelInfo implements Parcelable {
    public static final Parcelable.Creator<IVrChannelInfo> CREATOR = new Parcelable.Creator<IVrChannelInfo>() { // from class: ecarx.xsf.mediacenter.vr.channel.IVrChannelInfo.1
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ IVrChannelInfo[] newArray(int i) {
            return new IVrChannelInfo[i];
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ IVrChannelInfo createFromParcel(Parcel parcel) {
            return new IVrChannelInfo(parcel);
        }
    };
    private int channelDataType;
    private String mediaDescription;
    private String mediaPackageName;
    private String mediaVersion;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void setMediaPackageName(String str) {
        this.mediaPackageName = str;
    }

    public void setMediaVersion(String str) {
        this.mediaVersion = str;
    }

    public void setMediaDescription(String str) {
        this.mediaDescription = str;
    }

    public void setChannelDataType(int i) {
        this.channelDataType = i;
    }

    public String getMediaPackageName() {
        return this.mediaPackageName;
    }

    public String getMediaVersion() {
        return this.mediaVersion;
    }

    public String getMediaDescription() {
        return this.mediaDescription;
    }

    public int getChannelDataType() {
        return this.channelDataType;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mediaPackageName);
        parcel.writeString(this.mediaVersion);
        parcel.writeString(this.mediaDescription);
        parcel.writeInt(this.channelDataType);
    }

    public IVrChannelInfo() {
    }

    protected IVrChannelInfo(Parcel parcel) {
        this.mediaPackageName = parcel.readString();
        this.mediaVersion = parcel.readString();
        this.mediaDescription = parcel.readString();
        this.channelDataType = parcel.readInt();
    }

    public String toString() {
        return "IVrChannelInfo{mediaPackageName='" + this.mediaPackageName + "', mediaVersion='" + this.mediaVersion + "', mediaDescription='" + this.mediaDescription + "', channelDataType=" + this.channelDataType + '}';
    }
}
