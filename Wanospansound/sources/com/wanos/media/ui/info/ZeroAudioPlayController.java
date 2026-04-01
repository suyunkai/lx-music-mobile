package com.wanos.media.ui.info;

import androidx.lifecycle.Observer;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.media.entity.EventExitScene;
import com.wanos.media.entity.ZeroPageMode;
import com.wanos.media.util.AlarmTools;
import com.wanos.media.util.ZeroAudioBallTools;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.zero_p.R;
import com.wanos.zero.ZeroAudioFocusTools;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes3.dex */
public class ZeroAudioPlayController {
    private static final String TAG = "ZeroAudioPlayController";
    private final Observer<ZeroAudioFocusTools.AudioFocusState> mAudioFocusStateObserver;
    private PlayerState mAlarmState = PlayerState.IDLE;
    private PlayerState mRelaxState = PlayerState.IDLE;
    private AlarmCausation mAlarmCausation = AlarmCausation.IDLE;
    private RelaxCausation mRelaxCausation = RelaxCausation.IDLE;
    private boolean mIsSetting = false;

    public enum AlarmCausation {
        AUDIO_FOCUS,
        IDLE
    }

    public enum PlayerState {
        PAUSE,
        PLAY,
        STOP,
        IDLE
    }

    public enum RelaxCausation {
        AUDIO_FOCUS,
        ALARM_PLAYING,
        IDLE
    }

    /* JADX INFO: renamed from: lambda$new$0$com-wanos-media-ui-info-ZeroAudioPlayController, reason: not valid java name */
    /* synthetic */ void m491lambda$new$0$comwanosmediauiinfoZeroAudioPlayController(ZeroAudioFocusTools.AudioFocusState audioFocusState) {
        if (audioFocusState == ZeroAudioFocusTools.AudioFocusState.GAIN) {
            if (this.mAlarmState == PlayerState.PAUSE && this.mAlarmCausation == AlarmCausation.AUDIO_FOCUS) {
                resumeAlarmMedia(this.mIsSetting);
                return;
            } else {
                if (this.mRelaxState == PlayerState.STOP && this.mRelaxCausation == RelaxCausation.AUDIO_FOCUS && this.mAlarmState != PlayerState.PLAY) {
                    startRelaxMedia(false, 1);
                    return;
                }
                return;
            }
        }
        if (audioFocusState == ZeroAudioFocusTools.AudioFocusState.LOSS) {
            ToastUtil.showMsg(R.string.error_audio_focus_loss);
            EventBus.getDefault().post(new EventExitScene());
        } else if (audioFocusState == ZeroAudioFocusTools.AudioFocusState.LOSS_TRANSIENT) {
            if (this.mAlarmState == PlayerState.PLAY) {
                pauseAlarmMedia(this.mIsSetting);
            }
            if (this.mRelaxState == PlayerState.PLAY) {
                stopRelaxMedia(RelaxCausation.AUDIO_FOCUS);
            }
        }
    }

    public ZeroAudioPlayController() {
        Observer<ZeroAudioFocusTools.AudioFocusState> observer = new Observer() { // from class: com.wanos.media.ui.info.ZeroAudioPlayController$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m491lambda$new$0$comwanosmediauiinfoZeroAudioPlayController((ZeroAudioFocusTools.AudioFocusState) obj);
            }
        };
        this.mAudioFocusStateObserver = observer;
        ZeroAudioFocusTools.getInstance().getAudioFocusState().observeForever(observer);
    }

    public void setAlarmPlayState(boolean z, ZeroPageMode zeroPageMode) {
        setAlarmPlayState(z, zeroPageMode, false);
    }

    public void setAlarmPlayState(boolean z, ZeroPageMode zeroPageMode, boolean z2) {
        this.mIsSetting = z2;
        if (z) {
            if (isHaveAudioFocusState()) {
                stopRelaxMedia(RelaxCausation.ALARM_PLAYING);
                startAlarmMedia(z2);
                return;
            }
            return;
        }
        stopAlarmMedia(z2);
        startRelaxMedia(z2 ? false : zeroPageMode == ZeroPageMode.MING_XIANG_STANDARD || zeroPageMode == ZeroPageMode.MING_XIANG_PRO, 2);
    }

    public void setRelaxPlayState(boolean z) {
        if (z) {
            if (isHaveAudioFocusState()) {
                if (this.mAlarmState == PlayerState.PLAY) {
                    ZeroLogcatTools.w(TAG, "setRelaxPlayState: 闹铃播放状态 = " + this.mAlarmState);
                    return;
                } else {
                    startRelaxMedia(false, 3);
                    return;
                }
            }
            return;
        }
        stopRelaxMedia(RelaxCausation.IDLE);
    }

    public void onReleaseAudioFocus() {
        ZeroAudioFocusTools.getInstance().getAudioFocusState().removeObserver(this.mAudioFocusStateObserver);
        ZeroAudioFocusTools.getInstance().releaseAudioFocus();
        if (this.mAlarmState == PlayerState.PLAY) {
            stopAlarmMedia(this.mIsSetting);
        }
    }

    private void startRelaxMedia(boolean z, int i) {
        this.mRelaxState = PlayerState.PLAY;
        this.mRelaxCausation = RelaxCausation.IDLE;
        if (z) {
            ZeroAudioBallTools.getInstance().onMediaReplay();
        }
        ZeroAudioBallTools.getInstance().onMediaStart(i);
    }

    private void stopRelaxMedia(RelaxCausation relaxCausation) {
        this.mRelaxState = PlayerState.STOP;
        this.mRelaxCausation = relaxCausation;
        ZeroAudioBallTools.getInstance().onMediaStop(1);
    }

    private void startAlarmMedia(boolean z) {
        this.mAlarmState = PlayerState.PLAY;
        this.mAlarmCausation = AlarmCausation.IDLE;
        if (z) {
            return;
        }
        AlarmTools.playAlarmMusic();
    }

    private void resumeAlarmMedia(boolean z) {
        this.mAlarmState = PlayerState.PLAY;
        this.mAlarmCausation = AlarmCausation.IDLE;
        if (z) {
            return;
        }
        AlarmTools.resumeAlarmMusic();
    }

    private void stopAlarmMedia(boolean z) {
        this.mAlarmState = PlayerState.STOP;
        this.mAlarmCausation = AlarmCausation.IDLE;
        if (z) {
            return;
        }
        AlarmTools.stopAlarmMusic();
    }

    private void pauseAlarmMedia(boolean z) {
        this.mAlarmState = PlayerState.PAUSE;
        this.mAlarmCausation = AlarmCausation.AUDIO_FOCUS;
        if (z) {
            return;
        }
        AlarmTools.pauseAlarmMusic();
    }

    private boolean isHaveAudioFocusState() {
        boolean zRequestAudioFocus = !ZeroAudioFocusTools.getInstance().isCanFocus() ? ZeroAudioFocusTools.getInstance().requestAudioFocus() : true;
        if (!zRequestAudioFocus) {
            ToastUtil.showMsg(R.string.error_audio_focus);
        }
        return zRequestAudioFocus;
    }
}
