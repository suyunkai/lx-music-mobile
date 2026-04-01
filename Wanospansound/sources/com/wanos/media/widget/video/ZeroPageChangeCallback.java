package com.wanos.media.widget.video;

import androidx.viewpager2.widget.ViewPager2;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes3.dex */
public class ZeroPageChangeCallback extends ViewPager2.OnPageChangeCallback {
    private int mPosition = 0;
    private final WeakReference<ZeroVideoPageView> mWeakReference;

    public ZeroPageChangeCallback(ZeroVideoPageView zeroVideoPageView) {
        this.mWeakReference = new WeakReference<>(zeroVideoPageView);
    }

    @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
    public void onPageScrolled(int i, float f, int i2) {
        super.onPageScrolled(i, f, i2);
        ZeroVideoPageView zeroVideoPageView = this.mWeakReference.get();
        if (zeroVideoPageView == null) {
            return;
        }
        if (this.mPosition != i) {
            this.mPosition = i;
            zeroVideoPageView.onViewPageSelected(i);
        }
        zeroVideoPageView.getIndicatorView().onPageScrolled(i % zeroVideoPageView.getItemCount(), f, i2);
    }

    @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
    public void onPageScrollStateChanged(int i) {
        super.onPageScrollStateChanged(i);
        ZeroVideoPageView zeroVideoPageView = this.mWeakReference.get();
        if (zeroVideoPageView == null) {
            return;
        }
        zeroVideoPageView.onPageScrollStateChanged(i);
    }

    public int getPosition() {
        return this.mPosition;
    }
}
