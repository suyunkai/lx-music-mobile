package com.wanos.media.widget.controller;

import android.os.CountDownTimer;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import com.blankj.utilcode.util.StringUtils;
import com.wanos.commonlibrary.event.LoginOrLogoutEvent;
import com.wanos.commonlibrary.event.UserInfoChangeEvent;
import com.wanos.media.entity.ZeroPageMode;
import com.wanos.media.util.LoginUtils;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.widget.RelaxInfoActionButton;
import com.wanos.media.widget.controller.WanosZeroControlView;
import com.wanos.media.zero_p.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: classes3.dex */
public class WanosControllerViewModel extends ViewModel {
    public static final int EDIT_STATE_IDLE = 100;
    public static final int EDIT_STATE_PREPARE = 101;
    public static final int EDIT_STATE_RUNNING = 102;
    private static final String TAG = "WanosControllerViewMode";
    private final MediatorLiveData<Integer> _BackButtonVisibilityState;
    private final MediatorLiveData<Integer> _ClockViewVisibilityState;
    private final MediatorLiveData<Integer> _CountdownTextVisibilityState;
    private final MutableLiveData<Boolean> _ImmerseState;
    private final MutableLiveData<Integer> _NapState;
    private final MediatorLiveData<Integer> _OpenEditBtnVisibilityState;
    private final MediatorLiveData<Integer> _OpenVipBtnVisibilityState;
    private final MutableLiveData<ZeroPageMode> _PageMode;
    private final MediatorLiveData<Integer> _RelaxActionBtnVisibilityState;
    private final MediatorLiveData<RelaxInfoActionButton.State> _RelaxInfoBtnState;
    private final MediatorLiveData<Integer> _SettingBtnVisibilityState;
    private final MutableLiveData<RelaxInfoActionButton.State> _TempRelaxInfoBtnState;
    private final MediatorLiveData<Integer> _TimePickerVisibilityState;
    private final MediatorLiveData<Integer> _TitleTextVisibilityState;
    private final MediatorLiveData<Integer> _VipFlagVisibilityState;
    private final MutableLiveData<Boolean> _VipState;
    public final LiveData<Integer> backButtonVisibilityState;
    public final LiveData<Integer> clockViewVisibilityState;
    public final LiveData<Integer> countdownTextVisibilityState;
    public final LiveData<Boolean> immerseState;
    public final LiveData<Integer> napState;
    public final LiveData<Integer> openEditBtnVisibilityState;
    public final LiveData<Integer> openVipBtnVisibilityState;
    public final LiveData<ZeroPageMode> pageMode;
    public final LiveData<Integer> relaxActionBtnVisibilityState;
    public final LiveData<RelaxInfoActionButton.State> relaxInfoButtonState;
    public final LiveData<Integer> settingBtnVisibilityState;
    public final LiveData<Integer> timePickerMaskVisibilityState;
    public final LiveData<Integer> timePickerVisibilityState;
    public final LiveData<Integer> titleTextVisibilityState;
    public final LiveData<Integer> vipFlagVisibilityState;
    public final LiveData<Boolean> vipState;
    private ZeroCountdownTime mCountdownTime = null;
    private boolean isMeEnter = false;
    private int mDownTime = -1;
    private String[] mMeditationTimeData = null;
    private int mMeditationIndex = -1;

    @Retention(RetentionPolicy.SOURCE)
    @interface EditState {
    }

    static /* synthetic */ Integer lambda$new$0(Integer num) {
        if (101 == num.intValue()) {
            return 0;
        }
        return 8;
    }

    public WanosControllerViewModel() {
        MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>(false);
        this._VipState = mutableLiveData;
        this.vipState = mutableLiveData;
        MutableLiveData<ZeroPageMode> mutableLiveData2 = new MutableLiveData<>(ZeroPageMode.XIAO_QI_STANDARD);
        this._PageMode = mutableLiveData2;
        this.pageMode = mutableLiveData2;
        MutableLiveData<Integer> mutableLiveData3 = new MutableLiveData<>(100);
        this._NapState = mutableLiveData3;
        this.napState = mutableLiveData3;
        MutableLiveData<RelaxInfoActionButton.State> mutableLiveData4 = new MutableLiveData<>(RelaxInfoActionButton.State.LOADING);
        this._TempRelaxInfoBtnState = mutableLiveData4;
        MediatorLiveData<RelaxInfoActionButton.State> mediatorLiveData = new MediatorLiveData<>(RelaxInfoActionButton.State.LOADING);
        this._RelaxInfoBtnState = mediatorLiveData;
        this.relaxInfoButtonState = mediatorLiveData;
        MutableLiveData<Boolean> mutableLiveData5 = new MutableLiveData<>(false);
        this._ImmerseState = mutableLiveData5;
        this.immerseState = mutableLiveData5;
        MediatorLiveData<Integer> mediatorLiveData2 = new MediatorLiveData<>(0);
        this._BackButtonVisibilityState = mediatorLiveData2;
        this.backButtonVisibilityState = mediatorLiveData2;
        MediatorLiveData<Integer> mediatorLiveData3 = new MediatorLiveData<>(0);
        this._TitleTextVisibilityState = mediatorLiveData3;
        this.titleTextVisibilityState = mediatorLiveData3;
        MediatorLiveData<Integer> mediatorLiveData4 = new MediatorLiveData<>(8);
        this._VipFlagVisibilityState = mediatorLiveData4;
        this.vipFlagVisibilityState = mediatorLiveData4;
        MediatorLiveData<Integer> mediatorLiveData5 = new MediatorLiveData<>(8);
        this._OpenVipBtnVisibilityState = mediatorLiveData5;
        this.openVipBtnVisibilityState = mediatorLiveData5;
        MediatorLiveData<Integer> mediatorLiveData6 = new MediatorLiveData<>(0);
        this._RelaxActionBtnVisibilityState = mediatorLiveData6;
        this.relaxActionBtnVisibilityState = mediatorLiveData6;
        MediatorLiveData<Integer> mediatorLiveData7 = new MediatorLiveData<>(0);
        this._OpenEditBtnVisibilityState = mediatorLiveData7;
        this.openEditBtnVisibilityState = mediatorLiveData7;
        MediatorLiveData<Integer> mediatorLiveData8 = new MediatorLiveData<>(8);
        this._CountdownTextVisibilityState = mediatorLiveData8;
        this.countdownTextVisibilityState = mediatorLiveData8;
        MediatorLiveData<Integer> mediatorLiveData9 = new MediatorLiveData<>(8);
        this._ClockViewVisibilityState = mediatorLiveData9;
        this.clockViewVisibilityState = mediatorLiveData9;
        MediatorLiveData<Integer> mediatorLiveData10 = new MediatorLiveData<>(8);
        this._TimePickerVisibilityState = mediatorLiveData10;
        this.timePickerVisibilityState = mediatorLiveData10;
        this.timePickerMaskVisibilityState = Transformations.map(mutableLiveData3, new Function1() { // from class: com.wanos.media.widget.controller.WanosControllerViewModel$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return WanosControllerViewModel.lambda$new$0((Integer) obj);
            }
        });
        MediatorLiveData<Integer> mediatorLiveData11 = new MediatorLiveData<>(8);
        this._SettingBtnVisibilityState = mediatorLiveData11;
        this.settingBtnVisibilityState = mediatorLiveData11;
        mediatorLiveData2.addSource(mutableLiveData5, new Observer() { // from class: com.wanos.media.widget.controller.WanosControllerViewModel$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m613x48a41d56((Boolean) obj);
            }
        });
        mediatorLiveData2.addSource(mutableLiveData3, new Observer() { // from class: com.wanos.media.widget.controller.WanosControllerViewModel$$ExternalSyntheticLambda14
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m624x4ea7e8b5((Integer) obj);
            }
        });
        mediatorLiveData2.addSource(mutableLiveData2, new Observer() { // from class: com.wanos.media.widget.controller.WanosControllerViewModel$$ExternalSyntheticLambda16
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m635x54abb414((ZeroPageMode) obj);
            }
        });
        mediatorLiveData3.addSource(mutableLiveData5, new Observer() { // from class: com.wanos.media.widget.controller.WanosControllerViewModel$$ExternalSyntheticLambda17
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m637x5aaf7f73((Boolean) obj);
            }
        });
        mediatorLiveData3.addSource(mutableLiveData3, new Observer() { // from class: com.wanos.media.widget.controller.WanosControllerViewModel$$ExternalSyntheticLambda18
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m638x60b34ad2((Integer) obj);
            }
        });
        mediatorLiveData4.addSource(mutableLiveData5, new Observer() { // from class: com.wanos.media.widget.controller.WanosControllerViewModel$$ExternalSyntheticLambda19
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m639x66b71631((Boolean) obj);
            }
        });
        mediatorLiveData4.addSource(mutableLiveData3, new Observer() { // from class: com.wanos.media.widget.controller.WanosControllerViewModel$$ExternalSyntheticLambda20
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m640x6cbae190((Integer) obj);
            }
        });
        mediatorLiveData4.addSource(mutableLiveData, new Observer() { // from class: com.wanos.media.widget.controller.WanosControllerViewModel$$ExternalSyntheticLambda21
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m641x72beacef((Boolean) obj);
            }
        });
        mediatorLiveData5.addSource(mutableLiveData, new Observer() { // from class: com.wanos.media.widget.controller.WanosControllerViewModel$$ExternalSyntheticLambda23
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m642x78c2784e((Boolean) obj);
            }
        });
        mediatorLiveData5.addSource(LoginUtils.getInstance().onLoginState, new Observer() { // from class: com.wanos.media.widget.controller.WanosControllerViewModel$$ExternalSyntheticLambda11
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m614xab4d2f46((LoginOrLogoutEvent) obj);
            }
        });
        mediatorLiveData5.addSource(LoginUtils.getInstance().onUserInfoChange, new Observer() { // from class: com.wanos.media.widget.controller.WanosControllerViewModel$$ExternalSyntheticLambda22
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m615xb150faa5((UserInfoChangeEvent) obj);
            }
        });
        mediatorLiveData5.addSource(mutableLiveData4, new Observer() { // from class: com.wanos.media.widget.controller.WanosControllerViewModel$$ExternalSyntheticLambda24
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m616xb754c604((RelaxInfoActionButton.State) obj);
            }
        });
        mediatorLiveData6.addSource(mediatorLiveData, new Observer() { // from class: com.wanos.media.widget.controller.WanosControllerViewModel$$ExternalSyntheticLambda25
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m617xbd589163((RelaxInfoActionButton.State) obj);
            }
        });
        mediatorLiveData6.addSource(mutableLiveData5, new Observer() { // from class: com.wanos.media.widget.controller.WanosControllerViewModel$$ExternalSyntheticLambda26
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m618xc35c5cc2((Boolean) obj);
            }
        });
        mediatorLiveData7.addSource(mutableLiveData2, new Observer() { // from class: com.wanos.media.widget.controller.WanosControllerViewModel$$ExternalSyntheticLambda27
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m619xc9602821((ZeroPageMode) obj);
            }
        });
        mediatorLiveData7.addSource(mutableLiveData5, new Observer() { // from class: com.wanos.media.widget.controller.WanosControllerViewModel$$ExternalSyntheticLambda28
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m620xcf63f380((Boolean) obj);
            }
        });
        mediatorLiveData7.addSource(mediatorLiveData, new Observer() { // from class: com.wanos.media.widget.controller.WanosControllerViewModel$$ExternalSyntheticLambda29
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m621xd567bedf((RelaxInfoActionButton.State) obj);
            }
        });
        mediatorLiveData8.addSource(mutableLiveData3, new Observer() { // from class: com.wanos.media.widget.controller.WanosControllerViewModel$$ExternalSyntheticLambda30
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m622xdb6b8a3e((Integer) obj);
            }
        });
        mediatorLiveData8.addSource(mutableLiveData5, new Observer() { // from class: com.wanos.media.widget.controller.WanosControllerViewModel$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m623xe16f559d((Boolean) obj);
            }
        });
        mediatorLiveData9.addSource(mutableLiveData5, new Observer() { // from class: com.wanos.media.widget.controller.WanosControllerViewModel$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m625x65c2cfc7((Boolean) obj);
            }
        });
        mediatorLiveData9.addSource(mutableLiveData3, new Observer() { // from class: com.wanos.media.widget.controller.WanosControllerViewModel$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m626x6bc69b26((Integer) obj);
            }
        });
        mediatorLiveData10.addSource(mutableLiveData5, new Observer() { // from class: com.wanos.media.widget.controller.WanosControllerViewModel$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m627x71ca6685((Boolean) obj);
            }
        });
        mediatorLiveData10.addSource(mutableLiveData3, new Observer() { // from class: com.wanos.media.widget.controller.WanosControllerViewModel$$ExternalSyntheticLambda6
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m628x77ce31e4((Integer) obj);
            }
        });
        mediatorLiveData10.addSource(mutableLiveData2, new Observer() { // from class: com.wanos.media.widget.controller.WanosControllerViewModel$$ExternalSyntheticLambda7
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m629x7dd1fd43((ZeroPageMode) obj);
            }
        });
        mediatorLiveData.addSource(mutableLiveData4, new Observer() { // from class: com.wanos.media.widget.controller.WanosControllerViewModel$$ExternalSyntheticLambda8
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m630x83d5c8a2((RelaxInfoActionButton.State) obj);
            }
        });
        mediatorLiveData.addSource(LoginUtils.getInstance().onLoginState, new Observer() { // from class: com.wanos.media.widget.controller.WanosControllerViewModel$$ExternalSyntheticLambda9
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m631x89d99401((LoginOrLogoutEvent) obj);
            }
        });
        mediatorLiveData.addSource(LoginUtils.getInstance().onUserInfoChange, new Observer() { // from class: com.wanos.media.widget.controller.WanosControllerViewModel$$ExternalSyntheticLambda10
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m632x8fdd5f60((UserInfoChangeEvent) obj);
            }
        });
        mediatorLiveData11.addSource(mutableLiveData2, new Observer() { // from class: com.wanos.media.widget.controller.WanosControllerViewModel$$ExternalSyntheticLambda12
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m633x95e12abf((ZeroPageMode) obj);
            }
        });
        mediatorLiveData11.addSource(mutableLiveData5, new Observer() { // from class: com.wanos.media.widget.controller.WanosControllerViewModel$$ExternalSyntheticLambda13
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m634x9be4f61e((Boolean) obj);
            }
        });
        mediatorLiveData11.addSource(mutableLiveData3, new Observer() { // from class: com.wanos.media.widget.controller.WanosControllerViewModel$$ExternalSyntheticLambda15
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m636x20387048((Integer) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$new$1$com-wanos-media-widget-controller-WanosControllerViewModel, reason: not valid java name */
    /* synthetic */ void m613x48a41d56(Boolean bool) {
        initBackBtnVisibilityState();
    }

    /* JADX INFO: renamed from: lambda$new$2$com-wanos-media-widget-controller-WanosControllerViewModel, reason: not valid java name */
    /* synthetic */ void m624x4ea7e8b5(Integer num) {
        initBackBtnVisibilityState();
    }

    /* JADX INFO: renamed from: lambda$new$3$com-wanos-media-widget-controller-WanosControllerViewModel, reason: not valid java name */
    /* synthetic */ void m635x54abb414(ZeroPageMode zeroPageMode) {
        initBackBtnVisibilityState();
    }

    /* JADX INFO: renamed from: lambda$new$4$com-wanos-media-widget-controller-WanosControllerViewModel, reason: not valid java name */
    /* synthetic */ void m637x5aaf7f73(Boolean bool) {
        initTitleTextVisibilityState();
    }

    /* JADX INFO: renamed from: lambda$new$5$com-wanos-media-widget-controller-WanosControllerViewModel, reason: not valid java name */
    /* synthetic */ void m638x60b34ad2(Integer num) {
        initTitleTextVisibilityState();
    }

    /* JADX INFO: renamed from: lambda$new$6$com-wanos-media-widget-controller-WanosControllerViewModel, reason: not valid java name */
    /* synthetic */ void m639x66b71631(Boolean bool) {
        initVipFlagVisibilityState();
    }

    /* JADX INFO: renamed from: lambda$new$7$com-wanos-media-widget-controller-WanosControllerViewModel, reason: not valid java name */
    /* synthetic */ void m640x6cbae190(Integer num) {
        initVipFlagVisibilityState();
    }

    /* JADX INFO: renamed from: lambda$new$8$com-wanos-media-widget-controller-WanosControllerViewModel, reason: not valid java name */
    /* synthetic */ void m641x72beacef(Boolean bool) {
        initVipFlagVisibilityState();
    }

    /* JADX INFO: renamed from: lambda$new$9$com-wanos-media-widget-controller-WanosControllerViewModel, reason: not valid java name */
    /* synthetic */ void m642x78c2784e(Boolean bool) {
        initOpenVipBtnVisibilityState();
    }

    /* JADX INFO: renamed from: lambda$new$10$com-wanos-media-widget-controller-WanosControllerViewModel, reason: not valid java name */
    /* synthetic */ void m614xab4d2f46(LoginOrLogoutEvent loginOrLogoutEvent) {
        initOpenVipBtnVisibilityState();
    }

    /* JADX INFO: renamed from: lambda$new$11$com-wanos-media-widget-controller-WanosControllerViewModel, reason: not valid java name */
    /* synthetic */ void m615xb150faa5(UserInfoChangeEvent userInfoChangeEvent) {
        initOpenVipBtnVisibilityState();
    }

    /* JADX INFO: renamed from: lambda$new$12$com-wanos-media-widget-controller-WanosControllerViewModel, reason: not valid java name */
    /* synthetic */ void m616xb754c604(RelaxInfoActionButton.State state) {
        initOpenVipBtnVisibilityState();
    }

    /* JADX INFO: renamed from: lambda$new$13$com-wanos-media-widget-controller-WanosControllerViewModel, reason: not valid java name */
    /* synthetic */ void m617xbd589163(RelaxInfoActionButton.State state) {
        initRelaxBtnVisibilityState();
    }

    /* JADX INFO: renamed from: lambda$new$14$com-wanos-media-widget-controller-WanosControllerViewModel, reason: not valid java name */
    /* synthetic */ void m618xc35c5cc2(Boolean bool) {
        initRelaxBtnVisibilityState();
    }

    /* JADX INFO: renamed from: lambda$new$15$com-wanos-media-widget-controller-WanosControllerViewModel, reason: not valid java name */
    /* synthetic */ void m619xc9602821(ZeroPageMode zeroPageMode) {
        initOpenEditBtnVisibilityState();
    }

    /* JADX INFO: renamed from: lambda$new$16$com-wanos-media-widget-controller-WanosControllerViewModel, reason: not valid java name */
    /* synthetic */ void m620xcf63f380(Boolean bool) {
        initOpenEditBtnVisibilityState();
    }

    /* JADX INFO: renamed from: lambda$new$17$com-wanos-media-widget-controller-WanosControllerViewModel, reason: not valid java name */
    /* synthetic */ void m621xd567bedf(RelaxInfoActionButton.State state) {
        initOpenEditBtnVisibilityState();
    }

    /* JADX INFO: renamed from: lambda$new$18$com-wanos-media-widget-controller-WanosControllerViewModel, reason: not valid java name */
    /* synthetic */ void m622xdb6b8a3e(Integer num) {
        initCountdownTextVisibilityState();
    }

    /* JADX INFO: renamed from: lambda$new$19$com-wanos-media-widget-controller-WanosControllerViewModel, reason: not valid java name */
    /* synthetic */ void m623xe16f559d(Boolean bool) {
        initCountdownTextVisibilityState();
    }

    /* JADX INFO: renamed from: lambda$new$20$com-wanos-media-widget-controller-WanosControllerViewModel, reason: not valid java name */
    /* synthetic */ void m625x65c2cfc7(Boolean bool) {
        initClockViewVisibilityState();
    }

    /* JADX INFO: renamed from: lambda$new$21$com-wanos-media-widget-controller-WanosControllerViewModel, reason: not valid java name */
    /* synthetic */ void m626x6bc69b26(Integer num) {
        initClockViewVisibilityState();
    }

    /* JADX INFO: renamed from: lambda$new$22$com-wanos-media-widget-controller-WanosControllerViewModel, reason: not valid java name */
    /* synthetic */ void m627x71ca6685(Boolean bool) {
        initTimePickerVisibilityState();
    }

    /* JADX INFO: renamed from: lambda$new$23$com-wanos-media-widget-controller-WanosControllerViewModel, reason: not valid java name */
    /* synthetic */ void m628x77ce31e4(Integer num) {
        initTimePickerVisibilityState();
    }

    /* JADX INFO: renamed from: lambda$new$24$com-wanos-media-widget-controller-WanosControllerViewModel, reason: not valid java name */
    /* synthetic */ void m629x7dd1fd43(ZeroPageMode zeroPageMode) {
        initTimePickerVisibilityState();
    }

    /* JADX INFO: renamed from: lambda$new$25$com-wanos-media-widget-controller-WanosControllerViewModel, reason: not valid java name */
    /* synthetic */ void m630x83d5c8a2(RelaxInfoActionButton.State state) {
        initRelaxBtnContentState();
    }

    /* JADX INFO: renamed from: lambda$new$26$com-wanos-media-widget-controller-WanosControllerViewModel, reason: not valid java name */
    /* synthetic */ void m631x89d99401(LoginOrLogoutEvent loginOrLogoutEvent) {
        initRelaxBtnContentState();
    }

    /* JADX INFO: renamed from: lambda$new$27$com-wanos-media-widget-controller-WanosControllerViewModel, reason: not valid java name */
    /* synthetic */ void m632x8fdd5f60(UserInfoChangeEvent userInfoChangeEvent) {
        initRelaxBtnContentState();
    }

    /* JADX INFO: renamed from: lambda$new$28$com-wanos-media-widget-controller-WanosControllerViewModel, reason: not valid java name */
    /* synthetic */ void m633x95e12abf(ZeroPageMode zeroPageMode) {
        initSettingBtnVisibilityState();
    }

    /* JADX INFO: renamed from: lambda$new$29$com-wanos-media-widget-controller-WanosControllerViewModel, reason: not valid java name */
    /* synthetic */ void m634x9be4f61e(Boolean bool) {
        initSettingBtnVisibilityState();
    }

    /* JADX INFO: renamed from: lambda$new$30$com-wanos-media-widget-controller-WanosControllerViewModel, reason: not valid java name */
    /* synthetic */ void m636x20387048(Integer num) {
        initSettingBtnVisibilityState();
    }

    public void setImmerseState(boolean z) {
        this._ImmerseState.setValue(Boolean.valueOf(z));
        if (!z || 102 == this.napState.getValue().intValue()) {
            return;
        }
        this._NapState.setValue(100);
    }

    public void setRelaxInfoButtonState(RelaxInfoActionButton.State state) {
        this._TempRelaxInfoBtnState.setValue(state);
    }

    public void setVipState(boolean z) {
        this._VipState.setValue(Boolean.valueOf(z));
    }

    public void setEditState(int i) {
        this._NapState.setValue(Integer.valueOf(i));
    }

    public int getEditState() {
        if (this._NapState.getValue() == null) {
            return 100;
        }
        return this._NapState.getValue().intValue();
    }

    public LiveData<Integer> getEditStateLiveData() {
        return this._NapState;
    }

    public void setZeroPageMode(ZeroPageMode zeroPageMode) {
        this._PageMode.setValue(zeroPageMode);
    }

    public ZeroPageMode getZeroPageMode() {
        return this.pageMode.getValue();
    }

    public void openCountDownTimer(WanosZeroControlView wanosZeroControlView, int i) {
        ZeroLogcatTools.d(TAG, "openCountDownTimer: downtime = " + i + ", state = " + this.pageMode.getValue());
        closeCountDownTimer();
        if (i <= 0) {
            ZeroLogcatTools.e(TAG, "openCountDownTimer: downtime <= 0");
            return;
        }
        this.mDownTime = i;
        ZeroCountdownTime zeroCountdownTime = new ZeroCountdownTime(wanosZeroControlView, ((long) (i * 60)) * 1000);
        this.mCountdownTime = zeroCountdownTime;
        zeroCountdownTime.start();
    }

    public void closeCountDownTimer() {
        if (this.mCountdownTime != null) {
            ZeroLogcatTools.d(TAG, "closeCountDownTimer: 停止释放计时器");
            this.mCountdownTime.cancel();
            this.mCountdownTime = null;
        }
    }

    public void setMeEnter(boolean z) {
        this.isMeEnter = z;
    }

    public boolean isMeEnter() {
        return this.isMeEnter;
    }

    public void setDownTime(int i) {
        ZeroLogcatTools.d(TAG, "setDownTime: 更新计时器时间 = " + i);
        this.mDownTime = i;
    }

    public int getDownTime() {
        return this.mDownTime;
    }

    public void setMeditationTimeData(String[] strArr) {
        this.mMeditationTimeData = strArr;
    }

    public String[] getMeditationTimeData() {
        return this.mMeditationTimeData;
    }

    public void setMeditationIndex(int i) {
        this.mMeditationIndex = i;
    }

    public int getMeditationIndex() {
        return this.mMeditationIndex;
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        ZeroLogcatTools.i(TAG, "onCleared: 释放计时器");
        closeCountDownTimer();
    }

    private static class ZeroCountdownTime extends CountDownTimer {
        private final WeakReference<WanosZeroControlView> mView;

        public ZeroCountdownTime(WanosZeroControlView wanosZeroControlView, long j) {
            super(j, 60000L);
            this.mView = new WeakReference<>(wanosZeroControlView);
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            WanosZeroControlView wanosZeroControlView = this.mView.get();
            if (wanosZeroControlView == null) {
                return;
            }
            int iCeil = (int) Math.ceil((j / 1000.0f) / 60.0f);
            if (iCeil <= 0) {
                onFinish();
            } else {
                wanosZeroControlView.setDownTime(iCeil);
                wanosZeroControlView.setCountDownTime(StringUtils.format(StringUtils.getString(R.string.zero_personalize_countdown_time), Integer.valueOf(iCeil)));
            }
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            WanosZeroControlView wanosZeroControlView = this.mView.get();
            if (wanosZeroControlView == null) {
                ZeroLogcatTools.e(WanosControllerViewModel.TAG, "WanosZeroControlView is null");
                return;
            }
            wanosZeroControlView.setRelaxEditState(100);
            WanosZeroControlView.IControlCallback iControlCallback = wanosZeroControlView.getIControlCallback();
            if (iControlCallback == null) {
                ZeroLogcatTools.e(WanosControllerViewModel.TAG, "ControlCallback is null");
            } else {
                iControlCallback.onExitRelaxState();
            }
        }
    }

    private void initBackBtnVisibilityState() {
        if (Boolean.TRUE == this.immerseState.getValue()) {
            this._BackButtonVisibilityState.setValue(4);
            return;
        }
        if (102 == this._NapState.getValue().intValue()) {
            if (ZeroPageMode.XIAO_QI_STANDARD == this.pageMode.getValue() || ZeroPageMode.MING_XIANG_STANDARD == this.pageMode.getValue()) {
                this._BackButtonVisibilityState.setValue(4);
                return;
            }
            if (ZeroPageMode.XIAO_QI_PRO == this.pageMode.getValue() || ZeroPageMode.MING_XIANG_PRO == this.pageMode.getValue()) {
                if (isMeEnter()) {
                    this._BackButtonVisibilityState.setValue(4);
                    return;
                } else {
                    this._BackButtonVisibilityState.setValue(0);
                    return;
                }
            }
            this._BackButtonVisibilityState.setValue(0);
            return;
        }
        this._BackButtonVisibilityState.setValue(0);
    }

    private void initTitleTextVisibilityState() {
        if (Boolean.TRUE == this.immerseState.getValue() || 102 == this._NapState.getValue().intValue()) {
            this._TitleTextVisibilityState.setValue(4);
        } else {
            this._TitleTextVisibilityState.setValue(0);
        }
    }

    private void initVipFlagVisibilityState() {
        if (Boolean.TRUE == this.immerseState.getValue() || 102 == this._NapState.getValue().intValue()) {
            this._VipFlagVisibilityState.setValue(4);
        } else if (Boolean.TRUE != this.vipState.getValue()) {
            this._VipFlagVisibilityState.setValue(4);
        } else {
            this._VipFlagVisibilityState.setValue(0);
        }
    }

    private void initOpenVipBtnVisibilityState() {
        if (Boolean.TRUE == this.vipState.getValue()) {
            if (this._TempRelaxInfoBtnState.getValue() == RelaxInfoActionButton.State.TIMING && (!LoginUtils.getInstance().isLogin() || !LoginUtils.getInstance().isVip())) {
                this._OpenVipBtnVisibilityState.setValue(0);
                return;
            } else {
                this._OpenVipBtnVisibilityState.setValue(8);
                return;
            }
        }
        this._OpenVipBtnVisibilityState.setValue(8);
    }

    private void initRelaxBtnVisibilityState() {
        if (this.relaxInfoButtonState.getValue() == RelaxInfoActionButton.State.LOADING || this.relaxInfoButtonState.getValue() == RelaxInfoActionButton.State.TIMING) {
            this._RelaxActionBtnVisibilityState.setValue(0);
        } else if (Boolean.TRUE != this.immerseState.getValue()) {
            this._RelaxActionBtnVisibilityState.setValue(0);
        } else {
            this._RelaxActionBtnVisibilityState.setValue(4);
        }
    }

    private void initOpenEditBtnVisibilityState() {
        if (ZeroPageMode.XIAO_QI_PRO == this.pageMode.getValue() || ZeroPageMode.MING_XIANG_PRO == this.pageMode.getValue()) {
            this._OpenEditBtnVisibilityState.setValue(8);
            return;
        }
        if (RelaxInfoActionButton.State.TIMING == this.relaxInfoButtonState.getValue() || RelaxInfoActionButton.State.LOADING == this.relaxInfoButtonState.getValue()) {
            this._OpenEditBtnVisibilityState.setValue(8);
        } else if (Boolean.TRUE == this.immerseState.getValue()) {
            this._OpenEditBtnVisibilityState.setValue(4);
        } else {
            this._OpenEditBtnVisibilityState.setValue(0);
        }
    }

    private void initCountdownTextVisibilityState() {
        if (102 == this.napState.getValue().intValue()) {
            if (Boolean.TRUE != this.immerseState.getValue()) {
                this._CountdownTextVisibilityState.setValue(0);
                return;
            } else {
                this._CountdownTextVisibilityState.setValue(4);
                return;
            }
        }
        this._CountdownTextVisibilityState.setValue(4);
    }

    private void initClockViewVisibilityState() {
        if (Boolean.TRUE == this.immerseState.getValue() || 102 == this.napState.getValue().intValue()) {
            this._ClockViewVisibilityState.setValue(0);
        } else {
            this._ClockViewVisibilityState.setValue(4);
        }
    }

    private void initTimePickerVisibilityState() {
        switch (this.napState.getValue().intValue()) {
            case 100:
                if (Boolean.TRUE != this.immerseState.getValue()) {
                    if (ZeroPageMode.MING_XIANG_STANDARD == this.pageMode.getValue()) {
                        this._TimePickerVisibilityState.setValue(0);
                    } else {
                        this._TimePickerVisibilityState.setValue(4);
                    }
                } else {
                    this._TimePickerVisibilityState.setValue(4);
                }
                break;
            case 101:
                this._TimePickerVisibilityState.setValue(0);
                break;
            case 102:
                this._TimePickerVisibilityState.setValue(4);
                break;
        }
    }

    private void initRelaxBtnContentState() {
        if (this._TempRelaxInfoBtnState.getValue() == RelaxInfoActionButton.State.TIMING) {
            if (LoginUtils.getInstance().isLogin() && LoginUtils.getInstance().isVip()) {
                this._RelaxInfoBtnState.setValue(RelaxInfoActionButton.State.NORMAL);
                return;
            } else {
                this._RelaxInfoBtnState.setValue(this._TempRelaxInfoBtnState.getValue());
                return;
            }
        }
        this._RelaxInfoBtnState.setValue(this._TempRelaxInfoBtnState.getValue());
    }

    private void initSettingBtnVisibilityState() {
        if (isMeEnter() || ZeroPageMode.XIAO_QI_STANDARD == this.pageMode.getValue() || ZeroPageMode.MING_XIANG_STANDARD == this.pageMode.getValue()) {
            if (Boolean.TRUE == this.immerseState.getValue() || 102 == this.napState.getValue().intValue()) {
                this._SettingBtnVisibilityState.setValue(4);
                return;
            } else {
                this._SettingBtnVisibilityState.setValue(0);
                return;
            }
        }
        this._SettingBtnVisibilityState.setValue(4);
    }
}
