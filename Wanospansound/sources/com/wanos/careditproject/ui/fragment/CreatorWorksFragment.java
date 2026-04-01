package com.wanos.careditproject.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.wanos.bean.ProjectInfo;
import com.wanos.careditproject.R;
import com.wanos.careditproject.data.bean.ProjectTagBean;
import com.wanos.careditproject.data.repo.LoadStatus;
import com.wanos.careditproject.databinding.FragmentCreatorWorksBinding;
import com.wanos.careditproject.event.DeleteWorkEvent;
import com.wanos.careditproject.ui.adapter.CreatorCommonChildAdapter;
import com.wanos.careditproject.ui.strategy.CreatorHomeTagItemStrategy;
import com.wanos.careditproject.ui.strategy.CreatorMineWorkItemStrategy;
import com.wanos.careditproject.ui.viewholder.CreatorMineWorkItemViewHolder;
import com.wanos.careditproject.ui.viewholder.HomeTagItemViewHolder;
import com.wanos.careditproject.ui.viewmodel.CreatorMineWorkListViewModel;
import com.wanos.media.base.WanosBaseFragment;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes3.dex */
public class CreatorWorksFragment extends WanosBaseFragment {
    private CreatorCommonChildAdapter<ProjectInfo, CreatorMineWorkItemViewHolder> mAdapter;
    private FragmentCreatorWorksBinding mBinding;
    private CreatorMineWorkListViewModel mViewModel;

    @Override // com.wanos.media.base.WanosBaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mViewModel = (CreatorMineWorkListViewModel) new ViewModelProvider(this).get(CreatorMineWorkListViewModel.class);
        if (bundle != null) {
            this.mViewModel.setType(bundle.getInt("type"));
        } else if (getArguments() != null) {
            this.mViewModel.setType(getArguments().getInt("type", -1));
        }
    }

    @Override // com.wanos.media.base.WanosBaseFragment
    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = FragmentCreatorWorksBinding.inflate(layoutInflater, viewGroup, false);
        initView();
        addObservers();
        return this.mBinding.getRoot();
    }

    private void initView() {
        CreatorCommonChildAdapter<ProjectInfo, CreatorMineWorkItemViewHolder> creatorCommonChildAdapter = new CreatorCommonChildAdapter<>(new DiffUtil.ItemCallback<ProjectInfo>() { // from class: com.wanos.careditproject.ui.fragment.CreatorWorksFragment.1
            @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
            public boolean areItemsTheSame(ProjectInfo projectInfo, ProjectInfo projectInfo2) {
                return Objects.equals(projectInfo.getId(), projectInfo2.getId());
            }

            @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
            public boolean areContentsTheSame(ProjectInfo projectInfo, ProjectInfo projectInfo2) {
                return projectInfo.equals(projectInfo2);
            }
        });
        this.mAdapter = creatorCommonChildAdapter;
        creatorCommonChildAdapter.registerStrategy(new CreatorMineWorkItemStrategy(this.mViewModel.getType()));
        this.mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.mBinding.recyclerView.setItemAnimator(null);
        this.mBinding.recyclerView.setAdapter(this.mAdapter);
        if (this.mViewModel.getType() != 4) {
            this.mBinding.rvTagList.setVisibility(8);
        } else {
            this.mBinding.rvTagList.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
            CreatorCommonChildAdapter creatorCommonChildAdapter2 = new CreatorCommonChildAdapter(new DiffUtil.ItemCallback<ProjectTagBean>() { // from class: com.wanos.careditproject.ui.fragment.CreatorWorksFragment.2
                @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
                public boolean areItemsTheSame(ProjectTagBean projectTagBean, ProjectTagBean projectTagBean2) {
                    return projectTagBean.getId() == projectTagBean2.getId();
                }

                @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
                public boolean areContentsTheSame(ProjectTagBean projectTagBean, ProjectTagBean projectTagBean2) {
                    return projectTagBean.equals(projectTagBean2);
                }
            });
            CreatorHomeTagItemStrategy creatorHomeTagItemStrategy = new CreatorHomeTagItemStrategy();
            creatorCommonChildAdapter2.registerStrategy(creatorHomeTagItemStrategy);
            this.mBinding.rvTagList.setAdapter(creatorCommonChildAdapter2);
            this.mBinding.rvTagList.setItemAnimator(null);
            creatorHomeTagItemStrategy.setOnCheckListener(new HomeTagItemViewHolder.OnCheckListener() { // from class: com.wanos.careditproject.ui.fragment.CreatorWorksFragment$$ExternalSyntheticLambda3
                @Override // com.wanos.careditproject.ui.viewholder.HomeTagItemViewHolder.OnCheckListener
                public final void onCheck(int i, int i2) {
                    this.f$0.m394x88c3aee5(i, i2);
                }
            });
        }
        if (this.mViewModel.getType() == 1 || this.mViewModel.getType() == 2) {
            this.mBinding.recyclerView.setPadding(0, 0, getResources().getDimensionPixelSize(R.dimen.creator_mine_works_page_padding_end), 0);
        }
        this.mBinding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.wanos.careditproject.ui.fragment.CreatorWorksFragment.3
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                CreatorWorksFragment.this.checkNeedLoadMore();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initView$0$com-wanos-careditproject-ui-fragment-CreatorWorksFragment, reason: not valid java name */
    /* synthetic */ void m394x88c3aee5(int i, int i2) {
        this.mViewModel.onTagSelected(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkNeedLoadMore() {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) this.mBinding.recyclerView.getLayoutManager();
        if (linearLayoutManager != null) {
            int itemCount = linearLayoutManager.getItemCount();
            int iFindLastCompletelyVisibleItemPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
            if (this.mViewModel.isHasMoreData() && iFindLastCompletelyVisibleItemPosition >= itemCount - 1) {
                this.mViewModel.loadMore();
            }
        }
    }

    private void addObservers() {
        this.mViewModel.getProjectInfoList().observe(this, new Observer() { // from class: com.wanos.careditproject.ui.fragment.CreatorWorksFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m391x68f7718d((List) obj);
            }
        });
        this.mViewModel.getLoadStatus().observe(this, new Observer() { // from class: com.wanos.careditproject.ui.fragment.CreatorWorksFragment$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m392x79ad3e4e((LoadStatus) obj);
            }
        });
        if (this.mViewModel.getType() == 4) {
            this.mViewModel.getTagList().observe(this, new Observer() { // from class: com.wanos.careditproject.ui.fragment.CreatorWorksFragment$$ExternalSyntheticLambda2
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    this.f$0.m393x8a630b0f((List) obj);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$addObservers$1$com-wanos-careditproject-ui-fragment-CreatorWorksFragment, reason: not valid java name */
    /* synthetic */ void m391x68f7718d(List list) {
        this.mAdapter.submitList(new ArrayList(list));
        this.mAdapter.notifyDataSetChanged();
        if (list.isEmpty()) {
            this.mBinding.emptyView.getRoot().setVisibility(0);
            this.mBinding.emptyView.tvEmpty.setText(R.string.creator_empty_0);
        } else {
            this.mBinding.emptyView.getRoot().setVisibility(8);
        }
    }

    /* JADX INFO: renamed from: com.wanos.careditproject.ui.fragment.CreatorWorksFragment$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$wanos$careditproject$data$repo$LoadStatus;

        static {
            int[] iArr = new int[LoadStatus.values().length];
            $SwitchMap$com$wanos$careditproject$data$repo$LoadStatus = iArr;
            try {
                iArr[LoadStatus.DEFAULT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$wanos$careditproject$data$repo$LoadStatus[LoadStatus.FAIL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$wanos$careditproject$data$repo$LoadStatus[LoadStatus.SUCCESS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: renamed from: lambda$addObservers$2$com-wanos-careditproject-ui-fragment-CreatorWorksFragment, reason: not valid java name */
    /* synthetic */ void m392x79ad3e4e(LoadStatus loadStatus) {
        int i = AnonymousClass4.$SwitchMap$com$wanos$careditproject$data$repo$LoadStatus[loadStatus.ordinal()];
        if (i == 1) {
            showLoadingView();
        } else if (i == 2 || i == 3) {
            closeLoadingView();
            checkNeedLoadMore();
        }
    }

    /* JADX INFO: renamed from: lambda$addObservers$3$com-wanos-careditproject-ui-fragment-CreatorWorksFragment, reason: not valid java name */
    /* synthetic */ void m393x8a630b0f(List list) {
        if (list.isEmpty()) {
            this.mBinding.emptyView.getRoot().setVisibility(0);
            this.mBinding.emptyView.tvEmpty.setText(R.string.creator_empty_0);
        } else {
            this.mBinding.emptyView.getRoot().setVisibility(8);
        }
        ((CreatorCommonChildAdapter) this.mBinding.rvTagList.getAdapter()).submitList(new ArrayList(list));
    }

    @Override // com.wanos.media.base.WanosBaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        CreatorMineWorkListViewModel creatorMineWorkListViewModel = this.mViewModel;
        if (creatorMineWorkListViewModel != null) {
            creatorMineWorkListViewModel.loadData();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("type", this.mViewModel.getType());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDeleteWork(DeleteWorkEvent deleteWorkEvent) {
        CreatorMineWorkListViewModel creatorMineWorkListViewModel = this.mViewModel;
        if (creatorMineWorkListViewModel != null) {
            creatorMineWorkListViewModel.deleteWork(deleteWorkEvent.getProjectInfoId());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onProjectInfoEvent(ProjectInfo projectInfo) {
        CreatorMineWorkListViewModel creatorMineWorkListViewModel = this.mViewModel;
        if (creatorMineWorkListViewModel != null) {
            creatorMineWorkListViewModel.onProjectInfoChange(projectInfo);
        }
    }
}
