package com.wanos.media.juyihall.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.wanos.commonlibrary.bean.MusicInfoBean;
import com.wanos.commonlibrary.utils.GlideUtil;
import com.wanos.media.juyihall.R;
import com.wanos.media.juyihall.viewholder.Rec2MusicVH;

/* JADX INFO: loaded from: classes3.dex */
public class Rec2MusicAdapter extends BaseListAdapter<MusicInfoBean, Rec2MusicVH> {
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public Rec2MusicVH onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new Rec2MusicVH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rec2_music, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(Rec2MusicVH rec2MusicVH, int i) {
        MusicInfoBean item = getItem(i);
        rec2MusicVH.tvname.setText(item.getName());
        if (item.getSingerList() != null && !item.getSingerList().isEmpty()) {
            rec2MusicVH.tvDesc.setText(item.getSingerList().get(0).getName());
        }
        GlideUtil.setImageData(item.getAvatar(), rec2MusicVH.ivAvatar, 352, 352);
        rec2MusicVH.bindData(item);
    }
}
