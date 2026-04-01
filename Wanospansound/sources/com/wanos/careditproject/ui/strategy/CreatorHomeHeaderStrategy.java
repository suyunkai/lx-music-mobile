package com.wanos.careditproject.ui.strategy;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.wanos.careditproject.databinding.ItemCreatorHomeHeaderBinding;
import com.wanos.careditproject.ui.adapter.CreatorCommonItem;
import com.wanos.careditproject.ui.viewholder.HomeHeaderViewHolder;

/* JADX INFO: loaded from: classes3.dex */
public class CreatorHomeHeaderStrategy extends CreatorBaseStrategy<CreatorCommonItem, HomeHeaderViewHolder> {
    @Override // com.wanos.careditproject.ui.strategy.CreatorBaseStrategy
    public HomeHeaderViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new HomeHeaderViewHolder(ItemCreatorHomeHeaderBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    @Override // com.wanos.careditproject.ui.strategy.CreatorBaseStrategy
    public void onBindViewHolder(HomeHeaderViewHolder homeHeaderViewHolder, CreatorCommonItem creatorCommonItem, int i) {
        homeHeaderViewHolder.bind(creatorCommonItem);
    }
}
