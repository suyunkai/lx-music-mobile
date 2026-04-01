package ecarx.xsf.mediacenter.session;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes3.dex */
public class PlaybackState {
    public static final int LOOP_MODE_ALL = 0;
    public static final int LOOP_MODE_SHUFFLE = 2;
    public static final int LOOP_MODE_SINGLE = 1;
    public static final int RADIO_MODE_CAROUSEL = 1;
    public static final int RADIO_MODE_PLAYING = 0;
    public static final int RADIO_MODE_SCAN = 4;
    public static final int RADIO_MODE_SEEK_NEXT = 3;
    public static final int RADIO_MODE_SEEK_PREV = 2;
    public static final int STATE_BUFFERING = 3;
    public static final int STATE_ERROR = 7;
    public static final int STATE_FAST_FORWARDING = 4;
    public static final int STATE_INTERRUPT = 2;
    public static final int STATE_NONE = -1;
    public static final int STATE_PAUSED = 0;
    public static final int STATE_PLAYING = 1;
    public static final int STATE_REWINDING = 5;
    public static final int STATE_SKIPPING_TO_NEXT = 10;
    public static final int STATE_SKIPPING_TO_PREVIOUS = 9;
    private int errorCode;
    private String errorMessage;
    private String itemId;
    private int loopMode;
    private String queueId;
    private int radioMode;
    private int state;

    @Retention(RetentionPolicy.SOURCE)
    public @interface LoopMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PlayState {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface RadioMode {
    }

    public String getQueueId() {
        return this.queueId;
    }

    public String getItemId() {
        return this.itemId;
    }

    public int getState() {
        return this.state;
    }

    public int getLoopMode() {
        return this.loopMode;
    }

    public int getRadioMode() {
        return this.radioMode;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public String toString() {
        return "PlaybackState{state=" + this.state + ", loopMode=" + this.loopMode + ", radioMode=" + this.radioMode + ", queueId='" + this.queueId + "', itemId='" + this.itemId + "', errorCode=" + this.errorCode + ", errorMessage='" + this.errorMessage + "'}";
    }

    public static PlaybackState empty() {
        return new Builder().build();
    }

    private PlaybackState(Builder builder) {
        this.state = builder.state;
        this.loopMode = builder.loopMode;
        this.radioMode = builder.radioMode;
        this.queueId = builder.queueId;
        this.itemId = builder.itemId;
        this.errorCode = builder.errorCode;
        this.errorMessage = builder.errorMessage;
    }

    public static final class Builder {
        private int errorCode;
        private String errorMessage;
        private String itemId;
        private int loopMode;
        private String queueId;
        private int radioMode;
        private int state;

        public Builder() {
        }

        public Builder(PlaybackState playbackState) {
            if (playbackState == null) {
                return;
            }
            this.state = playbackState.state;
            this.loopMode = playbackState.loopMode;
            this.radioMode = playbackState.radioMode;
            this.queueId = playbackState.queueId;
            this.itemId = playbackState.itemId;
            this.errorCode = playbackState.errorCode;
            this.errorMessage = playbackState.errorMessage;
        }

        public final Builder state(int i) {
            this.state = i;
            return this;
        }

        public final Builder loopMode(int i) {
            this.loopMode = i;
            return this;
        }

        public final Builder radioMode(int i) {
            this.radioMode = i;
            return this;
        }

        public final Builder itemId(String str) {
            this.itemId = str;
            return this;
        }

        public final Builder errorCode(int i) {
            this.errorCode = i;
            return this;
        }

        public final Builder errorMessage(String str) {
            this.errorMessage = str;
            return this;
        }

        public final Builder queueId(String str) {
            this.queueId = str;
            return this;
        }

        public final PlaybackState build() {
            return new PlaybackState(this);
        }
    }
}
