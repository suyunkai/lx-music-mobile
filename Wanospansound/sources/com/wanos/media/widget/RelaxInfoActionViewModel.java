package com.wanos.media.widget;

import android.animation.ValueAnimator;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.widget.RelaxInfoActionButton;

/* JADX INFO: loaded from: classes3.dex */
public class RelaxInfoActionViewModel extends ViewModel {
    private static final int DURATION = 20000;
    public static final int MAX_PROGRESS = 200;
    private static final String TAG = "RelaxInfoActionViewMode";
    private final MutableLiveData<RelaxInfoActionButton.State> _ButtonState;
    private final MutableLiveData<Integer> _Progress;
    public final LiveData<RelaxInfoActionButton.State> buttonState;
    private final ValueAnimator.AnimatorUpdateListener mTrialCallback;
    public final LiveData<Integer> progress;
    private ValueAnimator valueAnimator;

    public RelaxInfoActionViewModel() {
        MutableLiveData<RelaxInfoActionButton.State> mutableLiveData = new MutableLiveData<>(RelaxInfoActionButton.State.LOADING);
        this._ButtonState = mutableLiveData;
        this.buttonState = mutableLiveData;
        MutableLiveData<Integer> mutableLiveData2 = new MutableLiveData<>(0);
        this._Progress = mutableLiveData2;
        this.progress = mutableLiveData2;
        this.valueAnimator = null;
        this.mTrialCallback = new ValueAnimator.AnimatorUpdateListener() { // from class: com.wanos.media.widget.RelaxInfoActionViewModel.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int iIntValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                RelaxInfoActionViewModel.this._Progress.setValue(Integer.valueOf(iIntValue));
                if (iIntValue == 200) {
                    RelaxInfoActionViewModel.this.resetAnimator();
                }
            }
        };
    }

    public void setButtonState(RelaxInfoActionButton.State state) {
        ZeroLogcatTools.d(TAG, "setButtonState: state = " + state + ", current_state = " + this.buttonState.getValue());
        if (state != this.buttonState.getValue()) {
            this._ButtonState.setValue(state);
            if (state == RelaxInfoActionButton.State.TIMING) {
                ValueAnimator valueAnimator = new ValueAnimator();
                this.valueAnimator = valueAnimator;
                valueAnimator.setDuration(20000L);
                this.valueAnimator.setIntValues(0, 200);
                this.valueAnimator.addUpdateListener(this.mTrialCallback);
                this.valueAnimator.start();
                return;
            }
            resetAnimator();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetAnimator() {
        this._Progress.setValue(0);
        ValueAnimator valueAnimator = this.valueAnimator;
        if (valueAnimator != null) {
            valueAnimator.removeUpdateListener(this.mTrialCallback);
            this.valueAnimator.cancel();
            this.valueAnimator = null;
        }
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        resetAnimator();
    }
}
