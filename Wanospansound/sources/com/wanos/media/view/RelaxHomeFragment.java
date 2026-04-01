package com.wanos.media.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.wanos.WanosCommunication.service.MediaStatistic;
import com.wanos.commonlibrary.event.UserInfoChangeEvent;
import com.wanos.commonlibrary.utils.StatisticUtil;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.commonlibrary.utils.UserInfoUtil;
import com.wanos.media.ZeroApplication;
import com.wanos.media.adapter.RelaxHomeAdapter;
import com.wanos.media.entity.PageState;
import com.wanos.media.entity.SpaceThemeBaseInfo;
import com.wanos.media.entity.ThemeEvent;
import com.wanos.media.entity.ZeroPageMode;
import com.wanos.media.ui.info.ZeroInfoActivity;
import com.wanos.media.ui.theme.MeThemeActivity;
import com.wanos.media.util.CustomClick;
import com.wanos.media.util.LoginUtils;
import com.wanos.media.util.RelaxInfoRunBackground;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.view.MeSoundPopupWindow;
import com.wanos.media.viewmodel.RelaxHomeViewModel;
import com.wanos.media.zero_p.R;
import com.wanos.media.zero_p.databinding.FragmentRelaxHomeBinding;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes3.dex */
public class RelaxHomeFragment extends Fragment implements LoginUtils.IUserStateCallback {
    private static final String TAG = "RelaxHomeFragment";
    private FragmentRelaxHomeBinding binding;
    private RelaxHomeAdapter mAdapter;
    private MeSoundPopupWindow mMeSoundPopupWindow;
    private RelaxHomeViewModel viewModel;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentRelaxHomeBinding fragmentRelaxHomeBindingInflate = FragmentRelaxHomeBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = fragmentRelaxHomeBindingInflate;
        return fragmentRelaxHomeBindingInflate.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (getActivity() == null) {
            this.viewModel = (RelaxHomeViewModel) new ViewModelProvider(this).get(RelaxHomeViewModel.class);
        } else {
            this.viewModel = (RelaxHomeViewModel) new ViewModelProvider(getActivity()).get(RelaxHomeViewModel.class);
        }
        EventBus.getDefault().register(this);
        final int integer = getResources().getInteger(R.integer.relax_home_grid_code);
        final Context context = getContext();
        if (context == null) {
            return;
        }
        LoginUtils.getInstance().addUserStateCallback(this);
        this.binding.refresh.setEnableRefresh(true);
        this.binding.refresh.setEnableLoadMore(false);
        this.mAdapter = new RelaxHomeAdapter(context);
        this.binding.rvRelaxHome.setLayoutManager(getGridLayoutManager(context, integer));
        this.binding.rvRelaxHome.setAdapter(this.mAdapter);
        this.viewModel.homeUpDataThemeList.observe(getViewLifecycleOwner(), new Observer() { // from class: com.wanos.media.view.RelaxHomeFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m578lambda$onViewCreated$0$comwanosmediaviewRelaxHomeFragment(integer, (Integer) obj);
            }
        });
        this.viewModel.pageState.observe(getViewLifecycleOwner(), new Observer() { // from class: com.wanos.media.view.RelaxHomeFragment$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m579lambda$onViewCreated$1$comwanosmediaviewRelaxHomeFragment((PageState) obj);
            }
        });
        RelaxInfoRunBackground.relaxInfoRunBackground.observe(getViewLifecycleOwner(), new Observer() { // from class: com.wanos.media.view.RelaxHomeFragment$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ToastUtil.showMsg((String) obj);
            }
        });
        this.binding.refresh.setOnRefreshListener(new OnRefreshListener() { // from class: com.wanos.media.view.RelaxHomeFragment$$ExternalSyntheticLambda3
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout refreshLayout) {
                this.f$0.m580lambda$onViewCreated$2$comwanosmediaviewRelaxHomeFragment(refreshLayout);
            }
        });
        this.binding.errorView.btnRetry.setOnClickListener(new CustomClick() { // from class: com.wanos.media.view.RelaxHomeFragment.1
            @Override // com.wanos.media.util.CustomClick
            public void onAnitClick(View view2) {
                RelaxHomeFragment.this.viewModel.initHomeData(false);
            }
        });
        this.mAdapter.setOnItemClickListener(new RelaxHomeAdapter.OnItemClickListener() { // from class: com.wanos.media.view.RelaxHomeFragment.2
            @Override // com.wanos.media.adapter.RelaxHomeAdapter.OnItemClickListener
            public void onItemClick(View view2, SpaceThemeBaseInfo spaceThemeBaseInfo) {
                switch (spaceThemeBaseInfo.getItemType()) {
                    case 3001:
                        if (1001 == spaceThemeBaseInfo.getEnterWay()) {
                            RelaxListActivity.onLaunch(RelaxHomeFragment.this, 1001);
                            MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.COMFORT_SPACE_CLICK_NAP_SPACE_MORE, "", "", "", "", 0);
                        } else if (2001 == spaceThemeBaseInfo.getEnterWay()) {
                            RelaxListActivity.onLaunch(RelaxHomeFragment.this, 2001);
                            MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.COMFORT_SPACE_CLICK_MEDITE_SPACE_MORE, "", "", "", "", 0);
                        } else if (!UserInfoUtil.isLogin()) {
                            LoginUtils.getInstance().showLoginDialog();
                        } else {
                            MeThemeActivity.onLaunchActivity(RelaxHomeFragment.this);
                            MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.COMFORT_SPACE_CLICK_MY_SOUND_SPACE_MORE, "", "", "", "", 0);
                        }
                        break;
                    case 3002:
                        if (ZeroApplication.getZeroCallback().getScreenState() != 101) {
                            ToastUtil.showMsg(R.string.error_portrait_use);
                        } else if (1001 == spaceThemeBaseInfo.getEnterWay()) {
                            ZeroInfoActivity.onLaunch(RelaxHomeFragment.this.requireContext(), ZeroPageMode.XIAO_QI_STANDARD, spaceThemeBaseInfo);
                            Log.i("zt", "小憩内容播放次数----" + spaceThemeBaseInfo.getThemeId());
                            MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.COMFORT_SPACE_CLICK_NAP_PLAY, spaceThemeBaseInfo.getThemeId() + "", "", "", "", 0);
                        } else if (2001 == spaceThemeBaseInfo.getEnterWay()) {
                            ZeroInfoActivity.onLaunch(RelaxHomeFragment.this.requireContext(), ZeroPageMode.MING_XIANG_STANDARD, spaceThemeBaseInfo);
                            MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.COMFORT_SPACE_CLICK_MEDITE_PLAY, spaceThemeBaseInfo.getThemeId() + "", "", "", "", 0);
                        } else {
                            ZeroLogcatTools.w(RelaxHomeFragment.TAG, "模板主题点击: 类型异常，EnterWay = " + spaceThemeBaseInfo.getEnterWay());
                        }
                        break;
                    case 3003:
                        if (!UserInfoUtil.isLogin()) {
                            LoginUtils.getInstance().showLoginDialog();
                        } else if (RelaxHomeFragment.this.getActivity() != null) {
                            InputShareCodeDialog.showInputCode(RelaxHomeFragment.this.getActivity().getSupportFragmentManager());
                            MediaStatistic.getInstance().saveUserEventStatistic(StatisticUtil.COMFORT_SPACE_CLICK_ADD_SHARE_CODE, "", "", "", "", 0);
                        }
                        break;
                    case 3004:
                        if (ZeroApplication.getZeroCallback().getScreenState() != 101) {
                            ToastUtil.showMsg(R.string.error_portrait_use);
                        } else if (1002 == spaceThemeBaseInfo.getEnterWay()) {
                            ZeroInfoActivity.onLaunch(RelaxHomeFragment.this.requireContext(), ZeroPageMode.XIAO_QI_PRO, spaceThemeBaseInfo);
                        } else if (2002 == spaceThemeBaseInfo.getEnterWay()) {
                            ZeroInfoActivity.onLaunch(RelaxHomeFragment.this.requireContext(), ZeroPageMode.MING_XIANG_PRO, spaceThemeBaseInfo);
                        } else {
                            ZeroLogcatTools.w(RelaxHomeFragment.TAG, "收藏主题点击: 类型异常，EnterWay = " + spaceThemeBaseInfo.getEnterWay());
                        }
                        break;
                }
            }

            @Override // com.wanos.media.adapter.RelaxHomeAdapter.OnItemClickListener
            public void onItemMenuClick(View view2, final SpaceThemeBaseInfo spaceThemeBaseInfo) {
                if (RelaxHomeFragment.this.mMeSoundPopupWindow != null && RelaxHomeFragment.this.mMeSoundPopupWindow.isShowing()) {
                    RelaxHomeFragment.this.mMeSoundPopupWindow.dismiss();
                } else {
                    RelaxHomeFragment.this.mMeSoundPopupWindow = MeSoundPopupWindow.show(context, view2, false, spaceThemeBaseInfo.isCanShare(), new MeSoundPopupWindow.IPopupWindowListener() { // from class: com.wanos.media.view.RelaxHomeFragment.2.1
                        @Override // com.wanos.media.view.MeSoundPopupWindow.IPopupWindowListener
                        public void onTopHome() {
                        }

                        @Override // com.wanos.media.view.MeSoundPopupWindow.IPopupWindowListener
                        public void onDelete() {
                            RelaxHomeFragment.this.viewModel.deleteTheme(spaceThemeBaseInfo.getThemeId());
                        }

                        @Override // com.wanos.media.view.MeSoundPopupWindow.IPopupWindowListener
                        public void onShare() {
                            if (RelaxHomeFragment.this.getActivity() != null) {
                                SendShareCodeDialog.showSendCode(RelaxHomeFragment.this.getActivity().getSupportFragmentManager(), spaceThemeBaseInfo.getThemeId());
                            }
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: renamed from: lambda$onViewCreated$0$com-wanos-media-view-RelaxHomeFragment, reason: not valid java name */
    /* synthetic */ void m578lambda$onViewCreated$0$comwanosmediaviewRelaxHomeFragment(int i, Integer num) {
        if (num.intValue() != -1) {
            this.mAdapter.submitList(this.viewModel.getHomeDataList(i));
        }
    }

    /* JADX INFO: renamed from: com.wanos.media.view.RelaxHomeFragment$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$wanos$media$entity$PageState;

        static {
            int[] iArr = new int[PageState.values().length];
            $SwitchMap$com$wanos$media$entity$PageState = iArr;
            try {
                iArr[PageState.LOADING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$wanos$media$entity$PageState[PageState.SUCCESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$wanos$media$entity$PageState[PageState.ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$wanos$media$entity$PageState[PageState.EMPTY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: renamed from: lambda$onViewCreated$1$com-wanos-media-view-RelaxHomeFragment, reason: not valid java name */
    /* synthetic */ void m579lambda$onViewCreated$1$comwanosmediaviewRelaxHomeFragment(PageState pageState) {
        int i = AnonymousClass4.$SwitchMap$com$wanos$media$entity$PageState[pageState.ordinal()];
        if (i == 1) {
            this.binding.loadingView.getRoot().setVisibility(0);
            this.binding.rvRelaxHome.setVisibility(4);
            this.binding.emptyView.getRoot().setVisibility(4);
            this.binding.errorView.getRoot().setVisibility(4);
            return;
        }
        if (i == 2) {
            this.binding.rvRelaxHome.setVisibility(0);
            this.binding.loadingView.getRoot().setVisibility(4);
            this.binding.emptyView.getRoot().setVisibility(4);
            this.binding.errorView.getRoot().setVisibility(4);
            this.binding.refresh.finishRefresh();
            return;
        }
        if (i == 3) {
            this.binding.errorView.getRoot().setVisibility(0);
            this.binding.rvRelaxHome.setVisibility(4);
            this.binding.loadingView.getRoot().setVisibility(4);
            this.binding.emptyView.getRoot().setVisibility(4);
            this.binding.refresh.finishRefresh();
            return;
        }
        if (i != 4) {
            return;
        }
        this.binding.emptyView.getRoot().setVisibility(0);
        this.binding.errorView.getRoot().setVisibility(4);
        this.binding.rvRelaxHome.setVisibility(4);
        this.binding.loadingView.getRoot().setVisibility(4);
        this.binding.emptyView.tvEmpty.setText(R.string.zero_error_empty);
        this.binding.refresh.finishRefresh();
    }

    /* JADX INFO: renamed from: lambda$onViewCreated$2$com-wanos-media-view-RelaxHomeFragment, reason: not valid java name */
    /* synthetic */ void m580lambda$onViewCreated$2$comwanosmediaviewRelaxHomeFragment(RefreshLayout refreshLayout) {
        this.viewModel.initHomeData(true);
    }

    private GridLayoutManager getGridLayoutManager(Context context, final int i) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, i);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.wanos.media.view.RelaxHomeFragment.3
            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int i2) {
                if (RelaxHomeFragment.this.mAdapter.getItemViewType(i2) == 3001) {
                    return i;
                }
                return 1;
            }
        });
        return gridLayoutManager;
    }

    @Override // com.wanos.media.util.LoginUtils.IUserStateCallback
    public void onLoginStateChange(boolean z) {
        ZeroLogcatTools.i(TAG, "onLoginStateChange: " + z);
        this.viewModel.initHomeData(true);
    }

    @Override // com.wanos.media.util.LoginUtils.IUserStateCallback
    public void onUserInfoChange(UserInfoChangeEvent userInfoChangeEvent) {
        ZeroLogcatTools.i(TAG, "onUserInfoChange: " + userInfoChangeEvent.getUserInfo().getUserName());
        this.mAdapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefreshDataEvent(ThemeEvent themeEvent) {
        switch (themeEvent.getEventType()) {
            case 101:
            case 102:
            case 103:
                this.viewModel.initHomeData(true);
                break;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        LoginUtils.getInstance().removeUserStateCallback(this);
        this.binding = null;
    }
}
