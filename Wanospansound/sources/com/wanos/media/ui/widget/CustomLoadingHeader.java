package com.wanos.media.ui.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.constraintlayout.motion.widget.Key;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshKernel;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.RefreshState;
import com.scwang.smart.refresh.layout.constant.SpinnerStyle;
import com.wanos.media.R;

/* JADX INFO: loaded from: classes3.dex */
public class CustomLoadingHeader extends LinearLayout implements RefreshHeader {
    private ImageView mLoadingImageView;
    private ObjectAnimator mRotateAnimator;

    @Override // com.scwang.smart.refresh.layout.api.RefreshComponent
    public boolean autoOpen(int i, float f, boolean z) {
        return false;
    }

    @Override // com.scwang.smart.refresh.layout.api.RefreshComponent
    public View getView() {
        return this;
    }

    @Override // com.scwang.smart.refresh.layout.api.RefreshComponent
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override // com.scwang.smart.refresh.layout.api.RefreshComponent
    public void onHorizontalDrag(float f, int i, int i2) {
    }

    @Override // com.scwang.smart.refresh.layout.api.RefreshComponent
    public void onInitialized(RefreshKernel refreshKernel, int i, int i2) {
    }

    @Override // com.scwang.smart.refresh.layout.api.RefreshComponent
    public void onMoving(boolean z, float f, int i, int i2, int i3) {
    }

    @Override // com.scwang.smart.refresh.layout.api.RefreshComponent
    public void onReleased(RefreshLayout refreshLayout, int i, int i2) {
    }

    @Override // com.scwang.smart.refresh.layout.api.RefreshComponent
    public void setPrimaryColors(int... iArr) {
    }

    public CustomLoadingHeader(Context context) {
        super(context);
        init();
    }

    public CustomLoadingHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public CustomLoadingHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        setOrientation(1);
        setGravity(17);
        LayoutInflater.from(getContext()).inflate(R.layout.layout_custom_header, (ViewGroup) this, true);
        ImageView imageView = (ImageView) findViewById(R.id.iv_loading);
        this.mLoadingImageView = imageView;
        imageView.setImageResource(R.drawable.ic_header_loading);
        initAnimator();
    }

    private void initAnimator() {
        ObjectAnimator duration = ObjectAnimator.ofFloat(this.mLoadingImageView, Key.ROTATION, 0.0f, 360.0f).setDuration(1000L);
        this.mRotateAnimator = duration;
        duration.setInterpolator(new LinearInterpolator());
        this.mRotateAnimator.setRepeatCount(-1);
        this.mRotateAnimator.setRepeatMode(1);
    }

    @Override // com.scwang.smart.refresh.layout.api.RefreshComponent
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override // com.scwang.smart.refresh.layout.api.RefreshComponent
    public void onStartAnimator(RefreshLayout refreshLayout, int i, int i2) {
        startAnimation();
    }

    @Override // com.scwang.smart.refresh.layout.api.RefreshComponent
    public int onFinish(RefreshLayout refreshLayout, boolean z) {
        stopAnimation();
        return 100;
    }

    /* JADX INFO: renamed from: com.wanos.media.ui.widget.CustomLoadingHeader$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$scwang$smart$refresh$layout$constant$RefreshState;

        static {
            int[] iArr = new int[RefreshState.values().length];
            $SwitchMap$com$scwang$smart$refresh$layout$constant$RefreshState = iArr;
            try {
                iArr[RefreshState.PullDownToRefresh.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    @Override // com.scwang.smart.refresh.layout.listener.OnStateChangedListener
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState refreshState, RefreshState refreshState2) {
        if (AnonymousClass1.$SwitchMap$com$scwang$smart$refresh$layout$constant$RefreshState[refreshState2.ordinal()] != 1) {
            return;
        }
        stopAnimation();
        this.mLoadingImageView.setVisibility(0);
    }

    private void startAnimation() {
        ObjectAnimator objectAnimator = this.mRotateAnimator;
        if (objectAnimator == null || objectAnimator.isRunning()) {
            return;
        }
        this.mRotateAnimator.start();
    }

    private void stopAnimation() {
        ObjectAnimator objectAnimator = this.mRotateAnimator;
        if (objectAnimator == null || !objectAnimator.isRunning()) {
            return;
        }
        this.mRotateAnimator.cancel();
    }
}
