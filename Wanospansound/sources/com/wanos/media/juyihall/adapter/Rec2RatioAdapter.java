package com.wanos.media.juyihall.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.wanos.WanosCommunication.bean.AudioBookAlbumInfoBean;
import com.wanos.commonlibrary.utils.GlideUtil;
import com.wanos.media.juyihall.R;
import com.wanos.media.juyihall.viewholder.Rec2RatioVH;

/* JADX INFO: loaded from: classes3.dex */
public class Rec2RatioAdapter extends BaseListAdapter<AudioBookAlbumInfoBean, Rec2RatioVH> {
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public Rec2RatioVH onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new Rec2RatioVH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rec2_ratio, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(Rec2RatioVH rec2RatioVH, int i) {
        AudioBookAlbumInfoBean item = getItem(i);
        rec2RatioVH.tvName.setText(item.getName());
        rec2RatioVH.tvDesc.setText(item.getSubtitle());
        if (item.getChapterList() != null && item.getChapterList().size() > 1) {
            rec2RatioVH.tvEpisodes.setText(item.getChapterList().get(1).getName());
            rec2RatioVH.tvEpisodes2.setText(item.getChapterList().get(0).getName());
        }
        GlideUtil.setImageData(item.getAvatar(), rec2RatioVH.ivAvatar, 235, 235);
        if (item.getWritingStatus() == 1) {
            rec2RatioVH.ivAlbumState.setImageResource(com.wanos.media.R.drawable.ab_album_serialization);
        } else {
            rec2RatioVH.ivAlbumState.setImageResource(com.wanos.media.R.drawable.ab_album_end);
        }
        rec2RatioVH.bindData(item);
    }
}
