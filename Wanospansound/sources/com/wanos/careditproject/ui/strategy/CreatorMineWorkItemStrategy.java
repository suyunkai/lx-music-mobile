package com.wanos.careditproject.ui.strategy;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.wanos.bean.ProjectInfo;
import com.wanos.careditproject.databinding.ItemCreatorMineWorkBinding;
import com.wanos.careditproject.ui.viewholder.CreatorMineWorkItemViewHolder;
import com.wanos.careditproject.ui.viewmodel.CreatorMineWorkItemViewModel;
import com.wanos.careditproject.ui.viewmodel.CreatorWorkTypeViewModel;

/* JADX INFO: loaded from: classes3.dex */
public class CreatorMineWorkItemStrategy extends CreatorBaseStrategy<ProjectInfo, CreatorMineWorkItemViewHolder> {
    private final CreatorWorkTypeViewModel workType;

    public CreatorMineWorkItemStrategy(int i) {
        this.workType = new CreatorWorkTypeViewModel(i);
    }

    @Override // com.wanos.careditproject.ui.strategy.CreatorBaseStrategy
    public CreatorMineWorkItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new CreatorMineWorkItemViewHolder(ItemCreatorMineWorkBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    @Override // com.wanos.careditproject.ui.strategy.CreatorBaseStrategy
    public void onBindViewHolder(final CreatorMineWorkItemViewHolder creatorMineWorkItemViewHolder, final ProjectInfo projectInfo, int i) {
        final CreatorMineWorkItemViewModel creatorMineWorkItemViewModel = new CreatorMineWorkItemViewModel(projectInfo, this.workType);
        creatorMineWorkItemViewHolder.bind(creatorMineWorkItemViewModel);
        creatorMineWorkItemViewModel.getRefreshEvent().observe((LifecycleOwner) creatorMineWorkItemViewHolder.itemView.getContext(), new Observer() { // from class: com.wanos.careditproject.ui.strategy.CreatorMineWorkItemStrategy$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m396xee13cd62(projectInfo, creatorMineWorkItemViewHolder, creatorMineWorkItemViewModel, (Boolean) obj);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$onBindViewHolder$0$com-wanos-careditproject-ui-strategy-CreatorMineWorkItemStrategy, reason: not valid java name */
    /* synthetic */ void m396xee13cd62(ProjectInfo projectInfo, CreatorMineWorkItemViewHolder creatorMineWorkItemViewHolder, CreatorMineWorkItemViewModel creatorMineWorkItemViewModel, Boolean bool) {
        this.workType.setData(projectInfo);
        creatorMineWorkItemViewHolder.bind(creatorMineWorkItemViewModel);
    }

    @Override // com.wanos.careditproject.ui.strategy.CreatorBaseStrategy
    public void onUnbind(CreatorMineWorkItemViewHolder creatorMineWorkItemViewHolder) {
        super.onUnbind(creatorMineWorkItemViewHolder);
        creatorMineWorkItemViewHolder.unbind();
    }
}
