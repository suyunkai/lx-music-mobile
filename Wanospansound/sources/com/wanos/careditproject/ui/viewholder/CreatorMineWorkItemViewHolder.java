package com.wanos.careditproject.ui.viewholder;

import com.wanos.careditproject.databinding.ItemCreatorMineWorkBinding;
import com.wanos.careditproject.ui.viewmodel.CreatorMineWorkItemViewModel;

/* JADX INFO: loaded from: classes3.dex */
public class CreatorMineWorkItemViewHolder extends BaseViewHolder<ItemCreatorMineWorkBinding, CreatorMineWorkItemViewModel> {
    @Override // com.wanos.careditproject.ui.viewholder.BaseViewHolder
    protected void onUnbind() {
    }

    public CreatorMineWorkItemViewHolder(ItemCreatorMineWorkBinding itemCreatorMineWorkBinding) {
        super(itemCreatorMineWorkBinding);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wanos.careditproject.ui.viewholder.BaseViewHolder
    protected void onBind() {
        ((CreatorMineWorkItemViewModel) this.data).workType.setData(((CreatorMineWorkItemViewModel) this.data).getInfo().getValue());
    }
}
