package com.wanos.careditproject.ui.viewholder;

import com.wanos.careditproject.data.bean.ProjectTagBean;
import com.wanos.careditproject.databinding.ItemCreatorHomeTagListBinding;
import com.wanos.careditproject.ui.adapter.CreatorCommonChildAdapter;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class HomeTagListViewHolder extends BaseViewHolder<ItemCreatorHomeTagListBinding, ProjectTagBean> {
    @Override // com.wanos.careditproject.ui.viewholder.BaseViewHolder
    protected void onUnbind() {
    }

    public HomeTagListViewHolder(ItemCreatorHomeTagListBinding itemCreatorHomeTagListBinding) {
        super(itemCreatorHomeTagListBinding);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.wanos.careditproject.ui.viewholder.BaseViewHolder
    protected void onBind() {
        if (!(((ItemCreatorHomeTagListBinding) this.binding).rvTagList.getAdapter() instanceof CreatorCommonChildAdapter) || this.data == 0) {
            return;
        }
        ((CreatorCommonChildAdapter) ((ItemCreatorHomeTagListBinding) this.binding).rvTagList.getAdapter()).submitList(new ArrayList(((ProjectTagBean) this.data).getList()));
    }
}
