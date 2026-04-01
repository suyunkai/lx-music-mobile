package com.wanos.media.juyihall.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.wanos.WanosCommunication.bean.MusicGroupInfo;
import com.wanos.commonlibrary.bean.SingerInfoBean;
import com.wanos.commonlibrary.utils.GlideUtil;
import com.wanos.media.juyihall.R;
import com.wanos.media.juyihall.viewholder.Rec2AlbumVH;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class Rec2AlbumAdapter extends BaseListAdapter<MusicGroupInfo, Rec2AlbumVH> {
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public Rec2AlbumVH onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new Rec2AlbumVH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rec2_album, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(Rec2AlbumVH rec2AlbumVH, int i) {
        List<SingerInfoBean> singerList;
        MusicGroupInfo item = getItem(i);
        rec2AlbumVH.tvName.setText(item.getName());
        if (item.getMusicList() != null && !item.getMusicList().isEmpty() && (singerList = item.getMusicList().get(0).getSingerList()) != null && !singerList.isEmpty()) {
            rec2AlbumVH.tvDesc.setText(singerList.get(0).getName());
        }
        GlideUtil.setImageData(item.getAvatar(), rec2AlbumVH.ivAvatar, 264, 264);
        rec2AlbumVH.bindData(item);
    }
}
