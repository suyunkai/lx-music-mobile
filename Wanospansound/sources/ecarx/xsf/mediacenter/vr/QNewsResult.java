package ecarx.xsf.mediacenter.vr;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public class QNewsResult implements Parcelable {
    public static final Parcelable.Creator<QNewsResult> CREATOR = new Parcelable.Creator<QNewsResult>() { // from class: ecarx.xsf.mediacenter.vr.QNewsResult.1
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ QNewsResult[] newArray(int i) {
            return new QNewsResult[i];
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ QNewsResult createFromParcel(Parcel parcel) {
            return new QNewsResult(parcel);
        }
    };
    public String newsCategory;
    public String newsId;
    public String rawText;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.newsId);
        parcel.writeString(this.newsCategory);
        parcel.writeString(this.rawText);
    }

    public QNewsResult() {
    }

    protected QNewsResult(Parcel parcel) {
        this.newsId = parcel.readString();
        this.newsCategory = parcel.readString();
        this.rawText = parcel.readString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("QNewsResult{newsId='");
        sb.append(this.newsId).append("', newsCategory='");
        sb.append(this.newsCategory).append("', rawText='");
        sb.append(this.rawText).append("'}");
        return sb.toString();
    }
}
