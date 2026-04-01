package com.wanos.careditproject.ui.strategy;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.wanos.bean.ProjectInfo;
import com.wanos.careditproject.databinding.ItemCreatorHomeCardItemBinding;
import com.wanos.careditproject.ui.viewholder.HomeCardItemViewHolder;

/* JADX INFO: loaded from: classes3.dex */
public class CreatorHomeCardItemStrategy extends CreatorBaseStrategy<ProjectInfo, HomeCardItemViewHolder> {
    @Override // com.wanos.careditproject.ui.strategy.CreatorBaseStrategy
    public HomeCardItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new HomeCardItemViewHolder(ItemCreatorHomeCardItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    @Override // com.wanos.careditproject.ui.strategy.CreatorBaseStrategy
    public void onBindViewHolder(HomeCardItemViewHolder homeCardItemViewHolder, ProjectInfo projectInfo, int i) {
        homeCardItemViewHolder.bind(projectInfo);
    }

    @Override // com.wanos.careditproject.ui.strategy.CreatorBaseStrategy
    public void onUnbind(HomeCardItemViewHolder homeCardItemViewHolder) {
        super.onUnbind(homeCardItemViewHolder);
        homeCardItemViewHolder.unbind();
    }
}
