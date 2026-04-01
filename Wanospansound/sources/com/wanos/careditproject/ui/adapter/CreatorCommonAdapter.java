package com.wanos.careditproject.ui.adapter;

import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import com.wanos.careditproject.ui.strategy.CreatorBaseStrategy;
import com.wanos.careditproject.ui.viewholder.BaseViewHolder;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class CreatorCommonAdapter extends ListAdapter<CreatorCommonItem, BaseViewHolder<?, CreatorCommonItem>> {
    private int bottomMargin;
    private Map<Integer, CreatorBaseStrategy<CreatorCommonItem, BaseViewHolder<?, CreatorCommonItem>>> mStrategyMap;

    public void setBottomMargin(int i) {
        this.bottomMargin = i;
    }

    public CreatorCommonAdapter(DiffUtil.ItemCallback<CreatorCommonItem> itemCallback) {
        super(itemCallback);
        this.mStrategyMap = new HashMap();
    }

    public void registerStrategy(int i, CreatorBaseStrategy creatorBaseStrategy) {
        this.mStrategyMap.put(Integer.valueOf(i), creatorBaseStrategy);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public BaseViewHolder<?, CreatorCommonItem> onCreateViewHolder(ViewGroup viewGroup, int i) {
        return this.mStrategyMap.get(Integer.valueOf(i)).onCreateViewHolder(viewGroup, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(BaseViewHolder<?, CreatorCommonItem> baseViewHolder, int i) {
        this.mStrategyMap.get(Integer.valueOf(getItemViewType(i))).onBindViewHolder(baseViewHolder, getItem(i), i);
        if (this.bottomMargin > 0) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) baseViewHolder.itemView.getLayoutParams();
            if (i == getItemCount() - 1) {
                marginLayoutParams.bottomMargin = this.bottomMargin;
            } else {
                marginLayoutParams.bottomMargin = 0;
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return getItem(i).getType();
    }
}
