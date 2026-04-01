package com.wanos.media.widget.controller;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import com.blankj.utilcode.util.BarUtils;
import com.wanos.media.entity.ZeroPageMode;
import com.wanos.media.util.CustomClick;
import com.wanos.media.util.LoginUtils;
import com.wanos.media.util.ToastUtil;
import com.wanos.media.util.ZeroAudioBallTools;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.widget.RelaxInfoActionButton;
import com.wanos.media.widget.WanosClockView;
import com.wanos.media.widget.WanosTextView;
import com.wanos.media.widget.controller.WanosZeroControlTimeSelectAdapter;
import com.wanos.media.widget.controller.WanosZeroControlTimeSelectView;
import com.wanos.media.zero_p.R;
import com.wanos.media.zero_p.databinding.CustomZeroNormalActionBinding;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class WanosZeroControlView extends FrameLayout {
    private static final String TAG = "WanosZeroControlView";
    private IControlCallback mIControlCallback;
    private final CustomZeroNormalActionBinding mViewBinding;
    private final WanosControllerViewModel mViewModel;

    public interface IControlCallback {
        void onCancelRelaxState();

        void onEnterRelaxState();

        void onExitRelaxState();

        int onPageScrollState();

        void onRelaxTimeSelect(int i, int i2, WanosZeroControlTimeSelectAdapter.IItemStateCallback iItemStateCallback);

        void onSettingClick();

        void onTrialEnd();

        void onZeroBackClick();

        void onZeroPersonalizeClick(ZeroPageMode zeroPageMode);

        void onZeroRandomOrSelect(ZeroPageMode zeroPageMode, ISceneLoadState iSceneLoadState);
    }

    public interface ISceneLoadState {
        void onSceneLoaded();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public WanosZeroControlView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        WanosControllerViewModel wanosControllerViewModel = (WanosControllerViewModel) new ViewModelProvider((ViewModelStoreOwner) context).get(WanosControllerViewModel.class);
        this.mViewModel = wanosControllerViewModel;
        CustomZeroNormalActionBinding customZeroNormalActionBindingInflate = CustomZeroNormalActionBinding.inflate(LayoutInflater.from(context), this, true);
        this.mViewBinding = customZeroNormalActionBindingInflate;
        customZeroNormalActionBindingInflate.getRoot().setBackgroundColor(0);
        int dimension = (int) (getResources().getDimension(R.dimen.relax_title_merge_top) + BarUtils.getStatusBarHeight());
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) customZeroNormalActionBindingInflate.btnControlBack.getLayoutParams();
        layoutParams.topMargin = dimension;
        customZeroNormalActionBindingInflate.btnControlBack.setLayoutParams(layoutParams);
        switch (wanosControllerViewModel.getEditState()) {
            case 100:
                setIdleState();
                break;
            case 101:
                setPrepareState(wanosControllerViewModel.getDownTime());
                break;
            case 102:
                setRunningState(wanosControllerViewModel.getDownTime());
                break;
        }
        String[] meditationTimeData = wanosControllerViewModel.getMeditationTimeData();
        if (meditationTimeData != null) {
            customZeroNormalActionBindingInflate.wanosZeroTimeSelect.setMingXiangPickerData(meditationTimeData);
            customZeroNormalActionBindingInflate.wanosZeroTimeSelect.setDefaultSelectPosition(wanosControllerViewModel.getMeditationIndex());
        }
        customZeroNormalActionBindingInflate.btnControlBack.setOnClickListener(new CustomClick() { // from class: com.wanos.media.widget.controller.WanosZeroControlView.1
            @Override // com.wanos.media.util.CustomClick
            public void onAnitClick(View view) {
                if (WanosZeroControlView.this.mIControlCallback != null && WanosZeroControlView.this.mIControlCallback.onPageScrollState() == 0) {
                    WanosZeroControlView.this.mIControlCallback.onZeroBackClick();
                }
            }
        });
        customZeroNormalActionBindingInflate.btnControlPersonalize.setOnClickListener(new CustomClick() { // from class: com.wanos.media.widget.controller.WanosZeroControlView.2
            @Override // com.wanos.media.util.CustomClick
            public void onAnitClick(View view) {
                if (WanosZeroControlView.this.mIControlCallback == null || WanosZeroControlView.this.mIControlCallback.onPageScrollState() != 0 || WanosZeroControlView.this.mViewModel.getEditState() == 101 || WanosZeroControlView.this.mViewModel.relaxInfoButtonState.getValue() == RelaxInfoActionButton.State.LOADING || WanosZeroControlView.this.mViewModel.relaxInfoButtonState.getValue() == RelaxInfoActionButton.State.TIMING) {
                    return;
                }
                if (WanosZeroControlView.this.mViewModel.relaxInfoButtonState.getValue() != RelaxInfoActionButton.State.ERROR) {
                    WanosZeroControlView.this.mIControlCallback.onZeroPersonalizeClick(WanosZeroControlView.this.mViewModel.getZeroPageMode());
                } else {
                    ToastUtil.showMsg(R.string.relax_load_error);
                }
            }
        });
        customZeroNormalActionBindingInflate.btnAction.setOnClickListener(new CustomClick() { // from class: com.wanos.media.widget.controller.WanosZeroControlView.3
            @Override // com.wanos.media.util.CustomClick
            public void onAnitClick(View view) {
                if (WanosZeroControlView.this.mIControlCallback == null || WanosZeroControlView.this.mIControlCallback.onPageScrollState() != 0 || WanosZeroControlView.this.mViewModel.relaxInfoButtonState.getValue() == RelaxInfoActionButton.State.LOADING || WanosZeroControlView.this.mViewModel.relaxInfoButtonState.getValue() == RelaxInfoActionButton.State.TIMING) {
                    return;
                }
                if (WanosZeroControlView.this.mViewModel.relaxInfoButtonState.getValue() == RelaxInfoActionButton.State.ERROR) {
                    ToastUtil.showMsg(R.string.relax_load_error);
                    return;
                }
                switch (WanosZeroControlView.this.mViewModel.getEditState()) {
                    case 100:
                        WanosZeroControlView wanosZeroControlView = WanosZeroControlView.this;
                        wanosZeroControlView.setPrepareState(wanosZeroControlView.mViewBinding.wanosZeroTimeSelect.getCurrentTime());
                        break;
                    case 101:
                        WanosZeroControlView wanosZeroControlView2 = WanosZeroControlView.this;
                        wanosZeroControlView2.setRunningState(wanosZeroControlView2.mViewBinding.wanosZeroTimeSelect.getCurrentTime());
                        break;
                    case 102:
                        WanosZeroControlView.this.setIdleState();
                        break;
                }
            }
        });
        customZeroNormalActionBindingInflate.btnAction.setIActionCallback(new RelaxInfoActionButton.IActionCallback() { // from class: com.wanos.media.widget.controller.WanosZeroControlView$$ExternalSyntheticLambda0
            @Override // com.wanos.media.widget.RelaxInfoActionButton.IActionCallback
            public final void onTrialEnd() {
                this.f$0.m650x57c9d409();
            }
        });
        customZeroNormalActionBindingInflate.wanosZeroTimeSelect.setOnItemSelectedListener(new WanosZeroControlTimeSelectView.OnItemSelectedListener() { // from class: com.wanos.media.widget.controller.WanosZeroControlView$$ExternalSyntheticLambda4
            @Override // com.wanos.media.widget.controller.WanosZeroControlTimeSelectView.OnItemSelectedListener
            public final void onItemSelected(int i, int i2, WanosZeroControlTimeSelectAdapter.IItemStateCallback iItemStateCallback) {
                this.f$0.m651x590026e8(i, i2, iItemStateCallback);
            }
        });
        customZeroNormalActionBindingInflate.vMask.setOnClickListener(new CustomClick() { // from class: com.wanos.media.widget.controller.WanosZeroControlView.4
            @Override // com.wanos.media.util.CustomClick
            public void onAnitClick(View view) {
                WanosZeroControlView.this.setRelaxEditState(100);
            }
        });
        wanosControllerViewModel.relaxInfoButtonState.observe((LifecycleOwner) getContext(), new Observer<RelaxInfoActionButton.State>() { // from class: com.wanos.media.widget.controller.WanosZeroControlView.5
            @Override // androidx.lifecycle.Observer
            public void onChanged(RelaxInfoActionButton.State state) {
                ZeroLogcatTools.i(WanosZeroControlView.TAG, "onChanged: relaxInfoButtonState = " + state);
                WanosZeroControlView.this.mViewBinding.btnAction.setButtonState(state);
            }
        });
        customZeroNormalActionBindingInflate.btnOpenVip.setOnClickListener(new CustomClick() { // from class: com.wanos.media.widget.controller.WanosZeroControlView.6
            @Override // com.wanos.media.util.CustomClick
            public void onAnitClick(View view) {
                if (!LoginUtils.getInstance().isLogin()) {
                    LoginUtils.getInstance().showLoginDialog();
                } else if (!LoginUtils.getInstance().isVip()) {
                    LoginUtils.getInstance().showOpenVipDialog();
                } else {
                    ZeroLogcatTools.w(WanosZeroControlView.TAG, "OpenVipClickEventError");
                }
            }
        });
        customZeroNormalActionBindingInflate.btnSetting.setOnClickListener(new CustomClick() { // from class: com.wanos.media.widget.controller.WanosZeroControlView.7
            @Override // com.wanos.media.util.CustomClick
            public void onAnitClick(View view) {
                if (WanosZeroControlView.this.mIControlCallback != null) {
                    WanosZeroControlView.this.mIControlCallback.onSettingClick();
                }
            }
        });
        LiveData<Integer> liveData = wanosControllerViewModel.backButtonVisibilityState;
        LifecycleOwner lifecycleOwner = (LifecycleOwner) getContext();
        final AppCompatImageView appCompatImageView = customZeroNormalActionBindingInflate.btnControlBack;
        Objects.requireNonNull(appCompatImageView);
        liveData.observe(lifecycleOwner, new Observer() { // from class: com.wanos.media.widget.controller.WanosZeroControlView$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                appCompatImageView.setVisibility(((Integer) obj).intValue());
            }
        });
        LiveData<Integer> liveData2 = wanosControllerViewModel.titleTextVisibilityState;
        LifecycleOwner lifecycleOwner2 = (LifecycleOwner) getContext();
        final AppCompatTextView appCompatTextView = customZeroNormalActionBindingInflate.tvControlTitle;
        Objects.requireNonNull(appCompatTextView);
        liveData2.observe(lifecycleOwner2, new Observer() { // from class: com.wanos.media.widget.controller.WanosZeroControlView$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                appCompatTextView.setVisibility(((Integer) obj).intValue());
            }
        });
        LiveData<Integer> liveData3 = wanosControllerViewModel.vipFlagVisibilityState;
        LifecycleOwner lifecycleOwner3 = (LifecycleOwner) getContext();
        final AppCompatImageView appCompatImageView2 = customZeroNormalActionBindingInflate.ivVipFlag;
        Objects.requireNonNull(appCompatImageView2);
        liveData3.observe(lifecycleOwner3, new Observer() { // from class: com.wanos.media.widget.controller.WanosZeroControlView$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                appCompatImageView2.setVisibility(((Integer) obj).intValue());
            }
        });
        LiveData<Integer> liveData4 = wanosControllerViewModel.openVipBtnVisibilityState;
        LifecycleOwner lifecycleOwner4 = (LifecycleOwner) getContext();
        final LinearLayoutCompat linearLayoutCompat = customZeroNormalActionBindingInflate.btnOpenVip;
        Objects.requireNonNull(linearLayoutCompat);
        liveData4.observe(lifecycleOwner4, new Observer() { // from class: com.wanos.media.widget.controller.WanosZeroControlView$$ExternalSyntheticLambda6
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                linearLayoutCompat.setVisibility(((Integer) obj).intValue());
            }
        });
        LiveData<Integer> liveData5 = wanosControllerViewModel.relaxActionBtnVisibilityState;
        LifecycleOwner lifecycleOwner5 = (LifecycleOwner) getContext();
        final RelaxInfoActionButton relaxInfoActionButton = customZeroNormalActionBindingInflate.btnAction;
        Objects.requireNonNull(relaxInfoActionButton);
        liveData5.observe(lifecycleOwner5, new Observer() { // from class: com.wanos.media.widget.controller.WanosZeroControlView$$ExternalSyntheticLambda7
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                relaxInfoActionButton.setVisibility(((Integer) obj).intValue());
            }
        });
        LiveData<Integer> liveData6 = wanosControllerViewModel.openEditBtnVisibilityState;
        LifecycleOwner lifecycleOwner6 = (LifecycleOwner) getContext();
        final LinearLayoutCompat linearLayoutCompat2 = customZeroNormalActionBindingInflate.btnControlPersonalize;
        Objects.requireNonNull(linearLayoutCompat2);
        liveData6.observe(lifecycleOwner6, new Observer() { // from class: com.wanos.media.widget.controller.WanosZeroControlView$$ExternalSyntheticLambda6
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                linearLayoutCompat2.setVisibility(((Integer) obj).intValue());
            }
        });
        LiveData<Integer> liveData7 = wanosControllerViewModel.countdownTextVisibilityState;
        LifecycleOwner lifecycleOwner7 = (LifecycleOwner) getContext();
        final WanosTextView wanosTextView = customZeroNormalActionBindingInflate.tvCountDownTime;
        Objects.requireNonNull(wanosTextView);
        liveData7.observe(lifecycleOwner7, new Observer() { // from class: com.wanos.media.widget.controller.WanosZeroControlView$$ExternalSyntheticLambda8
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                wanosTextView.setVisibility(((Integer) obj).intValue());
            }
        });
        LiveData<Integer> liveData8 = wanosControllerViewModel.clockViewVisibilityState;
        LifecycleOwner lifecycleOwner8 = (LifecycleOwner) getContext();
        final WanosClockView wanosClockView = customZeroNormalActionBindingInflate.clockView;
        Objects.requireNonNull(wanosClockView);
        liveData8.observe(lifecycleOwner8, new Observer() { // from class: com.wanos.media.widget.controller.WanosZeroControlView$$ExternalSyntheticLambda9
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                wanosClockView.setVisibility(((Integer) obj).intValue());
            }
        });
        LiveData<Integer> liveData9 = wanosControllerViewModel.timePickerVisibilityState;
        LifecycleOwner lifecycleOwner9 = (LifecycleOwner) getContext();
        final WanosZeroControlTimeSelectView wanosZeroControlTimeSelectView = customZeroNormalActionBindingInflate.wanosZeroTimeSelect;
        Objects.requireNonNull(wanosZeroControlTimeSelectView);
        liveData9.observe(lifecycleOwner9, new Observer() { // from class: com.wanos.media.widget.controller.WanosZeroControlView$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                wanosZeroControlTimeSelectView.setVisibility(((Integer) obj).intValue());
            }
        });
        LiveData<Integer> liveData10 = wanosControllerViewModel.timePickerMaskVisibilityState;
        LifecycleOwner lifecycleOwner10 = (LifecycleOwner) getContext();
        final View view = customZeroNormalActionBindingInflate.vMask;
        Objects.requireNonNull(view);
        liveData10.observe(lifecycleOwner10, new Observer() { // from class: com.wanos.media.widget.controller.WanosZeroControlView$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                view.setVisibility(((Integer) obj).intValue());
            }
        });
        LiveData<Integer> liveData11 = wanosControllerViewModel.settingBtnVisibilityState;
        LifecycleOwner lifecycleOwner11 = (LifecycleOwner) getContext();
        final AppCompatImageView appCompatImageView3 = customZeroNormalActionBindingInflate.btnSetting;
        Objects.requireNonNull(appCompatImageView3);
        liveData11.observe(lifecycleOwner11, new Observer() { // from class: com.wanos.media.widget.controller.WanosZeroControlView$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                appCompatImageView3.setVisibility(((Integer) obj).intValue());
            }
        });
    }

    /* JADX INFO: renamed from: lambda$new$0$com-wanos-media-widget-controller-WanosZeroControlView, reason: not valid java name */
    /* synthetic */ void m650x57c9d409() {
        IControlCallback iControlCallback = this.mIControlCallback;
        if (iControlCallback != null) {
            iControlCallback.onTrialEnd();
        }
    }

    /* JADX INFO: renamed from: lambda$new$1$com-wanos-media-widget-controller-WanosZeroControlView, reason: not valid java name */
    /* synthetic */ void m651x590026e8(int i, int i2, WanosZeroControlTimeSelectAdapter.IItemStateCallback iItemStateCallback) {
        ZeroLogcatTools.d(TAG, "onItemSelected: 冥想选择时长，position = " + i + ", time = " + i2);
        this.mViewModel.setMeditationIndex(i);
        if (this.mIControlCallback == null) {
            ZeroLogcatTools.d(TAG, "onItemSelected: mIControlCallback == null");
            return;
        }
        if (this.mViewModel.getZeroPageMode() == ZeroPageMode.MING_XIANG_STANDARD || this.mViewModel.getZeroPageMode() == ZeroPageMode.MING_XIANG_PRO) {
            ZeroAudioBallTools.getInstance().onMediaReset();
        }
        this.mIControlCallback.onRelaxTimeSelect(i, i2, iItemStateCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPrepareState(int i) {
        if (ZeroPageMode.MING_XIANG_PRO == this.mViewModel.getZeroPageMode() || ZeroPageMode.MING_XIANG_STANDARD == this.mViewModel.getZeroPageMode()) {
            if (this.mViewBinding.wanosZeroTimeSelect.getItemState()) {
                return;
            }
            setRelaxEditState(102);
            this.mViewModel.openCountDownTimer(this, i);
            IControlCallback iControlCallback = this.mIControlCallback;
            if (iControlCallback == null) {
                return;
            }
            iControlCallback.onEnterRelaxState();
            return;
        }
        setRelaxEditState(101);
    }

    public LiveData<Integer> getEditStateLiveData() {
        return this.mViewModel.getEditStateLiveData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRunningState(int i) {
        setRelaxEditState(102);
        this.mViewModel.openCountDownTimer(this, i);
        IControlCallback iControlCallback = this.mIControlCallback;
        if (iControlCallback == null) {
            return;
        }
        iControlCallback.onEnterRelaxState();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIdleState() {
        setRelaxEditState(100);
        this.mViewModel.closeCountDownTimer();
        IControlCallback iControlCallback = this.mIControlCallback;
        if (iControlCallback == null) {
            return;
        }
        iControlCallback.onCancelRelaxState();
    }

    public void setStopCountdownTime() {
        setRelaxEditState(100);
        this.mViewModel.closeCountDownTimer();
    }

    public void setMeEnterState(boolean z) {
        this.mViewModel.setMeEnter(z);
    }

    public void setZeroPageMode(ZeroPageMode zeroPageMode) {
        ZeroLogcatTools.d(TAG, "setZeroPageMode: " + zeroPageMode + " editState: " + this.mViewModel.getEditState());
        this.mViewModel.setZeroPageMode(zeroPageMode);
        this.mViewBinding.wanosZeroTimeSelect.setThemeMode(zeroPageMode);
        setStartButtonDrawable(this.mViewModel.getZeroPageMode());
        setStartButtonText(this.mViewModel.getZeroPageMode(), this.mViewModel.getEditState());
        if (this.mViewModel.getZeroPageMode() == ZeroPageMode.XIAO_QI_STANDARD || this.mViewModel.getZeroPageMode() == ZeroPageMode.XIAO_QI_PRO) {
            setXiaoQiPickerData();
        }
    }

    public void setMingXiangPickerData(String[] strArr) {
        this.mViewModel.setMeditationTimeData(strArr);
        String[] meditationTimeData = this.mViewModel.getMeditationTimeData();
        if (meditationTimeData == null) {
            return;
        }
        this.mViewBinding.wanosZeroTimeSelect.setMingXiangPickerData(meditationTimeData);
    }

    public void setXiaoQiPickerData() {
        this.mViewBinding.wanosZeroTimeSelect.setXiaoQiPickerData();
    }

    public void setControlCallback(IControlCallback iControlCallback) {
        this.mIControlCallback = iControlCallback;
    }

    public void setRelaxInfoActionButtonState(RelaxInfoActionButton.State state) {
        this.mViewModel.setRelaxInfoButtonState(state);
    }

    public RelaxInfoActionButton.State getRelaxInfoActionButtonState() {
        return this.mViewModel.relaxInfoButtonState.getValue();
    }

    public boolean isRelaxRunning() {
        return this.mViewModel.getEditState() == 102;
    }

    public void setTitleText(String str, boolean z) {
        this.mViewModel.setVipState(z);
        ZeroLogcatTools.i(TAG, "setTitleText: title = " + str + ", isVipTheme = " + z);
        if (str == null) {
            return;
        }
        this.mViewBinding.tvControlTitle.setText(str);
    }

    private void setStartButtonDrawable(ZeroPageMode zeroPageMode) {
        int i;
        if (zeroPageMode == ZeroPageMode.MING_XIANG_STANDARD || zeroPageMode == ZeroPageMode.MING_XIANG_PRO) {
            i = R.drawable.ic_ming_xiang;
        } else {
            i = (zeroPageMode == ZeroPageMode.XIAO_QI_STANDARD || zeroPageMode == ZeroPageMode.XIAO_QI_PRO) ? R.drawable.icon_personalize_start : -1;
        }
        if (i == -1) {
            this.mViewBinding.btnAction.setNormalButtonIcon(i);
        } else {
            ZeroLogcatTools.w(TAG, "setStartButtonDrawable: ZeroPageMode = " + zeroPageMode);
        }
    }

    private void setStartButtonText(ZeroPageMode zeroPageMode, int i) {
        String string;
        if (zeroPageMode == ZeroPageMode.MING_XIANG_STANDARD || zeroPageMode == ZeroPageMode.MING_XIANG_PRO) {
            if (i == 102) {
                string = getResources().getString(R.string.zero_meditation_btn_end);
            } else {
                string = getResources().getString(R.string.zero_meditation_btn_start);
            }
        } else if (zeroPageMode != ZeroPageMode.XIAO_QI_STANDARD && zeroPageMode != ZeroPageMode.XIAO_QI_PRO) {
            string = "";
        } else if (i == 102) {
            string = getResources().getString(R.string.zero_personalize_btn_end);
        } else {
            string = getResources().getString(R.string.zero_personalize_btn_start);
        }
        if (TextUtils.isEmpty(string)) {
            ZeroLogcatTools.e(TAG, "setStartButtonText: 开始按钮图标设置失败，ZeroPageMode = " + zeroPageMode + ", EditState = " + i);
        } else {
            this.mViewBinding.btnAction.setNormalButtonText(string);
        }
    }

    public void setRelaxEditState(int i) {
        this.mViewModel.setEditState(i);
        switch (i) {
            case 100:
                setStartButtonText(this.mViewModel.getZeroPageMode(), i);
                break;
            case 101:
                setStartButtonText(this.mViewModel.getZeroPageMode(), i);
                break;
            case 102:
                setStartButtonText(this.mViewModel.getZeroPageMode(), i);
                break;
        }
    }

    public void setImmerseState(boolean z) {
        this.mViewModel.setImmerseState(z);
    }

    public void setCountDownTime(String str) {
        this.mViewBinding.tvCountDownTime.setText(str);
    }

    public IControlCallback getIControlCallback() {
        return this.mIControlCallback;
    }

    public void setDownTime(int i) {
        this.mViewModel.setDownTime(i);
    }
}
