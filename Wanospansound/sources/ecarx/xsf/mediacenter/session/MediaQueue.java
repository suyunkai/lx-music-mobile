package ecarx.xsf.mediacenter.session;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class MediaQueue implements Parcelable {
    public static final Parcelable.Creator<MediaQueue> CREATOR = new Parcelable.Creator<MediaQueue>() { // from class: ecarx.xsf.mediacenter.session.MediaQueue.1
        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ MediaQueue[] newArray(int i) {
            return new MediaQueue[i];
        }

        @Override // android.os.Parcelable.Creator
        public /* synthetic */ MediaQueue createFromParcel(Parcel parcel) {
            return new MediaQueue(parcel);
        }
    };
    public static final int QUEUE_TYPE_CONTENT = 16;
    public static final int QUEUE_TYPE_DOWNLOAD = 22;
    public static final int QUEUE_TYPE_FAVORITE = 21;
    public static final int QUEUE_TYPE_PLAYLIST = 17;
    public static final int QUEUE_TYPE_RECOMMEND = 18;
    public static final int QUEUE_TYPE_SCENARIO = 19;
    public static final int QUEUE_TYPE_UNSPECIFIED = -1;
    public static final int QUEUE_TYPE_VIP = 20;
    private Uri cover;
    private String id;
    private String pkgName;
    private List<MediaItem> queue;
    private PendingIntent queueIntent;
    private int queueType;
    private String title;

    @Retention(RetentionPolicy.SOURCE)
    public @interface QueueType {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void setQueueIntent(PendingIntent pendingIntent) {
        this.queueIntent = pendingIntent;
    }

    private MediaQueue(Builder builder) {
        this.queueType = -1;
        this.id = builder.id;
        this.queueType = builder.queueType;
        this.title = builder.title;
        this.cover = builder.cover;
        this.queueIntent = builder.queueIntent;
        this.queue = builder.queue;
        this.pkgName = builder.pkgName;
    }

    public String getId() {
        return this.id;
    }

    public int getQueueType() {
        return this.queueType;
    }

    public String getTitle() {
        return this.title;
    }

    public String getPkgName() {
        return this.pkgName;
    }

    public Uri getCover() {
        return this.cover;
    }

    public PendingIntent getQueueIntent() {
        return this.queueIntent;
    }

    public List<MediaItem> getQueue() {
        return this.queue;
    }

    public String toString() {
        return "MediaQueue{id='" + this.id + "', queueType=" + this.queueType + ", title='" + this.title + "', pkgName='" + this.pkgName + "', cover=" + this.cover + ", queueIntent=" + this.queueIntent + ", queue=" + this.queue + '}';
    }

    public static final class Builder {
        private Uri cover;
        private String id;
        private String pkgName;
        private List<MediaItem> queue;
        private PendingIntent queueIntent;
        private int queueType;
        private String title;

        public Builder() {
        }

        public Builder(MediaQueue mediaQueue) {
            if (mediaQueue == null) {
                return;
            }
            this.id = mediaQueue.id;
            this.queueType = mediaQueue.queueType;
            this.title = mediaQueue.title;
            this.pkgName = mediaQueue.pkgName;
            this.cover = mediaQueue.cover;
            this.queue = mediaQueue.queue;
        }

        public final Builder id(String str) {
            this.id = str;
            return this;
        }

        public final Builder queueType(int i) {
            this.queueType = i;
            return this;
        }

        public final Builder title(String str) {
            this.title = str;
            return this;
        }

        public final Builder pkgName(String str) {
            this.pkgName = str;
            return this;
        }

        public final Builder cover(Uri uri) {
            this.cover = uri;
            return this;
        }

        public final Builder queueIntent(PendingIntent pendingIntent) {
            this.queueIntent = pendingIntent;
            return this;
        }

        public final Builder queue(List<MediaItem> list) {
            this.queue = list;
            return this;
        }

        public final MediaQueue build() {
            return new MediaQueue(this);
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeInt(this.queueType);
        parcel.writeString(this.title);
        parcel.writeString(this.pkgName);
        parcel.writeParcelable(this.cover, i);
        parcel.writeParcelable(this.queueIntent, i);
        parcel.writeTypedList(this.queue);
    }

    public void readFromParcel(Parcel parcel) {
        this.id = parcel.readString();
        this.queueType = parcel.readInt();
        this.title = parcel.readString();
        this.pkgName = parcel.readString();
        this.cover = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.queueIntent = (PendingIntent) parcel.readParcelable(PendingIntent.class.getClassLoader());
        this.queue = parcel.createTypedArrayList(MediaItem.CREATOR);
    }

    protected MediaQueue(Parcel parcel) {
        this.queueType = -1;
        this.id = parcel.readString();
        this.queueType = parcel.readInt();
        this.title = parcel.readString();
        this.pkgName = parcel.readString();
        this.cover = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.queueIntent = (PendingIntent) parcel.readParcelable(PendingIntent.class.getClassLoader());
        this.queue = parcel.createTypedArrayList(MediaItem.CREATOR);
    }
}
