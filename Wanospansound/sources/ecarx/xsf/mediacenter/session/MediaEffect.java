package ecarx.xsf.mediacenter.session;

/* JADX INFO: loaded from: classes3.dex */
public class MediaEffect {
    int effectId;
    String effectName;

    public int getEffectId() {
        return this.effectId;
    }

    public String getEffectName() {
        return this.effectName;
    }

    private MediaEffect(Builder builder) {
        this.effectId = builder.effectId;
        this.effectName = builder.effectName;
    }

    public String toString() {
        return "MediaEffect{effectId=" + this.effectId + ", effectName='" + this.effectName + "'}";
    }

    public static final class Builder {
        private int effectId;
        private String effectName;

        public final Builder effectId(int i) {
            this.effectId = i;
            return this;
        }

        public final Builder effectName(String str) {
            this.effectName = str;
            return this;
        }

        public final MediaEffect build() {
            return new MediaEffect(this);
        }
    }
}
