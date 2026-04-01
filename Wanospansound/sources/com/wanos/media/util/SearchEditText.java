package com.wanos.media.util;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.res.ResourcesCompat;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public class SearchEditText extends AppCompatEditText {
    private static final int DRAWABLE_BOTTOM = 3;
    private static final int DRAWABLE_LEFT = 0;
    private static final int DRAWABLE_RIGHT = 2;
    private static final int DRAWABLE_TOP = 1;
    private Boolean clickable;
    private OnClickClearListener listener;
    private Drawable mClearDrawable;
    private int mClearIcon;
    private Drawable mSearchDrawable;
    private int mSearchIcon;

    public interface OnClickClearListener {
        void onClear();

        default void onDispatchKeyEventPreIme() {
        }
    }

    public SearchEditText(Context context) {
        super(context);
        this.clickable = true;
        this.mClearIcon = 0;
        this.mSearchIcon = 0;
        init();
    }

    public SearchEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.clickable = true;
        this.mClearIcon = 0;
        this.mSearchIcon = 0;
        init();
    }

    public SearchEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.clickable = true;
        this.mClearIcon = 0;
        this.mSearchIcon = 0;
        init();
    }

    private void init() {
        if (this.mClearIcon != 0) {
            this.mClearDrawable = ResourcesCompat.getDrawable(getResources(), this.mClearIcon, null);
        } else {
            this.mClearDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_search_close, null);
        }
        if (this.mSearchIcon != 0) {
            this.mSearchDrawable = ResourcesCompat.getDrawable(getResources(), this.mSearchIcon, null);
        } else {
            this.mSearchDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_search, null);
        }
        setCompoundDrawablesWithIntrinsicBounds(this.mSearchDrawable, getCompoundDrawables()[1], getCompoundDrawables()[2], getCompoundDrawables()[3]);
    }

    @Override // android.widget.TextView
    protected void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        setClearIconVisible(charSequence.length() > 0);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        setClearIconVisible(length() > 0);
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        Drawable drawable;
        if (motionEvent.getAction() == 1 && (drawable = getCompoundDrawables()[2]) != null && motionEvent.getX() <= getWidth() - getPaddingRight() && motionEvent.getX() >= (getWidth() - getPaddingRight()) - drawable.getBounds().width()) {
            OnClickClearListener onClickClearListener = this.listener;
            if (onClickClearListener != null) {
                onClickClearListener.onClear();
            }
            setText("");
        }
        return isEnabled() && super.onTouchEvent(motionEvent);
    }

    public void setClickableChild(Boolean bool) {
        this.clickable = bool;
    }

    private void setClearIconVisible(boolean z) {
        setCompoundDrawablesWithIntrinsicBounds(getCompoundDrawables()[0], getCompoundDrawables()[1], z ? this.mClearDrawable : null, getCompoundDrawables()[3]);
    }

    @Override // android.view.View
    public boolean dispatchKeyEventPreIme(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 4) {
            OnClickClearListener onClickClearListener = this.listener;
            if (onClickClearListener == null) {
                return true;
            }
            onClickClearListener.onDispatchKeyEventPreIme();
            return true;
        }
        return super.dispatchKeyEventPreIme(keyEvent);
    }

    public void setOnClickClearListener(OnClickClearListener onClickClearListener) {
        this.listener = onClickClearListener;
    }

    public void setmClearIcon(int i) {
        this.mClearIcon = i;
        init();
    }

    public void setmSearchIcon(int i) {
        this.mSearchIcon = i;
        init();
    }
}
