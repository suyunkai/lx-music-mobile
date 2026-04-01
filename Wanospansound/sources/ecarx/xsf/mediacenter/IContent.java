package ecarx.xsf.mediacenter;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public class IContent implements Parcelable {
    public static final Parcelable.Creator<IContent> CREATOR = new Parcelable.Creator<IContent>() { // from class: ecarx.xsf.mediacenter.IContent.1
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ IContent[] newArray(int i) {
            return new IContent[i];
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ IContent createFromParcel(Parcel parcel) {
            return new IContent(parcel);
        }
    };
    private Uri background;
    private String id;
    private PendingIntent pendingIntent;
    private String title;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IContent() {
    }

    public IContent(Parcel parcel) {
        this.id = parcel.readString();
        this.title = parcel.readString();
        this.pendingIntent = (PendingIntent) parcel.readParcelable(PendingIntent.class.getClassLoader());
        this.background = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public PendingIntent getPendingIntent() {
        return this.pendingIntent;
    }

    public Uri getBackground() {
        return this.background;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setPendingIntent(PendingIntent pendingIntent) {
        this.pendingIntent = pendingIntent;
    }

    public void setBackground(Uri uri) {
        this.background = uri;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.title);
        parcel.writeParcelable(this.pendingIntent, i);
        parcel.writeParcelable(this.background, i);
    }

    public String toString() {
        return "IContent: id = " + this.id + " title = " + this.title + " pendingIntent = " + this.pendingIntent + " , background = " + this.background;
    }
}
