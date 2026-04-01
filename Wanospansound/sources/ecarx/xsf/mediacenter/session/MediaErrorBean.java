package ecarx.xsf.mediacenter.session;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public class MediaErrorBean implements Parcelable {
    public static final Parcelable.Creator<MediaErrorBean> CREATOR = new Parcelable.Creator<MediaErrorBean>() { // from class: ecarx.xsf.mediacenter.session.MediaErrorBean.1
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ MediaErrorBean[] newArray(int i) {
            return new MediaErrorBean[i];
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ MediaErrorBean createFromParcel(Parcel parcel) {
            return new MediaErrorBean(parcel);
        }
    };
    private String errorMsg;
    private int errorState;
    private String packageName;
    private PendingIntent pendingIntent;
    private int sourceType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MediaErrorBean() {
    }

    protected MediaErrorBean(Parcel parcel) {
        this.packageName = parcel.readString();
        this.sourceType = parcel.readInt();
        this.errorState = parcel.readInt();
        this.errorMsg = parcel.readString();
        this.pendingIntent = (PendingIntent) parcel.readParcelable(PendingIntent.class.getClassLoader());
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public int getSourceType() {
        return this.sourceType;
    }

    public void setSourceType(int i) {
        this.sourceType = i;
    }

    public int getErrorState() {
        return this.errorState;
    }

    public void setErrorState(int i) {
        this.errorState = i;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setErrorMsg(String str) {
        this.errorMsg = str;
    }

    public PendingIntent getPendingIntent() {
        return this.pendingIntent;
    }

    public void setPendingIntent(PendingIntent pendingIntent) {
        this.pendingIntent = pendingIntent;
    }

    public String toString() {
        return "MediaErrorBean{errorState='" + this.errorState + "'sourceType='" + this.sourceType + "'errorMsg='" + this.errorMsg + "', pendingIntent='" + this.pendingIntent + "'}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.packageName);
        parcel.writeInt(this.sourceType);
        parcel.writeInt(this.errorState);
        parcel.writeString(this.errorMsg);
        parcel.writeParcelable(this.pendingIntent, i);
    }
}
