package com.wanos.media.juyihall.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wanos.commonlibrary.bean.TagInfoBean;
import com.wanos.media.juyihall.R;
import com.wanos.media.juyihall.viewholder.Rec2TagVH;

/* JADX INFO: loaded from: classes3.dex */
public class Rec2TagsAdapter extends BaseListAdapter<TagInfoBean, Rec2TagVH> {
    private static final int[] bgList = {R.drawable.bg_rec2_tag1, R.drawable.bg_rec2_tag2, R.drawable.bg_rec2_tag3, R.drawable.bg_rec2_tag4, R.drawable.bg_rec2_tag5, R.drawable.bg_rec2_tag6};

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public Rec2TagVH onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new Rec2TagVH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rec2_tag, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(Rec2TagVH rec2TagVH, int i) {
        TagInfoBean item = getItem(i);
        rec2TagVH.tvName.setText(item.getName());
        View view = rec2TagVH.itemView;
        int[] iArr = bgList;
        view.setBackgroundResource(iArr[i % iArr.length]);
        rec2TagVH.bindData(item);
    }
}
