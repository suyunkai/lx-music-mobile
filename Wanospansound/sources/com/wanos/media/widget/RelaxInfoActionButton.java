package com.wanos.media.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.widget.RelaxInfoActionButton;
import com.wanos.media.zero_p.databinding.WidgetRelaxInfoActionButtonBinding;

/* JADX INFO: loaded from: classes3.dex */
public class RelaxInfoActionButton extends FrameLayout {
    private static final String TAG = "RelaxInfoActionButton";
    private final WidgetRelaxInfoActionButtonBinding binding;
    private IActionCallback iActionCallback;
    private final Observer<Integer> mProgressObserver;
    private final RelaxInfoActionViewModel viewModel;

    public interface IActionCallback {
        void onTrialEnd();
    }

    public enum State {
        LOADING,
        TIMING,
        NORMAL,
        ERROR
    }

    public RelaxInfoActionButton(Context context) {
        this(context, null);
    }

    public RelaxInfoActionButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public RelaxInfoActionButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        WidgetRelaxInfoActionButtonBinding widgetRelaxInfoActionButtonBindingInflate = WidgetRelaxInfoActionButtonBinding.inflate(LayoutInflater.from(context), this, true);
        this.binding = widgetRelaxInfoActionButtonBindingInflate;
        RelaxInfoActionViewModel relaxInfoActionViewModel = (RelaxInfoActionViewModel) new ViewModelProvider((ViewModelStoreOwner) context).get(RelaxInfoActionViewModel.class);
        this.viewModel = relaxInfoActionViewModel;
        widgetRelaxInfoActionButtonBindingInflate.progressTrial.setMax(200);
        this.mProgressObserver = new Observer() { // from class: com.wanos.media.widget.RelaxInfoActionButton$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m605lambda$new$0$comwanosmediawidgetRelaxInfoActionButton((Integer) obj);
            }
        };
        relaxInfoActionViewModel.buttonState.observe((LifecycleOwner) context, new Observer() { // from class: com.wanos.media.widget.RelaxInfoActionButton$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m606lambda$new$1$comwanosmediawidgetRelaxInfoActionButton((RelaxInfoActionButton.State) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$new$0$com-wanos-media-widget-RelaxInfoActionButton, reason: not valid java name */
    /* synthetic */ void m605lambda$new$0$comwanosmediawidgetRelaxInfoActionButton(Integer num) {
        IActionCallback iActionCallback;
        this.binding.progressTrial.setProgress(num.intValue());
        if (200 != num.intValue() || (iActionCallback = this.iActionCallback) == null) {
            return;
        }
        iActionCallback.onTrialEnd();
    }

    /* JADX INFO: renamed from: com.wanos.media.widget.RelaxInfoActionButton$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$wanos$media$widget$RelaxInfoActionButton$State;

        static {
            int[] iArr = new int[State.values().length];
            $SwitchMap$com$wanos$media$widget$RelaxInfoActionButton$State = iArr;
            try {
                iArr[State.LOADING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$wanos$media$widget$RelaxInfoActionButton$State[State.TIMING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$wanos$media$widget$RelaxInfoActionButton$State[State.NORMAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$wanos$media$widget$RelaxInfoActionButton$State[State.ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: renamed from: lambda$new$1$com-wanos-media-widget-RelaxInfoActionButton, reason: not valid java name */
    /* synthetic */ void m606lambda$new$1$comwanosmediawidgetRelaxInfoActionButton(State state) {
        int i = AnonymousClass1.$SwitchMap$com$wanos$media$widget$RelaxInfoActionButton$State[state.ordinal()];
        if (i == 1) {
            this.binding.layoutLoading.setVisibility(0);
            this.binding.layoutTrial.setVisibility(8);
            this.binding.layoutNormal.setVisibility(8);
        } else if (i == 2) {
            this.binding.layoutLoading.setVisibility(8);
            this.binding.layoutTrial.setVisibility(0);
            this.binding.layoutNormal.setVisibility(8);
        } else if (i == 3 || i == 4) {
            this.binding.layoutLoading.setVisibility(8);
            this.binding.layoutTrial.setVisibility(8);
            this.binding.layoutNormal.setVisibility(0);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.viewModel.progress.observeForever(this.mProgressObserver);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mProgressObserver != null) {
            this.viewModel.progress.removeObserver(this.mProgressObserver);
        }
    }

    public void setButtonState(State state) {
        this.viewModel.setButtonState(state);
    }

    public void setNormalButtonText(String str) {
        if (this.binding.layoutNormal.getVisibility() == 0) {
            this.binding.tvTitle.setText(str);
        } else {
            ZeroLogcatTools.w(TAG, "setNormalButtonInfo: 按钮状态异常");
        }
    }

    public void setNormalButtonIcon(int i) {
        if (this.binding.layoutNormal.getVisibility() == 0) {
            this.binding.ivIcon.setImageResource(i);
        } else {
            ZeroLogcatTools.w(TAG, "setNormalButtonInfo: 按钮状态异常");
        }
    }

    public void setIActionCallback(IActionCallback iActionCallback) {
        this.iActionCallback = iActionCallback;
    }
}
