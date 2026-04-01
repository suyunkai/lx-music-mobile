package ecarx.xsf.mediacenter.session;

import android.app.PendingIntent;
import android.os.Bundle;
import com.ecarx.eas.sdk.mediacenter.RegisterRequest;
import ecarx.xsf.mediacenter.session.adapter.l;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface EasMediaController {
    public static final int CONTROL_TYPE_FASTFORWARD = 4;
    public static final int CONTROL_TYPE_FASTREWIND = 5;
    public static final int CONTROL_TYPE_NEXT = 10;
    public static final int CONTROL_TYPE_PAUSE = 0;
    public static final int CONTROL_TYPE_PLAY = 1;
    public static final int CONTROL_TYPE_PREVIOUS = 9;
    public static final int CONTROL_TYPE_SEEK = 12;
    public static final int CONTROL_TYPE_VR = 13;
    public static final String EXTRAS_KEY_SEEK = "extra_seek";
    public static final String EXTRAS_VR_CONTROL_REQUEST = "extra_vrControlRequest";

    public interface Callback {
        void onAppSourceInfoChange(List<AppSourceInfo> list);

        void onControlMediaSourceTypeChangedNotifity(String str, String str2, int i);

        void onCustomAppSourceInfoChange(List<AppSourceInfo> list);

        void onErrorMsgUpdate(String str, int i, String str2);

        void onErrorStateUpdateWithPendingIntent(String str, int i, int i2, String str2, PendingIntent pendingIntent);

        void onMediaOperateStateChanged(MediaOperateState mediaOperateState);

        void onMediaQueueChanged(MediaQueue mediaQueue);

        void onMediaQueueChangedWithPagination(int i, int i2, int i3, MediaQueue mediaQueue);

        void onMediaQueueListUpdated(List<MediaQueue> list);

        void onMediaSourceTypeChanged(int i);

        void onMediaSourceTypeChanged(String str, int i);

        void onMediaSourceTypeListChanged(int[] iArr);

        void onPlayLyricSentenceUpdated(String str);

        void onPlayProgressUpdated(long j);

        void onPlaybackInfoChange(PlaybackInfo playbackInfo);

        void onPlaybackStateChanged(PlaybackState playbackState);

        void onVRControlReply(ecarx.xsf.mediacenter.vr.a aVar);

        void onZonesChanged(int[] iArr);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ControlType {
    }

    public interface TransportControls {
        void download(String str, String str2, boolean z);

        void favorite(String str, String str2, boolean z);

        void select(String str, String str2, boolean z);

        void selectFrom(String str, String str2, boolean z, String str3);

        void setLoopMode(int i);

        void setMediaQuality(int i);

        void setMediaSource(int i);

        void setMediaSource(String str, int i);

        void setMediaSource(String str, String str2, int i);

        void transportControl(int i, Bundle bundle);
    }

    List<l> getAllFocusInfoList();

    List<AppSourceInfo> getAppSourceConfig();

    List<AppSourceInfo> getAppSourceInfo();

    int getCurrentMediaSourceType();

    List<AppSourceInfo> getCustomAppSourceInfo();

    MediaQueue getMediaQueue();

    int[] getMediaSourceTypeList();

    PlaybackInfo getPlaybackInfo();

    PlaybackState getPlaybackState();

    TransportControls getTransportControls();

    int[] getZones();

    TransportControls registerCallback(RegisterRequest registerRequest, Callback callback);

    boolean registerCallback(Callback callback);

    boolean registerCallbackWithZoneId(int i, Callback callback);

    void setAppSourceConfig(List<AppSourceInfo> list);

    boolean unregisterCallback(Callback callback);

    void updateZoneId(int i);
}
