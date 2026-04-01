package com.wanos.media.widget.controller;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.wanos.media.entity.ZeroPageMode;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.widget.WanosTimePicker;
import com.wanos.media.widget.controller.WanosZeroControlTimeSelectAdapter;
import com.wanos.media.zero_p.R;
import com.wanos.media.zero_p.databinding.WidgetControlTimeSelectBinding;

/* JADX INFO: loaded from: classes3.dex */
public class WanosZeroControlTimeSelectView extends FrameLayout {
    private static final String TAG = "WanosZeroControlTimeSel";
    private WanosZeroControlTimeSelectAdapter mAdapter;
    private OnItemSelectedListener mOnItemSelectedListener;
    private WidgetControlTimeSelectBinding mViewBinding;
    private ZeroPageMode mZeroPageMode;

    public interface OnItemSelectedListener {
        void onItemSelected(int i, int i2, WanosZeroControlTimeSelectAdapter.IItemStateCallback iItemStateCallback);
    }

    static /* synthetic */ void lambda$new$0(View view) {
    }

    static /* synthetic */ void lambda$new$1(View view) {
    }

    public WanosZeroControlTimeSelectView(Context context) {
        this(context, null);
    }

    public WanosZeroControlTimeSelectView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WanosZeroControlTimeSelectView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        WidgetControlTimeSelectBinding widgetControlTimeSelectBindingInflate = WidgetControlTimeSelectBinding.inflate(LayoutInflater.from(context), this, true);
        this.mViewBinding = widgetControlTimeSelectBindingInflate;
        widgetControlTimeSelectBindingInflate.tvFocus1.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.widget.controller.WanosZeroControlTimeSelectView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WanosZeroControlTimeSelectView.lambda$new$0(view);
            }
        });
        this.mViewBinding.tvFocus2.setOnClickListener(new View.OnClickListener() { // from class: com.wanos.media.widget.controller.WanosZeroControlTimeSelectView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WanosZeroControlTimeSelectView.lambda$new$1(view);
            }
        });
    }

    void setThemeMode(ZeroPageMode zeroPageMode) {
        ZeroLogcatTools.d(TAG, "setThemeMode: ZeroPageMode = " + zeroPageMode);
        this.mZeroPageMode = zeroPageMode;
        int i = AnonymousClass1.$SwitchMap$com$wanos$media$entity$ZeroPageMode[zeroPageMode.ordinal()];
        if (i == 1 || i == 2) {
            if (this.mViewBinding.flNumberPicker.getVisibility() != 0) {
                this.mViewBinding.flNumberPicker.setVisibility(0);
            }
            this.mViewBinding.rvMingXiang.setVisibility(8);
        } else if (i == 3) {
            if (this.mViewBinding.flNumberPicker.getVisibility() != 8) {
                this.mViewBinding.flNumberPicker.setVisibility(8);
            }
            this.mViewBinding.rvMingXiang.setVisibility(0);
        } else {
            if (i != 4) {
                return;
            }
            if (this.mViewBinding.flNumberPicker.getVisibility() != 8) {
                this.mViewBinding.flNumberPicker.setVisibility(8);
            }
            this.mViewBinding.rvMingXiang.setVisibility(8);
        }
    }

    /* JADX INFO: renamed from: com.wanos.media.widget.controller.WanosZeroControlTimeSelectView$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$wanos$media$entity$ZeroPageMode;

        static {
            int[] iArr = new int[ZeroPageMode.values().length];
            $SwitchMap$com$wanos$media$entity$ZeroPageMode = iArr;
            try {
                iArr[ZeroPageMode.XIAO_QI_STANDARD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$wanos$media$entity$ZeroPageMode[ZeroPageMode.XIAO_QI_PRO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$wanos$media$entity$ZeroPageMode[ZeroPageMode.MING_XIANG_STANDARD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$wanos$media$entity$ZeroPageMode[ZeroPageMode.MING_XIANG_PRO.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    void setMingXiangPickerData(String[] strArr) {
        if (ZeroPageMode.XIAO_QI_STANDARD == this.mZeroPageMode || ZeroPageMode.XIAO_QI_PRO == this.mZeroPageMode) {
            ZeroLogcatTools.e(TAG, "setMingXiangPickerData: ZeroPageMode = " + this.mZeroPageMode);
            return;
        }
        ZeroLogcatTools.d(TAG, "setMingXiangPickerData: data = " + strArr);
        this.mAdapter = new WanosZeroControlTimeSelectAdapter(strArr);
        this.mViewBinding.rvMingXiang.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        this.mViewBinding.rvMingXiang.setAdapter(this.mAdapter);
        this.mAdapter.onItemLongClick(new WanosZeroControlTimeSelectAdapter.OnItemClickListener() { // from class: com.wanos.media.widget.controller.WanosZeroControlTimeSelectView$$ExternalSyntheticLambda2
            @Override // com.wanos.media.widget.controller.WanosZeroControlTimeSelectAdapter.OnItemClickListener
            public final void onItemClick(int i, int i2, WanosZeroControlTimeSelectAdapter.IItemStateCallback iItemStateCallback) {
                this.f$0.m646x9a2471ac(i, i2, iItemStateCallback);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$setMingXiangPickerData$2$com-wanos-media-widget-controller-WanosZeroControlTimeSelectView, reason: not valid java name */
    /* synthetic */ void m646x9a2471ac(int i, int i2, WanosZeroControlTimeSelectAdapter.IItemStateCallback iItemStateCallback) {
        ZeroLogcatTools.d(TAG, "setMingXiangPickerData: onItemLongClick: position = " + i + ", time = " + i2 + ", iItemStateCallback = " + iItemStateCallback);
        OnItemSelectedListener onItemSelectedListener = this.mOnItemSelectedListener;
        if (onItemSelectedListener == null) {
            return;
        }
        onItemSelectedListener.onItemSelected(i, i2, iItemStateCallback);
    }

    void setDefaultSelectPosition(int i) {
        WanosZeroControlTimeSelectAdapter wanosZeroControlTimeSelectAdapter = this.mAdapter;
        if (wanosZeroControlTimeSelectAdapter == null) {
            ZeroLogcatTools.e(TAG, "setDefaultSelectPosition: mAdapter is null");
        } else {
            wanosZeroControlTimeSelectAdapter.setDefaultPosition(i);
        }
    }

    void setXiaoQiPickerData() {
        ZeroLogcatTools.d(TAG, "setXiaoQiPickerData: ZeroPageMode = " + this.mZeroPageMode + ", Values" + this.mViewBinding.numberPicker.getDisplayedValues().length);
        if (ZeroPageMode.MING_XIANG_STANDARD == this.mZeroPageMode || ZeroPageMode.MING_XIANG_PRO == this.mZeroPageMode) {
            ZeroLogcatTools.e(TAG, "setXiaoQiPickerData: ZeroPageMode = " + this.mZeroPageMode);
            return;
        }
        if (this.mViewBinding.numberPicker.getRawContentSize() <= 1) {
            CharSequence[] textArray = getResources().getTextArray(R.array.min_time_array);
            String[] strArr = new String[textArray.length];
            for (int i = 0; i < textArray.length; i++) {
                strArr[i] = textArray[i].toString();
            }
            this.mViewBinding.numberPicker.setDisplayedValuesAndPickedIndex(strArr, 110, true);
            this.mViewBinding.numberPicker.setOnValueChangedListener(new WanosTimePicker.OnValueChangeListener() { // from class: com.wanos.media.widget.controller.WanosZeroControlTimeSelectView$$ExternalSyntheticLambda3
                @Override // com.wanos.media.widget.WanosTimePicker.OnValueChangeListener
                public final void onValueChange(WanosTimePicker wanosTimePicker, int i2, int i3) {
                    this.f$0.m647x9e41049e(wanosTimePicker, i2, i3);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$setXiaoQiPickerData$3$com-wanos-media-widget-controller-WanosZeroControlTimeSelectView, reason: not valid java name */
    /* synthetic */ void m647x9e41049e(WanosTimePicker wanosTimePicker, int i, int i2) {
        if (this.mOnItemSelectedListener == null) {
            return;
        }
        try {
            int value = wanosTimePicker.getValue();
            this.mOnItemSelectedListener.onItemSelected(value, Integer.parseInt(wanosTimePicker.getDisplayedValues()[value]), null);
        } catch (Exception e) {
            ZeroLogcatTools.e(TAG, "setXiaoQiPickerData: " + e.getMessage());
        }
    }

    int getCurrentTime() {
        if (ZeroPageMode.XIAO_QI_PRO == this.mZeroPageMode || ZeroPageMode.XIAO_QI_STANDARD == this.mZeroPageMode) {
            try {
                return Integer.parseInt(this.mViewBinding.numberPicker.getContentByCurrValue());
            } catch (Exception unused) {
                return 5;
            }
        }
        if (ZeroPageMode.MING_XIANG_PRO != this.mZeroPageMode && ZeroPageMode.MING_XIANG_STANDARD != this.mZeroPageMode) {
            return 5;
        }
        RecyclerView.Adapter adapter = this.mViewBinding.rvMingXiang.getAdapter();
        if (adapter instanceof WanosZeroControlTimeSelectAdapter) {
            return ((WanosZeroControlTimeSelectAdapter) adapter).getSelectTime();
        }
        return 5;
    }

    void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.mOnItemSelectedListener = onItemSelectedListener;
    }

    boolean getItemState() {
        WanosZeroControlTimeSelectAdapter wanosZeroControlTimeSelectAdapter;
        if ((ZeroPageMode.MING_XIANG_PRO == this.mZeroPageMode || ZeroPageMode.MING_XIANG_STANDARD == this.mZeroPageMode) && (wanosZeroControlTimeSelectAdapter = this.mAdapter) != null) {
            return wanosZeroControlTimeSelectAdapter.getItemLoadState();
        }
        return false;
    }
}
