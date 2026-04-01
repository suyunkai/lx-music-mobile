package com.wanos.careditproject.ui.strategy;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.wanos.careditproject.data.bean.ProjectTagBean;
import com.wanos.careditproject.databinding.ItemCreatorHomeTagListBinding;
import com.wanos.careditproject.ui.adapter.CreatorCommonChildAdapter;
import com.wanos.careditproject.ui.adapter.CreatorCommonItem;
import com.wanos.careditproject.ui.viewholder.HomeTagItemViewHolder;
import com.wanos.careditproject.ui.viewholder.HomeTagListViewHolder;
import com.wanos.careditproject.ui.viewmodel.CreatorHomeViewModel;

/* JADX INFO: loaded from: classes3.dex */
public class CreatorHomeTagListStrategy extends CreatorBaseStrategy<CreatorCommonItem, HomeTagListViewHolder> {
    private CreatorHomeTagItemStrategy strategy;
    private CreatorHomeViewModel viewModel;

    public CreatorHomeTagListStrategy(CreatorHomeViewModel creatorHomeViewModel) {
        this.viewModel = creatorHomeViewModel;
    }

    @Override // com.wanos.careditproject.ui.strategy.CreatorBaseStrategy
    public HomeTagListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ItemCreatorHomeTagListBinding itemCreatorHomeTagListBindingInflate = ItemCreatorHomeTagListBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        itemCreatorHomeTagListBindingInflate.rvTagList.setLayoutManager(new LinearLayoutManager(viewGroup.getContext(), 0, false));
        CreatorCommonChildAdapter creatorCommonChildAdapter = new CreatorCommonChildAdapter(new DiffUtil.ItemCallback<ProjectTagBean>() { // from class: com.wanos.careditproject.ui.strategy.CreatorHomeTagListStrategy.1
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
        this.strategy = creatorHomeTagItemStrategy;
        creatorCommonChildAdapter.registerStrategy(creatorHomeTagItemStrategy);
        itemCreatorHomeTagListBindingInflate.rvTagList.setAdapter(creatorCommonChildAdapter);
        itemCreatorHomeTagListBindingInflate.rvTagList.setItemAnimator(null);
        return new HomeTagListViewHolder(itemCreatorHomeTagListBindingInflate);
    }

    /* JADX INFO: renamed from: lambda$onBindViewHolder$0$com-wanos-careditproject-ui-strategy-CreatorHomeTagListStrategy, reason: not valid java name */
    /* synthetic */ void m395x829e0b74(CreatorCommonItem creatorCommonItem, int i, int i2) {
        this.viewModel.onTagSelected(creatorCommonItem.getId(), i2);
    }

    @Override // com.wanos.careditproject.ui.strategy.CreatorBaseStrategy
    public void onBindViewHolder(HomeTagListViewHolder homeTagListViewHolder, final CreatorCommonItem creatorCommonItem, int i) {
        this.strategy.setOnCheckListener(new HomeTagItemViewHolder.OnCheckListener() { // from class: com.wanos.careditproject.ui.strategy.CreatorHomeTagListStrategy$$ExternalSyntheticLambda0
            @Override // com.wanos.careditproject.ui.viewholder.HomeTagItemViewHolder.OnCheckListener
            public final void onCheck(int i2, int i3) {
                this.f$0.m395x829e0b74(creatorCommonItem, i2, i3);
            }
        });
        homeTagListViewHolder.bind((ProjectTagBean) creatorCommonItem.getData());
    }
}
