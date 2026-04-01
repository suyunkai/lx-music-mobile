package com.wanos.careditproject.ui.strategy;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.wanos.careditproject.databinding.ItemCreatorHomeTitleBinding;
import com.wanos.careditproject.ui.adapter.CreatorCommonItem;
import com.wanos.careditproject.ui.viewholder.HomeTitleViewHolder;

/* JADX INFO: loaded from: classes3.dex */
public class CreatorHomeTitleStrategy extends CreatorBaseStrategy<CreatorCommonItem, HomeTitleViewHolder> {
    @Override // com.wanos.careditproject.ui.strategy.CreatorBaseStrategy
    public void onBindViewHolder(HomeTitleViewHolder homeTitleViewHolder, CreatorCommonItem creatorCommonItem, int i) {
    }

    @Override // com.wanos.careditproject.ui.strategy.CreatorBaseStrategy
    public HomeTitleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new HomeTitleViewHolder(ItemCreatorHomeTitleBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }
}
