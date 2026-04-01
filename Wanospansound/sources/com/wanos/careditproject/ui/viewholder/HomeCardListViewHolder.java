package com.wanos.careditproject.ui.viewholder;

import com.wanos.bean.ProjectInfo;
import com.wanos.careditproject.databinding.ItemCreatorHomeCardListBinding;
import com.wanos.careditproject.ui.adapter.CreatorCommonChildAdapter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class HomeCardListViewHolder extends BaseViewHolder<ItemCreatorHomeCardListBinding, List<ProjectInfo>> {
    @Override // com.wanos.careditproject.ui.viewholder.BaseViewHolder
    protected void onUnbind() {
    }

    public HomeCardListViewHolder(ItemCreatorHomeCardListBinding itemCreatorHomeCardListBinding) {
        super(itemCreatorHomeCardListBinding);
    }

    @Override // com.wanos.careditproject.ui.viewholder.BaseViewHolder
    protected void onBind() {
        if (((ItemCreatorHomeCardListBinding) this.binding).recyclerView.getAdapter() instanceof CreatorCommonChildAdapter) {
            CreatorCommonChildAdapter creatorCommonChildAdapter = (CreatorCommonChildAdapter) ((ItemCreatorHomeCardListBinding) this.binding).recyclerView.getAdapter();
            creatorCommonChildAdapter.submitList(new ArrayList((Collection) this.data));
            creatorCommonChildAdapter.notifyDataSetChanged();
        }
    }
}
