package com.wanos.careditproject.ui.strategy;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.wanos.bean.ProjectInfo;
import com.wanos.careditproject.databinding.ItemCreatorHomeCardListBinding;
import com.wanos.careditproject.ui.adapter.CreatorCommonChildAdapter;
import com.wanos.careditproject.ui.adapter.CreatorCommonItem;
import com.wanos.careditproject.ui.viewholder.HomeCardListViewHolder;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class CreatorHomeCardListStrategy extends CreatorBaseStrategy<CreatorCommonItem, HomeCardListViewHolder> {
    @Override // com.wanos.careditproject.ui.strategy.CreatorBaseStrategy
    public HomeCardListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ItemCreatorHomeCardListBinding itemCreatorHomeCardListBindingInflate = ItemCreatorHomeCardListBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        itemCreatorHomeCardListBindingInflate.recyclerView.setLayoutManager(new LinearLayoutManager(viewGroup.getContext(), 0, false));
        CreatorCommonChildAdapter creatorCommonChildAdapter = new CreatorCommonChildAdapter(new DiffUtil.ItemCallback<ProjectInfo>() { // from class: com.wanos.careditproject.ui.strategy.CreatorHomeCardListStrategy.1
            @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
            public boolean areItemsTheSame(ProjectInfo projectInfo, ProjectInfo projectInfo2) {
                return TextUtils.equals(projectInfo.getId(), projectInfo2.getId());
            }

            @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
            public boolean areContentsTheSame(ProjectInfo projectInfo, ProjectInfo projectInfo2) {
                return projectInfo.equals(projectInfo2);
            }
        });
        creatorCommonChildAdapter.registerStrategy(new CreatorHomeCardItemStrategy());
        itemCreatorHomeCardListBindingInflate.recyclerView.setAdapter(creatorCommonChildAdapter);
        itemCreatorHomeCardListBindingInflate.recyclerView.setItemAnimator(null);
        return new HomeCardListViewHolder(itemCreatorHomeCardListBindingInflate);
    }

    @Override // com.wanos.careditproject.ui.strategy.CreatorBaseStrategy
    public void onBindViewHolder(HomeCardListViewHolder homeCardListViewHolder, CreatorCommonItem creatorCommonItem, int i) {
        homeCardListViewHolder.bind((List) creatorCommonItem.getData());
    }
}
