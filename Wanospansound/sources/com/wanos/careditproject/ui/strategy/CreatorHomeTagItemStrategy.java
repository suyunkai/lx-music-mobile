package com.wanos.careditproject.ui.strategy;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.wanos.careditproject.data.bean.ProjectTagBean;
import com.wanos.careditproject.databinding.ItemCreatorHomeTagItemBinding;
import com.wanos.careditproject.ui.viewholder.HomeTagItemViewHolder;

/* JADX INFO: loaded from: classes3.dex */
public class CreatorHomeTagItemStrategy extends CreatorBaseStrategy<ProjectTagBean, HomeTagItemViewHolder> {
    private HomeTagItemViewHolder.OnCheckListener mOnCheckListener;

    public void setOnCheckListener(HomeTagItemViewHolder.OnCheckListener onCheckListener) {
        this.mOnCheckListener = onCheckListener;
    }

    @Override // com.wanos.careditproject.ui.strategy.CreatorBaseStrategy
    public HomeTagItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new HomeTagItemViewHolder(ItemCreatorHomeTagItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    @Override // com.wanos.careditproject.ui.strategy.CreatorBaseStrategy
    public void onBindViewHolder(HomeTagItemViewHolder homeTagItemViewHolder, ProjectTagBean projectTagBean, int i) {
        homeTagItemViewHolder.setOnCheckListener(this.mOnCheckListener, i);
        homeTagItemViewHolder.bind(projectTagBean);
    }
}
