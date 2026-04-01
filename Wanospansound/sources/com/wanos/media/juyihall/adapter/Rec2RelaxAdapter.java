package com.wanos.media.juyihall.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.commonlibrary.utils.GlideUtil;
import com.wanos.media.juyihall.R;
import com.wanos.media.juyihall.viewholder.Rec2RelaxVH;

/* JADX INFO: loaded from: classes3.dex */
public class Rec2RelaxAdapter extends BaseListAdapter<MusicInfoBean, Rec2RelaxVH> {
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public Rec2RelaxVH onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new Rec2RelaxVH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rec2_relax, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(Rec2RelaxVH rec2RelaxVH, int i) {
        MusicInfoBean item = getItem(i);
        rec2RelaxVH.tvName.setText(item.getName());
        GlideUtil.setImageData(item.getAvatar(), rec2RelaxVH.ivAvatar, 358, 358);
        rec2RelaxVH.bindData(item);
    }
}
