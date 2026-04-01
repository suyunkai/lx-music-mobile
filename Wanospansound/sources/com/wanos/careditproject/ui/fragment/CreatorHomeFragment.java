package com.wanos.careditproject.ui.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.wanos.bean.ProjectInfo;
import com.wanos.careditproject.R;
import com.wanos.careditproject.databinding.FragmentCreatorHomeBinding;
import com.wanos.careditproject.service.AudioPlayerManager.AudioPlayerManager;
import com.wanos.careditproject.ui.adapter.CreatorCommonAdapter;
import com.wanos.careditproject.ui.adapter.CreatorCommonItem;
import com.wanos.careditproject.ui.strategy.CreatorHomeCardListStrategy;
import com.wanos.careditproject.ui.strategy.CreatorHomeHeaderStrategy;
import com.wanos.careditproject.ui.strategy.CreatorHomeTagListStrategy;
import com.wanos.careditproject.ui.strategy.CreatorHomeTitleStrategy;
import com.wanos.careditproject.ui.viewmodel.CreatorHomeViewModel;
import com.wanos.commonlibrary.event.LoginOrLogoutEvent;
import com.wanos.media.base.WanosBaseFragment;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes3.dex */
public class CreatorHomeFragment extends WanosBaseFragment {
    private static boolean isUiModeChanged = false;
    private int currentNightMode;
    private CreatorCommonAdapter mAdapter;
    private FragmentCreatorHomeBinding mBinding;
    private CreatorHomeViewModel mViewModel;

    public static boolean isUiModeChanged() {
        return isUiModeChanged;
    }

    @Override // com.wanos.media.base.WanosBaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mViewModel = (CreatorHomeViewModel) new ViewModelProvider(getActivity()).get(CreatorHomeViewModel.class);
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = FragmentCreatorHomeBinding.inflate(layoutInflater);
        initView();
        addObservers();
        return this.mBinding.getRoot();
    }

    private void initView() {
        this.mAdapter = new CreatorCommonAdapter(new DiffUtil.ItemCallback<CreatorCommonItem>() { // from class: com.wanos.careditproject.ui.fragment.CreatorHomeFragment.1
            @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
            public boolean areItemsTheSame(CreatorCommonItem creatorCommonItem, CreatorCommonItem creatorCommonItem2) {
                return creatorCommonItem.getId() == creatorCommonItem2.getId();
            }

            @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
            public boolean areContentsTheSame(CreatorCommonItem creatorCommonItem, CreatorCommonItem creatorCommonItem2) {
                return creatorCommonItem.equals(creatorCommonItem2);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        this.mAdapter.registerStrategy(0, new CreatorHomeHeaderStrategy());
        this.mAdapter.registerStrategy(1, new CreatorHomeTitleStrategy());
        this.mAdapter.registerStrategy(2, new CreatorHomeTagListStrategy(this.mViewModel));
        this.mAdapter.registerStrategy(3, new CreatorHomeCardListStrategy());
        this.mAdapter.setBottomMargin(getResources().getDimensionPixelSize(R.dimen.creator_home_page_bottom_margin));
        this.mBinding.rcCreatorHome.setLayoutManager(linearLayoutManager);
        this.mBinding.rcCreatorHome.setItemAnimator(null);
        this.mBinding.rcCreatorHome.setAdapter(this.mAdapter);
    }

    private void addObservers() {
        this.mViewModel.getCreatorHomeItems().observe(this, new Observer() { // from class: com.wanos.careditproject.ui.fragment.CreatorHomeFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m390xd72c0435((List) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$addObservers$0$com-wanos-careditproject-ui-fragment-CreatorHomeFragment, reason: not valid java name */
    /* synthetic */ void m390xd72c0435(List list) {
        this.mAdapter.submitList(new ArrayList(list));
        this.mAdapter.notifyDataSetChanged();
    }

    @Override // com.wanos.media.base.WanosBaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        if (isUiModeChanged) {
            return;
        }
        AudioPlayerManager.getInstance().pause();
    }

    @Override // com.wanos.media.base.WanosBaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        isUiModeChanged = false;
        super.onResume();
        this.mViewModel.loadData();
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        int i = configuration.uiMode & 48;
        if (this.currentNightMode != i) {
            this.currentNightMode = i;
            isUiModeChanged = true;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onProjectInfoEvent(ProjectInfo projectInfo) {
        CreatorHomeViewModel creatorHomeViewModel = this.mViewModel;
        if (creatorHomeViewModel != null) {
            creatorHomeViewModel.onProjectInfoChange(projectInfo);
        }
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    public void loginOrLogout(LoginOrLogoutEvent loginOrLogoutEvent) {
        CreatorHomeViewModel creatorHomeViewModel = this.mViewModel;
        if (creatorHomeViewModel != null) {
            creatorHomeViewModel.clearCardList();
            this.mViewModel.loadData();
        }
    }
}
