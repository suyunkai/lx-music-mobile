package com.wanos.media.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatTextView;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public class WanosTextView extends AppCompatTextView {
    private boolean isFakeBold;
    private int mTextWeight;

    public WanosTextView(Context context) {
        super(context);
        this.mTextWeight = -1;
        this.isFakeBold = false;
    }

    public WanosTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTextWeight = -1;
        this.isFakeBold = false;
        initAttrs(context, attributeSet);
    }

    public WanosTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mTextWeight = -1;
        this.isFakeBold = false;
        initAttrs(context, attributeSet);
    }

    private void initAttrs(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.WanosTextView);
        this.mTextWeight = typedArrayObtainStyledAttributes.getInt(R.styleable.WanosTextView_wanos_weight, this.mTextWeight);
        this.isFakeBold = typedArrayObtainStyledAttributes.getBoolean(R.styleable.WanosTextView_wanos_fake_bold, this.isFakeBold);
        typedArrayObtainStyledAttributes.recycle();
        setTextWeight(this.mTextWeight);
        setFakeBold(this.isFakeBold);
    }

    public void setTextWeight(int i) {
        if (i == -1) {
            return;
        }
        this.mTextWeight = i;
        setTypeface(Typeface.create(Typeface.DEFAULT, this.mTextWeight, false));
    }

    public void setFakeBold(boolean z) {
        this.isFakeBold = z;
        getPaint().setFakeBoldText(this.isFakeBold);
    }

    @Override // android.widget.TextView, android.view.View
    public Parcelable onSaveInstanceState() {
        WanosTextViewState wanosTextViewState = new WanosTextViewState(super.onSaveInstanceState());
        wanosTextViewState.isFakeBold = this.isFakeBold;
        wanosTextViewState.textWeight = this.mTextWeight;
        return wanosTextViewState;
    }

    @Override // android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof WanosTextViewState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        WanosTextViewState wanosTextViewState = (WanosTextViewState) parcelable;
        super.onRestoreInstanceState(wanosTextViewState.getSuperState());
        setFakeBold(wanosTextViewState.isFakeBold);
        setTextWeight(wanosTextViewState.textWeight);
    }

    public static class WanosTextViewState extends View.BaseSavedState {
        public static final Parcelable.Creator<WanosTextViewState> CREATOR = new Parcelable.Creator<WanosTextViewState>() { // from class: com.wanos.media.ui.widget.WanosTextView.WanosTextViewState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public WanosTextViewState createFromParcel(Parcel parcel) {
                return new WanosTextViewState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public WanosTextViewState[] newArray(int i) {
                return new WanosTextViewState[i];
            }
        };
        boolean isFakeBold;
        int textWeight;

        public WanosTextViewState(Parcel parcel) {
            super(parcel);
            this.isFakeBold = parcel.readInt() != 0;
            this.textWeight = parcel.readInt();
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.isFakeBold ? 1 : 0);
            parcel.writeInt(this.textWeight);
        }

        public WanosTextViewState(Parcelable parcelable) {
            super(parcelable);
        }
    }
}
