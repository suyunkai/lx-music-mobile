package ecarx.xsf.mediacenter.session;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes3.dex */
public class MediaOperateState implements Parcelable {
    public static final Parcelable.Creator<MediaOperateState> CREATOR = new Parcelable.Creator<MediaOperateState>() { // from class: ecarx.xsf.mediacenter.session.MediaOperateState.1
        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ MediaOperateState[] newArray(int i) {
            return new MediaOperateState[i];
        }

        @Override // android.os.Parcelable.Creator
        public /* synthetic */ MediaOperateState createFromParcel(Parcel parcel) {
            return new MediaOperateState(parcel);
        }
    };
    public static final int OPERATE_DOWNLOAD = 1;
    public static final int OPERATE_FAVORITE = 2;
    public static final int OPERATE_SET_LYRIC = 3;
    public static final int STATE_CANCEL = 2;
    public static final int STATE_FAILURE = -1;
    public static final int STATE_SUCCESS = 1;
    private String mediaId;
    private String message;
    private int operate;
    private String queueId;
    private int state;

    @Retention(RetentionPolicy.SOURCE)
    public @interface MediaOperate {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface StateScope {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "MediaOperateState{queueId='" + this.queueId + "', mediaId='" + this.mediaId + "', operate=" + this.operate + ", state=" + this.state + ", message='" + this.message + "'}";
    }

    private MediaOperateState(Builder builder) {
        this.queueId = builder.queueId;
        this.mediaId = builder.mediaId;
        this.operate = builder.operate;
        this.state = builder.state;
        this.message = builder.message;
    }

    public String getQueueId() {
        return this.queueId;
    }

    public String getMediaId() {
        return this.mediaId;
    }

    public int getOperate() {
        return this.operate;
    }

    public int getState() {
        return this.state;
    }

    public String getMessage() {
        return this.message;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.queueId);
        parcel.writeString(this.mediaId);
        parcel.writeInt(this.operate);
        parcel.writeInt(this.state);
        parcel.writeString(this.message);
    }

    protected MediaOperateState(Parcel parcel) {
        this.queueId = parcel.readString();
        this.mediaId = parcel.readString();
        this.operate = parcel.readInt();
        this.state = parcel.readInt();
        this.message = parcel.readString();
    }

    public static final class Builder {
        private String mediaId;
        private String message;
        private int operate;
        private String queueId;
        private int state;

        public final Builder queueId(String str) {
            this.queueId = str;
            return this;
        }

        public final Builder mediaId(String str) {
            this.mediaId = str;
            return this;
        }

        public final Builder operate(int i) {
            this.operate = i;
            return this;
        }

        public final Builder state(int i) {
            this.state = i;
            return this;
        }

        public final Builder message(String str) {
            this.message = str;
            return this;
        }

        public final MediaOperateState build() {
            return new MediaOperateState(this);
        }
    }
}
