package com.wanos.media.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewbinding.ViewBinding;
import com.wanos.media.entity.IZeroBaseAction;
import com.wanos.media.presenter.IPresenter;
import com.wanos.media.ui.info.ZeroInfoActivity;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseZeroFragment<V extends ViewBinding, P extends IPresenter> extends Fragment {
    protected IZeroBaseAction mBaseAction;
    protected P mPresenter;
    protected V mViewBinding;

    public abstract int findSceneIndex(long j);

    protected abstract void initData(Bundle bundle);

    protected abstract void initListener(Bundle bundle);

    protected abstract P initPresenter(Bundle bundle);

    protected abstract void initSetting(Bundle bundle);

    protected abstract V initViewBinding(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle);

    public abstract void setControlState(boolean z, boolean z2);

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mViewBinding = (V) initViewBinding(layoutInflater, viewGroup, bundle);
        this.mPresenter = (P) initPresenter(bundle);
        return this.mViewBinding.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        FragmentActivity activity = getActivity();
        if (activity instanceof ZeroInfoActivity) {
            this.mBaseAction = ((ZeroInfoActivity) activity).getPlayEvent();
        }
        initSetting(bundle);
        initListener(bundle);
        initData(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        P p = this.mPresenter;
        if (p != null) {
            p.onDestroy();
        }
        this.mPresenter = null;
        this.mViewBinding = null;
    }
}
