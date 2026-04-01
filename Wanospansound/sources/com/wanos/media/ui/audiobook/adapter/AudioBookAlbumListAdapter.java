package com.wanos.media.ui.audiobook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wanos.WanosCommunication.bean.AudioBookAlbumInfoBean;
import com.wanos.media.R;
import com.wanos.media.ui.audiobook.adapter.AudioBookAlbumListBaseAdapter;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class AudioBookAlbumListAdapter extends AudioBookAlbumListBaseAdapter {
    private static final int ITEM_TYPE_NORMAL = 0;
    private static final int ITEM_TYPE_PADDING = 1;

    public AudioBookAlbumListAdapter(Context context, List<AudioBookAlbumInfoBean> datas) {
        super(context, datas);
    }

    @Override // com.wanos.media.ui.audiobook.adapter.AudioBookAlbumListBaseAdapter, com.wanos.commonlibrary.base.BaseRecycleAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public AudioBookAlbumListBaseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 4371) {
            return new AudioBookAlbumListBaseAdapter.ViewHolder(this.mFooterView);
        }
        if (viewType == 4370) {
            return new AudioBookAlbumListBaseAdapter.ViewHolder(this.mHeaderView);
        }
        if (viewType == 0) {
            return new AudioBookAlbumListBaseAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_audio_book_home_album_item, parent, false));
        }
        return new PaddingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_wanos_music_group_item_padding_bottom, parent, false));
    }

    @Override // com.wanos.commonlibrary.base.BaseRecycleAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.datas == null) {
            return 0;
        }
        return this.datas.size() + 7;
    }

    @Override // com.wanos.commonlibrary.base.BaseRecycleAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        return position >= this.datas.size() ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.wanos.media.ui.audiobook.adapter.AudioBookAlbumListBaseAdapter, com.wanos.commonlibrary.base.BaseRecycleAdapter
    public void bindData(AudioBookAlbumListBaseAdapter.ViewHolder holder, int position) {
        super.bindData(holder, position);
    }

    public static class PaddingViewHolder extends AudioBookAlbumListBaseAdapter.ViewHolder {
        public PaddingViewHolder(View itemView) {
            super(itemView);
        }
    }
}
