package com.wanos.media.base;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.wanos.commonlibrary.bean.MediaInfo;
import com.wanos.commonlibrary.event.LoginOrLogoutEvent;
import com.wanos.commonlibrary.event.MediaCollectChangeEvent;
import com.wanos.commonlibrary.mediaPlayer.MediaPlayerEnum;
import com.wanos.media.ComResCenter;
import com.wanos.media.R;
import com.wanos.media.databinding.FragmentWanosBaseBinding;
import com.wanos.media.presenter.IPresenter;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes3.dex */
public abstract class WanosBaseFragment<P extends IPresenter> extends Fragment {
    private BaseFragContent baseFragContent;
    private FragmentWanosBaseBinding binding;
    public boolean isResume = false;
    protected P mPresenter;

    public boolean isShowErrorOrLoadingViewCenterOnRootView() {
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loginOrLogout(LoginOrLogoutEvent loginOrLogoutEvent) {
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void mediaCollectChange(MediaCollectChangeEvent mediaCollectChangeEvent) {
    }

    protected abstract View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle);

    public void onMediaInfoCallbackAppNext(MediaPlayerEnum.MediaInfoCallbackType mediaInfoCallbackType, MediaInfo mediaInfo) {
    }

    public void onStatusonStatusCallbackNext(MediaPlayerEnum.CallBackState callBackState, Object... objArr) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (ComResCenter.getInstance().mainServer != null) {
            BaseFragContent baseFragContentBaseFragOnCreate = ComResCenter.getInstance().mainServer.baseFragOnCreate();
            this.baseFragContent = baseFragContentBaseFragOnCreate;
            baseFragContentBaseFragOnCreate.init(getActivity());
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        EventBus.getDefault().register(this);
        FragmentWanosBaseBinding fragmentWanosBaseBindingInflate = FragmentWanosBaseBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = fragmentWanosBaseBindingInflate;
        this.binding.baseFContentViewgroup.addView(onCreateContentView(layoutInflater, fragmentWanosBaseBindingInflate.baseFContentViewgroup, bundle));
        if (isShowErrorOrLoadingViewCenterOnRootView()) {
            this.binding.baseFErrorLoadingView.setPadding(0, 0, 0, getResources().getDimensionPixelSize(R.dimen.title_bar_height));
        }
        return this.binding.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.isResume = true;
        BaseFragContent baseFragContent = this.baseFragContent;
        if (baseFragContent != null) {
            baseFragContent.onResume();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.isResume = false;
        BaseFragContent baseFragContent = this.baseFragContent;
        if (baseFragContent != null) {
            baseFragContent.onPause();
        }
    }

    public void showLoginDialog(DialogInterface.OnDismissListener onDismissListener, boolean z) {
        BaseFragContent baseFragContent = this.baseFragContent;
        if (baseFragContent != null) {
            baseFragContent.showLoginDialog(onDismissListener, z);
        }
    }

    public void showLoginDialog(int i, String str) {
        BaseFragContent baseFragContent = this.baseFragContent;
        if (baseFragContent != null) {
            baseFragContent.showLoginDialog(i, str);
        }
    }

    public void openWeChatPayActivity() {
        BaseFragContent baseFragContent = this.baseFragContent;
        if (baseFragContent != null) {
            baseFragContent.openWeChatPayActivity();
        }
    }

    public void openWeChatPayActivity(int i, int i2) {
        BaseFragContent baseFragContent = this.baseFragContent;
        if (baseFragContent != null) {
            baseFragContent.openWeChatPayActivity(i, i2);
        }
    }

    public void showLoadingView() {
        this.binding.baseFLoadingView.getRoot().setVisibility(0);
        this.binding.baseFContentViewgroup.setVisibility(4);
        this.binding.baseFErrorView.getRoot().setVisibility(8);
    }

    public void showLoadingView(int i, int i2, String str) {
        if (i != -1) {
            this.binding.baseFLoadingView.pbLoading.setIndeterminateTintList(ContextCompat.getColorStateList(getActivity(), i));
        }
        if (i2 != -1) {
            this.binding.baseFLoadingView.tvLoading.setTextColor(getActivity().getColor(i2));
        }
        if (!str.isEmpty()) {
            this.binding.baseFLoadingView.tvLoading.setText(str);
        }
        this.binding.baseFLoadingView.getRoot().setVisibility(0);
        this.binding.baseFContentViewgroup.setVisibility(4);
        this.binding.baseFErrorView.getRoot().setVisibility(8);
    }

    protected void showEmptyView(int i, String str) {
        this.binding.baseFLoadingView.getRoot().setVisibility(8);
        this.binding.baseFContentViewgroup.setVisibility(4);
        this.binding.baseFErrorView.getRoot().setVisibility(0);
        this.binding.baseFErrorView.ivError.setImageResource(i);
        this.binding.baseFErrorView.tvErrorMessage.setText(str);
        this.binding.baseFErrorView.btnRetry.setVisibility(4);
    }

    public void closeLoadingView() {
        this.binding.baseFLoadingView.getRoot().setVisibility(8);
        this.binding.baseFContentViewgroup.setVisibility(0);
        this.binding.baseFErrorView.getRoot().setVisibility(8);
    }

    public void showLoadErrorView(View.OnClickListener onClickListener) {
        this.binding.baseFLoadingView.getRoot().setVisibility(8);
        this.binding.baseFContentViewgroup.setVisibility(4);
        this.binding.baseFErrorView.getRoot().setVisibility(0);
        this.binding.baseFErrorView.btnRetry.setOnClickListener(onClickListener);
    }

    public void showLoadErrorView(String str, View.OnClickListener onClickListener) {
        this.binding.baseFLoadingView.getRoot().setVisibility(8);
        this.binding.baseFContentViewgroup.setVisibility(4);
        this.binding.baseFErrorView.getRoot().setVisibility(0);
        this.binding.baseFErrorView.tvErrorMessage.setText(str);
        this.binding.baseFErrorView.btnRetry.setOnClickListener(onClickListener);
    }

    public void showLoadErrorView(String str, int i, View.OnClickListener onClickListener) {
        this.binding.baseFLoadingView.getRoot().setVisibility(8);
        this.binding.baseFContentViewgroup.setVisibility(4);
        this.binding.baseFErrorView.getRoot().setVisibility(0);
        this.binding.baseFErrorView.tvErrorMessage.setText(str);
        this.binding.baseFErrorView.ivError.setImageResource(i);
        this.binding.baseFErrorView.btnRetry.setOnClickListener(onClickListener);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        P p = this.mPresenter;
        if (p != null) {
            p.onDestroy();
        }
        this.mPresenter = null;
    }

    public void hideLoadErrorView() {
        this.binding.baseFErrorView.getRoot().setVisibility(8);
    }
}
