package com.wanos.media.base;

import android.os.Bundle;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseLazyFragment extends WanosBaseFragment {
    private boolean isLoaded = false;

    public abstract void lazyLoad();

    @Override // com.wanos.media.base.WanosBaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.wanos.media.base.WanosBaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.isLoaded) {
            return;
        }
        lazyLoad();
        this.isLoaded = true;
    }

    @Override // com.wanos.media.base.WanosBaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.isLoaded = false;
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    public void showLoadingView(int i, int i2, String str) {
        super.showLoadingView(i, i2, str);
    }
}
