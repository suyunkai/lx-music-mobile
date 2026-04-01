package com.wanos.media.ui.audiobook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.wanos.WanosCommunication.bean.AudioBookAlbumInfoBean;
import com.wanos.media.R;
import com.wanos.media.ui.audiobook.AudioBookGlobalParams;
import com.wanos.media.ui.audiobook.adapter.AudioBookAlbumListBaseAdapter;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class AudioBookMineFollowListAdapter extends AudioBookAlbumListBaseAdapter {
    public AudioBookMineFollowListAdapter(Context context, List<AudioBookAlbumInfoBean> datas) {
        super(context, datas);
    }

    @Override // com.wanos.media.ui.audiobook.adapter.AudioBookAlbumListBaseAdapter, com.wanos.commonlibrary.base.BaseRecycleAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public AudioBookAlbumListBaseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AudioBookAlbumListBaseAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_audio_book_mine_album_item, parent, false));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.wanos.media.ui.audiobook.adapter.AudioBookAlbumListBaseAdapter, com.wanos.commonlibrary.base.BaseRecycleAdapter
    public void bindData(AudioBookAlbumListBaseAdapter.ViewHolder holder, int position) {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.width = (holder.itemView.getContext().getResources().getDisplayMetrics().widthPixels * 280) / AudioBookGlobalParams.getScreenWidth();
        holder.itemView.setLayoutParams(layoutParams);
        super.bindData(holder, position);
    }
}
