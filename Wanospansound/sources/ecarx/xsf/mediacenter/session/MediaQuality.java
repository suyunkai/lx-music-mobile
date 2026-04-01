package ecarx.xsf.mediacenter.session;

/* JADX INFO: loaded from: classes3.dex */
public class MediaQuality {
    boolean active;
    int qualityId;
    String qualityName;

    private MediaQuality(Builder builder) {
        this.qualityId = builder.qualityId;
        this.qualityName = builder.qualityName;
        this.active = builder.active;
    }

    public int getQualityId() {
        return this.qualityId;
    }

    public String getQualityName() {
        return this.qualityName;
    }

    public boolean isActive() {
        return this.active;
    }

    public String toString() {
        return "MediaQuality{qualityId=" + this.qualityId + ", qualityName='" + this.qualityName + "', active=" + this.active + '}';
    }

    public static final class Builder {
        private boolean active;
        private int qualityId;
        private String qualityName;

        public final Builder qualityId(int i) {
            this.qualityId = i;
            return this;
        }

        public final Builder qualityName(String str) {
            this.qualityName = str;
            return this;
        }

        public final Builder active(boolean z) {
            this.active = z;
            return this;
        }

        public final MediaQuality build() {
            return new MediaQuality(this);
        }
    }
}
