package ecarx.xsf.mediacenter.vr;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public class QRadioResult implements Parcelable {
    public static final int COLLECTION_PLAY_TYPE = 1;
    public static final Parcelable.Creator<QRadioResult> CREATOR = new Parcelable.Creator<QRadioResult>() { // from class: ecarx.xsf.mediacenter.vr.QRadioResult.1
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ QRadioResult[] newArray(int i) {
            return new QRadioResult[i];
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ QRadioResult createFromParcel(Parcel parcel) {
            QRadioResult qRadioResult = new QRadioResult();
            qRadioResult.rawText = parcel.readString();
            qRadioResult.name = parcel.readString();
            qRadioResult.freq = parcel.readString();
            qRadioResult.category = parcel.readString();
            qRadioResult.location = parcel.readString();
            qRadioResult.mediaType = parcel.readInt();
            qRadioResult.sourceType = parcel.readInt();
            qRadioResult.targetPlayType = parcel.readInt();
            return qRadioResult;
        }
    };
    public static final int DEFAULT_PLAY_TYPE = -1;
    public static final int RECOMMEND_PLAY_TYPE = 2;
    public static final int SOURCE_TYPE_AM = 4;
    public static final int SOURCE_TYPE_FM = 3;
    public static final int SOURCE_TYPE_UNKNOWN = -2;
    public String rawText = "";
    public String name = "";
    public String freq = "";
    public String category = "";
    public String location = "";
    public int mediaType = -1;
    public int sourceType = -2;
    public int targetPlayType = -1;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.rawText);
        parcel.writeString(this.name);
        parcel.writeString(this.freq);
        parcel.writeString(this.category);
        parcel.writeString(this.location);
        parcel.writeInt(this.mediaType);
        parcel.writeInt(this.sourceType);
        parcel.writeInt(this.targetPlayType);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("========= QRadioResult ===== \n");
        stringBuffer.append("rawText: " + this.rawText + "\n");
        stringBuffer.append("name: " + this.name + "\n");
        stringBuffer.append("freq: " + this.freq + "\n");
        stringBuffer.append("category: " + this.category + "\n");
        stringBuffer.append("location: " + this.location + "\n");
        stringBuffer.append("mediaType: " + this.mediaType + "\n");
        stringBuffer.append("sourceType: " + this.sourceType + "\n");
        stringBuffer.append("targetPlayType: " + this.targetPlayType + "\n");
        return stringBuffer.toString();
    }
}
