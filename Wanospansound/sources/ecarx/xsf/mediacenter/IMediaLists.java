package ecarx.xsf.mediacenter;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class IMediaLists implements Parcelable {
    public static final Parcelable.Creator<IMediaLists> CREATOR = new Parcelable.Creator<IMediaLists>() { // from class: ecarx.xsf.mediacenter.IMediaLists.1
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ IMediaLists[] newArray(int i) {
            return new IMediaLists[i];
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ IMediaLists createFromParcel(Parcel parcel) {
            return new IMediaLists(parcel);
        }
    };
    private List<IMediaList> mediaLists;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IMediaLists() {
    }

    protected IMediaLists(Parcel parcel) {
        this.mediaLists = parcel.createTypedArrayList(IMediaList.CREATOR);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.mediaLists);
    }

    public void setMediaLists(List<IMediaList> list) {
        this.mediaLists = list;
    }

    public List<IMediaList> getMediaLists() {
        return this.mediaLists;
    }
}
