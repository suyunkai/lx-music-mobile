package com.wanos.media.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import com.wanos.MaterialRefreshLayout;
import com.wanos.MaterialRefreshListener;
import com.wanos.commonlibrary.utils.ToastUtil;
import com.wanos.media.ZeroApplication;
import com.wanos.media.adapter.RelaxCollectAdapter;
import com.wanos.media.entity.LoadState;
import com.wanos.media.entity.PageState;
import com.wanos.media.entity.SpaceThemeBaseInfo;
import com.wanos.media.entity.ZeroPageMode;
import com.wanos.media.ui.info.ZeroInfoActivity;
import com.wanos.media.util.CustomClick;
import com.wanos.media.util.RelaxInfoRunBackground;
import com.wanos.media.util.ZeroLogcatTools;
import com.wanos.media.view.MeSoundPopupWindow;
import com.wanos.media.viewmodel.RelaxCollectListViewModel;
import com.wanos.media.viewmodel.ViewModelIntentFactory;
import com.wanos.media.widget.ItemDecoration;
import com.wanos.media.zero_p.R;
import com.wanos.media.zero_p.databinding.FragmentRelaxCollectListBinding;

/* JADX INFO: loaded from: classes3.dex */
public class RelaxCollectListFragment extends Fragment {
    private static final String TAG = "RelaxCollectListFragmen";
    private FragmentRelaxCollectListBinding binding;
    private RelaxCollectAdapter mAdapter;
    private MeSoundPopupWindow mMeSoundPopupWindow;
    private RelaxCollectListViewModel viewModel;

    public static RelaxCollectListFragment newInstance(int i) {
        RelaxCollectListFragment relaxCollectListFragment = new RelaxCollectListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("tableId", i);
        relaxCollectListFragment.setArguments(bundle);
        return relaxCollectListFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.binding = FragmentRelaxCollectListBinding.inflate(layoutInflater, viewGroup, false);
        Intent intent = new Intent();
        Bundle arguments = getArguments();
        intent.putExtra(RelaxCollectListViewModel.KEY_TABLE_ID, arguments != null ? arguments.getInt("tableId", -1) : -1);
        this.viewModel = (RelaxCollectListViewModel) new ViewModelProvider(this, new ViewModelIntentFactory(intent)).get(RelaxCollectListViewModel.class);
        return this.binding.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (getContext() == null) {
            return;
        }
        this.binding.emptyView.tvEmpty.setText(R.string.zero_me_empty);
        this.mAdapter = new RelaxCollectAdapter(getContext(), this.viewModel.getItemList());
        this.binding.rvRelaxList.setAdapter(this.mAdapter);
        this.binding.rvRelaxList.addItemDecoration(new ItemDecoration((int) getResources().getDimension(R.dimen.home_item_space)));
        this.binding.rvRelaxList.setLayoutManager(new GridLayoutManager(getContext(), getResources().getInteger(R.integer.relax_home_grid_code)));
        this.binding.rvRelaxList.setAdapter(this.mAdapter);
        this.viewModel.pageState.observe(getViewLifecycleOwner(), new Observer() { // from class: com.wanos.media.view.RelaxCollectListFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m576x8ed2b6e5((PageState) obj);
            }
        });
        RelaxInfoRunBackground.relaxInfoRunBackground.observe(getViewLifecycleOwner(), new Observer() { // from class: com.wanos.media.view.RelaxCollectListFragment$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ToastUtil.showMsg((String) obj);
            }
        });
        this.viewModel.upDataItemList.observe(getViewLifecycleOwner(), new Observer() { // from class: com.wanos.media.view.RelaxCollectListFragment$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m577x44cdd26((Integer) obj);
            }
        });
        this.binding.refresh.setMaterialRefreshListener(new MaterialRefreshListener() { // from class: com.wanos.media.view.RelaxCollectListFragment.1
            @Override // com.wanos.MaterialRefreshListener
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                ZeroLogcatTools.i(RelaxCollectListFragment.TAG, "setMaterialRefreshListener: onRefresh");
                RelaxCollectListFragment.this.viewModel.initThemeList(LoadState.REFRESH);
            }

            @Override // com.wanos.MaterialRefreshListener
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                ZeroLogcatTools.i(RelaxCollectListFragment.TAG, "setMaterialRefreshListener: onRefreshLoadMore");
                RelaxCollectListFragment.this.viewModel.initThemeList(LoadState.MORE);
            }
        });
        this.mAdapter.setOnItemClickListener(new RelaxCollectAdapter.OnItemClickListener() { // from class: com.wanos.media.view.RelaxCollectListFragment.2
            @Override // com.wanos.media.adapter.RelaxCollectAdapter.OnItemClickListener
            public void onItemClick(SpaceThemeBaseInfo spaceThemeBaseInfo) {
                if (ZeroApplication.getZeroCallback().getScreenState() != 101) {
                    ToastUtil.showMsg(R.string.error_portrait_use);
                    return;
                }
                if (spaceThemeBaseInfo.getEnterWay() == 2002) {
                    ZeroInfoActivity.onLaunch(RelaxCollectListFragment.this.getContext(), ZeroPageMode.MING_XIANG_PRO, spaceThemeBaseInfo);
                } else if (spaceThemeBaseInfo.getEnterWay() == 1002) {
                    ZeroInfoActivity.onLaunch(RelaxCollectListFragment.this.getContext(), ZeroPageMode.XIAO_QI_PRO, spaceThemeBaseInfo);
                } else {
                    ZeroLogcatTools.w(RelaxCollectListFragment.TAG, "收藏主题点击: 类型异常，EnterWay = " + spaceThemeBaseInfo.getEnterWay());
                }
            }

            @Override // com.wanos.media.adapter.RelaxCollectAdapter.OnItemClickListener
            public void onMoreClick(View view2, final SpaceThemeBaseInfo spaceThemeBaseInfo) {
                RelaxCollectListFragment relaxCollectListFragment = RelaxCollectListFragment.this;
                relaxCollectListFragment.mMeSoundPopupWindow = MeSoundPopupWindow.show(relaxCollectListFragment.getContext(), view2, true, spaceThemeBaseInfo.isCanShare(), new MeSoundPopupWindow.IPopupWindowListener() { // from class: com.wanos.media.view.RelaxCollectListFragment.2.1
                    @Override // com.wanos.media.view.MeSoundPopupWindow.IPopupWindowListener
                    public void onDelete() {
                        RelaxCollectListFragment.this.viewModel.deleteTheme(spaceThemeBaseInfo.getThemeId());
                    }

                    @Override // com.wanos.media.view.MeSoundPopupWindow.IPopupWindowListener
                    public void onTopHome() {
                        RelaxCollectListFragment.this.viewModel.setTopTheme(spaceThemeBaseInfo);
                    }

                    @Override // com.wanos.media.view.MeSoundPopupWindow.IPopupWindowListener
                    public void onShare() {
                        SendShareCodeDialog.showSendCode(RelaxCollectListFragment.this.getChildFragmentManager(), spaceThemeBaseInfo.getThemeId());
                    }
                });
            }
        });
        this.binding.errorView.btnRetry.setOnClickListener(new CustomClick() { // from class: com.wanos.media.view.RelaxCollectListFragment.3
            @Override // com.wanos.media.util.CustomClick
            public void onAnitClick(View view2) {
                RelaxCollectListFragment.this.viewModel.initThemeList(LoadState.INIT);
            }
        });
    }

    /* JADX INFO: renamed from: com.wanos.media.view.RelaxCollectListFragment$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$wanos$media$entity$PageState;

        static {
            int[] iArr = new int[PageState.values().length];
            $SwitchMap$com$wanos$media$entity$PageState = iArr;
            try {
                iArr[PageState.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$wanos$media$entity$PageState[PageState.LOADING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$wanos$media$entity$PageState[PageState.EMPTY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$wanos$media$entity$PageState[PageState.ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: renamed from: lambda$onViewCreated$0$com-wanos-media-view-RelaxCollectListFragment, reason: not valid java name */
    /* synthetic */ void m576x8ed2b6e5(PageState pageState) {
        int i = AnonymousClass4.$SwitchMap$com$wanos$media$entity$PageState[pageState.ordinal()];
        if (i == 1) {
            this.binding.rvRelaxList.setVisibility(0);
            this.binding.loadingView.getRoot().setVisibility(8);
            this.binding.errorView.getRoot().setVisibility(8);
            this.binding.emptyView.getRoot().setVisibility(8);
            return;
        }
        if (i == 2) {
            this.binding.rvRelaxList.setVisibility(4);
            this.binding.loadingView.getRoot().setVisibility(0);
            this.binding.errorView.getRoot().setVisibility(8);
            this.binding.emptyView.getRoot().setVisibility(8);
            return;
        }
        if (i == 3) {
            this.binding.rvRelaxList.setVisibility(4);
            this.binding.loadingView.getRoot().setVisibility(8);
            this.binding.errorView.getRoot().setVisibility(8);
            this.binding.emptyView.getRoot().setVisibility(0);
            return;
        }
        if (i != 4) {
            return;
        }
        this.binding.rvRelaxList.setVisibility(4);
        this.binding.loadingView.getRoot().setVisibility(8);
        this.binding.errorView.getRoot().setVisibility(0);
        this.binding.emptyView.getRoot().setVisibility(8);
    }

    /* JADX INFO: renamed from: lambda$onViewCreated$1$com-wanos-media-view-RelaxCollectListFragment, reason: not valid java name */
    /* synthetic */ void m577x44cdd26(Integer num) {
        ZeroLogcatTools.i(TAG, "itemCount:" + num);
        if (num == null) {
            return;
        }
        if (num.intValue() == -1) {
            this.mAdapter.notifyDataSetChanged();
        } else {
            RelaxCollectAdapter relaxCollectAdapter = this.mAdapter;
            relaxCollectAdapter.notifyItemRangeChanged(relaxCollectAdapter.getItemCount() - num.intValue(), num.intValue());
        }
        this.binding.refresh.finishRefreshLoadMore();
        if (this.viewModel.getItemList().size() >= this.viewModel.getTotal()) {
            this.binding.refresh.setLoadMore(false);
        } else {
            this.binding.refresh.setLoadMore(true);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        MeSoundPopupWindow meSoundPopupWindow = this.mMeSoundPopupWindow;
        if (meSoundPopupWindow != null) {
            meSoundPopupWindow.dismiss();
        }
        this.binding = null;
    }
}
