package com.wanos.media.widget;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.widget.AppCompatEditText;
import com.blankj.utilcode.util.KeyboardUtils;
import com.wanos.media.zero_p.R;

/* JADX INFO: loaded from: classes3.dex */
public class WanosVerificationView extends FrameLayout {
    private static final String TAG = "wanos[WanosVerificationView]";
    private final AppCompatEditText mEditText;
    private IInputStateCallback mIInputStateCallback;
    private final WanosTextView[] mTextViews;
    private final TextWatcher mTextWatcher;

    public interface IInputStateCallback {
        default void onInputChange(String str) {
        }

        void onInputDone(String str);
    }

    public WanosVerificationView(Context context) {
        this(context, null);
    }

    public WanosVerificationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void hideSoftInput() {
        KeyboardUtils.hideSoftInput(this.mEditText);
    }

    public WanosVerificationView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mTextViews = new WanosTextView[]{(WanosTextView) findViewById(R.id.tv_input_1), (WanosTextView) findViewById(R.id.tv_input_2), (WanosTextView) findViewById(R.id.tv_input_3), (WanosTextView) findViewById(R.id.tv_input_4), (WanosTextView) findViewById(R.id.tv_input_5), (WanosTextView) findViewById(R.id.tv_input_6)};
        this.mTextWatcher = new TextWatcher() { // from class: com.wanos.media.widget.WanosVerificationView.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                WanosVerificationView.this.setInputText(editable.toString());
                if (editable.length() >= 6) {
                    WanosVerificationView.this.hideSoftInput();
                    if (WanosVerificationView.this.mIInputStateCallback != null) {
                        WanosVerificationView.this.mIInputStateCallback.onInputDone(editable.toString());
                    }
                }
                if (WanosVerificationView.this.mIInputStateCallback != null) {
                    WanosVerificationView.this.mIInputStateCallback.onInputChange(editable.toString());
                }
            }
        };
        LayoutInflater.from(context).inflate(R.layout.view_verification, (ViewGroup) this, true);
        AppCompatEditText appCompatEditText = (AppCompatEditText) findViewById(R.id.et_input);
        this.mEditText = appCompatEditText;
        appCompatEditText.setCursorVisible(false);
    }

    public void setInputStateCallback(IInputStateCallback iInputStateCallback) {
        this.mIInputStateCallback = iInputStateCallback;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mEditText.removeTextChangedListener(this.mTextWatcher);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mEditText.addTextChangedListener(this.mTextWatcher);
    }

    public void setText(String str) {
        this.mEditText.setText(str);
        setInputText(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setInputText(String str) {
        if (str == null) {
            return;
        }
        int length = str.length();
        int i = 0;
        while (true) {
            WanosTextView[] wanosTextViewArr = this.mTextViews;
            if (i >= wanosTextViewArr.length) {
                return;
            }
            WanosTextView wanosTextView = wanosTextViewArr[i];
            if (i < length) {
                wanosTextView.setText(String.valueOf(str.charAt(i)));
            } else {
                wanosTextView.setText("");
            }
            i++;
        }
    }

    public String getInputText() {
        Editable text = this.mEditText.getText();
        return text == null ? "" : text.toString().trim();
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.mEditText.setEnabled(z);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        WanosVerification wanosVerification = new WanosVerification(super.onSaveInstanceState());
        wanosVerification.inputString = getInputText();
        return wanosVerification;
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof WanosVerification)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        WanosVerification wanosVerification = (WanosVerification) parcelable;
        super.onRestoreInstanceState(wanosVerification.getSuperState());
        setInputText(wanosVerification.inputString);
    }

    private static class WanosVerification extends View.BaseSavedState {
        public static final Parcelable.Creator<WanosVerification> CREATOR = new Parcelable.Creator<WanosVerification>() { // from class: com.wanos.media.widget.WanosVerificationView.WanosVerification.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public WanosVerification createFromParcel(Parcel parcel) {
                return new WanosVerification(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public WanosVerification[] newArray(int i) {
                return new WanosVerification[i];
            }
        };
        String inputString;

        public WanosVerification(Parcel parcel) {
            super(parcel);
            this.inputString = parcel.readString();
        }

        public WanosVerification(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.inputString);
        }
    }
}
